package lol.mainMenuController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.CommunicationException;

import org.omg.CORBA.PolicyError;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import lol.CrearCampeonController.CrearCampeonesController;
import lol.Perfilcontroller.PerfilController;
import lol.champSelect.ChampSelectController;
import lol.postGameController.PostGameController;
import lol.settingsController.SettingsController;
import lol.tiendaController.TiendaController;
import lol.verCampeonesController.VerCampeonesController;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Usuario;
import model.UsuarioModel;
import transiciones.Transiciones;

public class LolemonController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private Button verCampeonesButton;

	@FXML
	private Button jugarButton;

	@FXML
	private Button crearCampeonButton;

	@FXML
	private Button tiendaButton;

	@FXML
	private Button settingsButton;

	@FXML
	private Button perfilButton;

	@FXML
	private Label usuarioLabel;

	@FXML
	private Button cambiarUsuarioButton;

	private ObjectProperty<VerCampeonesController> verCampeonesController = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<CrearCampeonesController> crearChampsController = new SimpleObjectProperty<>(this,
			"crear Champs controller");
	private ObjectProperty<TiendaController> tiendaController = new SimpleObjectProperty<>(this, "tienda controller");
	private ObjectProperty<ChampSelectController> champselectcontroller = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<SettingsController> settingsController = new SimpleObjectProperty<>(this,
			"settings controller");
	private ObjectProperty<PostGameController> PostGameController = new SimpleObjectProperty<>(this,
			"PostGame Controller");
	private ObjectProperty<PerfilController> perfilController = new SimpleObjectProperty<>(this, "Perfil controller");

	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());

	public LolemonController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalView.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cambiarUsuarioButton.setOnAction(e->cambiarUsuario(e));
		jugarButton.setOnAction(e -> jugar(e));
		verCampeonesButton.setOnAction(e -> verCampeones(e));
		crearCampeonButton.setOnAction(e -> crearCampeones(e));
		tiendaButton.setOnAction(e -> verTienda(e));
		settingsButton.setOnAction(e -> settings(e));
		perfilButton.setOnAction(e -> verPerfil(e));

		usuarioModel.addListener((o, ov, nv) -> {
			usuarioLabel.textProperty().bind(Bindings.concat("Usuario: ").concat(usuarioModel.get().nombreProperty()
					.concat(" Loles: ").concat(usuarioModel.get().puntosProperty())));
		});

	}

	private void cambiarUsuario(ActionEvent e) {
		
		 FadeTransition ft = new FadeTransition();
	      ft.setNode(view);
	      ft.setDuration(new Duration(2000));
	      ft.setFromValue(1.0);
	      ft.setToValue(0.0);
	      ft.setCycleCount(1);
	      ft.setOnFinished(e2->{
	    	  Main.getPrimaryStage().getScene().setRoot(Main.getIniciarSesionController().getView());
	    	  Transiciones.fadeIn(view);
	      });
	      ft.playFromStart();
	      
		
	}


	private void settings(ActionEvent e) {
		settingsController.get().show(Main.getPrimaryStage());

	}

	private void jugar(ActionEvent e) {
		Main.getPrimaryStage().getScene().setRoot((champselectcontroller.get().getView()));
	}

	private void verPerfil(ActionEvent e) {
		try {
			Main.getPrimaryStage().getScene().setRoot((perfilController.get().getView()));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void verTienda(ActionEvent e) {
		try {
			Main.getPrimaryStage().getScene().setRoot((tiendaController.get().getView()));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void crearCampeones(ActionEvent e) {
		try {

			Main.getPrimaryStage().getScene().setRoot((crearChampsController.get().getView()));

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void verCampeones(ActionEvent e) {
		try {

			Main.getPrimaryStage().getScene().setRoot(verCampeonesController.get().getView());

		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	public BorderPane getView() {
		return view;
	}

	public final ObjectProperty<VerCampeonesController> verCampeonesControllerProperty() {
		return this.verCampeonesController;
	}

	public final VerCampeonesController getVerCampeonesController() {
		return this.verCampeonesControllerProperty().get();
	}

	public final void setVerCampeonesController(final VerCampeonesController verCampeonesController) {
		this.verCampeonesControllerProperty().set(verCampeonesController);
	}

	public final ObjectProperty<CrearCampeonesController> crearChampsControllerProperty() {
		return this.crearChampsController;
	}

	public final CrearCampeonesController getCrearChampsController() {
		return this.crearChampsControllerProperty().get();
	}

	public final void setCrearChampsController(final CrearCampeonesController crearChampsController) {
		this.crearChampsControllerProperty().set(crearChampsController);
	}

	public final ObjectProperty<UsuarioModel> usuarioModelProperty() {
		return this.usuarioModel;
	}

	public final UsuarioModel getUsuarioModel() {
		return this.usuarioModelProperty().get();
	}

	public final void setUsuarioModel(final UsuarioModel usuarioModel) {
		this.usuarioModelProperty().set(usuarioModel);
	}

	public final ObjectProperty<TiendaController> tiendaControllerProperty() {
		return this.tiendaController;
	}

	public final TiendaController getTiendaController() {
		return this.tiendaControllerProperty().get();
	}

	public final void setTiendaController(final TiendaController tiendaController) {
		this.tiendaControllerProperty().set(tiendaController);
	}

	public final ObjectProperty<ChampSelectController> champselectcontrollerProperty() {
		return this.champselectcontroller;
	}

	public final ChampSelectController getChampselectcontroller() {
		return this.champselectcontrollerProperty().get();
	}

	public final void setChampselectcontroller(final ChampSelectController champselectcontroller) {
		this.champselectcontrollerProperty().set(champselectcontroller);
	}

	public final ObjectProperty<SettingsController> settingsControllerProperty() {
		return this.settingsController;
	}

	public final SettingsController getSettingsController() {
		return this.settingsControllerProperty().get();
	}

	public final void setSettingsController(final SettingsController settingsController) {
		this.settingsControllerProperty().set(settingsController);
	}

	public final ObjectProperty<PostGameController> PostGameControllerProperty() {
		return this.PostGameController;
	}

	public final PostGameController getPostGameController() {
		return this.PostGameControllerProperty().get();
	}

	public final void setPostGameController(final PostGameController PostGameController) {
		this.PostGameControllerProperty().set(PostGameController);
	}

	public final ObjectProperty<PerfilController> perfilControllerProperty() {
		return this.perfilController;
	}

	public final PerfilController getPerfilController() {
		return this.perfilControllerProperty().get();
	}

	public final void setPerfilController(final PerfilController perfilController) {
		this.perfilControllerProperty().set(perfilController);
	}

}
