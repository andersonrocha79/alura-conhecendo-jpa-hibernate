package br.com.rochasoft.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rochasoft.loja.dao.ClienteDAO;
import br.com.rochasoft.loja.dao.PedidoDAO;
import br.com.rochasoft.loja.dao.ProdutoDAO;
import br.com.rochasoft.loja.modelo.Cliente;
import br.com.rochasoft.loja.modelo.ItemPedido;
import br.com.rochasoft.loja.modelo.Pedido;
import br.com.rochasoft.loja.modelo.Produto;
import br.com.rochasoft.loja.util.JpaUtil;
import br.com.rochasoft.loja.vo.RelatorioVendasVO;

public class CadastroPedidos 
{
	
	public static void main(String[] args) 
	{
		
		PopularBancoDados.executar();
		
		EntityManager em = JpaUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		// retorna os produtos a serem incluidos no pedido
		ProdutoDAO daoProduto = new ProdutoDAO(em);
		Produto produto1 = daoProduto.findById(1l);
		Produto produto2 = daoProduto.findById(2l);
		Produto produto3 = em.find(Produto.class, 3l);
		
		// retorna o cliente
		ClienteDAO daoCliente = new ClienteDAO(em);
		Cliente cliente1 = daoCliente.findById(1L);
		Cliente cliente2 = daoCliente.findById(2L);
		
		PedidoDAO daoPedido = new PedidoDAO(em);
		
		// inclui o pedido 1
		Pedido pedido1 = new Pedido(cliente1);
		
		pedido1.adicionarItem(new ItemPedido(2l, pedido1, produto1));
		pedido1.adicionarItem(new ItemPedido(3l, pedido1, produto2));
		pedido1.adicionarItem(new ItemPedido(4l, pedido1, produto3));
		pedido1.adicionarItem(new ItemPedido(5l, pedido1, produto3));
		
		daoPedido.cadastrar(pedido1);

		// inclui o pedido 2
		Pedido pedido2 = new Pedido(cliente2);
		
		pedido2.adicionarItem(new ItemPedido(4l, pedido2, produto3));
		pedido2.adicionarItem(new ItemPedido(5l, pedido2, produto2));
		
		daoPedido.cadastrar(pedido2);
		
		// inclui o pedido 3
		Pedido pedido3 = new Pedido(cliente2);
		
		pedido3.adicionarItem(new ItemPedido(4l, pedido3, produto1));
		pedido3.adicionarItem(new ItemPedido(5l, pedido3, produto2));
		
		daoPedido.cadastrar(pedido3);		
		em.getTransaction().commit();
		
		Pedido pedidoIncluido = daoPedido.findById(1l);
		
		System.out.println("pedido incluido");
		System.out.println(pedidoIncluido);
		
		BigDecimal valorTotal = daoPedido.getValorTotalVendido();
		System.out.println("valor total pedidos : " + valorTotal);
		
		List<Object[]> relatorioVendas = daoPedido.relatorioVendasModelo1();
		
		System.out.println("relatorio de vendas modelo 1");
		relatorioVendas.forEach(obj -> {
			System.out.println(obj[0]);
			System.out.println(obj[1]);
			System.out.println(obj[2]);			
		});
		
		System.out.println("relatorio de vendas modelo 2");
		List<RelatorioVendasVO> relatorioVendas2 = daoPedido.relatorioVendasModelo2();
		relatorioVendas2.forEach(item -> {
			System.out.println(item);
		});
		
		System.out.println("relatorio de vendas modelo 3");
		relatorioVendas2.forEach(System.out::println);
		
		// buscando um pedido
		System.out.println("seleciona pedido padrão");
		Pedido pedidoPadrao = daoPedido.findById(2l);
		System.out.println(pedidoPadrao.getData());
		System.out.println(pedidoPadrao.getCliente().getDadosPessoais().getNome()); // como o relacionamento é LAZY, vai fazer o select para pegar o cliente posteriormente
		System.out.println(pedidoPadrao.getItens().size());
		
		// carregamento EAGER (busca as entidades relacionadas automaticamente)
		// carregamento LAZY  (busca as entidades relacionadas somente quando forem acessadas)
		
		// consulta para trazer o cliente sempre, independente de como foi configurado o relacionamento
		System.out.println("seleciona pedido com select personalizado sempre gerando join com cliente");
		Pedido pedidoCliente = daoPedido.buscarPedidoComCliente(1l);
		System.out.println(pedidoCliente.getData());
		System.out.println(pedidoCliente.getCliente().getDadosPessoais().getNome()); // como o relacionamento é LAZY, vai fazer o select para pegar o cliente posteriormente
		System.out.println(pedidoCliente.getItens().size());
		
		System.out.println("seleção com parametros opcionais - tradicional");
		List<Produto> buscarPorParametros = daoProduto.buscarPorParametros("Guitarra Ibanez", null, LocalDate.now());
		System.out.println(buscarPorParametros);
		
		System.out.println("seleção com parametros opcionais - criteriaAPI");
		List<Produto> buscarPorParametros2 = daoProduto.buscarPorParametrosComCriteria("Guitarra Ibanez", null, LocalDate.now());
		System.out.println(buscarPorParametros2);
		
		em.close();
		
	}

}
