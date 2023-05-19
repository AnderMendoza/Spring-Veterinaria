package com.certus.spring.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "mascota")
public class Mascota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMascota;
	
	@NotEmpty(message = "Completar el nombre de la mascota")
	private String nombres;
	
	@NotEmpty(message = "Completar la raza de la mascota")
	private String raza;
	
	private String edad;
	private String dueño;
	private String caso;
	
	@NotEmpty(message = "Indicar la fecha de ingreso")
	private String ingreso;
	
	
	private String uriImagen;
	
		
	
	public int getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}
	
	public String getUriImagen() {
		return uriImagen;
	}
	public void setUriImagen(String uriImagen) {
		this.uriImagen = uriImagen;
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
	
	

}
