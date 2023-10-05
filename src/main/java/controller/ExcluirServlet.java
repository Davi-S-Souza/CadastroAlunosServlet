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
 * Servlet implementation class ExcluirServlet
 */
public class ExcluirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("−−−−−−−−−−−−− Passei no servlet de excluir −−−−−−−−−−−−");
		// Recupera o nome do aluno que deve ser excluido
		Integer id =  Integer.parseInt(request.getParameter("id")) ;
		// Recupera a lista de alunos da sessão
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		// Recupera o aluno que tem o nome informado (e que deve ser excluido)
		Aluno aluno = null;
		for (Aluno a : listaAlunos) {
			if (a.getId() == id) {
		aluno = a;
		}
		}
		// Removendo o aluno da lista
		listaAlunos.remove(aluno);
		// Encaminhar a requisição para o JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("listarAlunos.jsp");
		dispatcher.forward(request, response);

	}

}
