package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private Connection connection;

	public ConnectionFactory(){
	
		try {
		this.connection =  DriverManager.getConnection("jdbc:mysql://localhost/hotel_alura?use"
				+ "Timezone=true&serverTimezone=UTC", "root", "3speronaoesquecer!");
		}
		catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public Connection conectar() {
		return this.connection;
		
	}
	
}