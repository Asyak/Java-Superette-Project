package Serie61;

import java.util.TreeMap;
import MesInterfaces.InterfaceSt;
import java.io.*;

public class TableArticles61 implements InterfaceSt<ArticleAbstrait, Integer>,Serializable { // On Precise le metier et le type de code
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<Integer, ArticleAbstrait> tabArt= new TreeMap<Integer, ArticleAbstrait>();
	
	// Constructeurs
	public TableArticles61(){
		ArticleAbstrait a1=new Article61(1,"Disc Dur",(float)50.5);
		ArticleAbstrait a2=new Article61(4,"Carte mére",(float)1000.0);
		ArticleAbstrait a3=new Article61(5,"Carte reseau",(float) 24.7);
		ArticleAbstrait a4=new Article61(12,"Boite 100CD",(float)75.5);
		ArticleAbstrait a5=new Article61(18,"MEMOIRE FLASH",(float)17.0);
		
		tabArt.put(a1.getCode(),a1);
		tabArt.put(a2.getCode(),a2);
		tabArt.put(a3.getCode(),a3);
		tabArt.put(a4.getCode(),a4);
		tabArt.put(a5.getCode(),a5);
	
	}
	
	public TableArticles61(TreeMap<Integer, ArticleAbstrait> tabArt){
		this.tabArt=tabArt;
		
	}
	
	public TreeMap<Integer, ArticleAbstrait> getTabArt() {
		return tabArt;
	}

	public void setTabArt(TreeMap<Integer, ArticleAbstrait> tabArt) {
		this.tabArt = tabArt;
	}
	

	public String toString(){
		 String st="\n\t *** LISTE DES ARTICLES EN STOCK *** \n\n";
		 for(ArticleAbstrait art:tabArt.values()){
			 st=st+art.toString() + "\n";
		 }
		 return st;
	}
	
	public int taille(){
		return tabArt.size();
	}
	
	public ArticleAbstrait retourner(Integer code){ 
		return tabArt.get(code);
	}
	
/*	@Override
	public Article42 retourner(Integer code) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	// Cette methode est vide elle est créer pour supprimer l'interface.
	// Mais on utilise une autre methode supprimer
	
	
	public void ajouter(ArticleAbstrait art){
		if(! tabArt.containsKey(art.getCode())){
			tabArt.put(art.getCode(), art);
		}
	}
/*	
	@Override
	public void ajouter(ArticleAbstrait metier) {
		// TODO Auto-generated method stub
		
	}*/
	
	public void supprimer(int code, TableDesCommandes61 tabCde){
		tabArt.remove(code);
		tabCde.purge(code);
	}
	
//	public void supprimer(Integer code) {} 
	
	public void supprimer(Integer code){
		tabArt.remove(code);
	}

	public String cle(){    
		String st="\n\t *** VOICI LA LISTE DES CODES EXISTANTS ***\n\n";
		for (Integer code: tabArt.keySet())
		{
			st=st+"\t"+code+" * ";
		}

		return st;
	}
	
	// Méthodes Spécifiques à la Série 41, mon inplementation de test
	// Il faut implementer pour que affiche rien quand liste vide
	// ajout mot clé INSTANCEOF
	public String afficherPromo(){		
		int nb = nbArtPromo();
		String st = "";
	
		if (nb>0) {
			st = "\n \t\t*** VOICI LA LISTE DES ARTICLES EN PROMO ***\n\n";
		} else {
			st= "\n \t\t*** AUCUN ARTICLE EN PROMO ***\n\n";
		}
		
		for (ArticleAbstrait art : tabArt.values()){
			if(art instanceof ArticlePromo61 ){
			st = st + art.toString() + "\n";
			}
		}
		return st;
	}
	
	// Ma methode
	public int nbArtPromo () {
		int counter = 0;
		for (ArticleAbstrait art : tabArt.values()) {
			if(art instanceof ArticlePromo61 ){
				counter++;
			}
		}
		return counter;
	}



}


