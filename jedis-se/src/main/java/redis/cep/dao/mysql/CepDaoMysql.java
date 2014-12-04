package redis.cep.dao.mysql;

import java.sql.SQLException;

import javax.inject.Inject;

import org.hibernate.StatelessSession;

import redis.cep.dao.CepDao;
import redis.cep.model.Cep;
import redis.cep.model.CepFactory;

public class CepDaoMysql implements CepDao {

	@Inject
	private StatelessSession session;

	public CepDaoMysql() {
	}

	public void populateCep() {
		session.beginTransaction();

		for (int i = 1; i <= MAX_CEP_VALUE; i++) {
			// a cada 50 objetos, faz a sincronizacao e limpa o cache
			if (i % 50 == 0) {
				session.getTransaction().commit();
				session.beginTransaction();
			}
			session.insert(CepFactory.create(i));
		}

		session.getTransaction().commit();
	}

	public Long countObjects() {
		return new Long(session
				.createQuery("SELECT COUNT(cep) FROM ref_cep_info")
				.uniqueResult().toString());
	}

	@SuppressWarnings("deprecation")
	public boolean testConnection() {
		try {
			return session.connection().isClosed();
		} catch (SQLException e) {
			return false;
		}
	}

	public void save(Cep cep) {
		// TODO Auto-generated method stub
		
	}

	public Cep load(String cepNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}
