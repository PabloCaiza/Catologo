package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Categoria;

public interface ServicioCategoria {
	
	List<Categoria> listarCategoria();
	void crear(Categoria caterogira);
	Categoria encontrarCategoria(int id);
	List<String> listarTipos();
	List<String> listarGeneros();
}
