/**
 * 
 */
package com.marcos.dao;

import com.marcos.dto.Imagen;

/**
 * @author c-ado
 *Servicio que contiene los metodos de logica de negocio para manejar las imagenes 
 */
public interface ServicioImagen  {
	/**
	 * Permite agregar una imagen en la bd
	 * @param imagen {@link Imagen} imagen que se guardara
	 */
      void crear(Imagen imagen);
}
