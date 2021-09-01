package br.com.rochasoft.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "produtos_livro")
public class ProdutoLivro extends Produto 
{
	
	private String autor;
	private Integer numeroPaginas;
	
	public ProdutoLivro()
	{
		
	}
		
	public ProdutoLivro(String autor, Integer numeroPaginas) {
		super();
		this.autor = autor;
		this.numeroPaginas = numeroPaginas;
	}



	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	
	

}
