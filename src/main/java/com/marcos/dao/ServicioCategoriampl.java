package com.marcos.dao;

import java.util.List;

import javax.inject.*;
import javax.persistence.EntityManager;

import com.marcos.dto.Categoria;

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

}
