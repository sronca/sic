package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOCorsiCFP implements Serializable {

	private String codScuUt;
	private String datAnnScoRil;
	
	//corsi per i centri professionali
	private String cpCodiceCorso;
	private String cpTitoloCorso;
	private String cpCicloFormativo;
	private String cpSettore;
	private String cpComparto;
	private String cpTotaleOre;
	private String cpTotaleOreStage;
	
	public String getCpCodiceCorso() {
		return cpCodiceCorso;
	}
	public void setCpCodiceCorso(String cpCodiceCorso) {
		this.cpCodiceCorso = cpCodiceCorso;
	}
	
	public String getCpTitoloCorso() {
		return cpTitoloCorso;
	}
	public void setCpTitoloCorso(String cpTitoloCorso) {
		this.cpTitoloCorso = cpTitoloCorso;
	}
	
	public String getCpCicloFormativo() {
		return cpCicloFormativo;
	}
	public void setCpCicloFormativo(String cpCicloFormativo) {
		this.cpCicloFormativo = cpCicloFormativo;
	}
	
	public String getCpSettore() {
		return cpSettore;
	}
	public void setCpSettore(String cpSettore) {
		this.cpSettore = cpSettore;
	}
	
	public String getCpComparto() {
		return cpComparto;
	}
	public void setCpComparto(String cpComparto) {
		this.cpComparto = cpComparto;
	}
	
	public String getCpTotaleOre() {
		return cpTotaleOre;
	}
	public void setCpTotaleOre(String cpTotaleOre) {
		this.cpTotaleOre = cpTotaleOre;
	}
	
	public String getCpTotaleOreStage() {
		return cpTotaleOreStage;
	}
	public void setCpTotaleOreStage(String cpTotaleOreStage) {
		this.cpTotaleOreStage = cpTotaleOreStage;
	}
	
	
	public String getCodScuUt() {
		return codScuUt;
	}
	
	public void setCodScuUt(String codScuUt) {
		this.codScuUt = codScuUt;
	}
	
	
	
	public String getDatAnnScoRil() {
		return datAnnScoRil;
	}
	
	public void setDatAnnScoRil(String datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	
	
	
}
