package redis.cep.dao.jedis;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import redis.clients.jedis.Jedis;

public class JedisConnectionProduces {

	private static final int PORT = 6379;
	private static final String HOST = "172.16.29.138";
//	private static final String HOST = "10.0.2.15";

	@Produces
	@CepConnection
	public Jedis createJedisCep() {
		Jedis jedis = new Jedis(HOST, PORT);
		jedis.select(1);
		return jedis;
	}

	@Produces
	@AnnotherConnection
	public Jedis createJedisUsuario() {
		Jedis jedis = new Jedis(HOST, PORT);
		jedis.select(2);
		return jedis;
	}

	public void disposeCep(@Disposes @AnnotherConnection Jedis jedis) {
		jedis.close();
	}

	public void disposeUsuario(@Disposes @CepConnection Jedis jedis) {
		jedis.close();
	}

}
