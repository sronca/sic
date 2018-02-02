package it.istruzione.ptof.beans;

import java.io.Serializable;
import java.util.LinkedList;

public class PageDTO implements Serializable {

	/**
	 * inizialmente e' null 
	 */
	private Integer paginaCorrente ;
	
	/**
	 * inzialemente null
	 */
	private LinkedList<SortDTO> sort;
	 
	 
	private Integer numeroRighePerPagina = new Integer(2) ;
	
	public Integer getPaginaCorrente() {
		return paginaCorrente;
	}
	 
	public void setPaginaCorrente(Integer paginaCorrente) {
		this.paginaCorrente = paginaCorrente;
	}

	public LinkedList<SortDTO> getSort() {
		return sort;
	}

	public void setSort(LinkedList<SortDTO> sort) {
		this.sort = sort;
	}

	public Integer getNumeroRighePerPagina() {
		return numeroRighePerPagina;
	}

	public void setNumeroRighePerPagina(Integer numeroRighePerPagina) {
		this.numeroRighePerPagina = numeroRighePerPagina;
	}
 
}