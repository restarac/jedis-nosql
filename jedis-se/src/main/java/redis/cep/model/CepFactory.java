package redis.cep.model;

import org.apache.commons.lang3.RandomUtils;

public class CepFactory {

	private CepFactory() {
		super();
	}

	public static Cep create(final Integer cepNumber){
		final Cep cep = new Cep(cepNumber);
		final String cepString = cep.getCep();
		final ESTADOS estados = ESTADOS.values()[RandomUtils.nextInt(0, 9)];
		cep.setBairro("Bairro - "+cepString);
		cep.setCidade("Cidade - "+cepString);
		cep.setCountry_code("BR");
		cep.setEndereco("Endereco - "+cepString);
		cep.setEstado(estados.name());
		cep.setNome_estado("Desc. Estado - "+cepString);
		return cep;
	}
	
	private enum ESTADOS{
		ES,SP,RJ,MT,RE,CE,AM,RS,DF,MS,MN;
	}
	
}
