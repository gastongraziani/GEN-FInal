package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.conexion;
import java.sql.ResultSet;

import modelo.Accion;
import modelo.AccionRiesgo;
import modelo.Ciudad;
import modelo.Provincia;
import modelo.Riesgo;
import modelo.TipoAccion;
import modelo.TipoRiesgo;
import modelo.Usuario;

public class AccionRiesgoDatos {
	
	
	public static void nuevoAR(AccionRiesgo ar) {
		Connection conn = null;
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = 
			conn.prepareStatement("INSERT INTO `accion_riesgo` (`id_riesgo`,`id_accion`,`descripcion`,`id_usualta`,`fecha_alta`,`latitud`,`longitud`) VALUES ( ?, ?, ?, ?, NOW(),?,?)");
			pst.setInt(1, ar.getIdriesgo());
			pst.setInt(2, ar.getId_accion());
			pst.setString(3, ar.getDescripcion());
			pst.setInt(4, ar.getId_usualta());
			pst.setFloat(5, ar.getLatitud());
			pst.setFloat(6, ar.getLongitud());
			pst.executeUpdate();
			conn.commit();
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}

		}	
	}
	public static  List<AccionRiesgo> mostrarAccRie() {
		Connection conn = null;
		List<AccionRiesgo> accries=new ArrayList<AccionRiesgo>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT"
					+ " accion_riesgo.id_accrie,accion_riesgo.id_usualta, accion_riesgo.latitud,accion_riesgo.longitud,riesgos.color,riesgos.tamaño,accion_riesgo.descripcion,riesgos.nombre nomrie,accion.nombre nomacc FROM accion_riesgo\r\n" + 
					"INNER JOIN riesgos ON riesgos.id_riesgo=accion_riesgo.id_riesgo\r\n" + 
					"INNER JOIN accion ON accion.id_accion=accion_riesgo.id_Accion\r\n" + 
					"WHERE riesgos.estado<>'Cancelado'");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				AccionRiesgo accrie=new AccionRiesgo();
				accrie.setIdaccrie(rs.getInt("id_accrie"));
				accrie.setLatitud(rs.getFloat("latitud"));
				accrie.setLongitud(rs.getFloat("longitud"));
				accrie.setColor(rs.getString("color"));
				accrie.setTam(rs.getString("tamaño"));
				accrie.setDescripcion(rs.getString("descripcion"));
				accrie.setNomrie(rs.getString("nomrie"));
				accrie.setNomacc(rs.getString("nomacc"));
				accrie.setId_usualta(rs.getInt("id_usualta"));
				accries.add(accrie);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return accries;
	}
	
	public static boolean validarUsuarioAccRie(int idusu,int idusuact) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
		    //Asi se hace una consulta para que no metan SQL inject
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM usuarios WHERE BINARY usuario=? and  password=? and habilitado=0");
			pst.setInt(1, idusu); 
			pst.setInt(2, idusuact);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				resp = true;
			}
		
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
		}
		return resp;
	}
	
	public static boolean eliminarAccRie(int id) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = conn.prepareStatement("DELETE FROM `accion_riesgo` WHERE `id_accrie`=?");				
			pst.setInt(1, id);
			pst.executeUpdate();
			resp = true;
			conn.close();
		} 
		catch (SQLException e) {resp=false;System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {resp=false;conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return resp;	
	}
}


	