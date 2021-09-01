package br.com.rochasoft.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.rochasoft.loja.modelo.Categoria;
import br.com.rochasoft.loja.modelo.CategoriaId;

public class CategoriaDAO 
{
	
	private EntityManager em;

	public CategoriaDAO(EntityManager em) 
	{
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria)
	{
		this.em.persist(categoria);
	}	
	
	public void remover(Categoria categoria)
	{
		categoria = this.em.merge(categoria);
		this.em.remove(categoria);
	}	
	
	public void atualizar(Categoria categoria)
	{
		categoria = this.em.merge(categoria); // coloca o objeto no estado 'managed' caso não esteja, para gerar o update quando rodar o 'flush' ou 'commit'
	}	
	
	public Categoria obter(CategoriaId id)
	{
		return this.em.find(Categoria.class, id);
	}		
	
	public List<Categoria> lista()
	{
		// jpql
		String jpql = "SELECT c FROM Categoria c";
		return this.em.createQuery(jpql, Categoria.class).getResultList();
		
	}
}
