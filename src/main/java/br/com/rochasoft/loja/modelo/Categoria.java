package br.com.rochasoft.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	// exemplo de chave primaria composta
	@EmbeddedId
	private CategoriaId id;
	
	private String nome;

	public Categoria() {
		super();
	}

	public Categoria(CategoriaId id, String nome) 
	{
		super();
		this.id = id;
		this.nome = nome;
	}

	public CategoriaId getId() {
		return id;
	}

	public void setId(CategoriaId id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
	

}
