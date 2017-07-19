package Utils;

import java.io.Serializable;
import java.util.*;

public class DateUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int jour;
	private int mois;
	private int annee;
	
	public DateUser() {
		Calendar calc=Calendar.getInstance();
		jour=calc.get(Calendar.DAY_OF_MONTH);
		mois=calc.get(Calendar.MONTH)+1;
		annee=calc.get(Calendar.YEAR);
	}
	
	public DateUser(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
		
	public int getJour() {
		return jour;
	}
	
	public void setJour(int jour) {
		this.jour = jour;
	}
	
	public int getMois() {
		return mois;
	}
	
	public void setMois(int mois) {
		this.mois = mois;
	}
	
	
	public int getAnnee() {
		return annee;
	}
	
	public void setAnnee(int annee) {
		this.annee= annee;
	}
	
	public static boolean validDate(int jour, int mois, int annee) {
		return (jour <= nbJourMois(mois, annee));
	}
		
	
	public static int nbJourMois(int mois, int annee) {
		switch(mois) {
		case 4: case 6: case 9: case 11: return 30;
		case 2: if (bissextile(annee)) return 29; else return 28;
		default: return 31;
		}
	}
	
	public static boolean bissextile(int annee) {
		boolean res = false;
		if ((annee % 400 == 0) || (annee % 100 !=0) && (annee % 4 == 0)) {
			res = true;
		}
		return res;
	}
	
	public void hier(){
		jour--;
		if (jour < 1){
			mois--;
			jour = nbJourMois(mois, annee);
			if (mois < 1) {jour = 31; mois = 12; annee--;}
		}
	}
	
	public DateUser hier2() {
		DateUser dateHier = new DateUser(this.jour, this.mois, this.annee);
		dateHier.hier();
		return dateHier;
	}
	
	public void lendemain() {
		jour++;
		if (jour > nbJourMois(mois, annee)) {
			jour =1; mois ++;
			if (mois > 12) {
				mois = 1;
				annee ++;
			}
		}
	}
	
	public DateUser lendemain2() {
		DateUser dateDemain = new DateUser(this.jour, this.mois, this.annee);
		dateDemain.lendemain();
		return dateDemain;
	}
	
	public DateUser ajouterNombreJours(int nbj) {
		DateUser ajouterNBJ = new DateUser(this.jour, this.mois, this.annee);
		int i=0;
		while (i< nbj) {
			ajouterNBJ.lendemain();
			i++;
		}
		return ajouterNBJ;
	}
	
	
	
	public String toString () {
		return (jour + "/" + mois + "/" + annee);
	}
	
	public boolean avant(DateUser d) {
		if (this.annee < d.getAnnee()) {
			return true;
		} else if ((this.annee == d.getAnnee()) && (this.mois <d.getMois())) {
			return true;
		} else if ((this.annee == d.getAnnee()) && (this.mois == d.getMois()) && (this.jour < d.getJour())) {
			return true;
		} else return false;
	}
	
	public int age(DateUser d) {
		DateUser today = new DateUser();
		if (this.avant(today)) { 
			if (this.annee < today.getAnnee() && this.mois < today.getMois()) {
				return (today.getAnnee() - this.annee);
			} else if (this.annee < today.getAnnee() && this.mois > today.getMois()) {
				return (today.getAnnee() - this.annee);
			} else if ((this.annee < today.getAnnee() && this.mois == today.getMois()) && this.jour <=today.getJour()) {
				return (today.getAnnee() - this.annee -1);
			}
		}
		return 0;
	}
	
	public int zeller() {
		int moisZ, anneeZ, siecZ;
		if (mois > 2) moisZ = mois-2; else {
			moisZ = mois +10; 
			annee = annee-1;
		}
		anneeZ=annee%100;
		siecZ=(int)(annee/100);
		
		return (((int)(2.6*moisZ -0.2) +jour +anneeZ + (int)(anneeZ/4) + (int)(siecZ/4) -2*siecZ)%7);
	}

	public String jourSemaine() {
		int zel = this.zeller();
		switch(zel) {
		case 0: return "Dimanche";
		case 1: return "Lundi";
		case 2: return "Mardi";
		case 3: return "Mercredi";
		case 4: return "Jeudi";
		case 5: return "Vendredi";
		case 6: return "Samedi";
		default: return "A faire";
		}
	}

	
}
