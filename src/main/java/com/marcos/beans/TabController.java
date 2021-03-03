/**
 * 
 */
package com.marcos.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.marcos.dao.ServicioProducto;
import com.marcos.dto.Producto;

/**
 * @author vicen
 *
 */
@RequestScoped
public class TabController {
	
	private List<Producto> listaTemporal;
	
	@Inject
	private ServicioProducto servicio;
	
	
	@PostConstruct
	public void init () {
		listaTemporal = servicio.listarProductos();
	}
	
	
	
	
	public void cargarEconomicos() {
		
	}
	
	public void cargarRandom() {
		
	}

	/**
	 * @return the listaTemporal
	 */
	public List<Producto> getListaTemporal() {
		return listaTemporal;
	}

	/**
	 * @param listaTemporal the listaTemporal to set
	 */
	public void setListaTemporal(List<Producto> listaTemporal) {
		this.listaTemporal = listaTemporal;
	}

}
