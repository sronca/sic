package it.istruzione.cercalatuascuola.ricerca.dao.model;

public class VOSuccursale
{
	private String codice = "";
	private String codiceMeccanografico = "";
	private String annoRiferimento = "";
	private String desAnnScoRil = "";			
	private String progressivo = "";
	private String descrizione = "";
	private String latitudine = "";
	private String longitudine = "";
		
	public String getCodice()
	{
		return codice;
	}
	public void setCodice(String codice)
	{
		this.codice = codice;
	}
	public String getCodiceMeccanografico()
	{
		return codiceMeccanografico;
	}
	public void setCodiceMeccanografico(String codiceMeccanografico)
	{
		this.codiceMeccanografico = codiceMeccanografico;
	}
	public String getAnnoRiferimento()
	{
		return annoRiferimento;
	}
	public void setAnnoRiferimento(String annoRiferimento)
	{
		this.annoRiferimento = annoRiferimento;
	}
	public String getProgressivo()
	{
		return progressivo;
	}
	public void setProgressivo(String progressivo)
	{
		this.progressivo = progressivo;
	}
	public String getDescrizione()
	{
		return descrizione;
	}
	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}
	public String getLatitudine()
	{
		return latitudine;
	}
	public void setLatitudine(String latitudine)
	{
		this.latitudine = latitudine;
	}
	public String getLongitudine()
	{
		return longitudine;
	}
	public void setLongitudine(String longitudine)
	{
		this.longitudine = longitudine;
	}
	
	public void setDesAnnScoRil(String desAnnScoRil) {
		this.desAnnScoRil = desAnnScoRil;
	}
	
	public String getDesAnnScoRil() {
		return desAnnScoRil;
	}		
}
