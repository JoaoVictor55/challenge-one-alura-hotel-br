package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.hospede.Hospede;
import domain.nacionalidade.Nacionalidade;

public class HospedeDAO {

	private Connection connection;
	
	private enum SQL_COMANDOS_SIMPLES{
		
		SALVAR_HOSPEDE("insert into tbl_hospede(hos_nome, hos_sobrenome, hos_data_nascimento,"
				+ "hos_nacionalidade, hos_telefone)"
				+ "values(?, ?, ?, ?, ?)"),
		
		SELECIONAR_TUDO("select hos_id, hos_nome, hos_sobrenome, hos_data_nascimento, hos_nacionalidade,"
				+ "hos_telefone from tbl_hospede"),
		
		DETALHAR_HOSPEDES("select  hos_id, hos_nome, hos_sobrenome, hos_data_nascimento, hos_nacionalidade, nac_nome,hos_telefone "
				+ "from vw_detalhar_hospede"),
		
		DELETAR_POR_ID("delete from tbl_hospede where hos_id = ?");

		
		SQL_COMANDOS_SIMPLES(String comando) {
					
					this.comando = comando;
				}
		@Override
		public String toString() {
			return comando;
		}

		private final String comando;
		
		
	}

	public HospedeDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	
	
	public List<Hospede> listarHospedes(){
		
		try {
			List<Hospede> hospedes = new ArrayList<Hospede>();
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.DETALHAR_HOSPEDES.toString())){
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					
					while(rst.next()) {
						
						
						Nacionalidade nacionalidade = new Nacionalidade(rst.getInt(5), rst.getString(6));
						Hospede novoHospede = new Hospede(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4).toString(), nacionalidade, rst.getString(7));
						
						hospedes.add(novoHospede);
					}
				}
			}
			
			return hospedes;
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void deletar(Integer hospedeIndice) {
		
		try {
			
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.DELETAR_POR_ID.toString())){
				
				pstm.setInt(1, hospedeIndice);
				pstm.execute();
				
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void atualizar(Integer id, String nome, String sobrenome, String datanascimento, Nacionalidade nacionalidade,
			String telefone) {
		
		if(id == null) {
			throw new IllegalArgumentException("Id não pode ser nulo");
		}
		
		Boolean argumentosNULL = true;
		
		String psql = "update tbl_hospede set";
		
		if(nome != null) {
			psql += " hos_nome=?";
			argumentosNULL = false;
		}
		if(sobrenome != null) {
			psql += " hos_sobrenome=?";
			argumentosNULL = false;
		}
		if(datanascimento != null) {
			psql += " hos_data_nascimento=?";
			argumentosNULL = false;
		}
		if(nacionalidade != null) {
			psql += " hos_nacionalidade=?";
			argumentosNULL = false;
		}
		if(telefone != null) {
			psql += " hos_telefone=?";
			argumentosNULL = false;
		}
		
		if(argumentosNULL) {
			throw new IllegalArgumentException("Ao menos um dos parâmetros devem ser não nulos");
		}
		
		psql+=" where hos_id=?";
		
		try {
			
			try(PreparedStatement pstm = connection.prepareStatement(psql)){
				
				Integer atual = 1;
				
				if(nome != null) {
					pstm.setString(atual++, nome);
				}
				if(sobrenome != null) {
					pstm.setString(atual++, sobrenome);
				}
				if(datanascimento != null) {
					pstm.setDate(atual++, Date.valueOf(datanascimento));
					
				}
				if(nacionalidade != null) {
					pstm.setInt(atual++, nacionalidade.getId());
					
				}
				if(telefone != null) {
					pstm.setString(atual++, telefone);
					
				}
				
				pstm.setInt(atual, id);
				
				pstm.execute();
				
			}
			
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvar(Hospede hospede) {
		
		try {
			
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.SALVAR_HOSPEDE.toString(), Statement.RETURN_GENERATED_KEYS)){
				
				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setString(3, hospede.getDataNascimento().toString());
				pstm.setInt(4, hospede.getNacionalidade().getId());
				pstm.setString(5, hospede.getTelefone());
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					
					while(rst.next()) {
						
						hospede.setIndice(rst.getInt(1)); 
					}
					
					
				}
				
				
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
