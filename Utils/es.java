package Utils;

import java.util.*;

public class es {

		static Scanner sc = new Scanner(System.in);
		
		public static void main (String [] args) {
			
			System.out.println("Saisir un jour, un mois, une année");
			int jour = SaisieEntier("Jour =", 1, 31);
			int mois = SaisieEntier("Mois =", 1, 12);
			int annee = SaisieEntier("Annee =",0, Integer.MAX_VALUE);
			
			DateUser dat1 = new DateUser(1,3,2007);
			System.out.println("dat1 ="+dat1);
			
			if (DateUser.validDate(jour, mois, annee)) {
				DateUser d1 = new DateUser(jour, mois, annee);
				System.out.println("La date saisie est: " +d1.toString());
				
				d1.lendemain();
				System.out.println("La date du lendemain est: "+d1.toString());
				
				DateUser d2 = new DateUser(jour, mois, annee);
				d2.hier();
				System.out.println("Hier nous étions: ");
	
				
				DateUser d3 = new DateUser();
				System.out.println("Nous sommes: " + d3.toString() + "\n");
				System.out.println("C'est un "+d3.jourSemaine());

			} else {
				System.out.println ("Date invalide");
	
			}
			
			
		}
			
			public static int SaisieEntier(String mes, int inf, int sup) {
				System.out.println(mes);
				int nbSaisie;
				
				do {
				nbSaisie = sc.nextInt();// pour lire un entier, une methode JAVA
				
				if (nbSaisie >=inf && nbSaisie <= sup) {
					return nbSaisie;
			} 
					System.out.println("Saisi incorrecte, reessayez");
				} while (true);
			}
			
			public static String lireString(String mes) {
				System.out.println(mes);
				return sc.nextLine();
			}
			
			public static void affiche(String affiche) {
				System.out.println(affiche);
			}
			
			
			public static float lireFloat(String mes, float inf, float sup) {
				affiche(mes);
				float nbSaisie;
				
				do {
				nbSaisie = sc.nextInt();// pour lire un entier, une methode JAVA
				
				if (nbSaisie >=inf && nbSaisie <= sup) {
					return nbSaisie;
			} 
					affiche("SSAISIE HOR INTERVALE...RESSAISISSEZ SVP\n");
				} while (true);
			}
	
		
}
