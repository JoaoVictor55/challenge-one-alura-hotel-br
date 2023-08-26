package domain.reserva;

import java.time.LocalDateTime;

import domain.formaPagamento.FormaPagamento;
import domain.hospede.Hospede;

public class Reserva {

	Integer id;
	LocalDateTime dataReserva;
	Double valor;
	FormaPagamento formaPagamento;
	Hospede hospede;
	
	public Reserva(Integer id, LocalDateTime dataReserva, Double valor, FormaPagamento formaPagamento,
			Hospede hospede) {
		super();
		this.id = id;
		this.dataReserva = dataReserva;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.hospede = hospede;
	}
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(LocalDateTime dataReserva) {
		this.dataReserva = dataReserva;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Hospede getHospede() {
		return hospede;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	
}
