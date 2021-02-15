package com.marcos.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.marcos.dto.Persona;
@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersonaI {

	@Inject
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> listar() {

		return em.createNamedQuery("Persona.findAll").getResultList();
	}

	@Override
	public void crear(Persona persona) {
		em.getTransaction().begin();
		em.persist(persona);
		em.getTransaction().commit();
	}

	@Override
	public void actualizar(int id, Persona persona) {
		persona.setPersId(id);
		em.getTransaction().begin();
		em.merge(persona);
		em.getTransaction().commit();
	}

	@Override
	public void eliminar(int id) {
		em.getTransaction().begin();
		em.remove(em.find(Persona.class, id));
		em.getTransaction().commit();
	}

}
