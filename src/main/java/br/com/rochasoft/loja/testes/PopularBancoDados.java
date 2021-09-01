package br.com.rochasoft.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rochasoft.loja.dao.CategoriaDAO;
import br.com.rochasoft.loja.dao.ClienteDAO;
import br.com.rochasoft.loja.dao.ProdutoDAO;
import br.com.rochasoft.loja.modelo.Categoria;
import br.com.rochasoft.loja.modelo.CategoriaId;
import br.com.rochasoft.loja.modelo.Cliente;
import br.com.rochasoft.loja.modelo.Produto;
import br.com.rochasoft.loja.util.JpaUtil;

public class PopularBancoDados 
{
	
	public static void executar()
	{
		
		Categoria celulares = new Categoria(new CategoriaId(1, "Produto"), "Celulares");
		Categoria informatica = new Categoria(new CategoriaId(2, "Produto"), "Informáticas");
		Categoria instrumentos = new Categoria(new CategoriaId(3, "Produto"), "Instrumentos Musicais");
		Categoria eletrodomesticos = new Categoria(new CategoriaId(4, "Produto"), "Eletrodomésticos");

		Produto produto1 = new Produto(	"Xiaomi Redmi", 
										"Celular top de linha", 
										new BigDecimal("1500.00"), 
										celulares);

		Produto produto2 = new Produto(	"Sansumg", 
				"telefone teste sansumg", 
				new BigDecimal("850.99"), 
				celulares);

		Produto produto3 = new Produto(	"WebCam", 
				"webcam teste", 
				new BigDecimal("190.59"), 
				informatica);
		
		Produto produto4 = new Produto(	"Guitarra Ibanez", 
				"guitarra ibanez teste", 
				new BigDecimal("4500.00"), 
				instrumentos);		

		Produto produto5 = new Produto(	"Violão Eagle", 
				"violão eagle teste", 
				new BigDecimal("850.00"), 
				instrumentos);	
		
		Produto produto6 = new Produto(	"Tv led 50 polegadas", 
				"tv led teste", 
				new BigDecimal("3949.99"), 
				eletrodomesticos);			
		
		Produto produto7 = new Produto(	"geladeira consul 500 lts", 
				"geladeira consul 500 lts", 
				new BigDecimal("6957.50"), 
				eletrodomesticos);	
		
		Cliente cliente1 = new Cliente("Anderson Rocha", "11111111111");
		Cliente cliente2 = new Cliente("Hiriane Gomes", "22222222222");
		
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO daoProduto = new ProdutoDAO(em);
		CategoriaDAO daoCategoria = new CategoriaDAO(em);
		ClienteDAO daoCliente = new ClienteDAO(em);

		em.getTransaction().begin();
		
		daoCategoria.cadastrar(eletrodomesticos);
		daoCategoria.cadastrar(celulares);
		daoCategoria.cadastrar(instrumentos);
		daoCategoria.cadastrar(informatica);
		
		daoProduto.cadastrar(produto1);
		daoProduto.cadastrar(produto2);
		daoProduto.cadastrar(produto3);
		daoProduto.cadastrar(produto4);
		daoProduto.cadastrar(produto5);
		daoProduto.cadastrar(produto6);
		daoProduto.cadastrar(produto7);
		
		daoCliente.cadastrar(cliente1);
		daoCliente.cadastrar(cliente2);
		
		em.getTransaction().commit();		
		
		System.out.println("categorias cadastradas:");
		List<Categoria> categorias = daoCategoria.lista();
		categorias.forEach(cat -> {
			System.out.println(cat);
		});		
		
		System.out.println("produtos cadastradas:");
		List<Produto> produtos = daoProduto.findAll();
		produtos.forEach(prod -> {
			System.out.println(prod);
		});	
		
		System.out.println("clientes cadastrados:");
		List<Cliente> clientes = daoCliente.findAll();
		clientes.forEach(cli -> {
			System.out.println(cli);
		});			
		
		em.close();
		
	}

}
