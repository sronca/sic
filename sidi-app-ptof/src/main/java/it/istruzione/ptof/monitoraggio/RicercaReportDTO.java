package it.istruzione.ptof.monitoraggio;

import it.istruzione.ptof.beans.PageDTO;
import it.istruzione.ptof.beans.ReportCompletoFiltroDTO;

public class RicercaReportDTO extends PageDTO { 
	 
	private static final long serialVersionUID = 1L;
	
	private ReportCompletoFiltroDTO reportCompleto ;

	public ReportCompletoFiltroDTO getReportCompleto() {
		return reportCompleto;
	}

	public void setReportCompleto(ReportCompletoFiltroDTO reportCompleto) {
		this.reportCompleto = reportCompleto;
	}
 
	
}
