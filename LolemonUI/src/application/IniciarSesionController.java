package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Usuario;
import model.UsuarioModel;
import music.MusicPlayer;

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

	private Consultas con = new Consultas();
	private MusicPlayer musicPlayer = new MusicPlayer();
	
	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "controller",
			new LolemonController());
	private ObjectProperty<VerCampeonesController> verChampsController = new SimpleObjectProperty<>(this, "ver champs controller",
			new VerCampeonesController());
	private ObjectProperty<CrearCampeonesController> crearChampsController = new SimpleObjectProperty<>(this, "crear Champs controller", 
			new CrearCampeonesController());
	private ObjectProperty<TiendaController> tiendaController = new SimpleObjectProperty<>(this, "tienda controller",
			new TiendaController());
	private ObjectProperty<ChampSelectController> champSelectController = new SimpleObjectProperty<>(this, "champ select", 
			new ChampSelectController());
	private ObjectProperty<SettingsController> settingsController = new SimpleObjectProperty<>(this, "settings controller",
			new SettingsController());
	private ObjectProperty<PostGameController> PostGameController= new SimpleObjectProperty<>(this,"PostGame Controller",
			new PostGameController());
	
	public static ObjectProperty<Usuario> usuario = new SimpleObjectProperty<>();

	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());

	public IniciarSesionController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
		usuario.addListener((o, ov, nv)->{
			if(nv!=null) {
				UsuarioModel u = convertirEnUsuarioModel();
				usuarioModel.set(u);
			}
			
		});
		
		loginButton.setOnAction(e -> login(e));
		loginButton.disableProperty()
				.bind(usuarioText.textProperty().isEmpty().or(passwordText.textProperty().isEmpty()));

		registrarseButton.setOnAction(e->registrarse());
		
		// Bindeos del usuario con properties
		controller.get().usuarioModelProperty().bindBidirectional(usuarioModel);
		verChampsController.get().usuarioModelProperty().bindBidirectional(usuarioModel);
		crearChampsController.get().usuarioModelProperty().bindBidirectional(usuarioModel);
		tiendaController.get().usuarioModelProperty().bindBidirectional(usuarioModel);
		champSelectController.get().usuarioModelProperty().bindBidirectional(usuarioModel);
		settingsController.get().usuarioModelProperty().bindBidirectional(usuarioModel);

		// Bindeos de controladores para cambiar la escena entre ellos
		verChampsController.get().controllerProperty().bindBidirectional(controller);
		crearChampsController.get().mainControllerProperty().bindBidirectional(controller);
		tiendaController.get().controllerProperty().bindBidirectional(controller);
		champSelectController.get().controllerProperty().bindBidirectional(controller);
		PostGameController.get().controllerProperty().bindBidirectional(controller);


		controller.get().verCampeonesControllerProperty().bindBidirectional(verChampsController);
		controller.get().crearChampsControllerProperty().bindBidirectional(crearChampsController);
		controller.get().tiendaControllerProperty().bindBidirectional(tiendaController);
		controller.get().champselectcontrollerProperty().bindBidirectional(champSelectController);
		controller.get().settingsControllerProperty().bindBidirectional(settingsController);
		controller.get().PostGameControllerProperty().bindBidirectional(PostGameController);
		
		//inicia la musica
		musicPlayer.playInicial();
		
		//pasar el postgamecontroller (chambergada)
	    champSelectController.get().setPgcontroller(PostGameController.get());

	}

	private void login(ActionEvent e) {


		usuario.set(con.getUsuario(usuarioText.getText()));
			if (usuario.get().getContraseña().equals(passwordText.getText())) {
				Main.getPrimaryStage().getScene().setRoot(controller.get().getView());
				animarMenu();
			} else
				error();


	}
	
	private void registrarse() {
		RegistrarseController registrarse;
		try {
			registrarse = new RegistrarseController();
			registrarse.show(Main.getPrimaryStage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}

	private void error() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Iniciar sesión");
		alert.setHeaderText("Error al iniciar sesión");
		alert.setContentText("Compruebe que las credenciales son correctas");

		alert.showAndWait();
	}

	private UsuarioModel convertirEnUsuarioModel() {
		UsuarioModel u = new UsuarioModel();
		u.setContraseña(usuario.get().getContraseña());
		u.setHistorial(usuario.get().getHistorial());
		u.setInventario(usuario.get().getInventario());
		u.setNombre(usuario.get().getNombre());
		u.setPersonajes(FXCollections.observableArrayList(usuario.get().getPersonajes()));
		u.setPuntos(usuario.get().getPuntos());
		return u;
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
