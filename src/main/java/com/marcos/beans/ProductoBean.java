package com.marcos.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioCategoria;
import com.marcos.dao.ServicioProducto;
import com.marcos.dto.Categoria;
import com.marcos.dto.Producto;

@Named("productoB")
@RequestScoped
public class ProductoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Producto producto;
	private List<Producto> productos;
	private String categoria;
	private String filtroPorNombre;
	@Inject
	private ServicioProducto servicio;
	@Inject
	private ServicioCategoria servicioC;

	@PostConstruct
	public void init() {
		productos = servicio.listarProductos();
		producto = new Producto();

	}

	public void crearProducto() {
		try {
			servicio.crear(producto);

		} catch (Exception e) {

		} finally {
			producto = new Producto();
			productos = servicio.listarProductos();
		}
	}

	public void imprimir() {
		for (int i = 0; i < productos.size(); i++) {
			System.out.println(productos.get(i).getNombre());
		}
	}

	public void eliminarProducto(int id) {
		try {
			servicio.elimnar(id);
		} catch (Exception e) {

		}

	}

	public void modificarProducto(Producto prodcuto, int id) {
		try {
			servicio.modificar(id, prodcuto);
		} catch (Exception e) {

		}

	}

	public void consultarPorFiltro() {

		if (filtroPorNombre.length() == 0) {
			productos = servicio.listarProductos();
		} else {
			productos = servicio.queryByNameFilter(filtroPorNombre);
			productos.forEach(producto -> {
				System.out.println(producto.getNombre());

			});
		}

	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
		Categoria categoriaSelecioanda = this.servicioC.encontrarCategoria(Integer.parseInt(categoria));
		System.out.println("***********************************************************");
		System.out.println(categoriaSelecioanda.getTipo());
		System.out.println("***********************************************************");
		this.producto.setCategoria(categoriaSelecioanda);

	}

	public String getFiltroPorNombre() {
		return filtroPorNombre;
	}

	public void setFiltroPorNombre(String filtroPorNombre) {
		this.filtroPorNombre = filtroPorNombre;
	}

}
