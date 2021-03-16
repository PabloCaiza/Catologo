package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.marcos.utils.CommonUtils;

@Named("paginaController")
@RequestScoped
public class PaginaController  implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void redireccionarPagina(String url) {
		System.out.println("entramos al redireccionae");
		try {
			CommonUtils.redireccionarPagina(url);
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "ups !", "no se pudo cargar la  pagina");
		}
	}
	
	

}
