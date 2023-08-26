package testes.testeDeConexao;

import java.sql.SQLException;

import connection.ConnectionFactory;
import controller.HospedeController;
import dao.HospedeDAO;
import domain.hospede.DadosCadastroHospede;
import domain.nacionalidade.Nacionalidade;

public class DeletarHospede {

	public static void main(String[] args) throws SQLException {
		
		HospedeController controller = new HospedeController();
		
		controller.deletarHospede(7);;
		
		
		
	}

}