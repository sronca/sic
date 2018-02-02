package it.istruzione.cercalatuascuola.dettaglio.dao.model;


public class VOAssenza implements java.io.Serializable{
	private String tipoPersonale = "";
	private String tipologiaAssenza = "";
	private Integer giorniAssenza;
	private Integer giorniAssenzaProv;
	private Integer giorniAssenzaNaz;

	public String getTipoPersonale() {
		return tipoPersonale;
	}

	public void setTipoPersonale(String tipoPersonale) {
		this.tipoPersonale = tipoPersonale;
	}

	public String getTipologiaAssenza() {
		return tipologiaAssenza;
	}

	public void setTipologiaAssenza(String tipologiaAssenza) {
		this.tipologiaAssenza = tipologiaAssenza;
	}

	public Integer getGiorniAssenza() {
		return giorniAssenza;
	}

	public void setGiorniAssenza(Integer giorniAssenza) {
		this.giorniAssenza = giorniAssenza;
	}

	public Integer getGiorniAssenzaProv() {
		return giorniAssenzaProv;
	}

	public void setGiorniAssenzaProv(Integer giorniAssenzaProv) {
		this.giorniAssenzaProv = giorniAssenzaProv;
	}

	public Integer getGiorniAssenzaNaz() {
		return giorniAssenzaNaz;
	}

	public void setGiorniAssenzaNaz(Integer giorniAssenzaNaz) {
		this.giorniAssenzaNaz = giorniAssenzaNaz;
	}
}
