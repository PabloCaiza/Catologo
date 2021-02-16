package com.marcos.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;
import javax.persistence.EntityManager;

import com.marcos.dto.Producto;
@ApplicationScoped
public class ServicioProductompl implements ServicioProducto{
	@Inject 
	private EntityManager em;
	@Override
	public List<Producto> listarProductos() {
		return em.createNamedQuery("Producto.findAll").getResultList();
	}
	@Override
	public void crear(Producto producto) {
		em.getTransaction().begin();
		em.persist(producto);
		em.getTransaction().commit();
		
	}
	@Override
	public void elimnar(int id) {
		em.getTransaction().begin();
		Producto producto=em.find(Producto.class, id);
		em.remove(producto);
		em.getTransaction().commit();
		
	}
	@Override
	public void modificar(int id, Producto producto) {
		producto.setId(id);
		em.getTransaction().begin();
		em.merge(producto);
		em.getTransaction().commit();
		
	}

}
