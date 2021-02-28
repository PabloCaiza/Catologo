package com.marcos.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioCarrito;
import com.marcos.dao.ServicioProducto;
import com.marcos.dto.Carrito;
import com.marcos.dto.CarritoProducto;
import com.marcos.dto.Producto;

@Named("detalleController")
@ViewScoped
public class DetalleController implements Serializable {

	private static final long serialVersionUID = 1L;


	private int cantidadProductoSelecionado;
	
	@Inject 
	private ServicioCarrito servicioCarrito;
	
	@Inject
	private SessionController session;
	
	@PostConstruct
	public void init() {
		this.cantidadProductoSelecionado=1;
	}
	
	
	public void agregarProductoCarrito(Producto producto) {
		CarritoProducto carritoProducto=new CarritoProducto();
		
		
		carritoProducto.setCarrito(session.getPersona().getCarrito());
		carritoProducto.setCantidad(cantidadProductoSelecionado);
		carritoProducto.setEstatus("PENDIENTE");
		carritoProducto.setProducto(producto);
		
		
		servicioCarrito.agregarProductoCarrito(carritoProducto);
		session.getPersona().getCarrito().getCarritosProducto().add(carritoProducto);

	}

	public int getCantidadProductoSelecionado() {
		return cantidadProductoSelecionado;
	}


	public void setCantidadProductoSelecionado(int cantidadProductoSelecionado) {
		this.cantidadProductoSelecionado = cantidadProductoSelecionado;
	}
	
	
}
