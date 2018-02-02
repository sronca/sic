package it.istruzione.ptof.beans.documenti.componenti;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPersonaleAmmTecAusiDTO;

/**
 * @author sarellano
 * RF045–  Fabbisogno dei posti di personale amministrativo, tecnico e ausiliare. 
 * 
 */
public class ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO extends  ComponenteBaseListDTO<FabbisognoPostiPersonaleAmmTecAusiDTO>   {
	private static final long serialVersionUID = -4687257537266577625L;

	
	private Integer versione;
	
	private String labelAnno0;
	private String labelAnno1;
	private String labelAnno2;
	
	
 
	public Integer getVersione() {
		return versione;
	}



	public void setVersione(Integer versione) {
		this.versione = versione;
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



	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI;
	}
}
