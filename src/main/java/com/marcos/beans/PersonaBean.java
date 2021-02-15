package com.marcos.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioPersonaI;
import com.marcos.dto.Persona;

@Named("personaB")
@RequestScoped
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Persona> personas;
	private Persona persona;


	@Inject
	private ServicioPersonaI servicio;

	@PostConstruct
	public void init() {
		personas = servicio.listar();
		persona = new Persona();
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public void crearPersona() {
		try {
			servicio.crear(persona);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			persona = new Persona();
			personas = servicio.listar();
		}

	}
}