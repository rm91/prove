package prove;

public class Attaccanti extends Giocatore {
	private int rigori;
	private int goal;
	private int assist;
	private int ammonizioni;
	private int espulsioni;
	private int goalfatti;
	
	public Attaccanti() {
		super();
		this.rigori = -1;
		this.goal = -1;
		this.assist = -1;
		this.ammonizioni = -1;
		this.espulsioni = -1;
		this.goalfatti = -1;
	}
	
	public Attaccanti(int id, String cognome, int presenze, float mediaVoto, float fantaMedia, String squadra, int rigori, int goal, int assist, int ammonizioni, int espulsioni,int giornate, int goalfatti,float forma,int titolare) {
		super(id,cognome,presenze, mediaVoto, fantaMedia,squadra,giornate,forma,titolare);
		this.rigori = rigori;	
		this.goal = goal;
		this.assist = assist;
		this.ammonizioni = ammonizioni;
		this.espulsioni = espulsioni;
		this.goalfatti = goalfatti;
	}
	
	
	int getRigori() {
		return rigori;
	}
	
	int getGoal() {
		return goal;
	}
	
	int getAssist() {
		return assist;
	}
	
	int getAmmonizioni() {
		return ammonizioni;
	}
	
	int getEspulsioni() {
		return espulsioni;
	}
	
	void setAI() {
		this.setAI(2*this.getPresenze()+4*this.getMediaVoto()+4*this.getFantaMedia()+(-3*this.ammonizioni)+(-4*this.espulsioni)+5*this.goal+5*this.assist+5*this.rigori+3*(this.goalfatti/this.getGiornate())-5*this.getSI()+this.getLI());
	}

}
