package Serie61;

import java.io.Serializable;
import java.text.DecimalFormat;


import java.util.*;

import MesInterfaces.InterfaceSt;
import Utils.DateUser;

public class UneCommande61<TypeNumeroCommande> implements InterfaceSt <LDC61, Integer>, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateUser datecde= new DateUser();
	private String numCde;
	private Vector<LDC61>tabLDC=new Vector<LDC61>();
	
	private boolean etatFact;
	private String dateFact;
	
	
	public UneCommande61() {
	}
	
	// constructeur
	public UneCommande61(DateUser datCde, String numCde,Vector<LDC61>tabLDC) {
		this.datecde=datCde;
		this.numCde=numCde;		
		this.tabLDC=tabLDC;
		this.etatFact = false;
	}
	
	// getter et setter de var tabLDC
	public Vector<LDC61>getLDC(){
		return tabLDC;
	}
	
	public void setLDC(Vector<LDC61>ldc) {
		this.tabLDC=ldc;
	}
	
	public DateUser getDate() {
		return datecde;
		
	}

	// getter et setter de var numCde
	public String getNumeroCde() {
		return  this.numCde;
	}

	public void setNumeroCommande(String num) {
		this.numCde= num;
	}
	
	// getters et setters de var etatFact
	public boolean getEtatFact() {
		return etatFact;
	}
	
	public void setEtatFact(boolean bool) {
		this.etatFact = bool;
	}
	
	// getters et setters de var dateFact
	
	public String getDateFact() {
		return dateFact;
	}
	
	public void setDateFact() {
		DateUser dateFacture = new DateUser();
		this.dateFact = dateFacture.toString();
	}
	
	
	public void purge(int code) {
		for (LDC61 ldc: tabLDC) {
			if (ldc.getCode() == code)	{tabLDC.remove(ldc); break;}
		}
	}
	
	
	public String etatFact() {
		String etat = "";
		if (getEtatFact() == false) {
			etat = "NON encore FACTUREE";
		} else if (getEtatFact() == true) {
			etat = "déjà FACTUREE";
		}
		return etat;
	}
	
	public String toString() {
      String st = "\n\t *** CONTENU DE LA COMMANDE N°"+ getNumeroCde() +" du " + datecde +" * " +etatFact()+"\n";
          for (LDC61 ldc: tabLDC) {
           	st=st+ldc.toString();
          }
        return st;
	}
	
	public String toStringCours() {		
	      String st = "\n\t *** CONTENU DE LA COMMANDE en cours N° "+ getNumeroCde()+ " du "+datecde+ " * "+etatFact()+" ***\n";
	          for (LDC61 ldc: tabLDC) {
	           	st=st+ldc.toString();
	          }
	        return st;
		}

	
	// cette methode retourne la taille du tableau 
	public int taille () {
		return tabLDC.size();
	}
	

	
	public String facturer(TableArticles61 tabArt, TableDesFactures61 tabFact) {

		DateUser dateFacture = new DateUser();
		String dateF = dateFacture.toString();
		setEtatFact(true);

	
	
		String entete= " *** FACTURE COMMANDE N°"+getNumeroCde()+" date Facture: "+dateF+ " *** \n\n" +
				" CODE       DESIGNATION       QUANTITE       PU       TOTAL HT       TOTAL TTC \n";
		String souligne = "____________________________________________________________________________________________________ \n";
		String detail = "";
	
		for (LDC61 ldc: tabLDC) {
			detail = detail +ldc.facturer(tabArt);

		}
				
		String pied = "\n                                                                                      TOTAL ARTICLES: "  + totalQte()+ "\n"+
		"                                                                                      TOTAL HT:  "+arrondir(totalHT(tabArt))+"\n"+
		"                                                                                      TOTAL TTC: "+arrondir(totalTTC(tabArt))+"\n"+
		"                                                                                      TVA 19,6%:  "+arrondir(calcTVA(tabArt));
		
		String contenu = entete+souligne+detail+souligne+pied;
		Facture61<String> fact = new Facture61<String>(contenu, dateFacture);
		fact.setNumFact(getNumeroCde());
		tabFact.ajouter(getNumeroCde(), fact);
	
		
		return entete+souligne+detail+souligne+pied;
	}
	

	
	public Integer totalQte() {
		Integer res = 0;
		for (LDC61 ldc: tabLDC) {
			res = res +ldc.getQte(); 
		}
		return res;
	}
	
	public float totalHT(TableArticles61 tabArt) {
		float resHT = 0;
		for (LDC61 ldc: tabLDC) {
			ArticleAbstrait art = tabArt.retourner(ldc.getCode());
			resHT = resHT + ldc.prixTotalHT(art);
		}
		return resHT;
	}
	
	
	public float totalTTC(TableArticles61 tabArt) {
		float resTTC = 0;
		for (LDC61 ldc: tabLDC) {
			ArticleAbstrait art = tabArt.retourner(ldc.getCode());
			resTTC = resTTC + ldc.prixTotalTTC(art);
		}
		return resTTC;
	}
	
	public float calcTVA(TableArticles61 tabArt) {
		float TVA = totalTTC(tabArt)-totalHT(tabArt);
		return TVA;	
	}
	
	public static String arrondir(float f) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(1);
		String res = (df.format(f));
		return res;
	}
	
	
//cette methode elle verif si le code entrer est existe dans la ligne des commande ou nn 
	//si le code existe il doit retourner la ligne de commande 
	//si non il doit retourner null
	public LDC61 retourner(Integer code) {
		for(LDC61 ldc: tabLDC) {
			if(ldc.getCode()==code)
				return ldc;
	}return null;
}
	
	//A l'ajout d'une ligne de commandes on verifie d'abord si elle existe
	public void ajouter(LDC61 ldc) {
		if (retourner(ldc.getCode())==null) 
			tabLDC.addElement(ldc);
	
}

	//on va supprimer une ligne de commande qui contient le code entrer 
	//on le cherche au tableau de ligne de commande pour le supprimer 
	public void supprimer(int code) {
		for(LDC61 ldc :tabLDC) {
			if(ldc.getCode() == code)
				tabLDC.remove(code);
		}
		
	}
	
	// nouvelle methode pour respecter l'interface
	// c'est un vector don une supprime ligne
	public void supprimer(Integer ligne) {
		if(ligne >=0 && ligne <= taille()) tabLDC.remove(ligne);
	}

	

}
