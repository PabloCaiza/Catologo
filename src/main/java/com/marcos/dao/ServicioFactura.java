package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Factura;
import com.marcos.dto.Persona;
import com.paypal.orders.Order;
/**
 * Servicio que contiene los metodos de logica de negocio para manejar las facturas
 * @author c-ado
 *
 */
public interface ServicioFactura {
	/**
	 * Metodo que permite guardar una factura en la bd
	 * @param factura {@link Factura} a guardarse en la bd
	 * @param order {@link Order} orden de paypal asociada a la Factura
	 * @param persona {@link Persona} que realizo la compra
	 * @return
	 */
	Factura guardarFactura(Factura factura,Order order,Persona persona);
	/**
	 * Metodo que lista las facturas asociadas a una persona
	 * @param persona {@link Persona} a la que se le desar listar sus facturas 
	 * @return {@link List<Factura>} lista de factura de la persona deseada
	 */
	List<Factura> findAllByPersona(Persona persona);

}
