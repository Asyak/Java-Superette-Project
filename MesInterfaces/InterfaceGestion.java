package MesInterfaces;

import Serie61.TableArticles61;
import Serie61.TableDesCommandes61;

public interface InterfaceGestion <TypeTabGere, TypeTab1> { // param gereriques<>
	
	public abstract void menuGeneral(TypeTabGere tab, TypeTab1 tab1) throws Exception;
	
	public abstract int menuChoix(TypeTabGere tab, TypeTab1 tab1) throws Exception; // partou ou E/S except à gerer
	
	public void creer(TypeTabGere tab, TypeTab1 tab1) throws Exception; // ajouter nouveller commande
	
	public void supprimer (TypeTabGere tab, TypeTab1 tab1) throws Exception;
	
	public void modifier(TypeTabGere tab, TypeTab1 tab1) throws Exception; 
	// si notre methode n'utilise qu'un param on peut surcharge, ceer une autre avce 2 juste pour respecter 
	// l'interface = tricher un peu
	
	public void afficher(TypeTabGere tab) throws Exception;

	
	


}
