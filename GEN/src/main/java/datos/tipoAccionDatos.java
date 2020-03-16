package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.conexion;
import java.sql.ResultSet;

import modelo.TipoAccion;
import modelo.TipoRiesgo;

public class tipoAccionDatos {
	public static  List<TipoAccion> mostrarTodos() {
		Connection conn = null;
		List<TipoAccion> tas=new ArrayList<TipoAccion>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * from tipo_accion order by nombre asc");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				TipoAccion ta=new TipoAccion();
				ta.setIdTipo_accion(rs.getInt("id_tipo_Accion"));
				ta.setDescripcion(rs.getString("descripcion"));
				tas.add(ta);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return tas;
	}
	
	public static void nuevoTA(TipoAccion ta) {
		Connection conn = null;
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = 
			conn.prepareStatement("INSERT INTO `tipo_accion` (`descripcion`,`usu_alta_ta`,`fecha_alta_ta`,`nombre`) VALUES ( ?, ?, NOW(), ?)");
			pst.setString(1, ta.getDescripcion());
			pst.setInt(2,ta.getUsu_alta_ta());
			pst.setString(3,ta.getNombre());
			pst.executeUpdate();
			conn.commit();
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}

		}	
	}
	
	public static  List<TipoAccion> mostrarTipoAccion() {
		Connection conn = null;
		List<TipoAccion> taccs=new ArrayList<TipoAccion>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * FROM tipo_accion");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				TipoAccion ta=new TipoAccion();
				ta.setNombre(rs.getString("nombre"));
				ta.setDescripcion(rs.getString("descripcion"));
				ta.setFecha_alta_ta(rs.getDate("fecha_alta_ta"));
				ta.setIdTipo_accion(rs.getInt("id_tipo_Accion"));
				ta.setUsu_alta_ta(rs.getInt("usu_alta_ta"));
				taccs.add(ta);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return taccs;
	}
	
	public static boolean eliminartipoAccion(int id_ta) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = conn.prepareStatement("DELETE FROM `tipo_accion` WHERE `id_tipo_Accion`=?");				
			pst.setInt(1, id_ta);
			pst.executeUpdate();
			resp = true;
			conn.close();
		} 
		catch (SQLException e) {resp=false;System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {resp=false;conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return resp;	
	}
	
	
	public static TipoAccion buscartipoAcc(int idta) {
		Connection conn = null;
		TipoAccion ta=new TipoAccion();
		try {
			conn = conexion.getConnection();
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * FROM tipo_Accion WHERE id_tipo_Accion=?" );
			pst.setInt(1, idta);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{                           
				ta.setNombre(rs.getString("nombre"));
				ta.setDescripcion(rs.getString("descripcion"));
				ta.setFecha_alta_ta(rs.getDate("fecha_alta_ta"));
				ta.setIdTipo_accion(rs.getInt("id_tipo_Accion"));
				ta.setUsu_alta_ta(rs.getInt("usu_alta_ta"));
			}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return ta;
	}
	

	public static boolean modificarTipoAcc(TipoAccion ta) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = conn.prepareStatement("UPDATE `tipo_accion` SET `nombre`=?, `descripcion`=? WHERE id_tipo_Accion=?");				
			pst.setString(1, ta.getNombre());
			pst.setString(2, ta.getDescripcion());
			pst.setInt(3,ta.getIdTipo_accion());
			System.out.println(pst);
			pst.executeUpdate();
			conn.commit();
			conn.close();
		} 
		catch (SQLException e) {resp=false;System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {resp=false;conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return resp;	
	}
}


	