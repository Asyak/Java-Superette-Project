package Serie61;

import java.awt.*;
import java.awt.event.*;
import Ipane.ES;
import javax.swing.*;



public class FrameIdentification extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ES es = new ES();
	
	private JButton valid = new JButton("VALIDER");
	private JTextField login = new JTextField(15); // 20 caracters
	private JPasswordField pwd = new JPasswordField(10); 
	
	
	public ImageIcon getImage()
	{
		return (new ImageIcon(ImageIcon.class.getResource("/ada.png")));

	}

	
	// On defini la forme de notre Frame
	public FrameIdentification() {
		this.setLayout(new GridLayout(4,1));
		this.setSize(300, 300); // des pixels
		this.setTitle("IDENTIFICATION");
				
		// On ajoute le scomposantes:
		JPanel pan0, pan1, pan2, pan3;
		
		JLabel lab0 = new JLabel(getImage());
		JLabel lab1 = new JLabel("LOGIN");
		JLabel lab2 = new JLabel("PASSWORD");		
		
		pan0 = new JPanel();
		pan0.add(lab0);
		this.add(pan0);
		pan0.add(lab0);

	   // panel.setBackground(java.awt.Color.white);
		
		pan1 = new JPanel(); // le premier container
		login.addActionListener(this); // pour que il ecoute ce que on saisi
		pan1.add(lab1); // on ajout label
		pan1.add(login); // on ajout login editable
		this.add(pan1); // on ajoute le panel au Frame

		pan2 = new JPanel(); // le deuxième container
		pan2.add(lab2);
		pan2.add(pwd);
		pwd.addActionListener(this);
		this.add(pan2);
	
		pan3 = new JPanel(); // le troisième container
		pan3.add(valid);
		valid.addActionListener(this);
		this.add(pan3);
		
		// On rend visible, on affiche
		this.setVisible(true);
		
		// Il faut lancer Frame, on va créer une nouvelle class Lanceur
		// C'est un main au faite
		
		this.setLocationRelativeTo(null);
		
	}

	// On recupere l'evenement, tout ce que il a entendu avec ActionListener
	public void actionPerformed(ActionEvent evt) {
		
		// si on a cliqué sur Valider
		if (evt.getSource()==valid) {
			String loginUser = login.getText(); // on recuper le text
			char[] pwdUser = pwd.getPassword(); // le methode getPassword envoie tab de char
			String  pwdUserString = conversion(pwdUser); // On converti en String, on va la créer
			
			// On va créer une methode verif
			if (verif(loginUser, pwdUserString)) {
				es.affiche("IDENTIFICATION REUSSI");
				this.setVisible(false); // on ferme la fenetre precedente
				new FrameClientS61(); // et on cree le lien avec nousvelle fenetre
			} else {
				es.affiche ("PROBLEME D'IDENTIFICATION");
				raz(); // une procedure qui met en blanc tout ce qu'on a saisi; On va la créer. 
			}
		}
	}
	
	public String conversion(char[] tab) {
		String conv = "";
		for (int i=0; i<tab.length; i++) {
			conv = conv+tab[i];
		}
		return conv;
	}
	
	
	public boolean verif(String login, String pwd) {
		return login.equals("admin")&&pwd.equals("admin");
	}
	
	public void raz(){
		login.setText("");
		pwd.setText("");
	}
	
}
