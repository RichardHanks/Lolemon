package lol.Perfilcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import lol.mainMenuController.LolemonController;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.RegistroPartida;
import lolemon.persistencia.modelo.Resultado;
import model.RegistroPartidasModel;
import model.UsuarioModel;

public class PerfilController implements Initializable {

	@FXML
	private BorderPane view;
	@FXML
	private Label usuarioLabel, puntosLabel;
	@FXML
	private Label partidasLabel;

	@FXML
	private Label victoriasLabel;

	@FXML
	private Label derrotasLabel;

	@FXML
	private Label pocionesLabel;

	@FXML
	private Label elixiresLabel;

	@FXML
	private Label vialesLabel;

	@FXML
    private Button atrasButton;

	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "");

	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario");

	public PerfilController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		atrasButton.setOnAction(e->Main.getPrimaryStage().getScene().setRoot(controller.get().getView()));
		usuarioModel.addListener((o, ov, nv) -> {
			if (nv != null) {
				usuarioLabel.textProperty().bind(nv.nombreProperty());
				puntosLabel.textProperty().bind(nv.puntosProperty().asString());
				if (nv.getHistorial() != null) {
					partidasLabel.setText(String.valueOf(nv.getHistorial().getNumeroPartidas()));
					victoriasLabel.setText(String.valueOf(nv.getHistorial().getNumeroVictorias()));
					derrotasLabel.setText(String
							.valueOf(nv.getHistorial().getNumeroPartidas() - nv.getHistorial().getNumeroVictorias()));
					pocionesLabel.setText(String.valueOf(nv.getInventario().getPocionesList().size()));
					elixiresLabel.setText(String.valueOf(nv.getInventario().getElixiresList().size()));
					vialesLabel.setText(String.valueOf(nv.getInventario().getVialesList().size()));
				}

			}
			

			// personajeUsadoColumn.setCellValueFactory( value ->
			// value.getValue().personajeUsadoProperty());
			// personajeContrarioColumn.setCellValueFactory( value ->
			// value.getValue().personajeContrarioProperty());
			// duracionColumn.setCellValueFactory(value ->
			// value.getValue().duracionProperty().asString());
			// puntosColumn.setCellValueFactory(value ->
			// value.getValue().puntosGanadosProperty().asString());
			// resultadoColumn.setCellValueFactory(value ->
			// value.getValue().resultadoProperty());
			//

		});

	}

	public final ObjectProperty<LolemonController> controllerProperty() {
		return this.controller;
	}

	public final LolemonController getController() {
		return this.controllerProperty().get();
	}

	public final void setController(final LolemonController controller) {
		this.controllerProperty().set(controller);
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

	public BorderPane getView() {
		return view;
	}

	public void setView(BorderPane view) {
		this.view = view;
	}

}
