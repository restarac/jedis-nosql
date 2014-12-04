package redis.cep.mysql.controller;

import java.util.List;

import javax.inject.Inject;

import redis.cep.dao.jedis.CepDaoJedis;
import redis.cep.model.Cep;
import br.com.caelum.vraptor.Controller;

@Controller
public class CepController {

	@Inject
	private CepDaoJedis dao;

	public void form() {
	}

	public void add(Cep cep) {
		dao.save(cep);
	}

	public List<Cep> list() {
        return dao.listAll();
    }

	public Cep view(String cepNumber) {
		return dao.load(cepNumber);
	}
}