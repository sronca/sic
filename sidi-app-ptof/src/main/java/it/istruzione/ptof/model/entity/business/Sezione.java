package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

	@Entity
	public class Sezione implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="CHIAVE")
		private String chiave;

		@Column(name="COD_SEZ_SOT_SEZ")
		private String codSezSotSez;

		@Column(name="DES_SEZ")
		private String desSez;

		@Column(name="COD_STA")
		private String codSta;
		
		@Column(name="PRG_SEZ_SOT_SEZ_PAD")
		private Long prgSezSotSezPad;		
		
		@Column(name="PRG_SEZ_SOT_SEZ")
		private Long prgSezSotSez;

		@Column(name="PRG_SEZ_SOT_SEZ_PLE")
		private Long prgSezSotSezPle;
		
		@Column(name="COD_SCU_UTE_PLE")
		private String codScuUtePle;
		
		
		public String getChiave() {
			return chiave;
		}

		public void setChiave(String chiave) {
			this.chiave = chiave;
		}

		public String getCodSezSotSez() {
			return codSezSotSez;
		}

		public void setCodSezSotSez(String codSezSotSez) {
			this.codSezSotSez = codSezSotSez;
		}

		public String getDesSez() {
			return desSez;
		}

		public void setDesSez(String desSez) {
			this.desSez = desSez;
		}

		public String getCodSta() {
			return codSta;
		}

		public void setCodSta(String codSta) {
			this.codSta = codSta;
		}

		public Long getPrgSezSotSezPad() {
			return prgSezSotSezPad;
		}

		public void setPrgSezSotSezPad(Long prgSezSotSezPad) {
			this.prgSezSotSezPad = prgSezSotSezPad;
		}

		public Long getPrgSezSotSez() {
			return prgSezSotSez;
		}

		public void setPrgSezSotSez(Long prgSezSotSez) {
			this.prgSezSotSez = prgSezSotSez;
		}

		public Long getPrgSezSotSezPle() {
			return prgSezSotSezPle;
		}

		public void setPrgSezSotSezPle(Long prgSezSotSezPle) {
			this.prgSezSotSezPle = prgSezSotSezPle;
		}

		public String getCodScuUtePle() {
			return codScuUtePle;
		}

		public void setCodScuUtePle(String codScuUtePle) {
			this.codScuUtePle = codScuUtePle;
		}	





	}
