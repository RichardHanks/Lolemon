package lolemon.logicaDeNegocio.Clases;

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
	
	
	public void comprarItem(Item objeto) {
		
		if(usuario.getPuntos()>objeto.getCoste()) {
	    	if(objeto.getTipo()==Tipo.VIDA) {
	    		if(usuario.getInventario().getPocionesList().size()<10) {
	    			usuario.getInventario().getPocionesList().add(objeto);
	    			usuario.setPuntos(usuario.getPuntos()-objeto.getCoste());
	    			
	    			Consultas consulta= new Consultas();
	    			consulta.editarUsuario(usuario);
	    			}
	    		else {
	    			System.out.println("No tienes espacio para comprar pociones");
	    		}
	    	}
	    	else if(objeto.getTipo()==Tipo.ENERGIA) {
	    		if(usuario.getInventario().getElixiresList().size()<10) {
	    			usuario.getInventario().getElixiresList().add(objeto);
	    			usuario.setPuntos(usuario.getPuntos()-objeto.getCoste());
	    			
	    			Consultas consulta= new Consultas();
	    			consulta.editarUsuario(usuario);
	    		}
	    		else {
	    			System.out.println("No tienes espacio para comprar Elixires");
	    		}
	    		
	    	}
	    	else {
	    		if(usuario.getInventario().getVialesList().size()<10) {
	    			usuario.getInventario().getVialesList().add(objeto);
	    			usuario.setPuntos(usuario.getPuntos()-objeto.getCoste());
	    			
	    			Consultas consulta= new Consultas();
	    			consulta.editarUsuario(usuario);
	    		}
	    		else {
	    			System.out.println("No tienes espacio para comprar Viales");
	    		}
	    		
	    	}
	    }
	    else {
	    	System.out.println("No tienes dinero suficiente para comprar");
	    }
	}
	
	public void comprarPersonaje(Personaje personaje) {
		if(usuario.getPuntos()>personaje.getCoste()) {
			usuario.setPuntos(usuario.getPuntos()-personaje.getCoste());
			
			Consultas consulta= new Consultas();
			usuario.getPersonajes().get(personaje.getId()-1).setEstaBloqueado(false);
			
			consulta.editarUsuario(usuario);
			
//			Usuario u=consulta.getUsuario(usuario.getNombre());
//			u.getPersonajes().get(0).EstaBloqueado();
			
			//Hay que probar si la función que edita el usuario funciona correctamente y como nosotros queremos.
			}
			else {
				System.out.println("No tienes dinero para comprar este personaje");
			}
	}
	
	
	

}
