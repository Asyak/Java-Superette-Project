package IConsole;

import java.util.*;
import MesExceptions.ErreurSaisieException;

public class ES {
	private Scanner sc = new Scanner(System.in);
	
	
	public int SaisieEntier(String mes, int inf, int sup) throws ErreurSaisieException {
		int nbSaisie;
		
		do {
			System.out.println(mes);
			try {
				nbSaisie = sc.nextInt();// pour lire un entier, une methode JAVA
				sc.nextLine();
		
					if (nbSaisie >=inf && nbSaisie <= sup) return nbSaisie;
					throw new ErreurSaisieException();
					} 
		
			 catch (InputMismatchException e) {
				System.out.println("Saisie NON numerique. RESSAISISSEZ un choix NUMERIQUE SVP ***\n");
				sc.nextLine(); // vider la chaine de caractère pour sortir de la boucle infinie
			}
			
			catch(ErreurSaisieException er) {
				boolean abandon = saisieOuiNon("Saisie HORS INTERVAL, abandonner O/N?");
				if (abandon)throw er;
				
			}
		} while (true);
	}
	
	
	public boolean saisieOuiNon (String mes) {
		char reponse = lireString(mes).charAt(0); /// !!!!!!
		return (reponse == 'o' || reponse == 'O');
	}
	
	
	// Mon implementation. 
	public float lireFloat(String mes, float inf, float sup) throws ErreurSaisieException {
		float nbSaisie;
		
		do {
				System.out.println(mes);
			try {
			nbSaisie = sc.nextFloat();
			sc.nextLine();
			
			if (nbSaisie >=inf && nbSaisie <= sup) return nbSaisie;
				throw new ErreurSaisieException();
			}  
			
			 catch (InputMismatchException e) {
				System.out.println("Saisie NON numerique. SAISISSEZ un choix NUMERIQUE (par ex 10,0) SVP ***\n");
				sc.nextLine(); // vider la chaine de caractère pour sortir de la boucle infinie
			}
			
			catch(ErreurSaisieException er) {
				boolean abandon = saisieOuiNon("Saisie HORS INTERVAL, abandonner O/N?");
				if (abandon)throw er;
			}
		} while (true);
	}
	
	public void affiche(String affiche) {
		System.out.println(affiche);
	}
	
	
	public String lireString(String mes) {
		System.out.println(mes);
		return sc.nextLine();
	}
	
	

}
