package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lolemon.persistencia.modelo.Personaje;

public class PostGameController implements Initializable {
	
	
	    //Elementos visuales
	    @FXML
	    private BorderPane View;

	    @FXML
	    private ImageView imageViewResult;

	    @FXML
	    private ImageView imageViewChamp;

	    @FXML
	    private Label labelMensaje;

	    @FXML
	    private Label labelPuntos;

	    @FXML
	    private Button BtnTerminar;
	    
	    
	    //Elementos de lógica
	    
	    private IntegerProperty puntosganados= new SimpleIntegerProperty(this,"puntosganados");
	    
	    private ObjectProperty<Personaje> personajeUsado= new SimpleObjectProperty<>(this,"personajeUsado");;
	    
	    private StringProperty imagenResultado= new SimpleStringProperty(this,"imagenResultado");;
	    
	    private IntegerProperty valor= new SimpleIntegerProperty(this,"valor");
	    
	    //Controllers
	    private ObjectProperty<LolemonController> controller = new SimpleObjectProperty<>(this, "");
	

	
	public PostGameController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PostgameView.fxml"));
		loader.setController(this);
	    loader.load();
		
	};


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		puntosganados.addListener((o, ov, nv)->{
			labelPuntos.setText(String.valueOf(nv));
		});
		
		personajeUsado.addListener((o, ov, nv)->{
			imageViewChamp.setImage(new Image(nv.getAspecto()));
			//labelMensaje.setText(nv.getMensajes().get(valor.get()));
		});
		
		imagenResultado.addListener((o, ov, nv)->{
			imageViewResult.setImage(new Image(nv));
		});
		
	;
		
		BtnTerminar.setOnAction(e-> volver());
		
		}
	
	
	
	
	
	private void volver() {
		Main.getPrimaryStage().getScene().setRoot(controller.get().getView());
		
	}

	public BorderPane getView() {
		return View;
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

	public ImageView getImageViewResult() {
		return imageViewResult;
	}

	public void setImageViewResult(ImageView imageViewResult) {
		this.imageViewResult = imageViewResult;
	}

	public ImageView getImageViewChamp() {
		return imageViewChamp;
	}

	public void setImageViewChamp(ImageView imageViewChamp) {
		this.imageViewChamp = imageViewChamp;
	}

	public Label getLabelMensaje() {
		return labelMensaje;
	}

	public void setLabelMensaje(Label labelMensaje) {
		this.labelMensaje = labelMensaje;
	}

	public Label getLabelPuntos() {
		return labelPuntos;
	}

	public void setLabelPuntos(Label labelPuntos) {
		this.labelPuntos = labelPuntos;
	}

	public Button getBtnTerminar() {
		return BtnTerminar;
	}

	public void setBtnTerminar(Button btnTerminar) {
		BtnTerminar = btnTerminar;
	}

	public void setView(BorderPane view) {
		View = view;
	}

	public void setController(ObjectProperty<LolemonController> controller) {
		this.controller = controller;
	}


	public final IntegerProperty puntosganadosProperty() {
		return this.puntosganados;
	}
	


	public final int getPuntosganados() {
		return this.puntosganadosProperty().get();
	}
	


	public final void setPuntosganados(final int puntosganados) {
		this.puntosganadosProperty().set(puntosganados);
	}
	


	public final ObjectProperty<Personaje> personajeUsadoProperty() {
		return this.personajeUsado;
	}
	


	public final Personaje getPersonajeUsado() {
		return this.personajeUsadoProperty().get();
	}
	


	public final void setPersonajeUsado(final Personaje personajeUsado) {
		this.personajeUsadoProperty().set(personajeUsado);
	}
	


	public final StringProperty imagenResultadoProperty() {
		return this.imagenResultado;
	}
	


	public final String getImagenResultado() {
		return this.imagenResultadoProperty().get();
	}
	


	public final void setImagenResultado(final String imagenResultado) {
		this.imagenResultadoProperty().set(imagenResultado);
	}
	


	public final IntegerProperty valorProperty() {
		return this.valor;
	}
	


	public final int getValor() {
		return this.valorProperty().get();
	}
	


	public final void setValor(final int valor) {
		this.valorProperty().set(valor);
	}
	
	
	
	
	
	

}
