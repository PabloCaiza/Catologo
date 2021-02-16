package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Categoria;

public interface ServicioCategoria {
	
	List<Categoria> listarCategoria();
	void crear(Categoria caterogira);

}
