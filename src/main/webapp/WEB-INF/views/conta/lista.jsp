<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lista de contas</title>
	<script type="text/javascript">
		function deuCerto(dadosResposta){
			alert("Conta paga com sucesso!");
			//	IMPLEMENTAR SUMIR COISAS, APARECER BOTÃO., ETC
		}
	
		//	Faz uma requisição assíncrona para essa URL por baixo dos panos - AJAX
		//	Quando essa requisição voltar, executa a função 'deuCerto'
		function pagarAgora(id){
			$.get("pagarConta?id=" + id, function(){
				$("#conta_" + id).html("Paga");
			});
		}
		
		//	Mesma bosta só que mais bonito
		/*function pagaAgora(id) {
		    $.post("pagaConta", {'id' : id}, function() {
		      alert("Conta paga com sucesso");
		    });
		  }*/
	</script>
</head>
<body>
	<h3>Contas cadastradas</h3>
	<table>
		<tr>
			<th>#</th>
			<th>Descrição</th>
			<th>Valor</th>
			<th>Tipo</th>
			<th>Paga?</th>
			<th>Data de pagamento</th>
			<th>Ações</th>
		</tr>
		<c:forEach items="${contas}" var="conta">
			<tr>
				<td>${conta.id}</td>
				<td>${conta.descricao}</td>
				<td>${conta.valor}</td>
				<td>${conta.tipo}</td>
				<td>
					<c:if test="${conta.paga eq false}">
						<b style="color: #F44336;">Não paga</b>
					</c:if>
					<c:if test="${conta.paga eq true}">
						<b style="color: #4CAF50;">Paga</b>
					</c:if>
				</td>
				<td><fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy"/></td>
				<td id="conta_${conta.id}">
					<a href="deletarConta?id=${conta.id}">Deletar</a>
					<a href="formEdicao?id=${conta.id}">Editar</a> |
					
					<c:if test="${conta.paga eq false}">
						<a href="#" onclick="pagarAgora(${conta.id});">Pagar</a>
					</c:if> 
				</td>
			</tr>
		</c:forEach>
	</table>
<script type="text/javascript" src="resources/js/jquery.js"></script>
</body>
</html>