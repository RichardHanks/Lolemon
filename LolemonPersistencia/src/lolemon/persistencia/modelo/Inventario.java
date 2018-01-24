package lolemon.persistencia.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inventario {
	
	@Id
	private int Inventario;
	
	private List<Item> PocionesList;
	private List<Item> ElixiresList;
	private List<Item> VialesList;
	
	public Inventario() {
		PocionesList= new ArrayList<Item>();
		ElixiresList= new ArrayList<Item>();
		VialesList= new ArrayList<Item>();
	}
	
	public int getInventario() {
		return Inventario;
	}

	public List<Item> getPocionesList() {
		return PocionesList;
	}

	public void setPocionesList(List<Item> pocionesList) {
		PocionesList = pocionesList;
	}

	public List<Item> getElixiresList() {
		return ElixiresList;
	}

	public void setElixiresList(List<Item> elixiresList) {
		ElixiresList = elixiresList;
	}

	public List<Item> getVialesList() {
		return VialesList;
	}

	public void setVialesList(List<Item> vialesList) {
		VialesList = vialesList;
	}

	public void setInventario(int inventario) {
		Inventario = inventario;
	}
	
	public int getNumPociones() {
		return PocionesList.size();
	}

	public int getNumElixires() {
		return ElixiresList.size();
	}
	
	public int getNumViales() {
		return VialesList.size();
	}
	
	

}
