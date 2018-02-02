package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

	@Entity
	public class CountEntity implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="ID")
		private String id;
		
		@Column(name="NUM_POS_PRI")
		private Long numPosPri;

		@Column(name="NUM_POS_SEC")
		private Long numPosSec;

		@Column(name="NUM_POS_TER")
		private Long numPosTer;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Long getNumPosPri() {
			return numPosPri;
		}

		public void setNumPosPri(Long numPosPri) {
			this.numPosPri = numPosPri;
		}

		public Long getNumPosSec() {
			return numPosSec;
		}

		public void setNumPosSec(Long numPosSec) {
			this.numPosSec = numPosSec;
		}

		public Long getNumPosTer() {
			return numPosTer;
		}

		public void setNumPosTer(Long numPosTer) {
			this.numPosTer = numPosTer;
		}
		
		



	}
