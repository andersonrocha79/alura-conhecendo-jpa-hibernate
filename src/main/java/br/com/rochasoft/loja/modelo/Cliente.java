package br.com.rochasoft.loja.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded // inclui os parametros de outra classe nesta entidade
	private DadosPessoais dadosPessoais;
	
	@OneToOne(fetch = FetchType.EAGER)
    private Endereco endereco;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Telefone> telefones = new ArrayList<>();	
	
	public Cliente()
	{
		
	}
	
	public Cliente(String nome, String cpf) {
		super();
		dadosPessoais = new DadosPessoais(nome, cpf);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dadosPessoais=" + dadosPessoais + ", endereco=" + endereco + ", telefones="
				+ telefones + "]";
	}


	
	
	
	
	

}
