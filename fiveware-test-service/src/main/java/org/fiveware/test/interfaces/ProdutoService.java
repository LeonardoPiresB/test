package org.fiveware.test.interfaces;

import java.util.List;

import org.fiveware.test.model.Produto;

public interface ProdutoService {
	
	Produto getProdutoPorId(Long id);
	List<Produto> selecionarTodos();
	void armazenarProduto(Produto produto);
	void removerProduto(Produto produto);
	
}
