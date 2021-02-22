package com.marcos.beans;


import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

import com.marcos.dto.Persona;


@Named("sessionController")
@SessionScoped
public class SessionController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Persona persona;
	
	
	@PostConstruct
	public void init() {
		System.out.println("***********************************");
		System.out.println("ingresamos a una nueva sesion");
		
	}

	public Persona getPersona() {
		
		return persona;
	}

	public void setPersona(Persona persona) {
		
		this.persona = persona;
		System.out.println(persona.getNombre());
	}
	
	public void imprimir() {
		System.out.println("Nombre de la persona:  "+persona.getNombre());
	}
	
	
	

}
