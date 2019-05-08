package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;


import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	
	private Map<String, String> backMap; 
	private Graph <String, DefaultEdge> grafo; 
	private List <String> parole; 
	
	public Model() {
		 
		
	}
	
	

	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

   


	public void createGraph(int numeroLettere) {
		//creo l'oggetto grafo
      this.grafo= new SimpleGraph<>(DefaultEdge.class); 
      //aggiungo i vertici
      WordDAO dao= new WordDAO(); 
      this.parole= dao.getAllWordsFixedLength(numeroLettere); 
     
      Graphs.addAllVertices(this.grafo, this.parole);
      
      // aggiungo gli archi
      for (String parola1: this.grafo.vertexSet()) {
    	  for (String parola2 : this.grafo.vertexSet()) {
    		  if(this.esisteConnessione(parola1, parola2)) {
    			  this.grafo.addEdge(parola1, parola2); 
    		  }
    	  }
      }
		
	}
	
	public  boolean esisteConnessione(String s1, String s2) {
		int dimensione= s1.length(); 
		int cont=0; 
		for (int i=0; i<dimensione; i++) {
			if(s1.charAt(i)!=s2.charAt(i)) {
				cont++; 
			}
		}
		if(cont==1) {
			return true; 
		}
	
			return false; 
	}
	

	public List<String> displayNeighbours(String parolaInserita) {
    List <DefaultEdge> archi= new ArrayList<>(); 
	List <String> vertici = new ArrayList<>(); 	
	
	for( DefaultEdge e: this.grafo.edgeSet()) {
		if(parolaInserita.equals(grafo.getEdgeSource(e))|| parolaInserita.equals(grafo.getEdgeTarget(e))) {
			archi.add(e); 
		
			String opposto = Graphs.getOppositeVertex(grafo, e, parolaInserita); 
			vertici.add(opposto); 
			
		}
	}
	
    
		return vertici;
	}
	
	
	
	

	public int findMaxDegree() {
		int max=0; 
		
		for (String v : grafo.vertexSet()) {
			if(grafo.degreeOf(v)>max) {
				max=grafo.degreeOf(v); 
			
			}
		}
		
		return max;
	}
	
	public String verticeMax() {
		int max=0; 
		String verticeMax=""; 
		for (String v : grafo.vertexSet()) {
			if(grafo.degreeOf(v)>max) {
				max=grafo.degreeOf(v); 
				verticeMax= v; 
			}
		}
		
		return verticeMax;
	}



	public List<String> getParole() {
		return parole;
	}
	
	
	
	
	
}
