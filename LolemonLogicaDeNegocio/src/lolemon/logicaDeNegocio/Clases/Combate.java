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
	private int da�oj1, da�oj2;

	public Combate(Personaje p1, Personaje p2, Inventario inventario) {
		this.p1 = p1;
		this.p2 = p2;
		this.inventario = inventario;

	}
    /**
     * M�todo que calcula el da�o total hecho en el combate por cada jugador.
     * @param i cantidad de da�o hecha en el turno.
     */
	public void calcularDmg(int i) {
		if (getTurno()) {
			da�oj1 += i;
			System.out.println(da�oj1);
			System.out.println("hola");
			System.out.println(da�oj2);
		} else {
			da�oj2 += i;
			System.out.println(da�oj2);
			System.out.println("adios");
		}
	}
    /**
     * M�todo que hace que los Personajes recuperen energ�a encada turno.
     * @param p12 personaje que recargara energ�a
     */
	public void recargar(Personaje p12) {
		// TODO Auto-generated method stub

		int coeficiente = (int) (p12.getEnergiaTotal() * p12.getRecargo());

		if (p12.getEnergia().equals(p12.getEnergiaTotal())) {
			System.out.println("Energ�a completa!");
		} else if(p12.getEnergiaTotal()-p12.getEnergia()<coeficiente) {
			p12.setEnergia(p12.getEnergia()+coeficiente-(coeficiente-(p12.getEnergiaTotal()-p12.getEnergia())));
		}
		else {
			p12.setEnergia(p12.getEnergia() + coeficiente);
			// System.out.println("+"+coeficiente+" energ�a");
			System.out.println(p12.getNombre() + " ha recargado");
		}
	}
    /**
     * M�todo que determina y hace los cambios de estad�sticas entre los personajes al usar habilidades.
     * es el algoritmo principal del combate.
     * @param numHabilidad entero que determina la posici�n de la habilidad
     * @param p1 personaje que atacar�
     * @param p2 personaje que recibir� el ataque
     * @return String que nos indica si ha fallado, si no tiene energ�a para atacar o si ha sido critico.
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
					p2.setVida(p2.getVida() - usada.getDa�o() + p2.getDefensa() - Integer.parseInt(critico[0]));
					// System.out.println(nombre+" ha
					// hecho"+String.valueOf(habilidad.da�o-rival.defensa+critico(habilidad.numHabilidad)));
					calcularDmg(usada.getDa�o() - p2.getDefensa() + Integer.parseInt(critico[0]));
				} else {
					if (usada.isDmgverdadero()) {
						p2.setVida(p2.getVida() - usada.getDa�o() - Integer.parseInt(critico[0]));
						// System.out.println(nombre+" ha
						// hecho"+String.valueOf(habilidad.da�o+critico(habilidad.numHabilidad)));
						calcularDmg(usada.getDa�o() + Integer.parseInt(critico[0]));
					} else {
						p2.setVida(p2.getVida() - usada.getDa�o() + p2.getDefensa() - Integer.parseInt(critico[0]));
						// System.out.println(nombre+" ha
						// hecho"+String.valueOf(habilidad.da�o-rival.defensa+critico(habilidad.numHabilidad)));
						calcularDmg(usada.getDa�o() - p2.getDefensa() + Integer.parseInt(critico[0]));
					}
				}
				p1.setEnergia(p1.getEnergia() - usada.getCoste());
				mensaje = critico[1];

			}

			else {
				mensaje = p1.getNombre() + " fall�";
			}
		} else {
			mensaje = "�Sin energ�a!";
		}

		return mensaje;
	}
    /**
     * M�todo que suma vida a un personaje a cambio de usar un item 
     * @param p1 Personaje que recuperar� vida 
     * @return devuelve un boolean que determina si es usada o no.
     */
	public boolean UsarPocion(Personaje p1) {
		boolean usada = false;
		int curacion = inventario.getPocionesList().get(0).getIncremento();

		if (p1.getVidaTotal().equals(p1.getVida())) {
			System.out.println(p1.getVidaTotal() + " " + p1.getVida());
			System.out.println("La vida est� completa, no se puede usar una poci�n");
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
     * M�todo que suma energ�a a cambio de usar un item
     * @param p1 Personaje que recuperar� energ�a
     */
	public void UsarElixir(Personaje p1) {
		int recarga = inventario.getElixiresList().get(0).getIncremento();

		if (p1.getEnergiaTotal() == p1.getEnergia()) {
			System.out.println("La Energ�a est� completa, no se puede usar una Elixir");
		} else if (p1.getEnergiaTotal() - p1.getEnergia() > recarga) {
			p1.setEnergia(p1.getEnergia() + recarga);
		} else {
			p1.setEnergia(p1.getEnergia() + recarga - (recarga - (p1.getEnergiaTotal() - p1.getEnergia())));

		}
	}
    /**
     * M�todo que suma defensa a un personaje a cambio de usar un item.
     * @param p1 Personaje que ganar� defensa
     */
	public void UsarVial(Personaje p1) {

		p1.setDefensa(p1.getDefensa() + inventario.getVialesList().get(0).getIncremento());

	}
    /**
     * M�todo que cambia el turno.
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
     * M�todo que determina si al usar una habilidad es golpe cr�tico.
     * @param usada Habilidad de la cu�l obtendremos el da�o critico a sumar
     * @return se devuelve un array de tama�o 2 que indica un mensaje de si ha sido crtico o no, y el da�o extra que se hace.
     */
	private static String[] critico(Habilidad usada) {

		int random = (int) (Math.random() * 100);
		int da�o = 0;
		String[] devolver = new String[2];
		String m = "";

		if (random > 85) {
			da�o = usada.getCritico();

			// System.out.println(nombre+" ha hecho critico!");
			m = "�Cr�tico!";
		}

		devolver[0] = String.valueOf(da�o);
		devolver[1] = m;

		return devolver;
	}
	/**
	 * M�todo que reconoce el tipo e robo de vida que se aplicar� y la ecuaci�n que determina la cantidad de vida a robar.
	 * @param usada habilidad dela que obtendremos el porcentaje y el tipo de robo de vida 
	 * @param p1 personaje que ganar� vida
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
		} // Robo vida en base a la vida m�x enemiga.
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
	 * funci�n que determina la forma en que ganar� vida para que el personaje no sobrepase la vidaTotal.
	 * @param p1 Personaje que ganar� vida
	 * @param robo cantidad de vida que ganar�
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
     * Funci�n que determina qui�n empezar� el combate.
     * @param p1 Personaje 1 del que se obtendr�  velocidad
     * @param p2 Personaje 2 del que se obtendr�a velocidad
     */
	public static void turno(Personaje p1, Personaje p2) {

		if (p1.getVelocidad() >= p2.getVelocidad()) {
			turno = true;
		} else {
			turno = false;
		}

	}

	/**
	 * Funci�n que calcula el n�mero de puntos que se consiguen al ganar
	 */
	public void CalcularPuntosVictoria() {

		puntosGanador = 10 * numturno;
	}

	/**
	 * Funci�n que calcula el n�mero de puntos que se consiguen al perder.
	 */
	public void CalcularPuntosDerrota() {
		puntosGanador = 3 * numturno;

	}

	/**
	 * Funci�n que va calculando el n�mero de turnos.
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

	public int getDa�oj1() {
		return da�oj1;
	}

	public int getDa�oj2() {
		return da�oj2;
	}

}
