package com.kaio.gerenciamento.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaio.gerenciamento.model.Pessoa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PessoasRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EntityManager manager;
	
	public PessoasRepository() {
	}
	
	public PessoasRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}
	
	public List<Pessoa> pesquisar(String nome){
		String jpql = "from Pessoa where nome like :nome";
		
		TypedQuery<Pessoa> query = manager
			    .createQuery(jpql, Pessoa.class);
		
		query.setParameter("nome", nome + "%");
		
		return query.getResultList();
	}
	
	public List<Pessoa> listarTodas() {
        return manager.createQuery("from Pessoa", Pessoa.class).getResultList();
   }
	
	public Pessoa guardar(Pessoa pessoa) {
		return manager.merge(pessoa);
	}
	
	public void remover(Pessoa pessoa) {
		pessoa = porId(pessoa.getId());
		manager.remove(pessoa);
	}
}