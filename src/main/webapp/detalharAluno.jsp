<%@page import="model.Aluno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Aluno aluno = (Aluno) request.getAttribute("aluno"); %>
<h2>Aluno Cadastrado</h2>
<%
if (aluno != null) {
%>
Id: <%= aluno.getId() %>
<br><br>
Nome: <%= aluno.getNome() %>
<br><br>
Idade: <%= aluno.getIdade() %>
<br><br>
Genero: <%= aluno.getGenero() %>
<br><br>
Semestre: <%= aluno.getSemestre() %>
<br><br>
Matricula: <%= aluno.getMatricula() %>
<br><br>
<input type="button" onclick="javascript:location.href='ListarServlet'" value="Confirmar">
<a href="AlterarServlet?id=<%=aluno.getId()%>">Alterar</a>
<br><br>
<%
} else {
%>
<p>Aluno não encontrado.</p>
<%
}
%>

</body>
</html>
