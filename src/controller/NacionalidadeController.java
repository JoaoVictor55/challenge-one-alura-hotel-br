package controller;

import java.util.List;

import connection.ConnectionFactory;
import dao.NacionalidadeDAO;
import domain.nacionalidade.Nacionalidade;

public class NacionalidadeController {

	private NacionalidadeDAO nacionalidadeDAO;
	
	public NacionalidadeController() {
		
		this.nacionalidadeDAO = new NacionalidadeDAO(new ConnectionFactory().conectar());
	}
	
	public List<Nacionalidade> listarNacionalidades() {
		
		return nacionalidadeDAO.listarNacionalidade();
	}
}
