package lol.tiendaController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import lol.iniciarSesionController.IniciarSesionController;
import lol.mainMenuController.LolemonController;
import lolemon.consultas.Consultas;
import lolemon.logicaDeNegocio.Clases.Compra;
import lolemon.persistencia.modelo.Item;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Usuario;
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

	private Compra compra;

	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());
	private ListProperty<Personaje> list = new SimpleListProperty<>(this, "list", FXCollections.observableArrayList());
	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<Personaje> seleccionado = new SimpleObjectProperty<>(this, "personaje selecionado");
	private Consultas con = new Consultas();

	public TiendaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TiendaView.fxml"));
		loader.setController(this);
		loader.load();
		compra = new Compra(null);
		usuarioModel.addListener((o, ov, nv) -> {
			init(nv);
			System.out.println(nv.puntosProperty());
		});

	}

	private void init(UsuarioModel nv) {
		list.clear();
		list.get().addAll(con.getCampeonesBloqueados(nv.getNombre()));
		compra.setUsuario(UsuarioModel.convertirEnUsuario(usuarioModel.get()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		campeonesList.itemsProperty().bindBidirectional(list);
		campeonesList.setPlaceholder(new Label("Ya has comprado todos los campeones"));
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

		seleccionado.addListener((o, ov, nv) -> {
			if (nv != null) {
				nombreCampeonLabel.setText(nv.getNombre());
				precioCampeonLabel.setText("Precio: " + nv.getCoste());
			} else {
				nombreCampeonLabel.setText("");
				precioCampeonLabel.setText("");
			}
		});

		atrasButton.setOnAction(e -> atras());
		comprarButton.setOnAction(e -> ComprarCampeon());
		comprarButton.disableProperty().bind(seleccionado.isNull());

		// estoy hay que mirarlo, lo bueno es pasar el item desde una consulta
		potas1Button.setOnAction(e -> comprarPocion());
		potas2Button.setOnAction(e -> comprarElixir());
		potas3Button.setOnAction(e -> comprarVial());

	}

	private void ComprarCampeon() {
		// Faltar�a poner avisos y esas cosas.
		String nombre = seleccionado.get().getNombre();
		Usuario u = compra.comprarPersonaje(seleccionado.get());
		if (u != null) {
			// Setea el usuario al controller principal, este user ya esta persistido
			IniciarSesionController.usuario.set(u);

			alertCompraCampeon(nombre);
		} else {
			alertErrorCompraCampeon(nombre);
		}
	}

	private void comprarPocion() {
		Usuario u = compra.comprarItem(con.getPocion());
		if(u!=null) {
			// Setea el usuario al controller principal, este user ya esta persistido
			IniciarSesionController.usuario.set(u);
			alertCompraPocion();
		}
		else
			alertErrorCompraPocion();


	}

	private void comprarVial() {
		Usuario u = compra.comprarItem(con.getVial());
		if(u!=null) {
			// Setea el usuario al controller principal, este user ya esta persistido
			IniciarSesionController.usuario.set(u);
			alertCompraPocion();
		}
		else
			alertErrorCompraPocion();



	}

	private void comprarElixir() {
		Usuario u = compra.comprarItem(con.getElixir());
		if(u!=null) {
			// Setea el usuario al controller principal, este user ya esta persistido
			IniciarSesionController.usuario.set(u);
			alertCompraPocion();
		}
		else
			alertErrorCompraPocion();


	}

	private void alertErrorCompraPocion() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(Main.getPrimaryStage());
		alert.getDialogPane().getStyleClass().add("myDialog");
		alert.setTitle("Error");
		alert.setHeaderText("Que pena, no tienes suficentes loles");
		alert.showAndWait();
	}

	private void alertCompraPocion() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(Main.getPrimaryStage());
		alert.getDialogPane().getStyleClass().add("myDialog");
		alert.setTitle("Comprado");
		alert.setHeaderText("Tus nuevos loles son :" + usuarioModel.get().getPuntos());
		alert.showAndWait();
	}

	private void atras() {
		init(usuarioModel.get());
		Main.getPrimaryStage().getScene().setRoot(controller.get().getView());
	}

	private void alertErrorCompraCampeon(String nombre) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(Main.getPrimaryStage());
		alert.getDialogPane().getStyleClass().add("myDialog");
		alert.setTitle("Error");
		alert.setHeaderText("No has podido comprar a " + nombre + " Tus Loles no son suficientes :(");
		alert.showAndWait();
	}

	private void alertCompraCampeon(String nombre) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(Main.getPrimaryStage());
		alert.getDialogPane().getStyleClass().add("myDialog");
		alert.setTitle("Comprado");
		alert.setHeaderText("Has comprado a " + nombre + " Tus nuevos loles son :" + usuarioModel.get().getPuntos());
		alert.showAndWait();
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
