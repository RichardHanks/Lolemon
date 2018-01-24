package lolemon.persistencia.base;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Habilidad;
import lolemon.persistencia.modelo.Personaje;
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
	
	private static void crear() {
		 em.getTransaction().begin();
		 //Usuarios
		 Usuario u = new Usuario();
		 u.setNombre("richard");
		 u.setContraseña("2018");
		 u.setPuntos(6300);
	
		 
		 //Personajes
		 Personaje ahri = new Personaje();
		 ahri.setNombre("Ahri");
		 ahri.setVida(450);
		 ahri.setEnergia(120);
		 ahri.setDefensa(25);
		 ahri.setRecargo(0.10);
		 ahri.setVelocidad(75);
		 ahri.setAspecto("/54/ahri/splash/ahri.jpg");
		 ahri.setSprite("54/ahri/sprite/ahri.png");
		 
		 	//habilidades ligadas al personaje
		 	Habilidad ahri1 = new Habilidad();
		 	ahri1.setNumHabilidad(1);
		 	ahri1.setNombre("Orbe del engaño");
		 	ahri1.setDescripcion("Ahri lanza y recupera su orbe, infligiendo daño verdadero al recuperarlo.");
		 	ahri1.setDaño(60);
		 	ahri1.setCoste(35);
		 	ahri1.setCritico(20);
		 	ahri1.setDmgverdadero(true);
		 	ahri1.setRobovida(false);
		 	ahri1.setPrecision(85);
		 	
		 	Habilidad ahri2 = new Habilidad();
		 	ahri2.setNumHabilidad(2);
		 	ahri2.setNombre("Fuego zorruno");
		 	ahri2.setDescripcion("Ahri lanza tres fuegos zorrunos que fijan y \n atacan a los enemigos cercanos");
		 	ahri2.setDaño(55);
		 	ahri2.setCoste(15);
		 	ahri2.setCritico(30);
		 	ahri2.setDmgverdadero(false);
		 	ahri2.setRobovida(false);
		 	ahri2.setPrecision(100);
		 	
		 	Habilidad ahri3 = new Habilidad();
		 	ahri3.setNumHabilidad(3);
		 	ahri3.setNombre("Hechizar");
		 	ahri3.setDescripcion("Ahri lanza un beso que daña y hechiza al \n enemigo al que alcance primero");
		 	ahri3.setDaño(45);
		 	ahri3.setCoste(40);
		 	ahri3.setCritico(0);
		 	ahri3.setDmgverdadero(false);
		 	ahri3.setRobovida(true);
		 	ahri3.setTipoRobovida(TiposRobosVida.VidaEnemigaMaxima);
		 	ahri3.setPorcentajeRV(0.05);
		 	ahri3.setPrecision(100);
		 	
		 	Habilidad ahri4 = new Habilidad();
		 	ahri4.setNumHabilidad(4);
		 	ahri4.setNombre("Impulso espiritual");
		 	ahri4.setDescripcion("Ahri corre hacia adelante y dispara rayos de esencia,\n infligiendo daño a enemigos cercanos.");
		 	ahri4.setDaño(300);
		 	ahri4.setCoste(85);
		 	ahri4.setCritico(0);
		 	ahri4.setDmgverdadero(false);
		 	ahri4.setRobovida(false);
		 	ahri4.setPrecision(85);
		 	
		 	ArrayList<Habilidad>hs= new ArrayList<>();
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
		 	brand.setAspecto("/54/brand/splash/brand.jpg");
		 	brand.setSprite("/54/brand/sprite/brand.png");
		 	brand.setEstaBloqueado(true);
			 
			 	//habilidades ligadas al personaje
			 	Habilidad brand1 = new Habilidad();
			 	brand1.setNumHabilidad(1);
			 	brand1.setNombre("Abrasar");
			 	brand1.setDescripcion("Brand lanza una bola de fuego que inflige daño mágico");
			 	brand1.setDaño(80);
			 	brand1.setCoste(40);
			 	brand1.setCritico(0);
			 	brand1.setDmgverdadero(false);
			 	brand1.setRobovida(false);
			 	brand1.setPrecision(70);
			 	
			 	Habilidad brand2 = new Habilidad();
			 	brand2.setNumHabilidad(2);
			 	brand2.setNombre("Pilar de llamas");
			 	brand2.setDescripcion("Brand crea un Pilar de llamas en la ubicación \n del objetivo que inflige daño mágico a las unidades enemigas");
			 	brand2.setDaño(70);
			 	brand2.setCoste(35);
			 	brand2.setCritico(15);
			 	brand2.setDmgverdadero(false);
			 	brand2.setRobovida(false);
			 	brand2.setPrecision(70);
			 	
			 	Habilidad brand3 = new Habilidad();
			 	brand3.setNumHabilidad(3);
			 	brand3.setNombre("Incendio");
			 	brand3.setDescripcion("Brand lanza un ataque poderoso a su objetivo y le inflige daño mágico.");
			 	brand3.setDaño(55);
			 	brand3.setCoste(10);
			 	brand3.setCritico(20);
			 	brand3.setDmgverdadero(false);
			 	brand3.setRobovida(false);
			 	brand3.setPrecision(85);
			 	
			 	Habilidad brand4 = new Habilidad();
			 	brand4.setNumHabilidad(4);
			 	brand4.setNombre("Detonación ígnea");
			 	brand4.setDescripcion("Brand libera un devastador torrente de fuego que inflige daño mágico.");
			 	brand4.setDaño(250);
			 	brand4.setCoste(100);
			 	brand4.setCritico(350);
			 	brand4.setDmgverdadero(true);
			 	brand4.setRobovida(false);
			 	brand4.setPrecision(50);
			 	
			 	ArrayList<Habilidad>hb= new ArrayList<>();
			 	hb.add(brand1);
			 	hb.add(brand2);
			 	hb.add(brand3);
			 	hb.add(brand4);
			 	
			 	brand.setHabilidades(hb);
		
		u.getPersonajes().add(ahri);
		u.getPersonajes().add(brand);
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

		 
		 em.getTransaction().commit();
	}
}
