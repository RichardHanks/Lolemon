package lol.settingsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToggleButton;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Stop;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lol.iniciarSesionController.IniciarSesionController;
import lolemon.logicaDeNegocio.Clases.Contacto;
import model.UsuarioModel;
import music.MusicPlayer;
import reports.ReportController;

public class SettingsController implements Initializable {

	@FXML
	private JFXTabPane view;

	@FXML
	private JFXToggleButton musicaButton;

	@FXML
	private JFXSlider volumenSlider;

	@FXML
	private Label usuarioLabel;

	@FXML
	private Label puntosLabel;

	@FXML
	private TextField correoText;

	@FXML
	private TextField asuntoText;

	@FXML
	private TextArea mensajeArea;
	
	@FXML
    private JFXButton enviarButton;
	
	@FXML
	private Button generarReportbutton;

	private Stage stage;
	private Scene scene;
	private Contacto contacto= new Contacto();

	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());

	public SettingsController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		musicaButton.setSelected(true);
		musicaButton.setOnAction(e -> controlarMusica(e));
		enviarButton.setOnAction(e->enviarCorreo(e));
		generarReportbutton.setOnAction(e->ReportController.makeReport());
		
		IniciarSesionController.musicPlayer.getReproductor().volumeProperty().bind(volumenSlider.valueProperty().divide(100));
		

		usuarioModel.addListener((o, ov, nv) -> {
			usuarioLabel.textProperty().bind(nv.nombreProperty());
			puntosLabel.textProperty().bind(Bindings.concat(nv.puntosProperty()));
		});
		scene = new Scene(view);

	}

	private void enviarCorreo(ActionEvent e) {
		if(contacto.enviar(correoText.getText(), asuntoText.getText(), mensajeArea.getText()));
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Mensaje Enviado");
		alert.setContentText("Mensaje enviado con éxito");
		alert.showAndWait();
	}

	private void controlarMusica(ActionEvent e) {
		if (musicaButton.isSelected())
			IniciarSesionController.musicPlayer.play();
		else
			IniciarSesionController.musicPlayer.stop();
	}

	public void show(Stage parentStage) {

		stage = new Stage();
		if (parentStage != null) {
			stage.initOwner(parentStage);
			stage.getIcons().setAll(parentStage.getIcons());

		}
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle("Settings");
		stage.setScene(scene);
		stage.getScene().getStylesheets().add("/application/application.css");
		stage.setResizable(false);
		stage.showAndWait();

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

}
