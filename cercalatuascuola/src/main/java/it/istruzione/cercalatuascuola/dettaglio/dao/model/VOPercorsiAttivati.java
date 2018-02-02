package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOPercorsiAttivati implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long prgPerScu;
	
	private String desPercorso;
	
	private Integer numOreAula;
	
	private Integer numOreStruttura;
	
	private Integer numAlunniAnno3;
	
	private Integer numAlunniAnno4;
	
	private Integer numAlunniAnno5;
	
	public Long getPrgPerScu() {
		return prgPerScu;
	}

	public void setPrgPerScu(Long prgPerScu) {
		this.prgPerScu = prgPerScu;
	}

	public String getDesPercorso() {
		return desPercorso;
	}

	public void setDesPercorso(String desPercorso) {
		this.desPercorso = desPercorso;
	}

	public Integer getNumOreAula() {
		return numOreAula;
	}

	public void setNumOreAula(Integer numOreAula) {
		this.numOreAula = numOreAula;
	}

	public Integer getNumOreStruttura() {
		return numOreStruttura;
	}

	public void setNumOreStruttura(Integer numOreStruttura) {
		this.numOreStruttura = numOreStruttura;
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
