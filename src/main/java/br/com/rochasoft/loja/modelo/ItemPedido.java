package br.com.rochasoft.loja.modelo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;

	private Long quantidade;

	@ManyToOne(fetch = FetchType.LAZY) // só traz dados do cliente se for acessado
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.LAZY) // só traz dados do cliente se for acessado
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ItemPedido(Long quantidade, Pedido pedido, Produto produto) {
		super();
		this.precoUnitario = produto.getPreco();
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.produto = produto;
	}

	public ItemPedido() {

	}

	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", precoUnitario=" + precoUnitario + ", quantidade=" + quantidade + ", pedido="
				+ pedido + ", produto=" + produto + "]";
	}

	public BigDecimal getValorTotalItem() {
		
		BigDecimal total = this.precoUnitario.multiply(new BigDecimal(quantidade)).setScale(2, RoundingMode.HALF_UP);
		return total;
		
	}

}
