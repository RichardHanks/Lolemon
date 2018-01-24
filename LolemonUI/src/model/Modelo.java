package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lolemon.persistencia.modelo.Habilidad;

public class Modelo {

	private StringProperty nombre;
	private ListProperty<Habilidad> habilidades1;
	private ListProperty<Habilidad> habilidades2;
	private ListProperty<Habilidad> habilidades3;
	private ListProperty<Habilidad> habilidades4;
	
	public Modelo() {
		nombre = new SimpleStringProperty(this, "nombre");
		habilidades1 = new SimpleListProperty<>(this,"habilidades 1", FXCollections.observableArrayList());
		habilidades2 = new SimpleListProperty<>(this,"habilidades 2", FXCollections.observableArrayList());
		habilidades3 = new SimpleListProperty<>(this,"habilidades 3", FXCollections.observableArrayList());
		habilidades4 = new SimpleListProperty<>(this,"habilidades 4", FXCollections.observableArrayList());
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
	

	public final ListProperty<Habilidad> habilidades1Property() {
		return this.habilidades1;
	}
	

	public final ObservableList<Habilidad> getHabilidades1() {
		return this.habilidades1Property().get();
	}
	

	public final void setHabilidades1(final ObservableList<Habilidad> habilidades1) {
		this.habilidades1Property().set(habilidades1);
	}
	

	public final ListProperty<Habilidad> habilidades2Property() {
		return this.habilidades2;
	}
	

	public final ObservableList<Habilidad> getHabilidades2() {
		return this.habilidades2Property().get();
	}
	

	public final void setHabilidades2(final ObservableList<Habilidad> habilidades2) {
		this.habilidades2Property().set(habilidades2);
	}
	

	public final ListProperty<Habilidad> habilidades3Property() {
		return this.habilidades3;
	}
	

	public final ObservableList<Habilidad> getHabilidades3() {
		return this.habilidades3Property().get();
	}
	

	public final void setHabilidades3(final ObservableList<Habilidad> habilidades3) {
		this.habilidades3Property().set(habilidades3);
	}
	

	public final ListProperty<Habilidad> habilidades4Property() {
		return this.habilidades4;
	}
	

	public final ObservableList<Habilidad> getHabilidades4() {
		return this.habilidades4Property().get();
	}
	

	public final void setHabilidades4(final ObservableList<Habilidad> habilidades4) {
		this.habilidades4Property().set(habilidades4);
	}
	
	
	
}
