package Ipane;


import javax.swing.*;
import MesExceptions.*;

public class ES extends JFrame{
	
	public int SaisieEntier(String mes, int inf, int sup) throws AbandonException //throws ErreurSaisieException 
	{
		do {
			try {
				String saisie=JOptionPane.showInputDialog(mes);
				
				if (saisie == null) throw new AbandonException();
				
				if (saisie.equals("")) affiche ("Il faut saisir quelque chose, SVP!!");
				else {
				
					int ent = Integer.parseInt(saisie);
				
					if (ent >= inf && ent <= sup) return ent;
				
					throw new ErreurSaisieException();
					}
				
				} catch (ErreurSaisieException er) 
				{
					affiche("Saisie HORS ENTERVALLE entre [ " +inf + " - " +sup+ " ]");
				} catch (NumberFormatException nfe)
				{ 
					affiche ("Saisie NON NUMERIQUE... Resaisissez SVP.");
				} catch (AbandonException abe)  {
					if (saisieOuiNon("VOULEZ VOUS ABANDONNER SUPERETTE OUI/NON")) throw abe; 
				}
				
	
		} while(true);
	}
	
	public boolean saisieOuiNon (String mes) {
		//return (JOptionPane.showConfirmDialog(null, mes)==0);
		return (JOptionPane.showConfirmDialog(null, mes, "FENETRE A CONFIRMER", JOptionPane.YES_NO_OPTION)==0);
	}
	
	// mon implementation
	public float lireFloat(String mes, float inf, float sup) throws AbandonException {
		do {
			try {
			String nbSaisie = JOptionPane.showInputDialog(mes);
			
			if (nbSaisie == null) throw new AbandonException();
			
			if (nbSaisie.equals("")) affiche ("Il faut saisit quelque chose, SVP!!");
			else {
				
				Float ent = Float.parseFloat(nbSaisie);
				
				if (ent >=inf && ent <= sup) return ent;
				
				throw new ErreurSaisieException();
				}
			}  
			catch(ErreurSaisieException er) 
			{
				affiche("Saisie HORS ENTERVALLE entre [ " +inf + " - " +sup+ " ]");
			} catch (NumberFormatException nfe)
			{ 
				affiche ("Saisie NON NUMERIQUE... Resaisissez SVP.");
			} catch (AbandonException abe)  {
				if (saisieOuiNon("VOULEZ VOUS ABANDONNER OUI/NON")) throw abe; 
			}
		} while (true);
	}
	
	// mon implementation
	public void affiche(String mes) {
		JOptionPane.showMessageDialog(null, mes);
	}
	
	// mon implementation
	public String lireString(String mes) {

		
		String stSaisie = JOptionPane.showInputDialog(mes);

		return stSaisie;
	}
	


}
