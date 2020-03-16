package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
	
	public static Connection getConnection() {
		Connection conexion = null;
	 
		try {
			String servidor = "jdbc:mysql://localhost:3306/desastres";
			Class.forName("com.mysql.jdbc.Driver");
			String usuario = "root";
			String password = "root";
			conexion = DriverManager.getConnection(servidor, usuario, password);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("3");
		}
		return conexion;
	}
}
		
	

