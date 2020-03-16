package modelo;

import java.sql.Date;

public class AccionRiesgo {
 private int id_accion;
 private int idriesgo;
 private int idaccrie;
 private String descripcion;
 private int id_usualta;
 private Date fecha_alta;
 private float latitud;
 private float longitud;
 private String color;
 private String nomrie;
 private String nomacc;
 private String tam;
 
	public int getId_accion() {
		return id_accion;
	}
	public void setId_accion(int id_accion) {
		this.id_accion = id_accion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
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

	public int getId_usualta() {
		return id_usualta;
	}
	public void setId_usualta(int id_usualta) {
		this.id_usualta = id_usualta;
	}
	public int getIdriesgo() {
		return idriesgo;
	}
	public void setIdriesgo(int idriesgo) {
		this.idriesgo = idriesgo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getNomrie() {
		return nomrie;
	}
	public void setNomrie(String nomrie) {
		this.nomrie = nomrie;
	}
	public String getNomacc() {
		return nomacc;
	}
	public void setNomacc(String nomacc) {
		this.nomacc = nomacc;
	}
	public String getTam() {
		return tam;
	}
	public void setTam(String tam) {
		this.tam = tam;
	}
	public int getIdaccrie() {
		return idaccrie;
	}
	public void setIdaccrie(int idaccrie) {
		this.idaccrie = idaccrie;
	}
	
}
