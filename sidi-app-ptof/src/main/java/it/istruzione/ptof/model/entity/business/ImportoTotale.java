package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

	@Entity
	public class ImportoTotale implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="KEY")
		private String key;

		@Column(name="IMP")
		private BigDecimal imp;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public BigDecimal getImp() {
			return imp;
		}

		public void setImp(BigDecimal imp) {
			this.imp = imp;
		}
		
		
		






	}
