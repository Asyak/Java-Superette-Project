package Serie61;

public class ArticlePromo61 extends ArticleAbstrait{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Nouvelles Variables
	private int quantiteMini;
	private float reduction;
	
	public ArticlePromo61(){}
	
	public ArticlePromo61(int code, String designation , float pu , int quantiteMini,float reduction){
		
		super(code , designation, pu); // Manière de faire reference à la class "pere" du quelle on a herité
		this.quantiteMini=quantiteMini;
		this.reduction=reduction;
	}
	

	// Getters et Setters de cette classe uniquement

	public int getQuantiteMini() {
		return quantiteMini;
	}

	public void setQuantiteMini(int quantiteMini) {
		this.quantiteMini = quantiteMini;
	}

	public float getReduction() {
		return reduction;
	}

	public void setReduction(float reduction) {
		this.reduction = reduction;
	}
	
	// On enlève SUPER par rapport à la serie 41 
	// On implemente les methodes de la classe abstraite 
	public String toString(){
		return ("\t Code: "+ getCode() + " Designation: " + getDesignation()+  " Quantité MIN pour PROMO: " + quantiteMini +" Reduction: " + reduction + " % ");

	}
	

	
	public float prixFacture(int quantite){
		if(quantite < quantiteMini) return quantite*getPu();
		else return (quantite*getPu())*(1 - reduction/100);
	}
	
	public String facturer( int quantite){
		//return " " + code + "\t " + designation + "\t\t "+ quantite + "\t\t" + pu +"\t\t " + prixFacture(quantite) ;
		/*return "  " + code+ "               " + designation + "                "+ quantite + "                   "+pu+ 
				"           "+prixFacture(quantite)+ "          "+UneCommande52.arrondir((prixFacture(quantite)/100)*(float)119.6)+" \n"
		 + "(REMISE: "+  reduction +" %" + " Si vous commandez " + quantiteMini +" unités ou plus.) \n" ;
		*/
		return  ("<html><table><tr>"
				+ "<th>"+ code + "</th>"
				+"<td>"+ designation + "</td>"
				+"<td>" + quantite + "</td>"
				+"<td>" + pu + "</td>"
				+"<td>"+ prixFacture(quantite) + "</td>"
				+"<td>" + UneCommande61.arrondir((prixFacture(quantite)/100)*(float)119.6) + "</td>"
				+ "</tr>"
				+ "<tr><th> REMISE:</th><td colspan=6>"
				+  reduction +" %" + " Si vous commandez " + quantiteMini +" unités ou plus"
				+ "</td></tr>"
				+ "<table></html>");
	}
		
	
}
