package org.fiveware.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fiveware.test.interfaces.ProdutoDao;
import org.fiveware.test.model.Produto;
import org.springframework.stereotype.Repository;

@Repository(value="produtoDao")
public class ProdutoDaoImpl implements ProdutoDao {

	@PersistenceContext
	private EntityManager manager; 
	
	@Override
	public Produto getById(long id) {		
		Produto produto = manager.find(Produto.class, id);
		return produto;
	}

	@Override
	public List<Produto> getAll() {
		List<Produto> resultList = manager
			.createQuery("select t from Produto t", Produto.class).getResultList();
		return resultList;
	}

	@Override
	public void save(Produto produto) {
		if (produto.getId() == null || produto.getId() == 0) {
			produto.setId(null);
			manager.persist(produto);
		} else {
			manager.merge(produto);
		}		
	}

	@Override
	public void delete(Produto produto) {
		manager.remove(produto);		
	}

	@Override
	public void delete(long id) {
		Produto produto = getById(id);
		delete(produto);		
	}

}
