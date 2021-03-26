package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.marcos.utils.CommonUtils;
/**
 * Clase que controla el flujo de datos de la pantalla navbar.xhtml
 * @author c-ado
 *
 */
@Named("navBarController")
@ViewScoped
public class NavBarController implements Serializable{
	
	

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		System.out.println("hola");
		
	}
	/**
	 * Metodo que nos sirve para redireccionar a la pantalla carrito.xhtml
	 */
	public void redireccionar() {
		try {
			CommonUtils.redireccionarPagina("/pages/cliente/carrito.xhtml");
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "UPS ", "no se pudo cargar la pagina");
		}
	}
	/**
	 * Metodo que nos sirve para redireccionar a la pantalla BuscarProductos.xhtml
	 */
	public void redireccionarProductos() {
		try {
			CommonUtils.redireccionarPagina("/pages/cliente/BuscarProductos.xhtml");
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "UPS ", "no se pudo cargar la pagina");
		}
	}
	/**
	 * Metodo que nos sirve para redireccionar a la pantalla dashBoard.xhtml
	 */
	public void redireccionarHome() {
		try {
			CommonUtils.redireccionarPagina("/pages/commons/dashBoard.xhtml");
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "UPS ", "no se pudo cargar la pagina");
		}
	}
	/**
	 *  Metodo que nos sirve para redireccionar a la pantalla infopersonal.xhtml
	 */
	public void redireccionarUser() {
		try {
			CommonUtils.redireccionarPagina("/pages/cliente/infopersonal.xhtml");
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "UPS ", "no se pudo cargar la pagina");
		}
	}

}
