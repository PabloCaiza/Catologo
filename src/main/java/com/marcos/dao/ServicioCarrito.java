package com.marcos.dao;

import java.util.List;

import com.marcos.dto.CarritoProducto;

public interface ServicioCarrito {
	
	void agregarProductoCarrito(CarritoProducto item);
	
	double calcularTotal(List<CarritoProducto> items);
	void eliminarCarritoProducto(CarritoProducto item);
	void actualizarCantidadProducto(CarritoProducto item);
	

}
