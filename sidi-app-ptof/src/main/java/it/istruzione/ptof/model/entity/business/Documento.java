package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

	@Entity
	public class Documento implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="CHIAVE")
		private String key;
		
		@Column(name="PRG_GES_CAT_DOC")
		private Long prgGesCatDoc;

		@Column(name="COD_SCU_UTE")
		private String codScuUte;
		
		@Column(name="PER_ANN_SCO")
		private Long perAnnSco;
		
		@Temporal(TemporalType.DATE)
		@Column(name="DAT_FIN_VAL")
		private Date datFinVal;

		@Temporal(TemporalType.DATE)
		@Column(name="DAT_INI_VAL")
		private Date datIniVal;

		@Column(name="DES_DOC")
		private String desDoc;

		@Column(name="NUM_VER_DOC")
		private BigDecimal numVerDoc;

		@Column(name="PER_TRI_RIF")
		private BigDecimal perTriRif;
		
		@Column(name="COD_STA")
		private String codSta;
		
		@Column(name="DES_STA")
		private String desSta;

		public Documento() {
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Long getPrgGesCatDoc() {
			return prgGesCatDoc;
		}

		public void setPrgGesCatDoc(Long prgGesCatDoc) {
			this.prgGesCatDoc = prgGesCatDoc;
		}

		public String getCodScuUte() {
			return codScuUte;
		}

		public void setCodScuUte(String codScuUte) {
			this.codScuUte = codScuUte;
		}

		public Long getPerAnnSco() {
			return perAnnSco;
		}

		public void setPerAnnSco(Long perAnnSco) {
			this.perAnnSco = perAnnSco;
		}

		public Date getDatFinVal() {
			return datFinVal;
		}

		public void setDatFinVal(Date datFinVal) {
			this.datFinVal = datFinVal;
		}

		public Date getDatIniVal() {
			return datIniVal;
		}

		public void setDatIniVal(Date datIniVal) {
			this.datIniVal = datIniVal;
		}

		public String getDesDoc() {
			return desDoc;
		}

		public void setDesDoc(String desDoc) {
			this.desDoc = desDoc;
		}

		public BigDecimal getNumVerDoc() {
			return numVerDoc;
		}

		public void setNumVerDoc(BigDecimal numVerDoc) {
			this.numVerDoc = numVerDoc;
		}

		public BigDecimal getPerTriRif() {
			return perTriRif;
		}

		public void setPerTriRif(BigDecimal perTriRif) {
			this.perTriRif = perTriRif;
		}

		public String getCodSta() {
			return codSta;
		}

		public void setCodSta(String codSta) {
			this.codSta = codSta;
		}

		public String getDesSta() {
			return desSta;
		}

		public void setDesSta(String desSta) {
			this.desSta = desSta;
		}
		
		
		


	}
