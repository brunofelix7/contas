<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Adicionar conta</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous"/>
</head>
<body>
	<h3>Adicionar contas</h3>
	<form action="adicionarConta" method="POST">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<label>Descrição:</label><br/>
		<textarea name="descricao" rows="5" cols="100"></textarea>
		<form:errors path="conta.descricao" cssClass="error"/>
		<br/>
		<label>Valor:</label><br/>
		<input type="text" name="valor"/>
		<form:errors path="conta.valor" cssClass="error"/>
		<br/>
		<label>Tipo:</label><br/>
		<select name="tipo">
			<option value="ENTRADA">Entrada</option>
			<option value="SAIDA">Saída</option>
		</select>
		<br/><br/>
		<input type="submit" value="Adicionar"/>
	</form>
	
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</body>
</html>