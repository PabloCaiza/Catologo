package com.marcos.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.marcos.dto.Comentario;
import com.marcos.dto.Producto;

@ApplicationScoped
public class ServicioComentarioImpl implements ServicioComentario{
	private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("pujpa");
	@Override
	public List<Comentario> listarComentaios(Producto producto) {
		EntityManager em =emf.createEntityManager();
		return em.createNamedQuery("findComentarioByProducto").setParameter("product", producto).getResultList();
		
		
	}

	@Override
	public void agregarComentario(Comentario comentario) {
		EntityManager em =emf.createEntityManager();
		em.getTransaction().begin();;
		try {
			em.persist(comentario);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		
		
		
	}

}
