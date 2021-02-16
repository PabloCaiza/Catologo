package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Producto;

public interface ServicioProducto {
	List<Producto> listarProductos();
	void crear(Producto producto);
	void elimnar(int id);
	void modificar(int id,Producto producto);

}
