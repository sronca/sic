package it.istruzione.ptof.beans.documenti;

import java.math.BigDecimal;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;

/**
 * @author mcatanzaro
 * : Articolazione Classi  Infanzia ( MM )
 */
public class ArticolazioneClassiAADTO   extends PtofItemsDTO {
    
	private String key ;
	
	private String tipologiaPosti	, descrizione ;
	private BigDecimal sezioneOrarioNormale	, sezioneOrarioRidotto;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTipologiaPosti() {
		return tipologiaPosti;
	}
	public void setTipologiaPosti(String tipologiaPosti) {
		this.tipologiaPosti = tipologiaPosti;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public BigDecimal getSezioneOrarioNormale() {
		return sezioneOrarioNormale;
	}
	public void setSezioneOrarioNormale(BigDecimal sezioneOrarioNormale) {
		this.sezioneOrarioNormale = sezioneOrarioNormale;
	}
	public BigDecimal getSezioneOrarioRidotto() {
		return sezioneOrarioRidotto;
	}
	public void setSezioneOrarioRidotto(BigDecimal sezioneOrarioRidotto) {
		this.sezioneOrarioRidotto = sezioneOrarioRidotto;
	}
	 
    
}
