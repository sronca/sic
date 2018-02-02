package it.istruzione.ptof.beans.documenti;



import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofTableItemActionFilterDTO;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;
import it.istruzione.ptof.helper.CommonsUtility;

/**
 * Rappresenta i dati comuni di una sezione
 */
public  class SezioneBaseDTO extends BaseDTO implements PtofTableItemActionFilterDTO {

	private String key;
	
	/**
	 * formato atteso ( #. )
	 */
	private String codice;
	
	/**
	 * esempio : copertina e indice 
	 */
	private String nome;
	

	/**
	 * testo fisso descrittivo della sezione 
	 * use:  il testo verra' risportato solo nella sezione di intestazione ( patre )   nel pdf
	 */
	private String testoFissoIntestazioneSezione;

	
	/**
	 * il nome dice tutto
	 */
	private TIPO_STATO_SEZIONE statoSezione;
	
	
	/**
	 * stato del documento 
	 */
	private TIPO_STATO_DOC statoDocumento ;
	
	private Boolean disabled = Boolean.FALSE;
	private String disabledMessage;
	
	public TIPO_STATO_DOC getStatoDocumento() {
		return statoDocumento;
	}

	public void setStatoDocumento(TIPO_STATO_DOC statoDocumento) {
		this.statoDocumento = statoDocumento;
	}
	
	
	/**
	 * identifica il tipo sezione : sezione 1 ( indice ) , sezione 2 ( Finalità e Iter ). 
	 */
	private TIPO_SEZIONE tipoSezione;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TIPO_STATO_SEZIONE getStatoSezione() {
		return statoSezione;
	}

	public void setStatoSezione(TIPO_STATO_SEZIONE statoSezione) {
		this.statoSezione = statoSezione;
	}

	public TIPO_SEZIONE getTipoSezione() {
		return tipoSezione;
	}

	public void setTipoSezione(TIPO_SEZIONE tipoSezione) {
		this.tipoSezione = tipoSezione;
	}

	@Override
	public boolean isModificabile() {
		return CommonsUtility.sezioneIsModificabile(tipoSezione,statoDocumento );
	}
  
	@Override
	public void setModificabile(boolean modificabile) {
	}

	@Override
	public boolean isCancellabile() {
		return CommonsUtility.sezioneIsCancellabile(tipoSezione,statoDocumento,statoSezione);
	}

	
	@Override
	public void setCancellabile(boolean modificabile) { }

	@Override
	public boolean isVisualizzabile() {
		boolean out = true;
		if ( tipoSezione==null){
			// caso eccezzionale in cui le sezioni che hanno dei figli non hanno un tipo sezione
			return out;
		}
		// la sezione 35 sara in visualizzazione fino a che il doc sar diverso dalla compilazione
		if (  tipoSezione.compareTo(TIPO_SEZIONE.SEZIONE_33_PIANO_NAZIONALE_SCUOLA_DIGITALE ) == 0
			  && statoDocumento.compareTo(TIPO_STATO_DOC.IN_COMPILAZIONE ) != 0 ) {
			out = false;
		} 
		return out;
	}

	@Override
	public void setVisualizzabile(boolean modificabile) { }

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getDisabledMessage() {
		return disabledMessage;
	}

	public void setDisabledMessage(String disabledMessage) {
		this.disabledMessage = disabledMessage;
	}



	public String getTestoFissoIntestazioneSezione() {
		return testoFissoIntestazioneSezione;
	}

	public void setTestoFissoIntestazioneSezione(String testoFissoIntestazioneSezione) {
		this.testoFissoIntestazioneSezione = testoFissoIntestazioneSezione;
	}

	 
}
