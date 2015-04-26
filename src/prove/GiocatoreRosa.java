package prove;

public class GiocatoreRosa {
private int titolare;
private int id;
private String nome;
private String ruolo;
private String squadra;

public GiocatoreRosa()
{
	titolare=0;
	id=-1;
	nome=ruolo=squadra="";
}
public GiocatoreRosa(int id,String nome,String ruolo,String squadra)
{
	this.id=id;
	this.nome=nome;
	this.ruolo=ruolo;
	this.squadra=squadra;
}
public String getSquadra() {
	return squadra;
}
public void setSquadra(String squadra) {
	this.squadra = squadra;
}
public String getRuolo() {
	return ruolo;
}
public void setRuolo(String ruolo) {
	this.ruolo = ruolo;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getTitolare() {
	return titolare;
}
public void setTitolare(int titolare) {
	this.titolare += titolare;
}
}