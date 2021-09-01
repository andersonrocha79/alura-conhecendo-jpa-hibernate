package br.com.rochasoft.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "produtos_informatica")
public class ProdutoInformatica extends Produto 
{
	
	private String marca;
	private String modelo;
	
	public ProdutoInformatica()
	{
		
	}

	public ProdutoInformatica(String marca, String modelo) {
		super();
		this.marca = marca;
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	

}
