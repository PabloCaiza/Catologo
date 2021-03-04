package com.marcos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.marcos.dto.CarritoProducto;
import com.marcos.dto.Factura;

@ApplicationScoped
public class ServicioCarritoImpl implements ServicioCarrito {

	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pujpa");
	private double total = 0;

	@Override
	public void agregarProductoCarrito(CarritoProducto item) {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			em.persist(item);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public double calcularTotal(List<CarritoProducto> items) {
			total=0;
		items.forEach(item -> {

			total += item.getCantidad() * item.getProducto().getPrecio();

		});
		return total;
	}

	@Override
	public void eliminarCarritoProducto(CarritoProducto item) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		try {
			em.remove(em.find(CarritoProducto.class,item.getIdCarritoProducto()));
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}

	}

	@Override
	public void actualizarCantidadProducto(CarritoProducto item) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();

		try {
			
			em.merge(item);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		
	}
	
	@Override
	public boolean actualizarCarritoProducto(List<CarritoProducto> items,Factura factura) {
		boolean isUpdate=false; 
		List<CarritoProducto> listaTemp=new ArrayList<CarritoProducto>();
		for (CarritoProducto carritoProducto : items) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			try {
				carritoProducto.setEstatus("PAGADO");
				carritoProducto.setFactura(factura);
				em.merge(carritoProducto);
				em.getTransaction().commit();
				
				listaTemp.add(carritoProducto);
				
			}catch(Exception e) {
				em.getTransaction().rollback();
				carritoProducto.setEstatus("PENDIENTE");
				carritoProducto.setFactura(null);
				
			}finally {
			 em.close();
			}
			
		}
		
		if(listaTemp.size()==items.size()) {
			isUpdate=true;
		}
		
		return isUpdate;
	}

}
