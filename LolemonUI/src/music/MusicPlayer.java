package music;

import javafx.scene.media.AudioClip;

public class MusicPlayer {

	private AudioClip inicial;

	public MusicPlayer() {
		inicial = new AudioClip(this.getClass().getResource("/application/fondomusica.mp3").toExternalForm());

	}

	public void playInicial() {
		inicial.setVolume(0.10);
		inicial.play();
	}

	public void stopInicial() {
		inicial.stop();
	}
}
