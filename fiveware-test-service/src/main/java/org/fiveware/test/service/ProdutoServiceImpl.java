package org.fiveware.test.service;

import java.util.List;

import javax.transaction.Transactional;

import org.fiveware.test.interfaces.ProdutoDao;
import org.fiveware.test.interfaces.ProdutoService;
import org.fiveware.test.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service("produtoService")
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Override
	public Produto getProdutoPorId(Long id) {		
		return produtoDao.getById(id);
	}

	@Override
	public List<Produto> selecionarTodos() {
		return produtoDao.getAll();
	}

	@Override
	public void armazenarProduto(Produto produto) {
		produtoDao.save(produto);
	}

	@Override
	public void removerProduto(Produto produto) {
		produtoDao.delete(produto);		
	}

}
