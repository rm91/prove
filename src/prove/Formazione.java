package prove;

import java.util.ArrayList;

public class Formazione {

	public ArrayList<Giocatore> getFormazione(ArrayList<Portiere> portieri, ArrayList<Difensori> difensori, ArrayList<Centrocampisti> centrocampisti, ArrayList<Attaccanti> attaccanti,String modulo) {
		char dif = modulo.charAt(0);
		char cc = modulo.charAt(2);
		char att = modulo.charAt(4);
		
		ordinamentoPort(portieri);
		ordinamentoDif(difensori);
		ordinamentoCc(centrocampisti);
		ordinamentoAtt(attaccanti);
		
		System.out.println("---------------- FORMAZIONE " + modulo +" -------------------------\n\n");
		
		System.out.println(portieri.get(0).getCognome());
		
		System.out.println("\n");

		for(int i=0; i< Integer.parseInt(String.valueOf(dif));i++) {
			System.out.println(difensori.get(i).getCognome());
		}
		System.out.println("\n");
		
		for(int i=0; i<Integer.parseInt(String.valueOf(cc));i++) {
			System.out.println(centrocampisti.get(i).getCognome());
		}
		System.out.println("\n");

		for(int i=0; i<Integer.parseInt(String.valueOf(att));i++) {
			System.out.println(attaccanti.get(i).getCognome());
		}
		System.out.println("\n");
		
		return null;
		
	}
	
	public void ordinamentoPort(ArrayList<Portiere> giocatori) {
		
		Portiere dtmp;
		
		for(int k = 0; k < giocatori.size(); k++) {
			
            boolean flag = false;
            for(int j = 0; j < giocatori.size()-1; j++) {

                if(giocatori.get(j).getAI() < giocatori.get(j+1).getAI()) {
                    dtmp =giocatori.get(j);
      
                    giocatori.set(j,giocatori.get(j+1));
                  
                    giocatori.set(j+1,dtmp);
              
                    flag=true; //Lo setto a true per indicare che è avvenuto uno scambio
                }
            }

            if(!flag) break; //Se flag=false allora vuol dire che nell' ultima iterazione
                             //non ci sono stati scambi, quindi il metodo può terminare
                             //poichè l' array risulta ordinato
        }
	
	}

	
	public void ordinamentoDif(ArrayList<Difensori> giocatori) {
		
		Difensori dtmp;
		
		for(int k = 0; k < giocatori.size(); k++) {
			
            boolean flag = false;
            for(int j = 0; j < giocatori.size()-1; j++) {

                if(giocatori.get(j).getAI() < giocatori.get(j+1).getAI()) {
                    dtmp =giocatori.get(j);
      
                    giocatori.set(j,giocatori.get(j+1));
                  
                    giocatori.set(j+1,dtmp);
              
                    flag=true; //Lo setto a true per indicare che è avvenuto uno scambio
                }
            }

            if(!flag) break; //Se flag=false allora vuol dire che nell' ultima iterazione
                             //non ci sono stati scambi, quindi il metodo può terminare
                             //poichè l' array risulta ordinato
        }
	
	}
	
	public void ordinamentoCc(ArrayList<Centrocampisti> giocatori) {
		
		Centrocampisti dtmp;
		
		for(int k = 0; k < giocatori.size(); k++) {
			
            boolean flag = false;
            for(int j = 0; j < giocatori.size()-1; j++) {

                if(giocatori.get(j).getAI() < giocatori.get(j+1).getAI()) {
                    dtmp =giocatori.get(j);
      
                    giocatori.set(j,giocatori.get(j+1));
                  
                    giocatori.set(j+1,dtmp);
              
                    flag=true; //Lo setto a true per indicare che è avvenuto uno scambio
                }
            }

            if(!flag) break; //Se flag=false allora vuol dire che nell' ultima iterazione
                             //non ci sono stati scambi, quindi il metodo può terminare
                             //poichè l' array risulta ordinato
        }
	
	}


	public void ordinamentoAtt(ArrayList<Attaccanti> giocatori) {
		
		Attaccanti dtmp;
		
		for(int k = 0; k < giocatori.size(); k++) {
			
            boolean flag = false;
            for(int j = 0; j < giocatori.size()-1; j++) {

                if((giocatori.get(j).getAI() < giocatori.get(j+1).getAI())&&(giocatori.get(j).getTitolare()==1)) {
                    dtmp =giocatori.get(j);
      
                    giocatori.set(j,giocatori.get(j+1));
                  
                    giocatori.set(j+1,dtmp);
              
                    flag=true; //Lo setto a true per indicare che è avvenuto uno scambio
                }
            }

            if(!flag) break; //Se flag=false allora vuol dire che nell' ultima iterazione
                             //non ci sono stati scambi, quindi il metodo può terminare
                             //poichè l' array risulta ordinato
        }

	}


}
