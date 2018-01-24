package lolemon.logicaDeNegocio.Clases;

import lolemon.persistencia.modelo.Item;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Usuario;

public class Compra {
	
	private static Usuario usuario;
	
	
	public Compra(Usuario usuario, Object compra) {
		super();
		this.usuario = usuario;
		
	}
	
	
	public static void comprarItem(Item objeto) {
		
//		if(usuario.getPuntos()>objeto.getCoste()) {
//			if(usuario.getInventario().get)
//		usuario.setPuntos(usuario.getPuntos()-objeto.getCoste());
//		}
//		else {
//			System.out.println("No tienes dinero para comprar este item");
//		}
	}
	
	public static void comprarPersonaje(Personaje personaje) {
		if(usuario.getPuntos()>personaje.getCoste()) {
		usuario.setPuntos(usuario.getPuntos()-personaje.getCoste());
		personaje.setEstaBloqueado(false);
		}
		else {
			System.out.println("No tienes dinero para comprar este personaje");
		}
	}
	
	
	

}
