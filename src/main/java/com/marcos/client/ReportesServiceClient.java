package com.marcos.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;


/**
 * Clase que consumeel web service  de reportes
 * @author pablo
 *
 */

@ApplicationScoped
public class ReportesServiceClient {
	
	String urlReportesWS="http://localhost:8080/tienda-repportes/pablo/reporteWS";
	public Response generarReporte(String orderId,String destinatario,String cliente) {
		ClientConfig config=new ClientConfig();
		Client client=ClientBuilder.newClient(config);
		WebTarget webTarget=client.target(urlReportesWS).path("generarReporte");
		Form form=new Form();
		form.param("orderID", orderId);
		form.param("cliente", cliente);
		form.param("destinatario", destinatario);
		
		Response  response=webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE),Response.class);
		
		return response;
		
		
	}
	

}
