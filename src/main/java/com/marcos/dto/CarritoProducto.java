package com.marcos.dto;

import javax.persistence.*;

@Entity
@Table(name = "carrito_producto")
public class CarritoProducto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carrito_producto")
	private int idCarritoProducto;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_carrito")
	private Carrito carrito;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name = "id_factura")
	private Factura factura;
	
	@Column(name = "estatus")
	private String estatus;
	

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getIdCarritoProducto() {
		return idCarritoProducto;
	}

	public void setIdCarritoProducto(int idCarritoProducto) {
		this.idCarritoProducto = idCarritoProducto;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	
	
	
	
	
	
}
