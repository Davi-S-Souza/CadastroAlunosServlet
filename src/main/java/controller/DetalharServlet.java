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
 * Servlet implementation class DetalharServlet
 */
public class DetalharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("−−−−−−−−−−−−− Passei no servlet de detalhar −−−−−−−−−−−−");
		// Recupera o nome do aluno que precisa ser detalhado
		Integer id =  Integer.parseInt(request.getParameter("id")) ;
		// Adicionar a lista de alunos na sessão
		HttpSession session = request.getSession();
		// Recuperando a lista da seção
		@SuppressWarnings("unchecked")
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		// Recuperando o aluno da lista com o nome especificado
		Aluno aluno = null;
		for (Aluno a : listaAlunos) {
			if (a.getId() == id) { // convertendo para string o getNome
				aluno = a;
			}
		}
		// Guarda o aluno no resquest, para mostrar na pagina de detalhes
		request.setAttribute("aluno", aluno);
		// Encaminhar a requisição para a pagina detalhar aluno
		RequestDispatcher dispatcher = request.getRequestDispatcher("detalharAluno.jsp");
		dispatcher.forward(request, response);
	}

}
