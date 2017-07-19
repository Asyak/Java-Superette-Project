package MesInterfaces;

public interface InterfaceSt <TypeMetier, TypeCode> { // type metier est parametre generique
	
	public abstract int taille(); // on peut ajouter anstract ou pas
	
	// le metier retourné ets different d'une structure à autre : commande, article, etc.
	public abstract TypeMetier retourner(TypeCode code); 
	
	public abstract void supprimer (TypeCode code);
	
	public abstract void ajouter (TypeMetier metier);


}
