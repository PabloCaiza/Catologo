package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import com.marcos.client.ReportesServiceClient;
import com.marcos.dao.ServicioCarrito;
import com.marcos.dao.ServicioFactura;
import com.marcos.dto.CarritoProducto;
import com.marcos.dto.Factura;
import com.marcos.dto.Persona;
import com.marcos.utils.CommonUtils;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

@Named("pagoController")
@ViewScoped
public class PagoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Objeto que permite mostrar los mensajes de LOG en la consola del servidor o
	 * en un archivo externo.
	 */
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(PagoController.class);
	@Inject
	SessionController session;
	@Inject
	ServicioFactura sevicioFactura;
	@Inject
	ServicioCarrito servicioCarrito;
	@Inject
	 ReportesServiceClient reportesServiceClient;

	@PostConstruct
	public void init() {
		LOGGER.info("Iniciamos pagoController correctamente");
	}
/**
 * Metodo que nos permite guardar la factura
 */
	public void guardarFactura() {
		LOGGER.info("guardando factura");
		HttpResponse<Order> order = this.session.getOrder();
		Persona persona = this.session.getPersona();
		Factura factura = this.sevicioFactura.guardarFactura(new Factura(), order.result(), persona);

		if (factura != null) {
			boolean isUpdateCarrito = this.servicioCarrito
					.actualizarCarritoProducto(persona.getCarrito().getCarritosProducto(), factura);
			
			if(isUpdateCarrito) {
				//Response response =reportesServiceClient.generarReporte(order.result().id(), persona.getCorreo(), persona.getNombre());
				//LOGGER.info("Response "+response.getStatus());
				this.session.getPersona().getCarrito().setCarritosProducto(new ArrayList<CarritoProducto>());
				this.cambiarPaso("/pages/cliente/confirmacion.xhtml", 2);
			}

		} else {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "UPS", "no se genero la factura");
		}

	}
/**
 * Metodo que nospermite cambiar de pasos para el proceso de compra
 * @param url {@link String} parametro con la informacion del url de la pantalla que queremos cargar
 * @param paso {@link Integer} parametros con el numeros de paso al que queremos ir
 */
	public void cambiarPaso(String url,int paso) {
		try {
			session.setPaso(paso);
			CommonUtils.redireccionarPagina(url);
			
		} catch (IOException e) {
			CommonUtils.mostarMensaje(FacesMessage.SEVERITY_ERROR, "ups", "no se pudo avanzar el paso");
		}
	}
	
	
	public SessionController getSession() {
		return session;
	}

	public void setSession(SessionController session) {
		this.session = session;
	}
	public ReportesServiceClient getReportesServiceClient() {
		return reportesServiceClient;
	}

	public void setReportesServiceClient(ReportesServiceClient reportesServiceClient) {
		this.reportesServiceClient = reportesServiceClient;
	}
	
}
