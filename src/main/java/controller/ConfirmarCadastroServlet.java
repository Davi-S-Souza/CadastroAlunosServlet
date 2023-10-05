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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Servlet implementation class ConfirmarCadastroServlet
 */
public class ConfirmarCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("−−−−−−−−−−−−− Passei no servlet de confirmar cadastro −−−−−−−−−−−−");
		// Recuperando a sessao
		HttpSession session = request.getSession();
		// Recuperar os valores informados

		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String genero = request.getParameter("genero");
		String semestre = request.getParameter("semestre");
		// Recuperando a lista da seção, caso não exista, cria
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		Aluno aluno = null;
		if (listaAlunos == null) {
			listaAlunos = new ArrayList<>(); // Criando a lista
			 aluno = new Aluno(1 ,nome, idade, semestre, genero, "");
		}
		else {
			Aluno ultimoAluno = listaAlunos.get(listaAlunos.size() - 1);
			 aluno = new Aluno( ultimoAluno.getId() + 1,nome, idade, semestre, genero, "");
		}
		
		LocalDate dataAtual = LocalDate.now();
		int anoInt = dataAtual.getYear();
		int mesInt = dataAtual.getMonthValue();
		int semestreDataInt = (mesInt < 7) ? 1 : 2;
		 String ano = Integer.toString(anoInt);
		 String mes = Integer.toString(mesInt);
		 String mesDataInt = Integer.toString(semestreDataInt);
		 StringBuilder matriculaString = new StringBuilder(ano);
		 matriculaString.append(mesInt);
		 matriculaString.append(semestreDataInt);
		 matriculaString.append(idade);
		 Random rand = new Random();
		int n = rand.nextInt(9999);
		String x = Integer.toString(n);
		matriculaString.append(x);
		aluno.setMatricula(matriculaString.toString());
		// Adicionando um aluno na lista
		
		listaAlunos.add(aluno);
		session.setAttribute("listaAlunos", listaAlunos);
		request.setAttribute("aluno", aluno);
		// Encaminhar a requisição para o JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("detalharAluno.jsp");
		dispatcher.forward(request, response);

	}

}
