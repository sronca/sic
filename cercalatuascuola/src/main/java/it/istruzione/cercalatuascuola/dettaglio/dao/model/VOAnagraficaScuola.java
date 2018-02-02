package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import it.istruzione.cercalatuascuola.common.util.Utility;

import java.io.Serializable;
import java.util.List;

/**
 * @author slsRonca
 *
 */
public class VOAnagraficaScuola implements Serializable {

	private String codScuUt;
	private String codScuUtPri;
	private String codForScuApp;
	private String desNomScu;
	private String denScuPri;
	private String desIndScu;
	private String desIndEml;
	private String codTelScu;
	private String codFaxScu;
	private String datAnnScoRil;
	private String desAnnScoRil;	
	private String codReg;
	private String desReg;
	private String codPrv;
	private String desPrv;
	private String codCom;
	private String desCom;
	private String codCap;
	private String codFis;
	private String codTipSit;
	private String desTipSit;
	private String flgSedDir;
	private String flgIstSta;
	private String desIndWeb;
	private String desIndEmaPce;
	private List<VOCorsiCFP> listaCorsi;
	private String codCarScu;

	//private String codFiscDirigente;
	//private String cognomeDirigente;
	//private String nomeDirigente;

	private String flgPon;

	private boolean presenzaSuccursali;
	private boolean presenzaAnnunci;
	private String flagCentroFormazioneProfessionale;
	private String urlDocOrientamento;
	
	private boolean mostraPulsanteEdilizia;
	
	private List<VOMenu> caratteristicaDiplomato;
	
	public String getCodCarScu() {
		return codCarScu;
	}

	public void setCodCarScu(String codCarScu) {
		this.codCarScu = codCarScu;
	}
	
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
	
	public String getCodForScuApp() {
		return codForScuApp;
	}
	
	public void setCodForScuApp(String codForScuApp) {
		this.codForScuApp = codForScuApp;
	}
	
	public void setDesNomScu(String desNomScu) {
		this.desNomScu = desNomScu;
	}
	
	public String getDesIndScu() {
		return desIndScu;
	}
	
	public void setDesIndScu(String desIndScu) {
		this.desIndScu = desIndScu;
	}
	
	public void setDesIndEml(String desIndEml) {
		this.desIndEml = desIndEml;
	}
	
	public void setCodTelScu(String codTelScu) {
		this.codTelScu = codTelScu;
	}
	
	public void setCodFaxScu(String codFaxScu) {
		this.codFaxScu = codFaxScu;
	}
	
	public String getDatAnnScoRil() {
		return datAnnScoRil;
	}
	
	public void setDatAnnScoRil(String datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	
	public String getCodReg() {
		return codReg;
	}
	
	public void setCodReg(String codReg) {
		this.codReg = codReg;
	}
	
	public String getDesReg() {
		return desReg;
	}
	
	public void setDesReg(String desReg) {
		this.desReg = desReg;
	}
	
	public String getCodPrv() {
		return codPrv;
	}
	
	public void setCodPrv(String codPrv) {
		this.codPrv = codPrv;
	}
	
	public String getDesPrv() {
		return desPrv;
	}
	
	public void setDesPrv(String desPrv) {
		this.desPrv = desPrv;
	}
	
	public String getCodCom() {
		return codCom;
	}
	
	public void setCodCom(String codCom) {
		this.codCom = codCom;
	}
	
	public String getDesCom() {
		return desCom;
	}
	
	public void setDesCom(String desCom) {
		this.desCom = desCom;
	}
	
	public String getCodCap() {
		return codCap;
	}
	
	public void setCodCap(String codCap) {
		this.codCap = codCap;
	}
	
	public String getCodTipSit() {
		return codTipSit;
	}
	
	public void setCodTipSit(String codTipSit) {
		this.codTipSit = codTipSit;
	}
	
	public String getDesTipSit() {
		return desTipSit;
	}
	
	public void setDesTipSit(String desTipSit) {
		this.desTipSit = desTipSit;
	}
	
	public String getFlgSedDir() {
		return flgSedDir;
	}
	
	public void setFlgSedDir(String flgSedDir) {
		this.flgSedDir = flgSedDir;
	}
	
	/*
	public String getCodFiscDirigente() {
		return codFiscDirigente;
	}
	
	public void setCodFiscDirigente(String codFiscDirigente) {
		this.codFiscDirigente = codFiscDirigente;
	}
	
	public String getCognomeDirigente() {
		return cognomeDirigente;
	}
	
	public void setCognomeDirigente(String cognomeDirigente) {
		this.cognomeDirigente = cognomeDirigente;
	}
	
	public String getNomeDirigente() {
		return nomeDirigente;
	}
	
	public void setNomeDirigente(String nomeDirigente) {
		this.nomeDirigente = nomeDirigente;
	}
	*/
	
	public String getFlgIstSta() {
		return flgIstSta;
	}

	public void setFlgIstSta(String flgIstSta) {
		this.flgIstSta = flgIstSta;
	}

	public void setDesIndWeb(String desIndWeb) {
		this.desIndWeb = desIndWeb;
	}

	public void setDesIndEmaPce(String desIndEmaPce) {
		this.desIndEmaPce = desIndEmaPce;
	}

	public String getCodFis() {
		return codFis;
	}

	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}

	public boolean isPresenzaSuccursali() {
		return presenzaSuccursali;
	}

	public void setPresenzaSuccursali(boolean presenzaSuccursali) {
		this.presenzaSuccursali = presenzaSuccursali;
	}

	public boolean isPresenzaAnnunci() {
		return presenzaAnnunci;
	}

	public void setPresenzaAnnunci(boolean presenzaAnnunci) {
		this.presenzaAnnunci = presenzaAnnunci;
	}
	
	public void setDesAnnScoRil(String desAnnScoRil) {
		this.desAnnScoRil = desAnnScoRil;
	}
	
	public String getDesAnnScoRil() {
		return desAnnScoRil;
	}

	public String getFlagCentroFormazioneProfessionale() {
		return flagCentroFormazioneProfessionale;
	}

	public void setFlagCentroFormazioneProfessionale(
			String flagCentroFormazioneProfessionale) {
		this.flagCentroFormazioneProfessionale = flagCentroFormazioneProfessionale;
	}

    public String getFlgPon() {
        return flgPon;
    }

    public void setFlgPon(String flgPon) {
        this.flgPon = flgPon;
    }

    public List<VOCorsiCFP> getListaCorsi() {
		return listaCorsi;
	}

	public void setListaCorsi(List<VOCorsiCFP> listaCorsi) {
		this.listaCorsi = listaCorsi;
	}

    public String getUrlDocOrientamento() {
        return urlDocOrientamento;
    }

    public void setUrlDocOrientamento(String urlDocOrientamento) {
        this.urlDocOrientamento = urlDocOrientamento;
    }

    public boolean isSerale() {
        return this.isSecondariaSecGrado() && (Integer.parseInt(codScuUt.substring(7, 9)) > 49);
    }
    
    public boolean isCpia() {
        return "1".equals(codCarScu);
    }

    public boolean isSecondariaSecGrado() {
        return (codTipSit.compareTo("IS") == 0 || codTipSit.compareTo("MM") > 0);
    }

    public boolean isCircoloDidattico() {
        return (codTipSit.compareTo("EE") == 0 && codScuUt.substring(7, 9).compareTo("00") == 0);
    }

    public boolean isIstitutoPrincipale() {
        return codScuUt.equals(codScuUtPri);
    }
	
	public boolean isMostraPulsanteEdilizia() {
		return mostraPulsanteEdilizia;
	}

	public void setMostraPulsanteEdilizia(boolean mostraPulsanteEdilizia) {
		this.mostraPulsanteEdilizia = mostraPulsanteEdilizia;
	}


	public void setDenScuPri(String denScuPri) {
		this.denScuPri = denScuPri;
	}

	public String getIndirizzoCompleto() {
		String out = Utility.trimValue(desIndScu) +
				(Utility.trimValue(desIndScu).equals("")?" ":", ") + Utility.trimValue(codCap) +
			    " " + Utility.trimValue(desCom) +
			    " (" + Utility.trimValue(codPrv) + ")"
			   ;
		
		out = out.replaceAll("\"", "").replaceAll("'", "");
		return out;
	}
	
	
	/** TODO RIPRISTINARE **/
	public String getDesNomScu() {
		String out = "";
		if (desNomScu != null){
			out = desNomScu.replaceAll("\"", "").replaceAll("'", "");
		}
		return out;
	}
	
	public String getDenScuPri() {
		String out = "";
		if (denScuPri != null){
			out = denScuPri.replaceAll("\"", "").replaceAll("'", "");
		}
		return out;
	}
	
	public String getDesIndEml() {
		return desIndEml;
	}
	
	public String getCodTelScu() {
		return codTelScu;
	}
	
	public String getCodFaxScu() {
		return codFaxScu;
	}
	
	public String getDesIndWeb() {
		return desIndWeb;
	}
	
	public String getDesIndEmaPce() {
		return desIndEmaPce;
	}
	
	public String getDesNomScuNorm() {
		return Utility.normalizzaNomeScuola(desNomScu);
	}
	
	public String getDenScuPriNorm() {
		return Utility.normalizzaNomeScuola(denScuPri);
	}

	public List<VOMenu> getCaratteristicaDiplomato() {
		return caratteristicaDiplomato;
	}

	public void setCaratteristicaDiplomato(List<VOMenu> caratteristicaDiplomato) {
		this.caratteristicaDiplomato = caratteristicaDiplomato;
	}
	
	/** TODO FINE **/
	
	/** TODO ELIMINARE **/
//	public String getDesNomScu() {
//		return "Istituto di prova";
//	}
//	
//	public String getDenScuPri() {
//		return "Istituto principale di prova";
//	}
//	
//	public String getDesIndEml() {
//		return "istituto_di_prova@istruzione.it";
//	}
//	
//	public String getCodTelScu() {
//		return "00000000000";
//	}
//	
//	public String getCodFaxScu() {
//		return "00000000000";
//	}
//	
//	public String getDesIndWeb() {
//		return "www.istituto_di_prova.it";
//	}
//	
//	public String getDesIndEmaPce() {
//		return "istituto_di_prova@pec.it";
//	}
//	
//	public String getDesNomScuNorm() {
//		return "Istituto-di-prova";
//	}
//	
//	public String getDenScuPriNorm() {
//		return "Istituto-principale-di-prova";
//	}
	/** TODO FINE **/
}
