package prove;

public class Portiere extends Giocatore {
	
	private int rigori;
	private int imbattibilitą;
	private int goalsubiti;
	
	public Portiere() {
		super();
		this.rigori = -1;
		this.imbattibilitą = -1;
		this.goalsubiti = -1;
		
	}
	
	public Portiere(int id, String cognome, int presenze, float mediaVoto, float fantaMedia, String squadra, int giornate, int rigori, int imbattibilitą, int goalsubiti, float forma,int titolare) {
		super(id,cognome,presenze, mediaVoto, fantaMedia,squadra,giornate,forma,titolare);
		this.rigori = rigori;
		this.imbattibilitą = imbattibilitą;
		this.goalsubiti = goalsubiti;
		
	}
	
	void setImbattibilitą(int a) {
		this.imbattibilitą=a;
	}
	
	int getImbattibilitą() {
		return imbattibilitą;
	}
	
	int getRigori() {
		return rigori;
		
	}
	void setAI() {
		this.setAI(2*this.getPresenze()+5*this.getMediaVoto()+3*this.getFantaMedia()+3*this.rigori+(-5*this.getGiornate()/this.goalsubiti)+5*imbattibilitą-5*this.getSI()+this.getLI());
	}

}
