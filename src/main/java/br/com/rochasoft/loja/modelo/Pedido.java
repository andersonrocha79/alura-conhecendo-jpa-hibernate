package br.com.rochasoft.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate data = LocalDate.now();
	
	@ManyToOne(fetch = FetchType.LAZY) // só traz dados do cliente se for acessado
	private Cliente cliente;
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	// se tivesse apenas uma tabela com as pks das tabelas, seria um
	// relacionamento simples, conforme descrito abaixo
	// @ManyToMany
	// private List<Produto> produtos;
	
	// relacionamento um para muitos (um pedido pode ter vários itens)
	// relacionamento bidirecional com ItemPedido
	@OneToMany (mappedBy = "pedido",    // indica que o mapeamento já foi feito pelo atributo pedido da classe ItemPedido (nome do atributo: pedido)
			cascade = CascadeType.ALL)  // indica que deve fazer os comandos em cascata, ou seja, incluindo o pedido, já inclui os itens também. se excluir o pedido, exclui os itens também, porque é uma tabela filha
	private List<ItemPedido> itens = new ArrayList<>(); // já inicializa a lista 
	
	public Pedido()
	{
		
	}
	
	public Pedido(Cliente cliente) {
		super();
		this.cliente = cliente;
		this.valorTotal = new BigDecimal("0.00");
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data=" + data + ", cliente=" + cliente + ", valorTotal=" + valorTotal + "]";
	}
	
	// cria um método para adicionar item ao pedido,
	// já fazendo o vínculo necessário
	public void adicionarItem(ItemPedido item) {		
		item.setPedido(this);
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getValorTotalItem());
	}
	
	

}
