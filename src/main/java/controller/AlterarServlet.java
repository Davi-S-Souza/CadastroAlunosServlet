package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Aluno;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class AlterarServlet
 */
public class AlterarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("−−−−−−−−−−−−− Passei no servlet de alterar −−−−−−−−−−−−");
		// Recupera o atributo id do aluno que seve ser alterado
		Integer id =  Integer.parseInt(request.getParameter("id")) ;
		// Recupera a lista de alunos da sessao
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		// Recuperando o aluno que tem o nome informado (e que deve ser alterado)
		Aluno aluno = null;
		for (Aluno a : listaAlunos) {
			if (a.getId() == id) {
		aluno = a;
		}
		}
		// Adiciona o aluno no request, para exibir seus dados na pagina de alterar
		request.setAttribute("aluno", aluno);
		// Encaminhar a requisição para o JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("alterarAluno.jsp");
		dispatcher.forward(request, response);

	}

}
