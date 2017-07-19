package Serie61;

import java.io.Serializable;
import java.util.Hashtable;

import MesInterfaces.InterfaceSt;
import Utils.DateUser;

public class TableDesFactures61 implements InterfaceSt<Facture61<String>, String>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hashtable<String,Facture61<String>> tabFact = 
	new Hashtable <String,Facture61<String>>();

	// Constructeur par default
	public TableDesFactures61(){
		
	}
	
	public TableDesFactures61(Hashtable<String, Facture61<String>> tabFact){
		this.tabFact=tabFact;
	}
	
	public int taille() {
		return tabFact.size();
	}

	
	public Facture61<String> retourner(String numero) {
		return tabFact.get(numero);
	}

	public void supprimer(String numFact) {
		tabFact.remove(numFact);
		
	}

	public String toString() {
		String st = "\n\t *** LISTE DES FACTURES ***\n\n";
		for (Facture61<String> fact: tabFact.values()) {
			st = st + fact.toString()+"\n\n";
		}
		return st;
		
	}
	
	public String cle() {
		String st = "\n\t *** LISTE DES NUMEROS DE FACTURES ENREGISTRES ***\n";
		for (String num: tabFact.keySet()) {
			st = st+ "\n\t N°" + num+ " * Date Facture: "+tabFact.get(num).getDate().toString()+ " * ";
		}
		return st;
	}
	

	public void ajouter(String numFact, Facture61<String> fact) {
		if(!tabFact.contains(numFact)) {
			tabFact.put(numFact,fact);
		}
	}

	@Override
	public void ajouter(Facture61<String> metier) {
		// TODO Auto-generated method stub
		
	}
	
}
