package controller;

import java.util.List;

import connection.ConnectionFactory;
import dao.FormaPagamentoDAO;
import dao.NacionalidadeDAO;
import domain.formaPagamento.FormaPagamento;
import domain.nacionalidade.Nacionalidade;

public class FormaDePagamentoController {
	
	private  FormaPagamentoDAO fpagamentoDAO;
	
	public FormaDePagamentoController() {
		
		this.fpagamentoDAO = new FormaPagamentoDAO(new ConnectionFactory().conectar());
	}
	
	public List<FormaPagamento> listarFormasDePagamento() {
		
		return fpagamentoDAO.listarFormaPagamento();
	}
}
