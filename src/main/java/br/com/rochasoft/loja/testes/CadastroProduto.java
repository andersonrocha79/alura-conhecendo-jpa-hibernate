package br.com.rochasoft.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rochasoft.loja.dao.CategoriaDAO;
import br.com.rochasoft.loja.dao.ProdutoDAO;
import br.com.rochasoft.loja.modelo.Categoria;
import br.com.rochasoft.loja.modelo.CategoriaId;
import br.com.rochasoft.loja.modelo.Produto;
import br.com.rochasoft.loja.util.JpaUtil;

public class CadastroProduto {

	private static ProdutoDAO produtoDAO;

	public static void main(String[] args) {
		
		cadastrarProduto();
		
		selecionarUmProduto();
		
		selecionarTodos();
		
		selecionarPorNome();
		
		selecionarPorNomeCategoria();
		
		selecionarPrecoDeUmProdutoPorNome();

	}

	private static void selecionarPrecoDeUmProdutoPorNome() {
		System.out.println("retornando o preço de um produto por nome:");
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO daoProduto = new ProdutoDAO(em);
		BigDecimal precoProduto = daoProduto.buscarPrecoProdutoComNome("Xiaomi Redmi");		
		System.out.println("preço: " + precoProduto);	
		
	}

	private static void selecionarPorNomeCategoria() {
		System.out.println("retornando a lista de produtos por nome da categoria:");
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO daoProduto = new ProdutoDAO(em);
		List<Produto> lista = daoProduto.findByNomeCategoria("XPTO2");		
		lista.forEach(registro -> {
			System.out.println(registro);	
		});
		
		// utilizando namedquery
		List<Produto> lista2 = daoProduto.findByNomeCategoriaModelo2("XPTO2");		
		lista2.forEach(registro -> {
			System.out.println(registro);	
		});		
		
	}

	private static void selecionarPorNome() {
		System.out.println("retornando a lista de produtos por nome:");
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO daoProduto = new ProdutoDAO(em);
		List<Produto> lista = daoProduto.findByName("Xiaomi Redmi");		
		lista.forEach(registro -> {
			System.out.println(registro);	
		});
		
	}

	private static void selecionarTodos() 
	{
		System.out.println("retornando a lista de produtos:");
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO daoProduto = new ProdutoDAO(em);
		List<Produto> lista = daoProduto.findAll();		
		lista.forEach(registro -> {
			System.out.println(registro);	
		});
				
	}

	private static void selecionarUmProduto() 
	{	
		System.out.println("retornando um único produto:");
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO daoProduto = new ProdutoDAO(em);
		Produto produto = daoProduto.findById(1l);
		System.out.println(produto);
	}

	private static void cadastrarProduto() {

		// cadastrando uma categoria e um produto
		Categoria celulares = new Categoria(new CategoriaId(1, "Produto"), "Celulares");

		Produto produto = new Produto("Xiaomi Redmi", "Celular top de linha", new BigDecimal("1500.00"), celulares);

		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO daoProduto = new ProdutoDAO(em);
		CategoriaDAO daoCategoria = new CategoriaDAO(em);

		em.getTransaction().begin();

		daoCategoria.cadastrar(celulares);
		System.out.println("categoria: " + celulares);

		// como ainda está dentro da transação que foi iniciada
		// e a entidade 'celulares' está sendo gerenciada após a 'inclusão'
		// esta alteração do nome da categoria vai gerar um 'update'
		celulares.setNome("XPTO");

		// executa os comandos mas não realiza o commit
		// depois limpa o contexto de objetos managed da jpa
		em.flush();
		em.clear();

		// retorna o objeto 'celulares'
		// devolve a entidade no estado 'managed'
		// alterando qualquer campo do objeto, realiza o update
		celulares = em.merge(celulares);
		celulares.setNome("XPTO2");

		daoProduto.cadastrar(produto);
		System.out.println("produto: " + produto);

		em.getTransaction().commit();

		Categoria celular2 = daoCategoria.obter(celulares.getId());
		System.out.println("categoria atualizada: " + celular2.getNome());

		em.close();

		// novo teste
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();

		// teste de atualização e deleção
		Categoria novaCategoria = new Categoria(new CategoriaId(2, "Produto"), "teste exclusao1 ");
		em.persist(novaCategoria);
		novaCategoria.setNome("teste exclusao 2");
		em.flush();
		em.clear();
		novaCategoria = em.merge(novaCategoria);
		novaCategoria.setNome("teste exclusao 3");
		em.flush();
		System.out.println("categoria teste exclusao: " + novaCategoria.getNome());
		em.remove(novaCategoria);
		em.flush();
	}
	
}
