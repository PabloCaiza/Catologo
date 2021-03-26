/**
 * 
 */
package com.marcos.dao;

import com.marcos.dto.Imagen;

/**
 * Servicio que contiene los metodos de logica de negocio para manejar las
 * imagenes
 * 
 * @author c-ado
 *
 */
public interface ServicioImagen {
	/**
	 * Permite agregar una imagen en la bd
	 * 
	 * @param imagen {@link Imagen} imagen que se guardara
	 */
	void crear(Imagen imagen);
}
