package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;

import com.marcos.dao.ServicioCarrito;
import com.marcos.dao.ServicioCategoria;
import com.marcos.dao.ServicioProducto;
import com.marcos.dto.CarritoProducto;
import com.marcos.dto.Categoria;
import com.marcos.dto.Producto;
import com.marcos.dto.ProductoReportes;
import com.marcos.utils.CommonUtils;

/**
 * Clase que controla el flujo de los productos en las pantallas
 * 
 * @author c-ado
 *
 */
@Named("productoB")
@ViewScoped
public class ProductoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ProductoBean.class);
	/**
	 * Producto seleccionado por el usuario
	 */
	private Producto producto;
	/**
	 * Lista de productos
	 */
	private List<Producto> productos;
    
	
	private List<ProductoReportes> productosReportes;
	
	private List<ProductoReportes> productosReportados;
	
	/**
	 * Categoria elegida por el usuario
	 */
	private String categoria;
	/**
	 * Filtro de busqueda en base al nombre
	 */
	private String filtroPorNombre;
	/**
	 * Forma de ordenar los productos elegida por el usuario
	 */
	private String opcionOrdenado;
	@Inject
	private SessionController sessionController;
	@Inject
	private ServicioProducto servicio;
	@Inject
	private ServicioCategoria servicioC;
	@Inject
	private ServicioCarrito servicioCarrito;

	@PostConstruct
	public void init() {
        
		if (sessionController.getPersona().getRol().getIdRol() == 1) {
			this.productosReportes=this.servicio.consultar();
			this.productosReportados=this.servicio.consultarGenerosCompras();
	        Collections.sort(this.productosReportes);
	        Collections.reverse(this.productosReportes);
			productos = servicio.listarProductos();
		} else {
			productos = servicio.listarProductosCliente();
		}

		producto = new Producto();

	}

	/**
	 * Metodo que sirve para mostrar el detalle de un producto
	 * 
	 * @param producto {@link Producto} producto elegido
	 */
	public void showDetail(Producto producto) {
		LOGGER.info(producto.getNombre());

		sessionController.setSelectProduct(producto);
		try {
			CommonUtils.redireccionarPagina("/pages/cliente/detalle.xhtml");
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!", "no se pudo ingresar");
		}

	}

	/**
	 * Metodo que sirve para agregar un producto al carrito
	 * 
	 * @param product
	 */
	public void agregarProducto(Producto product) {
		CarritoProducto carritoProducto = new CarritoProducto();
		carritoProducto.setCarrito(sessionController.getPersona().getCarrito());
		carritoProducto.setCantidad(1);
		carritoProducto.setEstatus("PENDIENTE");
		carritoProducto.setProducto(product);
		this.servicioCarrito.agregarProductoCarrito(carritoProducto);
		this.sessionController.getPersona().getCarrito().getCarritosProducto().add(carritoProducto);
	}

	/**
	 * Metod que sirve para crear un producto en el catalogo
	 */
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
/**
 * Metodo que sirve para consultar un producto por un filtro
 */
	public void consultarPorFiltro() {

		if (filtroPorNombre.length() == 0) {
			productos = servicio.listarProductosCliente();
		} else {

			productos = servicio.queryByNameFilter(filtroPorNombre.toLowerCase());
		}

	}
/**
 * Metodo que sirve para consulta los productos en base a una categoria
 * @param genero {@link}
 * @param tipo
 */
	public void consultarPorCategoria(String genero, String tipo) {
		productos = servicio.queryByCategoria(genero, tipo);

	}

	public void consultarPorGenero(String genero) {
		productos = servicio.queryByGenero(genero);
	}

	public void ordenar() {
		if (opcionOrdenado.equals("1")) {
			Collections.sort(productos);
		} else if (opcionOrdenado.equals("2")) {
			Collections.sort(productos);
			Collections.reverse(productos);
		} else if (opcionOrdenado.equals("3")) {
			Collections.sort(productos, new Producto().new precioComparador());

		} else if (opcionOrdenado.equals("4")) {
			Collections.sort(productos, new Producto().new precioComparador());
			Collections.reverse(productos);
		} else if (opcionOrdenado.equals("5")) {
			Collections.sort(productos, new Producto().new fechaComparador());
			Collections.reverse(productos);
		} else if (opcionOrdenado.equals("6")) {
			Collections.sort(productos, new Producto().new fechaComparador());
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

	/**
	 * @return the opcionOrdenado
	 */
	public String getOpcionOrdenado() {
		return opcionOrdenado;
	}

	/**
	 * @param opcionOrdenado the opcionOrdenado to set
	 */
	public void setOpcionOrdenado(String opcionOrdenado) {
		this.opcionOrdenado = opcionOrdenado;
	}

	/**
	 * @return the productosReportes
	 */
	public List<ProductoReportes> getProductosReportes() {
		return productosReportes;
	}

	/**
	 * @param productosReportes the productosReportes to set
	 */
	public void setProductosReportes(List<ProductoReportes> productosReportes) {
		this.productosReportes = productosReportes;
	}

	/**
	 * @return the productosReportados
	 */
	public List<ProductoReportes> getProductosReportados() {
		return productosReportados;
	}

	/**
	 * @param productosReportados the productosReportados to set
	 */
	public void setProductosReportados(List<ProductoReportes> productosReportados) {
		this.productosReportados = productosReportados;
	}

	/**
	 * @return the productosReportes
	 */


}
