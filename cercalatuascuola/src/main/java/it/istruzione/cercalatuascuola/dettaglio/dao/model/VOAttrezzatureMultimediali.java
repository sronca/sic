package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOAttrezzatureMultimediali implements Serializable {	
	private String codScuUt;
	private String codScuUtPri;
	private String datAnnScoRil;
	
	private Integer numTotPcDes;
	private Integer numTotPcLap;
	private Integer numTotLimAu;
	private Integer numTotLimMo;
	private Integer numTotLimLb;
	private Integer numTotAul;
	private Integer numTotAulLan;
	private Integer numTotAulWi;
	
	private Integer numTotLim;
	private Float numAluPerPc;
	private Float percentAulLan;
	private Float percentAulWi;
	
	//Nuova tab attrezzature multimediali
	private String compLab = "";
	public String getCompLab() {
		return compLab;
	}

	public void setCompLab(String compLab) {
		this.compLab = compLab;
	}

	public String getCompBib() {
		return compBib;
	}

	public void setCompBib(String compBib) {
		this.compBib = compBib;
	}

	public String getLimLab() {
		return limLab;
	}

	public void setLimLab(String limLab) {
		this.limLab = limLab;
	}

	public String getLimBib() {
		return limBib;
	}

	public void setLimBib(String limBib) {
		this.limBib = limBib;
	}

	public String getProiettLab() {
		return proiettLab;
	}

	public void setProiettLab(String proiettLab) {
		this.proiettLab = proiettLab;
	}

	public String getProiettBib() {
		return proiettBib;
	}

	public void setProiettBib(String proiettBib) {
		this.proiettBib = proiettBib;
	}

	public String getCompScu() {
		return compScu;
	}

	public void setCompScu(String compScu) {
		this.compScu = compScu;
	}

	public String getDispMobScu() {
		return dispMobScu;
	}

	public void setDispMobScu(String dispMobScu) {
		this.dispMobScu = dispMobScu;
	}

	public String getLimScu() {
		return limScu;
	}

	public void setLimScu(String limScu) {
		this.limScu = limScu;
	}

	public String getProiettScu() {
		return proiettScu;
	}

	public void setProiettScu(String proiettScu) {
		this.proiettScu = proiettScu;
	}

	public String getNumAuleDid() {
		return numAuleDid;
	}

	public void setNumAuleDid(String numAuleDid) {
		this.numAuleDid = numAuleDid;
	}

	public String getPercAuleConn() {
		return percAuleConn;
	}

	public void setPercAuleConn(String percAuleConn) {
		this.percAuleConn = percAuleConn;
	}

	private String compBib = "";
	private String limLab = "";
	private String limBib = "";
	private String proiettLab = "";
	private String proiettBib = "";
	private String compScu = "";
	private String dispMobScu = "";
	private String limScu = "";
	private String proiettScu = "";
	private String numAuleDid = "";
	private String percAuleConn = "";	
	
	public String getCodScuUt() {
		return codScuUt;
	}
	
	public void setCodScuUt(String codScuUt) {
		this.codScuUt = codScuUt;
	}
	
	public String getCodScuUtPri() {
		return codScuUtPri;
	}
	
	public void setCodScuUtPri(String codScuUtPri) {
		this.codScuUtPri = codScuUtPri;
	}
	
	public String getDatAnnScoRil() {
		return datAnnScoRil;
	}
	
	public void setDatAnnScoRil(String datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	
	public Integer getNumTotPcDes() {
		return numTotPcDes;
	}
	
	public void setNumTotPcDes(Integer numTotPcDes) {
		this.numTotPcDes = numTotPcDes;
	}
	
	public Integer getNumTotPcLap() {
		return numTotPcLap;
	}
	
	public void setNumTotPcLap(Integer numTotPcLap) {
		this.numTotPcLap = numTotPcLap;
	}
	
	public Integer getNumTotLimAu() {
		return numTotLimAu;
	}
	
	public void setNumTotLimAu(Integer numTotLimAu) {
		this.numTotLimAu = numTotLimAu;
	}
	
	public Integer getNumTotLimMo() {
		return numTotLimMo;
	}
	
	public void setNumTotLimMo(Integer numTotLimMo) {
		this.numTotLimMo = numTotLimMo;
	}
	
	public Integer getNumTotLimLb() {
		return numTotLimLb;
	}
	
	public void setNumTotLimLb(Integer numTotLimLb) {
		this.numTotLimLb = numTotLimLb;
	}
	
	public Integer getNumTotAul() {
		return numTotAul;
	}
	
	public void setNumTotAul(Integer numTotAul) {
		this.numTotAul = numTotAul;
	}
	
	public Integer getNumTotAulLan() {
		return numTotAulLan;
	}
	
	public void setNumTotAulLan(Integer numTotAulLan) {
		this.numTotAulLan = numTotAulLan;
	}
	
	public Integer getNumTotAulWi() {
		return numTotAulWi;
	}
	
	public void setNumTotAulWi(Integer numTotAulWi) {
		this.numTotAulWi = numTotAulWi;
	}

	public Float getNumAluPerPc() {
		return numAluPerPc;
	}
	
	public void setNumAluPerPc(Float numAluPerPc) {
		this.numAluPerPc = numAluPerPc;
	}

	public Integer getNumTotLim() {
		return numTotLim;
	}

	public void setNumTotLim(Integer numTotLim) {
		this.numTotLim = numTotLim;
	}

	public Float getPercentAulLan() {
		return percentAulLan;
	}
	
	public void setPercentAulLan(Float percentAulLan) {
		this.percentAulLan = percentAulLan;
	}

	public Float getPercentAulWi() {
		return percentAulWi;
	}
	
	public void setPercentAulWi(Float percentAulWi) {
		this.percentAulWi = percentAulWi;
	}
}
