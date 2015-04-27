package prove;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.MatchingCoefficient;

import java.io.File;
import java.util.ArrayList;
 

public class SettaggioTitolarità {
	
	private ArrayList<GiocatoreRosa> rosagiocatori;
	private int giornata;

	
	public SettaggioTitolarità(int giornata)
	{
		  rosagiocatori = new ArrayList<GiocatoreRosa>();
		  this.giornata=giornata;

	}
	public void getGiocatoreperRuolo(String ruolo,int number) {
		  try {
			  
			
			  ArrayList<GiocatoreRosa> giocatori = new ArrayList<GiocatoreRosa>();
			  DatabaseRosa rosa = new DatabaseRosa();
			  
			  giocatori = rosa.getGiocatore(ruolo);
			  AbstractStringMetric metric = new MatchingCoefficient();
			  
			  
			  for(int i=0; i<number; i++) {
				  GiocatoreRosa current = giocatori.get(i);
				  File fXmlFile = new File("../ProbabiliFormazioni/Giornata"+giornata+"/"+ current.getSquadra() +".xml");
				  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				  Document doc = dBuilder.parse(fXmlFile);
				  doc.getDocumentElement().normalize();
				  
				  NodeList nList = doc.getElementsByTagName("titolari");
				  NodeList n2List = doc.getElementsByTagName("panchina");
				  NodeList n3List = doc.getElementsByTagName("ballottaggi");
				  NodeList tit = nList.item(0).getChildNodes();
				  NodeList panch = n2List.item(0).getChildNodes();
				  NodeList ballot = n3List.item(0).getChildNodes();
				  
				  
				  /*estrazione titolari */
				  for(int j=0;j<tit.getLength();j++) {
					  Node nodo = (Node) tit.item(j);
					  if(nodo.getNodeType()==1) {
						  String a = nodo.getFirstChild().getNodeValue().toUpperCase();
						  String b = current.getNome().toUpperCase();
						  if(metric.getSimilarity(a, b) >= 0.5) {
							  current.setTitolare(1);
							  rosagiocatori.add(current);
						  }
					  }
				 }
				  
				  /*estrazione panchinari */
				  for(int j=0;j<panch.getLength();j++) {
					  Node nodo = (Node) panch.item(j);
					  if(nodo.getNodeType()==1) {
						  String a = nodo.getFirstChild().getNodeValue().toUpperCase();
						  String b = current.getNome().toUpperCase();
						  if(metric.getSimilarity(a, b) >= 0.5) {
							  for(int z=0;z<ballot.getLength();z++) {
								  Node nodob = (Node) ballot.item(z);
								  if(nodob.getNodeType()==1) {	  
									  String string = nodob.getFirstChild().getNodeValue().toUpperCase();
									  String[] parts = string.split(" [0-9]");
									  String part2 = parts[1]; 
									  String[] parts2 = part2.split("%");
									  String part3 = parts2[0]; 
									  String nome2 = parts2[1]; 
									  if(metric.getSimilarity(current.getNome(), nome2)>=0.5) {
										  current.setTitolare(1); }
									  else {
										  current.setTitolare(0); }
								  }
							  }
							 rosagiocatori.add(current);
						  }
					  }
				 } }
		  } 
		  catch (Exception e) {
				e.printStackTrace();
		    }
	  }
	
	public  ArrayList<GiocatoreRosa> getRosaAggiornata() {
		getGiocatoreperRuolo("P",3);
		getGiocatoreperRuolo("D",8);
		getGiocatoreperRuolo("C",8);
		getGiocatoreperRuolo("A",6);
		
		return rosagiocatori;
	}
	
}
