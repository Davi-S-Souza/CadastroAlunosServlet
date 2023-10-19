package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.jasper.tagplugins.jstl.core.Out;

import dao.AlunoJDBCDAO;

/**
 * Servlet implementation class PesquisarServlet
 */
public class PesquisarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pesquisa = request.getParameter("pesquisa");
		AlunoJDBCDAO dao = new AlunoJDBCDAO();
		request.setAttribute("listaAlunos", dao.pesquisarNomeMat(pesquisa));
		// Encaminhar a requisição para o JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("listarAlunos.jsp");
		dispatcher.forward(request, response);
	}

}
