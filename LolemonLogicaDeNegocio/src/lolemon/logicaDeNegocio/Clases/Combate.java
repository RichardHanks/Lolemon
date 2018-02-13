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
	private int pocionesusadas=0;
	
	
	public Combate(Personaje p1,Personaje p2,Inventario inventario) {
		this.p1=p1;
		this.p2=p2;
		this.inventario=inventario;
		
		}
	

	public void recargar(Personaje p12) {
		// TODO Auto-generated method stub
		
		
		int coeficiente=(int) (p12.getEnergiaTotal()*p12.getRecargo());
	    p12.setEnergia(p12.getEnergia()+coeficiente);
	    //System.out.println("+"+coeficiente+" energía");
		System.out.println(p12.getNombre()+" ha recargado");
		
	}
	
	public void Atacar(int numHabilidad,Personaje p1,Personaje p2) {
		
		Habilidad usada= new Habilidad();
		usada=p1.getHabilidades().get(numHabilidad);
 		
        int random= (int) ((Math.random()*100)+1);
		
        if(p1.getEnergia()>p1.getHabilidades().get(numHabilidad).getCoste()) {
		if(random<p1.getHabilidades().get(numHabilidad).getPrecision()) {
			if(usada.isRobovida()) {
				robarvida(usada,p1,p2);
				p2.setVida(p2.getVida()-usada.getDaño()+p2.getDefensa()-critico(usada));
				//System.out.println(nombre+" ha hecho"+String.valueOf(habilidad.daño-rival.defensa+critico(habilidad.numHabilidad))); 
			}
			else {
				if(usada.isDmgverdadero()) {
					p2.setVida(p2.getVida()-usada.getDaño()-critico(usada));
				    //System.out.println(nombre+" ha hecho"+String.valueOf(habilidad.daño+critico(habilidad.numHabilidad)));
				    }
				else {
					p2.setVida(p2.getVida()-usada.getDaño()+p2.getDefensa()-critico(usada));
					//System.out.println(nombre+" ha hecho"+String.valueOf(habilidad.daño-rival.defensa+critico(habilidad.numHabilidad)));
				
					}
			}
			p1.setEnergia(p1.getEnergia()-usada.getCoste());
			
			}
			
		else {
			System.out.println("¡Oh,"+p1.getNombre()+" ha fallado!");
		}}
        else {
        	System.out.println("No tiene energía para atacar.");
        }
       
        
		}
	
	public void UsarPocion(Personaje p1) {

		int curacion = inventario.getPocionesList().get(0).getIncremento();

		if (p1.getVidaTotal() == p1.getVida()) {
			System.out.println("La vida está completa, no se puede usar una poción");
		} else if (p1.getVidaTotal() - p1.getVida() > curacion) {
			p1.setVida(p1.getVida() + curacion);
			inventario.getPocionesList().remove(0);
		} else {
			p1.setVida(p1.getVida() + curacion - (curacion - (p1.getVidaTotal() - p1.getVida())));
			inventario.getPocionesList().remove(0);

		}
	}
	
	public void UsarElixir(Personaje p1) {
		int recarga=inventario.getElixiresList().get(0).getIncremento();
		
		if(p1.getEnergiaTotal()==p1.getEnergia()) {
			System.out.println("La Energía está completa, no se puede usar una poción");
		}
		else if(p1.getEnergiaTotal()-p1.getEnergia()>recarga){
			p1.setEnergia(p1.getEnergia()+recarga);
			inventario.getElixiresList().remove(0);
		}
		else {
			p1.setEnergia(p1.getEnergia()+recarga-(recarga-(p1.getEnergiaTotal()-p1.getEnergia())));
			inventario.getElixiresList().remove(0);
			
		}
	}
	
	public void UsarVial(Personaje p1) {

		p1.setDefensa(p1.getDefensa() + inventario.getVialesList().get(0).getIncremento());
		inventario.getVialesList().remove(0);

	}

    public void cambiarturno() {
		// TODO Auto-generated method stub
    	
    	if(turno) {
    		turno=false;
    	}
    	else {
    		turno=true;
    	}
		
	}

	private static int critico(Habilidad usada) {
	
	int random=(int) (Math.random()*100);
	int daño=0;
	
	if (random>85) {
	daño=usada.getCritico();
	
	//System.out.println(nombre+" ha hecho critico!");
	
	System.out.println("+"+daño+" de daño");
	}
	
	
	
	return daño;
	}

// TENGO QUE CAMBIAR ESTA FUNCIÓN	

	private static void robarvida(Habilidad usada,Personaje p1, Personaje rival) {
		if (usada.getTipoRobovida()==TiposRobosVida.VidaActual) {
			p1.setVida( (int)(p1.getVida()+p1.getVida()*usada.getPorcentajeRV()));
		System.out.println(p1.getNombre()+"ha robado vida 1");
		}//Robo vida en base a la vida actual
		else if(usada.getTipoRobovida()==TiposRobosVida.VidaEnemigaActual) {
			p1.setVida((int) (p1.getVida()+rival.getVida()*usada.getPorcentajeRV()));
			System.out.println(p1.getNombre()+"ha robado vida 2");
			
		} //Robo vida en base a la vida actual del enemigo
		else if(usada.getTipoRobovida()==TiposRobosVida.VidaPropiaMaxima) {
			p1.setVida((int) (p1.getVida()+p1.getVidaTotal()*usada.getPorcentajeRV()));
			System.out.println(p1.getNombre()+"ha robado vida 3");
			//Robo vida en base a vida max. 
		}
		else if(usada.getTipoRobovida()==TiposRobosVida.VidaEnemigaMaxima) {
			System.out.println(p1.getVida());
			System.out.println(rival.getVidaTotal());
			System.out.println(usada.getPorcentajeRV());
			p1.setVida((int) (p1.getVida()+rival.getVidaTotal()*usada.getPorcentajeRV()));
			System.out.println(p1.getNombre()+"ha robado vida 4");
			}//Robo vida en base a la vida máx enemiga.
		else if(usada.getTipoRobovida()==TiposRobosVida.VidaFaltantePropia) {
			p1.setVida((int) (p1.getVida()+(p1.getVidaTotal()-p1.getVida())*usada.getPorcentajeRV()));
			System.out.println(p1.getNombre()+"ha robado vida 5");
			}
		//Robo vida en base a la vida que falta
		else if(usada.getTipoRobovida()==TiposRobosVida.VidaFaltanteEnemiga) {
			p1.setVida((int) (p1.getVida()+(rival.getVidaTotal()-rival.getVida())*usada.getPorcentajeRV()));
		    System.out.println(p1.getNombre()+"ha robado vida 6");
		    }
		else {
			
		}
	}

	public static void turno(Personaje p1, Personaje p2) {
		
		
		if(p1.getVelocidad() >= p2.getVelocidad())
		{
			turno= true;
		}
		else {
			turno = false;
		}
		
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
	
	
	

}
