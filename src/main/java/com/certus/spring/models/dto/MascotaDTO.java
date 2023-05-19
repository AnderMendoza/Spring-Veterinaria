package com.certus.spring.models.dto;


public class MascotaDTO {
	
	private int idMascota;
	private String nombres;
	private String raza;	
	private String edad;
	private String dueño;
	private String caso;
	private String ingreso;
	private String uriImagen;
	private String nombreFileExtension;
	private String fileBase64;

	
	public int getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getDueño() {
		return dueño;
	}
	public void setDueño(String dueño) {
		this.dueño = dueño;
	}
	public String getCaso() {
		return caso;
	}
	public void setCaso(String caso) {
		this.caso = caso;
	}
	public String getIngreso() {
		return ingreso;
	}
	public void setIngreso(String ingreso) {
		this.ingreso = ingreso;
	}
	public String getUriImagen() {
		return uriImagen;
	}
	public void setUriImagen(String uriImagen) {
		this.uriImagen = uriImagen;
	}
	public String getNombreFileExtension() {
		return nombreFileExtension;
	}
	public void setNombreFileExtension(String nombreFileExtension) {
		this.nombreFileExtension = nombreFileExtension;
	}
	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}
	
	
	
	

}
