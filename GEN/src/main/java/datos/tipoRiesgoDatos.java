package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.conexion;
import java.sql.ResultSet;

import modelo.Riesgo;
import modelo.TipoRiesgo;

public class tipoRiesgoDatos {
	public static  List<TipoRiesgo> mostrarTodos() {
		Connection conn = null;
		List<TipoRiesgo> trs=new ArrayList<TipoRiesgo>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * from tipo_riesgo order by nombre asc");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				TipoRiesgo tr=new TipoRiesgo();
				tr.setId_tipor(rs.getInt("id_tipo"));
				tr.setNombre(rs.getString("nombre"));
				tr.setDescripcion(rs.getString("descripcion"));
				trs.add(tr);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return trs;
	}
	
	public static void nuevoTR(TipoRiesgo tr) {
		Connection conn = null;
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = 
			conn.prepareStatement("INSERT INTO `tipo_riesgo` (`descripcion`,`nombre`,`usu_alta_tr`,`fecha_alta_tr`) VALUES (?, ?, ?, NOW())");
			pst.setString(1, tr.getDescripcion());
			pst.setString(2, tr.getNombre());
			pst.setInt(3, tr.getId_usu_atr());
			pst.executeUpdate();
			conn.commit();
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}

		}	
	}
	
	public static  List<TipoRiesgo> mostrarTipoRiesgos() {
		Connection conn = null;
		List<TipoRiesgo> trs=new ArrayList<TipoRiesgo>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * FROM tipo_riesgo");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				TipoRiesgo tr=new TipoRiesgo();
				tr.setNombre(rs.getString("nombre"));
				tr.setDescripcion(rs.getString("descripcion"));
				tr.setFecha_alta_tr(rs.getDate("fecha_alta_tr"));
				tr.setId_tipor(rs.getInt("id_tipo"));
				tr.setId_usu_atr(rs.getInt("usu_alta_tr"));
				trs.add(tr);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return trs;
	}
	
	public static boolean eliminartipoRiesgo(int id_tr) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = conn.prepareStatement("DELETE FROM `tipo_riesgo` WHERE `id_tipo`=?");				
			pst.setInt(1, id_tr);
			System.out.println(pst);
			pst.executeUpdate();
			resp = true;
			conn.close();
		} 
		catch (SQLException e) {resp=false;System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {resp=false;conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return resp;	
	}
	
	
	public static  TipoRiesgo buscartipoRie(int idtr) {
		Connection conn = null;
		TipoRiesgo tr=new TipoRiesgo();
		try {
			conn = conexion.getConnection();
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * FROM tipo_riesgo WHERE id_tipo=?" );
			pst.setInt(1, idtr);
			System.out.println(pst);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				tr.setId_tipor(rs.getInt("id_tipo"));
				tr.setNombre(rs.getString("nombre"));
				tr.setDescripcion(rs.getString("descripcion"));
				tr.setFecha_alta_tr(rs.getDate("fecha_alta_tr"));
				tr.setId_usu_atr(rs.getInt("usu_alta_tr"));
			}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return tr;
	}
	

	public static boolean modificarTipoRie(TipoRiesgo tr) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = conn.prepareStatement("UPDATE `tipo_riesgo` SET `nombre`=?, `descripcion`=? WHERE id_tipo=?");				
			pst.setString(1, tr.getNombre());
			pst.setString(2, tr.getDescripcion());
			pst.setInt(3,tr.getId_tipor());
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
	