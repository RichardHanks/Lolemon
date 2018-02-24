package lolemon.logicaDeNegocio.Clases;

import java.util.ArrayList;

import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Item;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Tipo;
import lolemon.persistencia.modelo.Usuario;

public class Compra {
	
	private Usuario usuario;
	
	
	public Compra(Usuario usuario) {
		super();
		this.usuario = usuario;
		
	}
	
	public Usuario comprarItem(Item i) {
		Usuario u=null;
		if(usuario.getPuntos()>i.getCoste()) {
			Consultas consulta= new Consultas();
			u=consulta.comprarItem(usuario, i);
			
		}

		return u;
	}
	
	public Usuario comprarPersonaje(Personaje personaje) {
		Usuario u=null;
		if(usuario.getPuntos()>personaje.getCoste()) {
			Consultas consulta= new Consultas();
			u=consulta.comprarPersonaje(usuario, personaje);
			}
		return u;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
