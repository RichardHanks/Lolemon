package lolemon.persistencia.modelo;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Item extends Movimiento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer incremento;
	private Tipo tipo;
	
	public Item() {
		
	}

	public Item(Integer incremento,Tipo tipo) {
		super();
		
		this.incremento = incremento;
		this.tipo= tipo;
	}


	public Integer getIncremento() {
		return incremento;
	}

	public void setIncremento(Integer incremento) {
		this.incremento = incremento;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	
	
	
}
