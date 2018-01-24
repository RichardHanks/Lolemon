package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Personaje;
import model.UsuarioModel;

public class TiendaController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private ListView<Personaje> campeonesList;

	@FXML
	private Button atrasButton;

	@FXML
	private Label nombreCampeonLabel;

	@FXML
	private Label precioCampeonLabel;

	@FXML
	private Button potas1Button;

	@FXML
	private Button potas2Button;

	@FXML
	private Button potas3Button;

	@FXML
	private Button comprarButton;
	
	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());
	private ListProperty<Personaje> list = new SimpleListProperty<>(this, "list",
			FXCollections.observableArrayList());
	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<Personaje> seleccionado = new SimpleObjectProperty<>(this, "personaje selecionado");
	private Consultas con = new Consultas();

	public TiendaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TiendaView.fxml"));
		loader.setController(this);
		loader.load();
		usuarioModel.addListener((o, ov, nv)->{
			list.get().addAll(con.getCampeonesBloqueados(usuarioModel.get().getNombre()));
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		campeonesList.itemsProperty().bindBidirectional(list);
		campeonesList.setOrientation(Orientation.HORIZONTAL);
		seleccionado.bind(campeonesList.getSelectionModel().selectedItemProperty());
		campeonesList.setCellFactory(new Callback<ListView<Personaje>, ListCell<Personaje>>() {
		
			@Override
			public ListCell<Personaje> call(ListView<Personaje> param) {

				ListCell<Personaje> p = new ListCell<Personaje>() {
					@Override
					protected void updateItem(Personaje item, boolean empty) {
						super.updateItem(item, empty);

						if (item != null) {
							ImageView i = new ImageView(item.getAspecto());
							i.setFitHeight(430);
							i.setFitWidth(230);
							setGraphic(i);
						
						} else {
							setText("");
							setGraphic(null);

						}
					}
				};

				return p;
			}
		});
		
		seleccionado.addListener((o, ov, nv)->{
			if(nv!=null)
			nombreCampeonLabel.setText(nv.getNombre()+" "+nv.getCoste());
			else {
				nombreCampeonLabel.setText("");
			}
		});
		
		atrasButton.setOnAction(e->atras());

	}
	
	private void atras() {
		Main.getPrimaryStage().getScene().setRoot(controller.get().getView());
	}

	public BorderPane getView() {
		return view;
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
	

	public final ObjectProperty<LolemonController> controllerProperty() {
		return this.controller;
	}
	

	public final LolemonController getController() {
		return this.controllerProperty().get();
	}
	

	public final void setController(final LolemonController controller) {
		this.controllerProperty().set(controller);
	}
	
	
	

}
