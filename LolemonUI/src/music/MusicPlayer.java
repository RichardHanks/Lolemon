package music;
import javafx.beans.property.DoubleProperty;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer {

	private  MediaPlayer reproductor;
	
	public MusicPlayer() {
		reproductor =new MediaPlayer(new Media(MusicPlayer.class.getResource(Musica.POSTGAME.getNombreCancion()).toExternalForm()));
		reproductor.setCycleCount(MediaPlayer.INDEFINITE);
		reproductor.play();
	}

	public void play() {
		reproductor.play();
	}

	public void stop() {
		reproductor.stop();
	}
	
	public void volumen(double volumen) {
		reproductor.setVolume(volumen);
	}
	
	public MediaPlayer getReproductor() {
		return reproductor;
	}
	
	public void setMusica(Musica musica) {
		reproductor= new MediaPlayer(new Media(MusicPlayer.class.getResource(Musica.INICIAL.getNombreCancion()).toExternalForm()));
		
		reproductor.setCycleCount(MediaPlayer.INDEFINITE);
	}
}
