package com.marcos.dto;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Producto.findByName",query = "SELECT p FROM Producto p WHERE p.nombre like :filter ")
@NamedQuery(name = "Producto.findAll",query = "SELECT E FROM Producto E")
public class Producto  implements Comparable<Producto> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int id;
	@Column(name="nombre")
	private String nombre;
	private double precio;
	private String descripcion;
	private String imagen;
	@ManyToOne
	@JoinColumn(name="id_categoria", nullable=false)
	private Categoria categoria;
	@OneToMany(mappedBy = "producto")
	private List<Comentario> comentarios;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public int compareTo(Producto o) {
		 
		if(o.getPrecio()==this.precio) {
			return 0;
		}else if(this.precio>o.precio) {
			return 1;
		}else {
			return -1;
		}
		
		
	}
	
	
	

}
