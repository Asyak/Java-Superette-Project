package Serie61;

import java.io.Serializable;

public class LDC61 implements Serializable {
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int code;
 private int qte;

 
 //constructeur
 public LDC61(int code, int quantite) {
	 this.code=code;
	 this.qte=quantite;
	 
 }
 
 public int getCode() {
	 return code;
 }
 
 public int getQte() {
	 return qte;
 }
 
 public void setCode(int code) {
	 this.code=code;
 }
 
 public void setQuantite(int qte) {
	 this.qte=qte;
	 
 }
 public String toString() {
	 return "Code: "+code+ " Qte: "+qte+"\n";
	 
 }
 
 public String facturer(TableArticles61 tabArt){
		ArticleAbstrait art = tabArt.retourner(code);
		String facture;
		facture = art.facturer(qte) + "\n";
		return facture;
}
 
public float prixTotalHT(ArticleAbstrait art) {
	return (float)(art.prixFacture(qte));
}

public float prixTotalTTC(ArticleAbstrait art) {
	return ((art.prixFacture(qte)/100)*(float)119.6);
}
	

 
}

