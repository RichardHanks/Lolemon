package transiciones;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Transiciones {
	private static Random random= new Random();
	
	public static void parpadear(double time, Node iv22, int ciclecount) {

		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(time), iv22);		
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setCycleCount(ciclecount);
		fadeTransition.setAutoReverse(true);
		fadeTransition.playFromStart();
		
	}

	public static void lloverCirculos(BorderPane view) {
		Circle c[] = new Circle[2000];

		for (int i = 0; i < 100; i++) {
			c[i] = new Circle(1, 1, 1);
			c[i].setRadius(random.nextDouble() * 1.5);
			Color color = Color.rgb(255, 255, 255, random.nextDouble());
			c[i].setFill(color);
			view.getChildren().add(c[i]);
			Transiciones.Raining(c[i]);
		}
	}

	public static void moverAlaDerecha(Node node) {
		TranslateTransition t= new TranslateTransition();
		t.setDuration(Duration.seconds(0.25));
		t.setToX(((ImageView) node).getX()+10);
		t.setAutoReverse(true);
		t.setCycleCount(4);
		t.setNode(node);
		t.playFromStart();
	}
	
	public static void brilloImagen(Node node, Color color) {
		int depth = 1000;

		DropShadow borderGlow = new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(color);
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);

		node.setEffect(borderGlow);
	}
	

	public static void Raining(Circle c) {
		c.setCenterX(random.nextInt(950));// Window width = 950
		int time = 10 + random.nextInt(50);
		TranslateTransition t = new TranslateTransition();
		t.setNode(c);
		t.setFromY(-200);
		t.setToY(534 + 200);
		t.setToX(random.nextDouble() * c.getCenterX());
		t.setDuration(Duration.seconds(time));
		t.setOnFinished(e -> Raining(c));
		t.playFromStart();
	}
	
	public static void fadeOut(Node node) {
		FadeTransition fade= new FadeTransition();
		fade.setNode(node);
		fade.setCycleCount(1);
		fade.setDuration(Duration.seconds(1));
		fade.setToValue(0);
		fade.playFromStart();
	}
	
	public static void fadeIn(Node node) {
		FadeTransition fade= new FadeTransition();
		fade.setNode(node);
		fade.setCycleCount(1);
		fade.setDuration(Duration.seconds(1));
		fade.setToValue(0.0);
		fade.setToValue(1.0);
		fade.playFromStart();
	}

}
