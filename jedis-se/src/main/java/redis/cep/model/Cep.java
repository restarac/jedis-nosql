package redis.cep.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

@Entity(name = "ref_cep_info")
public class Cep {

	@Id
	private String cep;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String nome_estado;
	private String country_code;

	public Cep() {
	}
	
	public Cep(Integer cepNumber) {
		final String cepString = StringUtils.leftPad(cepNumber.toString(), 8, '0');
		this.cep = cepString;
	}

	public Cep(String cep, String endereco, String bairro, String cidade,
			String estado, String nome_estado, String country_code) {
		super();
		this.cep = cep;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.nome_estado = nome_estado;
		this.country_code = country_code;
	}

	public String getCep() {
		return cep;
	}
	public Integer getCepNumber() {
		String cepWithoutMasks = cep.replaceAll("-", "");
		return new Integer(cepWithoutMasks);
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNome_estado() {
		return nome_estado;
	}

	public void setNome_estado(String nome_estado) {
		this.nome_estado = nome_estado;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

}
