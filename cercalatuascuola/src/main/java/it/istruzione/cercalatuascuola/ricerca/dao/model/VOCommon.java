package it.istruzione.cercalatuascuola.ricerca.dao.model;

public class VOCommon implements Comparable<VOCommon>
{
	private String id = "";
	private String codice = "";
	private String descrizione = "";
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodice()
	{
		return codice;
	}

	public void setCodice(String codice)
	{
		this.codice = codice;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}
	
	public int compareTo(VOCommon vo) {
        return this.codice.compareTo(vo.getCodice());
    }
}