package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Comentario;

import com.marcos.dto.Producto;

public interface ServicioComentario {
	
	List<Comentario> listarComentaios(Producto producto);
	void agregarComentario(Comentario comentario);
	

}
