package com.marcos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import com.marcos.dto.Categoria;
import com.marcos.dto.Category;

@ApplicationScoped

public class ServicioCategoriampl implements ServicioCategoria {
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

	public List<String> listarTipos() {
		List<String> tipos = em.createQuery("select distinct tipo " + "from Categoria p ", String.class)
				.getResultList();
		return tipos;
	}

	@Override
	public List<String> listarGeneros() {
		List<String> generos = em.createQuery("select distinct genero " + "from Categoria p ", String.class)
				.getResultList();
		return generos;
	}

	@Override
	public List<Category> obtainCateogires() {
		List<Category> categories = new ArrayList<Category>();
		// obtain gender
		List<String> genders = em.createQuery("select distinct genero " + "from Categoria p ", String.class)
				.getResultList();
		genders.forEach(gender -> {
			List<String> types = em
					.createQuery("select c.tipo  from Categoria c WHERE c.genero = :gender", String.class)
					.setParameter("gender", gender).getResultList();
			
			categories.add(new Category(gender,types));

		});

		return categories;
	}
}
