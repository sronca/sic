package it.istruzione.ptof.beans;

import java.util.LinkedList;


public class FabbisognoPostiComuniDTO extends BaseDTO {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String labelAnno0;
	private String labelAnno1;
	private String labelAnno2;
	
	private FabbisognoPostiComuniItem fabbisognoPostiComuniInfanzia;
	private FabbisognoPostiComuniItem fabbisognoPostiComuniPrimaria;
	private LinkedList<FabbisognoPostiComuniItem> fabbisognoPostiComuniIGrado;
	private LinkedList<FabbisognoPostiComuniItem> fabbisognoPostiComuniIIGrado;
	
	public FabbisognoPostiComuniDTO() {
		 
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

	public FabbisognoPostiComuniItem getFabbisognoPostiComuniInfanzia() {
		return fabbisognoPostiComuniInfanzia;
	}

	public void setFabbisognoPostiComuniInfanzia(
			FabbisognoPostiComuniItem fabbisognoPostiComuniInfanzia) {
		this.fabbisognoPostiComuniInfanzia = fabbisognoPostiComuniInfanzia;
	}

	public FabbisognoPostiComuniItem getFabbisognoPostiComuniPrimaria() {
		return fabbisognoPostiComuniPrimaria;
	}

	public void setFabbisognoPostiComuniPrimaria(
			FabbisognoPostiComuniItem fabbisognoPostiComuniPrimaria) {
		this.fabbisognoPostiComuniPrimaria = fabbisognoPostiComuniPrimaria;
	}

	public LinkedList<FabbisognoPostiComuniItem> getFabbisognoPostiComuniIGrado() {
		return fabbisognoPostiComuniIGrado;
	}

	public void setFabbisognoPostiComuniIGrado(
			LinkedList<FabbisognoPostiComuniItem> fabbisognoPostiComuniIGrado) {
		this.fabbisognoPostiComuniIGrado = fabbisognoPostiComuniIGrado;
	}

	public LinkedList<FabbisognoPostiComuniItem> getFabbisognoPostiComuniIIGrado() {
		return fabbisognoPostiComuniIIGrado;
	}

	public void setFabbisognoPostiComuniIIGrado(
			LinkedList<FabbisognoPostiComuniItem> fabbisognoPostiComuniIIGrado) {
		this.fabbisognoPostiComuniIIGrado = fabbisognoPostiComuniIIGrado;
	}


	 
 
}
