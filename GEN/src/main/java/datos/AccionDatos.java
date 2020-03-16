package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.conexion;
import java.sql.ResultSet;

import modelo.Accion;
import modelo.TipoAccion;

public class AccionDatos {
	public static  List<Accion> mostrarTodos() {
		Connection conn = null;
		List<Accion> as=new ArrayList<Accion>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * from accion order by nombre asc");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				Accion a=new Accion();
				a.setId_accion(rs.getInt("id_accion"));
				a.setId_tipo_Accion(rs.getInt("id_tipo_accion"));
				a.setDescripcion(rs.getString("descripcion"));
				a.setNombre(rs.getString("nombre"));
				a.setLatitud(rs.getFloat("latitud"));
				a.setLongitud(rs.getFloat("longitud"));
				as.add(a);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return as;
	}
	
	public static void nuevoA(Accion a) {
		Connection conn = null;
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = 
			conn.prepareStatement("INSERT INTO `accion` (`id_tipo_Accion`,`descripcion`,`nombre`,`id_usualta_acc`,`fecha_alta`,`latitud`,`longitud`) VALUES ( ?, ?, ?, ?, NOW(),?,?)");
			pst.setInt(1, a.getId_tipo_Accion());
			pst.setString(2, a.getDescripcion());
			pst.setString(3, a.getNombre());
			pst.setInt(4, a.getId_usualta_acc());
			pst.setFloat(5, a.getLatitud());
			pst.setFloat(6, a.getLongitud());
			System.out.println(pst);
			pst.executeUpdate();
			conn.commit();
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}

		}	
	}

	
	public static  List<Accion> mostrarAcciones(int idusu) {
		Connection conn = null;
		List<Accion> accs=new ArrayList<Accion>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT accion.*,tipo_accion.nombre FROM accion\r\n" + 
					"INNER JOIN tipo_Accion ON accion.id_tipo_accion=tipo_accion.id_tipo_Accion\r\n" +  
					"WHERE accion.id_usualta_acc=? AND estado<>'Cancelado'");
			pst.setInt(1, idusu);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				Accion acc=new Accion();
				acc.setId_accion(rs.getInt("id_Accion"));
				acc.setNombre(rs.getString("nombre"));
				acc.setFecha_alta(rs.getDate("fecha_alta"));
				acc.setEstado(rs.getString("estado"));
				acc.setId_usualta_acc(rs.getInt("id_usualta_acc"));
				acc.setDescripcion(rs.getString("descripcion"));
				acc.setLatitud(rs.getFloat("latitud"));
				acc.setLongitud(rs.getFloat("longitud"));
					TipoAccion ta = new TipoAccion();
					ta.setNombre(rs.getString("nombre"));
					ta.setIdTipo_accion(rs.getInt("id_tipo_Accion"));
				acc.setTa(ta);
				accs.add(acc);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return accs;
	}
	

		public static boolean eliminarAccion(int id_acc) {
			Connection conn = null;
			boolean resp = false;
			try {
				conn = conexion.getConnection();
				//Insert con parametros para que no hagan SQL Inject
				PreparedStatement pst = conn.prepareStatement("UPDATE `accion` SET `estado`='Cancelado' where `id_accion`=?");				
				pst.setInt(1, id_acc);
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
		
		public static Accion buscarAcc(int idacc) {
			Connection conn = null;
			Accion acc=new Accion();
			try {
				conn = conexion.getConnection();
				PreparedStatement pst = 
				conn.prepareStatement("SELECT * FROM accion WHERE id_accion=?" );
				pst.setInt(1, idacc);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					acc.setId_accion(rs.getInt("id_accion"));
					acc.setNombre(rs.getString("nombre"));
					acc.setId_tipo_Accion(rs.getInt("id_tipo_Accion"));
					acc.setDescripcion(rs.getString("descripcion"));
					acc.setFecha_alta(rs.getDate("fecha_alta"));
					acc.setId_usualta_acc(rs.getInt("id_usualta_acc"));
					acc.setEstado(rs.getString("estado"));
					acc.setLatitud(rs.getFloat("latitud"));
					acc.setLongitud(rs.getFloat("longitud"));
				}
				conn.close();
			} 
			catch (SQLException e) {System.out.println(e.toString());}
			finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
			}
			return acc;
		}
		
		
		public static boolean modificarAcc(Accion acc) {
			Connection conn = null;
			boolean resp = false;
			try {
				conn = conexion.getConnection();
				//Insert con parametros para que no hagan SQL Inject
				PreparedStatement pst = conn.prepareStatement("UPDATE `accion` SET `nombre`=?, `estado`=?, `id_tipo_Accion`=?, `descripcion`=?, `latitud`=?, `longitud`=? WHERE id_accion=?");				
				pst.setString(1, acc.getNombre());
				pst.setString(2, acc.getEstado());
				pst.setInt(3,acc.getId_tipo_Accion());
				pst.setString(4,acc.getDescripcion());
				pst.setFloat(5, acc.getLatitud());
				pst.setFloat(6, acc.getLongitud());
				pst.setInt(7, acc.getId_accion());
				pst.executeUpdate();
				conn.close();
			} 
			catch (SQLException e) {resp=false;System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
			finally {if(conn!=null)	try {resp=false;conn.close();} catch (SQLException e) {System.out.println(e.toString());}
			}
			return resp;	
		}
		
		
		public static  List<Accion> mostrarTodAcciones() {
			Connection conn = null;
			List<Accion> accs=new ArrayList<Accion>();
			try {
				conn = conexion.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pst = 
				conn.prepareStatement("SELECT accion.*,tipo_accion.nombre FROM accion\r\n" + 
						"INNER JOIN tipo_Accion ON accion.id_tipo_accion=tipo_accion.id_tipo_Accion\r\n" +  
						"WHERE estado<>'Cancelado'");
				ResultSet rs=pst.executeQuery();
				while(rs.next())
					{                           
					Accion acc=new Accion();
					acc.setId_accion(rs.getInt("id_Accion"));
					acc.setNombre(rs.getString("nombre"));
					acc.setFecha_alta(rs.getDate("fecha_alta"));
					acc.setEstado(rs.getString("estado"));
					acc.setId_usualta_acc(rs.getInt("id_usualta_acc"));
					acc.setDescripcion(rs.getString("descripcion"));
					acc.setLatitud(rs.getFloat("latitud"));
					acc.setLongitud(rs.getFloat("longitud"));
						TipoAccion ta = new TipoAccion();
						ta.setNombre(rs.getString("nombre"));
						ta.setIdTipo_accion(rs.getInt("id_tipo_Accion"));
					acc.setTa(ta);
					accs.add(acc);
					}
				conn.close();
			} 
			catch (SQLException e) {System.out.println(e.toString());}
			finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
			}
			return accs;
		}
}


	