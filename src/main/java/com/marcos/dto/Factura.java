package com.marcos.dto;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
/**
 * @author pablo
 *
 */
@Entity
@Table(name ="factura" )
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_factura")
	private int idFactura;
	
	@Column(name = "total")
	private double total;
	
	@ManyToOne
	@JoinColumn(name = "id_persona")
	private Persona personaF;
	
	@OneToMany(mappedBy = "factura")
	List<CarritoProducto> itemsCarrito;
	
	@Column(name = "fecha")
	private Date fecha;
	
	
	
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Persona getPersona() {
		return personaF;
	}
	public void setPersona(Persona persona) {
		this.personaF = persona;
	}
	public List<CarritoProducto> getItemsCarrito() {
		return itemsCarrito;
	}
	public void setItemsCarrito(List<CarritoProducto> itemsCarrito) {
		this.itemsCarrito = itemsCarrito;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
	

}
