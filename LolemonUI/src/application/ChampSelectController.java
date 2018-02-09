package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.script.SimpleScriptContext;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Duration;
import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Personaje;
import model.UsuarioModel;

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
	private ObjectProperty<Personaje> champ1 = new SimpleObjectProperty<>(this, "perosnaje 1", new Personaje());
	private ObjectProperty<Personaje> champ2 = new SimpleObjectProperty<>(this, "perosnaje 2", new Personaje());
	private ListProperty<Personaje> listaPersonajes = new SimpleListProperty<>(this, "lista de personajes",
			FXCollections.observableArrayList());
	private StringProperty turno = new SimpleStringProperty(this, "turno", "Primera selección");
	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<BatallaController> Bcontroller = new SimpleObjectProperty<>(this, "");

	private Consultas con = new Consultas();
	private boolean primeraSeleccion = true;

	public ChampSelectController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChampSelectView.fxml"));
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
				BackgroundImage myBI = new BackgroundImage(new Image("/application/fondolol.jpg"), null, null, null,
						null);
				view.setBackground(new Background(myBI));
			} else if (nv == "2") {
				BackgroundImage myBI = new BackgroundImage(new Image("/application/fondo0.jpg"), null, null, null,
						null);
				view.setBackground(new Background(myBI));
			} else {
				BackgroundImage myBI = new BackgroundImage(new Image("/application/fondoOscuro.jpg"), null, null, null,
						null);
				view.setBackground(new Background(myBI));
			}
		});

		parpadear(-1);

	}

	private void atras(ActionEvent e) {
		Main.getPrimaryStage().getScene().setRoot(controller.get().getView());
		init();
	}

	private void init() {
		listaPersonajes.setAll(con.getCampeones());
		champ1.set(null);
		champ2.set(null);
		campeonesList.setDisable(false);
		bloquearButton1.setDisable(false);
		bloquearButton2.setDisable(false);
		turno.set("Primera seleccion");
		primeraSeleccion = true;
		parpadear(-1);

	}

	private void parpadear(int time) {
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.0), turnoLabel);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setCycleCount(time);
		fadeTransition.playFromStart();
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
		primeraSeleccion = false;
		brilloImagen(champ1Foto);
		bloquearButton1.setDisable(true);
		turno.set("Segunda selección");
	}

	private void bloqueo2(ActionEvent e) {
		brilloImagen(champ2Foto);
		campeonesList.setDisable(true);
		bloquearButton1.setDisable(true);
		bloquearButton2.setDisable(true);
		cuentaAtras();

	}

	private void cuentaAtras() {
		int startValue = 10;
		turno.set("10");
		Timeline countdown = new Timeline(
				new KeyFrame(Duration.seconds(1), e -> turno.set(String.valueOf((Integer.valueOf(turno.get()) - 1)))));
		countdown.setCycleCount(startValue);
		countdown.play();
		countdown.setOnFinished(e -> {
			iniciarBatallaController();
		});
	}

	private void iniciarBatallaController() {

		FadeTransition fd = new FadeTransition();
		fd.setNode(getView());
		fd.setCycleCount(1);
		fd.setToValue(0);
		fd.playFromStart();
		fd.setOnFinished(e -> {
			try {
				Bcontroller.set(new BatallaController(champ1.get(), champ2.get()));
				Bcontroller.get().getBatallaBox().setBackground(view.getBackground());
				Main.getPrimaryStage().getScene().setRoot(Bcontroller.get().getView());
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
	}

	private void brilloImagen(Node node) {
		int depth = 70;

		DropShadow borderGlow = new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.GOLD);
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);

		node.setEffect(borderGlow);
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
