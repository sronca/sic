package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOAlunniImmAD implements java.io.Serializable {

	private String idAreaDidattica;
	private String desAreaDidattica;
	private String percentStudenti;
		
	public String getPercentStudenti() {
		return percentStudenti;
	}
	
	public void setPercentStudenti(String percentStudenti) {
		this.percentStudenti = percentStudenti;
	}

	public String getIdAreaDidattica() {
		return idAreaDidattica;
	}

	public void setIdAreaDidattica(String idAreaDidattica) {
		this.idAreaDidattica = idAreaDidattica;
	}

	public String getDesAreaDidattica() {
		return desAreaDidattica;
	}

	public void setDesAreaDidattica(String desAreaDidattica) {
		this.desAreaDidattica = desAreaDidattica;
	}
}