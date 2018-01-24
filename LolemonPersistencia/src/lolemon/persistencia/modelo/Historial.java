package lolemon.persistencia.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Historial implements Serializable {

	
	@Id
	private int id;
	private int numeroPartidas;
	private int numeroVictorias;
	private Personaje personajeMasUsado;
	
	public Historial(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroPartidas() {
		return numeroPartidas;
	}

	public void setNumeroPartidas(int numeroPartidas) {
		this.numeroPartidas = numeroPartidas;
	}

	public int getNumeroVictorias() {
		return numeroVictorias;
	}

	public void setNumeroVictorias(int numeroVictorias) {
		this.numeroVictorias = numeroVictorias;
	}

	public Personaje getPersonajeMasUsado() {
		return personajeMasUsado;
	}

	public void setPersonajeMasUsado(Personaje personajeMasUsado) {
		this.personajeMasUsado = personajeMasUsado;
	}
	
	
	
	
}
