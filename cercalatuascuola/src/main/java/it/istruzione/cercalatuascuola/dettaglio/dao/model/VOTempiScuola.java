package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOTempiScuola  implements Serializable 
{
	
	private String codSer;
	private String desSer;	
	private String descTemp = "";
	private String codTemp = "";
	private int datAnnScoRil;		
	
	public String getCodSer() {
		return codSer;
	}
	public void setCodSer(String codSer) {
		this.codSer = codSer;
	}
	public String getDesSer() {
		return desSer;
	}
	public void setDesSer(String desSer) {
		this.desSer = desSer;
	}
	public String getDescTemp() {
		return descTemp;
	}
	public void setDescTemp(String descTemp) {
		this.descTemp = descTemp;
	}
	public String getCodTemp() {
		return codTemp;
	}
	public void setCodTemp(String codTemp) {
		this.codTemp = codTemp;
	}
	public int getDatAnnScoRil() {
		return datAnnScoRil;
	}
	public void setDatAnnScoRil(int datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	
}