package Serie61;

import java.io.Serializable;
import java.util.Hashtable;
import MesInterfaces.InterfaceSt;
import Utils.DateUser;

public class TableDesCommandes61 implements InterfaceSt <UneCommande61<String>, String>, Serializable { // genericité type clé et commande
	
	// manque getters et setters 

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Hashtable<String,UneCommande61<String>> tabCde
		=new Hashtable<String,UneCommande61<String>>();

		// construct par default
		public TableDesCommandes61(){}
		
		// construct
		public TableDesCommandes61(Hashtable<String, UneCommande61<String>> tabCde){
			this.tabCde=tabCde;
		}
		
		// getter d'une Commande
		public UneCommande61<String> getCommande(String numCde) {
			return tabCde.get(numCde);
		}

		public int taille(){return tabCde.size();}
		
		public UneCommande61<String> retourner(String numero) {
			return tabCde.get(numero);
		}
		
		public void ajouter (UneCommande61<String>cde) {
			if(!tabCde.contains(cde.getNumeroCde())) {
				tabCde.put(cde.getNumeroCde(), cde);
			}
		}
		
		public void supprimer (String numCde) { // a la place de int aussi dans RETOURNER
			tabCde.remove(numCde);
		}
		
/*		public void supprimer(String code) {
			// TODO Auto-generated method stub
			
		}*/
		
		public String toString() {
			String st="\n\t *** LISTE DES COMMANDES ***\n";
			for (UneCommande61<String> cde: tabCde.values()) {
				st = st +cde.toString();
			}
			return st;
		}
		
		public String cle () {
			String st = "\n\t *** LISTE DES NUMEROS DE COMMANDES ENREGISTRES ***\n";
			for (String num: tabCde.keySet()) {
				st = st+"\n\t N°" +num+ " * " +tabCde.get(num).getDate()+ " * "+ tabCde.get(num).etatFact()+ " * ";
			}
			return st; 
		}
		
		// ma méthode
		public String cleCommNonFact () {
			String st = "\n\t *** LISTE DES NUMEROS DE COMMANDES NON FACTURES ***\n";
			for (String num: tabCde.keySet()) {
				if (tabCde.get(num).getEtatFact() == false) {
					st = st+"\t\n N°" +num+ " * Date Commande: "+ tabCde.get(num).getDate()+ " * ";
				}
			}
			return st; 
		}
		
		
		public int nbCommNonFact() {
		int i = 0;
			for (String num: tabCde.keySet()) {
				if (tabCde.get(num).getEtatFact() == false) {
					i++;
				}

			}
			return i;
		}

		
		public String cleCommFact () {
			String st = "\n\t *** LISTE DES NUMEROS DE COMMANDES FACTURES ***\n";
			for (String num: tabCde.keySet()) {
				if(tabCde.get(num).getEtatFact() == true) {
					st = st+"\t N°" +num+ " * "+ tabCde.get(num).getDate()+ " * ";
				}
			}
			return st; 
		}

		
		
		public void purge(int code) {
			for (UneCommande61<String> cde: tabCde.values()) {
				cde.purge(code);
			}
		}






}
