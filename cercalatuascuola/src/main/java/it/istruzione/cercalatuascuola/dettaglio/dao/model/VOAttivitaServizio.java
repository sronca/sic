package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOAttivitaServizio implements Serializable {

	private String codAttSer;	
	private String desAttSer;
	private String desFonPro;
	private String codTipCam;
	private String desValAmm;
	private String numLunMas;
	private String numOrdVis;
	private String codStaPub;
	private String desNot;
	private String desVal;
		
	private String attSerAttivo; // checkbox
			
	public String getCodAttSer() {
		return codAttSer;
	}
	
	public void setCodAttSer(String codAttSer) {
		this.codAttSer = codAttSer;
	}
	
	public String getDesAttSer() {
		return desAttSer;
	}
	
	public void setDesAttSer(String desAttSer) {
		this.desAttSer = desAttSer;
	}
	
	public String getDesFonPro() {
		return desFonPro;
	}
	
	public void setDesFonPro(String desFonPro) {
		this.desFonPro = desFonPro;
	}
	
	public String getCodTipCam() {
		return codTipCam;
	}
	
	public void setCodTipCam(String codTipCam) {
		this.codTipCam = codTipCam;
	}
	
	public String getDesValAmm() {
		return desValAmm;
	}
	
	public void setDesValAmm(String desValAmm) {
		this.desValAmm = desValAmm;
	}
	
	public String getNumLunMas() {
		return numLunMas;
	}
	
	public void setNumLunMas(String numLunMas) {
		this.numLunMas = numLunMas;
	}

	public String getDesNot() {
		return desNot;
	}

	public void setDesNot(String desNot) {
		this.desNot = desNot;
	}

	public String getNumOrdVis() {
		return numOrdVis;
	}

	public void setNumOrdVis(String numOrdVis) {
		this.numOrdVis = numOrdVis;
	}

	public String getAttSerAttivo() {
		return attSerAttivo;
	}

	public void setAttSerAttivo(String attSerAttivo) {
		this.attSerAttivo = attSerAttivo;
	}

	public String getCodStaPub() {
		return codStaPub;
	}

	public void setCodStaPub(String codStaPub) {
		this.codStaPub = codStaPub;
	}

	public String getDesVal() {
		return desVal;
	}

	public void setDesVal(String desVal) {
		this.desVal = desVal;
	}
}