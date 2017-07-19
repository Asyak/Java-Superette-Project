package Serie61;

import java.io.Serializable;
import Utils.DateUser;

public class Facture61<String> implements Serializable {

	private String numFact;
	private DateUser dateFact;
	private String contenu;
	
	// Constructeur par defaut
	public Facture61() {
		
	}
	
	public Facture61(String contenu, DateUser dateFact) {
		this.contenu=contenu;
		this.dateFact = dateFact;
	}
	
	public String getNumFact() {
		return this.numFact;
	}
	
	public void setNumFact(String num) {
		this.numFact = num;
	}
	

	
	public DateUser getDate() {
		return dateFact;
	}
	
	public String getContenu() {
		return contenu;
	}
	

	public java.lang.String toString() {
		return (java.lang.String) getContenu();
	}

}
