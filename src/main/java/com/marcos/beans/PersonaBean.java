package com.marcos.beans;

import java.io.IOException;
import java.io.Serializable;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;

import javax.faces.context.ExternalContext;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioPersonaI;
import com.marcos.dto.Persona;

/**
 * Clase que controla el flujo de datos de la pantalla login.xhtml y
 * registro.xthml
 * 
 * @author c-ado
 *
 */
@Named("personaBean")
@javax.faces.view.ViewScoped
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Lista de persona existente en la bd de la tienda
	 */
	private List<Persona> personas;

	/**
	 * Password de la persona que desea ingresar
	 */
	private String password;
	/**
	 * Correo de la persona que desea ingresar
	 */
	private String correo;
	/**
	 * Object que contiene a la persona que desea ingresar
	 */
	private Persona persona;
	/**
	 * Numero de intentos que el usuario tiene para ingresar
	 */
	private int contador;

	@Inject
	private ServicioPersonaI servicio;
	@Inject
	private SessionController session;

	@PostConstruct
	public void init() {
		personas = servicio.listar();

		persona = new Persona();
	}

	/**
	 * Metodo que permite a persona ingresar al sistema
	 */

	public void ingresar() {

		Persona persona = coincidenCredenciales();

		if (this.contador != 3) {

			if (persona != null) {
				if (persona.getRol().getNombre().equals("admin")) {
					session.setPersona(persona);
					rediccionar("pages/admin/admin.xhtml");
				} else {
					persona.getCarrito().setCarritosProducto(persona.getCarrito().getCarritosProducto().stream()
							.filter(item -> item.getEstatus().equals("PENDIENTE")).collect(Collectors.toList()));

					session.setPersona(persona);
					rediccionar("pages/commons/dashBoard.xhtml");
				}
			} else {
				FacesContext.getCurrentInstance().addMessage("loginForm",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "No coinciden las credenciales", ""));

			}
		} else {
			FacesContext.getCurrentInstance().addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Su intentos estan agotados intentelo mas tarde", ""));
		}
	}

	/**
	 * Metodo que redireccion a la persona a la pagina deseada
	 * 
	 * @param pagina {@link String} direccion de la pagina a ingresar
	 */
	public void rediccionar(String pagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que nos permite verificar si las credenciales ingresadas por el
	 * usuario tienen coincidencias
	 * 
	 * @return {@link Persona} persona registrada con la base de datos que coincide
	 *         con las credenciales ingresadas
	 */
	public Persona coincidenCredenciales() {
		if (this.contador != 3) {
			for (int i = 0; i < personas.size(); i++) {

				if (personas.get(i).getCorreo().equalsIgnoreCase(correo)
						&& personas.get(i).getClave().equals(password)) {
					return personas.get(i);
				} else if (personas.get(i).getCorreo().equalsIgnoreCase(correo)) {
					this.contador += 1;
					FacesContext.getCurrentInstance().addMessage("loginForm", new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Le quedan " + (3 - this.contador) + " intentos ", ""));

				}

			}
		}
		return null;

	}

	/**
	 * Metodo que nos permite crear una persona
	 */
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

	/**
	 * Metodo que nos permite saber si el correo que se ingresa en el registro ya
	 * existe
	 * 
	 * @return {@link Boolean} con el valor true si el usuario existe en la bd ,caso
	 *         contrario false
	 */
	public boolean existeUsuario() {

		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getCorreo().equals(persona.getCorreo())) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Metodo que sirve para saber si los dos campos de password coinciden
	 * 
	 * @return
	 */
	public boolean coincidenPasswords() {
		if (password.equals(persona.getClave())) {
			return true;
		}
		return false;
	}

	/**
	 * @return the contador
	 */
	public int getContador() {
		return contador;
	}

	/**
	 * @param contador the contador to set
	 */
	public void setContador(int contador) {
		this.contador = contador;
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

}