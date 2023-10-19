package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Aluno;

public class AlunoJDBCDAO {
	public Connection getConexao() throws ClassNotFoundException {
		// Driver
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		// Banco de dadosjdbc:mysql://127.0.0.1/escola
		String url = "jdbc:mysql://localhost:3306/cadastroalunos?useTimezone=true&serverTimezone=UTC";
		// Usuario
		String user = "root";
		// Senha
		String password = "root";
		Connection con = null;
		try {
		con = DriverManager.getConnection(url, user, password);
		System.out.println("Conectado ao banco de dados");
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return con;
		}
	
	public ArrayList<Aluno> listarAlunos() {
		ArrayList<Aluno> alunos = new ArrayList<>();
		String query = "SELECT * FROM alunos";
		try {
		Connection con = getConexao();
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		String idade = rs.getString(3);
		String semestre = rs.getString(4);
		String genero = rs.getString(5);
		String matricula = rs.getString(6);
		alunos.add(new Aluno(id, name, idade, semestre,genero,matricula));
		}
		rs.close();
		pst.close();
		con.close();
		} catch (Exception e) {
		System.out.println(e);
		}
		return alunos;	
		}
	
	public Aluno cadastrarAluno(Aluno aluno) {
		String query = "INSERT INTO alunos(nome,idade,semestre,genero,matricula) VALUES(?, ?, ?, ?, ?);";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getIdade());
			pst.setString(3, aluno.getSemestre());
			pst.setString(4, aluno.getGenero());
			pst.setString(5, aluno.getMatricula());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			while (rs.next()) {
				int id = rs.getInt(1);
				aluno.setId(id);
			}
			
	}
		catch (Exception e) {
			System.out.println(e);
			}
		return aluno;
		}
	
	
	
	public void excluirAluno(int id) {
		String query = "delete from alunos where id = ?";
		try {
		Connection con = getConexao();
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		pst.executeUpdate();
		pst.close();
		con.close();
		} catch (Exception e) {
		System.out.println(e);
		}
		
		}
	
public void alterarAluno(Aluno aluno) {
		
	}
	
	
	public Aluno pesquisarId(int id) {
		String query = "Select * from alunos where id = " + id;
		Aluno aluno = null;
		try {
			Connection con = getConexao();
			 Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
			int id2 = rs.getInt(1);
			String name = rs.getString(2);
			String idade = rs.getString(3);
			String semestre = rs.getString(4);
			String genero = rs.getString(5);
			String matricula = rs.getString(6);
			
			 aluno = new Aluno(id2, name, idade, semestre,genero,matricula);
			}
			rs.close();
			stmt.close();
			con.close();
		}catch (Exception e) {
			System.out.println(e);
			}
		
		return aluno;
			
			}
				
	
	
	public ArrayList<Aluno> pesquisarNomeMat(String pesquisa) {
		String query = "select * from alunos where nome like '%"+ pesquisa +"%' or matricula like '%" + pesquisa + "%'";
		ArrayList<Aluno> alunos = new ArrayList<>();
		try {
			Connection con = getConexao();
			 Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String idade = rs.getString(3);
			String semestre = rs.getString(4);
			String genero = rs.getString(5);
			String matricula = rs.getString(6);
			alunos.add(new Aluno(id, name, idade, semestre,genero,matricula));
			}
			rs.close();
			stmt.close();
			con.close();
		}catch (Exception e) {
			System.out.println(e);
			}
		
		return alunos;	
			
				
}
}
