package it.istruzione.ptof.beans.documenti;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.PtofItemsDTO;
import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;

public class ProgrammazioneFormDTO   extends PtofItemsDTO {
	
	private String key;
	private BeanValueDTO ambitoFormativo;
	private String denominazionePercorsoFormativo, obiettivi, contenuti;
	private BigDecimal tempiOreComplessive;
	private Date dataInizio, dataFine;
	private String areaTematicaPNSD;

	/**
	 * Percorso formativo organizzato tra più scuole
	 */
	private BeanValueDTO percorsoFormativo;
	
	private BeanValueDTO collaborazioneReteScolastic, condiviso;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public BeanValueDTO getAmbitoFormativo() {
		return ambitoFormativo;
	}
	public void setAmbitoFormativo(BeanValueDTO ambitoFormativo) {
		this.ambitoFormativo = ambitoFormativo;
	}
	public String getDenominazionePercorsoFormativo() {
		return denominazionePercorsoFormativo;
	}
	public void setDenominazionePercorsoFormativo(String denominazionePercorsoFormativo) {
		this.denominazionePercorsoFormativo = denominazionePercorsoFormativo;
	}
	public String getObiettivi() {
		return obiettivi;
	}
	public void setObiettivi(String obiettivi) {
		this.obiettivi = obiettivi;
	}
	public String getContenuti() {
		return contenuti;
	}
	public void setContenuti(String contenuti) {
		this.contenuti = contenuti;
	}

	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataInizio() {
		return dataInizio;
	}
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataFine() {
		return dataFine;
	}
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public String getAreaTematicaPNSD() {
		return areaTematicaPNSD;
	}
	public void setAreaTematicaPNSD(String areaTematicaPNSD) {
		this.areaTematicaPNSD = areaTematicaPNSD;
	}
	public BeanValueDTO getPercorsoFormativo() {
		return percorsoFormativo;
	}
	public void setPercorsoFormativo(BeanValueDTO percorsoFormativo) {
		this.percorsoFormativo = percorsoFormativo;
	}
	public BeanValueDTO getCollaborazioneReteScolastic() {
		return collaborazioneReteScolastic;
	}
	public void setCollaborazioneReteScolastic(BeanValueDTO collaborazioneReteScolastic) {
		this.collaborazioneReteScolastic = collaborazioneReteScolastic;
	}
	public BeanValueDTO getCondiviso() {
		return condiviso;
	}
	public void setCondiviso(BeanValueDTO condiviso) {
		this.condiviso = condiviso;
	}
	public BigDecimal getTempiOreComplessive() {
		return tempiOreComplessive;
	}
	public void setTempiOreComplessive(BigDecimal tempiOreComplessive) {
		this.tempiOreComplessive = tempiOreComplessive;
	}

}
