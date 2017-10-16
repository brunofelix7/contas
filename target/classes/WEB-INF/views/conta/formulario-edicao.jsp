<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>Atualizar conta</title>
</head>
<body>
	<h3>Atualizar conta</h3>
	<form action="alterarConta" method="POST">
		<label>Descrição:</label><br/>
		<textarea name="descricao" rows="5" cols="100">${conta.descricao}</textarea>
		<br/>
		<label>Valor:</label><br/>
		<input type="text" name="valor" value="${conta.valor}"/>
		<br/>
		<label>Tipo:</label><br/>
		<select name="tipo">
			<option value="ENTRADA" ${conta.tipo=='ENTRADA' ? 'selected':''}>Entrada</option>
			<option value="SAIDA" ${conta.tipo=='SAIDA' ? 'selected':''}>Saída</option>
		</select>
		<br/>
		<label>Pago?</label><br/>
		<input type="checkbox" name="paga" ${conta.paga ? 'checked':''}/>
		<br/>
		<label>Data de Pagamento:</label><br/>
		<input type="text" name="dataPagamento" value="<fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy" />" />
		<br/>
		<br/>
		<input type="hidden" name="id" value="${conta.id}"/>
		<input type="submit" value="Atualizar"/>
	</form>
</body>
</html>