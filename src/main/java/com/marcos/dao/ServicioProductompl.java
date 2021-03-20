package com.marcos.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;
import javax.persistence.EntityManager;

import com.marcos.dto.Categoria;
import com.marcos.dto.Imagen;
import com.marcos.dto.Producto;

@ApplicationScoped
public class ServicioProductompl implements ServicioProducto {
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
		Producto producto = em.find(Producto.class, id);
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

	@Override
	public List<Producto> queryByNameFilter(String filter) {
		return em.createNamedQuery("Producto.findByName").setParameter("filter", "%" + filter + "%").getResultList();

	}

	@Override
	public List<Producto> queryByCategoria(String genero, String tipo) {
		Categoria categoria = em
				.createQuery("select c from Categoria c WHERE c.genero = :gender AND c.tipo = :type", Categoria.class)
				.setParameter("gender", genero).setParameter("type", tipo).getResultList().get(0);

		return em.createQuery("select p from Producto p WHERE p.categoria = :categoria", Producto.class)
				.setParameter("categoria", categoria).getResultList();

	}

	@Override
	public List<Producto> queryByGenero(String genero) {

		
		
		  List<Categoria> categorias=em.
		  createQuery("select c from Categoria c WHERE c.genero = :gender ",
		  Categoria.class).setParameter("gender", genero) .getResultList();
		  
		  
		  
		  return
		  em.createQuery("select p from Producto p WHERE p.categoria IN :categorias",
		  Producto.class) .setParameter("categorias", categorias).getResultList();
		 
		 

	
		 

	}

	@Override
	public void crearImagen(Imagen image) {
		em.getTransaction().begin();
		try {
			em.persist(image);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			
		}
	
		
	}

}
