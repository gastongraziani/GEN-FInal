package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import datos.conexion;
import java.sql.ResultSet;

import modelo.Accion;
import modelo.TipoAccion;
import modelo.TipoRiesgo;
import modelo.Usuario;

public class usuarioDatos {
	public static boolean validarUsuario(Usuario usu) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
		    //Asi se hace una consulta para que no metan SQL inject
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM usuarios WHERE BINARY usuario=? and  password=? and habilitado=0");
			pst.setString(1, usu.getUsuario()); 
			pst.setString(2, usu.getClave());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				resp = true;
				usu.setNombre(rs.getString("nombre"));
				usu.setApellido(rs.getString("apellido"));
				usu.setEmail(rs.getString("email"));
				usu.setUsuario(rs.getString("usuario"));
				usu.setClave(rs.getString("password"));
				usu.setIdusuario(rs.getInt("id_usuario"));
				usu.setHabilitado(rs.getBoolean("habilitado"));
				usu.setTipoUsuario(rs.getInt("tipo_usuario"));
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
	
	public static void nuevoUsu(Usuario usu) {
		Connection conn = null;
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			//Tipo_usuario 1 = usuario común, Tipo_usuario=0 Admin
			PreparedStatement pst = 
			conn.prepareStatement("INSERT INTO `usuarios` (`nombre`, `apellido`,`email`,`usuario`, `password`,`habilitado`,`tipo_usuario`) VALUES ( ?, ?, ?,?,?,?,?)");
			pst.setString(1, usu.getNombre());
			pst.setString(2, usu.getApellido());
			pst.setString(3, usu.getEmail());
			pst.setString(4, usu.getUsuario());
			pst.setString(5, usu.getClave());
			pst.setBoolean(6, true);
			pst.setInt(7, usu.getTipoUsuario());
			pst.executeUpdate();
			conn.commit();
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}

		}	
	}	
	
	public static  List<Usuario> mostrarInhab() {
		Connection conn = null;
		List<Usuario> usus=new ArrayList<Usuario>();
		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * FROM usuarios WHERE tipo_usuario<>0");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
				{                           
				Usuario usu=new Usuario();
				usu.setNombre(rs.getString("nombre"));
				usu.setApellido(rs.getString("apellido"));
				usu.setEmail(rs.getString("email"));
				usu.setUsuario(rs.getString("usuario"));
				usu.setClave(rs.getString("password"));
				usu.setIdusuario(rs.getInt("id_usuario"));
				usu.setHabilitado(rs.getBoolean("habilitado"));
				usu.setTipoUsuario(rs.getInt("tipo_usuario"));
				usus.add(usu);
				}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return usus;
	}
	
	public static boolean habilitarUsu(int idusu) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
		    //HABILITADO = 0
			PreparedStatement pst = conn.prepareStatement("UPDATE `usuarios` SET `habilitado`=0 where `id_usuario`=?");
			pst.setInt(1, idusu); 
			pst.executeUpdate();
			resp = true;
			conn.close();
		} catch (SQLException e) {resp=false;
			System.out.println(e.toString());
		} finally {resp=false;
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
		}
		return resp;
	}
	
	public static boolean deshabilitarUsu(int idusu) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
		    //DESHABILITADO = 1
			PreparedStatement pst = conn.prepareStatement("UPDATE `usuarios` SET `habilitado`=1 where `id_usuario`=?");
			pst.setInt(1, idusu);
			pst.executeUpdate();
			resp = true;
			conn.close();
		} catch (SQLException e) {resp=false;
			System.out.println(e.toString());
		} finally {resp=false;
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
		}
		return resp;
	}
	
	public static Usuario buscarUsu(int idusu) {
		Connection conn = null;
		Usuario usu=new Usuario();
		try {
			conn = conexion.getConnection();
			PreparedStatement pst = 
			conn.prepareStatement("SELECT * FROM usuarios WHERE id_usuario=?" );
			pst.setInt(1, idusu);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				usu.setIdusuario(rs.getInt("id_usuario"));
				usu.setNombre(rs.getString("nombre"));
				usu.setApellido(rs.getString("apellido"));
				usu.setEmail(rs.getString("email"));
				usu.setUsuario(rs.getString("usuario"));
				usu.setImagen(rs.getString("imagen"));
			}
			conn.close();
		} 
		catch (SQLException e) {System.out.println(e.toString());}
		finally {if(conn!=null)	try {conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return usu;
	}
	

	public static boolean modificarUsu(Usuario usu) {
		Connection conn = null;
		boolean resp = false;
		try {
			conn = conexion.getConnection();
			//Insert con parametros para que no hagan SQL Inject
			PreparedStatement pst = conn.prepareStatement("UPDATE `usuarios` SET `nombre`=?, `apellido`=?, `usuario`=?, `email`=?, `imagen`=? WHERE id_usuario=?");				
			pst.setString(1, usu.getNombre());
			pst.setString(2, usu.getApellido());
			pst.setString(3,usu.getUsuario());
			pst.setString(4,usu.getEmail());
			pst.setString(5, usu.getImagen());
			pst.setInt(6, usu.getIdusuario());
			pst.executeUpdate();
			conn.close();
		} 
		catch (SQLException e) {resp=false;System.out.println(e.toString());try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();	}}
		finally {if(conn!=null)	try {resp=false;conn.close();} catch (SQLException e) {System.out.println(e.toString());}
		}
		return resp;	
	}
	
	
}
