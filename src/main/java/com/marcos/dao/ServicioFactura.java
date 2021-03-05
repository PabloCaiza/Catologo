package com.marcos.dao;

import java.util.List;

import com.marcos.dto.Factura;
import com.marcos.dto.Persona;
import com.paypal.orders.Order;

public interface ServicioFactura {
	
	Factura guardarFactura(Factura factura,Order order,Persona persona);
	
	List<Factura> findAllByPersona(Persona persona);

}
