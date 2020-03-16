package modelo;

import java.util.Calendar;
import java.util.Date;

public class TipoAccion {
	private int idTipo_accion;
	private String nombre;
	private String descripcion;
	private int usu_alta_ta;
	private Date fecha_alta_ta;
	

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getUsu_alta_ta() {
		return usu_alta_ta;
	}
	public void setUsu_alta_ta(int usu_alta_ta) {
		this.usu_alta_ta = usu_alta_ta;
	}
	public Date getFecha_alta_ta() {
		return fecha_alta_ta;
	}
	public void setFecha_alta_ta(Date fecha_alta_ta) {
		this.fecha_alta_ta = fecha_alta_ta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdTipo_accion() {
		return idTipo_accion;
	}
	public void setIdTipo_accion(int idTipo_accion) {
		this.idTipo_accion = idTipo_accion;
	}
	
}
