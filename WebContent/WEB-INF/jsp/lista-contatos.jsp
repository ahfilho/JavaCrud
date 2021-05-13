<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,  br.com.dao.*, br.com.model.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="/WEB-INF/jsp/cabecalho.jsp"></c:import>

<html>
<head>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>

<meta charset="ISO-8859-1">
<title>SISTEMA.COM</title>

</head>
<body>

	<div class="cabecalho">
		<h2 style="text-align:center">LISTA DE CONTATOS</h2>
	</div>
	<hr>
	<!-- TAGLIB - INSTANDIANDO UMA POJO  --- aqui � criado o DAO-->

	<!--r.com.dao.ContatoDao dao = new br.com.dao.ContatoDao();-->
	<jsp:useBean id="contato" class="br.com.model.Contato" />
	<!-- Vari�vel dao / acessa o metodo list atraves da expression language -->

	<!-- EEM SEGUIDA EXECUTAR UM LOOP PARA CARA CONTATO DENTRO DA COLE��O-->
	<h4 style="text-align:center">
	<table border="1">
		<td><h3>ID</h3></td>


		<td><h3>Usuario</h3></td>

		<td><h3>Email</h3></td>

		<td><h3>Telefone</h3></td>

		<td><h3>Data Nasc.</h3></td>

		<td><h3>OP��ES</h3></td>

		<!-- PERCORRE OS CONTATOS MONTANDO CADA LINHA DA TABELA -->
		<c:forEach var="contato" items="${contatos}">
			<!--  e modificar o item para contatos, assim usar a lista que foi
	colocada no request-->
			<tr bgcolor="${id.count % 2 == 0?'aaee88': 'ffffff' }">

				<td>${contato.id}</td>
				<td>${contato.usuario}</td>
				<td>${contato.telefone}</td>
				<td><c:if test="${not empty contato.email}">
						<!-- VERIFICA SE O EMAIL EST� PREENCHIDO OU NAO -->
						<a href="mailto:${contato.email}">${contato.email}</a>
					</c:if> <c:if test="${empty contato.email}">

  E-mail n�o informado
  </c:if></td>
				<td>
					<!--  
<input id="dataNascimento" name="dataNascimento" type="text">
--> Data Nasc:<caelum:campoData id="dataNascimento" /><br /> <fmt:formatDate
						value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy" />
				</td>
				<td>
					<!--         <a href="mvc?logica=AlteraContatoLogica&id=${contato.id}">alterar</a> -->
					<a href="mvc?logica=AlteraContatoLogica&id=${contato.id}">alterar</a>
					<!-- lista os contatos com um link chamando a l�gica de remo��o e passando o id do contato: -->/
					<a href="mvc?logica=RemoveContatoLogica&id=${contato.id}">remover</a>
				</td>
			</tr>
		</c:forEach>
		<h4 style="text-align:center" >
		<a href="mvc?logica=InicioLogica">IN�CIO</a>
		<br>
		<a href="mvc?logica=AdicionaContatoLogica">ADICIONAR CONTATO </a>
		<br>
		<br>
		<br>

</h4>

	</table>
	</h4>
	<hr>
	<c:import url="/WEB-INF/jsp/rodape.jsp"></c:import>

</body>
</html>