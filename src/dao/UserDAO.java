package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.hospede.Hospede;
import domain.user.User;

public class UserDAO {
	
	private Connection connection;
	
	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	public User buscar(String userName) {
		
		User novoUser = new User();
		
		try {
		
			final String BUSCAR = "select indice, senha from tbl_usuarios where userName=?";
			
			try(PreparedStatement pstm = connection.prepareStatement(BUSCAR, Statement.RETURN_GENERATED_KEYS)){
				System.out.println(userName);
				pstm.setString(1, userName);
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					
					while(rst.next()) {
						
						novoUser.setIndice(rst.getInt(1));
						novoUser.setUserName(userName);
						novoUser.setSenha(rst.getString(2));
						
					}
					
				}
				
			}
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
		}
		
		return novoUser;
		
	}
}
