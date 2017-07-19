package Serie61;

import MesInterfaces.InterfaceGestion;
import MesExceptions.*;
import Utils.DateUser;
import Ipane.ES;
import ConnexionFichier.Connexion;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;


public class FrameGestionTableDesFactures61 extends JFrame
implements InterfaceGestion<TableDesFactures61, TableDesCommandes61>, Serializable, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static private ES es = new ES();
	static public DateUser date = new DateUser();
	private Connexion<TableDesFactures61> tabFactConnectee;
	
	JButton creer = new JButton("FACTURER COMMANDE");
	JButton afficher = new JButton("AFFICHER FACTURE");
	JButton afficherTous = new JButton("AFFICHER TOUS");
	JButton supprimer = new JButton("SUPPRIMER FACTURE");
	JButton fin = new JButton ("FIN");
	
	TableDesCommandes61 tabCde;
	TableDesFactures61 tabFact;
	
	
	public FrameGestionTableDesFactures61(String nomPhysique) {
		tabFactConnectee = new Connexion<TableDesFactures61>(nomPhysique);
	}
	
	public TableDesFactures61 recuperer() 
	{
		TableDesFactures61 tabFact = tabFactConnectee.lire();
		
		if (tabFact == null) {
			es.affiche(" *** FICHIER NOUVEAU. TAB FACTURES PAR DEFAULT *** \n");
			tabFact = new TableDesFactures61();
		}
		return tabFact;
	}
	
	
	public void sauvegarder (TableDesFactures61 tabFact) {
		es.affiche(" *** SAUVEGARDE du Fichier "+ tabFactConnectee.getNomPhys() +" en cours *** \n");
		tabFactConnectee.ecrire(tabFact);
	}
	
	
	
	public void menuGeneral(TableDesFactures61 tabFact, TableDesCommandes61 tabCde) throws Exception {
		
		this.tabCde = tabCde;
		this.tabFact = tabFact;
				
		this.setLayout(new GridLayout(5,1));
		this.setTitle("GESTION TABLE DES FACTURES");
		this.setSize(300, 300);
		
		creer.addActionListener(this);
		this.add(creer);
		
		supprimer.addActionListener(this);
		this.add(supprimer);
		
		afficher.addActionListener(this);
		this.add(afficher);
		
		afficherTous.addActionListener(this);
		this.add(afficherTous);

		fin.addActionListener(this);
		this.add(fin);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==creer) 
			try {
				creer(tabFact, tabCde);
			} catch (Exception e) {}
		
		if(evt.getSource()==supprimer) 
			try {
				supprimer(tabFact, tabCde);
			} catch (Exception e) {}
		
		
		if(evt.getSource()==afficher) {
			try {
				afficher(tabFact); 
			} catch (Exception e) {}
		}
		
		if(evt.getSource()==afficherTous) {
			try {
				afficherToutesLesFactures(tabFact);
			} catch (Exception e) {}
		}
		
		if(evt.getSource()==fin) {
			this.setVisible(false);
		}
		
	}

	public int menuChoix(TableDesFactures61 tabFact, TableDesCommandes61 tabCde) throws Exception {
		return 0;
	}


	public void creer(TableDesFactures61 tabFact, TableDesCommandes61 tabCde) throws AbandonException {
		String str = tabCde.cleCommNonFact()+"\n";
		
		// test si pas de commande 
		if (tabCde.taille() == 0) {
			es.affiche("\n *** AUCUNE COMMANDE A FACTURER *** ");
		// test si tout est facturé
		} else if (tabCde.nbCommNonFact()<=0) {
			es.affiche("\n *** TOUTES LES COMMANDES SONT FACTURES*** ");
		} else {
			String numCde = es.lireString(str + "\n Quelle commande voulez vous FACTURER? \n Entrez numero: ");
			// test si le num saisi existe
			if (tabCde.retourner(numCde)!=null) {
				// test si le num est déjà dans tabFact => facture existe
				if (tabFact.retourner(numCde) !=null) {
					es.affiche(" *** COMMANDE DEJA FACTURE ***");
				} else {
					TableArticles61 tabArt = new TableArticles61();
					String contenu = tabCde.getCommande(numCde).facturer(tabArt, tabFact);
					
					Facture61<String> facture = new Facture61<String>(contenu, date);
					String numero = tabCde.getCommande(numCde).getNumeroCde();
					tabCde.getCommande(numCde).setEtatFact(true);
					tabFact.ajouter(numero, facture);
					es.affiche("*** FACTURE AJOUTE ***\n\n"+ facture.toString() );
				}
				
			} else {
				es.affiche(" *** AUCUNE COMMANDE ne correspons à ce numéro *** ");
			}
		}
	}


	public void supprimer(TableDesFactures61 tabFact, TableDesCommandes61 tabCde) throws AbandonException {
	
		String st = tabFact.cle()+"\n";
		
		if (tabFact.taille() == 0) es.affiche("\n *** AUCUNE FACTURE ENREGISTRE ***\n");
		else {
		String numFact = es.lireString(st + "\n Quelle FACTURE voulez vous SUPPRIMER? \n Entrez numero: ");
		
			if (tabFact.retourner(numFact) == null) {
				es.affiche("\n *** AUCUNE FACTURE ne correspons à ce numéro *** ");
			} else {
				// test// date.setJour(25);
				if (tabFact.retourner(numFact).getDate().ajouterNombreJours(7).avant(date) == true) {
					tabFact.supprimer(numFact);	
					tabCde.retourner(numFact).setEtatFact(false);
					es.affiche("\n *** FACTURE SUPPRIMEE *** ");
				} else {
					es.affiche("*** VOUS NE POUVEZ PAS SUPPRIMER CETTE FACTURE AUJOURD'HUI *** ");
				}

			}
		}
	}
	
	public void modifier(TableDesFactures61 tab, TableDesCommandes61 tab1) throws Exception {
		
	}


	public void afficher(TableDesFactures61 tabFact) throws AbandonException {
		String str= tabFact.cle()+"\n";
		
		if (tabFact.taille() == 0) {
			es.affiche("\n *** AUCUNE FACTURE ENREGISTRE ***\n");
		}
		else {
			String cle = es.lireString(str + "\n Quelle FACTURE voulez vous AFFICHER? \n Entrez numero:");
			Facture61<String> fact = tabFact.retourner(cle);
			if (tabFact.retourner(cle) == null) {
				es.affiche("\n *** AUCUNE FACTURE AVEC CE NUMERO ***");
			}
			else {
				es.affiche(fact.toString());
			}
		}
	}
	
	public  void afficherToutesLesFactures(TableDesFactures61 tabFact) {
		if (tabFact.taille() == 0) {
			es.affiche("\n *** AUCUNE FACTURE ENREGISTRE ***\n");
		}
		else 
		es.affiche(tabFact.toString());
	}


}
