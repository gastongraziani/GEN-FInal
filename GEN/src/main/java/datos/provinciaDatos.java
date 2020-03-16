package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import datos.conexion;
import java.sql.ResultSet;

import modelo.Provincia;

public class provinciaDatos {
	public static  List<Provincia> mostrarTodos() {
		Connection conn = null;
		List<Provincia> provs=new ArrayList<Provincia>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * from provincia order by nombre asc");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				Provincia pr=new Provincia();
				pr.setId_provincia(rs.getInt("id_provincia"));
				pr.setNombre(rs.getString("nombre"));
				provs.add(pr);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return provs;
	}
	
	
}
	
	
		
	
	

