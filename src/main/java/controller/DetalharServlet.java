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

import dao.AlunoJDBCDAO;

/**
 * Servlet implementation class DetalharServlet
 */
public class DetalharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("−−−−−−−−−−−−− Passei no servlet de detalhar −−−−−−−−−−−−");
		// Recupera o nome do aluno que deve ser excluido
				int id =  Integer.parseInt(request.getParameter("id")) ;

				AlunoJDBCDAO dao = new AlunoJDBCDAO();
				
				request.setAttribute("aluno", dao.pesquisarId(id));
				RequestDispatcher dispatcher = request.getRequestDispatcher("detalharAluno.jsp");
				dispatcher.forward(request, response);
				// Encaminhar a requisição para o JSP
	}

}

