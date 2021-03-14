/**
 * 
 */
package com.marcos.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioCategoria;
import com.marcos.dao.ServicioProducto;
import com.marcos.dto.Categoria;
import com.marcos.dto.Producto;

/**
 * @author c-ado
 *
 */
@Named("registroProductoController")
@ViewScoped
public class RegistroProductoController implements Serializable {
	private static final long serialVersionUID = 1L;
	private Producto producto;
	private String tipo;
	private List<String> tipos;
	private String genero;
	private List<String> generos;
	List<Categoria> categorias ;
	/**
	 * @return the producto
	 */
	@Inject
 ServicioCategoria servicioCategoria;
	@Inject
 ServicioProducto servicioProducto;

	@PostConstruct
	public void init(){
		producto = new Producto();
		categorias = this.servicioCategoria.listarCategoria();
		this.listarTipos();
		this.listarGeneros();
	}
	public void identificaCategoria() {


		for (Categoria aux : categorias) {
			if (aux.getTipo().equals(tipo) && aux.getGenero().equals(genero)) {
				this.producto.setCategoria(aux);
			}
		}

	}
    
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void crearProducto() {
		System.out.println(producto);
		identificaCategoria();
		try {
			servicioProducto.crear(producto);

		} catch (Exception e) {

		} finally {
			producto = new Producto();

		}

	}
	
	public void actualizarProducto() {
		
		try {
			
			servicioProducto.modificar(this.producto.getId(), this.producto);
			System.out.println("Ent");

		} catch (Exception e) {
			System.out.println("E");
		}
	}
	
	public void cargarProducto(Producto pro) {
		this.producto = pro;
		System.out.println("Entroooooooooooooo");

	}
	
	public void listarTipos(){
		tipos= this.servicioCategoria.listarTipos();
	}
	public void listarGeneros(){
		generos= this.servicioCategoria.listarGeneros();
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
	 * @return the tipos
	 */
	public List<String> getTipos() {
		return tipos;
	}
	/**
	 * @param tipos the tipos to set
	 */
	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}
	/**
	 * @return the generos
	 */
	public List<String> getGeneros() {
		return generos;
	}
	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}
}
