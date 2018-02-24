package lolemon.consultas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.objectdb.o.ITE;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import lolemon.persistencia.base.*;
import lolemon.persistencia.modelo.*;

/**
 * @author Richard
 */

public class Consultas {

	Conexion con = new Conexion();
	EntityManager em;

	public Consultas() {
		em = con.getem();
	}

	/**
	 * 
	 * @return Todos los personajes, bloqueados o no
	 */
	@SuppressWarnings("unchecked")
	public List<Personaje> getCampeones() {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT p FROM Personaje p", Personaje.class);
		List<Personaje> lista = query.getResultList();
		em.getTransaction().commit();
		return lista;
	}

	/**
	 * Devuelve una lista con los personajes desbloqueados de un usuario en
	 * concreto.
	 * 
	 * @param nombreUsuario
	 *            El nombre del usuario cuyos campeones se van a buscar
	 * @return Lista de personajes desbloqueados
	 */
	@SuppressWarnings("unchecked")
	public List<Personaje> getCampeonesDesbloqueados(String nombreUsuario) {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT s FROM Usuario p JOIN p.personajes s where p.nombre=:nombre",
				Usuario.class);
		query.setParameter("nombre", nombreUsuario);
		List<Personaje> lista = query.getResultList();
		em.getTransaction().commit();

		return lista;
	}

	/**
	 * Devuelve una lista con los personajes bloqueados de un usuario en concreto.
	 * 
	 * @param nombreUsuario
	 *            El nombre del usuario cuyos campeones se van a buscar
	 * @return Lista de personajes bloqueados
	 */
	@SuppressWarnings("unchecked")
	public List<Personaje> getCampeonesBloqueados(String nombreUsuario) {
		em.getTransaction().begin();
		// Todos los personajes
		Query query = em.createQuery("SELECT s FROM Personaje s", Personaje.class);
		List<Personaje> todos = query.getResultList();
		em.getTransaction().commit();
		// Personajes que tiene el usuario
		List<Personaje> enPosesion = getCampeonesDesbloqueados(nombreUsuario);
		// Personajes que le faltan
		todos.removeAll(enPosesion);

		return todos;

	}

	/**
	 * Inserta usuario en la base de datos
	 * 
	 * @param nombre
	 *            El nombre del usuario
	 * @param contraseña
	 *            La contraseña del usuario
	 * @return
	 */

	public boolean insertarUsuario(String nombre, String contraseña) {
		Usuario u = null;
		boolean registrado = false;
		ArrayList<Personaje> principales = new ArrayList<>();
		try {
			em.getTransaction().begin();
			u = new Usuario();
			u.setNombre(nombre);
			u.setContraseña(contraseña);
			u.setPuntos(6300);

			for (int i = 3; i <= 4; i++) {
				principales.add(em.find(Personaje.class, i));
			}
			
			Historial hist=new Historial();
			em.persist(hist);
			u.setHistorial(hist);
			Inventario inv = new Inventario();
			em.persist(inv);
			u.setInventario(inv);
			u.setPersonajes(principales);
			em.persist(u);
			em.getTransaction().commit();
			registrado = true;
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Registrarse");
			alert.setHeaderText("Error");
			alert.setContentText("Ya hay un usuario llamado asi en la base de datos");
			alert.showAndWait();
			registrado = false;
		} finally {
			if (u == null)
				em.getTransaction().commit();

		}
		return registrado;

	}

	/**
	 * Inserta un personaje en la base de datos
	 * 
	 * @param nombre
	 * @param aspecto
	 * @param habilidades
	 * @param vida
	 * @param energia
	 * @param velocidad
	 * @param defensa
	 * @param sprite
	 */
	public void insertarPersonaje(String nombre, String aspecto, ArrayList<Habilidad> habilidades, Integer vida,
			Integer energia, Integer velocidad, Integer defensa, String sprite, Usuario u) {
		em.getTransaction().begin();
		Personaje p = new Personaje();
		p.setNombre(nombre);
		p.setAspecto(aspecto);
		p.setHabilidades(habilidades);
		p.setVida(vida);
		p.setEnergia(energia);
		p.setVelocidad(velocidad);
		p.setDefensa(defensa);
		p.setSprite(sprite);
		em.persist(p);
		Usuario usuario = em.find(Usuario.class, u.getNombre());
		usuario.getPersonajes().add(p);
		em.merge(usuario);
		em.getTransaction().commit();

	}

	/**
	 * Devuelve el objeto de tipo usuario asociado a un nombre en concreto
	 * 
	 * @param nombre
	 *            nombre del usuario a buscar
	 * @return el usuario en cuestión
	 */
	public Usuario getUsuario(String nombre) {
		Usuario u = null;
		try {
			em.getTransaction().begin();
			u=em.find(Usuario.class, nombre);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (u == null)
				em.getTransaction().commit();
		}
		return u;

	}

	/**
	 * Borra un personaje de la base de datos, esta acción no se puede deshacer
	 * 
	 * @param p
	 *            El personaje a borrar.
	 */
	public void eliminarPersonaje(Personaje p) {
		em.getTransaction().begin();
		Personaje borrar = em.find(Personaje.class, p.getId());
		em.remove(borrar);
		em.getTransaction().commit();
	}

	/**
	 * Actualiza los datos de un personaje en la base de datos. Debe ser un
	 * personaje ya existente en la base de datos.
	 * 
	 * @param personaje
	 *            El objeto personaje a actualizar con los nuevos valores ya
	 *            guardados en el objeto. Se debera seleccionar el objeto ya
	 *            existente, modificar los datos de este y pasarlo a la funcion. En
	 *            su defecto se puede hacer un find del objeto, por su clave
	 *            primaria modificar los valores, y llamar a esta funcion para
	 *            actualizarlos.
	 */
	public void editarPersonaje(Personaje personaje) {
		em.getTransaction().begin();
		Personaje u = em.find(Personaje.class, personaje.getId());
		u.setNombre(personaje.getNombre());
		u.setAspecto(personaje.getAspecto());
		u.setDefensa(personaje.getDefensa());
		u.setEnergia(personaje.getEnergia());
		u.setEstaBloqueado(personaje.EstaBloqueado());
		u.setHabilidades((ArrayList<Habilidad>) personaje.getHabilidades());
		u.setRecargo(personaje.getRecargo());
		u.setSprite(personaje.getSprite());
		u.setVelocidad(personaje.getVelocidad());
		u.setVida(personaje.getVida());

		em.merge(u);
		em.getTransaction().commit();

	}

	/**
	 * 
	 * @param numHabilidad
	 *            El numero de habilidad a buscar
	 * @return La lista de habilidades de un numero de habilidad en concreto
	 */
	@SuppressWarnings("unchecked")
	public List<Habilidad> getHabilidades(int numHabilidad) {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT h from Habilidad h where h.numHabilidad=:numHabilidad", Habilidad.class);
		query.setParameter("numHabilidad", numHabilidad);

		List<Habilidad> u = query.getResultList();
		em.getTransaction().commit();
		return u;
	}

	public void insertarPersonaje(Personaje p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}

	public Usuario comprarPersonaje(Usuario u, Personaje p) {
		em.getTransaction().begin();
		Personaje pe = em.find(Personaje.class, p.getId());
		Usuario us = em.find(Usuario.class, u.getNombre());
		us.setPuntos(us.getPuntos() - pe.getCoste());
		us.getPersonajes().add(pe);
		Usuario user=em.merge(us);
		em.getTransaction().commit();
		return user;
	}

	
	public Usuario comprarItem(Usuario u, Item i) {
		em.getTransaction().begin();
		Item it = em.find(Item.class, i.getNombre());
		Usuario us = em.find(Usuario.class, u.getNombre());
		switch (i.getTipo()) {
		case VIDA:
			if(us.getInventario().getPocionesList().size()<10) {
			Item item=it;
			us.getInventario().getPocionesList().add(item);
			us.setPuntos(u.getPuntos()-it.getCoste());
			System.out.println(us.getInventario().getPocionesList().size());
			}
			break;
		case ENERGIA:
			if(us.getInventario().getElixiresList().size()<10) {
			us.getInventario().getElixiresList().add(it);
			us.setPuntos(u.getPuntos()-it.getCoste());
			}
			break;
		case DEFENSA:
			if(us.getInventario().getVialesList().size()<10) {
			us.getInventario().getVialesList().add(it);
			us.setPuntos(u.getPuntos()-it.getCoste());
			}
			break;
		default:
			System.out.println("no se reconoce el item");
			break;
		}
		Usuario user=em.merge(us);
		em.getTransaction().commit();
		return user;
	}

	public void actualizarUsuario(Usuario u) {
		em.getTransaction().begin();
		Usuario us = em.find(Usuario.class, u.getNombre());
		Historial his=em.find(Historial.class, u.getHistorial().getId());
		his.setNumeroPartidas(u.getHistorial().getNumeroPartidas());
		his.setNumeroVictorias(u.getHistorial().getNumeroVictorias());
		Inventario in= em.find(Inventario.class, u.getInventario().getInventario());
		in.setPocionesList(u.getInventario().getPocionesList());
		in.setVialesList(u.getInventario().getVialesList());
		in.setElixiresList(u.getInventario().getElixiresList());
		em.merge(his);
		em.merge(in);
		us.setPuntos(u.getPuntos());
		us.setHistorial(his);
		u.setInventario(in);
		em.merge(us);
		em.getTransaction().commit();
	}

	public Item getPocion(){
		em.getTransaction().begin();
		Item pocion = new Item(20, Tipo.VIDA);
		pocion.setCoste(50);
		pocion.setNombre("pocionnnn"+String.valueOf(Math.random()+100));
		em.persist(pocion);
		em.getTransaction().commit();
		return pocion;
	}
	
	public Item getVial(){
		em.getTransaction().begin();
		Item vial = new Item(20, Tipo.DEFENSA);
		vial.setCoste(50);
		vial.setNombre("pocionnnn"+String.valueOf(Math.random()+100));
		em.persist(vial);
		em.getTransaction().commit();
		return vial;
	}
	
	public Item getElixir(){
		em.getTransaction().begin();
		Item elixir = new Item(20, Tipo.ENERGIA);
		elixir.setCoste(50);
		elixir.setNombre("pocionnnn"+String.valueOf(Math.random()+100));
		em.persist(elixir);
		em.getTransaction().commit();
		return elixir;
	}
	public EntityManager getEm() {
		return em;
	}
}
