package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.conexion;
import java.sql.ResultSet;

import modelo.Ciudad;

public class ciudadDatos {
	public static  List<Ciudad> mostrarTodos() {
		Connection conn = null;
		List<Ciudad> cius=new ArrayList<Ciudad>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * from ciudad order by nombre asc");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				Ciudad ci=new Ciudad();
				ci.setId_provincia(rs.getInt("id_ciudad"));
				ci.setNombre(rs.getString("nombre"));
				ci.setCodigo_postal(rs.getInt("codigo_postal"));
				ci.setId_provincia(rs.getInt("id_provincia"));
				cius.add(ci);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return cius;
	}
	
	
}
	