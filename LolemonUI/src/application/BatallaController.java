package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lolemon.logicaDeNegocio.Clases.Combate;
import lolemon.persistencia.modelo.Personaje;
import model.UsuarioModel;

public class BatallaController implements Initializable {

	// Elementos de la vista del xml
	@FXML
	private BorderPane view;

	@FXML
	private Label labelj1;

	@FXML
	private ProgressBar pb1;

	@FXML
	private ProgressBar pb2;

	@FXML
	private ImageView iv1;

	@FXML
	private Label labelj2;

	@FXML
	private ProgressBar pb3;

	@FXML
	private ProgressBar pb4;

	@FXML
	private ImageView iv2;

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

	// Elementos de la l�gica de negocio

	private Combate combate;
	private Personaje personaje1;
	private Personaje personaje2;

	// Elementos del modelo de la UI
	private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "");
	private ObjectProperty<PersonajeModel> seleccionado1 = new SimpleObjectProperty<>(this, "seleccionado1");
	private ObjectProperty<PersonajeModel> seleccionado2 = new SimpleObjectProperty<>(this, "seleccionado2");
	private ObjectProperty<UsuarioModel> usuarioModel = new SimpleObjectProperty<>(this, "usuario", new UsuarioModel());

	public BatallaController(Personaje p1, Personaje p2) throws IOException {
		
		this.personaje1 = p1;
		this.personaje2 = p2;
		System.out.println(personaje1.getNombre());
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BattleView.fxml"));
		loader.setController(this);
		loader.load();

		// Creamos el combate, y lanzamos el inicio de la pelea.
		combate = new Combate(personaje1, personaje2);
		Combate.turno(personaje1, personaje2);
		Pelea(personaje1, personaje2);

		// Image imagen = new Image("/view/PogChamp.png");
		// view.setCursor(new ImageCursor(imagen));

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Asignarle al ObjectProperty<PersonajeModel> el personajeModel
		// correspondiente, que tendr� sus valores del personaje plano correspondiente.
		PersonajeModel pm = new PersonajeModel();
		seleccionado1.set(pm.convertirAPersonajeModel(personaje1));

		PersonajeModel pm2 = new PersonajeModel();
		seleccionado2.set(pm2.convertirAPersonajeModel(personaje2));

		// Binds
		labelj1.textProperty().bind(seleccionado1.get().nombreProperty());
		labelj2.textProperty().bind(seleccionado2.get().nombreProperty());

		pb1.progressProperty()
				.bind(seleccionado1.get().vidaProperty().multiply(1.0).divide(seleccionado1.get().getVidaTotal()));
		pb2.progressProperty().bind(
				seleccionado1.get().energiaProperty().multiply(1.0).divide(seleccionado1.get().getEnergiaTotal()));

		pb3.progressProperty()
				.bind(seleccionado2.get().vidaProperty().multiply(1.0).divide(seleccionado2.get().getVidaTotal()));
		
		pb4.progressProperty().bind(
				seleccionado2.get().energiaProperty().multiply(1.0).divide(seleccionado2.get().getEnergiaTotal()));

		//setea las imagenes a los campeones y los textos de explicacion
		iv1.setImage(new Image(seleccionado1.get().getSprite()));
		Tooltip.install(
				iv1,
			    new Tooltip(seleccionado1.get().toString())
			);
		iv2.setImage(new Image(seleccionado2.get().getSprite()));
		Tooltip.install(
				iv2,
			    new Tooltip(seleccionado2.get().toString())
			);

		btn1.setOnAction(e -> llamarAtacar(0));
		btn2.setOnAction(e -> llamarAtacar(1));
		btn3.setOnAction(e -> llamarAtacar(2));
		btn4.setOnAction(e -> llamarAtacar(3));

	}

	private void llamarAtacar(int habilidad) {

		if (Combate.getTurno()) {
			HacerAtaque(combate.getJ1().getHabilidades().get(habilidad).getNumHabilidad(), combate.getJ1(),
					combate.getJ2());

		} else {
			HacerAtaque(combate.getJ2().getHabilidades().get(habilidad).getNumHabilidad(), combate.getJ2(),
					combate.getJ1());
		}
	}

	private void HacerAtaque(int habilidad, Personaje j1, Personaje j2) {

		combate.Atacar(j1.getHabilidades().get(habilidad).getNumHabilidad(), j1, j2);
		//ataca y actualiza las vidas y energias de los campeones
		
		seleccionado2.get().vidaProperty().set(combate.getJ2().getVida());
		seleccionado1.get().energiaProperty().set(combate.getJ1().getEnergia());
		seleccionado1.get().vidaProperty().set(combate.getJ1().getVida());
		seleccionado2.get().energiaProperty().set(combate.getJ2().getEnergia());
		
		
		//recarga y luego lo refleja en la vista
		System.out.println("hora de recargar!");
		combate.recargar(j1);
		
		
	    
		seleccionado1.get().energiaProperty().set(combate.getJ1().getEnergia());
		seleccionado2.get().energiaProperty().set(combate.getJ2().getEnergia());

		

		combate.cambiarturno();

		// falta incrementar el contador del turno
		// falta decir cual es la ultima habilidad usada

		Pelea(j1, j2);
	}

	public void Pelea(Personaje p1, Personaje p2) {

		if (p1.getVida() > 0 & p2.getVida() > 0) {
			System.out.println("Vida de " + p1.getNombre() + " " + p1.getVida());
			System.out.println("Energ�a de " + p1.getNombre() + p1.getEnergia());
			System.out.println("Vida de " + p2.getNombre() + " " + p2.getVida());
			System.out.println("Energ�a " + p2.getNombre() + " " + p2.getEnergia());
			show();
			System.out.println("-----------------------");

		} else {
			if (p1.getVida() > 0)
				System.out.println("Ha ganado j1");
			else
				System.out.println("Ha ganado random!!!!!!!!!!");
		}

	}
	
	public void show() {
		//cambia el texto y el tooltip de los botones segun el turno
		if(combate.getTurno()) {
		System.out.println("Turno de "+combate.getJ1().getNombre());
		
		btn1.setText(seleccionado1.get().getHabilidades().get(0).getNombre());
		btn1.setTooltip(new Tooltip("Coste: "+seleccionado1.get().getHabilidades().get(0).getCoste()+
									"\nDa�o: "+seleccionado1.get().getHabilidades().get(0).getDa�o()));
		
		btn2.setText(seleccionado1.get().getHabilidades().get(1).getNombre());
		btn2.setTooltip(new Tooltip("Coste: "+seleccionado1.get().getHabilidades().get(1).getCoste()+
									"\nDa�o: "+seleccionado1.get().getHabilidades().get(1).getDa�o()));
		
		btn3.setText(seleccionado1.get().getHabilidades().get(2).getNombre());
		btn3.setTooltip(new Tooltip("Coste: "+seleccionado1.get().getHabilidades().get(2).getCoste()+
									"\nDa�o: "+seleccionado1.get().getHabilidades().get(2).getDa�o()));
		
		btn4.setText(seleccionado1.get().getHabilidades().get(3).getNombre());
		btn4.setTooltip(new Tooltip("Coste: "+seleccionado1.get().getHabilidades().get(3).getCoste()+
									"\nDa�o: "+seleccionado1.get().getHabilidades().get(3).getDa�o()));
		}
		else {
			System.out.println("Turno de "+combate.getJ2().getNombre());
			btn1.setText(seleccionado2.get().getHabilidades().get(0).getNombre());
			btn1.setTooltip(new Tooltip("Coste: "+seleccionado2.get().getHabilidades().get(0).getCoste()+
					"\nDa�o: "+seleccionado2.get().getHabilidades().get(0).getDa�o()));
			
			btn2.setText(seleccionado2.get().getHabilidades().get(1).getNombre());
			btn1.setTooltip(new Tooltip("Coste: "+seleccionado2.get().getHabilidades().get(1).getCoste()+
					"\nDa�o: "+seleccionado2.get().getHabilidades().get(1).getDa�o()));
			
			btn3.setText(seleccionado2.get().getHabilidades().get(2).getNombre());
			btn1.setTooltip(new Tooltip("Coste: "+seleccionado2.get().getHabilidades().get(2).getCoste()+
					"\nDa�o: "+seleccionado2.get().getHabilidades().get(2).getDa�o()));
			
			btn4.setText(seleccionado2.get().getHabilidades().get(3).getNombre());
			btn1.setTooltip(new Tooltip("Coste: "+seleccionado2.get().getHabilidades().get(3).getCoste()+
					"\nDa�o: "+seleccionado2.get().getHabilidades().get(3).getDa�o()));
		}
		
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
	

}
