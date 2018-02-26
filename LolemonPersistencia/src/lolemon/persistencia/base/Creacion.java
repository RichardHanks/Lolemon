package lolemon.persistencia.base;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import lolemon.persistencia.modelo.Habilidad;
import lolemon.persistencia.modelo.Historial;
import lolemon.persistencia.modelo.Inventario;
import lolemon.persistencia.modelo.Item;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Tipo;
import lolemon.persistencia.modelo.TiposRobosVida;
import lolemon.persistencia.modelo.Usuario;

public class Creacion {
	


	private static EntityManagerFactory emf;
	private static EntityManager em;

	public Creacion() {
		emf = Persistence.createEntityManagerFactory("$objectdb/db/lolemon.odb");
		em = emf.createEntityManager();
		if(existeDB()) System.out.println("La DB ya existe");
		else System.out.println("La DB no existe, se crea ahora.");

	}


	public EntityManager getem() {
		return em;

	}

	public void crear() {
		if(!existeDB()) {
			em.getTransaction().begin();
			// Pociones
			Item poVida = new Item(20, Tipo.VIDA);
			poVida.setNombre("PocionVida");
			poVida.setCoste(50);
			Item poEnergia = new Item(20, Tipo.ENERGIA);
			poEnergia.setNombre("PocionEnergia");
			poEnergia.setCoste(50);
			Item poDefensa = new Item(20, Tipo.DEFENSA);
			poDefensa.setNombre("PocionDefensa");
			poDefensa.setCoste(50);

			Inventario inventario = new Inventario();
			Historial historial = new Historial();

			// Usuarios
			Usuario u = new Usuario();
			u.setNombre("richard");
			u.setContraseña("2018");
			u.setPuntos(10000);
			u.setInventario(inventario);
			u.setHistorial(historial);

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
			ahri1.setNombre("Orbe del engaño");
			ahri1.setDescripcion("Ahri lanza y recupera su orbe, infligiendo daño verdadero al recuperarlo.");
			ahri1.setDaño(60);
			ahri1.setCoste(35);
			ahri1.setCritico(20);
			ahri1.setDmgverdadero(true);
			ahri1.setRobovida(false);
			ahri1.setPrecision(85);

			Habilidad ahri2 = new Habilidad();
			ahri2.setNumHabilidad(1);
			ahri2.setNombre("Fuego zorruno");
			ahri2.setDescripcion("Ahri lanza tres fuegos zorrunos que fijan y \n atacan a los enemigos cercanos");
			ahri2.setDaño(55);
			ahri2.setCoste(15);
			ahri2.setCritico(30);
			ahri2.setDmgverdadero(false);
			ahri2.setRobovida(false);
			ahri2.setPrecision(100);

			Habilidad ahri3 = new Habilidad();
			ahri3.setNumHabilidad(2);
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
			ahri4.setNumHabilidad(3);
			ahri4.setNombre("Impulso espiritual");
			ahri4.setDescripcion(
					"Ahri corre hacia adelante y dispara rayos de esencia,\n infligiendo daño a enemigos cercanos.");
			ahri4.setDaño(300);
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
			brand1.setDescripcion("Brand lanza una bola de fuego que inflige daño mágico");
			brand1.setDaño(80);
			brand1.setCoste(40);
			brand1.setCritico(0);
			brand1.setDmgverdadero(false);
			brand1.setRobovida(false);
			brand1.setPrecision(70);

			Habilidad brand2 = new Habilidad();
			brand2.setNumHabilidad(1);
			brand2.setNombre("Pilar de llamas");
			brand2.setDescripcion(
					"Brand crea un Pilar de llamas en la ubicación \n del objetivo que inflige daño mágico a las unidades enemigas");
			brand2.setDaño(70);
			brand2.setCoste(35);
			brand2.setCritico(15);
			brand2.setDmgverdadero(false);
			brand2.setRobovida(false);
			brand2.setPrecision(70);

			Habilidad brand3 = new Habilidad();
			brand3.setNumHabilidad(2);
			brand3.setNombre("Incendio");
			brand3.setDescripcion("Brand lanza un ataque poderoso a su objetivo y le inflige daño mágico.");
			brand3.setDaño(55);
			brand3.setCoste(10);
			brand3.setCritico(20);
			brand3.setDmgverdadero(false);
			brand3.setRobovida(false);
			brand3.setPrecision(85);

			Habilidad brand4 = new Habilidad();
			brand4.setNumHabilidad(3);
			brand4.setNombre("Detonación ígnea");
			brand4.setDescripcion("Brand libera un devastador torrente de fuego que inflige daño mágico.");
			brand4.setDaño(250);
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
					"Darius reúne fuerzas y traza un amplio círculo con su hacha.\nDarius se cura con esta habilidad");
			darius1.setDaño(70);
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
			darius2.setDaño(55);
			darius2.setCoste(20);
			darius2.setCritico(15);
			darius2.setDmgverdadero(false);
			darius2.setRobovida(false);
			darius2.setPrecision(100);

			Habilidad darius3 = new Habilidad();
			darius3.setNumHabilidad(2);
			darius3.setNombre("Atrapar");
			darius3.setDescripcion("Brand lanza un ataque poderoso a su objetivo y le inflige daño mágico.");
			darius3.setDaño(50);
			darius3.setCoste(20);
			darius3.setCritico(0);
			darius3.setDmgverdadero(true);
			darius3.setRobovida(false);
			darius3.setPrecision(80);

			Habilidad darius4 = new Habilidad();
			darius4.setNumHabilidad(3);
			darius4.setNombre("hab4");
			darius4.setDescripcion(
					"Darius pone a punto su hacha, lo cual provoca que, de forma pasiva,\nsu daño físico ignore un porcentaje de la armadura de su objetivo");
			darius4.setDaño(150);
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
			
			
			// Personajes
			Personaje Diana = new Personaje();
			Diana.setNombre("Diana");
			Diana.setCoste(2500);
			Diana.setVida(480);
			Diana.setEnergia(120);
			Diana.setDefensa(30);
			Diana.setRecargo(0.10);
			Diana.setVelocidad(72);
			Diana.setVidaTotal(480);
			Diana.setEnergiaTotal(120);
			Diana.setMensajes(new ArrayList<String>());
			Diana.setAspecto("/54/diana/splash/diana.jpg");
			Diana.setSprite("/54/diana/sprite/Diana.png");

			// habilidades ligadas al personaje
			Habilidad Diana1 = new Habilidad();
			Diana1.setNumHabilidad(0);
			Diana1.setNombre("Impacto Creciente");
			Diana1.setDescripcion("Diana blande su espada para liberar un rayo de energía lunar que inflige daño en un arco antes de explotar.");
			Diana1.setDaño(80);
			Diana1.setCoste(40);
			Diana1.setCritico(80);
			Diana1.setDmgverdadero(false);
			Diana1.setRobovida(false);
			Diana1.setPrecision(65);

			Habilidad diana2 = new Habilidad();
			diana2.setNumHabilidad(1);
			diana2.setNombre("Cascada pálida");
			diana2.setDescripcion("Crea tres esferas que orbitan y explotan al impactar con los enemigos");
			diana2.setDaño(50);
			diana2.setCoste(10);
			diana2.setCritico(15);
			diana2.setDmgverdadero(false);
			diana2.setRobovida(false);
			diana2.setPrecision(100);

			Habilidad diana3 = new Habilidad();
			diana3.setNumHabilidad(2);
			diana3.setNombre("Lluvia de luna");
			diana3.setDescripcion("Diana atrae a todos los enemigos cercanos y les hace daño.");
			diana3.setDaño(65);
			diana3.setCoste(0);
			diana3.setCritico(0);
			diana3.setDmgverdadero(false);
			diana3.setRobovida(false);
			diana3.setPrecision(60);

			Habilidad diana4 = new Habilidad();
			diana4.setNumHabilidad(3);
			diana4.setNombre("Torrente lunar");
			diana4.setDescripcion(
					"Diana corre hacia un enemigo y le inflige daño mágico.");
			diana4.setDaño(100);
			diana4.setCoste(50);
			diana4.setCritico(150);
			diana4.setDmgverdadero(false);
			diana4.setRobovida(false);
			diana4.setPrecision(100);

			ArrayList<Habilidad> hdiana = new ArrayList<>();
			hdiana.add(Diana1);
			hdiana.add(diana2);
			hdiana.add(diana3);
			hdiana.add(diana4);

			Diana.setHabilidades(hdiana);
			
			
			
			
			Personaje jayce = new Personaje();
			jayce.setNombre("Jayce");
			jayce.setCoste(2500);
			jayce.setVida(450);
			jayce.setEnergia(90);
			jayce.setDefensa(30);
			jayce.setRecargo(0.15);
			jayce.setVelocidad(80);
			jayce.setVidaTotal(450);
			jayce.setEnergiaTotal(90);
			jayce.setMensajes(new ArrayList<String>());
			jayce.setAspecto("/54/jayce/splash/jayce.jpg");
			jayce.setSprite("/54/jayce/sprite/Jayce.png");

			// habilidades ligadas al personaje
			Habilidad jayce1 = new Habilidad();
			jayce1.setNumHabilidad(0);
			jayce1.setNombre("Explosión eléctrica");
			jayce1.setDescripcion("Lanza un orbe de electricidad que detona al golpear a un enemigo");
			jayce1.setDaño(100);
			jayce1.setCoste(40);
			jayce1.setCritico(25);
			jayce1.setDmgverdadero(false);
			jayce1.setRobovida(false);
			jayce1.setPrecision(60);

			Habilidad jayce2 = new Habilidad();
			jayce2.setNumHabilidad(1);
			jayce2.setNombre("Campo de rayos");
			jayce2.setDescripcion("Crea un campo de rayos que daña a los enemigos cercanos");
			jayce2.setDaño(65);
			jayce2.setCoste(15);
			jayce2.setCritico(25);
			jayce2.setDmgverdadero(false);
			jayce2.setRobovida(false);
			jayce2.setPrecision(80);

			Habilidad jayce3 = new Habilidad();
			jayce3.setNumHabilidad(2);
			jayce3.setNombre("Golpe tormentoso");
			jayce3.setDescripcion("Golpe de martillo que Inflige daño.");
			jayce3.setDaño(40);
			jayce3.setCoste(15);
			jayce3.setCritico(25);
			jayce3.setDmgverdadero(true);
			jayce3.setRobovida(false);
			jayce3.setPrecision(80);

			Habilidad jayce4 = new Habilidad();
			jayce4.setNumHabilidad(3);
			jayce4.setNombre("Martillo de mercurio");
			jayce4.setDescripcion(
					"Jayce desprende toda la energía acumulada en su martillo y causa mucho daño.");
			jayce4.setDaño(200);
			jayce4.setCoste(60);
			jayce4.setCritico(25);
			jayce4.setDmgverdadero(false);
			jayce4.setRobovida(true);
			jayce4.setTipoRobovida(TiposRobosVida.VidaEnemigaActual);
			jayce4.setPorcentajeRV(0.08);
			jayce4.setPrecision(75);

			ArrayList<Habilidad> hjayce = new ArrayList<>();
			hjayce.add(jayce1);
			hjayce.add(jayce2);
			hjayce.add(jayce3);
			hjayce.add(jayce4);

			jayce.setHabilidades(hjayce);
			
			//falta añadir mensaje a los campeones, cambiar el nombre de las habilidades y añadir splasharts
			Personaje lucian = new Personaje();
			lucian.setNombre("Lucian");
			lucian.setCoste(3000);
			lucian.setVida(410);
			lucian.setEnergia(120);
			lucian.setDefensa(20);
			lucian.setRecargo(0.10);
			lucian.setVelocidad(85);
			lucian.setVidaTotal(410);
			lucian.setEnergiaTotal(120);
			lucian.setMensajes(new ArrayList<String>());
			lucian.setAspecto("/54/lucian/splash/lucian.jpg");
			lucian.setSprite("/54/lucian/sprite/Lucian.png");

			// habilidades ligadas al personaje
			Habilidad lucian1 = new Habilidad();
			lucian1.setNumHabilidad(0);
			lucian1.setNombre("Luz lacerante");
			lucian1.setDescripcion("Lucian dispara un rayo de luz lacerante a través de un enemigo.");
			lucian1.setDaño(75);
			lucian1.setCoste(45);
			lucian1.setCritico(50);
			lucian1.setDmgverdadero(false);
			lucian1.setRobovida(false);
			lucian1.setPrecision(85);

			Habilidad lucian2 = new Habilidad();
			lucian2.setNumHabilidad(1);
			lucian2.setNombre("Resplandor ardiente");
			lucian2.setDescripcion("Lucian dispara un misil que explota en forma de estrella");
			lucian2.setDaño(50);
			lucian2.setCoste(40);
			lucian2.setCritico(30);
			lucian2.setDmgverdadero(true);
			lucian2.setRobovida(false);
			lucian2.setPrecision(85);

			Habilidad lucian3 = new Habilidad();
			lucian3.setNumHabilidad(2);
			lucian3.setNombre("Persecución implacable");
			lucian3.setDescripcion("Lucian se desplaza una corta distancia e inflinge daño.");
			lucian3.setDaño(50);
			lucian3.setCoste(20);
			lucian3.setCritico(20);
			lucian3.setDmgverdadero(false);
			lucian3.setRobovida(true);
			lucian3.setTipoRobovida(TiposRobosVida.VidaEnemigaMaxima);
			lucian3.setPorcentajeRV(0.05);
			lucian3.setPrecision(80);

			Habilidad lucian4 = new Habilidad();
			lucian4.setNumHabilidad(3);
			lucian4.setNombre("El Sacrificio");
			lucian4.setDescripcion(
					"Lucian desata un torrente de disparos de sus armas.");
			lucian4.setDaño(275);
			lucian4.setCoste(70);
			lucian4.setCritico(0);
			lucian4.setDmgverdadero(false);
			lucian4.setRobovida(true);
			lucian4.setTipoRobovida(TiposRobosVida.VidaFaltanteEnemiga);
			lucian4.setPorcentajeRV(0.2);
			lucian4.setPrecision(80);

			ArrayList<Habilidad> hlucian = new ArrayList<>();
			hlucian.add(lucian1);
			hlucian.add(lucian2);
			hlucian.add(lucian3);
			hlucian.add(lucian4);

			lucian.setHabilidades(hlucian);
			
			
			
			
			
			Personaje lux = new Personaje();
			lux.setNombre("Lux");
			lux.setCoste(3000);
			lux.setVida(450);
			lux.setEnergia(150);
			lux.setDefensa(20);
			lux.setRecargo(0.08);
			lux.setVelocidad(73);
			lux.setVidaTotal(450);
			lux.setEnergiaTotal(150);
			lux.setMensajes(new ArrayList<String>());
			lux.setAspecto("/54/lux/splash/lux.jpg");
			lux.setSprite("/54/lux/sprite/Lux.png");

			// habilidades ligadas al personaje
			Habilidad lux1 = new Habilidad();
			lux1.setNumHabilidad(0);
			lux1.setNombre("Enlace de luz");
			lux1.setDescripcion("Lux lanza una esfera de luz que causa daño.");
			lux1.setDaño(70);
			lux1.setCoste(40);
			lux1.setCritico(50);
			lux1.setDmgverdadero(false);
			lux1.setRobovida(false);
			lux1.setPrecision(95);

			Habilidad lux2 = new Habilidad();
			lux2.setNumHabilidad(1);
			lux2.setNombre("Barrera prismática");
			lux2.setDescripcion("Lanza su varita y deforma la luz alrededor causando daño");
			lux2.setDaño(55);
			lux2.setCoste(30);
			lux2.setCritico(100);
			lux2.setDmgverdadero(false);
			lux2.setRobovida(false);
			lux2.setPrecision(60);

			Habilidad lux3 = new Habilidad();
			lux3.setNumHabilidad(2);
			lux3.setNombre("Singularidad brillante");
			lux3.setDescripcion("Lanza una anomalía de luz entrelazada a un área que daña a los enemigos.");
			lux3.setDaño(55);
			lux3.setCoste(40);
			lux3.setCritico(0);
			lux3.setDmgverdadero(false);
			lux3.setRobovida(false);
			lux3.setPrecision(100);

			Habilidad lux4 = new Habilidad();
			lux4.setNumHabilidad(3);
			lux4.setNombre("Chispa final");
			lux4.setDescripcion(
					"Tras reunir la energía necesaria, Lux lanza un rayo de luz que daña a todos los objetivos de la zona.");
			lux4.setDaño(280);
			lux4.setCoste(70);
			lux4.setCritico(40);
			lux4.setDmgverdadero(false);
			lux4.setRobovida(false);
			lux4.setPrecision(70);

			ArrayList<Habilidad> hlux = new ArrayList<>();
			 hlux.add(lux1);
			 hlux.add(lux2);
			 hlux.add(lux3);
			 hlux.add(lux4);

			lux.setHabilidades(hlux);
			
			
			
			
			
			Personaje myi = new Personaje();
			myi.setNombre("Maestro Yi");
			myi.setCoste(3500);
			myi.setVida(480);
			myi.setEnergia(100);
			myi.setDefensa(30);
			myi.setRecargo(0.12);
			myi.setVelocidad(82);
			myi.setVidaTotal(480);
			myi.setEnergiaTotal(100);
			myi.setMensajes(new ArrayList<String>());
			myi.setAspecto("/54/yi/splash/yi.jpg");
			myi.setSprite("/54/yi/sprite/MasterYi.png");

			// habilidades ligadas al personaje
			Habilidad yi1 = new Habilidad();
			yi1.setNumHabilidad(0);
			yi1.setNombre("Golpe fulgurante");
			yi1.setDescripcion("El Maestro Yi se teleporta por el campo de batalla a toda velocidad y"
					+ " daña a múltiples unidades a su paso, sin que nadie pueda tocarlo.");
			yi1.setDaño(60);
			yi1.setCoste(30);
			yi1.setCritico(60);
			yi1.setDmgverdadero(true);
			yi1.setRobovida(false);
			yi1.setPrecision(70);

			Habilidad yi2 = new Habilidad();
			yi2.setNumHabilidad(1);
			yi2.setNombre("Meditar");
			yi2.setDescripcion("El Maestro Yi medita y causa daño.");
			yi2.setDaño(40);
			yi2.setCoste(25);
			yi2.setCritico(10);
			yi2.setDmgverdadero(true);
			yi2.setRobovida(false);
			yi2.setPrecision(100);

			Habilidad yi3 = new Habilidad();
			yi3.setNumHabilidad(2);
			yi3.setNombre("Estilo Wuju");
			yi3.setDescripcion("El Maestro Yi aplica una técnica wuju ");
			yi3.setDaño(55);
			yi3.setCoste(15);
			yi3.setCritico(0);
			yi3.setDmgverdadero(false);
			yi3.setRobovida(true);
			yi3.setTipoRobovida(TiposRobosVida.VidaEnemigaActual);
			yi3.setPorcentajeRV(0.05);
			yi3.setPrecision(80);

			Habilidad yi4 = new Habilidad();
			yi4.setNumHabilidad(3);
			yi4.setNombre("Imparable");
			yi4.setDescripcion(
					"Maestro Yi desata todo su poder, lo que causa gran daño al enemigo.");
			yi4.setDaño(200);
			yi4.setCoste(60);
			yi4.setCritico(0);
			yi4.setDmgverdadero(false);
			yi4.setRobovida(true);
			yi4.setTipoRobovida(TiposRobosVida.VidaEnemigaActual);
			yi4.setPorcentajeRV(0.2);
			yi4.setPrecision(70);

			ArrayList<Habilidad> hyi = new ArrayList<>();
			hyi.add(yi1);
			hyi.add(yi2);
			hyi.add(yi3);
			hyi.add(yi4);

			myi.setHabilidades(hyi);
			
			
			
			
			
			
			Personaje riven = new Personaje();
			riven.setNombre("Riven");
			riven.setCoste(3500);
			riven.setVida(480);
			riven.setEnergia(120);
			riven.setDefensa(30);
			riven.setRecargo(0.10);
			riven.setVelocidad(81);
			riven.setVidaTotal(480);
			riven.setEnergiaTotal(120);
			riven.setMensajes(new ArrayList<String>());
			riven.setAspecto("/54/Riven/splash/riven.jpg");
			riven.setSprite("/54/Riven/sprite/Riven.png");

			// habilidades ligadas al personaje
			Habilidad riven1 = new Habilidad();
			riven1.setNumHabilidad(0);
			riven1.setNombre("Alas rotas");
			riven1.setDescripcion("Riven lanza una serie de golpes.");
			riven1.setDaño(65);
			riven1.setCoste(40);
			riven1.setCritico(35);
			riven1.setDmgverdadero(true);
			riven1.setRobovida(false);
			riven1.setPrecision(80);

			Habilidad riven2 = new Habilidad();
			riven2.setNumHabilidad(1);
			riven2.setNombre("Estallido de ki");
			riven2.setDescripcion("Riven emite un Estallido de ki que daña.");
			riven2.setDaño(50);
			riven2.setCoste(0);
			riven2.setCritico(0);
			riven2.setDmgverdadero(false);
			riven2.setRobovida(false);
			riven2.setPrecision(90);

			Habilidad riven3 = new Habilidad();
			riven3.setNumHabilidad(2);
			riven3.setNombre("Valor");
			riven3.setDescripcion("Riven se arma de valor y causa daño con el ki que genera su aura.");
			riven3.setDaño(55);
			riven3.setCoste(25);
			riven3.setCritico(15);
			riven3.setDmgverdadero(false);
			riven3.setRobovida(false);
		    riven3.setPrecision(80);

			Habilidad riven4 = new Habilidad();
			riven4.setNumHabilidad(3);
			riven4.setNombre("Cuchillada de Viento");
			riven4.setDescripcion(
					"Riven lanza una onda de choque que inflige daño .");
			riven4.setDaño(500);
			riven4.setCoste(100);
			riven4.setCritico(60);
			riven4.setDmgverdadero(false);
			riven4.setRobovida(false);
            riven4.setPrecision(20);

			ArrayList<Habilidad> hriven = new ArrayList<>();
			hriven.add(riven1);
			hriven.add(riven2);
			hriven.add(riven3);
			hriven.add(riven4);

			riven.setHabilidades(hriven);
			
			
			
			
			
			Personaje sivir = new Personaje();
			sivir.setNombre("Sivir");
			sivir.setCoste(3500);
			sivir.setVida(410);
			sivir.setEnergia(150);
			sivir.setDefensa(20);
			sivir.setRecargo(0.08);
			sivir.setVelocidad(84);
			sivir.setVidaTotal(410);
			sivir.setEnergiaTotal(150);
			sivir.setMensajes(new ArrayList<String>());
			sivir.setAspecto("/54/sivir/splash/sivir.jpg");
			sivir.setSprite("/54/sivir/sprite/Sivir.png");

			// habilidades ligadas al personaje
			Habilidad sivir1 = new Habilidad();
			sivir1.setNumHabilidad(0);
			sivir1.setNombre("Cuchilla bumerán");
			sivir1.setDescripcion("Sivir arroja su arma como un bumerán, causando daño.");
			sivir1.setDaño(90);
			sivir1.setCoste(50);
			sivir1.setCritico(30);
			sivir1.setDmgverdadero(false);
			sivir1.setRobovida(false);
			sivir1.setPrecision(70);

			Habilidad sivir2 = new Habilidad();
			sivir2.setNumHabilidad(1);
			sivir2.setNombre("Ricochet");
			sivir2.setDescripcion("Los ataques de Sivir rebotan en objetivos cercanos, infligiendo menos daño tras el primer rebote.");
			sivir2.setDaño(60);
			sivir2.setCoste(35);
			sivir2.setCritico(0);
			sivir2.setDmgverdadero(false);
			sivir2.setRobovida(false);
			sivir2.setPrecision(100);

			Habilidad sivir3 = new Habilidad();
			sivir3.setNumHabilidad(2);
			sivir3.setNombre("Escudo de hechizos");
			sivir3.setDescripcion("Crea una barrera mágica que daña a los enemigos al tocarla.");
			sivir3.setDaño(70);
			sivir3.setCoste(45);
			sivir3.setCritico(40);
			sivir3.setDmgverdadero(false);
			sivir3.setRobovida(true);
			sivir3.setTipoRobovida(TiposRobosVida.VidaEnemigaMaxima);
			sivir3.setPorcentajeRV(0.10);
			sivir3.setPrecision(90);

			Habilidad sivir4 = new Habilidad();
			sivir4.setNumHabilidad(3);
			sivir4.setNombre("A la caza");
			sivir4.setDescripcion(
					"Sivir causa gran daño a su presa.");
			sivir4.setDaño(150);
			sivir4.setCoste(75);
			sivir4.setCritico(200);
			sivir4.setDmgverdadero(true);
			sivir4.setRobovida(false);
			sivir4.setPrecision(100);

			ArrayList<Habilidad> hsivir = new ArrayList<>();
			hsivir.add(sivir1);
			hsivir.add(sivir2);
			hsivir.add(sivir3);
			hsivir.add(sivir4);

			sivir.setHabilidades(hsivir);
			
			
			
			
			
			
			
			
			
			Personaje thresh = new Personaje();
			thresh.setNombre("Thresh");
			thresh.setCoste(3500);
			thresh.setVida(620);
			thresh.setEnergia(90);
			thresh.setDefensa(35);
			thresh.setRecargo(0.10);
			thresh.setVelocidad(69);
			thresh.setVidaTotal(620);
			thresh.setEnergiaTotal(90);
			thresh.setMensajes(new ArrayList<String>());
			thresh.setAspecto("/54/thresh/splash/thresh.jpg");
			thresh.setSprite("/54/thresh/sprite/Thresh.png");

			// habilidades ligadas al personaje
			Habilidad thresh1 = new Habilidad();
			thresh1.setNumHabilidad(0);
			thresh1.setNombre("Sentencia de muerte");
			thresh1.setDescripcion("Thresh atrapa a un enemigo con sus cadenas y lo atrae hacia él.");
			thresh1.setDaño(50);
			thresh1.setCoste(20);
			thresh1.setCritico(25);
			thresh1.setDmgverdadero(false);
			thresh1.setRobovida(false);
			thresh1.setPrecision(60);

			Habilidad thresh2 = new Habilidad();
			thresh2.setNumHabilidad(1);
			thresh2.setNombre("Camino oscuro");
			thresh2.setDescripcion("Thresh lanza una linterna que estalla y causa daño");
			thresh2.setDaño(40);
			thresh2.setCoste(30);
			thresh2.setCritico(45);
			thresh2.setDmgverdadero(false);
			thresh2.setRobovida(false);
			thresh2.setPrecision(100);

			Habilidad thresh3 = new Habilidad();
			thresh3.setNumHabilidad(2);
			thresh3.setNombre("Despellejar");
			thresh3.setDescripcion("Thresh barre la zona con sus cadenas y derriba a todos los enemigos ");
			thresh3.setDaño(35);
			thresh3.setCoste(0);
			thresh3.setCritico(15);
			thresh3.setDmgverdadero(true);
			thresh3.setRobovida(false);
		    thresh3.setPrecision(100);

			Habilidad thresh4 = new Habilidad();
			thresh4.setNumHabilidad(3);
			thresh4.setNombre("La caja");
			thresh4.setDescripcion(
					"Una prisión de muros que se estrechan hasta causar un gran daño.");
			thresh4.setDaño(80);
			thresh4.setCoste(75);
			thresh4.setCritico(0);
			thresh4.setDmgverdadero(false);
			thresh4.setRobovida(true);
			thresh4.setTipoRobovida(TiposRobosVida.VidaFaltantePropia);
			thresh4.setPorcentajeRV(0.5);
			thresh4.setPrecision(80);

			ArrayList<Habilidad> hthresh = new ArrayList<>();
			hthresh.add(thresh1);
			hthresh.add(thresh2);
			hthresh.add(thresh3);
			hthresh.add(thresh4);

			thresh.setHabilidades(hthresh);
			
			
			
			
			
			
			
			
			Personaje yasuo = new Personaje();
			yasuo.setNombre("Yasuo");
			yasuo.setCoste(3500);
			yasuo.setVida(450);
			yasuo.setEnergia(100);
			yasuo.setDefensa(20);
			yasuo.setRecargo(0.10);
			yasuo.setVelocidad(90);
			yasuo.setVidaTotal(450);
			yasuo.setEnergiaTotal(100);
			yasuo.setMensajes(new ArrayList<String>());
			yasuo.setAspecto("/54/yasuo/splash/yasuo.jpg");
			yasuo.setSprite("/54/yasuo/sprite/Yasuo.png");

			// habilidades ligadas al personaje
			Habilidad yasuo1 = new Habilidad();
			yasuo1.setNumHabilidad(0);
			yasuo1.setNombre("Tempestad de acero");
			yasuo1.setDescripcion("Lanza una estocada al frente que inflige daño.");
			yasuo1.setDaño(100);
			yasuo1.setCoste(50);
			yasuo1.setCritico(100);
			yasuo1.setDmgverdadero(false);
			yasuo1.setRobovida(false);
			yasuo1.setPrecision(50);

			Habilidad yasuo2 = new Habilidad();
			yasuo2.setNumHabilidad(1);
			yasuo2.setNombre("Muro de viento");
			yasuo2.setDescripcion("Crea un muro móvil que bloquea los proyectiles enemigos y devuelve daño.");
			yasuo2.setDaño(70);
			yasuo2.setCoste(40);
			yasuo2.setCritico(70);
			yasuo2.setDmgverdadero(true);
			yasuo2.setRobovida(false);
			yasuo2.setPrecision(50);

			Habilidad yasuo3 = new Habilidad();
			yasuo3.setNumHabilidad(2);
			yasuo3.setNombre("Hoja cortante");
			yasuo3.setDescripcion("Se desliza a través de una unidad e inflige una cantidad de daño ");
			yasuo3.setDaño(50);
			yasuo3.setCoste(20);
			yasuo3.setCritico(50);
			yasuo3.setDmgverdadero(false);
			yasuo3.setRobovida(true);
			yasuo3.setTipoRobovida(TiposRobosVida.VidaEnemigaActual);
		    yasuo3.setPrecision(70);

			Habilidad yasuo4 = new Habilidad();
			yasuo4.setNumHabilidad(3);
			yasuo4.setNombre("Último aliento");
			yasuo4.setDescripcion(
					"Se abalanza sobre una unidad y la golpea repetidamente, infligiéndole mucho daño.");
			yasuo4.setDaño(300);
			yasuo4.setCoste(70);
			yasuo4.setCritico(300);
			yasuo4.setDmgverdadero(true);
			yasuo4.setRobovida(false);
			yasuo4.setPrecision(30);

			ArrayList<Habilidad> hyasuo = new ArrayList<>();
			hyasuo.add(yasuo1);
			hyasuo.add(yasuo2);
			hyasuo.add(yasuo3);
			hyasuo.add(yasuo4);

			yasuo.setHabilidades(hyasuo);

			// u.getPersonajes().add(ahri);
			u.getPersonajes().add(brand);
			u.getPersonajes().add(darius);

			u.getInventario().getPocionesList().add(poVida);
			u.getInventario().getElixiresList().add(poEnergia);
			u.getInventario().getVialesList().add(poDefensa);

			em.persist(poDefensa);
			em.persist(poEnergia);
			em.persist(poVida);
			em.persist(inventario);
			em.persist(historial);
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
			
			em.persist(Diana1);
			em.persist(diana2);
			em.persist(diana3);
			em.persist(diana4);
			em.persist(Diana);
			
			em.persist(jayce4);
			em.persist(jayce1);
			em.persist(jayce2);
			em.persist(jayce3);
			em.persist(jayce);
			
			em.persist(lucian4);
			em.persist(lucian1);
			em.persist(darius3);
			em.persist(darius2);
			em.persist(lucian);
			
			em.persist(lux1);
			em.persist(lux2);
			em.persist(lux3);
			em.persist(lux4);
			em.persist(lux);
			
			em.persist(sivir1);
			em.persist(sivir2);
			em.persist(sivir3);
			em.persist(sivir4);
			em.persist(sivir);
			
			em.persist(yi1);
			em.persist(yi2);
			em.persist(yi3);
			em.persist(yi4);
			em.persist(myi);
			
			em.persist(riven1);
			em.persist(riven2);
			em.persist(riven3);
			em.persist(riven4);
			em.persist(riven);
			
			em.persist(thresh1);
			em.persist(thresh2);
			em.persist(thresh3);
			em.persist(thresh4);
			em.persist(thresh);
			
			em.persist(yasuo1);
			em.persist(yasuo2);
			em.persist(yasuo3);
			em.persist(yasuo4);
			em.persist(yasuo);
			

			em.getTransaction().commit();
		}
		
	}
	
	private boolean existeDB() {
		boolean existe=false;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT p FROM Usuario p where p.nombre=:nombre", Usuario.class);
			query.setParameter("nombre", "richard");
			Usuario lista = (Usuario) query.getSingleResult();
			em.getTransaction().commit();
			existe=true;
		} catch (Exception e) {
			//e.printStackTrace();
			existe=false;
		}
		finally {
			if(em.getTransaction().isActive()) em.getTransaction().commit();
		}
		return existe;
			
		}
	

}
