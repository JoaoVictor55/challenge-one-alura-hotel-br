package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.formaPagamento.FormaPagamento;
import domain.hospede.Hospede;
import domain.nacionalidade.Nacionalidade;
import domain.reserva.Reserva;
import domain.reserva.ReservaDetalhes;

public class ReversaDAO {

	private Connection connection;

	private enum SQL_COMANDOS_SIMPLES{
		
		SALVAR_RESERVA("insert into tbl_reserva(res_data_entrada, data_saida, res_valor, "
				+ "hos_id_tbl_hospede, res_forma_pagamento)"
				+ "values(?, ?, ?, ?, ?)"),
		
		
		DETALHAR_RESERVA("select res_id, res_data_entrada, res_valor, forma_pagamento, pagamentoID, "
				+ "data_saida, pagamentoID from vw_detalhar_reserva;"),
		
		DETALHAR_RESERVA_HOSPEDE("select res_id, res_data_entrada, res_valor, forma_pagamento,"
				+ "pagamentoID, "
				+ "data_saida, hos_id, hos_nome, "
				+ "hos_sobrenome, hos_data_nascimento, hos_nacionalidade, nac_nome, hos_telefone "
				+ "from vw_detalhar_reserva;"),
		
		DETALHAR_RESERVA_FORMA_PAGAMENTO_SOBRENOME("select res_id, res_data_entrada, res_valor, "
				+ "tbl_forma_pagamento.nome as forma_pagamento,  tbl_forma_pagamento.id as pagamentoID, "
				+ "data_saida, hos_id, hos_nome, hos_sobrenome, hos_data_nascimento, hos_nacionalidade, "
				+ "nac_nome, hos_telefone, nac_id  from tbl_reserva "
				+ "inner join tbl_forma_pagamento on tbl_forma_pagamento.id = tbl_reserva.res_forma_pagamento "
				+ "inner join vw_detalhar_hospede on vw_detalhar_hospede.hos_id = tbl_reserva.hos_id_tbl_hospede "
				+ "and vw_detalhar_hospede.hos_sobrenome = ?;"),
		
		
		DETALHAR_RESERVA_FORMA_PAGAMENTO_RESERVA_ID("select res_id, res_data_entrada, res_valor, "
				+ "tbl_forma_pagamento.nome as forma_pagamento,  tbl_forma_pagamento.id as pagamentoID, "
				+ "data_saida, hos_id, hos_nome, hos_sobrenome, hos_data_nascimento, hos_nacionalidade, "
				+ "nac_nome, hos_telefone, nac_id  from tbl_reserva "
				+ "inner join tbl_forma_pagamento on tbl_forma_pagamento.id = tbl_reserva.res_forma_pagamento "
				+ "inner join vw_detalhar_hospede on vw_detalhar_hospede.hos_id = tbl_reserva.hos_id_tbl_hospede "
				+ "where res_id = ?"
				),
		
		
		
		DELETAR_POR_ID("delete from tbl_reserva where res_id = ?");

		
		SQL_COMANDOS_SIMPLES(String comando) {
					
					this.comando = comando;
				}
		@Override
		public String toString() {
			return comando;
		}

		private final String comando;
		
		
	}
	
	public ReversaDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	

	public void deletar(Integer reservaId) {
		
		try {
			
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.DELETAR_POR_ID.toString())){
				
				pstm.setInt(1, reservaId);
				pstm.execute();
				
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public void salvar(Reserva reserva) {
		
		try {
			
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.SALVAR_RESERVA.toString(), Statement.RETURN_GENERATED_KEYS)){
				
				
				pstm.setDate(1, new java.sql.Date(reserva.getDataReserva().getTime()));
				pstm.setDate(2, new java.sql.Date(reserva.getDataSaida().getTime()));
				System.out.println(reserva.getValor());
				pstm.setDouble(3, reserva.getValor());
				pstm.setInt(5, reserva.getFormaPagamento().getId());
				if(reserva.getHospede() != null) {
					pstm.setInt(4, reserva.getHospede().getIndice());
				}
				else {
					
					pstm.setNull(4, java.sql.Types.INTEGER);
				}
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					
					while(rst.next()) {
						
						reserva.setId(rst.getInt(1)); 
					}
					
					
				}
				
				
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarReservaPorSobrenome(String sobrenome){
		
		try {
			
			List<Reserva> reservas = new ArrayList<Reserva>();
			
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.DETALHAR_RESERVA_FORMA_PAGAMENTO_SOBRENOME.toString())){
				
				pstm.setString(1, sobrenome);
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					
					while(rst.next()) {
						
						FormaPagamento formaPagamento = new FormaPagamento(rst.getInt(5), rst.getString(4));
						Nacionalidade nacionalidade = new Nacionalidade(rst.getInt(14), rst.getString(12));
						Hospede hospede = new Hospede(rst.getInt(7), rst.getString(8), rst.getString(9), 
								rst.getDate(10).toString(), nacionalidade, rst.getString(13));
						
						Reserva reserva = new Reserva( rst.getDate(2), rst.getDate(6), rst.getDouble(3), 
								formaPagamento, hospede);
						reserva.setId(rst.getInt(1));
						
						reservas.add(reserva);
					}
				}
			}
			
			return reservas;
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public Reserva buscarReservaPorId(Integer id) {
		// TODO Auto-generated method stub
		try {
			
			Reserva reserva = null;
			
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.DETALHAR_RESERVA_FORMA_PAGAMENTO_RESERVA_ID.toString())){
				
				pstm.setInt(1, id);
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					
				while(rst.next()) {	
					
					FormaPagamento formaPagamento = new FormaPagamento(rst.getInt(5), rst.getString(4));
					Nacionalidade nacionalidade = new Nacionalidade(rst.getInt(14), rst.getString(12));
					Hospede hospede = new Hospede(rst.getInt(7), rst.getString(8), rst.getString(9), 
							rst.getDate(10).toString(), nacionalidade, rst.getString(13));
					
					reserva = new Reserva( rst.getDate(2), rst.getDate(6), rst.getDouble(3), 
							formaPagamento, hospede);
					reserva.setId(rst.getInt(1));
					
				}
				}
			}
			
			return reserva;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Deprecated
	public List<Reserva> listarReserva(){
		
		try {
			List<Reserva> reservas = new ArrayList<Reserva>();
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.DETALHAR_RESERVA.toString())){
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					
					while(rst.next()) {
						
						
						FormaPagamento formaPagamento = new FormaPagamento(rst.getInt(5), rst.getString(4));
						Nacionalidade novaNacionalidade = new Nacionalidade(rst.getInt(11), rst.getString(12));
						Hospede novoHospede = new Hospede(rst.getInt(7), rst.getString(8), rst.getString(9), rst.getDate(10).toString(),novaNacionalidade, rst.getString(13));
						Reserva novaReserva = new Reserva( rst.getDate(2), rst.getDate(6),rst.getDouble(3), formaPagamento, novoHospede);
						novaReserva.setId(rst.getInt(1));
						
						reservas.add(novaReserva);
					}
				}
			}
			
			return reservas;
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void atualizar(Integer id, Date dataEntrada, Date dataSaida, Double valor,  Hospede hospede) {
		
		if(id == null) {
			throw new IllegalArgumentException("Id não pode ser nulo");
		}
		
		Boolean argumentosNULL = true;
		
		String psql = "update tbl_hospede set";
		
		if(dataEntrada != null) {
			psql += " res_data_entrada=?";
			argumentosNULL = false;
		}
		if(dataSaida != null) {
			psql += " data_saida?";
			argumentosNULL = false;
		}
		if(valor != null) {
			psql += " res_valor=?";
			argumentosNULL = false;
		}
		if(hospede != null) {
			psql += " hos_id_tbl_hospede=?";
			argumentosNULL = false;
		}
		
		if(argumentosNULL) {
			throw new IllegalArgumentException("Ao menos um dos parâmetros devem ser não nulos");
		}
		
		psql+=" where res_id=?";
		
		try {
			
			try(PreparedStatement pstm = connection.prepareStatement(psql)){
				
				Integer atual = 1;
				
				if(dataEntrada != null) {
					pstm.setDate(atual++, dataEntrada);
				}
				if(dataSaida != null) {
					pstm.setDate(atual++, dataSaida);
				}
				if(valor != null) {
					pstm.setDouble(atual++, valor);
					
				}
				if(hospede != null) {
					pstm.setInt(atual++, hospede.getIndice());
					
				}

				pstm.setInt(atual, id);
				
				pstm.execute();
				
			}
			
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
