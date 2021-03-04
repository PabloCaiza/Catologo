package com.marcos.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

/**
 * @author pablo
 *
 */
@Entity
@Table(name = "factura")
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_factura")
	private int idFactura;

	@Column(name = "orderId", length = 50, nullable = false)
	private String orderId;

	@Column(name = "impuestoTotal", nullable = false)
	private double impuestoTotal;

	@Column(name = "envio", nullable = false)
	private double envio;

	@Column(name = "envioDescuento", nullable = false)
	private double envioDescuento;

	@Column(name = "handling", nullable = false)
	private double handling;

	@Column(name = "total")
	private double total;

	@Column(name = "direccion", length = 500, nullable = false)
	private String direccion;

	@Column(name = "codigo_Postal", length = 5, nullable = false)
	private String codigoPostal;

	@Column(name = "pais", length = 45, nullable = false)
	private String pais;

	@Column(name = "ciudad", length = 45, nullable = false)
	private String ciudad;

	@Column(name = "divisa", length = 45, nullable = false)
	private String divisa;
	
	@Column(name = "fecha")
	private LocalDateTime fecha;

	@ManyToOne
	@JoinColumn(name = "id_persona")
	private Persona personaF;

	@OneToMany(mappedBy = "factura")
	List<CarritoProducto> itemsCarrito;



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



	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getImpuestoTotal() {
		return impuestoTotal;
	}

	public void setImpuestoTotal(double impuestoTotal) {
		this.impuestoTotal = impuestoTotal;
	}

	public double getEnvio() {
		return envio;
	}

	public void setEnvio(double envio) {
		this.envio = envio;
	}

	public double getEnvioDescuento() {
		return envioDescuento;
	}

	public void setEnvioDescuento(double envioDescuento) {
		this.envioDescuento = envioDescuento;
	}

	public double getHandling() {
		return handling;
	}

	public void setHandling(double handling) {
		this.handling = handling;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public Persona getPersonaF() {
		return personaF;
	}

	public void setPersonaF(Persona personaF) {
		this.personaF = personaF;
	}
	
	
	

}
