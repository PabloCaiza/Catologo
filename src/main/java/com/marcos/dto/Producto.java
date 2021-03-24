package com.marcos.dto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Producto.findByName",query = "SELECT p FROM Producto p WHERE LOWER(p.nombre)  LIKE :filter AND p.estado = true ")
@NamedQuery(name = "Producto.findAll",query = "SELECT E FROM Producto E")
public class Producto  implements Comparable<Producto> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int id;
	@Column(name="nombre")
	private String nombre;
	private String imagen;
	private double precio;
	private String descripcion;
	private boolean estado ;
	private LocalDateTime fecha_creacion;

	@ManyToOne
	@JoinColumn(name="id_categoria", nullable=false)
	private Categoria categoria;
	@OneToMany(mappedBy = "producto")
	private List<Comentario> comentarios;
	@OneToMany(mappedBy = "producto")
	private List<Imagen> imagenes;
	
	
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
	
	
	
	
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public List<Imagen> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
	@Override
	public int compareTo(Producto o) {
		 
		if(o.getNombre().equals(this.nombre)) {
			return 0;
		}else if(this.nombre.compareTo(o.getNombre())>0){
			return 1;
		}else {
			return -1;
		}
		
		
	}
	
	public class precioComparador implements Comparator<Producto>{

        @Override
        public int compare(Producto t, Producto t1) {
           return t.getPrecio() > t1.getPrecio() ? +1 : t.getPrecio() < t1.getPrecio() ? -1 : 0;
        }
     }
	
	public class fechaComparador implements Comparator<Producto>{

        @Override
        public int compare(Producto t, Producto t1) {
           return t.getFecha_creacion().compareTo(t1.getFecha_creacion());
        }
     }
	
	
	
	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	/**
	 * @return the fecha_creacion
	 */
	public LocalDateTime getFecha_creacion() {
		return fecha_creacion;
	}
	/**
	 * @param fecha_creacion the fecha_creacion to set
	 */
	public void setFecha_creacion(LocalDateTime fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	
	

}
