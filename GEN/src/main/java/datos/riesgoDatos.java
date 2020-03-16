package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.conexion;
import modelo.Ciudad;
import modelo.Provincia;
import modelo.Riesgo;
import modelo.TipoRiesgo;

public class riesgoDatos {
	public static void nuevoRiesgo(Riesgo rie) {
		Connection conn = null;
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			System.out.println("riesgoDatos - nuevoRiesgo");
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = 
			conn.prepareStatement("INSERT INTO `riesgos` (`nombre`, `estado`, `tipo_riesgo`, `id_usuario`, `descripcion`,`fecha_inicio`,`id_ciudad`,`id_provincia`,`imagen`,`latitud`,`longitud`,`color`,`tamaño`) VALUES ( ?, ?, ?, ?, ?,NOW(), ?, ?,?,?,?,?,?)");
			pst.setString(1, rie.getNombre());
			pst.setString(2, rie.getEstado());
			pst.setInt(3,rie.getTipo_riesgo());
			pst.setInt(4,rie.getId_usuario());
			pst.setString(5,rie.getDescripcion());
			pst.setInt(6,rie.getCiu());
			pst.setInt(7,rie.getPrv());
			pst.setString(8,rie.getImagen());
			pst.setFloat(9, rie.getLatitud());
			pst.setFloat(10, rie.getLongitud());
			pst.setString(11, rie.getColor());
			pst.setInt(12, rie.getTamaño());
			pst.executeUpdate();
			conn.commit();
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
			
	}
	
	public static  List<Riesgo> mostrarRiesgos(int idusu) {
		Connection conn = null;
		List<Riesgo> ries=new ArrayList<Riesgo>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT riesgos.*,tipo_riesgo.nombre trnom,ciudad.nombre ciunom,provincia.nombre prvnom FROM riesgos\r\n" + 
					"INNER JOIN tipo_riesgo ON riesgos.tipo_riesgo=tipo_riesgo.id_tipo\r\n" + 
					"INNER JOIN usuarios ON riesgos.id_usuario=usuarios.id_usuario\r\n" + 
					"INNER JOIN provincia ON riesgos.id_provincia=provincia.id_provincia\r\n" + 
					"INNER JOIN ciudad ON riesgos.id_ciudad=ciudad.id_ciudad\r\n" + 
					"WHERE riesgos.id_usuario=? AND estado<>'Cancelado'");
			pst.setInt(1, idusu);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				Riesgo rie=new Riesgo();
				rie.setIdriesgo(rs.getInt("id_riesgo"));
				rie.setNombre(rs.getString("nombre"));
				rie.setFecha_inicio(rs.getDate("fecha_inicio"));
				rie.setFecha_fin(rs.getDate("fecha_fin"));
				rie.setEstado(rs.getString("estado"));
				rie.setId_usuario(rs.getInt("id_usuario"));
				rie.setDescripcion(rs.getString("descripcion"));
					TipoRiesgo tr = new TipoRiesgo();
					tr.setNombre(rs.getString("trnom"));
					tr.setId_tipor(rs.getInt("tipo_riesgo"));
				rie.setTipoRiesgo(tr);
					Ciudad ci = new Ciudad();
					ci.setNombre(rs.getString("ciunom"));
					ci.setId_ciudad(rs.getInt("id_ciudad"));
				rie.setCiudad(ci);
					Provincia pr = new Provincia();
					pr.setNombre(rs.getString("prvnom"));
					pr.setId_provincia(rs.getInt("id_provincia"));
				rie.setProvincia(pr);
				ries.add(rie);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return ries;
	}
	
	public static  List<Riesgo> mostrarTodosRiesgos() {
		Connection conn = null;
		List<Riesgo> ries=new ArrayList<Riesgo>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT riesgos.*,tipo_riesgo.nombre trnom,ciudad.nombre ciunom,provincia.nombre prvnom FROM riesgos\r\n" + 
					"INNER JOIN tipo_riesgo ON riesgos.tipo_riesgo=tipo_riesgo.id_tipo\r\n" + 
					"INNER JOIN usuarios ON riesgos.id_usuario=usuarios.id_usuario\r\n" + 
					"INNER JOIN provincia ON riesgos.id_provincia=provincia.id_provincia\r\n" + 
					"INNER JOIN ciudad ON riesgos.id_ciudad=ciudad.id_ciudad\r\n" + 
					"WHERE estado<>'Cancelado'");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				Riesgo rie=new Riesgo();
				rie.setIdriesgo(rs.getInt("id_riesgo"));
				rie.setNombre(rs.getString("nombre"));
				rie.setFecha_inicio(rs.getDate("fecha_inicio"));
				rie.setFecha_fin(rs.getDate("fecha_fin"));
				rie.setEstado(rs.getString("estado"));
				rie.setId_usuario(rs.getInt("id_usuario"));
				rie.setLongitud(rs.getFloat("longitud"));
				rie.setLatitud(rs.getFloat("latitud"));
				rie.setColor(rs.getString("color"));
				rie.setTamaño(rs.getInt("tamaño"));
				rie.setImagen(rs.getString("imagen"));
				rie.setDescripcion(rs.getString("descripcion"));
					TipoRiesgo tr = new TipoRiesgo();
					tr.setNombre(rs.getString("trnom"));
					tr.setId_tipor(rs.getInt("tipo_riesgo"));
				rie.setTipoRiesgo(tr);
					Ciudad ci = new Ciudad();
					ci.setNombre(rs.getString("ciunom"));
					ci.setId_ciudad(rs.getInt("id_ciudad"));
				rie.setCiudad(ci);
					Provincia pr = new Provincia();
					pr.setNombre(rs.getString("prvnom"));
					pr.setId_provincia(rs.getInt("id_provincia"));
				rie.setProvincia(pr);
				ries.add(rie);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return ries;
	}
	

		public static boolean eliminarRiesgo(int id_rie) {
			Connection conn = null;
			boolean resp = false;
			try {
				conn = conexion.getConnection();
				//Insert con parametros para que no hagan SQL Inject
				PreparedStatement pst = conn.prepareStatement("UPDATE `riesgos` SET `estado`='Cancelado' where `id_riesgo`=?");				
				pst.setInt(1, id_rie);
				pst.executeUpdate();
				resp = true;
				conn.close();
			} 
			catch (SQLException e) {resp=false;System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
			finally {if(conn!=null)	try {resp=false;conn.close();} catch (SQLException e) {System.out.println(e.toString());}
			}
			return resp;	
		}
		
		public static  Riesgo buscarRie(int idrie) {
			Connection conn = null;
			Riesgo rie=new Riesgo();
			try {
				conn = conexion.getConnection();
				PreparedStatement pst = 
				conn.prepareStatement("SELECT riesgos.* FROM riesgos WHERE id_riesgo=?" );
				pst.setInt(1, idrie);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					rie.setIdriesgo(rs.getInt("id_riesgo"));
					rie.setNombre(rs.getString("nombre"));
					rie.setFecha_inicio(rs.getDate("fecha_inicio"));
					rie.setFecha_fin(rs.getDate("fecha_fin"));
					rie.setEstado(rs.getString("estado"));
					rie.setId_usuario(rs.getInt("tipo_riesgo"));
					rie.setId_usuario(rs.getInt("id_usuario"));
					rie.setDescripcion(rs.getString("descripcion"));
					rie.setId_usuario(rs.getInt("id_usuario"));
					rie.setId_usuario(rs.getInt("id_provincia"));
					rie.setImagen(rs.getString("imagen"));
					rie.setLongitud(rs.getFloat("longitud"));
					rie.setLatitud(rs.getFloat("latitud"));
					rie.setColor(rs.getString("color"));
					rie.setTamaño(rs.getInt("tamaño"));
				}
				conn.close();
			} 
			catch (SQLException e) {System.out.println(e.toString());}
			finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
			}
			return rie;
		}
		
		
		public static boolean modificarRie(Riesgo rie) {
			Connection conn = null;
			boolean resp = false;
			try {
				conn = conexion.getConnection();
				//Insert con parametros para que no hagan SQL Inject
				PreparedStatement pst = conn.prepareStatement("UPDATE `riesgos` SET `nombre`=?, `estado`=?, `tipo_riesgo`=?, `descripcion`=?,`id_ciudad`=?,`id_provincia`=?, `imagen`=?, `longitud`=?, `latitud`=?, `color`=?, `tamaño`=? WHERE id_riesgo=?");				
				pst.setString(1, rie.getNombre());
				pst.setString(2, rie.getEstado());
				pst.setInt(3,rie.getTipo_riesgo());
				pst.setString(4,rie.getDescripcion());
				pst.setInt(5,rie.getCiu());
				pst.setInt(6,rie.getPrv());
				pst.setString(7,rie.getImagen());
				pst.setFloat(8, rie.getLongitud());
				pst.setFloat(9, rie.getLatitud());
				pst.setString(10, rie.getColor());
				pst.setInt(11, rie.getTamaño());
				pst.setInt(12, rie.getIdriesgo());
				pst.executeUpdate();
				conn.close();
			} 
			catch (SQLException e) {resp=false;System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
			finally {if(conn!=null)	try {resp=false;conn.close();} catch (SQLException e) {System.out.println(e.toString());}
			}
			return resp;	
		}
	
}
