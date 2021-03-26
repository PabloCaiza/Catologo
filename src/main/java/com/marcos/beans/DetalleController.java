package com.marcos.beans;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioCarrito;
import com.marcos.dao.ServicioComentario;
import com.marcos.dto.CarritoProducto;
import com.marcos.dto.Comentario;
import com.marcos.dto.Imagen;
import com.marcos.dto.Producto;

/**
 * Clase que controla el flujo de datos de la pantalla detalle.xhtml
 * 
 * @author c-ado
 */
@Named("detalleController")
@ViewScoped
public class DetalleController implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Cantidad de unidades de un producto deseadas por un usuario
	 */
	private int cantidadProductoSelecionado;

	@Inject
	private ServicioCarrito servicioCarrito;

	@Inject
	private SessionController session;

	@Inject
	private ServicioComentario servicioComentario;
	/**
	 * Lista de comentarios del producto elegido por el usuario
	 */
	private List<Comentario> comentarios;
	/**
	 * Comentario temporal usado para agregar un comentario a un producto
	 */
	private Comentario comentario;
	/**
	 * Integerq que representa el zoom hecho a una imagen
	 */
	private int isZoom;
	/**
	 * Formato para la fecha usado en la pantalla
	 */
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@PostConstruct
	public void init() {
		this.comentario = new Comentario();
		this.cantidadProductoSelecionado = 1;
		this.isZoom = 0;
		comentarios = servicioComentario.listarComentaios(session.getSelectProduct());
	}

	/**
	 * Metodo para cambiar la imagen principal del detalle
	 * 
	 * @param imagen {@link Imagen} imagen elegida por el usuario para ver
	 */
	public void changeImage(Imagen imagen) {
		System.out.println(imagen.getId());
		this.session.getSelectProduct().setImagen(imagen.getNombre());
	}

	/**
	 * Metodo para activar el zoom
	 */
	public void changeZoomStatusT() {
		System.out.println(isZoom);
		if (this.isZoom != 1) {
			this.isZoom = 1;
		}
	}

	/**
	 * Metodo para desactivar el zoom
	 */
	public void changeZoomStatusF() {
		System.out.println(isZoom);
		if (this.isZoom != 0) {
			this.isZoom = 0;
		}
	}

	/**
	 * Metodo para poder agregar un comentario a un producto
	 */
	public void agregarComentario() {
		this.prepareComment();
		System.out.println("inserta un nuevo comentario");
		this.servicioComentario.agregarComentario(comentario);
		comentarios.add(comentario);
		this.comentario = new Comentario();
	}

	/**
	 * Metodo para poder inicializar los atributos de un comentario para que despues
	 * se pueda guardar
	 */
	public void prepareComment() {
		this.comentario.setPersona(session.getPersona());
		this.comentario.setProducto(session.getSelectProduct());
		this.comentario.setFecha(new Date());
	}

	/**
	 * Metodo para agregar un producot al carrito
	 * 
	 * @param producto {@link Producto} producto elegido por el usuario para se
	 *                 agregado al carrito
	 */
	public void agregarProductoCarrito(Producto producto) {
		CarritoProducto carritoProducto = new CarritoProducto();

		carritoProducto.setCarrito(session.getPersona().getCarrito());
		carritoProducto.setCantidad(cantidadProductoSelecionado);
		carritoProducto.setEstatus("PENDIENTE");
		carritoProducto.setProducto(producto);

		servicioCarrito.agregarProductoCarrito(carritoProducto);
		session.getPersona().getCarrito().getCarritosProducto().add(carritoProducto);

	}

	public int getCantidadProductoSelecionado() {
		return cantidadProductoSelecionado;
	}

	public void setCantidadProductoSelecionado(int cantidadProductoSelecionado) {
		this.cantidadProductoSelecionado = cantidadProductoSelecionado;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public DateTimeFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}

	public int getIsZoom() {
		return isZoom;
	}

	public void setIsZoom(int isZoom) {
		this.isZoom = isZoom;
	}

}
