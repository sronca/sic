package it.istruzione.ptof.beans;

 

import it.istruzione.ptof.beans.constant.SortElencoReportCompletoHelper;

import java.io.Serializable;

import org.springframework.data.domain.Sort;

public class SortDTO implements Serializable {

	/**
	 * nome attributo della lista esempio :
	 * campo richiesta :  sezioneRichiestaDatiGeneraliDTO.utenteCompilatore.cognome
	 */
	private String nome ;

	private SortElencoReportCompletoHelper campo ;
	
	private Sort.Direction dir  ;

	public SortElencoReportCompletoHelper getCampo() {
		return campo;
	}

	public void setCampo(SortElencoReportCompletoHelper campo) {
		this.campo = campo;
	}

	public Sort.Direction getDir() {
		return dir;
	}

	public void setDir(Sort.Direction dir) {
		this.dir = dir;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
 

	 
}
