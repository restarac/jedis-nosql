package redis.cep.dao.jedis;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.cep.dao.jedis.CepDaoJedis;
import redis.cep.dao.jedis.JedisConnectionProduces;
import redis.cep.model.Cep;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;

//@Ignore
@RunWith(CdiRunner.class)
@AdditionalClasses({ JedisConnectionProduces.class, Jedis.class })
public class JedisShardingDataTest {
	
	private static final String HOST_REDIS = "172.16.29.138";

	final static Logger logger = LoggerFactory.getLogger(JedisShardingDataTest.class);

	private ShardedJedisPool pool;

	@Before
	public void before() {
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		
		shards.add(new JedisShardInfo(HOST_REDIS, 6379));
		shards.add(new JedisShardInfo(HOST_REDIS, 6380));
		
		pool = new ShardedJedisPool(new GenericObjectPoolConfig(), shards, Hashing.MURMUR_HASH);
	}
	
	
	
//	@Ignore
	@Test
	public void testPopulateFasterButWithLoseCep() throws InterruptedException {
		String key = "SHARD";
		for (int i = 0; i < 30; i++) {
			ShardedJedis resource = pool.getResource();
			JedisShardInfo shardInfo = resource.getShardInfo("");
			resource.incr(key);
			logger.info("Server: {}, Value(\"SHARD\"): {}",  shardInfo.toString(), resource.get(key));
		}
	}		
}
