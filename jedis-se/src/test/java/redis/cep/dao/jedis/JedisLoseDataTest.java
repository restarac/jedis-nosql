package redis.cep.dao.jedis;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.cep.dao.jedis.CepDaoJedis;
import redis.cep.dao.jedis.JedisConnectionProduces;
import redis.cep.model.Cep;
import redis.clients.jedis.Jedis;

//@Ignore
@RunWith(CdiRunner.class)
@AdditionalClasses({ JedisConnectionProduces.class, Jedis.class })
public class JedisLoseDataTest {
	
	final static Logger logger = LoggerFactory.getLogger(JedisLoseDataTest.class);

	@Inject
	private CepDaoJedis cepDao;
	
	@Ignore
	@Test
	public void testPopulateFasterButWithLoseCep() throws InterruptedException {

		Date ini = new Date();
		logger.info("{} - Iniciando o processo de inclusao...", ini.toString());

		cepDao.populateFastButWithLoseCep();

		long total = new Date().getTime() - ini.getTime();
		logger.info("{} - Finalizado o processo de inclusao...", ini.toString());
		logger.info("Tempo Total: {}ms ou {}s ou {}m", total, (total / 1000), ((total / 1000) / 60));
//		Thread.sleep(1000);
		assertThat(cepDao.countObjects().longValue(), lessThan(CepDaoJedis.MAX_CEP_VALUE));
	}

}
