package br.com.rochasoft.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
@NamedQuery(name = "Produto.produtosPorCategoria",
			query = "SELECT p FROM Produto p WHERE p.categoria.nome = :nomeCategoria")
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE) // gera apenas uma tabela no banco com os atributos das classes que herdam de Produto
@Inheritance(strategy = InheritanceType.JOINED) // gera uma tabela para cada entidade que decende de Produto
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long 		id;
	private String 		nome;
	private String 		descricao;
	private BigDecimal 	preco;
	private LocalDate 	dataCadastro = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY) // s� traz dados do cliente se for acessado
	private Categoria 	categoria;
	

	public Produto() {
		super();
	}

	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco
				+ ", dataCadastro=" + dataCadastro + ", categoria=" + categoria + "]";
	}

	
	
}
