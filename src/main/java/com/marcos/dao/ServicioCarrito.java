package com.marcos.dao;

import java.util.List;

import com.marcos.dto.CarritoProducto;
import com.marcos.dto.Factura;

public interface ServicioCarrito {
	
	void agregarProductoCarrito(CarritoProducto item);
	
	double calcularTotal(List<CarritoProducto> items);
	void eliminarCarritoProducto(CarritoProducto item);
	void actualizarCantidadProducto(CarritoProducto item);
	boolean actualizarCarritoProducto(List<CarritoProducto> items,Factura factura);
	

}
