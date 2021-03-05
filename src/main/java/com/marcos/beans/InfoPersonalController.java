package com.marcos.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioPersonaI;
import com.marcos.dto.Persona;
import com.marcos.utils.CommonUtils;

@Named("infoPersonalController")
@ViewScoped
public class InfoPersonalController implements Serializable{

	private static final long serialVersionUID = 1L;
	private Persona persona;
	private String password;
	@Inject
	SessionController session;
	@Inject
	ServicioPersonaI servicioPersona;
	
	@PostConstruct
	public void init() {
		persona=session.getPersona();
		password=persona.getClave();
	}
	
	public void modificarUsuario() {
		if(coicidenClaves()) {
			Persona personaActualizada=servicioPersona.actualizar(persona);
			if(personaActualizada!=null) {
				CommonUtils.mostarMensaje(FacesMessage.SEVERITY_INFO, "OK","Tu informacion ha siddo actualizada");
			}
		}else {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "verifique claves","las claves no coinciden");
		}
		
	}
	
	public boolean coicidenClaves() {
		return this.persona.getClave().equals(password);
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public SessionController getSession() {
		return session;
	}

	public void setSession(SessionController session) {
		this.session = session;
	}

	public ServicioPersonaI getServicioPersona() {
		return servicioPersona;
	}

	public void setServicioPersona(ServicioPersonaI servicioPersona) {
		this.servicioPersona = servicioPersona;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
