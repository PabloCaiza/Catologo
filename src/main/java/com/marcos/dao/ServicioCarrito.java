package com.marcos.dao;

import java.util.List;

import com.marcos.dto.CarritoProducto;
import com.marcos.dto.Factura;
/**
 * 
 * @author c-ado
 *Clase que contiene los metodos de la logica de negocio para manejar los productos del carrito
 */
public interface ServicioCarrito {
	/**
	 * Metodo que sirve para agregar un producto al carrito en la bd
	 * @param item {@link CarritoProducto} objeto a agregarse en el carrito
	 */
	void agregarProductoCarrito(CarritoProducto item);
	/**
	 * Metodo que calcula el total de la compra en el carrito del cliente
	 * @param items {@link List<CarritoProdcuto>} lista de productos que esta en el carrito
	 * @return
	 */
	double calcularTotal(List<CarritoProducto> items);
	/**
	 * Metodo que sirve para elimnar un producto del carrito en la bd
	 * @param item {@link CarritoProducto} producto a eliminarse
	 */
	void eliminarCarritoProducto(CarritoProducto item);
	/**
	 * Metodo que sirve para cambiar la cantidad de un producto en el carrito en la bd
	 * @param item {@link CarritoProducto} producto a cambiar la cantidad 
	 */
	void actualizarCantidadProducto(CarritoProducto item);
	/**
	 * Metodo que sirve para refrescar los productos del carrito despeus de ser pagados  en la bd
	 * @param items {@link List<CarritoProducto>} lista de los producto del carrito a refrescar
	 * @param factura {@link Factura} factura asociada a la la compra 
	 * @return
	 */
	boolean actualizarCarritoProducto(List<CarritoProducto> items,Factura factura);
	

}
