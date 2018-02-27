package reports;

import java.util.ArrayList;
import java.util.List;

import lolemon.persistencia.modelo.Personaje;

public class CampeonesCollection {

	private List<Personaje> personajesList= new ArrayList<>();
	
	public void addPersonaje(Personaje p) {
			personajesList.add(p);
	}
	
	public List<Personaje> getPersonajes() {
		return personajesList;
	}
}
