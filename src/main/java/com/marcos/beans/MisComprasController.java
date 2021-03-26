package com.marcos.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioFactura;
import com.marcos.dto.CarritoProducto;
import com.marcos.dto.Factura;
/**
 * Clase que controla el flujo de datos de la pantallas miscompras.xhtml
 * @author c-ado
 *
 */
@Named("misComprasController")
@ViewScoped
public class MisComprasController implements Serializable{
	
	
	@Inject
	ServicioFactura servicioFactura;
	@Inject
	SessionController session;

	private static final long serialVersionUID = 1L;
	/**
	 * Lista de facturas de la persona en sesion
	 */
	private List<Factura>  facturas;
	/**
	 * Lista de los productos de la compra que desea revisar el cliente
	 */
	private List<CarritoProducto> carritosProductos;
	
	@PostConstruct
	public void init() {
		facturas=servicioFactura.findAllByPersona(session.getPersona());
		
	
	}
	
	public void mostarDetalle(List<CarritoProducto> carritosProducto) {
		this.carritosProductos=carritosProducto;

		
	}

	public ServicioFactura getServicioFactura() {
		return servicioFactura;
	}

	public void setServicioFactura(ServicioFactura servicioFactura) {
		this.servicioFactura = servicioFactura;
	}

	public SessionController getSession() {
		return session;
	}

	public void setSession(SessionController session) {
		this.session = session;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public List<CarritoProducto> getCarritosProductos() {
		return carritosProductos;
	}

	public void setCarritosProductos(List<CarritoProducto> carritosProductos) {
		this.carritosProductos = carritosProductos;
	}
	
	
	
	
	

}
