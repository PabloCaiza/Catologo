package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Persona;

/**
 * 
 * @author c-ado
 * 
 *         Servicio que contiene los metodos de logica de negocio para manejar
 *         las personas
 */
public interface ServicioPersonaI {
	/**
	 * Metodo que lista las persona de la bd
	 * 
	 * @return {@link List<Persona>} lista de persona en la BD
	 */
	List<Persona> listar();

	/**
	 * Metodo que permite crear una persona en la bd
	 * 
	 * @param persona {@link Persona} a crearse
	 */
	void crear(Persona persona);

	/**
	 * Metodo que permite actualizar los datos de una persona en la bd
	 * 
	 * @param persona {@link Persona} persona a la se le actualizar en la bd
	 * @return {@link Persona} Persona con los datos actualizados
	 */
	Persona actualizar(Persona persona);

	/**
	 * Metodo que permite borrar un usuario por su id
	 * 
	 * @param id {@link Integer} del usuario a borrar
	 */
	void eliminar(int id);

}
