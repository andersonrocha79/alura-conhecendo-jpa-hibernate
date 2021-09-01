package br.com.rochasoft.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.rochasoft.loja.modelo.Produto;

public class ProdutoDAO 
{
	
	private EntityManager em;

	public ProdutoDAO(EntityManager em) 
	{
		this.em = em;
	}
	
	public void cadastrar(Produto produto)
	{
		this.em.persist(produto);
	}	
	
	public Produto findById(Long id)
	{
		return this.em.find(Produto.class, id);
	}
	
	public List<Produto> findAll()
	{
		// jpql
		String jpql = "SELECT p FROM Produto p";
		return this.em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> findByName(String nome)
	{
		// jpql
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return this.em
				.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
		
		// outra forma de passar parâmetros
		/*
		String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
		return this.em
				.createQuery(jpql, Produto.class)
				.setParameter(1, nome)
				.getResultList();
		 */
		
	}
	
	public List<Produto> findByNomeCategoria(String nomeCategoria)
	{
		// jpql
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nomeCategoria";
		return this.em
				.createQuery(jpql, Produto.class)
				.setParameter("nomeCategoria", nomeCategoria)
				.getResultList();
		
	}	
	
	public BigDecimal buscarPrecoProdutoComNome(String nomeProduto)
	{
		// jpql
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nomeProduto";
		return this.em
				.createQuery(jpql, BigDecimal.class)
				.setParameter("nomeProduto", nomeProduto)
				.getSingleResult();
		
	}		

	// utilizando namedQuery
	public List<Produto> findByNomeCategoriaModelo2(String nomeCategoria)
	{
		return this.em
				.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
				.setParameter("nomeCategoria", nomeCategoria)
				.getResultList();
		
	}
	
	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro)
	{
		
		// consulta com vários parâmetros opcionais de forma 'tradicional'
		
		String jpql = "SELECT p FROM Produto p WHERE 1=1";
		
		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND p.nome =:nome";
		}
		
		if (preco != null) {
			jpql += " AND p.preco =:preco";
		}

		if (dataCadastro != null) {
			jpql += " AND p.dataCadastro =:dataCadastro";
		}
		
		TypedQuery<Produto> createQuery = em.createQuery(jpql, Produto.class);
		
		if (nome != null && !nome.trim().isEmpty()) {
			createQuery.setParameter("nome", nome);
		}
		
		if (preco != null) {
			createQuery.setParameter("preco", preco);
		}

		if (dataCadastro != null) {
			createQuery.setParameter("dataCadastro", dataCadastro);
		}		
		
		return createQuery.getResultList();
		
	}

	public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro)
	{
		
		// consulta com vários parâmetros opcionais com 'criteriaAPI'
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();

		if (nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		
		if (preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}

		if (dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}		
		
		query.where(filtros);
		
		return em.createQuery(query).getResultList();
		
	}
	
}
