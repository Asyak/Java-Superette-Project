package ConnexionFichier;
import java.io.*;
import Ipane.ES;

public class Connexion <TypeStructure> {
	
	private String nomPhysique;
	private ES es = new ES();
	
	// Constructeur
	public Connexion(String nom) {
		nomPhysique=nom;
	}
	
	// Getter et Setter 
	public String getNomPhys() {
		return nomPhysique;
	}
	
	public String setNomPhys() {
		return nomPhysique;
	}
	
	
	// methodes specifiques à la série
	public TypeStructure lire() {
		
	TypeStructure obj=null;
	
	try {
		FileInputStream fis=new FileInputStream(nomPhysique);
		ObjectInputStream ois = new ObjectInputStream(fis);
		obj=(TypeStructure)ois.readObject();
		}
		catch (FileNotFoundException fne) {es.affiche("*** FICHIER NOUVEAU SERA CREE! (class connexion) ***");}
		catch (IOException ioe) {es.affiche("*** PROBLEME DE LECTURE ***" + ioe.getMessage());}
		catch (ClassNotFoundException cnf) {es.affiche("*** OBJET LU NON Correspondant ***");}
	
	return obj;	 // si tab non trouvé retourne null
	}
		
	
	
	public void ecrire(TypeStructure obj) {
	
	try {
		FileOutputStream fos=new FileOutputStream(nomPhysique);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject((TypeStructure)obj);
		}
		// si fichier n'existe pas il va le creer, mais si le fichier existe veut on l'ecraser ou pas?
		// Ouvrir pour concaterner ou pour ecrire dessues
		// on ajoute true dans FileOutPutStream => on veut ajouter mais on lit toujours le premier
		// si on ajoute false = ecrase l'ancienne table par nouvelle table
		// si on ajoute rien false pas default
		catch (FileNotFoundException fnf) {es.affiche("*** FICHIER NOUVEAU SERA CREE! ***");}
		catch (IOException ioe) {es.affiche("*** PROBLEME DE LECTURE ***" + ioe.getMessage());} // problem support, disque plein etc
	
	
	}


}
