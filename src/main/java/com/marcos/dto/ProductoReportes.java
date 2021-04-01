package com.marcos.dto;

public class ProductoReportes implements Comparable<ProductoReportes>  {
	private Producto producto;
	private long cantidadSuma;
    
	private String genero;
	private long sumatoria;
	
	private Persona persona;
	private double total ;
	
	public ProductoReportes() {
		super();
	}
	
	public ProductoReportes(Producto producto,long cantidadSuma) {
		this.producto=producto;
		this.cantidadSuma=cantidadSuma;
	}
	
	public ProductoReportes(Persona persona,double total) {
		this.persona=persona;
		this.total=total;
	}
	
	public ProductoReportes(String genero,long sumatoria ) {
		this.genero=genero;
		this.sumatoria=sumatoria;
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

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return the sumatoria
	 */
	public long getSumatoria() {
		return sumatoria;
	}

	/**
	 * @param sumatoria the sumatoria to set
	 */
	public void setSumatoria(long sumatoria) {
		this.sumatoria = sumatoria;
	}




}
