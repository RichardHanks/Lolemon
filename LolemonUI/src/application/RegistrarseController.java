package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Usuario;
import model.UsuarioModel;

public class RegistrarseController implements Initializable {

	private Stage stage;
	private UsuarioModel usuario = new UsuarioModel();
	private Consultas con = new Consultas();

    @FXML
    private VBox view;
    
    @FXML
    private TextField usuarioText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button registrarseButton;
	
	public RegistrarseController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RegistrarseView.fxml"));
		loader.setController(this);
		loader.load();

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usuario.nombreProperty().bind(usuarioText.textProperty());
		usuario.contraseñaProperty().bind(passwordText.textProperty());
		
		registrarseButton.setOnAction(e->registrarse(e));

	}



	private void registrarse(ActionEvent e) {
		boolean registrado=con.insertarUsuario(usuario.getNombre(), usuario.getContraseña());
		if(registrado) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Registro");
			alert.setContentText("Registro correcto.");
			alert.showAndWait();
			stage.close();
		}
	}

	public void show(Stage parentStage) {

		stage = new Stage();
		if (parentStage != null) {
			stage.initOwner(parentStage);
			stage.getIcons().setAll(parentStage.getIcons());

		}
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle("Editar contacto");
		stage.setScene(new Scene(view));
		stage.setResizable(false);
		
		stage.showAndWait();

	}

	public VBox getView() {
		return view;
	}

	

}
