package br.com.rochasoft.loja.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaId implements Serializable
{
	
	private String 	tipo;	
	private int 	codigo;
	
	public CategoriaId()
	{
		
	}	
	
	public CategoriaId(int codigo, String tipo) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}	

}
