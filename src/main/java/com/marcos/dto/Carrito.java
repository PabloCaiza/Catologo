package com.marcos.dto;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "carrito")
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carrito")
	private int idCarrito;
	
	@OneToOne
	@JoinColumn(name = "id_persona")
	private Persona persona;
	
	@OneToMany(mappedBy = "carrito")
	private List<CarritoProducto> carritosProducto;

	public int getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<CarritoProducto> getCarritosProducto() {
		return carritosProducto;
	}

	public void setCarritosProducto(List<CarritoProducto> carritosProducto) {
		this.carritosProducto = carritosProducto;
	}
	
	

}
