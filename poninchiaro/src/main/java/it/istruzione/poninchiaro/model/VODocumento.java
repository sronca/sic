package it.istruzione.poninchiaro.model;

import java.io.InputStream;
import java.io.Serializable;

public class VODocumento implements Serializable {

	private String codScuUt;
	private String datAnnScoRil;
	private String codTipFil;
	private String desTipFil;
	private String codScuUtPri;
	private InputStream oggFil;
	private Integer fileSize;
	private String prgDoc;
	private String desNot;
	private String desNomFil;
	private String desExtFil;
	private String desStaPub;
	
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
	
	public String getCodTipFil() {
		return codTipFil;
	}
	
	public void setCodTipFil(String codTipFil) {
		this.codTipFil = codTipFil;
	}
	
	public String getCodScuUtPri() {
		return codScuUtPri;
	}
	
	public void setCodScuUtPri(String codScuUtPri) {
		this.codScuUtPri = codScuUtPri;
	}
	
	public String getPrgDoc() {
		return prgDoc;
	}
	
	public void setPrgDoc(String prgDoc) {
		this.prgDoc = prgDoc;
	}
	
	public String getDesNot() {
		return desNot;
	}
	
	public void setDesNot(String desNot) {
		this.desNot = desNot;
	}
	
	public String getDesNomFil() {
		return desNomFil;
	}
	
	public void setDesNomFil(String desNomFil) {
		this.desNomFil = desNomFil;
	}
	
	public String getDesExtFil() {
		return desExtFil;
	}
	
	public void setDesExtFil(String desExtFil) {
		this.desExtFil = desExtFil;
	}
	
	public String getDesStaPub() {
		return desStaPub;
	}
	
	public void setDesStaPub(String desStaPub) {
		this.desStaPub = desStaPub;
	}

	public String getDesTipFil() {
		return desTipFil;
	}

	public void setDesTipFil(String desTipFil) {
		this.desTipFil = desTipFil;
	}

	public InputStream getOggFil() {
		return oggFil;
	}

	public void setOggFil(InputStream oggFil) {
		this.oggFil = oggFil;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
}
