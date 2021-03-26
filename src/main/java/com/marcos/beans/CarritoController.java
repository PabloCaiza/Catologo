package com.marcos.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioCarrito;
import com.marcos.dto.CarritoProducto;
/**
 * Clase que controla el flujo de datos del carrito de compras
 * @author c-ado
 *
 */
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
		this.session.setPaso(0);
	}

	/**
	 * Metodo que nos permite calcular el total de la compra que este actualmente en
	 * el carrito del cliente
	 */
	public void calcularTotal() {

		session.setTotalCompra(servicioCarrito.calcularTotal(session.getPersona().getCarrito().getCarritosProducto()));

	}

	/**
	 * Metodo que nos permite eliminar un item del carrito del cliente
	 * 
	 * @param item {@link CarritoProducto} objeto a eliminar del carrito
	 */
	public void eliminarItemCarrito(CarritoProducto item) {
		servicioCarrito.eliminarCarritoProducto(item);
		session.getPersona().getCarrito().getCarritosProducto().remove(item);
		this.calcularTotal();
	}

	/**
	 * Metodo que nos permite cambiar la cantidad de unidades de un producto en el
	 * carrito
	 * 
	 * @param item {@link CarritoProducto} objeto a cambiar su cantidad en el
	 *             carrito
	 */
	public void actualizarCantidadCarrito(CarritoProducto item) {
		servicioCarrito.actualizarCantidadProducto(item);
		calcularTotal();
	}

	public SessionController getSession() {
		return session;
	}

	public void setSession(SessionController session) {
		this.session = session;
	}

}
