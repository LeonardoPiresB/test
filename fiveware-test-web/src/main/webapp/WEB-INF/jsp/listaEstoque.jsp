<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produtos do Estoque</title>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript">
function salvarProduto(elem) {
	var formulario = $(elem).closest("form");
	var argumentos = {};
	argumentos['descricao'] = formulario.find("input[name='descricao']").val();
	argumentos['emEstoque'] = false;
	if (formulario.find("input[name='emEstoque']").is(":checked"))
		argumentos['emEstoque'] = true;
	argumentos['cor'] = formulario.find("input[name='corSelecionada']").filter(':checked').val();
	argumentos['tamanho'] = formulario.find("select[name='tamanhoSelecionado'] > option").filter(':checked').val();
	//console.log(argumentos);
	$.post(formulario.attr('action'), argumentos, function(resposta){
		//console.log(resposta);
		//alert("Produto Armazenado!");
		formulario.trigger("reset");
		var produtoJson = $.parseJSON(resposta);
		if (produtoJson.sucesso) {
			var linha = "<tr>"
				+ "<td>" + produtoJson.id + "</td>"
				+ "<td>" + produtoJson.descricao + "</td>"
				+ "<td>" + produtoJson.tamanho + "</td>"
				+ "<td>" + produtoJson.cor + "</td>"
				+ "<td>" + produtoJson.emEstoque + "</td>"
				+ "<td>" +  (String(produtoJson.dataCriacao.day).length == 1 ? "0" : "") + produtoJson.dataCriacao.day 
					+ "/" + (String((produtoJson.dataCriacao.month+1)).length == 1 ? "0" : "") + (produtoJson.dataCriacao.month+1) 
					+ "/" + produtoJson.dataCriacao.year 
					+ " " + (String(produtoJson.dataCriacao.hour).length == 1 ? "0" : "") + produtoJson.dataCriacao.hour 
					+ ":" + (String(produtoJson.dataCriacao.minute).length == 1 ? "0" : "") + produtoJson.dataCriacao.minute 
					+ ":" + (String(produtoJson.dataCriacao.second).length == 1 ? "0" : "") + produtoJson.dataCriacao.second + "</td>"
				+"</tr>";
			$(".tabela").append(linha);			
		} else {
			alert("Descrição do Produto não pode ser VAZIA ou MENOR que 5 caracteres!\n"
					+ produtoJson.motivo);	
		}
	}).fail(function(){
		alert("Erro ao Inserir Novo Produto!");
	});	
}
</script>
<style type="text/css">
.painel {
	border: 1px solid #000;
	border-radius: 10px;
	padding: 10px;
	width: 40%;
}
.tabela {
	border-collapse: collapse;
	border: 1px solid #ddd;
}
.tabela th , .tabela td {
	padding: 10px;
	border: 1px solid #ddd;
} 
.tabela th {
	background-color: #4CAF50;
    color: white;
}
</style>
</head>
<body>
	<h1>Controle do Estoque '${nomeArmazem}'</h1>
	<p>Bem - Vindo!</p>
	<h3>Cadastro de Produtos</h3>
	<form action="armazenarProduto" class="painel" method="post">
		Descri&ccedil;&atilde;o:<br>
		<input type="text" name="descricao"><br>
		Em Estoque ?
		<input type="checkbox" name="emEstoque" value="true"><br>
		Cor:
		<input type="radio" name="corSelecionada" value="verde" checked> Verde
		<input type="radio" name="corSelecionada" value="azul"> Azul
		<input type="radio" name="corSelecionada" value="vermelho"> Vermelho
		<input type="radio" name="corSelecionada" value="amarelo"> Amarelo
		<br>
		Tamanho:
		<select name="tamanhoSelecionado">
			<option value="20">Grande - 20</option>
			<option value="15">M&eacute;dio - 15</option>
			<option value="10">Pequeno - 10</option>
		</select><br><br>
		<input type="button" onclick="salvarProduto(this)" value="Salvar">
	</form>
	<h3>Produtos</h3>
	<table class="tabela">
		<tr>
			<th>#ID
			<th>Descri&ccedil;&atilde;o
			<th>Tamanho
			<th>Cor			
			<th>Está em Falta?
			<th>Data Cria&ccedil;&atilde;o
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>${produto.id}
				<td>${produto.descricao}
				<td>${produto.tamanho}
				<td>${produto.cor }
				<td>${produto.emEstoque ? 'Sim' : 'N&atilde;o'}
				<td><fmt:formatDate value="${produto.dataCriacao.time}" pattern="dd/MM/yyyy HH:mm:ss"/>
			</tr>	
		</c:forEach>
	</table>
</body>
</html>