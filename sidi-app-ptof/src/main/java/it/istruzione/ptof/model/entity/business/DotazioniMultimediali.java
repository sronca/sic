package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

	@Entity
	public class DotazioniMultimediali implements Serializable {
		private static final long serialVersionUID = 1L;

		@EmbeddedId
		private DotazioniMultimedialiPK id;
		
		@Column(name="COMPUTER")
		private long computer;
		
		@Column(name="DISPOSITIVI_MOBILI")
		private long dispositiviMobili;
		
		@Column(name="LIM")
		private long lim;
		
		@Column(name="PROIETTORI_INTERATTIVI")
		private long proiettoriInterattivi;

		public DotazioniMultimedialiPK getId() {
			return id;
		}

		public void setId(DotazioniMultimedialiPK id) {
			this.id = id;
		}

		public long getComputer() {
			return computer;
		}

		public void setComputer(long computer) {
			this.computer = computer;
		}

		public long getDispositiviMobili() {
			return dispositiviMobili;
		}

		public void setDispositiviMobili(long dispositiviMobili) {
			this.dispositiviMobili = dispositiviMobili;
		}

		public long getLim() {
			return lim;
		}

		public void setLim(long lim) {
			this.lim = lim;
		}

		public long getProiettoriInterattivi() {
			return proiettoriInterattivi;
		}

		public void setProiettoriInterattivi(long proiettoriInterattivi) {
			this.proiettoriInterattivi = proiettoriInterattivi;
		}
		
		



		

	}
