package com.marcos.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;
import javax.persistence.EntityManager;

import com.marcos.dto.Categoria;

@ApplicationScoped
public class ServicioCategoriampl implements ServicioCategoria{
	@Inject
	EntityManager em;
	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listarCategoria() {
		return em.createNamedQuery("Categoria.findAll").getResultList();
	}

	@Override
	public void crear(Categoria caterogira) {
		em.getTransaction().begin();
		em.persist(caterogira);
		em.getTransaction().commit();
		
	}

	@Override
	public Categoria encontrarCategoria(int id) {
		em.getTransaction().begin();
		return em.find(Categoria.class, id);
	}

}
