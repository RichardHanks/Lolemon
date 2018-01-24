package model;



import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lolemon.persistencia.modelo.Historial;
import lolemon.persistencia.modelo.Inventario;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Usuario;

public class UsuarioModel{
	private StringProperty nombre;
	private StringProperty contraseña;
	private ListProperty<Personaje> personajes;
	private IntegerProperty puntos;
	private ObjectProperty<Historial> historial;
	private ObjectProperty<Inventario> Inventario;
	
	public UsuarioModel() {
		nombre = new SimpleStringProperty(this, "nombre");
		contraseña = new SimpleStringProperty(this, "contraseña");
		personajes = new SimpleListProperty<>(this, "personajes", FXCollections.observableArrayList());
		puntos = new SimpleIntegerProperty(this, "puntos");
		historial = new SimpleObjectProperty<>(this, "historial");
		Inventario = new SimpleObjectProperty<>(this, "inventario");
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
	

	public final StringProperty contraseñaProperty() {
		return this.contraseña;
	}
	

	public final String getContraseña() {
		return this.contraseñaProperty().get();
	}
	

	public final void setContraseña(final String contraseña) {
		this.contraseñaProperty().set(contraseña);
	}
	

	public final ListProperty<Personaje> personajesProperty() {
		return this.personajes;
	}
	

	public final ObservableList<Personaje> getPersonajes() {
		return this.personajesProperty().get();
	}
	

	public final void setPersonajes(final ObservableList<Personaje> personajes) {
		this.personajesProperty().set(personajes);
	}
	

	public final IntegerProperty puntosProperty() {
		return this.puntos;
	}
	

	public final Integer getPuntos() {
		return this.puntosProperty().get();
	}
	

	public final void setPuntos(final int puntos) {
		this.puntosProperty().set(puntos);
	}
	

	public final ObjectProperty<Historial> historialProperty() {
		return this.historial;
	}
	

	public final Historial getHistorial() {
		return this.historialProperty().get();
	}
	

	public final void setHistorial(final Historial historial) {
		this.historialProperty().set(historial);
	}
	

	public final ObjectProperty<Inventario> InventarioProperty() {
		return this.Inventario;
	}
	

	public final Inventario getInventario() {
		return this.InventarioProperty().get();
	}
	

	public final void setInventario(final Inventario Inventario) {
		this.InventarioProperty().set(Inventario);
	}
	
	
	
}
