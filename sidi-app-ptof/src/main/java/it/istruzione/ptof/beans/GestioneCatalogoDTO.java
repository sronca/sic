package it.istruzione.ptof.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.istruzione.ptof.helper.CustomDateDeserializer;
import it.istruzione.ptof.helper.CustomDateSerializer;

public class GestioneCatalogoDTO extends BaseDTO {
	private static final long serialVersionUID = 506928937521881021L;

	private Long progresivoGestioneCatalogoDocumento;
	private String descrizioneDocumento;
	private Date dataInzioValidita;
	private Date dataFineValidita;
	private BigDecimal periodoTriennioRiferimento;
	private String periodoTriennioRiferimentoAsString;
	private BigDecimal numeroVersioneDocumento;
	
	public GestioneCatalogoDTO(){};
	
	public GestioneCatalogoDTO(Long progresivoGestioneCatalogoDocumento, String descrizioneDocumento,
			BigDecimal periodoTriennioRiferimento, BigDecimal numeroVersioneDocumento) {
		super();
		this.progresivoGestioneCatalogoDocumento = progresivoGestioneCatalogoDocumento;
		this.descrizioneDocumento = descrizioneDocumento;
		this.periodoTriennioRiferimento = periodoTriennioRiferimento;
		this.numeroVersioneDocumento = numeroVersioneDocumento;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataInzioValidita() {
		return dataInzioValidita;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataInzioValidita(Date dataInzioValidita) {
		this.dataInzioValidita = dataInzioValidita;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Long getProgresivoGestioneCatalogoDocumento() {
		return progresivoGestioneCatalogoDocumento;
	}

	public void setProgresivoGestioneCatalogoDocumento(Long progresivoGestioneCatalogoDocumento) {
		this.progresivoGestioneCatalogoDocumento = progresivoGestioneCatalogoDocumento;
	}

	public String getDescrizioneDocumento() {
		return descrizioneDocumento;
	}

	public void setDescrizioneDocumento(String descrizioneDocumento) {
		this.descrizioneDocumento = descrizioneDocumento;
	}
	
	
	public BigDecimal getPeriodoTriennioRiferimento() {
		return periodoTriennioRiferimento;
	}

	public void setPeriodoTriennioRiferimento(BigDecimal periodoTriennioRiferimento) {
		this.periodoTriennioRiferimento = periodoTriennioRiferimento;
	}

	public BigDecimal getNumeroVersioneDocumento() {
		return numeroVersioneDocumento;
	}

	public void setNumeroVersioneDocumento(BigDecimal numeroVersioneDocumento) {
		this.numeroVersioneDocumento = numeroVersioneDocumento;
	}
	
	public String getPeriodoTriennioRiferimentoAsString() {
		String out = "";
		if (this.periodoTriennioRiferimento != null){
			out = periodoTriennioRiferimento.toString().substring(0,4) + "/" + periodoTriennioRiferimento.toString().substring(4);
		}
		return out;
	}

	public void setPeriodoTriennioRiferimentoAsString(
			String periodoTriennioRiferimentoAsString) {
		this.periodoTriennioRiferimentoAsString = periodoTriennioRiferimentoAsString;
	}
	
	
}
