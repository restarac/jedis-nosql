package redis.cep.dao.jedis;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import redis.cep.dao.CepDao;
import redis.cep.model.Cep;
import redis.cep.model.CepFactory;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CepDaoJedis implements CepDao {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	@Inject
	@CepConnection
	private Jedis jedis;

	/**
	 * A cada 50 inserts chama o comando para salvar o dataset, enquanto novos
	 * estao sendo salvos. Processo de AOF com algumas falhas, no entanto
	 * rapido.
	 * 
	 * <ul>
	 * <li>The RDB persistence performs point-in-time snapshots of your dataset
	 * at specified intervals.</li>
	 * <li>the AOF persistence logs every write operation received by the
	 * server, that will be played again at server startup, reconstructing the
	 * original dataset. Commands are logged using the same format as the Redis
	 * protocol itself, in an append-only fashion. Redis is able to rewrite the
	 * log on background when it gets too big.</li>
	 * </ul>
	 * 
	 * @see http://redis.io/topics/persistence
	 */
	public void populateFastButWithLoseCep() {
		jedis.flushDB();
		Client client = jedis.getClient();
		client.multi();
		for (int i = 1; i <= MAX_CEP_VALUE; i++) {
			if (i % 50 == 0) {
				client.exec();
				client.bgsave();
				System.out.println("Salvou: " + i);
			}
			Cep cep = CepFactory.create(i);
			client.set(cep.getCep(), GSON.toJson(cep));
		}
		client.exec();
		client.close();
	}

	/**
	 * Salva todo o dataset no disco no final da gravacao.
	 */
	public void populateFastButNoLose() {
		jedis.flushDB();
		Transaction t = jedis.multi();
		for (int i = 1; i <= MAX_CEP_VALUE; i++) {
			Cep cep = CepFactory.create(i);
			t.set(cep.getCep(), GSON.toJson(cep));
		}
		t.exec();
		t.save();
		t.bgrewriteaof();
	}

	public Long countObjects() {
		try {
			// Me parece um bug pois no test eventualmente da erro...
			//
			// java.lang.ClassCastException: [B cannot be cast to java.lang.Long
			// at
			// redis.clients.jedis.Connection.getIntegerReply(Connection.java:201)
			// at redis.clients.jedis.BinaryJedis.dbSize(BinaryJedis.java:296)
			// at cache.cep.redis.dao.CepDao.countObjects(CepDao.java:56)
			// at
			// cache.cep.redis.dao.CepDaoTest.testpopulateFastButNoLoseCep(CepDaoTest.java:64)
			// at
			// cache.cep.redis.dao.CepDaoTest$Proxy$_$$_WeldClientProxy.testpopulateFastButNoLoseCep(Unknown
			// Source)
			return jedis.dbSize();
		} catch (java.lang.ClassCastException e) {
			return jedis.dbSize();
		}
	}

	public boolean testConnection() {
		return "PONG".equals(jedis.ping());
	}

	public void save(Cep cep) {
		
		if (cep == null || StringUtils.isBlank(cep.getCep())){
			throw new IllegalStateException("Necesário um codigo de CEP");
		}
		jedis.set(cep.getCep(), GSON.toJson(cep));
	}

	public List<Cep> listAll() {
		
		//**DO NOT USE - KEYS**

		List<String> scan = jedis.scan("0").getResult();
//		scan.
		
		
		return null;
	}

	public Cep load(String cepNumber) {
		String jsonCep = jedis.get(cepNumber);
		return GSON.fromJson(jsonCep, Cep.class);
	}
}