package lol.CrearCampeonController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.text.html.parser.Entity;

import application.Main;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import lol.mainMenuController.LolemonController;
import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Habilidad;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Usuario;
import model.Modelo;
import model.UsuarioModel;

public class CrearCampeonesController implements Initializable {
	
	@FXML
    private BorderPane view;
	@FXML
    private TextField nombreText;
    @FXML
    private ComboBox<Habilidad> habilidad1Combo;
    @FXML
    private ComboBox<Habilidad> habilidad2Combo;
    @FXML
    private ComboBox<Habilidad> habilidad3Combo;
    @FXML
    private ComboBox<Habilidad> habilidad4Combo;
    @FXML
    private ImageView campeonImage;
    @FXML
    private Button anteriorButton;
    @FXML
    private Button siguienteButton;
    @FXML
    private Button guardarButton;
    @FXML
    private Button atrasButton;
    
    private Consultas consultas = new Consultas();
    //private EntityManager em = consultas.getEm();
    
    private Modelo model = new Modelo();
    
	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());
    private ObjectProperty<LolemonController> mainController = new SimpleObjectProperty<>(this,"");
    private ListProperty<Habilidad> h1 = new SimpleListProperty<>(this, "h1", FXCollections.observableArrayList());
    private ListProperty<Habilidad> h2 = new SimpleListProperty<>(this, "h2", FXCollections.observableArrayList());
    private ListProperty<Habilidad> h3 = new SimpleListProperty<>(this, "h3", FXCollections.observableArrayList());
    private ListProperty<Habilidad> h4 = new SimpleListProperty<>(this, "h4", FXCollections.observableArrayList());
    
	
	public CrearCampeonesController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CrearCampeonView.fxml"));
		loader.setController(this);
		loader.load();
		h1.get().setAll(consultas.getHabilidades(1));
		h2.get().setAll(consultas.getHabilidades(2));
		h3.get().setAll(consultas.getHabilidades(3));
		h4.get().setAll(consultas.getHabilidades(4));
	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		guardarButton.setOnAction(e->guardarCampeon(e));
		atrasButton.setOnAction(e->atras(e));
		habilidad1Combo.itemsProperty().bind(h1);
		habilidad2Combo.itemsProperty().bind(h2);
		habilidad3Combo.itemsProperty().bind(h3);
		habilidad4Combo.itemsProperty().bind(h4);
		
	
	}

	private void atras(ActionEvent e) {
		Main.getPrimaryStage().getScene().setRoot(mainController.get().getView());
	}

	private void guardarCampeon(ActionEvent e) {
	
		
	
	}

	public BorderPane getView() {
		return view;
	}


	public final ObjectProperty<LolemonController> mainControllerProperty() {
		return this.mainController;
	}
	

	public final LolemonController getMainController() {
		return this.mainControllerProperty().get();
	}
	

	public final void setMainController(final LolemonController mainController) {
		this.mainControllerProperty().set(mainController);
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
