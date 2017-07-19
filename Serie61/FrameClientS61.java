package Serie61;

import javax.swing.JFrame;
import Ipane.ES;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FrameClientS61 extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private ES es = new ES();
	private JButton gestArticles = new JButton("GESTION DES ARTICLES");
	private JButton gestCommandes = new JButton("GESTION DES COMMANDES");
	private JButton gestFactures = new JButton("GESTION DES FACTURES");
	private JButton fin = new JButton("FIN");
	
	
	FrameGestionTableDesArticles61 gta = new FrameGestionTableDesArticles61("TABLEARTICLES.data"); 
	TableArticles61 tabArt = gta.recuperer();
	
	FrameGestionTableDesCommandes61 gtc = new FrameGestionTableDesCommandes61("TABLEDESCOMMANDES.data");
	TableDesCommandes61 tabCde = gtc.recuperer();
	
	FrameGestionTableDesFactures61 gtf = new FrameGestionTableDesFactures61("TABLEDESFACTURES.data");
	TableDesFactures61 tabFact = gtf.recuperer();
	
	
public FrameClientS61() {
	this.setLayout(new GridLayout(4,1));
	this.setSize(300, 200);
	this.setTitle("GESTION SUPERETTE");
	
	
	gestArticles.addActionListener(this);
	this.add(gestArticles);
	
	gestCommandes.addActionListener(this);
	this.add(gestCommandes);
	
	gestFactures.addActionListener(this);
	this.add(gestFactures);
	
	fin.addActionListener(this);
	this.add(fin);
	
	this.setLocationRelativeTo(null);
	this.setVisible(true);
}


public void actionPerformed(ActionEvent evt) {
	if(evt.getSource()==gestArticles) {
		es.affiche(" GESTION DES ARTICLES APPELE");
		try {
			gta.menuGeneral(tabArt, tabCde, tabFact);
		} catch (Exception e){}
	}
	
	if(evt.getSource()==gestCommandes) {
		es.affiche(" GESTION DES COMMANDES APPELE");
		try {
			gtc.menuGeneral(tabCde, tabArt, tabFact);
		} catch (Exception e) {}
	}
	
	if(evt.getSource()==gestFactures) {
		es.affiche(" GESTION DES FACTURES APPELE");
		try {
			gtf.menuGeneral(tabFact, tabCde);
		} catch (Exception e) {}
	}
	
	if(evt.getSource()==fin) {
		gta.sauvegarder(tabArt);
		gtc.sauvegarder(tabCde);
		gtf.sauvegarder(tabFact);
		
		es.affiche(" AUREVOIR");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.exit(0);
	}
}

}
	
	
	
	
