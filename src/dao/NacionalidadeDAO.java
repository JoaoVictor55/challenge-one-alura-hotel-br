package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import domain.nacionalidade.Nacionalidade;
import java.util.ArrayList;

public class NacionalidadeDAO {

	private Connection connection;

	private enum SQL_COMANDOS_SIMPLES{
		
		SELECIONAR_TUDO("select nac_id, nac_nome from tbl_nacionalidade");

		SQL_COMANDOS_SIMPLES(String comando) {
			
			this.comando = comando;
		}
		
		final private String comando;
		
		@Override
		public String toString() {
			
			return this.comando;
		}
	}
	
	public NacionalidadeDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public List<Nacionalidade> listarNacionalidade(){
		
		try {
			
			List<Nacionalidade> nacionalidades = new ArrayList<Nacionalidade>();
			
			try(PreparedStatement pstm = connection.prepareStatement(SQL_COMANDOS_SIMPLES.SELECIONAR_TUDO.toString())){
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					
					while(rst.next()) {
						
						nacionalidades.add(new Nacionalidade(rst.getInt(1), rst.getString(2)));
					}
				}
				
				
			}
			
			return nacionalidades;
			
		}
		catch(SQLException e) {
			
			throw new RuntimeException(e);
		}
	}
	
}
