package controller;

import java.sql.Date;
import java.util.List;

import connection.ConnectionFactory;
import dao.ReversaDAO;
import domain.formaPagamento.FormaPagamento;
import domain.hospede.Hospede;
import domain.reserva.Reserva;
import domain.reserva.ReservaDetalhes;

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
	
	public void atualizar(Integer id, Date dataEntrada, Date dataSaida, Double valor,  Hospede hospede, FormaPagamento formaPagamento) {
		
		this.dao.atualizar(id, dataEntrada, dataSaida, valor, hospede, formaPagamento);
	}
	
	public List<Reserva> listarPorSobreNome(String sobrenome){
		
		return this.dao.buscarReservaPorSobrenome(sobrenome);
	}
	
	public Reserva listarPorId(Integer id){
		
		return this.dao.buscarReservaPorId(id);
	}
}
