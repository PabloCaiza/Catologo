package com.marcos.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.ExternalContextFactory;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioPersonaI;
import com.marcos.dto.Persona;

@Named("personaB")
@RequestScoped
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Persona> personas;
	private String password;
	private String correo;
	private Persona persona;

	@Inject
	private ServicioPersonaI servicio;

	@PostConstruct
	public void init() {
		personas = servicio.listar();

		persona = new Persona();
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
		Persona persona=coincidenCredenciales();
		if(persona!=null) {
			if(persona.getRol().getNombre().equals("admin")) {
				System.out.println("es admin");
				
			}else {
				
				System.out.println("es usuario");
				
			}
		}else {
			FacesContext.getCurrentInstance().addMessage("loginForm",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No coinciden las credenciales", ""));
			
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