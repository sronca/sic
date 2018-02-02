package it.istruzione.ptof.beans.documenti;

 

import java.util.LinkedList;

import it.istruzione.ptof.beans.BaseDTO;

/**
 * @author mcatanzaro Sottosezione della Sezione 6- RF018– Articolazione Oraria
 *         Quadri Orari per ogni indirizzo di studio presente a sistema
 *         nell’anno in corso.
 */
public class ArticolazioneQuadriOrariMateriaDTO extends BaseDTO {

	 
	private String descrizioneMateriaScuola , presenzaClassi;

	private Integer numeroAlunniPrimo , numeroAlunniSecondo ,numeroAlunniTerzo
	  	, numeroAlunniQuarto
	    , numeroAlunniQuinto , numeroAlunniSesto  ;

	public String getDescrizioneMateriaScuola() {
		return descrizioneMateriaScuola;
	}

	public void setDescrizioneMateriaScuola(String descrizioneMateriaScuola) {
		this.descrizioneMateriaScuola = descrizioneMateriaScuola;
	}

	public Integer getNumeroAlunniPrimo() {
		return numeroAlunniPrimo;
	}

	public void setNumeroAlunniPrimo(Integer numeroAlunniPrimo) {
		this.numeroAlunniPrimo = numeroAlunniPrimo;
	}

	public Integer getNumeroAlunniSecondo() {
		return numeroAlunniSecondo;
	}

	public void setNumeroAlunniSecondo(Integer numeroAlunniSecondo) {
		this.numeroAlunniSecondo = numeroAlunniSecondo;
	}

	public Integer getNumeroAlunniTerzo() {
		return numeroAlunniTerzo;
	}

	public void setNumeroAlunniTerzo(Integer numeroAlunniTerzo) {
		this.numeroAlunniTerzo = numeroAlunniTerzo;
	}

	public Integer getNumeroAlunniQuarto() {
		return numeroAlunniQuarto;
	}

	public void setNumeroAlunniQuarto(Integer numeroAlunniQuarto) {
		this.numeroAlunniQuarto = numeroAlunniQuarto;
	}

	public Integer getNumeroAlunniQuinto() {
		return numeroAlunniQuinto;
	}

	public void setNumeroAlunniQuinto(Integer numeroAlunniQuinto) {
		this.numeroAlunniQuinto = numeroAlunniQuinto;
	}

	public String getPresenzaClassi() {
		return presenzaClassi;
	}

	public void setPresenzaClassi(String presenzaClassi) {
		this.presenzaClassi = presenzaClassi;
	}

	public Integer getNumeroAlunniSesto() {
		return numeroAlunniSesto;
	}

	public void setNumeroAlunniSesto(Integer numeroAlunniSesto) {
		this.numeroAlunniSesto = numeroAlunniSesto;
	}

	 

}
