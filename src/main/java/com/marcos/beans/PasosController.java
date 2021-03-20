package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;

import com.marcos.utils.CommonUtils;



@Named("pasosController")
@ViewScoped
public class PasosController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(PasosController.class);
	@Inject
	private SessionController session;
	@PostConstruct
	public void init() {
		LOGGER.info("se ejeucuto pasos contoller");
	}
	
	
	public void cambiarPaso(String url,int paso) {
		try {
			session.setPaso(paso);
			CommonUtils.redireccionarPagina(url);
			
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "ups", "no se pudo avanzar el paso");
		}
		
	}


	public SessionController getSession() {
		return session;
	}


	public void setSession(SessionController session) {
		this.session = session;
	}
	
	

}
