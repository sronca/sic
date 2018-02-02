package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VONews  implements Serializable 
{
	
	private String codScuUt;
	private String datAnnScoRil;
	private String datBac;
	private String datBacShort;
	private String desTitBac;
	private String desOggBac;
	private String codScuIstPri;
	private String codForScu;
	private String codUteUltMov;
	private String flgStaPub;
	private String datOraUltMov;
	
	
	
	public String getDatOraUltMov() {
		return datOraUltMov;
	}
	public void setDatOraUltMov(String datOraUltMov) {
		this.datOraUltMov = datOraUltMov;
	}
	public String getFlgStaPub() {
		return flgStaPub;
	}
	public void setFlgStaPub(String flgStaPub) {
		this.flgStaPub = flgStaPub;
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
	public String getDatBac() {
		return datBac;
	}
	public void setDatBac(String datBac) {
		this.datBac = datBac;
		this.datBacShort = datBac.substring(0,datBac.lastIndexOf(":"));
	}
	public String getDesTitBac() {
		return desTitBac;
	}
	public String getDatBacShort() {
		return datBacShort;
	}
	public void setDesTitBac(String desTitBac) {
		this.desTitBac = desTitBac;
	}
	public String getDesOggBac() {
		return desOggBac;
	}
	public void setDesOggBac(String desOggBac) {
		this.desOggBac = desOggBac;
	}
	public String getCodScuIstPri() {
		return codScuIstPri;
	}
	public void setCodScuIstPri(String codScuIstPri) {
		this.codScuIstPri = codScuIstPri;
	}
	public String getCodForScu() {
		return codForScu;
	}
	public void setCodForScu(String codForScu) {
		this.codForScu = codForScu;
	}
	public String getCodUteUltMov() {
		return codUteUltMov;
	}
	public void setCodUteUltMov(String codUteUltMov) {
		this.codUteUltMov = codUteUltMov;
	}

}