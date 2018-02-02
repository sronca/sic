package it.istruzione.ptof.beans.documenti.componenti;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;

/**
 * Rappresenta un componente generico di una sezione
 */
/**
 * @author mcatanzaro
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
abstract public class ComponenteDTO extends BaseDTO {

	/**
	 * stato del documento in cui si carica il componente
	 */
	protected TIPO_STATO_DOC statoDocumento ;
	/**
	 * tipo sezione in cui si carica il componente
	 */
	protected TIPO_SEZIONE tipoSezione ;
	/**
	 * stato sezione in cui si carica il componente
	 */
	protected TIPO_STATO_SEZIONE statoSezione ;
	
	/**
	 * ove applicabile esempio : se e' un allegato : occorre inserire almeno un
	 * allega se e' un capo testo : occorre inserie alemeno un carattere
	 * 
	 * deve essere sempre != null 
	 * 
	 */
	private Boolean isObbligatorio = new Boolean(false);

	private TIPO_COMPONENTE tipoComponente;

	/**
	 * chiave univoca del componete
	 */
	private String key;

	/**
	 * nome del componte  da mostrare a video.
	 * ES:
	 * - nel componete DATI_ISTITUTO_PRINCIPALE sara' ovviamente oggi 'Dati istituto principale' domani non si sa'
	 * - nel componete ALLEGATO puo' essere 'allegato documento vattelaapesca ' 
	 * - nel camponete inputtext la label del campo
	 * ...
	 */
	private String nome;

	/**
	 * descrizione del componete a seguire il nome ( ove applicabile )
	 */
	private String descrizione;

	public Boolean isObbligatorio() {
		return isObbligatorio;
	}

	public void setObbligatorio(Boolean isObbligatorio) {
		this.isObbligatorio = isObbligatorio;
	}

	abstract public TIPO_COMPONENTE getTipoComponente();

	/**
	 * @deprecated
	 * @param tipoComponente
	 */
	public void setTipoComponente(TIPO_COMPONENTE tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public TIPO_STATO_DOC getStatoDocumento() {
		return statoDocumento;
	}

	public void setStatoDocumento(TIPO_STATO_DOC statoDocumento) {
		this.statoDocumento = statoDocumento;
	}

	public TIPO_SEZIONE getTipoSezione() {
		return tipoSezione;
	}

	public void setTipoSezione(TIPO_SEZIONE tipoSezione) {
		this.tipoSezione = tipoSezione;
	}

	public TIPO_STATO_SEZIONE getStatoSezione() {
		return statoSezione;
	}

	public void setStatoSezione(TIPO_STATO_SEZIONE statoSezione) {
		this.statoSezione = statoSezione;
	}

	 

}
