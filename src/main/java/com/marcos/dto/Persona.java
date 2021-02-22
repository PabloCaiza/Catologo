package com.marcos.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity

@NamedQuery(name = "Persona.findAll", query = "select E from Persona E")

public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "correo")
	private String correo ;
	@Column(name = "clave")
	private String clave;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRol")
	private Rol rol;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
	

}
