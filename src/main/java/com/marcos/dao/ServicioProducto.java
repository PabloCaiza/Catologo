package com.marcos.dao;

import java.util.List;



import com.marcos.dto.Producto;
import com.marcos.dto.ProductoReportes;

/**
 * Servicio que contiene los metodos de logica de negocio para manejar las
 * productos
 * 
 * @author c-ado
 *
 */
public interface ServicioProducto {
	/**
	 * Metodo que permite listar todos los producto de la bd
	 * 
	 * @return {@link List<Producto> } lista de todos los productos de la bd
	 */
	List<Producto> listarProductos();

	/**
	 * Metodo que permite guardar un producto
	 * 
	 * @param producto {@link Producto} a guardarse
	 */
	void crear(Producto producto);

	/**
	 * Metodo que borrar un producto por su id
	 * 
	 * @param id {@link Integer} del producto a eliminar
	 */
	void elimnar(int id);

	/**
	 * 
	 * @param id
	 * @param producto
	 */
	void modificar(int id, Producto producto);

	/**
	 * 
	 * @param filter
	 * @return
	 */
	List<Producto> queryByNameFilter(String filter);

	/**
	 * 
	 * @param genero
	 * @param tipo
	 * @return
	 */
	List<Producto> queryByCategoria(String genero, String tipo);

	/**
	 * 
	 * @param genero
	 * @return
	 */
	List<Producto> queryByGenero(String genero);

	List<Producto> listarProductosCliente();
	
	List<ProductoReportes> consultar(); 
}
