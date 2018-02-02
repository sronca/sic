package it.istruzione.ptof.controller.monitoraggio;

public class MonitoraggioDocumentoForm {
	private Integer progresivoGestioneCatalogoDocumento;
	private Integer periodoTriennioRiferimento;
	private Short numeroVersioneDocumento;
	
	public Integer getProgresivoGestioneCatalogoDocumento() {
		return progresivoGestioneCatalogoDocumento;
	}
	public void setProgresivoGestioneCatalogoDocumento(Integer progresivoGestioneCatalogoDocumento) {
		this.progresivoGestioneCatalogoDocumento = progresivoGestioneCatalogoDocumento;
	}
	public Integer getPeriodoTriennioRiferimento() {
		return periodoTriennioRiferimento;
	}
	public void setPeriodoTriennioRiferimento(Integer periodoTriennioRiferimento) {
		this.periodoTriennioRiferimento = periodoTriennioRiferimento;
	}
	public Short getNumeroVersioneDocumento() {
		return numeroVersioneDocumento;
	}
	public void setNumeroVersioneDocumento(Short numeroVersioneDocumento) {
		this.numeroVersioneDocumento = numeroVersioneDocumento;
	}
}
