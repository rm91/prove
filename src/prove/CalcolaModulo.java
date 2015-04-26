package prove;

import java.util.ArrayList;

public class CalcolaModulo {
	
	final String[] priorita={"3-4-3","4-3-3","3-5-2","4-4-2","5-3-2","4-5-1","5-4-1","3-6-1"};
	final int[] prioritaDf={3,4,3,4,5,4,5,3};
	final int[] prioritaCc={4,3,5,4,3,5,4,6};
	final int[] prioritaAt={3,3,2,2,2,1,1,1};
	 int countDf;
	 int countCc;
	 int countAt;
	 
	 ArrayList<Difensori> panchinariDf;
	 ArrayList<Centrocampisti> panchinariCc;
	 ArrayList<Attaccanti> panchinariAt;
	 
	 	 
	 public CalcolaModulo(ArrayList<Portiere> portieri, ArrayList<Difensori> difensori, ArrayList<Centrocampisti> centrocampisti, ArrayList<Attaccanti> attaccanti) {
		 panchinariDf = new ArrayList<Difensori>();
		 panchinariCc = new ArrayList<Centrocampisti>();
		 panchinariAt = new ArrayList<Attaccanti>();
		 countDf=countCc=countAt=0;

		 int nd = difensori.size();
		 for(int i=0; i<nd;i++) {
			 if(difensori.get(i).getTitolare()==0) {	//se giocatore non è titolare lo inserisco in array panchina e lo
				panchinariDf.add(difensori.get(i));		//elimino da array titolari
				difensori.remove(i);
				i--;
				nd--;
			 }
			 else										//se titolare incremento contatore
				 countDf++;
			 }
		 
		 int nc = centrocampisti.size();
		 for(int i=0; i<nc;i++) {
			 if(centrocampisti.get(i).getTitolare()==0) {
				panchinariCc.add(centrocampisti.get(i));
				centrocampisti.remove(i);
				i--;
				nc--;
			 }
			 else {
				 countCc++;
				 }
		 }
		 		 
		 int na = attaccanti.size();
		 for(int i=0; i<na;i++) {
			 if(attaccanti.get(i).getTitolare()==0) {
				panchinariAt.add(attaccanti.get(i));
				attaccanti.remove(i);
				i--;
				na--;
			 }
			 else
				 countAt++;
		 }
		
	 }

	 
	 public String getModulo(ArrayList<Portiere> portieri, ArrayList<Difensori> difensori, ArrayList<Centrocampisti> centrocampisti, ArrayList<Attaccanti> attaccanti)  {
		
		 for(int i=0;i<8;i++) {
			 if	((countDf>=prioritaDf[i])&&(countCc>=prioritaCc[i])&&(countAt>=prioritaAt[i]))
				 return priorita[i];
		 }
		 
		 /*se non trova un modulo promuove 1 panchinaro per ruolo */
		 promuoviPanchinaro(portieri, difensori, centrocampisti, attaccanti);
		 
		 /*ricalcolo modulo*/
		 for(int i=0;i<8;i++) {
			 if	((countDf>=prioritaDf[i])&&(countCc>=prioritaCc[i])&&(countAt>=prioritaAt[i]))
				 return priorita[i];
		 }
		 
		 return ("Non è possibile scegliere un modulo adeguato!");	 
	 }
	 
	public void promuoviPanchinaro(ArrayList<Portiere> portieri, ArrayList<Difensori> difensori, ArrayList<Centrocampisti> centrocampisti, ArrayList<Attaccanti> attaccanti) {
		
		if(panchinariDf.size()>0) {
			if(panchinariDf.size()==1)
				difensori.add(panchinariDf.get(0));
			else  {
				Difensori tmp=panchinariDf.get(0);
				for(int i=1;i<panchinariDf.size();i++) {
					if(panchinariDf.get(i).getPresenze()>panchinariDf.get(i-1).getPresenze())
						 tmp=panchinariDf.get(i);
				}
				difensori.add(tmp);
			}
			countDf++;
		}
		
		if(panchinariCc.size()>0) {
			if(panchinariCc.size()==1)
				centrocampisti.add(panchinariCc.get(0));
			else  {
				Centrocampisti tmp=panchinariCc.get(0);
				for(int i=1;i<panchinariCc.size();i++) {
					if(panchinariCc.get(i).getPresenze()>panchinariCc.get(i-1).getPresenze())
						 tmp=panchinariCc.get(i);
				}
				centrocampisti.add(tmp);
			}
			countCc++;
		}
		
		if(panchinariAt.size()>0) {
			if(panchinariAt.size()==1)
				attaccanti.add(panchinariAt.get(0));
			else  {
				Attaccanti tmp=panchinariAt.get(0);
				for(int i=1;i<panchinariAt.size();i++) {
					if(panchinariAt.get(i).getPresenze()>panchinariAt.get(i-1).getPresenze())
						 tmp=panchinariAt.get(i);
				}
				attaccanti.add(tmp);
			}
			countAt++;
		}
	}
	
}
