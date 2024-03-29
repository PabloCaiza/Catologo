package com.marcos.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;
import javax.persistence.EntityManager;

import com.marcos.dto.Categoria;

import com.marcos.dto.Producto;
import com.marcos.dto.ProductoReportes;

@ApplicationScoped
public class ServicioProductompl implements ServicioProducto {
	@Inject
	private EntityManager em;

	@Override
	public List<Producto> listarProductos() {
		return em.createNamedQuery("Producto.findAll").getResultList();
	}

	@Override
	public void crear(Producto producto) {
		em.getTransaction().begin();
		em.persist(producto);
		em.getTransaction().commit();

	}

	@Override
	public void elimnar(int id) {
		em.getTransaction().begin();
		Producto producto = em.find(Producto.class, id);
		em.remove(producto);
		em.getTransaction().commit();

	}

	@Override
	public void modificar(int id, Producto producto) {
		producto.setId(id);
		em.getTransaction().begin();
		em.merge(producto);
		em.getTransaction().commit();

	}

	@Override
	public List<Producto> queryByNameFilter(String filter) {
		return em.createNamedQuery("Producto.findByName").setParameter("filter", "%" + filter + "%").getResultList();

	}

	@Override
	public List<Producto> queryByCategoria(String genero, String tipo) {
		Categoria categoria = em
				.createQuery("select c from Categoria c WHERE c.genero = :gender AND c.tipo = :type", Categoria.class)
				.setParameter("gender", genero).setParameter("type", tipo).getResultList().get(0);

		return em.createQuery("select p from Producto p WHERE p.categoria = :categoria AND p.estado = true",
				Producto.class).setParameter("categoria", categoria).getResultList();

	}

	@Override
	public List<Producto> queryByGenero(String genero) {

		List<Categoria> categorias = em
				.createQuery("select c from Categoria c WHERE c.genero = :gender ", Categoria.class)
				.setParameter("gender", genero).getResultList();

		return em.createQuery("select p from Producto p WHERE p.categoria IN :categorias AND p.estado = true",
				Producto.class).setParameter("categorias", categorias).getResultList();

	}

	@Override
	public List<Producto> listarProductosCliente() {
		return em.createQuery("select p from Producto p WHERE p.estado = true" + "", Producto.class).getResultList();

	}

	@Override
	public List<ProductoReportes> consultar() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT new com.marcos.dto.ProductoReportes(p ,SUM(cp.cantidad)as cantidadSuma) "
				+ "FROM CarritoProducto cp " + "INNER JOIN cp.producto p WHERE cp.estatus ='PAGADO' " + "GROUP BY p.id",
				ProductoReportes.class).getResultList();
	}

	@Override
	public List<ProductoReportes> consultarGenerosCompras() {
		return em.createQuery("SELECT new com.marcos.dto.ProductoReportes(c.genero ,SUM(cp.cantidad)as sumatoria) "
				+"FROM CarritoProducto cp " 
				+"INNER JOIN cp.producto p "
				+"INNER JOIN p.categoria c "
				+"GROUP BY c.genero",
				ProductoReportes.class).getResultList();
	}
	
	
	

}
