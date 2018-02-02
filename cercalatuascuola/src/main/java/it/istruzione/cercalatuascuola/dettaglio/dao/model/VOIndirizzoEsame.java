package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;
import java.util.List;

public class VOIndirizzoEsame implements Serializable {
	private static final long serialVersionUID = 3135176796051513704L;

	private VOTipologia tipologia;
	private List<VOCaratteristica> listaCaratteristica;

	public VOTipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(VOTipologia tipologia) {
		this.tipologia = tipologia;
	}

	public List<VOCaratteristica> getListaCaratteristica() {
		return listaCaratteristica;
	}

	public void setListaCaratteristica(List<VOCaratteristica> listaCaratteristica) {
		this.listaCaratteristica = listaCaratteristica;
	}

}
