package com.marcos.utils;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;

import com.marcos.beans.ProductoBean;

public class CommonUtils {
	/**
	 * Metodo para refireccionarse entre pantallas del aplicativo
	 * @param url
	 * @throws IOException
	 */
	
	private static final org.apache.logging.log4j.Logger LOGGER=LogManager.getLogger(ProductoBean.class);
	public static void redireccionarPagina(String url) throws IOException {
		ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
		String contextPath=context.getRequestContextPath();
	
			context.redirect(contextPath+url);
		
		
	}
	
	public static void mostarMensaje(Severity severity,String summary,String detail) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity,summary,detail));
	}

}
