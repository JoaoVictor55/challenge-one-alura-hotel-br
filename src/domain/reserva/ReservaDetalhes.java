package domain.reserva;

import java.util.Date;

import domain.formaPagamento.FormaPagamento;

public record ReservaDetalhes(Integer numeroReserva, Date checkin, Date checkout, Double valor, FormaPagamento formaPagamento) {

}
