package lolemon.persistencia.base;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Habilidad;
import lolemon.persistencia.modelo.Inventario;
import lolemon.persistencia.modelo.Item;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Tipo;
import lolemon.persistencia.modelo.TiposRobosVida;
import lolemon.persistencia.modelo.Usuario;

public class Conexion {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public Conexion() {
		emf = Persistence.createEntityManagerFactory("$objectdb/db/lolemon.odb");
		em = emf.createEntityManager();

	}

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("$objectdb/db/lolemon.odb");
		em = emf.createEntityManager();
		crear();
	}

	public EntityManager getem() {
		return em;

	}

	public static void crear() {
			em.getTransaction().begin();
			// Pociones
			Item poVida = new Item(20, Tipo.VIDA);
			poVida.setNombre("PocionVida");
			Item poEnergia = new Item(20, Tipo.ENERGIA);
			poEnergia.setNombre("PocionEnergia");
			Item poDefensa = new Item(20, Tipo.DEFENSA);
			poDefensa.setNombre("PocionDefensa");

			Inventario i = new Inventario();

			// Usuarios
			Usuario u = new Usuario();
			u.setNombre("richard");
			u.setContrase�a("2018");
			u.setPuntos(10000);
			u.setInventario(i);

			// Personajes
			Personaje ahri = new Personaje();
			ahri.setNombre("Ahri");
			ahri.setCoste(2000);
			ahri.setVida(450);
			ahri.setEnergia(120);
			ahri.setDefensa(25);
			ahri.setRecargo(0.10);
			ahri.setVelocidad(75);
			ahri.setVidaTotal(450);
			ahri.setEnergiaTotal(120);
			ahri.setMensajes(new ArrayList<String>());
			ahri.setAspecto("/54/ahri/splash/ahri.jpg");
			ahri.setSprite("54/ahri/sprite/ahri.png");

			// habilidades ligadas al personaje
			Habilidad ahri1 = new Habilidad();
			ahri1.setNumHabilidad(0);
			ahri1.setNombre("Orbe del enga�o");
			ahri1.setDescripcion("Ahri lanza y recupera su orbe, infligiendo da�o verdadero al recuperarlo.");
			ahri1.setDa�o(60);
			ahri1.setCoste(35);
			ahri1.setCritico(20);
			ahri1.setDmgverdadero(true);
			ahri1.setRobovida(false);
			ahri1.setPrecision(85);

			Habilidad ahri2 = new Habilidad();
			ahri2.setNumHabilidad(1);
			ahri2.setNombre("Fuego zorruno");
			ahri2.setDescripcion("Ahri lanza tres fuegos zorrunos que fijan y \n atacan a los enemigos cercanos");
			ahri2.setDa�o(55);
			ahri2.setCoste(15);
			ahri2.setCritico(30);
			ahri2.setDmgverdadero(false);
			ahri2.setRobovida(false);
			ahri2.setPrecision(100);

			Habilidad ahri3 = new Habilidad();
			ahri3.setNumHabilidad(2);
			ahri3.setNombre("Hechizar");
			ahri3.setDescripcion("Ahri lanza un beso que da�a y hechiza al \n enemigo al que alcance primero");
			ahri3.setDa�o(45);
			ahri3.setCoste(40);
			ahri3.setCritico(0);
			ahri3.setDmgverdadero(false);
			ahri3.setRobovida(true);
			ahri3.setTipoRobovida(TiposRobosVida.VidaEnemigaMaxima);
			ahri3.setPorcentajeRV(0.05);
			ahri3.setPrecision(100);

			Habilidad ahri4 = new Habilidad();
			ahri4.setNumHabilidad(3);
			ahri4.setNombre("Impulso espiritual");
			ahri4.setDescripcion(
					"Ahri corre hacia adelante y dispara rayos de esencia,\n infligiendo da�o a enemigos cercanos.");
			ahri4.setDa�o(300);
			ahri4.setCoste(85);
			ahri4.setCritico(0);
			ahri4.setDmgverdadero(false);
			ahri4.setRobovida(false);
			ahri4.setPrecision(85);

			ArrayList<Habilidad> hs = new ArrayList<>();
			hs.add(ahri1);
			hs.add(ahri2);
			hs.add(ahri3);
			hs.add(ahri4);

			ahri.setHabilidades(hs);

			Personaje brand = new Personaje();
			brand.setNombre("Brand");
			brand.setVida(450);
			brand.setEnergia(150);
			brand.setDefensa(20);
			brand.setRecargo(0.08);
			brand.setVelocidad(70);
			brand.setVidaTotal(450);
			brand.setEnergiaTotal(150);
			brand.setMensajes(new ArrayList<String>());
			brand.setAspecto("/54/brand/splash/brand.jpg");
			brand.setSprite("/54/brand/sprite/brand.png");
			brand.setCoste(1);
			brand.setEstaBloqueado(true);

			// habilidades ligadas al personaje
			Habilidad brand1 = new Habilidad();
			brand1.setNumHabilidad(0);
			brand1.setNombre("Abrasar");
			brand1.setDescripcion("Brand lanza una bola de fuego que inflige da�o m�gico");
			brand1.setDa�o(80);
			brand1.setCoste(40);
			brand1.setCritico(0);
			brand1.setDmgverdadero(false);
			brand1.setRobovida(false);
			brand1.setPrecision(70);

			Habilidad brand2 = new Habilidad();
			brand2.setNumHabilidad(1);
			brand2.setNombre("Pilar de llamas");
			brand2.setDescripcion(
					"Brand crea un Pilar de llamas en la ubicaci�n \n del objetivo que inflige da�o m�gico a las unidades enemigas");
			brand2.setDa�o(70);
			brand2.setCoste(35);
			brand2.setCritico(15);
			brand2.setDmgverdadero(false);
			brand2.setRobovida(false);
			brand2.setPrecision(70);

			Habilidad brand3 = new Habilidad();
			brand3.setNumHabilidad(2);
			brand3.setNombre("Incendio");
			brand3.setDescripcion("Brand lanza un ataque poderoso a su objetivo y le inflige da�o m�gico.");
			brand3.setDa�o(55);
			brand3.setCoste(10);
			brand3.setCritico(20);
			brand3.setDmgverdadero(false);
			brand3.setRobovida(false);
			brand3.setPrecision(85);

			Habilidad brand4 = new Habilidad();
			brand4.setNumHabilidad(3);
			brand4.setNombre("Detonaci�n �gnea");
			brand4.setDescripcion("Brand libera un devastador torrente de fuego que inflige da�o m�gico.");
			brand4.setDa�o(250);
			brand4.setCoste(100);
			brand4.setCritico(350);
			brand4.setDmgverdadero(true);
			brand4.setRobovida(false);
			brand4.setPrecision(50);

			ArrayList<Habilidad> hb = new ArrayList<>();
			hb.add(brand1);
			hb.add(brand2);
			hb.add(brand3);
			hb.add(brand4);

			brand.setHabilidades(hb);

			Personaje darius = new Personaje();
			darius.setNombre("Darius");
			darius.setVida(560);
			darius.setEnergia(100);
			darius.setDefensa(35);
			darius.setRecargo(0.12);
			darius.setVelocidad(68);
			darius.setVidaTotal(560);
			darius.setEnergiaTotal(100);
			darius.setMensajes(new ArrayList<String>());
			darius.setAspecto("/54/darius/splash/darius.jpg");
			darius.setSprite("/54/darius/sprite/darius.png");
			darius.setEstaBloqueado(false);

			// habilidades ligadas al personaje
			Habilidad darius1 = new Habilidad();
			darius1.setNumHabilidad(0);
			darius1.setNombre("Diezmar");
			darius1.setDescripcion(
					"Darius re�ne fuerzas y traza un amplio c�rculo con su hacha.\nDarius se cura con esta habilidad");
			darius1.setDa�o(70);
			darius1.setCoste(40);
			darius1.setCritico(0);
			darius1.setDmgverdadero(false);
			darius1.setRobovida(true);
			darius1.setTipoRobovida(TiposRobosVida.VidaFaltantePropia);
			darius1.setPorcentajeRV(0.05);
			darius1.setPrecision(70);

			Habilidad darius2 = new Habilidad();
			darius2.setNumHabilidad(1);
			darius2.setNombre("Golpe atroz");
			darius2.setDescripcion("El siguiente ataque de Darius alcanza al enemigo en una arteria vital.");
			darius2.setDa�o(55);
			darius2.setCoste(20);
			darius2.setCritico(15);
			darius2.setDmgverdadero(false);
			darius2.setRobovida(false);
			darius2.setPrecision(100);

			Habilidad darius3 = new Habilidad();
			darius3.setNumHabilidad(2);
			darius3.setNombre("Atrapar");
			darius3.setDescripcion("Brand lanza un ataque poderoso a su objetivo y le inflige da�o m�gico.");
			darius3.setDa�o(50);
			darius3.setCoste(20);
			darius3.setCritico(0);
			darius3.setDmgverdadero(true);
			darius3.setRobovida(false);
			darius3.setPrecision(80);

			Habilidad darius4 = new Habilidad();
			darius4.setNumHabilidad(3);
			darius4.setNombre("hab4");
			darius4.setDescripcion(
					"Darius pone a punto su hacha, lo cual provoca que, de forma pasiva,\nsu da�o f�sico ignore un porcentaje de la armadura de su objetivo");
			darius4.setDa�o(150);
			darius4.setCoste(50);
			darius4.setCritico(150);
			darius4.setDmgverdadero(false);
			darius4.setRobovida(true);
			darius4.setTipoRobovida(TiposRobosVida.VidaFaltantePropia);
			darius4.setPorcentajeRV(0.5);
			darius4.setPrecision(80);

			ArrayList<Habilidad> hdarius = new ArrayList<>();
			hdarius.add(darius1);
			hdarius.add(darius2);
			hdarius.add(darius3);
			hdarius.add(darius4);

			darius.setHabilidades(hdarius);

			// u.getPersonajes().add(ahri);
			u.getPersonajes().add(brand);
			u.getPersonajes().add(darius);

			u.getInventario().getPocionesList().add(poVida);
			u.getInventario().getElixiresList().add(poEnergia);
			u.getInventario().getVialesList().add(poDefensa);

			em.persist(poDefensa);
			em.persist(poEnergia);
			em.persist(poVida);
			em.persist(i);
			em.persist(u);

			em.persist(ahri1);
			em.persist(ahri2);
			em.persist(ahri3);
			em.persist(ahri4);
			em.persist(ahri);

			em.persist(brand1);
			em.persist(brand2);
			em.persist(brand3);
			em.persist(brand4);
			em.persist(brand);

			em.persist(darius1);
			em.persist(darius2);
			em.persist(darius3);
			em.persist(darius4);
			em.persist(darius);

			em.getTransaction().commit();
		
	}
}
