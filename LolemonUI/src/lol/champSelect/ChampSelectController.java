package lol.champSelect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.script.SimpleScriptContext;

import application.Main;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Duration;
import lol.batallaController.BatallaController;
import lol.iniciarSesionController.IniciarSesionController;
import lol.mainMenuController.LolemonController;
import lol.postGameController.PostGameController;
import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Personaje;
import model.PersonajeModel;
import model.UsuarioModel;
import transiciones.Transiciones;

public class ChampSelectController implements Initializable {

	@FXML
	private ListView<Personaje> campeonesList;
	@FXML
	private ImageView champ1Foto;
	@FXML
	private Label champ1Label;
	@FXML
	private ImageView champ2Foto;
	@FXML
	private Label champ2Label;
	@FXML
	private BorderPane view;
	@FXML
	private Button bloquearButton2;
	@FXML
	private Button bloquearButton1;
	@FXML
	private Label turnoLabel;
	@FXML
	private Button atrasButton;
	@FXML
	private ComboBox<String> mapasCombo;

	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());
	private ObjectProperty<Personaje> champ1 = new SimpleObjectProperty<>(this, "perosnaje 1");
	private ObjectProperty<Personaje> champ2 = new SimpleObjectProperty<>(this, "perosnaje 2");
	private ListProperty<Personaje> listaPersonajes = new SimpleListProperty<>(this, "lista de personajes",
			FXCollections.observableArrayList());
	private StringProperty turno = new SimpleStringProperty(this, "turno", "Primera selección");
	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<BatallaController> Bcontroller = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<PostGameController> pgcontroller = new SimpleObjectProperty<>(this, "");

	private Consultas con = new Consultas();
	private boolean primeraSeleccion = true;

	public ChampSelectController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ChampSelectView.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		campeonesList.itemsProperty().bind(listaPersonajes);
		turnoLabel.textProperty().bind(turno);
		mapasCombo.getItems().addAll("1", "2", "3");
		bloquearButton1.setOnAction(e -> bloqueo());
		bloquearButton2.setOnAction(e -> bloqueo2(e));
		atrasButton.setOnAction(e -> atras(e));
		campeonesList.setOrientation(Orientation.HORIZONTAL);
		campeonesList.setCellFactory(customHorizontalListView());


		campeonesList.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			if (primeraSeleccion)
				champ1.set(nv);
			else
				champ2.set(nv);
		});
		usuarioModel.addListener((o, ov, nv) -> {
			listaPersonajes.setAll(con.getCampeonesDesbloqueados(usuarioModel.get().getNombre()));
		});

		champ1.addListener((o, ov, nv) -> {
			if (nv != null) {
				champ1Label.setText(nv.getNombre());
				champ1Foto.setImage(new Image(champ1.get().getAspecto()));
			} else {
				champ1Label.setText(null);
				champ1Foto.setImage(null);
			}
		});

		champ2.addListener((o, ov, nv) -> {
			if (nv != null) {
				champ2Label.setText(nv.getNombre());
				champ2Foto.setImage(new Image(champ2.get().getAspecto()));
			} else {
				champ2Label.setText(null);
				champ2Foto.setImage(null);
			}
		});

		mapasCombo.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			if (nv == "1") {
				BackgroundImage myBI = new BackgroundImage(new Image("/res/fondolol.jpg"), null, null, null,
						null);
				view.setBackground(new Background(myBI));
			} else if (nv == "2") {
				BackgroundImage myBI = new BackgroundImage(new Image("/res/fondo0.jpg"), null, null, null,
						null);
				view.setBackground(new Background(myBI));
			} else {
				BackgroundImage myBI = new BackgroundImage(new Image("/res/fondoOscuro.jpg"), null, null, null,
						null);
				view.setBackground(new Background(myBI));
			}
		});
		Transiciones.parpadear(1, turnoLabel, -1);

	}

	private void atras(ActionEvent e) {
		Main.getPrimaryStage().getScene().setRoot(controller.get().getView());
		init();
	}

	private void init() {
		reverseTransition();
		listaPersonajes.setAll(con.getCampeonesDesbloqueados(usuarioModel.get().getNombre()));
		champ1.set(null);
		champ2.set(null);
		campeonesList.setDisable(false);
		atrasButton.setDisable(false);
		bloquearButton1.setDisable(false);
		bloquearButton2.setDisable(false);
		turno.set("Primera seleccion");
		primeraSeleccion = true;
		Transiciones.parpadear(1, turnoLabel, -1);

	}

	private Callback<ListView<Personaje>, ListCell<Personaje>> customHorizontalListView() {
		return new Callback<ListView<Personaje>, ListCell<Personaje>>() {

			@Override
			public ListCell<Personaje> call(ListView<Personaje> param) {

				ListCell<Personaje> p = new ListCell<Personaje>() {
					@Override
					protected void updateItem(Personaje item, boolean empty) {
						super.updateItem(item, empty);

						if (item != null) {
							ImageView i = new ImageView(item.getSprite());
							i.setFitHeight(80);
							i.setFitWidth(80);
							setGraphic(i);

						} else {
							setText("");
							setGraphic(null);

						}
					}
				};

				return p;
			}
		};
	}

	private void bloqueo() {
		if(champ1.get()==null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ChampSelect");
			alert.initOwner(Main.getPrimaryStage());
			alert.setHeaderText("Selcciona un campeon");
			alert.showAndWait();
		}else {
		primeraSeleccion = false;
		Transiciones.brilloImagen(champ1Foto, Color.CRIMSON);
		bloquearButton1.setDisable(true);
		turno.set("Segunda selección");
		}
	}

	private void bloqueo2(ActionEvent e) {
		if(champ2.get()==null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ChampSelect");
			alert.initOwner(Main.getPrimaryStage());
			alert.setHeaderText("Selcciona un campeon");
			alert.showAndWait();
		}else {
		Transiciones.brilloImagen(champ2Foto, Color.CRIMSON);
		campeonesList.setDisable(true);
		bloquearButton1.setDisable(true);
		bloquearButton2.setDisable(true);
		cuentaAtras();
		}

	}

	private void cuentaAtras() {
		int startValue = 10;
		turno.set("10");
		try {
			Timeline countdown = new Timeline(
					new KeyFrame(Duration.seconds(1), e -> turno.set(String.valueOf((Integer.valueOf(turno.get()) - 1)))));
			countdown.setCycleCount(startValue);
			countdown.play();
			atrasButton.setDisable(true);
			countdown.setOnFinished(e -> {
				iniciarBatallaController();
			});
		} catch (Exception e) {
			
		}
	
	}

	private void iniciarBatallaController() {

		FadeTransition fd = new FadeTransition();
		fd.setNode(getView());
		fd.setDuration(Duration.seconds(0.5));
		fd.setCycleCount(1);
		fd.setToValue(0);
		fd.setAutoReverse(true);
		fd.playFromStart();
		fd.setOnFinished(e -> {
			try {
				Personaje personaje1 = PersonajeModel.copiarPersonaje(champ1.get());
				Personaje personaje2 = PersonajeModel.copiarPersonaje(champ2.get());
				Bcontroller.set(new BatallaController(personaje1, personaje2, usuarioModel.get()));
				Bcontroller.get().getBatallaBox().setBackground(view.getBackground());
				Bcontroller.get().setPgcontroller(pgcontroller.get());
				Main.getPrimaryStage().getScene().setRoot(Bcontroller.get().getView());	
				init();	
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		
		


	}

	public void reverseTransition() {
		FadeTransition fd = new FadeTransition();
		fd.setNode(getView());
		fd.setDuration(Duration.seconds(0.5));
		fd.setCycleCount(1);
		fd.setToValue(100);
		fd.play();
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

	public final ObjectProperty<PostGameController> pgcontrollerProperty() {
		return this.pgcontroller;
	}

	public final PostGameController getPgcontroller() {
		return this.pgcontrollerProperty().get();
	}

	public final void setPgcontroller(final PostGameController pgcontroller) {
		this.pgcontrollerProperty().set(pgcontroller);
	}

}
