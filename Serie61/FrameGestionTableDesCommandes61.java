package Serie61;

import MesExceptions.*;
import MesInterfaces.InterfaceGestion;
import Utils.DateUser;
import Ipane.ES;
import ConnexionFichier.Connexion;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;



public class FrameGestionTableDesCommandes61 extends JFrame
implements InterfaceGestion<TableDesCommandes61, TableArticles61>, Serializable, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static private ES es = new ES();
	static public DateUser date = new DateUser();
	private Connexion<TableDesCommandes61> tabCdeConnectee;
	
	JButton creer = new JButton("CREER une COMMANDE");
	JButton afficher = new JButton("AFFICHER une COMMANDE");
	JButton afficherTous = new JButton("AFFICHER toutes les COMMANDES");
	JButton supprimer = new JButton("SUPPRIMER une COMMANDES");
	JButton facturer = new JButton("EDITER FACTURE");
	JButton fin = new JButton("FIN");
	
	TableArticles61 tabArt;
	TableDesCommandes61 tabCde;
	TableDesFactures61 tabFact;
	
	Color myGreen = new Color(0, 165, 155);
	Color myRose = new Color(251, 145, 162);

	public FrameGestionTableDesCommandes61(String nomPhysique) {
		tabCdeConnectee = new Connexion<TableDesCommandes61>(nomPhysique);
	}
	

	public TableDesCommandes61 recuperer()
	{
		TableDesCommandes61 tabCde = tabCdeConnectee.lire();
		
		if (tabCde == null) {
			es.affiche(" *** FICHIER NOUVEAU. TAB COMMANDE PAR DEFAULT *** \n");
			tabCde = new TableDesCommandes61();
		}
		return tabCde;
	}
	
	
	// Methode pour sauvegarder une tab
	public void sauvegarder(TableDesCommandes61 tabCde) {
		es.affiche(" *** SAUVEGARDE du Fichier "+ tabCdeConnectee.getNomPhys() +" en cours *** \n");
		tabCdeConnectee.ecrire(tabCde);
	}
		
	public void menuGeneral (TableDesCommandes61 tabCde,TableArticles61 tabArt, TableDesFactures61 tabFact ) throws AbandonException, ErreurSaisieException {
		// recuperer les tables:
		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabFact = tabFact;
				
		this.setLayout(new GridLayout(6,1));
		this.setTitle("GESTION TABLE DES COMMANDES");
		this.setSize(300, 300);
		
		creer.addActionListener(this);
		this.add(creer);
		creer.setBackground(myGreen);
		
		supprimer.addActionListener(this);
		this.add(supprimer);
		
		afficher.addActionListener(this);
		this.add(afficher);
		
		afficherTous.addActionListener(this);
		this.add(afficherTous);
		
		facturer.addActionListener(this);
		this.add(facturer);

		fin.addActionListener(this);
		this.add(fin);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);


}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==creer) 
			try {
				creer(tabCde, tabArt);
			} catch (Exception e) {}
		
		if(evt.getSource()==supprimer) 
			try {
				supprimer(tabCde, tabArt);
			} catch (Exception e) {}
		
		
		if(evt.getSource()==afficher) {
			try {
				afficher(tabCde); 
			} catch (Exception e) {}
		}
		
		if(evt.getSource()==afficherTous) {
			try {
				afficherToutesLesCommandes(tabCde);
			} catch (Exception e) {}
		}
		
		if(evt.getSource()==facturer) {
			try {
				facturer1Commande(tabArt, tabCde, tabFact); 
			} catch (Exception e) {}
		}
		
		if(evt.getSource()==fin) {
			this.setVisible(false);
		}
		
	}
	
	

	public void menuGeneral (TableDesCommandes61 tabCde,TableArticles61 tabArt) throws AbandonException, ErreurSaisieException {
		
	}

	
	public int menuChoix(TableDesCommandes61 tabCde,TableArticles61 tabArt) throws AbandonException {  
		return 0;
	}
	
	
	public void creer(TableDesCommandes61 tabCde, TableArticles61 tabArt)  throws AbandonException, ErreurSaisieException  {
		UneCommande61<String>cde = new UneCommande61<String>();
		String numero = premierNumeroDisponible(tabCde);
		
		FrameGestionUneCommande61 g1Comm = new FrameGestionUneCommande61();
		g1Comm.menuGeneral(cde, tabArt, numero);
	
		if (cde.taille() >=0)  {
			cde.setNumeroCommande(numero); 
			tabCde.ajouter(cde);

		} 
	}
	
		public int numeroPart1() {
			int a = date.getAnnee();
			int m = date.getMois();
			int j = date.getJour();
			
			int part1= Integer.valueOf(""+a+m+j);
			return part1;
		}
		
		public  String premierNumeroDisponible(TableDesCommandes61 tabCde) {
			int numero = 1;
			String numCde = ""+numeroPart1()+numero;
		do {
				if (tabCde.retourner(numCde) == null) { 
					break; 
				}
				numero ++;
				numCde = ""+numeroPart1()+numero;
			} while (true);
		return numCde;
		}
		
		
		public void supprimer(TableDesCommandes61 tabCde, TableArticles61 tabArt) throws AbandonException{
			
			String str = tabCde.cle()+"\n";
			
			if (tabCde.taille() == 0) es.affiche("\n *** AUCUNE COMMANDE ENREGISTRE ***\n");
			else {
				String cde=es.lireString(str + "\n Quelle COMMANDE voulez vous SUPPRIMER? \n Entrez numéro:");
				
				if (tabCde.retourner(cde)!= null) {
					// test // date.setJour(25); 
					if(tabCde.retourner(cde).getDate().ajouterNombreJours(7).avant(date)==true) {
						tabCde.supprimer(cde);
						es.affiche("\n *** COMMANDE SUPPRIMEE *** ");
					} else {
						es.affiche("*** VOUS NE POUVEZ PAS SUPPRIMER CETTE COMMANDE AUJOURD'HUI *** ");
					}
				}else {
					es.affiche("AUCUNE COMMANDE ne corresponds à ce NUMERO");
				}
			}
		}
		

		
		// mon implementation
		public void afficher(TableDesCommandes61 tabCde) throws AbandonException{
			String str = tabCde.cle()+"\n";
			
			if (tabCde.taille() == 0) es.affiche("\n *** AUCUNE COMMANDE ENREGISTRE ***\n");
			
			else {
			
				String cde=es.lireString(str + "\n Commande à AFFICHER, entrez numéro:");
				if (tabCde.retourner(cde)!= null) {
					es.affiche(tabCde.getCommande(cde).toString());
				}else {
					es.affiche("AUCUNE COMMANDE ne corresponds à ce NUMERO");
				}
			}
		}

		
		public  void afficherToutesLesCommandes(TableDesCommandes61 tabCde) {
			if (tabCde.taille() == 0) es.affiche("\n *** AUCUNE COMMANDE ENREGISTRE ***\n");
			else es.affiche(tabCde.toString());
		}


		public  void facturer1Commande(TableArticles61 tabArt,TableDesCommandes61 tabCde, TableDesFactures61 tabFact) throws AbandonException {
			String numero = "";
			if (tabCde.taille()==0) {
				es.affiche("\n *** AUCUNE COMMANDE A FACTURER ***\n");
			} else if (tabCde.nbCommNonFact()<=0) {
				es.affiche("\n *** TOUTES LES COMMANDES SONT FACTURES*** ");
			} else {
				numero = es.lireString(tabCde.cle()+ "\n\n  Quelle COMMANDE voulez vous FACTURER? \n");
				UneCommande61<String>  cde = tabCde.retourner(numero);
				if (cde==null) es.affiche("\n *** AUCUNE COMMANDE AVEC CE NUMERO ***");
				else {
					if (tabFact.retourner(numero) == null) {
						es.affiche(cde.facturer(tabArt, tabFact));
					} else {
						es.affiche("\n *** COMMANDE DEJA FACTURE *** ");
					}
				}
			}
		}

		@Override
		public void modifier(TableDesCommandes61 tab, TableArticles61 tab1) throws Exception {}

	
}
