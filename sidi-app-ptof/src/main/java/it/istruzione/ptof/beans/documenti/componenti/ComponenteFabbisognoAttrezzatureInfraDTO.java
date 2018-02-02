package it.istruzione.ptof.beans.documenti.componenti;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.FabbisognoAttrezzatureInfraDTO;
/*
 *  RF048– Fabbisogno di attrezzature e infrastrutture  
 */
public class ComponenteFabbisognoAttrezzatureInfraDTO extends  ComponenteBaseListDTO<FabbisognoAttrezzatureInfraDTO>   {
 	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138929378883832764L;
	
	private String codOrdScu;
	private String codTipFab;


	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_FABBISOGNO_ATTREZZATURE_INFRA;
	}


	public String getCodOrdScu() {
		return codOrdScu;
	}


	public void setCodOrdScu(String codOrdScu) {
		this.codOrdScu = codOrdScu;
	}


	public String getCodTipFab() {
		return codTipFab;
	}


	public void setCodTipFab(String codTipFab) {
		this.codTipFab = codTipFab;
	}
	
	

	
}
