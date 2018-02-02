package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.sql.Blob;

public class VOGraficoInvalsi extends VOInvalsiBase  implements Comparable<VOGraficoInvalsi>{

	private String nomeFile;
	private Blob foto;

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Blob getFoto() {
		return foto;
	}

	public void setFoto(Blob foto) {
		this.foto = foto;
	}

	public int compareTo(VOGraficoInvalsi obj) {
		int ret = this.getMateriaDesc().compareToIgnoreCase(obj.getMateriaDesc());
		return ret;
	}
}
