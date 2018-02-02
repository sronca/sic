package it.istruzione.poninchiaro.model;

public class VODistribuzionePartecipanti {
	
	private String descrizioneArea;
	private long istituti;
	private long partecipanti;
	
	public String getDescrizioneArea() {
		return descrizioneArea;
	}
	public void setDescrizioneArea(String descrizioneArea) {
		this.descrizioneArea = descrizioneArea;
	}
	public long getIstituti() {
		return istituti;
	}
	public void setIstituti(long istituti) {
		this.istituti = istituti;
	}
	public long getPartecipanti() {
		return partecipanti;
	}
	public void setPartecipanti(long partecipanti) {
		this.partecipanti = partecipanti;
	}

	public long getNonPartecipanti() {
		return istituti - partecipanti;
	}
	
	

}
