package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;

/**
 * @author mcatanzaro
 * Sottosezione della Sezione 13 - RF048– Fabbisogno di attrezzature e infrastrutture
 */
public class ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO extends ComponenteDTO {

	
	private String  motivazione , finalita , beneficiAttesi;

	
	
	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_FABBISOGNO_ATTREZZATURE_INFRA_ALTRE_INFO;
	}



	public String getMotivazione() {
		return motivazione;
	}



	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}



	public String getFinalita() {
		return finalita;
	}



	public void setFinalita(String finalita) {
		this.finalita = finalita;
	}



	public String getBeneficiAttesi() {
		return beneficiAttesi;
	}



	public void setBeneficiAttesi(String beneficiAttesi) {
		this.beneficiAttesi = beneficiAttesi;
	}

}
