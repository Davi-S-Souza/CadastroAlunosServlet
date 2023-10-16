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

import dao.AlunoJDBCDAO;

/**
 * Servlet implementation class ConfirmarCadastroServlet
 */
public class ConfirmarCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("−−−−−−−−−−−−− Passei no servlet de confirmar cadastro −−−−−−−−−−−−");
		// Recuperando a sessao
		

		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String genero = request.getParameter("genero");
		String semestre = request.getParameter("semestre");
		
		AlunoJDBCDAO dao = new AlunoJDBCDAO();
		
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
		
		
		Aluno aluno = new Aluno(nome, idade, semestre, genero, matriculaString.toString());
		Aluno alunoCadastrado = dao.cadastrarAluno(aluno);
		request.setAttribute("aluno", alunoCadastrado);
		
		request.getRequestDispatcher("detalharAluno.jsp").forward(request, response);
		
		

	}

}

