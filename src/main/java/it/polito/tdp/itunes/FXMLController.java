/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.itunes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnComponente"
    private Button btnComponente; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnSet"
    private Button btnSet; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA1"
    private ComboBox<Album> cmbA1; // Value injected by FXMLLoader

    @FXML // fx:id="txtDurata"
    private TextField txtDurata; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    /**
     * Richiesta D: Analisi Componente
     * @param event
     */
    @FXML
    void doComponente(ActionEvent event) {
    	Album a1 = cmbA1.getValue() ;
    	if(a1==null) {
    		txtResult.appendText("Seleziona un album\n");
    		return ;
    	}
    	
    	Set<Album> connessa = model.getComponente(a1) ;
    	
    	double somma = 0.0 ;
    	for(Album a: connessa) {
    		somma += a.getDurata() ;
    	}
    	
    	txtResult.appendText("Dimensione componente: "+ connessa.size()+"\n");
    	txtResult.appendText("Durata totale: "+somma+"\n");
    	

    }
    /**
     * Richiesta B-C:
     * Creazione grafo:
     * @param event
     */
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String durataS = txtDurata.getText() ;
    	if(durataS.equals("")) {
    		txtResult.appendText("Valore 'd' obbligatorio\n");
    		return ;
    	}
    	Double duration ;
    	try {
    		duration = Double.parseDouble(durataS) ;
    	} catch(NumberFormatException e) {
    		txtResult.appendText("La durata deve essere un valore numerico\n");
    		return ;
    	}
    	
    	model.creaGrafo(duration);
    	
    	List<Album> albums = model.getAlbum() ;
    	
    	cmbA1.getItems().clear() ;
    	cmbA1.getItems().addAll(albums) ;
    }

    @FXML
    void doEstraiSet(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnComponente != null : "fx:id=\"btnComponente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSet != null : "fx:id=\"btnSet\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDurata != null : "fx:id=\"txtDurata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    
    }

}
