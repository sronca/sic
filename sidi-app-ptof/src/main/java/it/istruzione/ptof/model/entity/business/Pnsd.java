package it.istruzione.ptof.model.entity.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

	@Entity
	public class Pnsd implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="ID")
		private String id;
		
		@Column(name="AREA")
		private String area;

		@Column(name="COD_SEZ")
		private String codSez;

		@Column(name="DES_SEZ")
		private String desSez;

		@Column(name="CONTENUTO")
		private String contenuto;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getCodSez() {
			return codSez;
		}

		public void setCodSez(String codSez) {
			this.codSez = codSez;
		}

		public String getDesSez() {
			return desSez;
		}

		public void setDesSez(String desSez) {
			this.desSez = desSez;
		}

		public String getContenuto() {
			return contenuto;
		}

		public void setContenuto(String contenuto) {
			this.contenuto = contenuto;
		}
		





	}
