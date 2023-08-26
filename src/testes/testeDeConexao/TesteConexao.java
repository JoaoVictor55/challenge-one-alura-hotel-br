package testes.testeDeConexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Classe básica para testar a conexão com o banco de dados usando o JDBC
 * */
public class TesteConexao {

	public static void main(String [] args) throws SQLException {
		
		//Conexão
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hotel_alura?use"
				+ "Timezone=true&serverTimezone=UTC", "root", "3speronaoesquecer!");
		
		//Fazendo uma consulta simples
		
		Statement stm = connection.createStatement(); //criando um statement sql
		
		stm.execute("SELECT * FROM tbl_hospede"); //executando
		
		ResultSet rst = stm.getResultSet(); //pegando o resultado
		
		while(rst.next()) {
			
			System.out.println(rst.getString("hos_nome")); //buscando uma coluna de string chamada de "hos_nome"
		}
		
		//Fazendo uma inserção:
		
		Statement insert = connection.createStatement();
		insert.execute("INSERT INTO tbl_hospede(hos_nome, hos_sobrenome, hos_data_nascimento,\n"
				+ "hos_nacionalidade, hos_telefone)"
				+ "values('eu', 'da silva', '2020-12-10', 1, 1245)");
		
		System.out.println("finalizando...");
		connection.close();
	}
	

}
