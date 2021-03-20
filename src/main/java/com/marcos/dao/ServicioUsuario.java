package com.marcos.dao;

import com.marcos.dto.Usuario;

public interface ServicioUsuario {
	
	void crear(Usuario usuario);
	void eliminar(int id);
	void modificar(int id,Usuario usuario);
	

}
