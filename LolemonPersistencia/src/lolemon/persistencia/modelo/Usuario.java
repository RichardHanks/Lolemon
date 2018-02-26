package lolemon.persistencia.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String nombre;
	private String contraseña;
	private List<Personaje> personajes = new ArrayList<>();
	private List<RegistroPartida>  registroPartidas= new ArrayList<>();
	private Integer puntos;
	private Historial historial;
	private Inventario Inventario;
	
	public Usuario() {
		Inventario = new Inventario();
	}

	public Usuario(String nombre, String contraseña, ArrayList<Personaje> personajes, Integer puntos) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.personajes = personajes;
		this.puntos = puntos;
		Inventario = new Inventario();
		
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(ArrayList<Personaje> personajes) {
		this.personajes = personajes;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Historial getHistorial() {
		return historial;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}

	public Inventario getInventario() {
		return Inventario;
	}

	public void setInventario(Inventario inventario) {
		Inventario = inventario;
	}

	public List<RegistroPartida> getRegistroPartidas() {
		return registroPartidas;
	}

	public void setRegistroPartidas(List<RegistroPartida> registroPartidas) {
		this.registroPartidas = registroPartidas;
	}
	
	
	
	
}
