package controller;

import jakarta.servlet.RequestDispatcher; 
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dao.AlunoJDBCDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("−−−−−−−−−−−−− Passei no servlet de login −−−−−−−−−−−−");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		if (usuario.equals("admin") && senha.equals("admin") && usuario != null && !senha.isEmpty()) {
		HttpSession session = request.getSession();
		session.setAttribute("usuario", usuario);
		session.setMaxInactiveInterval(6 * 60);
		AlunoJDBCDAO dao = new AlunoJDBCDAO();
		request.setAttribute("listaAlunos", dao.listarAlunos());
		// Encaminhar a requisição para o JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("listarAlunos.jsp");
		dispatcher.forward(request, response);
//		response.sendRedirect("listarAlunos.jsp");
		} else {
		request.setAttribute("mensagem", "Senha ou Login incorreto");
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		}
	}

}
