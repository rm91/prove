package prove;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
public class Sistema {
 
  public static void main(String argv[]) {
 
    try {
 
    int giornata=32;
    
    
    ArrayList<GiocatoreRosa> rosa = new ArrayList<GiocatoreRosa>();		
    SettaggioTitolarità ctrl = new SettaggioTitolarità(giornata);
    rosa = ctrl.getRosaAggiornata();		//rosa di giocatori con parametro di titolarità settato a 1 o a 0
    
	File fXmlFile = new File("../Statistiche/Statistiche_Fantagazzetta_(NAPOLI)_alla_"+String.valueOf(giornata-1)+"_Giornata.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	doc.getDocumentElement().normalize();
  
	NodeList nList = doc.getElementsByTagName("Row");
 	
	ArrayList<Portiere> goalkeeper = new ArrayList<Portiere>();
	ArrayList<Difensori> defender = new ArrayList<Difensori>();
	ArrayList<Centrocampisti> midfielder = new ArrayList<Centrocampisti>();
	ArrayList<Attaccanti> forward = new ArrayList<Attaccanti>();
	int i=0,j=0,z=0,k=0;
	for(int w=0; w<rosa.size(); w++) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
	  
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
				
				int id = Integer.parseInt(eElement.getAttribute("A"));
				String nome= eElement.getAttribute("C");
				String sq=eElement.getAttribute("D");
				
				if((rosa.get(w).getNome().equals(nome)) && (rosa.get(w).getSquadra().equals(sq))) {
					int presenze=Integer.parseInt(eElement.getAttribute("E"));
					float mediaV=Float.parseFloat(eElement.getAttribute("R"));
					float fantaM=Float.parseFloat(eElement.getAttribute("T"));
					int rigoriP=Integer.parseInt(eElement.getAttribute("M"));
					String ruolo = eElement.getAttribute("B");
					int titolare = rosa.get(w).getTitolare();
					
					CalcolaGoalSquadra data = new CalcolaGoalSquadra(giornata);
					Map<String,Integer> goals = new HashMap<String,Integer>();
					goals=data.getData(sq);
					int gf = goals.get("goalFatti");
					int gs = goals.get("goalSubiti");
					
					CalcoloForma data_forma = new CalcoloForma(giornata);
					float forma = data_forma.getForma(id);					
					
					Avversario adv = new Avversario();
					Map<String,String> res = adv.getAvversario(sq, giornata);
					float SI = adv.getStrongness(res.get("avversario"), giornata);
				
					if(ruolo.equals("P")) {
						CalcoloImbattibilità imb = new CalcoloImbattibilità();
						int imbattibilità = imb.getValue(id,giornata);
						goalkeeper.add(new Portiere(id,nome,presenze,mediaV,fantaM,sq,giornata,rigoriP,imbattibilità,gs,forma,titolare));
						goalkeeper.get(i).setLI(Integer.parseInt(res.get("luogo")));
						goalkeeper.get(i).setSI(SI);
						goalkeeper.get(i).setAI();
						i++;
					}
					
					if(ruolo.equals("D")) {
						int rigoriS = Integer.parseInt(eElement.getAttribute("O"));
						int goal = Integer.parseInt(eElement.getAttribute("H"));
						int assist = Integer.parseInt(eElement.getAttribute("K")) + Integer.parseInt(eElement.getAttribute("L"));
						int ammonizioni = Integer.parseInt(eElement.getAttribute("F"));
						int espulsioni = Integer.parseInt(eElement.getAttribute("G"));
						defender.add(new Difensori(id,nome,presenze,mediaV,fantaM,sq,rigoriS,goal,assist,ammonizioni,espulsioni,giornata,gs,forma,titolare));
						defender.get(j).setLI(Integer.parseInt(res.get("luogo")));
						defender.get(j).setSI(SI);
						defender.get(j).setAI();
						j++;
					}
					
					if(ruolo.equals("C")) {
						int rigoriS = Integer.parseInt(eElement.getAttribute("O"));
						int goal = Integer.parseInt(eElement.getAttribute("H"));
						int assist = Integer.parseInt(eElement.getAttribute("K")) + Integer.parseInt(eElement.getAttribute("L"));
						int ammonizioni = Integer.parseInt(eElement.getAttribute("F"));
						int espulsioni = Integer.parseInt(eElement.getAttribute("G"));		
						midfielder.add(new Centrocampisti(id,nome,presenze,mediaV,fantaM,sq,rigoriS,goal,assist,ammonizioni,espulsioni,giornata,forma,titolare));
						midfielder.get(z).setLI(Integer.parseInt(res.get("luogo")));
						midfielder.get(z).setSI(SI);
						midfielder.get(z).setAI();
						z++;
					}
					
					if(ruolo.equals("A")) {
						int rigoriS = Integer.parseInt(eElement.getAttribute("O"));
						int goal = Integer.parseInt(eElement.getAttribute("H"));
						int assist = Integer.parseInt(eElement.getAttribute("K")) + Integer.parseInt(eElement.getAttribute("L"));
						int ammonizioni = Integer.parseInt(eElement.getAttribute("F"));
						int espulsioni = Integer.parseInt(eElement.getAttribute("G"));		
						forward.add(new Attaccanti(id,nome,presenze,mediaV,fantaM,sq,rigoriS,goal,assist,ammonizioni,espulsioni,giornata,gf,forma,titolare));
						forward.get(k).setLI(Integer.parseInt(res.get("luogo")));
						forward.get(k).setSI(SI);
						forward.get(k).setAI();
						k++;
				}
			}
		}  
	}
}
	
	/*STAMPA DI PROVA 
		for (int g = 0; g < i; g++) {
			System.out.println(goalkeeper.get(g).getCognome()+" "+goalkeeper.get(g).getID()+" "+goalkeeper.get(g).getPresenze()+" "+goalkeeper.get(g).getMediaVoto()+" "+goalkeeper.get(g).getFantaMedia()+" "+ goalkeeper.get(g).getRigori()+" "  +goalkeeper.get(g).getImbattibilità()+" "+(goalkeeper.get(g).getSI()*5-goalkeeper.get(g).getLI())+" " + goalkeeper.get(g).getTitolare() +"-->" + goalkeeper.get(g).getAI());
			System.out.println("\n--------------------------------------------------:\n"); }
		for (int g = 0; g < j; g++) {
			System.out.println(defender.get(g).getCognome()+" "+defender.get(g).getID()+" "+defender.get(g).getPresenze()+" "+defender.get(g).getMediaVoto()+" "+defender.get(g).getFantaMedia()+" " +defender.get(g).getGoal()+" " +(defender.get(g).getSI()*5-defender.get(g).getLI())+ " " + defender.get(g).getTitolare() +"-->" + defender.get(g).getAI());
			System.out.println("\n--------------------------------------------------:\n"); }
		for (int g = 0; g < z; g++) {
			System.out.println(midfielder.get(g).getCognome()+" "+midfielder.get(g).getID()+" "+midfielder.get(g).getPresenze()+" "+midfielder.get(g).getMediaVoto()+" "+midfielder.get(g).getFantaMedia()+" "+ midfielder.get(g).getRigori()+" "  +midfielder.get(g).getGoal()+" " + (midfielder.get(g).getSI()*5-midfielder.get(g).getLI())+" " + midfielder.get(g).getTitolare() +"-->" + midfielder.get(g).getAI());
			System.out.println("\n--------------------------------------------------:\n"); }
		for (int g = 0; g < k; g++) {
			System.out.println(forward.get(g).getCognome()+" "+forward.get(g).getID()+" "+forward.get(g).getPresenze()+" "+forward.get(g).getMediaVoto()+" "+forward.get(g).getFantaMedia()+" "+ forward.get(g).getRigori()+" "  +forward.get(g).getGoal()+" " + (forward.get(g).getSI()*5-forward.get(g).getLI())+" "+ forward.get(g).getTitolare() +"-->" + forward.get(g).getAI());
			System.out.println("\n--------------------------------------------------:\n"); }
		System.out.println("-----------------------------------------------------------------------------------------------");
	*/
	
	CalcolaModulo modulo = new CalcolaModulo(goalkeeper,defender,midfielder,forward);
	String mod = modulo.getModulo(goalkeeper,defender,midfielder,forward);
	Formazione team = new Formazione();
	team.getFormazione(goalkeeper,defender,midfielder,forward,mod);
	
 
	/*
	
	//STAMPA DI PROVA 
	for (int g = 0; g < goalkeeper.size(); g++) {
		System.out.println(goalkeeper.get(g).getCognome()+" "+goalkeeper.get(g).getID()+" "+goalkeeper.get(g).getPresenze()+" "+goalkeeper.get(g).getMediaVoto()+" "+goalkeeper.get(g).getFantaMedia()+" "+ goalkeeper.get(g).getRigori()+" "  +goalkeeper.get(g).getImbattibilità()+" "+(goalkeeper.get(g).getSI()*5-goalkeeper.get(g).getLI())+" " + goalkeeper.get(g).getTitolare() +"-->" + goalkeeper.get(g).getAI());
		System.out.println("\n--------------------------------------------------:\n"); }
	for (int g = 0; g < defender.size(); g++) {
		System.out.println(defender.get(g).getCognome()+" "+defender.get(g).getID()+" "+defender.get(g).getPresenze()+" "+defender.get(g).getMediaVoto()+" "+defender.get(g).getFantaMedia()+" " +defender.get(g).getGoal()+" " +(defender.get(g).getSI()*5-defender.get(g).getLI())+ " " + defender.get(g).getTitolare() +"-->" + defender.get(g).getAI());
		System.out.println("\n--------------------------------------------------:\n"); }
	for (int g = 0; g < midfielder.size(); g++) {
		System.out.println(midfielder.get(g).getCognome()+" "+midfielder.get(g).getID()+" "+midfielder.get(g).getPresenze()+" "+midfielder.get(g).getMediaVoto()+" "+midfielder.get(g).getFantaMedia()+" "+ midfielder.get(g).getRigori()+" "  +midfielder.get(g).getGoal()+" " + (midfielder.get(g).getSI()*5-midfielder.get(g).getLI())+" " + midfielder.get(g).getTitolare() +"-->" + midfielder.get(g).getAI());
		System.out.println("\n--------------------------------------------------:\n"); }
	for (int g = 0; g < forward.size(); g++) {
		System.out.println(forward.get(g).getCognome()+" "+forward.get(g).getID()+" "+forward.get(g).getPresenze()+" "+forward.get(g).getMediaVoto()+" "+forward.get(g).getFantaMedia()+" "+ forward.get(g).getRigori()+" "  +forward.get(g).getGoal()+" " + (forward.get(g).getSI()*5-forward.get(g).getLI())+" "+ forward.get(g).getTitolare() +"-->" + forward.get(g).getAI());
		System.out.println("\n--------------------------------------------------:\n"); }
	System.out.println(mod);
	*/
    } catch (Exception e) {
	e.printStackTrace();
    }
  }
 
}