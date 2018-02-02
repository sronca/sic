package it.istruzione.ptof.catalogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.istruzione.ptof.beans.DatiDecretoDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;

public class SummaryImportDecreti {
	private List<DatiDecretoDTO> listDatiDecretoDTO = new ArrayList<>();
	private ValidationErrorDTO validationErrorDTO;

	private Integer rowWorking;
	
	public List<DatiDecretoDTO> getListDatiDecretoDTO() {
		return listDatiDecretoDTO;
	}

	public void setListDatiDecretoDTO(List<DatiDecretoDTO> listDatiDecretoDTO) {
		this.listDatiDecretoDTO = listDatiDecretoDTO;
	}

	public ValidationErrorDTO getValidationErrorDTO() {
		return validationErrorDTO;
	}

	public void setValidationErrorDTO(ValidationErrorDTO validationErrorDTO) {
		this.validationErrorDTO = validationErrorDTO;
	}

	public Integer getRowWorking() {
		return rowWorking;
	}

	public void setRowWorking(Integer rowWorking) {
		this.rowWorking = rowWorking;
	}
}
