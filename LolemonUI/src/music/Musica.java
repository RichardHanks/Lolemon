package music;

public enum Musica {

	INICIAL("/music/fondomusica.mp3"), BATALLA("/music/batalla.mp3"),
	CHAMPSELECT("/music/champSelect.mp3"), POSTGAME("/music/postGame.mp3");
	
	private String nombreCancion;

	
	private Musica (String nombreClub){
		this.nombreCancion = nombreClub;

	}

	public String getNombreCancion() {
		return nombreCancion;
	}

	
}
