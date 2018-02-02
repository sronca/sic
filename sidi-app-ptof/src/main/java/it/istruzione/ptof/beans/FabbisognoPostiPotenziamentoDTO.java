package it.istruzione.ptof.beans;

import java.util.LinkedList;


public class FabbisognoPostiPotenziamentoDTO extends BaseDTO {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String labelAnno0;
	private String labelAnno1;
	private String labelAnno2;	
	
	FabbisognoPostiPotenziamentoPrimariaItem fabbisognoPostiPotenziamentoPrimaria;
	LinkedList<FabbisognoPostiPotenziamentoItem> fabbisognoPostiPotenziamentoIGrado;
	FabbisognoPostiPotenziamentoDiSostegnoItem fabbisognoPostiPotenziamentoDiSostegnoIGrado;
	LinkedList<FabbisognoPostiPotenziamentoItem> fabbisognoPostiPotenziamentoIIGrado;
	FabbisognoPostiPotenziamentoDiSostegnoItem fabbisognoPostiPotenziamentoDiSostegnoIIGrado;

	public FabbisognoPostiPotenziamentoDTO() {
	}

	public String getLabelAnno0() {
		return labelAnno0;
	}

	public void setLabelAnno0(String labelAnno0) {
		this.labelAnno0 = labelAnno0;
	}

	public String getLabelAnno1() {
		return labelAnno1;
	}

	public void setLabelAnno1(String labelAnno1) {
		this.labelAnno1 = labelAnno1;
	}

	public String getLabelAnno2() {
		return labelAnno2;
	}

	public void setLabelAnno2(String labelAnno2) {
		this.labelAnno2 = labelAnno2;
	}

	public FabbisognoPostiPotenziamentoPrimariaItem getFabbisognoPostiPotenziamentoPrimaria() {
		return fabbisognoPostiPotenziamentoPrimaria;
	}

	public void setFabbisognoPostiPotenziamentoPrimaria(
			FabbisognoPostiPotenziamentoPrimariaItem fabbisognoPostiPotenziamentoPrimaria) {
		this.fabbisognoPostiPotenziamentoPrimaria = fabbisognoPostiPotenziamentoPrimaria;
	}

	public LinkedList<FabbisognoPostiPotenziamentoItem> getFabbisognoPostiPotenziamentoIGrado() {
		return fabbisognoPostiPotenziamentoIGrado;
	}

	public void setFabbisognoPostiPotenziamentoIGrado(
			LinkedList<FabbisognoPostiPotenziamentoItem> fabbisognoPostiPotenziamentoIGrado) {
		this.fabbisognoPostiPotenziamentoIGrado = fabbisognoPostiPotenziamentoIGrado;
	}

	public FabbisognoPostiPotenziamentoDiSostegnoItem getFabbisognoPostiPotenziamentoDiSostegnoIGrado() {
		return fabbisognoPostiPotenziamentoDiSostegnoIGrado;
	}

	public void setFabbisognoPostiPotenziamentoDiSostegnoIGrado(
			FabbisognoPostiPotenziamentoDiSostegnoItem fabbisognoPostiPotenziamentoDiSostegnoIGrado) {
		this.fabbisognoPostiPotenziamentoDiSostegnoIGrado = fabbisognoPostiPotenziamentoDiSostegnoIGrado;
	}

	public LinkedList<FabbisognoPostiPotenziamentoItem> getFabbisognoPostiPotenziamentoIIGrado() {
		return fabbisognoPostiPotenziamentoIIGrado;
	}

	public void setFabbisognoPostiPotenziamentoIIGrado(
			LinkedList<FabbisognoPostiPotenziamentoItem> fabbisognoPostiPotenziamentoIIGrado) {
		this.fabbisognoPostiPotenziamentoIIGrado = fabbisognoPostiPotenziamentoIIGrado;
	}

	public FabbisognoPostiPotenziamentoDiSostegnoItem getFabbisognoPostiPotenziamentoDiSostegnoIIGrado() {
		return fabbisognoPostiPotenziamentoDiSostegnoIIGrado;
	}

	public void setFabbisognoPostiPotenziamentoDiSostegnoIIGrado(
			FabbisognoPostiPotenziamentoDiSostegnoItem fabbisognoPostiPotenziamentoDiSostegnoIIGrado) {
		this.fabbisognoPostiPotenziamentoDiSostegnoIIGrado = fabbisognoPostiPotenziamentoDiSostegnoIIGrado;
	}


}
