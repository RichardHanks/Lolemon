package lolemon.persistencia.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movimiento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descripcion;
	@Id
	private String nombre;
	private String icono;
	
	public Movimiento() {
		
	}

	public Movimiento(String descripcion, String nombre, String icono) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.icono = icono;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setIcono(String icono) {
		this.icono = icono;
	}
	
	public String getIcono() {
		return icono;
	}
	
	
}
