package com.marcos.dto;

public class ProductoReportes implements Comparable<ProductoReportes>  {
	private Producto producto;
	private long cantidadSuma;

	public ProductoReportes() {
		super();
	}
	
	public ProductoReportes(Producto producto,long cantidadSuma) {
		this.producto=producto;
		this.cantidadSuma=cantidadSuma;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the cantidadSuma
	 */
	public long getCantidadSuma() {
		return cantidadSuma;
	}

	/**
	 * @param cantidadSuma the cantidadSuma to set
	 */
	public void setCantidadSuma(long cantidadSuma) {
		this.cantidadSuma = cantidadSuma;
	}

	@Override
	public int compareTo(ProductoReportes or) {
		if(or.getCantidadSuma()==this.cantidadSuma) {
			return 0;
		}else if(this.cantidadSuma>or.getCantidadSuma()){
			return 1;
		}else {
			return -1;
		}
	}




}
