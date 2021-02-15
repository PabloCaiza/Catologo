package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Persona;

public interface ServicioPersonaI {

	List<Persona> listar();

	void crear(Persona persona);

	void actualizar(int id, Persona persona);

	void eliminar(int id);

}
