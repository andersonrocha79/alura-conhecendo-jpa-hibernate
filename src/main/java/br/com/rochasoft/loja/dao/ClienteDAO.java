package br.com.rochasoft.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.rochasoft.loja.modelo.Cliente;

public class ClienteDAO 
{
	
	private EntityManager em;

	public ClienteDAO(EntityManager em) 
	{
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente)
	{
		this.em.persist(cliente);
	}	
	
	public Cliente findById(Long id)
	{
		return this.em.find(Cliente.class, id);
	}
	
	public List<Cliente> findAll()
	{
		// jpql
		String jpql = "SELECT c FROM Cliente c";
		return this.em.createQuery(jpql, Cliente.class).getResultList();
	}
		
}
