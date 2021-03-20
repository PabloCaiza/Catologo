package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.marcos.utils.CommonUtils;

@Named("sessionClosedController")
@ViewScoped
public class SessionClosedController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cerrar="cerra";

	@PostConstruct
	public void init() {
		System.out.println("***********************************");
		System.out.println("cerramos session");
		
	}
	
	public void closeSession() {
		
		System.out.println("se va a cerrar sesion");
		
		try {
			CommonUtils.redireccionarPagina("/login.xhtml");
			ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
			context.invalidateSession();
		}catch(Exception e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "!Ups", "No se pudo cerrar sesion");
		}
		
	}

	public String getCerrar() {
		return cerrar;
	}

	public void setCerrar(String cerrar) {
		this.cerrar = cerrar;
	}
	



	
	
}
