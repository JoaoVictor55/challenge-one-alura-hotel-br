package controller;

import java.sql.SQLException;

import connection.ConnectionFactory;
import dao.HospedeDAO;
import domain.hospede.DadosCadastroHospede;
import domain.hospede.Hospede;
import domain.nacionalidade.Nacionalidade;

import java.util.List;
public class HospedeController {

	private HospedeDAO hosdao;
	
	public HospedeController(){
		
		this.hosdao = new HospedeDAO(new ConnectionFactory().conectar());
	}
	
	public Hospede cadastrarHospede(DadosCadastroHospede cadastro) {
		
		Hospede hospede = new Hospede(cadastro);
		hosdao.salvar(hospede);
		
		return hospede;
			
	}
	
	public void deletarHospede(Integer id) {
		
		hosdao.deletar(id);
	}

	public List<Hospede> listarHospedes() {
		
		return hosdao.listarHospedes();
	}
	

	public void atualizar(Integer id, String nome, String sobrenome, String datanascimento, Nacionalidade nacionalidade,
			String telefone) {
		
		hosdao.atualizar(id, nome, sobrenome, datanascimento, nacionalidade, telefone);
		
	}
	

}
