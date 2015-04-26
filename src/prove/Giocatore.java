package prove;

public class Giocatore {

	private int ID;
	private String cognome;
	private int presenze;
	private int giornate;
	private float mediaVoto;
	private float fantaMedia;
	private String squadra;
	private float AI;
	private float SI;
	private int LI;
	private float forma;
	private int titolare;
	
	public Giocatore() {
		this.setID(-1);
		this.setCognome("");
		this.setPresenze(-1);
		this.setMediaVoto(-1);
		this.setFantaMedia(-1);
		this.setSquadra("");
		this.setForma(-1);
		this.setTitolare(-1);
		this.setAI(0);
	}
	
	public Giocatore(int id, String cognome, int presenze, float mediaVoto, float fantaMedia, String squadra,int giornate, float forma,int titolare) {
		this.setID(id);
		this.setCognome(cognome);
		this.setPresenze(presenze);
		this.setMediaVoto(mediaVoto);
		this.setFantaMedia(fantaMedia);
		this.setSquadra(squadra);
		this.setAI(0);
		this.giornate=giornate;
		this.titolare=titolare;
		this.setForma(forma);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public int getPresenze() {
		return presenze;
	}

	public void setPresenze(int presenze) {
		this.presenze = presenze;
	}

	public float getMediaVoto() {
		return mediaVoto;
	}

	public void setMediaVoto(float mediaVoto) {
		this.mediaVoto = mediaVoto;
	}

	public float getFantaMedia() {
		return fantaMedia;
	}

	public void setFantaMedia(float fantaMedia) {
		this.fantaMedia = fantaMedia;
	}

	public String getSquadra() {
		return squadra;
	}

	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}

	public float getAI() {
		return AI;
	}

	public void setAI(float aI) {
		AI = aI;
	}

	public int getGiornate() {
		return giornate;
	}

	public void setGiornate(int giornate) {
		this.giornate = giornate;
	}

	public float getSI() {
		return SI;
	}

	public void setSI(float sI) {
		SI = sI;
	}

	public float getLI() {
		return LI;
	}

	public void setLI(int lI) {
		LI = lI;
	}

	public float getForma() {
		return forma;
	}

	public void setForma(float forma) {
		this.forma = forma;
	}

	public int getTitolare() {
		return titolare;
	}

	public void setTitolare(int titolare) {
		this.titolare = titolare;
	}
	
	
}
