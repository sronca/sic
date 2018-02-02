package it.istruzione.ptof.beans.documenti.componenti;

 
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;

import it.istruzione.ptof.beans.constant.TIPO_FILE_ACCETTATO;


public class ComponenteAllegatoDTO extends ComponenteDTO {
  
    /**
     * OBBLIGATORIO se null nessun controllo!!!
     */
    private TIPO_FILE_ACCETTATO tipoFileAccettato ;
 
	private FileDTO file;

	public FileDTO getFile() {
		return file;
	}

	public void setFile(FileDTO file) {
		this.file = file;
	}

	@Override
	public TIPO_COMPONENTE getTipoComponente() {
		// TODO Auto-generated method stub
		return TIPO_COMPONENTE.ALLEGATO;
	}

	public TIPO_FILE_ACCETTATO getTipoFileAccettato() {
		return tipoFileAccettato;
	}

	public void setTipoFileAccettato(TIPO_FILE_ACCETTATO tipoFileAccettato) {
		this.tipoFileAccettato = tipoFileAccettato;
	}

	 

 
	 
	
}
