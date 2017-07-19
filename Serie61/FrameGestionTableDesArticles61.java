package Serie61;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;
import ConnexionFichier.Connexion;
import Ipane.ES;
import MesExceptions.AbandonException;
import MesInterfaces.InterfaceGestion;
import javax.swing.JFrame;


public class FrameGestionTableDesArticles61 extends JFrame 
implements InterfaceGestion<TableArticles61, TableDesCommandes61>, Serializable, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ES es = new ES();
	private Connexion<TableArticles61> tabConnectee;
	
	JButton creer = new JButton ("CREER un nouvelle Article");
	JButton supprimer = new JButton ("SUPPRIMER un Article");
	JButton modifier = new JButton ("MODIFIER un Article");
	JButton afficher = new JButton("AFFICHER Tous");
	JButton afficherPromo = new JButton("AFFICHER Promos");
	JButton fin = new JButton("FIN");

	
	TableArticles61 tabArt;
	TableDesCommandes61 tabCde;
	TableDesFactures61 tabFact;

	public  FrameGestionTableDesArticles61 (String nomPhysique) {
		tabConnectee = new Connexion<TableArticles61>(nomPhysique);
	}
	

	// Methode pour recuperer la table
	public TableArticles61 recuperer() 
	{
		TableArticles61 tabArt = tabConnectee.lire();
		
		if (tabArt == null) {
			es.affiche(" *** FICHIER NOUVEAU. TAB ARTICLE PAR DEFAULT *** \n");
			tabArt = new TableArticles61();
		}
		return tabArt;
	}
	
	// Methode pour sauvegarder une tab
	public void sauvegarder(TableArticles61 tabArt) { 
		es.affiche(" *** SAUVEGARDE du Fichier "+ tabConnectee.getNomPhys() +" en cours *** \n");
		tabConnectee.ecrire(tabArt);
	}

	
	public void menuGeneral (TableArticles61 tabArt, TableDesCommandes61 tabCde, TableDesFactures61 tabFact)  throws  Exception
	{
		// recuperer les tables:
		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabFact = tabFact;
				
		this.setLayout(new GridLayout(6,1));
		this.setTitle("GESTION TABLE DES ARTICLES");
		this.setSize(300, 300);
		
		creer.addActionListener(this);
		this.add(creer);
		
		supprimer.addActionListener(this);
		this.add(supprimer);
		
		modifier.addActionListener(this);
		this.add(modifier);
		
		afficher.addActionListener(this);
		this.add(afficher);
		
		afficherPromo.addActionListener(this);
		this.add(afficherPromo);
		
		fin.addActionListener(this);
		this.add(fin);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==creer) 
			try {
				creer(tabArt, tabCde);
			} catch (Exception e) {}
		
		if(evt.getSource()==supprimer) 
			try {
				supprimer(tabArt, tabCde);
			} catch (Exception e) {}
		
		if(evt.getSource()==modifier) 
			{
				es.affiche("*** OPTION EN DEVELOPPEMENT *** ");
			} 
		
		if(evt.getSource()==afficher) {
			try {
				afficher(tabArt); 
			} catch (Exception e) {}
		}
		
		if(evt.getSource()==afficherPromo) {
			try {
				afficherPromo(tabArt); 
			} catch (Exception e) {}
		}
		
		if(evt.getSource()==fin) {
			this.setVisible(false);
		}
		
	}

	public void creer(TableArticles61 tabArt, TableDesCommandes61 tabCde) throws AbandonException {
		
		String mes1 = "Entrez code nouvelle article: ";
		int code = es.SaisieEntier(mes1, 0, Integer.MAX_VALUE);
		

		if (tabArt.retourner(code) != null){
			es.affiche("CE CODE ARTICLE EXISTE déjà dans notre CATALOGUE");
		} 
		
		if (tabArt.retourner(code) == null) {
			String mes = "Entrez designation nouvel article: ";
			String des = es.lireString(mes);
			
			String mes2 = "Entrez prix nouvelle article: ";
			float pu = es.SaisieEntier(mes2, 0, Integer.MAX_VALUE);
			
			if(es.saisieOuiNon("PROMOTION? (O/N)")){
				int quantinteMini = es.SaisieEntier("Quantité MINIMUM: ",1, Integer.MAX_VALUE);
				float reduc= es.lireFloat("REDUCTION: " , 1f , 100f);
				ArticlePromo61 a = new ArticlePromo61(code, des, pu , quantinteMini, reduc);
				tabArt.ajouter(a);
			} else {
				Article61 a = new Article61 (code,des, pu );
				tabArt.ajouter(a);
			}
		}
	}
	
	
	@Override
	public int menuChoix(TableArticles61 tab, TableDesCommandes61 tab1) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void menuGeneral(TableArticles61 tab, TableDesCommandes61 tab1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void supprimer(TableArticles61 tabArt, TableDesCommandes61 tabCde) throws AbandonException {
		
		String str = tabArt.cle()+"\n\n";
	
		String mes1 = str + "Article à SUPPRIMER, entrez code: ";
		int code = es.SaisieEntier(mes1, 1, Integer.MAX_VALUE);
		
		if (tabArt.retourner(code) != null) {
			tabArt.supprimer(code, tabCde);
		} else {
			es.affiche("CET ARTICLE N'EXISTE PAS dans notre CATALOGUE");
		}
	}
		

	@Override
	public void modifier(TableArticles61 tab, TableDesCommandes61 tab1) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	public  void  afficher(TableArticles61 tabArt) throws AbandonException {//prof 
		if(tabArt.taille()== 0 ) 
			es.affiche("*** AUCUN ARTICLE DANS LE CATALOGUE ***");
		else  
			es.affiche(tabArt.toString());	
	}
	

	public void afficherPromo( TableArticles61 tabArt){
		es.affiche(tabArt.afficherPromo());
	}

}
