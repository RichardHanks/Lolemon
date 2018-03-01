package lol.batallaController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ResourceBundle;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lol.iniciarSesionController.IniciarSesionController;
import lol.mainMenuController.LolemonController;
import lol.postGameController.PostGameController;
import lolemon.consultas.Consultas;
import lolemon.logicaDeNegocio.Clases.Combate;
import lolemon.persistencia.modelo.Personaje;
import lolemon.persistencia.modelo.Tipo;
import model.PersonajeModel;
import model.UsuarioModel;
import transiciones.Transiciones;

public class BatallaController implements Initializable {

	// Elementos de la vista del xml
	private final String VICTORYBACKROUND="-fx-background-image: url(\"/res/victory.png\");\r\n" + 
			"	-fx-background-size: stretch;\r\n" + 
			"	-fx-background-repeat: no-repeat;";
	private final String DEFEATBACKROUND="-fx-background-image: url(\"/res/defeat.png\");\r\n" + 
			"	-fx-background-size: stretch;\r\n" + 
			"	-fx-background-repeat: no-repeat;";
	@FXML
    private BorderPane view;

	
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btnItem1;

    @FXML
    private Button btnItem2;

    @FXML
    private Button btnItem3;

    @FXML
    private HBox batallaBox;

    @FXML
    private Label labelj1;

    @FXML
    private Label pj1vidaLabel;

    @FXML
    private ProgressBar pb1;

    @FXML
    private Label pj1manaLabel;

    @FXML
    private ProgressBar pb2;

    @FXML
    private ImageView iv1;

    @FXML
    private ImageView p1shieldimg;

    @FXML
    private Label alertasLabel;

    @FXML
    private Label labelj2;

    @FXML
    private Label pj2vidaLabel;

    @FXML
    private ProgressBar pb3;

    @FXML
    private Label pj2manaLabel;

    @FXML
    private ProgressBar pb4;

    @FXML
    private ImageView iv2;

    @FXML
    private ImageView p2shieldimg;


	// Elementos de la lógica de negocio

	private Combate combate;
	private Personaje personaje1;
	private Personaje personaje2;

	// Elementos del modelo de la UI
	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<PersonajeModel> seleccionado1 = new SimpleObjectProperty<>(this, "seleccionado1");
	private ObjectProperty<PersonajeModel> seleccionado2 = new SimpleObjectProperty<>(this, "seleccionado2");
	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario");
	private ObjectProperty<PostGameController> pgcontroller = new SimpleObjectProperty<>(this, "");

	// Persistencia
	private Consultas con = new Consultas();

	public BatallaController(Personaje p1, Personaje p2, UsuarioModel u) throws IOException {
		usuarioModel.set(u);

		this.personaje1 = p1;
		this.personaje2 = p2;
		System.out.println(personaje1.getNombre());

		FXMLLoader loader = new FXMLLoader(getClass().getResource("BattleView.fxml"));
		loader.setController(this);
		loader.load();

		// Creamos el combate, y lanzamos el inicio de la pelea.
		combate = new Combate(personaje1, personaje2, usuarioModel.get().getInventario());
		Combate.turno(personaje1, personaje2);
		Pelea(personaje1, personaje2);

		// Image imagen = new Image("/view/PogChamp.png");
		// view.setCursor(new ImageCursor(imagen));
		
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pb1.setStyle("-fx-accent: green;");
		pb3.setStyle("-fx-accent: green;");

		// Asignarle al ObjectProperty<PersonajeModel> el personajeModel
		// correspondiente, que tendrá sus valores del personaje plano correspondiente.
		PersonajeModel pm = new PersonajeModel();
		seleccionado1.set(pm.convertirAPersonajeModel(personaje1));

		PersonajeModel pm2 = new PersonajeModel();
		seleccionado2.set(pm2.convertirAPersonajeModel(personaje2));

		// Binds
		labelj1.textProperty().bind(seleccionado1.get().nombreProperty());
		labelj2.textProperty().bind(seleccionado2.get().nombreProperty());

		pj1vidaLabel.textProperty().bind(Bindings.concat(seleccionado1.get().vidaProperty()));
		pj1manaLabel.textProperty().bind(Bindings.concat(seleccionado1.get().energiaProperty()));
		pj2vidaLabel.textProperty().bind(Bindings.concat(seleccionado2.get().vidaProperty()));
		pj2manaLabel.textProperty().bind(Bindings.concat(seleccionado2.get().energiaProperty()));

		pb1.progressProperty()
				.bind(seleccionado1.get().vidaProperty().multiply(1.0).divide(seleccionado1.get().getVidaTotal()));
		pb2.progressProperty().bind(
				seleccionado1.get().energiaProperty().multiply(1.0).divide(seleccionado1.get().getEnergiaTotal()));

		pb3.progressProperty()
				.bind(seleccionado2.get().vidaProperty().multiply(1.0).divide(seleccionado2.get().getVidaTotal()));

		pb4.progressProperty().bind(
				seleccionado2.get().energiaProperty().multiply(1.0).divide(seleccionado2.get().getEnergiaTotal()));

		// setea las imagenes a los campeones y los textos de explicacion
		iv1.setImage(new Image(seleccionado1.get().getSprite()));
		Tooltip.install(iv1, new Tooltip(seleccionado1.get().toString()));
		iv2.setImage(new Image(seleccionado2.get().getSprite()));
		Tooltip.install(iv2, new Tooltip(seleccionado2.get().toString()));

		btn1.setOnAction(e -> llamarAtacar(0));
		btn2.setOnAction(e -> llamarAtacar(1));
		btn3.setOnAction(e -> llamarAtacar(2));
		btn4.setOnAction(e -> llamarAtacar(3));
		btnItem1.setOnAction(e -> UsarItem(Tipo.VIDA));
		btnItem2.setOnAction(e -> UsarItem(Tipo.ENERGIA));
		btnItem3.setOnAction(e -> UsarItem(Tipo.DEFENSA));

	}

	private void llamarAtacar(int habilidad) {

		if (Combate.getTurno()) {
			HacerAtaque(combate.getJ1().getHabilidades().get(habilidad).getNumHabilidad(), combate.getJ1(),
					combate.getJ2());
					Transiciones.moverAlaDerecha(iv1);
					Transiciones.parpadear(0.25, iv2, 4);
		} else {
			HacerAtaque(combate.getJ2().getHabilidades().get(habilidad).getNumHabilidad(), combate.getJ2(),
					combate.getJ1());
			Transiciones.moverAlaDerecha(iv2);
			Transiciones.parpadear(0.25, iv1, 4);
		}
	}



	private void HacerAtaque(int habilidad, Personaje j1, Personaje j2) {

		alertasLabel.setText(combate.Atacar(j1.getHabilidades().get(habilidad).getNumHabilidad(), j1, j2));
		// ataca y actualiza las vidas y energias de los campeones

		seleccionado2.get().vidaProperty().set(combate.getJ2().getVida());
		seleccionado1.get().energiaProperty().set(combate.getJ1().getEnergia());
		seleccionado1.get().vidaProperty().set(combate.getJ1().getVida());
		seleccionado2.get().energiaProperty().set(combate.getJ2().getEnergia());

		// recarga y luego lo refleja en la vista
		System.out.println("hora de recargar!");
		combate.recargar(j1);

		seleccionado1.get().energiaProperty().set(combate.getJ1().getEnergia());
		seleccionado2.get().energiaProperty().set(combate.getJ2().getEnergia());

		combate.cambiarturno();

		// falta incrementar el contador del turno

		Pelea(personaje1, personaje2);
	}

	// Revisar el numero de item utilizables durante el combate, ahora mismo solo
	// pueden usar 10 entre los 2.
	private void UsarItem(Tipo tipo) {
			if (tipo == Tipo.VIDA) {
				if (combate.getTurno()) {
					System.out.println(combate.UsarPocion(personaje1));
					usuarioModel.get().getInventario().getPocionesList().remove(0);
					seleccionado1.get().vidaProperty().set(combate.getJ1().getVida());

				} else {
					if (combate.UsarPocion(personaje2)) usuarioModel.get().getInventario().getPocionesList().remove(0);
						seleccionado2.get().vidaProperty().set(combate.getJ2().getVida());
				}

			} else if (tipo == Tipo.ENERGIA) {
				if (combate.getTurno()) {
					combate.UsarElixir(personaje1);
					usuarioModel.get().getInventario().getElixiresList().remove(0);
					seleccionado1.get().energiaProperty().set(combate.getJ1().getEnergia());

				} else {
					combate.UsarElixir(personaje2);
					usuarioModel.get().getInventario().getElixiresList().remove(0);
					seleccionado2.get().energiaProperty().set(combate.getJ2().getEnergia());
				}
			} else {
				if (combate.getTurno()) {
					combate.UsarVial(personaje1);
					usuarioModel.get().getInventario().getVialesList().remove(0);
					seleccionado1.get().defensaProperty().set(combate.getJ1().getDefensa());
					p1shieldimg.setImage(new Image("res/shield.png"));

				} else {
					combate.UsarVial(personaje2);
					usuarioModel.get().getInventario().getVialesList().remove(0);
					seleccionado2.get().defensaProperty().set(combate.getJ2().getDefensa());
					p2shieldimg.setImage(new Image("res/shield.png"));
				}
			}
			combate.setPocionesusadas(combate.getPocionesusadas()+1);
		combate.cambiarturno();
		Pelea(personaje1, personaje2);
	}
	
	public void Pelea(Personaje p1, Personaje p2) {

		if (p1.getVida() > 0 & p2.getVida() > 0) {
			System.out.println("Vida de " + p1.getNombre() + " " + p1.getVida());
			System.out.println("Energía de " + p1.getNombre() + p1.getEnergia());
			System.out.println("Vida de " + p2.getNombre() + " " + p2.getVida());
			System.out.println("Energía " + p2.getNombre() + " " + p2.getEnergia());
			combate.incrementarTurno();
			show();
			System.out.println("-----------------------");

		} else {
			if (p1.getVida() > 0) {
				System.out.println("Ha ganado " + p1.getNombre());
				combate.CalcularPuntosVictoria();
				postGame(true);

			} else {
				System.out.println("Ha ganado " + p2.getNombre());
				combate.CalcularPuntosDerrota();
				postGame(false);

			}
		}

	}

	private void postGame(boolean victoria) {
		usuarioModel.get().setPuntos(usuarioModel.get().getPuntos() + combate.getPuntosGanador());
		if (victoria) usuarioModel.get().getHistorial().setNumeroVictorias(usuarioModel.get().getHistorial().getNumeroVictorias() + 1);
		usuarioModel.get().getHistorial().setNumeroPartidas(usuarioModel.get().getHistorial().getNumeroPartidas() + 1);

		con.actualizarUsuario(UsuarioModel.convertirEnUsuario(usuarioModel.get()));

		IniciarSesionController.usuario.set(con.getUsuario(usuarioModel.get().getNombre()));
		
		if(victoria) {
			pgcontroller.get().getView().setStyle(VICTORYBACKROUND);
			pgcontroller.get().setDmg(combate.getDañoj1());
		}
		else { 
			pgcontroller.get().getView().setStyle(DEFEATBACKROUND);
			pgcontroller.get().setDmg(combate.getDañoj1());
		}
		
		
		pgcontroller.get().setPuntosganados(combate.getPuntosGanador());
		pgcontroller.get().setPersonajeUsado(personaje1);
		pgcontroller.get().setValor(0);

	
	   Main.getPrimaryStage().getScene().setRoot(pgcontroller.get().getView());
	   
		

	}

	public void show() {
		// cambia el texto y el tooltip de los botones segun el turno
		if (combate.getTurno()) {
			System.out.println("Turno de " + combate.getJ1().getNombre());
			cambiarBotones(seleccionado1.get());
			iv2.setEffect(null);
			Transiciones.brilloImagen(iv1, Color.YELLOW);
		} else {
			cambiarBotones(seleccionado2.get());
			iv1.setEffect(null);
			Transiciones.brilloImagen(iv2, Color.YELLOW);
		}

		cambiarBotonesItems(usuarioModel.get());

	}

	private void cambiarBotonesItems(UsuarioModel u) {
		btnItem1.setText("Pociones " + "x" + u.getInventario().getPocionesList().size());
		if (u.getInventario().getPocionesList().size() == 0 | combate.getPocionesusadas()>=3)
			btnItem1.setDisable(true);
		btnItem1.setTooltip(new Tooltip("+20 de vida"));

		btnItem2.setText("Elixires " + "x" + u.getInventario().getElixiresList().size());
		if (u.getInventario().getElixiresList().size() == 0 | combate.getPocionesusadas()>=3)
			btnItem2.setDisable(true);
		btnItem2.setTooltip(new Tooltip("+20 de energia"));

		btnItem3.setText("Viales " + "x" + u.getInventario().getVialesList().size());
		if (u.getInventario().getVialesList().size() == 0 | combate.getPocionesusadas()>=3)
			btnItem3.setDisable(true);
		btnItem3.setTooltip(new Tooltip("+20 de defensa"));
	}

	private void cambiarBotones(PersonajeModel p) {
		
		btn1.setText(p.getHabilidades().get(0).getNombre());
		btn1.setTooltip(new Tooltip(
				"Coste: " + p.getHabilidades().get(0).getCoste() + "\nDaño: " + p.getHabilidades().get(0).getDaño()));

		btn2.setText(p.getHabilidades().get(1).getNombre());
		btn2.setTooltip(new Tooltip(
				"Coste: " + p.getHabilidades().get(1).getCoste() + "\nDaño: " + p.getHabilidades().get(1).getDaño()));

		btn3.setText(p.getHabilidades().get(2).getNombre());
		btn3.setTooltip(new Tooltip(
				"Coste: " + p.getHabilidades().get(2).getCoste() + "\nDaño: " + p.getHabilidades().get(2).getDaño()));

		btn4.setText(p.getHabilidades().get(3).getNombre());
		btn4.setTooltip(new Tooltip(
				"Coste: " + p.getHabilidades().get(3).getCoste() + "\nDaño: " + p.getHabilidades().get(3).getDaño()));
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

	public HBox getBatallaBox() {
		return batallaBox;
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
