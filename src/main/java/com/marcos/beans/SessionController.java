package com.marcos.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

import com.marcos.dto.Persona;
import com.marcos.dto.Producto;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

@Named("sessionController")
@SessionScoped
public class SessionController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Persona persona;
	private Producto selectProduct;
	private double totalCompra = 0;
	private HttpResponse<Order> order;
	private int paso;

	@PostConstruct
	public void init() {
		System.out.println("***********************************");
		System.out.println("ingresamos a una nueva sesion");

	}

	public Persona getPersona() {

		return persona;

	}

	public void setPersona(Persona persona) {

		this.persona = persona;
		System.out.println(persona.getNombre());
	}

	public void imprimir() {
		System.out.println("Nombre de la persona:  " + persona.getNombre());
	}

	public Producto getSelectProduct() {
		return selectProduct;
	}

	public void setSelectProduct(Producto selectProduct) {
		this.selectProduct = selectProduct;
	}

	public double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(double totalCompra) {
		this.totalCompra = totalCompra;
	}

	public HttpResponse<Order> getOrder() {
		return order;
	}

	public void setOrder(HttpResponse<Order> order) {
		this.order = order;
	}

	public int getPaso() {
		return paso;
	}

	public void setPaso(int paso) {
		this.paso = paso;
	}

}
