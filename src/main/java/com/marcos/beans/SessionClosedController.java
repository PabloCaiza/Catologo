package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("sessionClosedController")
@RequestScoped
public class SessionClosedController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@PostConstruct
	public void init() {
		System.out.println("***********************************");
		System.out.println("cerramos session");
		
	}
	
	public void closeSession() {
		
		ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
		context.invalidateSession();
		
		try {
			redireccionar("login.xhtml");
		}catch(Exception e) {
			
		}
		
	}
	
	public void redireccionar(String pagina) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
	}


	
	
}
