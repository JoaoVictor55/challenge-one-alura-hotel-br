package testes.testeDeConexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import connection.ConnectionFactory;
import controller.HospedeController;
import dao.HospedeDAO;
import domain.hospede.DadosCadastroHospede;
import domain.nacionalidade.Nacionalidade;

public class TesteInsercaoHospede {

	public static void main(String[] args) throws SQLException {
		
		HospedeController controller = new HospedeController();
			
		DadosCadastroHospede dados = new DadosCadastroHospede("Ricardo", "Silvaaa", LocalDate.now().toString(),new Nacionalidade(74, "alemão") , "12345", 1);
		
		controller.cadastrarHospede(dados);
		
		
		
	}

}
