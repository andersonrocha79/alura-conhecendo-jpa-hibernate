package br.com.rochasoft.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rochasoft.loja.modelo.Pedido;
import br.com.rochasoft.loja.modelo.Produto;
import br.com.rochasoft.loja.vo.RelatorioVendasVO;

public class PedidoDAO 
{
	
	private EntityManager em;

	public PedidoDAO(EntityManager em) 
	{
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido)
	{
		this.em.persist(pedido);
	}	
	
	public Pedido findById(Long id)
	{
		return this.em.find(Pedido.class, id);
	}
	
	public List<Pedido> findAll()
	{
		// jpql
		String jpql = "SELECT p FROM Pedido p";
		return this.em.createQuery(jpql, Pedido.class).getResultList();
	}
	
	public BigDecimal getValorTotalVendido()
	{
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return this.em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	public List<Object[]> relatorioVendasModelo1()
	{
		
		StringBuilder sb = new StringBuilder()
				.append("SELECT")
				.append(" produto.nome,")
				.append(" SUM(item.quantidade),")
				.append(" MAX(pedido.data)")
				.append(" FROM Pedido pedido")
				.append(" JOIN pedido.itens item")
				.append(" JOIN item.produto produto")
				.append(" GROUP BY produto.nome, item.quantidade")
				.append(" ORDER BY item.quantidade DESC");
		
		return this.em.createQuery(sb.toString(), 
				Object[].class)
				.getResultList();
	}
	
	public List<RelatorioVendasVO> relatorioVendasModelo2()
	{
		
		StringBuilder sb = new StringBuilder()
				.append("SELECT")
				.append(" new br.com.rochasoft.loja.vo.RelatorioVendasVO") // a classe deve ter um construtor com os parametros retornados pela consulta
				.append(" (" )
				.append("    produto.nome,")
				.append("    SUM(item.quantidade),")
				.append("    MAX(pedido.data)")
				.append(" )")
				.append(" FROM Pedido pedido")
				.append(" JOIN pedido.itens item")
				.append(" JOIN item.produto produto")
				.append(" GROUP BY produto.nome, item.quantidade")
				.append(" ORDER BY item.quantidade DESC");
		
		return this.em.createQuery(sb.toString(), 
				RelatorioVendasVO.class)
				.getResultList();
	}	
	
	// consulta planejada
	// evita utilizar a consulta padrão do pedido, que pode fazer joins desnecessários
	public Pedido buscarPedidoComCliente(Long id)
	{
		return this.em
				.createQuery("SELECT p FROM Pedido p" + 
	                         " JOIN FETCH p.cliente"  +
				             " WHERE p.id = :id", 
				             Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
		
}
