package com.marcos.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.marcos.dto.Usuario;
@ApplicationScoped
public class ServicioUsuariompl implements ServicioUsuario {
	@Inject EntityManager em;
	@SuppressWarnings("unchecked")
	@Override
	public void crear(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		
		em.getTransaction().commit();
		
	}

	@Override
	public void eliminar(int id) {
		Usuario user=em.find(Usuario.class, id);
		em.remove(user);
		
	}

	@Override
	public void modificar(int id, Usuario usuario) {
		usuario.setId(id);
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
				
		
	}

}
