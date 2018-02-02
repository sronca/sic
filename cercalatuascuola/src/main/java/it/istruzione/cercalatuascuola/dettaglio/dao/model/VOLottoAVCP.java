package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VOLottoAVCP {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
	
	private NumberFormat nf = NumberFormat.getInstance(Locale.ITALY);
	
	public VOLottoAVCP() {
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}
	
	private String formatDecimal(Float $val2Format){
		try{
			return $val2Format!=null?nf.format($val2Format):"";
		}
		catch(Exception ex){
			return "";
		}
	}
	
	private String cig;
	
	private String strutturaProponenteCF;
	
	private String strutturaProponenteDesc;
	
	private String oggetto;
	
	private String proceduraDiScelta;
	
	private Float importoAggiudicazione;
	
	private Date dataInizio;
	
	private Date dataFine;
	
	private Float importoLiquidato;

	public String getCig() {
		return cig;
	}

	public void setCig(String cig) {
		this.cig = cig;
	}

	public String getStrutturaProponenteCF() {
		return strutturaProponenteCF;
	}

	public void setStrutturaProponenteCF(String $strutturaProponenteCF) {
		strutturaProponenteCF = $strutturaProponenteCF;
	}

	public String getStrutturaProponenteDesc() {
		return strutturaProponenteDesc;
	}

	public void setStrutturaProponenteDesc(String $strutturaProponenteDesc) {
		strutturaProponenteDesc = $strutturaProponenteDesc;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getProceduraDiScelta() {
		return proceduraDiScelta;
	}

	public void setProceduraDiScelta(String proceduraDiScelta) {
		this.proceduraDiScelta = proceduraDiScelta;
	}

	public Float getImportoAggiudicazione() {
		return importoAggiudicazione;
	}
	
	public String getImportoAggiudicazioneFormat() {
		return formatDecimal(importoAggiudicazione);
	}

	public void setImportoAggiudicazione(Float importoAggiudicazione) {
		this.importoAggiudicazione = importoAggiudicazione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public String getDataInizioFormat() {
		return dataInizio!=null?sdf.format(dataInizio):"";
	}
	
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}
	
	public String getDataFineFormat() {
		return dataFine!=null?sdf.format(dataFine):"";
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Float getImportoLiquidato() {
		return importoLiquidato;
	}

	public String getImportoLiquidatoFormat() {
		return formatDecimal(importoLiquidato);
	}
	
	public void setImportoLiquidato(Float importoLiquidato) {
		this.importoLiquidato = importoLiquidato;
	}
	
	
}
