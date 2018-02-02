package it.istruzione.ptof.beans.documenti.componenti;
  
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;

/**
 * @author sarellano
 * RF051– Dati Finali Scuola
 * 
 * DATI FINALI SCUOLA
 */
public class ComponenteDatiFinaliScuolaDTO extends  ComponenteDTO {
	/**
	 * Modalità di verifica
	 */
	private Date dataRatificaAttoIndirizzoDirigente ;
	private Date dataPredisposizionePTOFCollegioDocenti ;
	private Date dataApprovazioneConsiglioDocenti ;
	private Date dataInvioUSR ;
	

	/**
	 * numero del documento: il numero di revisione è 0 se si tratta della prima
	 * redazione, 1 o 2 per le revisione degli anni successivi
	 */
	private String versione;
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataRatificaAttoIndirizzoDirigente() {
		return dataRatificaAttoIndirizzoDirigente;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataRatificaAttoIndirizzoDirigente(
			Date dataRatificaAttoIndirizzoDirigente) {
		this.dataRatificaAttoIndirizzoDirigente = dataRatificaAttoIndirizzoDirigente;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataPredisposizionePTOFCollegioDocenti() {
		return dataPredisposizionePTOFCollegioDocenti;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataPredisposizionePTOFCollegioDocenti(
			Date dataPredisposizionePTOFCollegioDocenti) {
		this.dataPredisposizionePTOFCollegioDocenti = dataPredisposizionePTOFCollegioDocenti;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataApprovazioneConsiglioDocenti() {
		return dataApprovazioneConsiglioDocenti;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataApprovazioneConsiglioDocenti(
			Date dataApprovazioneConsiglioDocenti) {
		this.dataApprovazioneConsiglioDocenti = dataApprovazioneConsiglioDocenti;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataInvioUSR() {
		return dataInvioUSR;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataInvioUSR(Date dataInvioUSR) {
		this.dataInvioUSR = dataInvioUSR;
	}

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_DATI_FINALI_SCUOLA;
	}

	public String getVersione() {
		return versione;
	}

	public void setVersione(String versione) {
		this.versione = versione;
	}
}
