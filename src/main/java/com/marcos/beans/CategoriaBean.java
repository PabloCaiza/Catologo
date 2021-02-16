package com.marcos.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioCategoria;
import com.marcos.dto.Categoria;

@Named("categoriaB")
@RequestScoped
public class CategoriaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Categoria categoria;
	private List<Categoria> categorias;
	@Inject
	private ServicioCategoria servicio;
	
	@PostConstruct
	public void init() {
		categoria=new Categoria();
		categorias=servicio.listarCategoria();
			
	}
	
	public void crear() {
		try {
			servicio.crear(categoria);
		}catch(Exception e) {
			categoria=new Categoria();
			categorias=servicio.listarCategoria();
		}
	}
	

}
