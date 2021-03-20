package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Persona;

public interface ServicioPersonaI {

	List<Persona> listar();

	void crear(Persona persona);

	Persona actualizar(Persona persona);

	void eliminar(int id);

}
