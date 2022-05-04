package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;
import jdbc.DB;

public class Programa {

	public static void main(String[] args) throws IOException, SQLException {

		Connection con = DB.getConexao();
		System.out.println("Conexão realizada com sucesso !");
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		
		Scanner console = new Scanner(System.in);
		
		int opcao = 0;
		
		try {

			do {
				System.out.print("######## Menu ########" + 
								"\n1- Cadastrar" + 
								"\n2- Listar"    + 
								"\n3- Alterar"   +
								"\n4- Excluir"   + 
								"\n5- Sair"      +
								"\n\tOpção: ");
				opcao = Integer.parseInt(console.nextLine());

				switch(opcao) {
					case 1:
						Aluno a = new Aluno();
						AlunoJDBC acao = new AlunoJDBC();
	
						System.out.print("\n*** Cadastrar Aluno ***\n\r");
						System.out.print("Nome: ");
						a.setNome(console.nextLine());
						System.out.print("Sexo: ");
						a.setSexo(console.nextLine());
			
						System.out.print("Data de nascimento (dd/MM/yyyy): ");
						a.setDt_nasc( Date.valueOf( LocalDate.parse( console.nextLine(), formato) ) ) ;
						
						acao.salvar(a, con);
						console.nextLine();
						
						break;
						
					case 2 :
						AlunoJDBC aux = new AlunoJDBC();
						
						System.out.println("\n*** Lista de Alunos ***\n");
						aux.listar(con);
						System.out.println();
						
						break;
					case 3:
						Aluno a2 = new Aluno();
						AlunoJDBC aux2 = new AlunoJDBC();
						
						System.out.println("\n*** Alterar Aluno ***\n");
						
						aux2.listar(con);
						
						System.out.print("\nNovo nome: ");
						a2.setNome(console.nextLine());
						System.out.print("Novo sexo: ");
						a2.setSexo(console.nextLine());
						
						System.out.print("Nova data de nascimento (dd/MM/yyyy): ");
						a2.setDt_nasc(Date.valueOf( LocalDate.parse( console.nextLine(), formato) ));
						
						System.out.print("Informe o ID do aluno que será atualizado: ");
						a2.setId(console.nextInt());
						
						aux2.alterar(a2, con);
						console.nextLine();
						
						break;
					case 4:
						AlunoJDBC aux3 = new AlunoJDBC();
						
						System.out.println("\n*** Remover Alunos ***\n");
						aux3.listar(con);
						System.out.print("\nEscolha o ID do aluno que deseja remover: ");
						aux3.apagar(console.nextInt(), con);
						console.nextLine();
						
						break;
				}
				
			} while (opcao != 5);

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
		
		DB.fechaConexao();
		System.out.println("Conexão fechada com sucesso !");
	}
}
