package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class VOPon implements Serializable {
	
	NumberFormat nf = NumberFormat.getInstance(Locale.ITALY);
	StringEscapeUtils seu = new StringEscapeUtils();
	
	public VOPon() {
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}

	//TAB 1
	private String anno;
	private String numProgettiFsePon;
	private String numProgettiFsePor;
	private String numProgettiFesrPon;
	private String numProgettiFesrPor;
	private String impFinFsePon;
	private String impFinFsePor;
	private String impFinFesrPon;
	private String impFinFesrPor;
	
	//TAB2
	private String totIntReal;
	private String impTotAut;
	private String numTotDestForm;
	private String gen;
	private String pers;
	private String aln;
	private String numMedOreFormEff;
	private String numMedCertAttCons;
	
	//TAB3
	private String tipInt;
	private String numProg;
	//private String impTotAut;
	
	//TAB4
	private String fondo;
	private String codProgetto;
	private String flgSelEsp;
	private String descProg;
	private String durataProg;
	private String link;
	
	//TAB5
	//private String fondo;
	//private String codProgetto;	
	private String flgGare;
	//private String descProg;
	private String codBenIst;
	private String descBenefici;	
	//private String link;	
	
	public String getDescBenefici() {
		return descBenefici;
	}
	public void setDescBenefici(String descBenefici) {
		this.descBenefici = descBenefici;
	}
	//TAB6
	//private String tipInt;
	private String numMod;
	private String percAvvMod;
	private String numModConcl;
	private String numDestCoinv;
	
	
	//TAB7 
	//private String tipInt;	
	private String impAmmFin;
	private String statoAv;
	
	
	private String scuola;
	private String cod;
	private String codFondo;
	
	private String id;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodFondo() {
		return codFondo;
	}
	public void setCodFondo(String codFondo) {
		this.codFondo = codFondo;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	private String des;
	
	public String getScuola() {
		return scuola;
	}
	public void setScuola(String scuola) {
		this.scuola = scuola;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getNumProgettiFsePon() {
		return numProgettiFsePon;
	}
	public void setNumProgettiFsePon(String numProgettiFsePon) {
		this.numProgettiFsePon = numProgettiFsePon;
	}
	public String getNumProgettiFsePor() {
		return numProgettiFsePor;
	}
	public void setNumProgettiFsePor(String numProgettiFsePor) {
		this.numProgettiFsePor = numProgettiFsePor;
	}
	public String getNumProgettiFesrPon() {
		return numProgettiFesrPon;
	}
	public void setNumProgettiFesrPon(String numProgettiFesrPon) {
		this.numProgettiFesrPon = numProgettiFesrPon;
	}
	public String getNumProgettiFesrPor() {
		return numProgettiFesrPor;
	}
	public void setNumProgettiFesrPor(String numProgettiFesrPor) {
		this.numProgettiFesrPor = numProgettiFesrPor;
	}
	public String getImpFinFsePon() {
		return impFinFsePon;
	}
	public String getImpFinFsePonFormat() {
		return formatDecimal(impFinFsePon);
	}
	public void setImpFinFsePon(String impFinFsePon) {
		this.impFinFsePon = impFinFsePon;
	}
	public String getImpFinFsePor() {
		return impFinFsePor;
	}
	public String getImpFinFsePorFormat() {
		return formatDecimal(impFinFsePor);
	}
	public void setImpFinFsePor(String impFinFsePor) {
		this.impFinFsePor = impFinFsePor;
	}
	public String getImpFinFesrPon() {
		return impFinFesrPon;
	}
	public String getImpFinFesrPonFormat() {
		return formatDecimal(impFinFesrPon);
	}
	public void setImpFinFesrPon(String impFinFesrPon) {
		this.impFinFesrPon = impFinFesrPon;
	}
	public String getImpFinFesrPor() {
		return impFinFesrPor;
	}
	public String getImpFinFesrPorFormat() {
		return formatDecimal(impFinFesrPor);
	}
	public void setImpFinFesrPor(String impFinFesrPor) {
		this.impFinFesrPor = impFinFesrPor;
	}
	public String getTotIntReal() {
		return totIntReal;
	}
	public void setTotIntReal(String totIntReal) {
		this.totIntReal = totIntReal;
	}
	public String getImpTotAut() {
		return impTotAut;
	}
	public String getImpTotAutFormat() {
		return formatDecimal(impTotAut);
	}
	public void setImpTotAut(String impTotAut) {
		this.impTotAut = impTotAut;
	}
	public String getNumTotDestForm() {
		return numTotDestForm;
	}
	public void setNumTotDestForm(String numTotDestForm) {
		this.numTotDestForm = numTotDestForm;
	}
	public String getGen() {
		return gen;
	}
	public String getGenFormat() {
		return formatDecimal(gen);
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getPers() {
		return pers;
	}
	public String getPersFormat() {
		return formatDecimal(pers);
	}
	public void setPers(String pers) {
		this.pers = pers;
	}
	public String getAln() {
		return aln;
	}
	public String getAlnFormat() {
		return formatDecimal(aln);
	}
	public void setAln(String aln) {
		this.aln = aln;
	}
	public String getNumMedOreFormEff() {
		return numMedOreFormEff;
	}
	public String getNumMedOreFormEffFormat() {
		return formatDecimal(numMedOreFormEff);
	}
	public void setNumMedOreFormEff(String numMedOreFormEff) {
		this.numMedOreFormEff = numMedOreFormEff;
	}
	public String getNumMedCertAttCons() {
		return numMedCertAttCons;
	}
	public String getNumMedCertAttConsFormat() {
		return formatDecimal(numMedCertAttCons);
	}
	public void setNumMedCertAttCons(String numMedCertAttCons) {
		this.numMedCertAttCons = numMedCertAttCons;
	}
	public String getNumProg() {
		return numProg;
	}
	public void setNumProg(String numProg) {
		this.numProg = numProg;
	}
	public String getFondo() {
		return fondo;
	}
	public void setFondo(String fondo) {
		this.fondo = fondo;
	}
	public String getCodProgetto() {
		return codProgetto;
	}
	public void setCodProgetto(String codProgetto) {
		this.codProgetto = codProgetto;
	}
	public String getFlgSelEsp() {
		return flgSelEsp;
	}
	public void setFlgSelEsp(String flgSelEsp) {
		this.flgSelEsp = flgSelEsp;
	}
	public String getDescProg() {
		return descProg;
	}
	public String getDescProgFormat() {
		return formatDescProg(descProg);
	}
	public void setDescProg(String descProg) {
		this.descProg = descProg;
	}
	public String getDurataProg() {
		return durataProg;
	}
	public void setDurataProg(String durataProg) {
		this.durataProg = durataProg;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getFlgGare() {
		return flgGare;
	}
	public void setFlgGare(String flgGare) {
		this.flgGare = flgGare;
	}
	public String getCodBenIst() {
		return codBenIst;
	}
	public void setCodBenIst(String codBenIst) {
		this.codBenIst = codBenIst;
	}
	public String getNumMod() {
		return numMod;
	}
	public void setNumMod(String numMod) {
		this.numMod = numMod;
	}
	public String getPercAvvMod() {
		return percAvvMod;
	}
	public String getPercAvvModFormat() {
		return formatDecimal(percAvvMod);
	}
	public void setPercAvvMod(String percAvvMod) {
		this.percAvvMod = percAvvMod;
	}
	public String getNumModConcl() {
		return numModConcl;
	}
	public void setNumModConcl(String numModConcl) {
		this.numModConcl = numModConcl;
	}
	public String getNumDestCoinv() {
		return numDestCoinv;
	}
	public void setNumDestCoinv(String numDestCoinv) {
		this.numDestCoinv = numDestCoinv;
	}
	public String getImpAmmFin() {
		return impAmmFin;
	}
	public String getImpAmmFinFormat() {
		return formatDecimal(impAmmFin);
	}
	public void setImpAmmFin(String impAmmFin) {
		this.impAmmFin = impAmmFin;
	}
	public String getStatoAv() {
		return statoAv;
	}
	public void setStatoAv(String statoAv) {
		this.statoAv = statoAv;
	}
	public String getTipInt() {
		return tipInt;
	}
	public void setTipInt(String tipInt) {
		this.tipInt = tipInt;
	}
	
	private String formatDescProg(String $str2Format){
		String retStr = $str2Format;
		if(retStr == null){
			retStr = "";
		}
		else{
			retStr = StringEscapeUtils.unescapeXml(retStr.trim());
			// rimuovo eventuale ultima virgola
			if(retStr.charAt(retStr.length() - 1) == ',' && retStr.length() >= 1){
				retStr = retStr.substring(0, retStr.length() - 1);
			}
		}
		return retStr;
	}
	
	private String formatDecimal(String $str2Format){
		try{
			return $str2Format!=null?nf.format(Double.parseDouble($str2Format)):"";
		}
		catch(Exception ex){
			return "";
		}
	}
	
	
	public static void main(String[] args){
		VOPon vpon = new VOPon();
		vpon.setImpFinFsePon("123456789.0998");
		System.out.println(vpon.getImpFinFsePonFormat());
		vpon.setImpFinFsePor("2314f68.07");
		System.out.println(vpon.getImpFinFsePorFormat());
		vpon.setImpFinFesrPon("12355786789.0167");
		System.out.println(vpon.getImpFinFesrPonFormat());
		vpon.setImpFinFesrPor("24564568,890");
		System.out.println(vpon.getImpFinFesrPorFormat());
		String toMod = "Valutazione d&apos;inquinamento indoor: cosa respiriamo nelle nostre case,";
		System.out.println(vpon.formatDescProg(toMod));
		
	}
}