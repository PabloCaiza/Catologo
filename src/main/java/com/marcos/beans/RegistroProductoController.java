/**
 * 
 */
package com.marcos.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;

import com.marcos.utils.CommonUtils;
import com.marcos.dao.ServicioCategoria;
import com.marcos.dao.ServicioImagen;
import com.marcos.dao.ServicioProducto;
import com.marcos.dto.Categoria;
import com.marcos.dto.Imagen;
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
	private UploadedFile file;
	private UploadedFiles files;
	List<Categoria> categorias;
	private boolean registroHabilitado;
	private List<String> imagenesName;

	/**
	 * Directorio donde se almacenan las imagenes de productos del proyecto.
	 */
	String absolutePath = null;

	/**
	 * Objeto que contendra el flujo de bytes del archivo de imagen a cargar.
	 */
	private List<InputStream> inputStream = new ArrayList<InputStream>();
	/**
	 * /** Objeto que se utiliza para almacenar el archivo de la imagen del producto
	 * a cargar de forma temporal.
	 */
	private UploadedFiles uploadedFile;
	/**
	 * @return the producto
	 */

	@Inject
	SessionController sessionController;
	@Inject
	ServicioCategoria servicioCategoria;
	@Inject
	ServicioProducto servicioProducto;
	@Inject
	ServicioImagen servicioImagen;

	@PostConstruct
	public void init() {
		this.imagenesName = new ArrayList<String>();
		producto = new Producto();
		categorias = this.servicioCategoria.listarCategoria();
		this.listarTipos();
		this.listarGeneros();
		this.registroHabilitado = false;
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

	public void handleFileUpload(FileUploadEvent event) throws IOException {
		FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
		this.imagenesName.add(event.getFile().getFileName());
		this.inputStream.add(event.getFile().getInputStream());
		System.out.println(this.imagenesName.size());
		System.out.println(this.inputStream.size());
		this.registroHabilitado = false;
	}

	public void createImages() throws IOException {
		int longitud = this.inputStream.size();

		for (int i = 0; i < longitud; i++) {
			CommonUtils.guardarImagen(this.absolutePath, this.imagenesName.get(i), this.inputStream.get(i));
		}
		List<Imagen> imagenes = new ArrayList<Imagen>();

		for (int j = 0; j < longitud; j++) {
			Imagen img = new Imagen();
			img.setNombre(this.imagenesName.get(j));
			img.setProducto(this.producto);
			servicioImagen.crear(img);
			imagenes.add(img);
		}

		producto.setImagenes(imagenes);

	}

	public void crearProducto() {

		identificaCategoria();

		if (this.imagenesName.size() > 0) {
			this.producto.setImagen(this.imagenesName.get(0));
		} else if (this.registroHabilitado) {

		} else {
			FacesContext.getCurrentInstance().addMessage("registryForm",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay imagen", ""));

			return;
		}

		try {
            if(!this.registroHabilitado) {
            	producto.setFecha_creacion(LocalDateTime.now());
            	producto.setEstado(true);
            }
			servicioProducto.crear(producto);
			createImages();
			FacesContext.getCurrentInstance().addMessage("registryForm",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo con exito", ""));
		} catch (Exception e) {

		} finally {
			producto = new Producto();
			this.imagenesName.clear();
			this.inputStream.clear();
			this.registroHabilitado = false;
		}
	}

	public void actualizarProducto() {

		try {
			this.producto = this.sessionController.getSelectProduct();
			this.registroHabilitado = true;
			crearProducto();
			
			this.sessionController.setSelectProduct(null);
			
			FacesContext.getCurrentInstance().addMessage("registryForm",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico con exito", ""));
			
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

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public UploadedFiles getFiles() {
		return files;
	}

	public void setFiles(UploadedFiles files) {
		this.files = files;
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

	/**
	 * @return the absolutePath
	 */
	public String getAbsolutePath() {
		return absolutePath;
	}

	/**
	 * @param absolutePath the absolutePath to set
	 */
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	/**
	 * @return the inputStream
	 */
	public List<InputStream> getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(List<InputStream> inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the uploadedFile
	 */
	public UploadedFiles getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile the uploadedFile to set
	 */
	public void setUploadedFile(UploadedFiles uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * @return the servicioImagen
	 */
	public ServicioImagen getServicioImagen() {
		return servicioImagen;
	}

	/**
	 * @param servicioImagen the servicioImagen to set
	 */
	public void setServicioImagen(ServicioImagen servicioImagen) {
		this.servicioImagen = servicioImagen;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the registroHabilitado
	 */
	public boolean isRegistroHabilitado() {
		return registroHabilitado;
	}

	/**
	 * @param registroHabilitado the registroHabilitado to set
	 */
	public void setRegistroHabilitado(boolean registroHabilitado) {
		this.registroHabilitado = registroHabilitado;
	}

	/**
	 * @return the imagenesName
	 */
	public List<String> getImagenesName() {
		return imagenesName;
	}

	/**
	 * @param imagenesName the imagenesName to set
	 */
	public void setImagenesName(List<String> imagenesName) {
		this.imagenesName = imagenesName;
	}
}
