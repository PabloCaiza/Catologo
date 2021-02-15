package com.marcos.em;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@ApplicationScoped
public class ProducerEm {

	@ApplicationScoped
	@Produces
	public EntityManager crearEm() {
		return Persistence.createEntityManagerFactory("pujpa").createEntityManager();
	}

}
