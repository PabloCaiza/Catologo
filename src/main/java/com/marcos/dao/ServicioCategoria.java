package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Categoria;
import com.marcos.dto.Category;
/**
 * @author c-ado
 *Servicio que contiene los metodos de logica de negocio para manejar las categorias
 */
public interface ServicioCategoria {
	/**
	 * Metodo que permite listar las categorias de productos en la bd
	 * @return {@link List<Categoria>} lista con las categorias almacednads en la bd
	 */
	List<Categoria> listarCategoria();
	/**
	 * Metodo que permite crear una categoria en la bd
	 * @param caterogira {@link Categoria} categoria a almacenarse en la bd
	 */
	void crear(Categoria caterogira);
	/**
	 * Metodo que permite encontar una categoria por su id
	 * @param id {@link Integer} de la categoria a buscar
	 * @return {@link Categoria} categoria buscada
	 */
	Categoria encontrarCategoria(int id);
	/**
	 * Metodo que permite listar los diferentes tipos que existen en las categorias en la bd
	 * @return {@link List<String>} lista de los tipos 
	 */
	List<String> listarTipos();
	/**
	 * Metodo que permite listar los diferentes generos que existen en las categorias en la bd
	 * @return {@link List<String>} lista de los generos 
	 */
	List<String> listarGeneros();
	List<Category> obtainCateogires();
}
