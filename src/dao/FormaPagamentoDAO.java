package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.formaPagamento.FormaPagamento;


public class FormaPagamentoDAO {

	private Connection connection;
	
	private enum SQL_COMANDOS_SIMPLES{
		
		SELECIONAR_TUDO("select id, nome from tbl_forma_pagamento");

		SQL_COMANDOS_SIMPLES(String comando) {
			
			this.comando = comando;
		}
		
		final private String comando;
		
		@Override
		public String toString() {
			
			return this.comando;
		}
	}

	public FormaPagamentoDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public List<FormaPagamento> listarFormaPagamento(){
		
		try {
			
			List<FormaPagamento> formaDePagamentos = new ArrayList<FormaPagamento>();
			
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.SELECIONAR_TUDO.toString())){
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					
					while(rst.next()) {
						
						formaDePagamentos.add(new FormaPagamento(rst.getInt(1), rst.getString(2)));
					}
				}
				
				
			}
			
			return formaDePagamentos;
			
		}
		catch(SQLException e) {
			
			throw new RuntimeException(e);
		}
	}
	
	
}
