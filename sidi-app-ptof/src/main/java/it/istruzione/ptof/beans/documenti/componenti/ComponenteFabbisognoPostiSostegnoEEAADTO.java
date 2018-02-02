package it.istruzione.ptof.beans.documenti.componenti;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiSostegnoEEAADTO;
 
/*
  Sezione 13 -  RF043– Fabbisogno di posti di sostegno 
  per infanzia e primaria
 */
public class ComponenteFabbisognoPostiSostegnoEEAADTO extends  ComponenteBaseListDTO<FabbisognoPostiSostegnoEEAADTO>   {
 	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138929378883832764L;
	
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
		return TIPO_COMPONENTE.S_FABBISOGNO_POSTI_SOSTEGNO_AA_EE_MMI;
	}

	
}
