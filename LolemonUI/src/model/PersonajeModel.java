package model;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lolemon.persistencia.modelo.Habilidad;
import lolemon.persistencia.modelo.Personaje;

public class PersonajeModel {

	private IntegerProperty Id;
	private StringProperty nombre;
	private StringProperty aspecto;
	private ListProperty<Habilidad> habilidades;
	private IntegerProperty vida;
	private IntegerProperty vidaTotal;
	private IntegerProperty energiaTotal;
	private IntegerProperty energia;
	private IntegerProperty velocidad;
	private IntegerProperty defensa;
	private DoubleProperty recargo;
	private StringProperty sprite;
	private BooleanProperty estaBloqueado;
	private BooleanProperty creado;
	private ListProperty<String> mensajes;
    private IntegerProperty coste;
    private DoubleProperty proporcionvida;
    
    public PersonajeModel() {
    	Id= new SimpleIntegerProperty(this,"id");
    	nombre= new SimpleStringProperty(this,"nombre");
    	aspecto= new SimpleStringProperty(this,"aspecto");
    	habilidades= new SimpleListProperty(this,"habilidades",FXCollections.observableArrayList());
    	vida= new SimpleIntegerProperty(this,"vida");
    	vidaTotal= new SimpleIntegerProperty(this,"vidaTotal");
    	energia= new SimpleIntegerProperty(this,"energia");
    	energiaTotal= new SimpleIntegerProperty(this,"energiaTotal");
    	defensa= new SimpleIntegerProperty(this,"defensa");
    	velocidad= new SimpleIntegerProperty(this,"velocidad");
    	recargo= new SimpleDoubleProperty(this,"recargo");
    	sprite=new SimpleStringProperty(this,"sprite");
    	estaBloqueado=new SimpleBooleanProperty(this,"estaBloqueado");
    	creado= new SimpleBooleanProperty(this,"creado");
    	mensajes=new SimpleListProperty(this,"mensajes",FXCollections.observableArrayList());
    	coste= new SimpleIntegerProperty(this,"coste");
    	proporcionvida= new SimpleDoubleProperty(this,"proporcionvida");
    	}
    
    
    public PersonajeModel convertirAPersonajeModel(Personaje p) {
    	PersonajeModel personaje= new PersonajeModel();
    	personaje.setId(p.getId());
    	personaje.setAspecto(p.getAspecto());
    	personaje.setCoste(p.getCoste());
    	personaje.setCreado(p.isCreado());
    	personaje.setDefensa(p.getDefensa());
    	personaje.setEnergia(p.getEnergia());
    	personaje.setEnergiaTotal(p.getEnergiaTotal());
    	personaje.setEstaBloqueado(p.isEstaBloqueado());
    	personaje.setVida(p.getVida());
    	personaje.setVidaTotal(p.getVidaTotal());
    	personaje.setVelocidad(p.getVelocidad());
    	personaje.setNombre(p.getNombre());
    	personaje.setSprite(p.getSprite());
    	personaje.setRecargo(p.getRecargo());
    	//personaje.setMensajes(FXCollections.observableArrayList(p.getMensajes()));
    	personaje.setHabilidades(FXCollections.observableArrayList(p.getHabilidades()));
    	
    	return personaje;
    	
    }

	public final IntegerProperty IdProperty() {
		return this.Id;
	}
	

	public final int getId() {
		return this.IdProperty().get();
	}
	

	public final void setId(final int Id) {
		this.IdProperty().set(Id);
	}
	

	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public final String getNombre() {
		return this.nombreProperty().get();
	}
	

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public final StringProperty aspectoProperty() {
		return this.aspecto;
	}
	

	public final String getAspecto() {
		return this.aspectoProperty().get();
	}
	

	public final void setAspecto(final String aspecto) {
		this.aspectoProperty().set(aspecto);
	}
	

	public final ListProperty<Habilidad> habilidadesProperty() {
		return this.habilidades;
	}
	

	public final ObservableList<Habilidad> getHabilidades() {
		return this.habilidadesProperty().get();
	}
	

	public final void setHabilidades(final ObservableList<Habilidad> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}
	

	public final IntegerProperty vidaProperty() {
		return this.vida;
	}
	

	public final int getVida() {
		return this.vidaProperty().get();
	}
	

	public final void setVida(final int vida) {
		this.vidaProperty().set(vida);
	}
	

	public final IntegerProperty vidaTotalProperty() {
		return this.vidaTotal;
	}
	

	public final int getVidaTotal() {
		return this.vidaTotalProperty().get();
	}
	

	public final void setVidaTotal(final int vidaTotal) {
		this.vidaTotalProperty().set(vidaTotal);
	}
	

	public final IntegerProperty energiaTotalProperty() {
		return this.energiaTotal;
	}
	

	public final int getEnergiaTotal() {
		return this.energiaTotalProperty().get();
	}
	

	public final void setEnergiaTotal(final int energiaTotal) {
		this.energiaTotalProperty().set(energiaTotal);
	}
	

	public final IntegerProperty energiaProperty() {
		return this.energia;
	}
	

	public final int getEnergia() {
		return this.energiaProperty().get();
	}
	

	public final void setEnergia(final int energia) {
		this.energiaProperty().set(energia);
	}
	

	public final IntegerProperty velocidadProperty() {
		return this.velocidad;
	}
	

	public final int getVelocidad() {
		return this.velocidadProperty().get();
	}
	

	public final void setVelocidad(final int velocidad) {
		this.velocidadProperty().set(velocidad);
	}
	

	public final IntegerProperty defensaProperty() {
		return this.defensa;
	}
	

	public final int getDefensa() {
		return this.defensaProperty().get();
	}
	

	public final void setDefensa(final int defensa) {
		this.defensaProperty().set(defensa);
	}
	

	public final DoubleProperty recargoProperty() {
		return this.recargo;
	}
	

	public final double getRecargo() {
		return this.recargoProperty().get();
	}
	

	public final void setRecargo(final double recargo) {
		this.recargoProperty().set(recargo);
	}
	

	public final StringProperty spriteProperty() {
		return this.sprite;
	}
	

	public final String getSprite() {
		return this.spriteProperty().get();
	}
	

	public final void setSprite(final String sprite) {
		this.spriteProperty().set(sprite);
	}
	

	public final BooleanProperty estaBloqueadoProperty() {
		return this.estaBloqueado;
	}
	

	public final boolean isEstaBloqueado() {
		return this.estaBloqueadoProperty().get();
	}
	

	public final void setEstaBloqueado(final boolean estaBloqueado) {
		this.estaBloqueadoProperty().set(estaBloqueado);
	}
	

	public final BooleanProperty creadoProperty() {
		return this.creado;
	}
	

	public final boolean isCreado() {
		return this.creadoProperty().get();
	}
	

	public final void setCreado(final boolean creado) {
		this.creadoProperty().set(creado);
	}
	

	public final ListProperty<String> mensajesProperty() {
		return this.mensajes;
	}
	

	public final ObservableList<String> getMensajes() {
		return this.mensajesProperty().get();
	}
	

	public final void setMensajes(final ObservableList<String> mensajes) {
		this.mensajesProperty().set(mensajes);
	}
	

	public final IntegerProperty costeProperty() {
		return this.coste;
	}
	

	public final int getCoste() {
		return this.costeProperty().get();
	}
	

	public final void setCoste(final int coste) {
		this.costeProperty().set(coste);
	}
	
    public static Personaje copiarPersonaje(Personaje p) {
    	Personaje personaje= new Personaje();
    	personaje.setId(p.getId());
    	personaje.setAspecto(p.getAspecto());
    	personaje.setCoste(p.getCoste());
    	personaje.setCreado(p.isCreado());
    	personaje.setDefensa(p.getDefensa());
    	personaje.setEnergia(p.getEnergia());
    	personaje.setEnergiaTotal(p.getEnergiaTotal());
    	personaje.setEstaBloqueado(p.isEstaBloqueado());
    	personaje.setVida(p.getVida());
    	personaje.setVidaTotal(p.getVidaTotal());
    	personaje.setVelocidad(p.getVelocidad());
    	personaje.setNombre(p.getNombre());
    	personaje.setSprite(p.getSprite());
    	personaje.setRecargo(p.getRecargo());
    	//personaje.setMensajes(FXCollections.observableArrayList(p.getMensajes()));
    	personaje.setHabilidades((ArrayList<Habilidad>) p.getHabilidades());
    	
    	return personaje;
    	
    }
    @Override
    public String toString() {
    	return getNombre()+"\nVida Base: "+getVidaTotal()+
    			"\nEnergía Base: "+getEnergiaTotal()+
    			"\nVelocidad Base: "+getVelocidad()+
    			"\nDefensa Base"+getDefensa();
    }
    
    

}
