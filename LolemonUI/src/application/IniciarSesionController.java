package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Usuario;
import model.UsuarioModel;

public class IniciarSesionController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private Button registrarseButton;

	@FXML
	private TextField usuarioText;

	@FXML
	private PasswordField passwordText;

	@FXML
	private Button loginButton;

	@FXML
	private Label lolemonLabel;

	private Consultas con = new Consultas();
	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "controller",
			new LolemonController());
	private ObjectProperty<VerCampeonesController> verChampsController = new SimpleObjectProperty<>(this,
			"ver champs controller", new VerCampeonesController());
	private ObjectProperty<CrearCampeonesController> crearChampsController = new SimpleObjectProperty<>(this,
			"crear Champs controller", new CrearCampeonesController());
	private ObjectProperty<TiendaController> tiendaController = new SimpleObjectProperty<>(this, "tienda controller", new TiendaController());

	private ObjectProperty<Usuario> usuario = new SimpleObjectProperty<>(this, "usuario");

	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());

	public IniciarSesionController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginButton.setOnAction(e -> login(e));
		loginButton.disableProperty()
				.bind(usuarioText.textProperty().isEmpty().or(passwordText.textProperty().isEmpty()));

		// Bindeos del usuario con properties
		controller.get().usuarioModelProperty().bindBidirectional(usuarioModel);
		verChampsController.get().usuarioModelProperty().bindBidirectional(usuarioModel);
		crearChampsController.get().usuarioModelProperty().bindBidirectional(usuarioModel);
		tiendaController.get().usuarioModelProperty().bindBidirectional(usuarioModel);
		

		// Bindeos de controladores
		verChampsController.get().controllerProperty().bindBidirectional(controller);
		crearChampsController.get().mainControllerProperty().bindBidirectional(controller);
		tiendaController.get().controllerProperty().bindBidirectional(controller);

		controller.get().verCampeonesControllerProperty().bindBidirectional(verChampsController);
		controller.get().crearChampsControllerProperty().bindBidirectional(crearChampsController);
		controller.get().tiendaControllerProperty().bindBidirectional(tiendaController);
		
		

		
	}

	private void login(ActionEvent e) {
		try {
			usuario.set(con.getUsuario(usuarioText.getText()));
			UsuarioModel u = new UsuarioModel();
			u.setContraseña(usuario.get().getContraseña());
			u.setHistorial(usuario.get().getHistorial());
			u.setInventario(usuario.get().getInventario());
			u.setNombre(usuario.get().getNombre());
			u.setPersonajes(FXCollections.observableArrayList(usuario.get().getPersonajes()));
			u.setPuntos(usuario.get().getPuntos());
			usuarioModel.set(u);
			if (usuario.get().getContraseña().equals(passwordText.getText())) {
				Main.getPrimaryStage().getScene().setRoot(controller.get().getView());
				animarMenu();

			}
		} catch (Exception e2) {
			System.out.println("error");
			e2.printStackTrace();

		}
	}

	private void animarMenu() {
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(900));
		fade.setFromValue(0.0);
		fade.setToValue(1.0);
		fade.setCycleCount(1);
		fade.setNode(controller.get().getView().getCenter());
		fade.play();
	}

	public BorderPane getView() {
		return view;
	}
	
	

}
