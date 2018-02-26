package lolemon.persistencia.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RegistroPartida implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	private Personaje personajeUsado;
	private Personaje personajeContrario;
	private Integer duracion;
	private Integer puntosGanados;
	private Resultado resultado;

	public RegistroPartida() {
		personajeUsado= new Personaje();
		personajeContrario= new Personaje();
		duracion=0;
		puntosGanados=0;
		resultado=Resultado.VICTORIA;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Personaje getPersonajeUsado() {
		return personajeUsado;
	}

	public void setPersonajeUsado(Personaje personajeUsado) {
		this.personajeUsado = personajeUsado;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getPuntosGanados() {
		return puntosGanados;
	}

	public void setPuntosGanados(int puntosGanados) {
		this.puntosGanados = puntosGanados;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public Personaje getPersonajeContrario() {
		return personajeContrario;
	}

	public void setPersonajeContrario(Personaje personajeContrario) {
		this.personajeContrario = personajeContrario;
	}
	
	
	
	
	

}
