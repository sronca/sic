package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuadroOrario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PRG_PIA_STU")
	private Long prgPiaStu;
	
	@Column(name="INDIRIZZO")
	private String indirizzo;
	
	@Column(name="ANNO")
	private String anno;

	@Column(name="PIANO_STUDIO")
	private String pianoStudio;

	public Long getPrgPiaStu() {
		return prgPiaStu;
	}

	public void setPrgPiaStu(Long prgPiaStu) {
		this.prgPiaStu = prgPiaStu;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getPianoStudio() {
		return pianoStudio;
	}

	public void setPianoStudio(String pianoStudio) {
		this.pianoStudio = pianoStudio;
	}


}
