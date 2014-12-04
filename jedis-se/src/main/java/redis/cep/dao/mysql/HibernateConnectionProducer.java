package redis.cep.dao.mysql;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;

public class HibernateConnectionProducer {

	// configures settings from hibernate.cfg.xml
	@SuppressWarnings("deprecation")
	private static final SessionFactory BUILD_SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

	@Produces
	public StatelessSession producer() {
		return BUILD_SESSION_FACTORY.openStatelessSession();
	}
	
	public void disposeHibernate(@Disposes StatelessSession session) {
		session.close();
	}
}
