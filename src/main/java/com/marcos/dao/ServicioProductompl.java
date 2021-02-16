package com.marcos.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;
import javax.persistence.EntityManager;

import com.marcos.dto.Producto;
@ApplicationScoped
public class ServicioProductompl implements ServicioProducto{
	@Inject 
	private EntityManager em;
	@Override
	public List<Producto> listarProductos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void crear(Producto producto) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void elimnar(int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modificar(int id, Producto producto) {
		// TODO Auto-generated method stub
		
	}

}
