package Serie61;

import java.io.*;

public abstract class ArticleAbstrait implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int code;
	protected String designation ;
	protected float pu;
	
//CONSTRUCTEURS
	
	public ArticleAbstrait (){}
	
	public ArticleAbstrait ( int code , String designation, float pu){
		
		this.code = code;
		this.designation= designation;
		this.pu =(float) (pu*(100/100.00));
		}
	
// METHODES ABSTRAITES
	
	public abstract String toString();
	
	public abstract float prixFacture(int quantinte);
	
	public abstract String facturer( int quantite);
	
	//GETTERS, SETTERS
	
	public int  getCode(){
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public float getPu() {
		return pu;
	}

	public void setPu(float pu) {
		this.pu = pu;
	}


}
