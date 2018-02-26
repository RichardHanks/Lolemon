package lolemon.logicaDeNegocio.Clases;

import lolemon.persistencia.modelo.Habilidad;
import lolemon.persistencia.modelo.Inventario;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.TiposRobosVida;

public class Combate {

	private static Personaje p1;
	private static Personaje p2;
	private static int numturno;
	private static Habilidad ultimaUsada;
	private int puntosGanador;
	private int puntosPerdedor;
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

	public String Atacar(int numHabilidad, Personaje p1, Personaje p2) {

		String mensaje = "";
		Habilidad usada = new Habilidad();
		usada = p1.getHabilidades().get(numHabilidad);

		int random = (int) ((Math.random() * 100) + 1);

		if (p1.getEnergia() > p1.getHabilidades().get(numHabilidad).getCoste()) {
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

	public void UsarElixir(Personaje p1) {
		int recarga = inventario.getElixiresList().get(0).getIncremento();

		if (p1.getEnergiaTotal() == p1.getEnergia()) {
			System.out.println("La Energía está completa, no se puede usar una poción");
		} else if (p1.getEnergiaTotal() - p1.getEnergia() > recarga) {
			p1.setEnergia(p1.getEnergia() + recarga);
		} else {
			p1.setEnergia(p1.getEnergia() + recarga - (recarga - (p1.getEnergiaTotal() - p1.getEnergia())));

		}
	}

	public void UsarVial(Personaje p1) {

		p1.setDefensa(p1.getDefensa() + inventario.getVialesList().get(0).getIncremento());

	}

	public void cambiarturno() {
		// TODO Auto-generated method stub

		if (turno) {
			turno = false;
		} else {
			turno = true;
		}

	}

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
	// TENGO QUE CAMBIAR ESTA FUNCIÓN

	private static void robarvida(Habilidad usada, Personaje p1, Personaje rival) {
		if (usada.getTipoRobovida() == TiposRobosVida.VidaActual) {
			p1.setVida((int) (p1.getVida() + p1.getVida() * usada.getPorcentajeRV()));
			System.out.println(p1.getNombre() + "ha robado vida 1");
		} // Robo vida en base a la vida actual
		else if (usada.getTipoRobovida() == TiposRobosVida.VidaEnemigaActual) {
			p1.setVida((int) (p1.getVida() + rival.getVida() * usada.getPorcentajeRV()));
			System.out.println(p1.getNombre() + "ha robado vida 2");

		} // Robo vida en base a la vida actual del enemigo
		else if (usada.getTipoRobovida() == TiposRobosVida.VidaPropiaMaxima) {
			p1.setVida((int) (p1.getVida() + p1.getVidaTotal() * usada.getPorcentajeRV()));
			System.out.println(p1.getNombre() + "ha robado vida 3");
			// Robo vida en base a vida max.
		} else if (usada.getTipoRobovida() == TiposRobosVida.VidaEnemigaMaxima) {
			System.out.println(p1.getVida());
			System.out.println(rival.getVidaTotal());
			System.out.println(usada.getPorcentajeRV());
			p1.setVida((int) (p1.getVida() + rival.getVidaTotal() * usada.getPorcentajeRV()));
			System.out.println(p1.getNombre() + "ha robado vida 4");
		} // Robo vida en base a la vida máx enemiga.
		else if (usada.getTipoRobovida() == TiposRobosVida.VidaFaltantePropia) {
			p1.setVida((int) (p1.getVida() + (p1.getVidaTotal() - p1.getVida()) * usada.getPorcentajeRV()));
			System.out.println(p1.getNombre() + "ha robado vida 5");
		}
		// Robo vida en base a la vida que falta
		else if (usada.getTipoRobovida() == TiposRobosVida.VidaFaltanteEnemiga) {
			p1.setVida((int) (p1.getVida() + (rival.getVidaTotal() - rival.getVida()) * usada.getPorcentajeRV()));
			System.out.println(p1.getNombre() + "ha robado vida 6");
		} else {

		}
	}

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

	public Habilidad getUltimaUsada() {
		return ultimaUsada;
	}

	public void setUltimaUsada(Habilidad ultimaUsada) {
		this.ultimaUsada = ultimaUsada;
	}

	public int getPuntosGanador() {
		return puntosGanador;
	}

	public void setPuntosGanador(int puntosGanador) {
		this.puntosGanador = puntosGanador;
	}

	public int getPuntosPerdedor() {
		return puntosPerdedor;
	}

	public void setPuntosPerdedor(int puntosPerdedor) {
		this.puntosPerdedor = puntosPerdedor;
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
