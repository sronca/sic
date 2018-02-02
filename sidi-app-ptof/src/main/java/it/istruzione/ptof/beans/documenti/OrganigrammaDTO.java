package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

public class OrganigrammaDTO extends PtofItemsDTO {
	
	private String key;

	private BeanValueDTO ruolo;
	private String nome,   responsabilita, noteResponsabilita;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public BeanValueDTO getRuolo() {
		return ruolo;
	}

	public void setRuolo(BeanValueDTO ruolo) {
		this.ruolo = ruolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

 

	public String getResponsabilita() {
		return responsabilita;
	}

	public void setResponsabilita(String responsabilita) {
		this.responsabilita = responsabilita;
	}

	public String getNoteResponsabilita() {
		return noteResponsabilita;
	}

	public void setNoteResponsabilita(String noteResponsabilita) {
		this.noteResponsabilita = noteResponsabilita;
	}


}
