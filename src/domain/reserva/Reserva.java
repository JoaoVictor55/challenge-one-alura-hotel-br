package domain.reserva;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import domain.formaPagamento.FormaPagamento;
import domain.hospede.Hospede;

public class Reserva {

	Integer id;
	Date dataReserva;
	Date dataSaida;
	Double valor;
	FormaPagamento formaPagamento;
	Hospede hospede;
	
	public Reserva(Date date, Date date2,Double valor, FormaPagamento formaPagamento,
			Hospede hospede) {
		super();
		this.id = null;
		this.dataReserva = date;
		this.dataSaida = date2;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.hospede = hospede;
		this.valor = Reserva.calcularValor(this.dataReserva, this.dataSaida);
	}
	
	public Reserva(Date date, Date date2,Double valor, FormaPagamento formaPagamento) {
		super();
		this.id = null;
		this.dataReserva = date;
		this.dataSaida = date2;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.hospede = null;
	}
	
	public static Double calcularValor(Date inicio, Date fim) {
		
		if(inicio == null || fim == null) {
			return null;
		}
		return (double) Math.abs((TimeUnit.DAYS.convert(inicio.getTime() - fim.getTime(), TimeUnit.MILLISECONDS) * 120));
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}
	public Date getDataSaida() {
		return dataSaida;
	}




	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	
	public void atualizarValor() {
		
		this.valor = calcularValor(dataReserva, dataSaida);
	}


	public Double getValor() {
		
		
		
		if(valor == null) {
			atualizarValor();
		}
		return valor;
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
