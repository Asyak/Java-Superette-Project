package Serie61;

public class Article61 extends ArticleAbstrait {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Article61() {
		
	}
	
	public Article61(int code, String designation, float pu) {
		super (code, designation, pu);
	}
	
	// public float prixFacture()
	public float prixFacture(int quantinte){
		return quantinte*pu;
	}
	
	public String facturer( int quantite){
		//return " " + code + "\t " + designation + "\t\t "+ quantite + "\t\t" + pu +"\t\t " + prixFacture(quantite) ;
		return "  " + code+ "               " + designation + "                "+ quantite + "                   "+pu+ 
				"           "+prixFacture(quantite)+ "          "+UneCommande61.arrondir((prixFacture(quantite)/100)*(float)119.6)+" \n";
	}
	
	
	public String toString(){
		return "\t Code: "+code+ " Designation: "+ designation+" Prix Utinaire: "+pu;
	}

}
