/**
 * 
 */
package com.marcos.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.marcos.utils.CommonUtils;
import com.marcos.dao.ServicioCategoria;
import com.marcos.dao.ServicioProducto;
import com.marcos.dto.Categoria;
import com.marcos.dto.Producto;

/**
 * @author c-ado
 *
 */
@Named("registroProductoController")
@ViewScoped
public class RegistroProductoController implements Serializable {
	private static final long serialVersionUID = 1L;
	private Producto producto;
	private String tipo;
	private List<String> tipos;
	private String genero;
	private List<String> generos;
	List<Categoria> categorias;

	/**
	 * Directorio donde se almacenan las imagenes de productos del proyecto.
	 */
	String absolutePath = null;
	/**
	 * Objeto que contendra el flujo de bytes del archivo de imagen a cargar.
	 */
	private InputStream inputStream;
	/**
	 * /** Objeto que se utiliza para almacenar el archivo de la imagen del producto
	 * a cargar de forma temporal.
	 */
	private UploadedFile uploadedFile;
	/**
	 * @return the producto
	 */
	@Inject
	SessionController sessionController;
	@Inject
	ServicioCategoria servicioCategoria;
	@Inject
	ServicioProducto servicioProducto;

	@PostConstruct
	public void init() {
		producto = new Producto();
		categorias = this.servicioCategoria.listarCategoria();
		this.listarTipos();
		this.listarGeneros();

		String relativePath = "resources/imgs/producto";
		this.absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);

		if (this.sessionController.getSelectProduct() != null) {
			this.genero = this.sessionController.getSelectProduct().getCategoria().getGenero();
			this.tipo = this.sessionController.getSelectProduct().getCategoria().getTipo();
		}
	}

	public void identificaCategoria() {

		for (Categoria aux : categorias) {
			if (aux.getTipo().equals(tipo) && aux.getGenero().equals(genero)) {
				this.producto.setCategoria(aux);
			}
		}

	}

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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * Metodo que permite inicializar una imagen de carga temporal para un album.
	 * 
	 * @param fileUploadEvent {@link FileUploadEvent} objeto que carga la imagen de
	 *                        forma temporal.
	 */
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {

		// :Se guarda el file en esta variable
		this.uploadedFile = fileUploadEvent.getFile();

		try {
			this.inputStream = fileUploadEvent.getFile().getInputStream();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void crearProducto() {
		System.out.println(producto);
		identificaCategoria();

		try {
			CommonUtils.guardarImagen(this.absolutePath, this.uploadedFile.getFileName(), this.inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.producto.setImagen(this.uploadedFile.getFileName());
		try {
			servicioProducto.crear(producto);
			FacesContext.getCurrentInstance().addMessage("registryForm",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo con exito", ""));
		} catch (Exception e) {

		} finally {
			producto = new Producto();

		}

	}

	public void actualizarProducto() {
        
		try {
			this.producto=this.sessionController.getSelectProduct();
			crearProducto();
			System.out.println("Ent");
			this.sessionController.setSelectProduct(null);
			rediccionar("http://localhost:8080/app-marcos-01/pages/admin/modificarProducto.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rediccionar(String pagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargarProducto(Producto pro) {
		this.sessionController.setSelectProduct(pro);
		rediccionar("http://localhost:8080/app-marcos-01/pages/admin/modificar.xhtml");
	}

	public void listarTipos() {
		tipos = this.servicioCategoria.listarTipos();
	}

	public void listarGeneros() {
		generos = this.servicioCategoria.listarGeneros();
	}

	/**
	 * @return the categorias
	 */
	public List<Categoria> getCategorias() {
		return categorias;
	}

	/**
	 * @param categorias the categorias to set
	 */
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	/**
	 * @return the tipos
	 */
	public List<String> getTipos() {
		return tipos;
	}

	/**
	 * @param tipos the tipos to set
	 */
	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}

	/**
	 * @return the generos
	 */
	public List<String> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	/**
	 * @return the sessionController
	 */
	public SessionController getSessionController() {
		return sessionController;
	}

	/**
	 * @param sessionController the sessionController to set
	 */
	public void setSessionController(SessionController sessionController) {
		this.sessionController = sessionController;
	}

	/**
	 * @return the servicioCategoria
	 */
	public ServicioCategoria getServicioCategoria() {
		return servicioCategoria;
	}

	/**
	 * @param servicioCategoria the servicioCategoria to set
	 */
	public void setServicioCategoria(ServicioCategoria servicioCategoria) {
		this.servicioCategoria = servicioCategoria;
	}

	/**
	 * @return the servicioProducto
	 */
	public ServicioProducto getServicioProducto() {
		return servicioProducto;
	}

	/**
	 * @param servicioProducto the servicioProducto to set
	 */
	public void setServicioProducto(ServicioProducto servicioProducto) {
		this.servicioProducto = servicioProducto;
	}
}
