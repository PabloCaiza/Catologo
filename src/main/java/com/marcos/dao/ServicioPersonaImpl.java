package com.marcos.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.marcos.dto.Persona;
import com.marcos.dto.Rol;

@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersonaI {

//	@Inject
//	private EntityManager em;
	private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("pujpa");

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> listar() {
		EntityManager em=emf.createEntityManager();
		return em.createNamedQuery("Persona.findAll").getResultList();
	}

	@Override
	public void crear(Persona persona) {
		
		EntityManager em=emf.createEntityManager();
		// recuperar rol
		Rol userRol = em.find(Rol.class, 2);
		System.out.println(userRol.getNombre());
		persona.setRol(userRol);
		em.getTransaction().begin();
		try {
			em.persist(persona);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public void actualizar(Persona persona) {

//		em.getTransaction().begin();
//		em.merge(persona);
//		em.getTransaction().commit();
	}

	@Override
	public void eliminar(int id) {
//		em.getTransaction().begin();
//		em.remove(em.find(Persona.class, id));
//		em.getTransaction().commit();
	}

}
