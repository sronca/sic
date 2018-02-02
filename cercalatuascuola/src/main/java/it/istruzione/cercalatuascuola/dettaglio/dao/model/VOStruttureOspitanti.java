package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOStruttureOspitanti implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long prgStrScu;
	
	private String desStrutturaOspitante;
	
	private Integer numPercorsiAttivati;
	
	private Integer numAlunniAnno3;
	
	private Integer numAlunniAnno4;
	
	private Integer numAlunniAnno5;

	public Long getPrgStrScu() {
		return prgStrScu;
	}

	public void setPrgStrScu(Long prgStrScu) {
		this.prgStrScu = prgStrScu;
	}

	public String getDesStrutturaOspitante() {
		return desStrutturaOspitante;
	}

	public void setDesStrutturaOspitante(String desStrutturaOspitante) {
		this.desStrutturaOspitante = desStrutturaOspitante;
	}

	public Integer getNumPercorsiAttivati() {
		return numPercorsiAttivati;
	}

	public void setNumPercorsiAttivati(Integer numPercorsiAttivati) {
		this.numPercorsiAttivati = numPercorsiAttivati;
	}

	public Integer getNumAlunniAnno3() {
		return numAlunniAnno3;
	}

	public void setNumAlunniAnno3(Integer numAlunniAnno3) {
		this.numAlunniAnno3 = numAlunniAnno3;
	}

	public Integer getNumAlunniAnno4() {
		return numAlunniAnno4;
	}

	public void setNumAlunniAnno4(Integer numAlunniAnno4) {
		this.numAlunniAnno4 = numAlunniAnno4;
	}

	public Integer getNumAlunniAnno5() {
		return numAlunniAnno5;
	}

	public void setNumAlunniAnno5(Integer numAlunniAnno5) {
		this.numAlunniAnno5 = numAlunniAnno5;
	}

}
