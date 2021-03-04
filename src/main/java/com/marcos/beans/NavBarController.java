package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.marcos.utils.CommonUtils;

@Named("navBarController")
@ViewScoped
public class NavBarController implements Serializable{
	
	

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		System.out.println("hola");
		
	}
	
	public void redireccionar() {
		try {
			CommonUtils.redireccionarPagina("/pages/cliente/carrito.xhtml");
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "UPS ", "no se pudo cargar la pagina");
		}
	}
	
	public void redireccionarProductos() {
		try {
			CommonUtils.redireccionarPagina("/pages/cliente/BuscarProductos.xhtml");
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "UPS ", "no se pudo cargar la pagina");
		}
	}
	
	public void redireccionarHome() {
		try {
			CommonUtils.redireccionarPagina("/pages/commons/dashBoard.xhtml");
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "UPS ", "no se pudo cargar la pagina");
		}
	}

}
