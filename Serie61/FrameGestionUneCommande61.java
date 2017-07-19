package Serie61;

import java.io.Serializable;
import Ipane.*;
import MesExceptions.AbandonException;
import MesExceptions.ErreurSaisieException;
import MesInterfaces.InterfaceGestion;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import ConnexionFichier.Connexion;
import Ipane.ES;


public class FrameGestionUneCommande61 extends JFrame
implements InterfaceGestion<UneCommande61<String>, TableArticles61 >, Serializable, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ES es = new ES();
	
	TableArticles61 tabArt;
	UneCommande61<String> cde;
	String numCde;

	JButton saisir = new JButton("SAISIR LIGNE");
	JButton supprimer = new JButton("SUPPRIMER LIGNE");
	JButton editer = new JButton("EDITER COMMANDE");
	JButton fin = new JButton("FIN");
	
	Color myGreen = new Color(0, 165, 155);
	Color myRose = new Color(251, 145, 162);
	
	
	public  void menuGeneral(UneCommande61<String> cde, TableArticles61 tabArt, String numCde) throws ErreurSaisieException, AbandonException {
		this.tabArt = tabArt;
		this.cde = cde;
		this.numCde = numCde;
				
		this.setLayout(new GridLayout(4,1));
		this.setSize(300, 300);
		this.setTitle("GESTION UNE COMMANDE");

		
		saisir.addActionListener(this);
		this.add(saisir);
		saisir.setBackground(myGreen);
		
		supprimer.addActionListener(this);
		this.add(supprimer);
		supprimer.setBackground(myGreen);
		
		editer.addActionListener(this);
		this.add(editer);
		editer.setBackground(myGreen);

		fin.addActionListener(this);
		this.add(fin);
		fin.setBackground(myGreen);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	
	public  void menuGeneral(UneCommande61<String> cde, TableArticles61 tabArt) throws ErreurSaisieException, AbandonException {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==saisir) 
			try {
				creer(cde, tabArt, numCde);
			} catch (Exception e) {}
		
		if(evt.getSource()==supprimer) 
			try {
				supprimer(cde, tabArt);
			} catch (Exception e) {}
		
		
		if(evt.getSource()==editer) {
			try {
				afficher(cde); 
			} catch (Exception e) {}
		}
		
		if(evt.getSource()==fin) {
			this.setVisible(false);
		}
		
	}

	@Override
	public int menuChoix(UneCommande61<String> tab, TableArticles61 tab1) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void creer(UneCommande61<String> cde, TableArticles61 tabArt, String numCde) throws AbandonException {
		LDC61 ldc = saisieLdc(cde, tabArt);	
		cde.setNumeroCommande(numCde);
		
		if (ldc != null) {
			cde.ajouter(ldc);

		} 
	}
	
	public void creer(UneCommande61<String> tab, TableArticles61 tab1) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public  LDC61 saisieLdc(UneCommande61<String> cde, TableArticles61 tabArt) throws AbandonException {
		int codeProd, qteProd;
		codeProd = es.SaisieEntier("\n Code Article", 1, Integer.MAX_VALUE);
		if (tabArt.retourner(codeProd) == null) {
			es.affiche("CET ARTICLE N'EXISTE PAS dans notre CATALOGUE");
			return null;
		}
		
		qteProd = es.SaisieEntier("\n Quantité", 1, Integer.MAX_VALUE);
		
		LDC61 tableLigneDeCommandes61 = cde.retourner(codeProd);
		if (tableLigneDeCommandes61 !=null) { 
			// ldc.setQte(qteProd+ldc.getQte());
			es.affiche("ARTICLE DEJA en COMMANDE \n");
			return null;
		}
		
		return new LDC61(codeProd, qteProd);
	}
	
	public void supprimer(UneCommande61<String> cde, TableArticles61 tabArt) throws AbandonException {
		int codeProd;
		if (cde.taille() == 0)  {
			es.affiche("*** COMMANDE VIDE! ***");
		} else {
			codeProd = es.SaisieEntier(cde.toString() + "\n QUELLE ARTICLE VOULEZ VOUS SUPPRIMER DE LA COMMANDE?\n "
					+ "Entrez CODE Article", 1, Integer.MAX_VALUE);
						
			LDC61 tableLigneDeCommandes51 = cde.retourner(codeProd);
			if (tableLigneDeCommandes51 !=null) {
				cde.purge(codeProd);
				es.affiche("*** ARTICLE SUPPRIME ***");
			} else {
				es.affiche("*** AUCUN Article avec ce CODE dans la COMMANDE ***");
			}
		
		}
	}
	
	
	// mon implementation
public void afficher(UneCommande61<String> cde) { 
		if (cde.taille() == 0) {
			es.affiche("\n *** COMMANDE VIDE! ****");
		} else {
			es.affiche(cde.toStringCours());
		}
	}

	
	public void modifier(UneCommande61<String> tab, TableArticles61 tab1) throws Exception {
		// TODO Auto-generated method stub
		
	}


	
}
