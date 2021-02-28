package com.marcos.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioCarrito;
import com.marcos.dto.CarritoProducto;

@Named("carritoController")
@ViewScoped
public class CarritoController implements Serializable {


	private static final long serialVersionUID = 1L;
	@Inject
	private SessionController session;
	@Inject
	private ServicioCarrito servicioCarrito;

	@PostConstruct
	public void init() {
		calcularTotal();
	}

	public void calcularTotal() {

		session.setTotalCompra(servicioCarrito.calcularTotal(session.getPersona().getCarrito().getCarritosProducto()));

	}
	
	public void eliminarItemCarrito(CarritoProducto item) {
		servicioCarrito.eliminarCarritoProducto(item);
		session.getPersona().getCarrito().getCarritosProducto().remove(item);
		this.calcularTotal();
	}

	public SessionController getSession() {
		return session;
	}

	public void setSession(SessionController session) {
		this.session = session;
	}
	
	public void actualizarCantidadCarrito(CarritoProducto item) {
		servicioCarrito.actualizarCantidadProducto(item);
		calcularTotal();
	}



}
