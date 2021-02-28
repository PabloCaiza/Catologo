package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.ExternalContextFactory;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioPersonaI;
import com.marcos.dto.Persona;
import com.marcos.utils.CommonUtils;

@Named("personaBean")
@javax.faces.view.ViewScoped
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Persona> personas;
	private String password;
	private String correo;
	private Persona persona;

	@Inject
	private ServicioPersonaI servicio;
	@Inject
	private SessionController session;

	@PostConstruct
	public void init() {
		personas = servicio.listar();

		persona = new Persona();
	}

	public SessionController getSession() {
		return session;
	}

	public void setSession(SessionController session) {
		this.session = session;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Persona getPersona() {
		return persona;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void ingresar() {
		Persona persona = coincidenCredenciales();
		
		if (persona != null) {
			persona.getCarrito().setCarritosProducto(persona.getCarrito().getCarritosProducto().stream()
					.filter(item -> item.getEstatus().equals("PENDIENTE")).collect(Collectors.toList()));
		
			session.setPersona(persona);
			rediccionar("pages/commons/dashBoard.xhtml");

		} else {
			FacesContext.getCurrentInstance().addMessage("loginForm",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No coinciden las credenciales", ""));

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

	public Persona coincidenCredenciales() {
		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getCorreo().equalsIgnoreCase(correo) && personas.get(i).getClave().equals(password)) {
				return personas.get(i);
			}

		}

		return null;

	}

	public void crearPersona() {

		try {
			if (coincidenPasswords()) {
				if (!existeUsuario()) {
					servicio.crear(persona);
					ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
					context.redirect("login.xhtml");
				} else {
					FacesContext.getCurrentInstance().addMessage("registryForm",
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario ya existe", ""));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage("registryForm",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "No coinciden las contraseñas", ""));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			persona = new Persona();
			personas = servicio.listar();

		}

	}

	public boolean existeUsuario() {

		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getCorreo().equals(persona.getCorreo())) {
				return true;
			}
		}
		return false;

	}

	public boolean coincidenPasswords() {
		if (password.equals(persona.getClave())) {
			return true;
		}
		return false;
	}
}