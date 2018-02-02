package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOTipoDocumento implements Serializable {

	private String codTipFil;
	private String desTipFil;
	private String numMaxSiz;
	private String desExtFil;
	private String flgBinFil;
	private String flgMulFil;
	
	public String getCodTipFil() {
		return codTipFil;
	}
	
	public void setCodTipFil(String codTipFil) {
		this.codTipFil = codTipFil;
	}
	
	public String getDesTipFil() {
		return desTipFil;
	}
	
	public void setDesTipFil(String desTipFil) {
		this.desTipFil = desTipFil;
	}
	
	public String getNumMaxSiz() {
		return numMaxSiz;
	}
	
	public void setNumMaxSiz(String numMaxSiz) {
		this.numMaxSiz = numMaxSiz;
	}
	
	public String getDesExtFil() {
		return desExtFil;
	}
	
	public void setDesExtFil(String desExtFil) {
		this.desExtFil = desExtFil;
	}
	
	public String getFlgBinFil() {
		return flgBinFil;
	}
	
	public void setFlgBinFil(String flgBinFil) {
		this.flgBinFil = flgBinFil;
	}
	
	public String getFlgMulFil() {
		return flgMulFil;
	}
	
	public void setFlgMulFil(String flgMulFil) {
		this.flgMulFil = flgMulFil;
	}
}
