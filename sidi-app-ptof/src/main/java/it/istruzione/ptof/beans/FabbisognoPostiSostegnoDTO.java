package it.istruzione.ptof.beans;

import java.util.LinkedList;


public class FabbisognoPostiSostegnoDTO extends BaseDTO {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String labelAnno0;
	private String labelAnno1;
	private String labelAnno2;
	
	FabbisognoPostiSostegnoItem fabbisognoPostiSostegnoInfanzia;
	FabbisognoPostiSostegnoItem fabbisognoPostiSostegnoPrimaria;
	FabbisognoPostiSostegnoItem fabbisognoPostiSostegnoIGrado;
	FabbisognoPostiSostegnoIIGradoItem fabbisognoPostiSostegnoIIGrado;

	public FabbisognoPostiSostegnoDTO() {
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

	public FabbisognoPostiSostegnoItem getFabbisognoPostiSostegnoInfanzia() {
		return fabbisognoPostiSostegnoInfanzia;
	}

	public void setFabbisognoPostiSostegnoInfanzia(
			FabbisognoPostiSostegnoItem fabbisognoPostiSostegnoInfanzia) {
		this.fabbisognoPostiSostegnoInfanzia = fabbisognoPostiSostegnoInfanzia;
	}

	public FabbisognoPostiSostegnoItem getFabbisognoPostiSostegnoPrimaria() {
		return fabbisognoPostiSostegnoPrimaria;
	}

	public void setFabbisognoPostiSostegnoPrimaria(
			FabbisognoPostiSostegnoItem fabbisognoPostiSostegnoPrimaria) {
		this.fabbisognoPostiSostegnoPrimaria = fabbisognoPostiSostegnoPrimaria;
	}

	public FabbisognoPostiSostegnoItem getFabbisognoPostiSostegnoIGrado() {
		return fabbisognoPostiSostegnoIGrado;
	}

	public void setFabbisognoPostiSostegnoIGrado(
			FabbisognoPostiSostegnoItem fabbisognoPostiSostegnoIGrado) {
		this.fabbisognoPostiSostegnoIGrado = fabbisognoPostiSostegnoIGrado;
	}

	public FabbisognoPostiSostegnoIIGradoItem getFabbisognoPostiSostegnoIIGrado() {
		return fabbisognoPostiSostegnoIIGrado;
	}

	public void setFabbisognoPostiSostegnoIIGrado(
			FabbisognoPostiSostegnoIIGradoItem fabbisognoPostiSostegnoIIGrado) {
		this.fabbisognoPostiSostegnoIIGrado = fabbisognoPostiSostegnoIIGrado;
	}

	
	
}
