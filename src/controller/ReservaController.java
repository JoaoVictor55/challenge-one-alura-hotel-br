package controller;

import java.sql.Date;
import java.util.List;

import connection.ConnectionFactory;
import dao.ReversaDAO;
import domain.hospede.Hospede;
import domain.reserva.Reserva;

public class ReservaController {

	private ReversaDAO dao;
	
	public ReservaController() {
		
		this.dao = new ReversaDAO(new ConnectionFactory().conectar());
	}
	
	public void salvar(Reserva reserva) {
		
		this.dao.salvar(reserva);
	}
	
	public void deletar(Integer reservaID) {
		
		this.dao.deletar(reservaID);
	}
	
	public void atualizar(Integer id, Date dataEntrada, Date dataSaida, Double valor,  Hospede hospede) {
		
		this.dao.atualizar(id, dataEntrada, dataSaida, valor, hospede);
	}
	
	public List<Reserva> listar(){
		
		return this.dao.listarReserva();
	}
}
