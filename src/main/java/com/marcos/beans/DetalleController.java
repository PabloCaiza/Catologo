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

@Named("detalleController")
@ViewScoped
public class DetalleController implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cantidadProductoSelecionado;

	@Inject
	private ServicioCarrito servicioCarrito;

	@Inject
	private SessionController session;

	@Inject
	private ServicioComentario servicioComentario;

	private List<Comentario> comentarios;
	private Comentario comentario;
	private int isZoom;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@PostConstruct
	public void init() {
		this.comentario = new Comentario();
		this.cantidadProductoSelecionado = 1;
		this.isZoom=0;
		comentarios = servicioComentario.listarComentaios(session.getSelectProduct());
	}

	public void changeImage(Imagen imagen) {
		System.out.println(imagen.getId());
		this.session.getSelectProduct().setImagen(imagen.getNombre());
	}
	
	public void changeZoomStatusT() {
		System.out.println(isZoom);
		if(this.isZoom!=1) {
		this.isZoom=1;
		}
	}
	public void changeZoomStatusF() {
		System.out.println(isZoom);
		if(this.isZoom!=0) {
		this.isZoom=0;
		}
	}
	
	public void agregarComentario() {
		this.prepareComment();
		System.out.println("inserta un nuevo comentario");
		this.servicioComentario.agregarComentario(comentario);
		comentarios.add(comentario);
		this.comentario = new Comentario();
	}

	public void prepareComment() {
		this.comentario.setPersona(session.getPersona());
		this.comentario.setProducto(session.getSelectProduct());
		this.comentario.setFecha(new Date());
	}

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
