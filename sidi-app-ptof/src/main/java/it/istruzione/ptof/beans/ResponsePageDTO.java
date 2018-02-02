package it.istruzione.ptof.beans;

import java.util.LinkedList;
 

 
public class ResponsePageDTO<E> extends PageDTO  {
 
	private Integer pagineTotali , righeTotali ;
	
	private ResponseDTO<LinkedList<E>> items ;
 
	public ResponseDTO<LinkedList<E>> getItems() {
		return items;
	}

	public void setItems(ResponseDTO<LinkedList<E>> items) {
		this.items = items;
	}

	public Integer getPagineTotali() {
		return pagineTotali;
	}

	public void setPagineTotali(Integer pagineTotali) {
		this.pagineTotali = pagineTotali;
	}

	public Integer getRigheTotali() {
		return righeTotali;
	}

	public void setRigheTotali(Integer righeTotali) {
		this.righeTotali = righeTotali;
	}
	

	
}