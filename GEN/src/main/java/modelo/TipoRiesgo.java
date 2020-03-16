package modelo;

import java.util.Date;

public class TipoRiesgo {
	private int id_tipor;
	private String nombre;
	private String descripcion;
	private int id_usu_atr;
	private Date fecha_alta_tr;
		
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getId_tipor() {
		return id_tipor;
	}
	public void setId_tipor(int id_tipor) {
		this.id_tipor = id_tipor;
	}
	public Date getFecha_alta_tr() {
		return fecha_alta_tr;
	}
	public void setFecha_alta_tr(Date fecha_alta_tr) {
		this.fecha_alta_tr = fecha_alta_tr;
	}
	public int getId_usu_atr() {
		return id_usu_atr;
	}
	public void setId_usu_atr(int id_usu_atr) {
		this.id_usu_atr = id_usu_atr;
	}
	
}
