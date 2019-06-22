package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.numeron.model.NumeroModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	
	private NumeroModel model;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox boxControlloPartita;

    @FXML
    private TextField txtRimasti;
    // numero di tedntativi ancora da fare

    @FXML
    private HBox boxControlloTentativi;

    @FXML
    private TextField txtTentativo;

    @FXML
    private TextArea txtMessaggi;

    @FXML
    void handleNuovaPartita(ActionEvent event) {
    	// Gestisce l'inizio di una nuova partita
    	
    	
    	//Gestione interfaccia
    	boxControlloPartita.setDisable(true);
    	boxControlloTentativi.setDisable(false);
    	txtMessaggi.clear();
    	txtTentativo.clear();
    	//txtRimasti.setText(Integer.toString(model.getTMAX()));
    	
    	//comunico al modello di iniziare una nuova partita
    	model.newGame();
    	
    }
    

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	//leggi valore inserito
    	String ts = txtTentativo.getText();
    	
    	// controlla se valido il tipo di dato
    	int tentativo; 
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch (NumberFormatException e) {
    		//Stringa inserita non e' num valido
    		txtMessaggi.appendText("Non e' num valido\n");
    		return;
    	}
    	
    	if(!model.tentativoValido(tentativo)) {
    		txtMessaggi.appendText("Tentativo non valido\n");
    		return;
    	}
    	
    	int risultato = model.tentativo(tentativo);
    	
    	if(risultato == 0) {
    		txtMessaggi.appendText("Complimenti, hai indovinato! in "+model.getTentativiFatti()+"tentativi/n");
    		
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    	}
    	else if(risultato<0) {
    		txtMessaggi.appendText("Tentativo troppo Basso\n");
    	} else {
    		txtMessaggi.appendText("Tentativo troppo Alto\n");
    	}
    	
    	//aggiorna interfaccia tentativi rimasti
    	//txtRimasti.setText(Integer.toString(model.getTMAX()-model.getTentativiFatti()));
    	
    	if(!model.isInGioco()) {
    		//la partita e' finita
    		
    		if(risultato !=0) {
    			txtMessaggi.appendText("Hai perso");
    			txtMessaggi.appendText("\n il numero segreto era:"+model.getSegreto());
    			boxControlloPartita.setDisable(false);
    			boxControlloTentativi.setDisable(true);
    		}
    	}
    }

    @FXML
    void initialize() {
        assert boxControlloPartita != null : "fx:id=\"boxControlloPartita\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Numero.fxml'.";
        assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Numero.fxml'.";

    }
    
    public void setModel(NumeroModel model) {
    	this.model = model;
    	
    	txtRimasti.accessibleTextProperty().bind(Bindings.convert(model.tentativiFattiProperty()));
    }
}


  