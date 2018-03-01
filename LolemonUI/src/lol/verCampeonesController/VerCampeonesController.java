package lol.verCampeonesController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import lol.mainMenuController.LolemonController;
import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Usuario;
import model.UsuarioModel;

public class VerCampeonesController implements Initializable {

	@FXML
	private BorderPane view;
	@FXML
	private ListView<Personaje> campeonesList;
	@FXML
	private Label vidaLabel;
	@FXML
	private Label manaLabel;
	@FXML
	private Label defensaLabel;
	@FXML
	private Label velocidadLabel;
	@FXML
	private Label habilidad1Label;
	@FXML
	private Label habilidad2Label;
	@FXML
	private Label habilidad3Label;
	@FXML
	private Label habilidad4Label;
	@FXML
	private ImageView campeonImage;
	@FXML
	private ComboBox<String> filtroBusquedaCombo;
	@FXML
	private Button atrasbutton;

	private Consultas consultas = new Consultas();

	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "");

	private ListProperty<Personaje> list = new SimpleListProperty<>(this, "list", FXCollections.observableArrayList());
	private ObjectProperty<Personaje> seleccionado = new SimpleObjectProperty<>(this, "seleccionado");
	private StringProperty filtro = new SimpleStringProperty();

	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());

	public VerCampeonesController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaCampeonesView.fxml"));
		loader.setController(this);
		loader.load();
		usuarioModel.addListener((o, ov, nv)->{
			init();
		});

	}

	private void init() {
		list.clear();
		list.setAll(consultas.getCampeones());
		filtroBusquedaCombo.getItems().clear();
		filtroBusquedaCombo.getItems().add("Todos");
		filtroBusquedaCombo.getItems().add("En posesion");
		filtroBusquedaCombo.getItems().add("No en posesión");
		filtroBusquedaCombo.getItems().add("Creaciones propias");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		campeonesList.itemsProperty().bind(list);
		campeonesList.setCellFactory(new Callback<ListView<Personaje>, ListCell<Personaje>>() {

			@Override
			public ListCell<Personaje> call(ListView<Personaje> param) {

				ListCell<Personaje> p = new ListCell<Personaje>() {
					@Override
					protected void updateItem(Personaje item, boolean empty) {
						super.updateItem(item, empty);

						if (item != null) {
							ImageView i = new ImageView(item.getSprite());
							i.setFitHeight(50);
							i.setFitWidth(50);
							setText(item.getNombre());
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

		seleccionado.bind(campeonesList.getSelectionModel().selectedItemProperty());
		seleccionado.addListener((o, ov, nv) -> campeonSeleccionado(nv));
		filtro.bind(filtroBusquedaCombo.getSelectionModel().selectedItemProperty());
		filtro.addListener((o, ov, nv) -> onFiltroChange(nv));
		atrasbutton.setOnAction(e -> atras(e));

	}

	private void atras(ActionEvent e) {
		init();
		Main.getPrimaryStage().getScene().setRoot(controller.get().getView());
	}

	private void onFiltroChange(String nv) {
		if (nv != null) {
			switch (nv) {
			case "Todos":
				campeonesList.getItems().clear();
				list.setAll(consultas.getCampeones());
				break;
			case "En posesion":
				campeonesList.getItems().clear();
				list.setAll(consultas.getCampeonesDesbloqueados(usuarioModel.get().getNombre()));
				break;
			case "No en posesión":
				campeonesList.getItems().clear();
				list.setAll(consultas.getCampeonesBloqueados(usuarioModel.get().getNombre()));
				/*
				 * TODO pendiente de hacer una consulta que devuelva las creaciones propias
				 * seguramente habra que modificar la base de datos
				 */
				break;
			default:
				break;
			}
		}
	}

	private void campeonSeleccionado(Personaje nv) {
		if (nv != null) {
			vidaLabel.setText(nv.getVida().toString());
			manaLabel.setText(nv.getEnergia().toString());
			defensaLabel.setText(nv.getDefensa().toString());
			velocidadLabel.setText(nv.getDefensa().toString());
			habilidad1Label.setText(nv.getHabilidades().get(0).getDescripcion());
			habilidad2Label.setText(nv.getHabilidades().get(1).getDescripcion());
			habilidad3Label.setText(nv.getHabilidades().get(2).getDescripcion());
			habilidad4Label.setText(nv.getHabilidades().get(3).getDescripcion());
			Image im = new Image(nv.getAspecto());
			campeonImage.setImage(im);
		} else {
			vidaLabel.setText("");
			campeonImage.setImage(null);
			manaLabel.setText(null);
			defensaLabel.setText(null);
			velocidadLabel.setText(null);
			habilidad1Label.setText(null);
			habilidad2Label.setText(null);
			habilidad3Label.setText(null);
			habilidad4Label.setText(null);
		}
	}

	public BorderPane getView() {
		return view;
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

}
