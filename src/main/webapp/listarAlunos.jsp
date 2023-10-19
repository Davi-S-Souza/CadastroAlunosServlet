<%@page import="java.util.List"%>
<%@page import="model.Aluno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF−8">
<title>Alunos Cadastrados</title>
</head><body> <br><br>
<% String usuario = (String) session.getAttribute("usuario");
out.print("Bem vindo, "+usuario+" <br/>");
%>
Clique <a href="cadastrarAluno.jsp">aqui</a> para cadastar um novo aluno
<% List<Aluno> listaAlunos = (List<Aluno>) request.getAttribute("listaAlunos"); %>
<h2>Alunos Cadastrados</h2>
<table border="1">
<tr>
<th> Detalhar </th>
<th> Id </th>
<th> Nome </th>
<th> Idade </th>
<th> Sexo </th>
<th> Semestre </th>
<th> Matricula </th>
<th> Excluir </th>
</tr>
<% if (listaAlunos != null) {
for (Aluno aluno : listaAlunos) { %>
<tr>
<td> <a href="DetalharServlet?id=<%= aluno.getId() %>"> Detalhar</a></td>
<td> <%= aluno.getId() %> </td>
<td> <%= aluno.getNome() %> </td>
<td> <%= aluno.getIdade() %> </td>
<td> <%= aluno.getGenero() %> </td>
<td> <%= aluno.getSemestre() %> </td>
<td> <%= aluno.getMatricula() %> </td>
<td> <a href="ExcluirServlet?id=<%= aluno.getId() %>">Excluir</a></td>
</tr>
<% } }%>
</table>
<br>
<form action="PesquisarServlet" method="get">

Pesquisar por nome ou matrícula: <input type="text" name="pesquisa">
<br><br>
<input type="submit" value="Pesquisar">
</form>
<br><br>
<a href="LogoutServlet">Sair</a>
</head>
<body>

</body>
</html>