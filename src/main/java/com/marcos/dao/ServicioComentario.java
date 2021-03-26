package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Comentario;

import com.marcos.dto.Producto;
/**
 * Servicio que contiene los metodos de logica de negocio para manejar los comentarios
 * @author c-ado
 *
 */
public interface ServicioComentario {
	/**
	 * Metodpo que sirve para listar los comentarios de un producto en la bd
	 * @param producto {@link Producto} producto al que se desar buscar sus comentarios
	 * @return {@link List<Comentario>} Comentarios del producto deseado
	 */
	List<Comentario> listarComentaios(Producto producto);
	/**
	 * Metodo que permite agregar un comentario a la bd
	 * @param comentario {@link Comentario} que se desea agregar en la bd
	 */
	void agregarComentario(Comentario comentario);
	

}
