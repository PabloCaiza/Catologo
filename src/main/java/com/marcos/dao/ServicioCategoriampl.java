package com.marcos.dao;

import java.util.List;

import javax.inject.*;
import javax.persistence.EntityManager;

import com.marcos.dto.Categoria;

public class ServicioCategoriampl implements ServicioCategoria{
	@Inject
	EntityManager em;
	@Override
	public List<Categoria> listarCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crear(Categoria caterogira) {
		// TODO Auto-generated method stub
		
	}

}
