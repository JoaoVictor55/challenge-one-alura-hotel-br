package testes.testeDeConexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import connection.ConnectionFactory;
import controller.HospedeController;
import dao.HospedeDAO;
import domain.hospede.DadosCadastroHospede;
import domain.hospede.Hospede;
import domain.nacionalidade.Nacionalidade;

public class TesteDetalharHospedes {

	public static void main(String[] args) throws SQLException {
		
		HospedeController controller = new HospedeController();
			
		List<Hospede> hospedes = controller.listarHospedes();
		
		hospedes.stream().forEach(p -> System.out.println(p));
		
		
		
	}

}