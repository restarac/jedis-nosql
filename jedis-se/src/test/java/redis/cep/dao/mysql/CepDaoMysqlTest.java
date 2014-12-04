package redis.cep.dao.mysql;

import static org.junit.Assert.*;

import java.util.Date;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.cep.dao.jedis.CepDaoJedis;
import redis.cep.dao.jedis.CepDaoJedisTest;
import redis.cep.dao.jedis.JedisConnectionProduces;
import redis.cep.dao.mysql.CepDaoMysql;
import redis.cep.dao.mysql.HibernateConnectionProducer;
import redis.cep.model.Cep;
import redis.clients.jedis.Jedis;

@Ignore
@RunWith(CdiRunner.class)
@AdditionalClasses({ HibernateConnectionProducer.class, Cep.class})
public class CepDaoMysqlTest {
	
	final static Logger logger = LoggerFactory.getLogger(CepDaoJedisTest.class);

	@Inject
	private CepDaoMysql cepDao;


	@Test
	public void testTestConnectionCep() {
		assertTrue(cepDao.testConnection());
	}
	
	@Ignore
	@Test
	public void test() {

		Date ini = new Date();
		logger.info("{} - Iniciando o processo de inclusao...", ini.toString());

		cepDao.populateCep();

		long total = new Date().getTime() - ini.getTime();
		logger.info("{} - Finalizado o processo de inclusao...", ini.toString());
		logger.info("Tempo Total: {}ms ou {}s ou {}m", total, (total / 1000), ((total / 1000) / 60));
		
		assertEquals(CepDaoJedis.MAX_CEP_VALUE, cepDao.countObjects().longValue());
	}

}
