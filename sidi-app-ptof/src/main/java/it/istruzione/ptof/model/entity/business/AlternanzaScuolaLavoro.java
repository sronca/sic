package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

	@Entity
	public class AlternanzaScuolaLavoro implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="ID")
		private String id;
		
		@Column(name="DES_PER_SCU")
		private String percorso;

		@Column(name="DES_STR_SCU")
		private String struttura;

		@Column(name="DES_AZI")
		private String azienda;

		@Column(name="NUM_ALU_PRI_ANN")
		private Integer alunniIAnno;
		
		@Column(name="NUM_ALU_SEC_ANN")
		private Integer alunniIIAnno;
		
		@Column(name="NUM_ALU_TER_ANN")
		private Integer alunniIIIAnno;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPercorso() {
			return percorso;
		}

		public void setPercorso(String percorso) {
			this.percorso = percorso;
		}

		public String getStruttura() {
			return struttura;
		}

		public void setStruttura(String struttura) {
			this.struttura = struttura;
		}

		public String getAzienda() {
			return azienda;
		}

		public void setAzienda(String azienda) {
			this.azienda = azienda;
		}

		public Integer getAlunniIAnno() {
			return alunniIAnno;
		}

		public void setAlunniIAnno(Integer alunniIAnno) {
			this.alunniIAnno = alunniIAnno;
		}

		public Integer getAlunniIIAnno() {
			return alunniIIAnno;
		}

		public void setAlunniIIAnno(Integer alunniIIAnno) {
			this.alunniIIAnno = alunniIIAnno;
		}

		public Integer getAlunniIIIAnno() {
			return alunniIIIAnno;
		}

		public void setAlunniIIIAnno(Integer alunniIIIAnno) {
			this.alunniIIIAnno = alunniIIIAnno;
		}
		
		
		






	}
