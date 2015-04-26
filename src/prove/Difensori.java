package prove;

public class Difensori extends Giocatore{
	private int goal;
	private int assist;
	private int ammonizioni;
	private int espulsioni;
	private int goalsubiti;
	
	public Difensori() {
		super();
		this.goal = -1;
		this.assist = -1;
		this.ammonizioni = -1;
		this.espulsioni = -1;
		this.goalsubiti = -1;
	}
	
	public Difensori(int id, String cognome, int presenze, float mediaVoto, float fantaMedia, String squadra, int rigori, int goal, int assist, int ammonizioni, int espulsioni,int giornate, int goalsubiti,float forma,int titolare) {
		super(id,cognome,presenze, mediaVoto, fantaMedia,squadra,giornate,forma,titolare);
		this.goal = goal;
		this.assist = assist;
		this.ammonizioni = ammonizioni;
		this.espulsioni = espulsioni;
		this.goalsubiti = goalsubiti;
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
		this.setAI(4*this.getPresenze()+5*this.getMediaVoto()+3*this.getFantaMedia()+(-3*this.ammonizioni)+(-4*this.espulsioni)+5*this.goal+5*this.assist +(-3*this.getGiornate()/this.goalsubiti)-5*this.getSI()+this.getLI());
	}

}


