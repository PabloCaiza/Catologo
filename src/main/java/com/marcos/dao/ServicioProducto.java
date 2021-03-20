package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Imagen;
import com.marcos.dto.Producto;

public interface ServicioProducto {
	List<Producto> listarProductos();
	void crear(Producto producto);
	void elimnar(int id);
	void modificar(int id,Producto producto);
	List<Producto> queryByNameFilter(String filter);
	List<Producto> queryByCategoria(String genero,String tipo);
	List<Producto> queryByGenero(String genero);
	void crearImagen(Imagen image);

}
