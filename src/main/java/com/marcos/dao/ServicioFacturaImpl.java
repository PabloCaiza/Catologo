package com.marcos.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.marcos.dto.Factura;
import com.marcos.dto.Persona;
import com.paypal.orders.Order;

@ApplicationScoped
public class ServicioFacturaImpl implements ServicioFactura {
	private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("pujpa");
	@Override
	public Factura guardarFactura(Factura factura, Order order, Persona persona) {
		
		//obtener datos de la ornden de paypal
		double envioDescuento = Double.parseDouble(
				order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shippingDiscount().value());
		
		double envio = Double
				.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shipping().value());
		
		double handling = Double
				.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().handling().value());
		
		double total = Double
				.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().itemTotal().value());
		
		double impuestoTotal = Double
				.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().taxTotal().value());
		
		
		
		factura.setOrderId(order.id());
		factura.setFecha(LocalDateTime.now());
		factura.setPais(order.payer().addressPortable().adminArea1());
		factura.setCiudad(order.payer().addressPortable().adminArea2());
		factura.setCodigoPostal(order.payer().addressPortable().postalCode());
		factura.setDireccion(
				order.payer().addressPortable().addressLine1() + order.payer().addressPortable().addressLine2());

		factura.setDivisa(order.purchaseUnits().get(0).amountWithBreakdown().currencyCode());

		factura.setEnvio(envio);
		factura.setEnvioDescuento(envioDescuento);
		factura.setHandling(handling);
		factura.setTotal(total);
		factura.setImpuestoTotal(impuestoTotal);
		
		factura.setPersona(persona);
		
		//almacenamos en la base de datos
		EntityManager em =emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(factura);
			em.getTransaction().commit();
			return factura;
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			return null;
		}finally {
			em.close();
		}
		
		
		
		

	}
	@Override
	public List<Factura> findAllByPersona(Persona persona) {
		EntityManager em=emf.createEntityManager();
		return em.createNamedQuery("Factura.findAllByPersona").setParameter("persona", persona).getResultList();
		
	}

}
