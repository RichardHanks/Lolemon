package lolemon.persistencia.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Personaje implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Integer Id;
	private String nombre;
	private String aspecto;
	private List<Habilidad> habilidades= new ArrayList<>();
	private Integer vida;
	private Integer vidaTotal;
	private Integer energiaTotal;
	private Integer energia;
	private Integer velocidad;
	private Integer defensa;
	private Double recargo;
	private String sprite;
	private boolean estaBloqueado;
	private boolean creado;
	private List<String> mensajes;
	private int coste;
	
	public Personaje() {
		
		
	}

	

	public Personaje(Integer id, String nombre, String aspecto, ArrayList<Habilidad> habilidades, Integer vida,
			Integer vidaTotal, Integer energiaTotal, Integer energia, Integer velocidad, Integer defensa,
			Double recargo, String sprite, boolean estaBloqueado, boolean creado) {
		super();
		Id = id;
		this.nombre = nombre;
		this.aspecto = aspecto;
		this.habilidades = habilidades;
		this.vida = vida;
		this.vidaTotal = vidaTotal;
		this.energiaTotal = energiaTotal;
		this.energia = energia;
		this.velocidad = velocidad;
		this.defensa = defensa;
		this.recargo = recargo;
		this.sprite = sprite;
		this.estaBloqueado = estaBloqueado;
		this.creado = creado;
		
		mensajes= new ArrayList<>();
	}



	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAspecto() {
		return aspecto;
	}

	public void setAspecto(String aspecto) {
		this.aspecto = aspecto;
	}

	public List<Habilidad> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(ArrayList<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}

	public Integer getVida() {
		return vida;
	}

	public void setVida(Integer vida) {
		this.vida = vida;
	}

	public Integer getEnergia() {
		return energia;
	}

	public void setEnergia(Integer energia) {
		this.energia = energia;
	}

	public Integer getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Integer velocidad) {
		this.velocidad = velocidad;
	}

	public Integer getDefensa() {
		return defensa;
	}

	public void setDefensa(Integer defensa) {
		this.defensa = defensa;
	}

	public Double getRecargo() {
		return recargo;
	}

	public void setRecargo(Double recargo) {
		this.recargo = recargo;
	}
	
	public String getSprite() {
		return sprite;
	}
	
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}
	
	public boolean EstaBloqueado() {
		return estaBloqueado;
	}

	public void setEstaBloqueado(boolean estaBloqueado) {
		this.estaBloqueado = estaBloqueado;
	}

	
	public boolean esCreado() {
		return creado;
	}

	public void setCreado(boolean creado) {
		this.creado = creado;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public Integer getVidaTotal() {
		return vidaTotal;
	}

	public void setVidaTotal(Integer vidaTotal) {
		this.vidaTotal = vidaTotal;
	}

	public Integer getEnergiaTotal() {
		return energiaTotal;
	}

	public void setEnergiaTotal(Integer energiaTotal) {
		this.energiaTotal = energiaTotal;
	}

	public boolean isEstaBloqueado() {
		return estaBloqueado;
	}

	public boolean isCreado() {
		return creado;
	}

	public List<String> getMensajes() {
		return mensajes;
	}



	public void setMensajes(ArrayList<String> mensajes) {
		this.mensajes = mensajes;
	}
	
	public void setCoste(int coste) {
		this.coste = coste;
	}
	
	public int getCoste() {
		return coste;
	}

	
}
