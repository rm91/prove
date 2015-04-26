package prove;

public class Portiere extends Giocatore {
	
	private int rigori;
	private int imbattibilità;
	private int goalsubiti;
	
	public Portiere() {
		super();
		this.rigori = -1;
		this.imbattibilità = -1;
		this.goalsubiti = -1;
		
	}
	
	public Portiere(int id, String cognome, int presenze, float mediaVoto, float fantaMedia, String squadra, int giornate, int rigori, int imbattibilità, int goalsubiti, float forma,int titolare) {
		super(id,cognome,presenze, mediaVoto, fantaMedia,squadra,giornate,forma,titolare);
		this.rigori = rigori;
		this.imbattibilità = imbattibilità;
		this.goalsubiti = goalsubiti;
		
	}
	
	void setImbattibilità(int a) {
		this.imbattibilità=a;
	}
	
	int getImbattibilità() {
		return imbattibilità;
	}
	
	int getRigori() {
		return rigori;
		
	}
	void setAI() {
		this.setAI(2*this.getPresenze()+5*this.getMediaVoto()+3*this.getFantaMedia()+3*this.rigori+(-5*this.getGiornate()/this.goalsubiti)+5*imbattibilità-5*this.getSI()+this.getLI());
	}

}
