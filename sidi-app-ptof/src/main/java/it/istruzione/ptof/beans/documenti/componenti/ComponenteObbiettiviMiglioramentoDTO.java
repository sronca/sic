package it.istruzione.ptof.beans.documenti.componenti;

import java.util.LinkedList;

import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.documenti.PrioritaTraguardiDTO;
import it.istruzione.ptof.beans.documenti.SintesiProcessoDTO;

public class ComponenteObbiettiviMiglioramentoDTO extends  ComponenteDTO {

	private LinkedList<PrioritaTraguardiDTO> prioritaTraguardi ;
	private String motivazioneSceltaPriorita;
	private LinkedList<SintesiProcessoDTO> sintesiProcesso;
	public LinkedList<PrioritaTraguardiDTO> getPrioritaTraguardi() {
		return prioritaTraguardi;
	}
	public void setPrioritaTraguardi(LinkedList<PrioritaTraguardiDTO> prioritaTraguardi) {
		this.prioritaTraguardi = prioritaTraguardi;
	}
	public String getMotivazioneSceltaPriorita() {
		return motivazioneSceltaPriorita;
	}
	public void setMotivazioneSceltaPriorita(String motivazioneSceltaPriorita) {
		this.motivazioneSceltaPriorita = motivazioneSceltaPriorita;
	}
	public LinkedList<SintesiProcessoDTO> getSintesiProcesso() {
		return sintesiProcesso;
	}
	public void setSintesiProcesso(LinkedList<SintesiProcessoDTO> sintesiProcesso) {
		this.sintesiProcesso = sintesiProcesso;
	}
	
	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.S_OBBIETTIVI_MIGLIORAMENTO;
	}
}
