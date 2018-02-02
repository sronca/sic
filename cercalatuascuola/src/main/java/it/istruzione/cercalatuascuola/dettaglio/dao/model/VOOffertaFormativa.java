package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOOffertaFormativa  implements Serializable 
{
	
	private String codSer;
	private String desSer;	
	private String descInd = "";
	private String codIndMin = "";
	private String codClaMin = "";
	private int datAnnScoRil;
	private int perAnnIndCor;	
	private String codScu = "";
	private String desPer = "";
	
	public String getDesPer() {
		return desPer;
	}

	public void setDesPer(String desPer) {
		this.desPer = desPer;
	}

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

	public String getDescInd() {
		return descInd;
	}

	public void setDescInd(String descInd) {
		this.descInd = descInd;
	}

	public String getCodIndMin() {
		return codIndMin;
	}

	public void setCodIndMin(String codIndMin) {
		this.codIndMin = codIndMin;
	}

	public String getCodClaMin() {
		return codClaMin;
	}

	public void setCodClaMin(String codClaMin) {
		this.codClaMin = codClaMin;
	}

	public int getDatAnnScoRil() {
		return datAnnScoRil;
	}

	public void setDatAnnScoRil(int datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}

	public int getPerAnnIndCor() {
		return perAnnIndCor;
	}

	public void setPerAnnIndCor(int perAnnIndCor) {
		this.perAnnIndCor = perAnnIndCor;
	}

	public String getCodScu() {
		return codScu;
	}

	public void setCodScu(String codScu) {
		this.codScu = codScu;
	}
	
	public String toString(){
		return this.getCodSer();
	}
	
}