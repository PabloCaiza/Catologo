package com.marcos.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.marcos.dao.ServicioUsuario;

import com.marcos.dto.Usuario;

@Named("usuarioB")
@RequestScoped
public class UsuarioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	@Inject
	private ServicioUsuario servicio;
	@PostConstruct
	

	public void init() {
		usuario=new Usuario();
	}
	
	public void crearUsuario() {
		try {
			System.out.println(usuario.getNombre()+usuario.getCorreo()+usuario.getClave());
			servicio.crear(usuario);
			
		}catch(Exception e) {
			
		}finally {
			System.out.print(usuario.getNombre());
			usuario=new Usuario();
			System.out.println(" ");
			System.out.print(usuario.getNombre());
			
		}
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
