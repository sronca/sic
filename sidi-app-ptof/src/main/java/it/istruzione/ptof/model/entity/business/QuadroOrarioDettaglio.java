package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuadroOrarioDettaglio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="DES_MAT_SCU")
	private String materia;
	
	@Column(name="NUN_SAN_PRI_ACO")
	private Integer numPrima;
	
	@Column(name="NUN_SAN_SEC_ACO")
	private Integer numSeconda;
	
	@Column(name="NUN_SAN_TER_ACO")
	private Integer numTerza;
	
	@Column(name="NUN_SAN_QUA_ACO")
	private Integer numQuarta;
	
	@Column(name="NUN_SAN_QUI_ACO")
	private Integer numQuinta;
	
	@Column(name="NUN_SAN_SES_ACO")
	private Integer numSesta;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Integer getNumPrima() {
		return numPrima;
	}

	public void setNumPrima(Integer numPrima) {
		this.numPrima = numPrima;
	}

	public Integer getNumSeconda() {
		return numSeconda;
	}

	public void setNumSeconda(Integer numSeconda) {
		this.numSeconda = numSeconda;
	}

	public Integer getNumTerza() {
		return numTerza;
	}

	public void setNumTerza(Integer numTerza) {
		this.numTerza = numTerza;
	}

	public Integer getNumQuarta() {
		return numQuarta;
	}

	public void setNumQuarta(Integer numQuarta) {
		this.numQuarta = numQuarta;
	}

	public Integer getNumQuinta() {
		return numQuinta;
	}

	public void setNumQuinta(Integer numQuinta) {
		this.numQuinta = numQuinta;
	}

	public Integer getNumSesta() {
		return numSesta;
	}

	public void setNumSesta(Integer numSesta) {
		this.numSesta = numSesta;
	}




}
