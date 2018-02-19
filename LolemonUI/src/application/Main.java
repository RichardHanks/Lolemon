package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import lolemon.persistencia.base.Conexion;
import lolemon.persistencia.base.Creacion;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	private IniciarSesionController controller;
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		try {
			controller = new IniciarSesionController();
			Scene scene = new Scene(controller.getView(), 1200, 700);
			scene.getStylesheets().add("/application/application.css");
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icono.png")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		
		
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
}
