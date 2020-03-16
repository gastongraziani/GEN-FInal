package modelo;

import java.sql.Date;

public class Accion {
 private int id_accion;
 private String nombre;
 private int id_tipo_Accion;
 private String descripcion;
 private int id_usualta_acc;
 private Date fecha_alta;
 private String estado;
 private TipoAccion ta;
 private float latitud;
 private float longitud;
 
	public int getId_accion() {
		return id_accion;
	}
	public void setId_accion(int id_accion) {
		this.id_accion = id_accion;
	}
	public int getId_tipo_Accion() {
		return id_tipo_Accion;
	}
	public void setId_tipo_Accion(int id_tipo_Accion) {
		this.id_tipo_Accion = id_tipo_Accion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId_usualta_acc() {
		return id_usualta_acc;
	}
	public void setId_usualta_acc(int id_usualta_acc) {
		this.id_usualta_acc = id_usualta_acc;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public TipoAccion getTa() {
		return ta;
	}
	public void setTa(TipoAccion ta) {
		this.ta = ta;
	}
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	
}
