package org.fiveware.test.interfaces;

import java.util.List;

import org.fiveware.test.model.Produto;

public interface ProdutoDao {

	Produto getById(long id);
	List<Produto> getAll();
	void save(Produto produto);	
	void delete(Produto produto);
	void delete(long id);
	
}
