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
 * Servlet implementation class ConfirmarAlteracaoServlet
 */
public class ConfirmarAlteracaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("−−−−−−−−−−−−− Passei no servlet de confirmar alteração −−−−−−−−−−−−");
		// Recuperar os valores informados
		Integer id =  Integer.parseInt(request.getParameter("id")) ;
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String genero = request.getParameter("genero");
		String semestre = request.getParameter("semestre");
		// Recuperando a sessao e conseuquentemente a lista
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		Aluno aluno = null;
		for (Aluno a : listaAlunos ) {
			if (a.getId() == id) {
		aluno = a;
		}
		}
		// Alterando objeto
		aluno.setSemestre(semestre);
		aluno.setNome(nome);
		aluno.setGenero(genero);
		aluno.setIdade(idade);
		// Guarda o aluno na requisição para mostrar na pagina de detalhes
		request.setAttribute("aluno", aluno);
		// Encaminhar a requisição para o JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("detalharAluno.jsp");
		dispatcher.forward(request, response);

	}

}
