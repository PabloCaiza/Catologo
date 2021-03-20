package com.marcos.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioCategoria;
import com.marcos.dto.Category;


@Named("categoriaController")
@ViewScoped
public class CategoriaController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServicioCategoria servicioCategoria;
	
	private List<Category> categories;
	
	@PostConstruct
	public void init() {
		categories=this.servicioCategoria.obtainCateogires();
	}

	public ServicioCategoria getServicioCategoria() {
		return servicioCategoria;
	}

	public void setServicioCategoria(ServicioCategoria servicioCategoria) {
		this.servicioCategoria = servicioCategoria;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	

}
