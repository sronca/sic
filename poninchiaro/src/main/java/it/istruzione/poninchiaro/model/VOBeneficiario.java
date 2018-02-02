package it.istruzione.poninchiaro.model;

import it.istruzione.poninchiaro.common.util.Utility;

import java.math.BigDecimal;

public class VOBeneficiario {
	
	private String denominazione;
	private BigDecimal importoAutorizzato;
	private BigDecimal importoErogato;
	private int numeroProgetti;
	
	private BigDecimal importoAutorizzatoFSE;
	private BigDecimal importoAutorizzatoFESR;
	private int numeroProgettiFSE;
	private int numeroProgettiFESR;

	public BigDecimal getImportoAutorizzato() {
		return importoAutorizzato;
	}
	public void setImportoAutorizzato(BigDecimal importoAutorizzato) {
		this.importoAutorizzato = importoAutorizzato;
	}
	public BigDecimal getImportoErogato() {
		return importoErogato;
	}
	public void setImportoErogato(BigDecimal importoErogato) {
		this.importoErogato = importoErogato;
	}
	public int getNumeroProgetti() {
		return numeroProgetti;
	}
	public void setNumeroProgetti(int numeroProgetti) {
		this.numeroProgetti = numeroProgetti;
	}

	public String getDenominazione() {
		String out = "";
		if (denominazione != null){
			out = denominazione.replaceAll("\"", "").replaceAll("'", "");
		}
		return out;
	}	
	
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getDesNomScuNorm() {
		return Utility.normalizzaNomeScuola(denominazione);
	}
	public BigDecimal getImportoAutorizzatoFSE() {
		return importoAutorizzatoFSE;
	}
	public void setImportoAutorizzatoFSE(BigDecimal importoAutorizzatoFSE) {
		this.importoAutorizzatoFSE = importoAutorizzatoFSE;
	}
	public BigDecimal getImportoAutorizzatoFESR() {
		return importoAutorizzatoFESR;
	}
	public void setImportoAutorizzatoFESR(BigDecimal importoAutorizzatoFESR) {
		this.importoAutorizzatoFESR = importoAutorizzatoFESR;
	}
	public int getNumeroProgettiFSE() {
		return numeroProgettiFSE;
	}
	public void setNumeroProgettiFSE(int numeroProgettiFSE) {
		this.numeroProgettiFSE = numeroProgettiFSE;
	}
	public int getNumeroProgettiFESR() {
		return numeroProgettiFESR;
	}
	public void setNumeroProgettiFESR(int numeroProgettiFESR) {
		this.numeroProgettiFESR = numeroProgettiFESR;
	}


}
