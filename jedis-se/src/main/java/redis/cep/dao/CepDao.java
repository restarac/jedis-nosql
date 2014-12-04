package redis.cep.dao;

import redis.cep.model.Cep;

public interface CepDao {

	public static final long MAX_CEP_VALUE = 99999L;
	
	public static final long LIMIT_CEP = 99999L;
	
	
	Long countObjects();
	
	boolean testConnection();
	
	void save(Cep cep);

	Cep load(String cepNumber);	
	
}