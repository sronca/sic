package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;
import java.util.List;

public class VOCaratteristica implements Serializable {
	private static final long serialVersionUID = -5704865120042612308L;

	private String sottotitolo;
	private List<String> listaCaratteristica;

	public String getSottotitolo() {
		return sottotitolo;
	}

	public void setSottotitolo(String sottotitolo) {
		this.sottotitolo = sottotitolo;
	}

	public List<String> getListaCaratteristica() {
		return listaCaratteristica;
	}

	public void setListaCaratteristica(List<String> listaCaratteristica) {
		this.listaCaratteristica = listaCaratteristica;
	}

}
