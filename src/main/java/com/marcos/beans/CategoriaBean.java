package com.marcos.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioCategoria;
import com.marcos.dto.Categoria;

@Named("categoriaB")
@RequestScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Categoria categoria;
	private Categoria categoriaSeleccionada;
	private List<Categoria> categorias;
	private List<String> tipos;
	@Inject
	private ServicioCategoria servicio;

	@PostConstruct
	public void init() {
		categoria = new Categoria();
		categorias = servicio.listarCategoria();
	
	}

	public void crear() {
		try {
			servicio.crear(categoria);
		} catch (Exception e) {
			categoria = new Categoria();
			categorias = servicio.listarCategoria();
		}
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the categorias
	 */
	public List<Categoria> getCategorias() {
		return categorias;
	}

	/**
	 * @param categorias the categorias to set
	 */
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	/**
	 * @return the categoriaSeleccionada
	 */
	public Categoria getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	/**
	 * @param categoriaSeleccionada the categoriaSeleccionada to set
	 */
	public void setCategoriaSeleccionada(Categoria categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

}
