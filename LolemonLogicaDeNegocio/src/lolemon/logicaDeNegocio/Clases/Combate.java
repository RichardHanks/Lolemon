package lolemon.logicaDeNegocio.Clases;

import lolemon.persistencia.modelo.Habilidad;
import lolemon.persistencia.modelo.Inventario;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.TiposRobosVida;

public class Combate {

	private static Personaje p1;
	private static Personaje p2;
	private static int numturno;
	private int puntosGanador;
	private Boolean ganaJ1;
	private static Boolean turno;
	private Inventario inventario;
	private int pocionesusadas = 0;
	private int dañoj1, dañoj2;

	public Combate(Personaje p1, Personaje p2, Inventario inventario) {
		this.p1 = p1;
		this.p2 = p2;
		this.inventario = inventario;

	}
    /**
     * Método que calcula el daño total hecho en el combate por cada jugador.
     * @param i cantidad de daño hecha en el turno.
     */
	public void calcularDmg(int i) {
		if (getTurno()) {
			dañoj1 += i;
			System.out.println(dañoj1);
			System.out.println("hola");
			System.out.println(dañoj2);
		} else {
			dañoj2 += i;
			System.out.println(dañoj2);
			System.out.println("adios");
		}
	}
    /**
     * Método que hace que los Personajes recuperen energía encada turno.
     * @param p12 personaje que recargara energía
     */
	public void recargar(Personaje p12) {
		// TODO Auto-generated method stub

		int coeficiente = (int) (p12.getEnergiaTotal() * p12.getRecargo());

		if (p12.getEnergia().equals(p12.getEnergiaTotal())) {
			System.out.println("Energía completa!");
		} else if(p12.getEnergiaTotal()-p12.getEnergia()<coeficiente) {
			p12.setEnergia(p12.getEnergia()+coeficiente-(coeficiente-(p12.getEnergiaTotal()-p12.getEnergia())));
		}
		else {
			p12.setEnergia(p12.getEnergia() + coeficiente);
			// System.out.println("+"+coeficiente+" energía");
			System.out.println(p12.getNombre() + " ha recargado");
		}
	}
    /**
     * Método que determina y hace los cambios de estadísticas entre los personajes al usar habilidades.
     * es el algoritmo principal del combate.
     * @param numHabilidad entero que determina la posición de la habilidad
     * @param p1 personaje que atacará
     * @param p2 personaje que recibirá el ataque
     * @return String que nos indica si ha fallado, si no tiene energía para atacar o si ha sido critico.
     */
	public String Atacar(int numHabilidad, Personaje p1, Personaje p2) {

		String mensaje = "";
		Habilidad usada = new Habilidad();
		usada = p1.getHabilidades().get(numHabilidad);

		int random = (int) ((Math.random() * 100) + 1);

		if (p1.getEnergia() >= p1.getHabilidades().get(numHabilidad).getCoste()) {
			if (random < p1.getHabilidades().get(numHabilidad).getPrecision()) {
				String[] critico = critico(usada);
				if (usada.isRobovida()) {
					robarvida(usada, p1, p2);
					p2.setVida(p2.getVida() - usada.getDaño() + p2.getDefensa() - Integer.parseInt(critico[0]));
					// System.out.println(nombre+" ha
					// hecho"+String.valueOf(habilidad.daño-rival.defensa+critico(habilidad.numHabilidad)));
					calcularDmg(usada.getDaño() - p2.getDefensa() + Integer.parseInt(critico[0]));
				} else {
					if (usada.isDmgverdadero()) {
						p2.setVida(p2.getVida() - usada.getDaño() - Integer.parseInt(critico[0]));
						// System.out.println(nombre+" ha
						// hecho"+String.valueOf(habilidad.daño+critico(habilidad.numHabilidad)));
						calcularDmg(usada.getDaño() + Integer.parseInt(critico[0]));
					} else {
						p2.setVida(p2.getVida() - usada.getDaño() + p2.getDefensa() - Integer.parseInt(critico[0]));
						// System.out.println(nombre+" ha
						// hecho"+String.valueOf(habilidad.daño-rival.defensa+critico(habilidad.numHabilidad)));
						calcularDmg(usada.getDaño() - p2.getDefensa() + Integer.parseInt(critico[0]));
					}
				}
				p1.setEnergia(p1.getEnergia() - usada.getCoste());
				mensaje = critico[1];

			}

			else {
				mensaje = p1.getNombre() + " falló";
			}
		} else {
			mensaje = "¡Sin energía!";
		}

		return mensaje;
	}
    /**
     * Método que suma vida a un personaje a cambio de usar un item 
     * @param p1 Personaje que recuperará vida 
     * @return devuelve un boolean que determina si es usada o no.
     */
	public boolean UsarPocion(Personaje p1) {
		boolean usada = false;
		int curacion = inventario.getPocionesList().get(0).getIncremento();

		if (p1.getVidaTotal().equals(p1.getVida())) {
			System.out.println(p1.getVidaTotal() + " " + p1.getVida());
			System.out.println("La vida está completa, no se puede usar una poción");
			usada = false;
		} else if (p1.getVidaTotal() - p1.getVida() > curacion) {
			System.out.println("le falta vida, se usa la poti");
			p1.setVida(p1.getVida() + curacion);
			usada = true;
		} else {
			System.out.println("el ultimo else");
			p1.setVida(p1.getVida() + curacion - (curacion - (p1.getVidaTotal() - p1.getVida())));
			usada = true;
		}
		return usada;
	}
    /**
     * Método que suma energía a cambio de usar un item
     * @param p1 Personaje que recuperará energía
     */
	public void UsarElixir(Personaje p1) {
		int recarga = inventario.getElixiresList().get(0).getIncremento();

		if (p1.getEnergiaTotal() == p1.getEnergia()) {
			System.out.println("La Energía está completa, no se puede usar una Elixir");
		} else if (p1.getEnergiaTotal() - p1.getEnergia() > recarga) {
			p1.setEnergia(p1.getEnergia() + recarga);
		} else {
			p1.setEnergia(p1.getEnergia() + recarga - (recarga - (p1.getEnergiaTotal() - p1.getEnergia())));

		}
	}
    /**
     * Método que suma defensa a un personaje a cambio de usar un item.
     * @param p1 Personaje que ganará defensa
     */
	public void UsarVial(Personaje p1) {

		p1.setDefensa(p1.getDefensa() + inventario.getVialesList().get(0).getIncremento());

	}
    /**
     * Método que cambia el turno.
     */
	public void cambiarturno() {
		// TODO Auto-generated method stub

		if (turno) {
			turno = false;
		} else {
			turno = true;
		}

	}
    /**
     * Método que determina si al usar una habilidad es golpe crítico.
     * @param usada Habilidad de la cuál obtendremos el daño critico a sumar
     * @return se devuelve un array de tamaño 2 que indica un mensaje de si ha sido crtico o no, y el daño extra que se hace.
     */
	private static String[] critico(Habilidad usada) {

		int random = (int) (Math.random() * 100);
		int daño = 0;
		String[] devolver = new String[2];
		String m = "";

		if (random > 85) {
			daño = usada.getCritico();

			// System.out.println(nombre+" ha hecho critico!");
			m = "¡Crítico!";
		}

		devolver[0] = String.valueOf(daño);
		devolver[1] = m;

		return devolver;
	}
	/**
	 * Método que reconoce el tipo e robo de vida que se aplicará y la ecuación que determina la cantidad de vida a robar.
	 * @param usada habilidad dela que obtendremos el porcentaje y el tipo de robo de vida 
	 * @param p1 personaje que ganará vida
	 * @param rival personaje enemigo del cual obtendremos estadisticas para robar vida dependiendo del tipo.
	 */

	private static void robarvida(Habilidad usada, Personaje p1, Personaje rival) {
		double robo;
		if (usada.getTipoRobovida() == TiposRobosVida.VidaActual) {
			robo= p1.getVida() * usada.getPorcentajeRV();
			RobovidaActual(p1,robo);
			System.out.println(p1.getNombre() + "ha robado vida 1");
		} // Robo vida en base a la vida actual
		else if (usada.getTipoRobovida() == TiposRobosVida.VidaEnemigaActual) {
			robo=rival.getVida()*usada.getPorcentajeRV();
			RobovidaActual(p1,robo);
			System.out.println(p1.getNombre() + "ha robado vida 2");

		} // Robo vida en base a la vida actual del enemigo
		else if (usada.getTipoRobovida() == TiposRobosVida.VidaPropiaMaxima) {
			robo=p1.getVidaTotal()*usada.getPorcentajeRV();
			RobovidaActual(p1,robo);
			System.out.println(p1.getNombre() + "ha robado vida 3");
			// Robo vida en base a vida max.
		} else if (usada.getTipoRobovida() == TiposRobosVida.VidaEnemigaMaxima) {
			System.out.println(p1.getVida());
			System.out.println(rival.getVidaTotal());
			System.out.println(usada.getPorcentajeRV());
			
			robo=rival.getVidaTotal() * usada.getPorcentajeRV();
			RobovidaActual(p1,robo);
			System.out.println(p1.getNombre() + "ha robado vida 4");
		} // Robo vida en base a la vida máx enemiga.
		else if (usada.getTipoRobovida() == TiposRobosVida.VidaFaltantePropia) {
			robo=(p1.getVidaTotal() - p1.getVida()) * usada.getPorcentajeRV();
			RobovidaActual(p1,robo);
			System.out.println(p1.getNombre() + "ha robado vida 5");
		}
		// Robo vida en base a la vida que falta
		else if (usada.getTipoRobovida() == TiposRobosVida.VidaFaltanteEnemiga) {
			robo=(rival.getVidaTotal() - rival.getVida()) * usada.getPorcentajeRV();
			RobovidaActual(p1,robo);
			System.out.println(p1.getNombre() + "ha robado vida 6");
		} else {

		}
	}
	/**
	 * función que determina la forma en que ganará vida para que el personaje no sobrepase la vidaTotal.
	 * @param p1 Personaje que ganará vida
	 * @param robo cantidad de vida que ganará
	 */
	private static void RobovidaActual(Personaje p1,double robo) {
		
		int vidafaltante= p1.getVidaTotal()-p1.getVida();
		
		if(p1.getVida().equals(p1.getVidaTotal())){
			System.out.println("No hace falta robarVida");
		}else if(vidafaltante<robo) {
			p1.setVida( (int) (p1.getVida()+robo-(robo-vidafaltante)));
		}else {
			p1.setVida((int) (p1.getVida()+robo));
		}
		
		
	}
    /**
     * Función que determina quién empezará el combate.
     * @param p1 Personaje 1 del que se obtendrá  velocidad
     * @param p2 Personaje 2 del que se obtendrña velocidad
     */
	public static void turno(Personaje p1, Personaje p2) {

		if (p1.getVelocidad() >= p2.getVelocidad()) {
			turno = true;
		} else {
			turno = false;
		}

	}

	/**
	 * Función que calcula el número de puntos que se consiguen al ganar
	 */
	public void CalcularPuntosVictoria() {

		puntosGanador = 10 * numturno;
	}

	/**
	 * Función que calcula el número de puntos que se consiguen al perder.
	 */
	public void CalcularPuntosDerrota() {
		puntosGanador = 3 * numturno;

	}

	/**
	 * Función que va calculando el número de turnos.
	 */
	public void incrementarTurno() {
		// TODO Auto-generated method stub
		numturno++;

	}

	public Personaje getJ1() {
		return p1;
	}

	public void setJ1(Personaje j1) {
		this.p1 = j1;
	}

	public Personaje getJ2() {
		return p2;
	}

	public void setJ2(Personaje j2) {
		this.p2 = j2;
	}

	public static Boolean getTurno() {
		return turno;
	}

	public static void setTurno(Boolean turno) {
		Combate.turno = turno;
	}


	public int getPuntosGanador() {
		return puntosGanador;
	}

	public void setPuntosGanador(int puntosGanador) {
		this.puntosGanador = puntosGanador;
	}

	public Boolean getGanaJ1() {
		return ganaJ1;
	}

	public void setGanaJ1(Boolean ganaJ1) {
		this.ganaJ1 = ganaJ1;
	}

	public int getPocionesusadas() {
		return pocionesusadas;
	}

	public void setPocionesusadas(int pocionesusadas) {
		this.pocionesusadas = pocionesusadas;
	}

	public int getDañoj1() {
		return dañoj1;
	}

	public int getDañoj2() {
		return dañoj2;
	}

}
