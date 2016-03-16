package org.fiveware.test.web.controller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.fiveware.test.interfaces.ProdutoDao;
import org.fiveware.test.interfaces.ProdutoService;
import org.fiveware.test.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EstoqueController {
	/*
	*/
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value = "lista" , method = RequestMethod.GET)
	public String listaCompras(Model model) {
		System.out.println("Listando TODOS Produtos do Estoque...");
		List<Produto> produtos = produtoService.selecionarTodos();
		model.addAttribute("produtos", produtos);
		model.addAttribute("nomeArmazem", "TESTE");
		return "listaEstoque";
	}
	
	@RequestMapping(value = "armazenarProduto", method = RequestMethod.POST)
	@ResponseBody
	public String armazenarProduto(@Valid Produto produto, BindingResult result) {
		System.out.println("Armazenando NOVO PRODUTO...");
		if (result.hasErrors()) {
			System.out.println("Erros encontrados no preenchimento do Formulario de Produto:" 
					+ result.toString());			
			return "{\"sucesso\":false, \"motivo\":\""
					+ result.getFieldError().getDefaultMessage() +"\"}";
		}
		produto.setDataCriacao(Calendar.getInstance());
		produtoService.armazenarProduto(produto);
		return converterJSON(produto);
	}
	
	@RequestMapping(value = "inserirExemplo{numero}")
	@ResponseBody
	public String inserirProdutoExemplo(@PathVariable("numero") Long numero) {
		System.out.println("Adicionando NOVO PRODUTO EXEMPLO "+numero+"...");
		Produto produto = new Produto();
		produto.setDescricao("Produto TESTE " + numero);
		produto.setEmEstoque(true);
		produto.setCor("azul");
		produto.setTamanho(10l);
		produto.setDataCriacao(Calendar.getInstance());
		produtoService.armazenarProduto(produto);
		String json = converterJSON(produto);
		return json;
	}

	private String converterJSON(Produto produto) {
		StringBuilder json = new StringBuilder();
		json.append("{\"sucesso\":true,")
			.append("\"id\":").append(produto.getId()).append(",")
			.append("\"descricao\":\"").append(produto.getDescricao()).append("\",")
			.append("\"emEstoque\":").append(produto.getEmEstoque()).append(",")
			.append("\"cor\":\"").append(produto.getCor()).append("\",")
			.append("\"tamanho\":").append(produto.getTamanho());
		if (produto.getDataCriacao() != null) {
			Calendar dataCriacao = produto.getDataCriacao();
			json.append(", \"dataCriacao\":{")
				.append("\"year\":").append(dataCriacao.get(Calendar.YEAR)).append(",")
				.append("\"month\":").append(dataCriacao.get(Calendar.MONTH)).append(",")
				.append("\"day\":").append(dataCriacao.get(Calendar.DAY_OF_MONTH)).append(",")
				.append("\"hour\":").append(dataCriacao.get(Calendar.HOUR_OF_DAY)).append(",")
				.append("\"minute\":").append(dataCriacao.get(Calendar.MINUTE)).append(",")
				.append("\"second\":").append(dataCriacao.get(Calendar.SECOND))
				.append("}");
		}
		json.append("}");
		return json.toString();
	}

}
