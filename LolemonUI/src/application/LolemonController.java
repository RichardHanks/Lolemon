package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.CommunicationException;

import org.omg.CORBA.PolicyError;

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
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Usuario;
import model.UsuarioModel;

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
	private Label usuarioLabel;

	private ObjectProperty<VerCampeonesController> verCampeonesController = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<CrearCampeonesController> crearChampsController = new SimpleObjectProperty<>(this,
			"crear Champs controller");
	private ObjectProperty<TiendaController> tiendaController = new SimpleObjectProperty<>(this,
			"tienda controller");


	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());

	public LolemonController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PrincipalView.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		verCampeonesButton.setOnAction(e -> verCampeones(e));
		crearCampeonButton.setOnAction(e -> crearCampeones(e));
		tiendaButton.setOnAction(e -> verTienda(e));

		usuarioModel.addListener((o, ov, nv) -> {
			usuarioLabel.textProperty().bind(Bindings.concat("Usuario: ").concat(usuarioModel.get().nombreProperty()
					.concat(" Loles: ").concat(usuarioModel.get().puntosProperty())));
		});
		
	

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
			// verCampeonesController.get().usuarioProperty().bind(usuario);

			Main.getPrimaryStage().getScene().setRoot(verCampeonesController.get().getView());

		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	public BorderPane getView() {
		return view;
	}

	/*
	 * public final ObjectProperty<Usuario> usuarioProperty() { return this.usuario;
	 * }
	 * 
	 * 
	 * public final Usuario getUsuario() { return this.usuarioProperty().get(); }
	 * 
	 * 
	 * public final void setUsuario(final Usuario usuario) {
	 * this.usuarioProperty().set(usuario); }
	 */

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
	

	
	
	

}
