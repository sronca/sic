package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public class VOTracciaInvalsi extends VOInvalsiBase implements Comparable<VOTracciaInvalsi>{
	
	
	private Integer livello;
	
	private Integer tipo;
	
	private Float media;
	
	private Float diff;
	
	private String regioneDesc;
	
	private Float regioneMedia;
	
	private Integer regioneIndicatore;
	
	private String zonaDesc;
	
	private Float zonaMedia;
	
	private Integer zonaIndicatore;
	
	private String nazioneDesc;
	
	private Float nazioneMedia;
	
	private Integer nazioneIndicatore;
	
	public Integer getLivello() {
		return livello;
	}

	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Float getMedia() {
		return media;
	}

	public void setMedia(Float media) {
		this.media = media;
	}

	public Float getDiff() {
		return diff;
	}

	public void setDiff(Float diff) {
		this.diff = diff;
	}

	public String getRegioneDesc() {
		return regioneDesc;
	}

	public void setRegioneDesc(String regioneDesc) {
		this.regioneDesc = regioneDesc;
	}

	public Float getRegioneMedia() {
		return regioneMedia;
	}

	public void setRegioneMedia(Float regioneMedia) {
		this.regioneMedia = regioneMedia;
	}

	public Integer getRegioneIndicatore() {
		return regioneIndicatore;
	}

	public void setRegioneIndicatore(Integer regioneIndicatore) {
		this.regioneIndicatore = regioneIndicatore;
	}

	public String getZonaDesc() {
		return zonaDesc;
	}

	public void setZonaDesc(String zonaDesc) {
		this.zonaDesc = zonaDesc;
	}

	public Float getZonaMedia() {
		return zonaMedia;
	}

	public void setZonaMedia(Float zonaMedia) {
		this.zonaMedia = zonaMedia;
	}

	public Integer getZonaIndicatore() {
		return zonaIndicatore;
	}

	public void setZonaIndicatore(Integer zonaIndicatore) {
		this.zonaIndicatore = zonaIndicatore;
	}

	public String getNazioneDesc() {
		return nazioneDesc;
	}

	public void setNazioneDesc(String nazioneDesc) {
		this.nazioneDesc = nazioneDesc;
	}

	public Float getNazioneMedia() {
		return nazioneMedia;
	}

	public void setNazioneMedia(Float nazioneMedia) {
		this.nazioneMedia = nazioneMedia;
	}

	public Integer getNazioneIndicatore() {
		return nazioneIndicatore;
	}

	public void setNazioneIndicatore(Integer nazioneIndicatore) {
		this.nazioneIndicatore = nazioneIndicatore;
	}

	@Override
	public int compareTo(VOTracciaInvalsi $obj) {
		int ret = this.getMateriaDesc().compareToIgnoreCase( $obj.getMateriaDesc() );
		if(ret == 0){
			 ret = this.getTipo().compareTo( $obj.getTipo() );
			 if(ret == 0){
				 ret = this.getLivello().compareTo( $obj.getLivello() );
			 }
		}
		return ret;
	}

}
