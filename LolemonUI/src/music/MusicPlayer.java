package music;
import javafx.beans.property.DoubleProperty;
import javafx.scene.media.AudioClip;

public class MusicPlayer {

	private static AudioClip inicial;

	public MusicPlayer() {
		inicial = new AudioClip(this.getClass().getResource("/application/fondomusica.mp3").toExternalForm());
		inicial.setCycleCount(AudioClip.INDEFINITE);
		inicial.volumeProperty().addListener((o, ov, nv)->{
			stopInicial();
			playInicial();
		});

	}

	public static void playInicial() {
		inicial.play();
	}

	public static void stopInicial() {
		inicial.stop();
	}
	
	public static void volumen(double volumen) {
		inicial.setVolume(volumen);
	}
	
	public static DoubleProperty volumenProperty() {
		return inicial.volumeProperty();
	}
}
