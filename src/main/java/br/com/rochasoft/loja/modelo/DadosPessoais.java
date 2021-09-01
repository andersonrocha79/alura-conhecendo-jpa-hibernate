package br.com.rochasoft.loja.modelo;

import javax.persistence.Embeddable;

@Embeddable // indica que é uma classe que complementa uma entidade
public class DadosPessoais 
{

	private String nome;
	private String cpf;
	
	public DadosPessoais()
	{}
	
	public DadosPessoais(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
}
