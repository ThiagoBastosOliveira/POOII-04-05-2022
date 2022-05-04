package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	public static String sql;
	public static PreparedStatement pst;
	
	public void salvar(Aluno a, Connection con) throws IOException {
		
		try {
			
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?,  ?, ?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(3, dataSql);
			
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar(Connection con) {
		
		try {
			
			sql = "SELECT * FROM aluno";
			
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " - " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getDate("dt_nasc"));
			}
			
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public void apagar(int id, Connection con) {
		
		try {
	
			sql = "DELETE FROM aluno WHERE id = ?";
			
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			
			System.out.println("Aluno de ID = " + id + " excluido.\n");
			
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void alterar(Aluno a, Connection con) {
		
		try {
			sql = "UPDATE aluno SET nome = ?, sexo = ?, dt_nasc = ? WHERE id = ?";
			
			pst = con.prepareStatement(sql);
			
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			pst.setDate(3, a.getDt_nasc());
			pst.setInt(4, a.getId());
			
			pst.executeUpdate();
			
			System.out.println("Aluno atualizado com sucesso!\n");
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
}

