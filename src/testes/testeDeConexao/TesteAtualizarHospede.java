package testes.testeDeConexao;

import java.sql.SQLException;
import java.util.List;

import controller.HospedeController;
import domain.hospede.Hospede;

public class TesteAtualizarHospede {

	public static void main(String[] args) throws SQLException {

		HospedeController controller = new HospedeController();
		
		controller.atualizar(5, null, "Ricardo", null, null, null);
		
		

	}

}
