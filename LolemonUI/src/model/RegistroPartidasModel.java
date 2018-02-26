package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Resultado;

public class RegistroPartidasModel {
	
	private IntegerProperty id;
	private ObjectProperty<Personaje> personajeUsado;
	private ObjectProperty<Personaje> personajeContrario;
	private IntegerProperty duracion;
	private IntegerProperty puntosGanados;
	private ObjectProperty<Resultado> resultado;
	
	public RegistroPartidasModel() {
		id= new SimpleIntegerProperty();
		personajeContrario= new SimpleObjectProperty<>();
		personajeUsado= new SimpleObjectProperty<>();
		duracion= new SimpleIntegerProperty();
		puntosGanados= new SimpleIntegerProperty();
		resultado= new SimpleObjectProperty<>();
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	

	public final ObjectProperty<Personaje> personajeUsadoProperty() {
		return this.personajeUsado;
	}
	

	public final Personaje getPersonajeUsado() {
		return this.personajeUsadoProperty().get();
	}
	

	public final void setPersonajeUsado(final Personaje personajeUsado) {
		this.personajeUsadoProperty().set(personajeUsado);
	}
	

	public final ObjectProperty<Personaje> personajeContrarioProperty() {
		return this.personajeContrario;
	}
	

	public final Personaje getPersonajeContrario() {
		return this.personajeContrarioProperty().get();
	}
	

	public final void setPersonajeContrario(final Personaje personajeContrario) {
		this.personajeContrarioProperty().set(personajeContrario);
	}
	

	public final IntegerProperty duracionProperty() {
		return this.duracion;
	}
	

	public final int getDuracion() {
		return this.duracionProperty().get();
	}
	

	public final void setDuracion(final int duracion) {
		this.duracionProperty().set(duracion);
	}
	

	public final IntegerProperty puntosGanadosProperty() {
		return this.puntosGanados;
	}
	

	public final int getPuntosGanados() {
		return this.puntosGanadosProperty().get();
	}
	

	public final void setPuntosGanados(final int puntosGanados) {
		this.puntosGanadosProperty().set(puntosGanados);
	}
	

	public final ObjectProperty<Resultado> resultadoProperty() {
		return this.resultado;
	}
	

	public final Resultado getResultado() {
		return this.resultadoProperty().get();
	}
	

	public final void setResultado(final Resultado resultado) {
		this.resultadoProperty().set(resultado);
	}
	
	
	

}
