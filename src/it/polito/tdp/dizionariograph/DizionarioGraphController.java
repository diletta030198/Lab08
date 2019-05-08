package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

	Model model; 
	int numero; 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInserisciLettere;

    @FXML
    private TextField txtInserisciParola;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private Button btnGradoMax;

    @FXML
    private TextArea txtRestituisci;

    @FXML
    private Button btnReset;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String numeroLettere = this.txtInserisciLettere.getText(); 
    	try {
    numero= 	Integer.parseInt(numeroLettere); 
    	this.model.createGraph(numero);
    	this.txtRestituisci.setText("Grafico creato!\n");
    	} catch(NumberFormatException e) {
    		this.txtRestituisci.setText("Devi inserire un numero!");
    	}
    }

    @FXML
    void doGradoMax(ActionEvent event) {
    	if(this.model.getGrafo()!=null) {
    		int grado = this.model.findMaxDegree(); 
        	this.txtRestituisci.appendText("Il grado massimo del grafo è: "+grado);
    	}
    	else 
    		this.txtRestituisci.appendText("Prima devi creare il grafo!\n");
    }

    @FXML
    void doReset(ActionEvent event) {
     this.txtRestituisci.clear();
     this.txtInserisciLettere.clear();
     this.txtInserisciParola.clear();
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	if(this.model.getGrafo()!=null) {
    	
   String parola=  this.txtInserisciParola.getText(); 
   
   List <String>parole = new ArrayList<>( this.model.getParole()); 
   if(parola.length()!=numero) {
	   this.txtRestituisci.appendText("Devi inserire una parola di "+numero+" lettere\n");
   }
   
   else {
   for (String p: parole) {
	   if(p.equals(parola)) {
		   this.txtRestituisci.appendText( this.model.displayNeighbours(this.txtInserisciParola.getText()).toString()+"\n");
		   break; 
		  
		   
	   }
	   
   }
   this.txtRestituisci.appendText("La parola non è contenuta nel dizionario! \n");
   }}
    	else 
    		this.txtRestituisci.appendText("Prima devi creare il grafo!\n");	
   
   
  
   
    }
    
    public void setModel(Model model) {
		this.model=model; 
		
	}

    @FXML
    void initialize() {
        assert txtInserisciLettere != null : "fx:id=\"txtInserisciLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtInserisciParola != null : "fx:id=\"txtInserisciParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtRestituisci != null : "fx:id=\"txtRestituisci\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
}