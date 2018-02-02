package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

	@Entity
	public class Scuola implements Serializable {
		private static final long serialVersionUID = 1L;

		@EmbeddedId
		private ScuolaPK id;
		
		@Column(name="COD_FOR_SCU_APP")
		private String codForScuApp;

		@Column(name="DES_NOM_SCU")
		private String desNomScu;
		
		@Column(name="DES_IND_SCU")
		private String desIndScu;
		
		@Column(name="DES_TIP_IST")
		private String desTipIst;
		
		@Column(name="DES_COM")
		private String desCom;
		
		@Column(name="DES_PRV")
		private String desPrv;
		
		@Column(name="COD_TEL_SCU")
		private String codTelScu;
		
		@Column(name="COD_FAX_SCU")
		private String codFaxScu;
		
		@Column(name="DES_IND_EML")
		private String desIndEml;
		
		@Column(name="DES_IND_WEB")
		private String desIndWeb;

		@Column(name="DES_IND_EMA_PCE")
		private String desIndEmaPce;
		
		@Column(name="COD_SCU_UT_PRI")
		private String codScuUtPri;

		@Column(name="DIRIGENTE")
		private String dirigente;
		
		public Scuola() {
		}

		public ScuolaPK getId() {
			return id;
		}

		public void setId(ScuolaPK id) {
			this.id = id;
		}

		public String getCodForScuApp() {
			return codForScuApp;
		}

		public void setCodForScuApp(String codForScuApp) {
			this.codForScuApp = codForScuApp;
		}

		public String getDesNomScu() {
			return desNomScu;
		}

		public void setDesNomScu(String desNomScu) {
			this.desNomScu = desNomScu;
		}

		public String getDesIndScu() {
			return desIndScu;
		}

		public void setDesIndScu(String desIndScu) {
			this.desIndScu = desIndScu;
		}

		public String getDesCom() {
			return desCom;
		}

		public void setDesCom(String desCom) {
			this.desCom = desCom;
		}

		public String getDesPrv() {
			return desPrv;
		}

		public void setDesPrv(String desPrv) {
			this.desPrv = desPrv;
		}

		public String getDesTipIst() {
			return desTipIst;
		}

		public void setDesTipIst(String desTipIst) {
			this.desTipIst = desTipIst;
		}

		public String getCodTelScu() {
			return codTelScu;
		}

		public void setCodTelScu(String codTelScu) {
			this.codTelScu = codTelScu;
		}

		public String getCodFaxScu() {
			return codFaxScu;
		}

		public void setCodFaxScu(String codFaxScu) {
			this.codFaxScu = codFaxScu;
		}

		public String getDesIndEml() {
			return desIndEml;
		}

		public void setDesIndEml(String desIndEml) {
			this.desIndEml = desIndEml;
		}

		public String getDesIndWeb() {
			return desIndWeb;
		}

		public void setDesIndWeb(String desIndWeb) {
			this.desIndWeb = desIndWeb;
		}

		public String getDesIndEmaPce() {
			return desIndEmaPce;
		}

		public void setDesIndEmaPce(String desIndEmaPce) {
			this.desIndEmaPce = desIndEmaPce;
		}

		public String getCodScuUtPri() {
			return codScuUtPri;
		}

		public void setCodScuUtPri(String codScuUtPri) {
			this.codScuUtPri = codScuUtPri;
		}

		public String getDirigente() {
			return dirigente;
		}

		public void setDirigente(String dirigente) {
			this.dirigente = dirigente;
		}

		

	}
