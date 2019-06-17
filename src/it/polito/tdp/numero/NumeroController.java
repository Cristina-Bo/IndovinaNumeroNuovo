package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	
	private int segreto;
	int tentativiFatti;
	private boolean inGioco = false;
	

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
    	
    	//Logica gioco
    	this.segreto = (int)(Math.random()*NMAX)+1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	//Gestione interfaccia
    	boxControlloPartita.setDisable(true);
    	boxControlloTentativi.setDisable(false);
    	txtMessaggi.clear();
    	txtRimasti.setText(Integer.toString(this.TMAX));
    	
    }
    

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	//leggi valore inserito
    	String ts = txtTentativo.getText();
    	
    	// controlla se valido
    	int tentativo; 
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch (NumberFormatException e) {
    		//Stringa inserita non e' num valido
    		txtMessaggi.appendText("Non e' num valido\n");
    		return;
    	}
    	
    	tentativiFatti++;
    	
    	// controlla se indovinato
    	//->fine partita
    	if(tentativo == segreto) {
    		txtMessaggi.appendText("Complimenti, hai indovinato! in "+tentativiFatti+"tentativi/n");
    		
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    		this.inGioco = false;
    		return;
    		
    	}
    	
    	//verifica se esaurito tentativi
    	//->fine partita
    	if(tentativiFatti ==TMAX) {
    		txtMessaggi.appendText("HAI PERSo, il num segreto era : "+segreto+"\n");
    		
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    		this.inGioco = false;
    		return;
    		//non va bene fare copia in colla!!!
    	}
    	
    	//infornma troppo alto o basso 
    	//->stampa messaggio
    	if(tentativo <segreto) {
    		txtMessaggi.appendText("Tentativo troppo Basso\n");
    	}else {
    		txtMessaggi.appendText("Tentativo troppo Alto\n");
    	}
    	//aggiorna interfaccia tentativi rimasti
    	txtRimasti.setText(Integer.toString(TMAX-tentativiFatti));

    }

    @FXML
    void initialize() {
        assert boxControlloPartita != null : "fx:id=\"boxControlloPartita\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Numero.fxml'.";
        assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Numero.fxml'.";

    }
}


  