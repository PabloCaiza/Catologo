package com.marcos.dto;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name="Categoria.findAll",query = "SELECT c FROM Categoria c")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tipo;
	private String genero;
	@OneToMany(mappedBy = "categoria",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Producto> productos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", tipo=" + tipo + ", genero=" + genero + "]";
	}
	
	
	
	
	
}
