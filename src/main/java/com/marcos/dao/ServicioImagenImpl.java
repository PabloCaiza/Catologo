/**
 * 
 */
package com.marcos.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.marcos.dto.Imagen;

/**
 * @author c-ado
 *
 */
@ApplicationScoped
public class ServicioImagenImpl implements ServicioImagen {
	@Inject
	EntityManager em;
	
	@Override
	public void crear(Imagen imagen) {
		em.getTransaction().begin();
		em.persist(imagen);
		em.getTransaction().commit();
	}

}
