package com.marcos.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.NamedQuery;

@Entity

@NamedQuery(name = "Persona.findAll", query = "select E from Persona E")

public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	
	@Column(name = "pers_id")
	private int persId;
	@Column(name = "pers_nombre")
	private String persNombre;
	@Column(name = "pers_edad")
	private int persEdad;

	public int getPersId() {
		return persId;
	}

	public void setPersId(int persId) {
		this.persId = persId;
	}

	public String getPersNombre() {
		return persNombre;
	}

	public void setPersNombre(String persNombre) {
		this.persNombre = persNombre;
	}

	public int getPersEdad() {
		return persEdad;
	}

	public void setPersEdad(int persEdad) {
		this.persEdad = persEdad;
	}
	

}
