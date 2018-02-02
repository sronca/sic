package it.istruzione.ptof.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TBS1004_DATISEZIONESOTTOSEZ database table.
 * 
 */
@Entity
@Table(name="TBS1004_DATISEZIONESOTTOSEZ", schema = "UBSOFFFOR_OWN", catalog = "")
@NamedQuery(name="Tbs1004Datisezionesottosez.findAll", query="SELECT t FROM Tbs1004Datisezionesottosez t")
public class Tbs1004Datisezionesottosez implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBS1004_DATISEZIONESOTTOSEZ_PRGDATPTF_GENERATOR", sequenceName="Q1004DATISEZIONESOTTOSEZ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBS1004_DATISEZIONESOTTOSEZ_PRGDATPTF_GENERATOR")
	@Column(name="PRG_DAT_PTF")
	private Long prgDatPtf;

	@Column(name="COD_SCU_UTE")
	private String codScuUte;
	
	@Column(name="PER_ANN_SCO")
	private Long perAnnSco;
	
	@Column(name="PRG_GES_CAT_DOC")
	private Long prgGesCatDoc;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRG_SEZ_SOT_SEZ")
	private Tbs1003Catalogosezione tbs1003Catalogosezione;
	
	@Column(name="COD_STA")
	private String codSta;

	@Column(name="COD_TIP_CTS")
	private String codTipCts;

	@Column(name="COD_TIP_MLT")
	private String codTipMlt;

	@Column(name="COD_PGM_ULT_MOV")
	private String codPgmUltMov;
	
	@Column(name="COD_UTE_ULT_MOV")
	private String codUteUltMov;

	@Temporal(TemporalType.DATE)
	@Column(name="DAT_ORA_ULT_MOV")
	private Date datOraUltMov;
	
	@Column(name="COD_SCU_UTE_PLE")
	private String codScuUtePle;
	
	@Column(name="DES_MOD_VER")
	private String desModVer;
	
	@Column(name="DES_RAP_PIA")
	private String desRapPia;
	
	@Column(name="DES_NOT_IND_STU")
	private String desNotIndStu;
	
	@Column(name="DES_NOT_QUA_ORA")
	private String desNotQuaOra;
	
	@Column(name="DES_NOT_TMP_SCU")
	private String desNotTmpScu;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DAT_RAT_DIR_SCO")
	private Date datRatDirSco;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DAT_PRD_PTO")
	private Date datPrdPto;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DAT_APP_CDI")
	private Date datAppCdi;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DAT_INV_USR")
	private Date datInvUsr;
	
	@Column(name="DES_MOT_FAB_ATM")
	private String desMotFabAtm;
	
	@Column(name="DES_FIN_FAB_ATM")
	private String desFinFabAtm;
	
	@Column(name="DES_BEA_FAB_ATM")
	private String desBeaFabAtm;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@OrderBy("prgAll")
	private List<Tbs1005Allegato> tbs1005Allegatos;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@OrderBy("prgTes")
	private List<Tbs1007Testo> tbs1007Testos;

	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1009Obiettivo> tbs1009Obiettivos;

	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1013Altrobiettivo> tbs1013Altrobiettivos;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1021Ambitoprogettoptof> tbs1021Ambitoprogettoptofs;

	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1022Ambitoprogettoaltro> tbs1022Ambitoprogettoaltros;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1023Altreinizididattiche> tbs1023Altreinizididattiches;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1024Attivitasostegnoptof> tbs1024Attivitasostegnoptofs;

	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1026Organrisorseptof> tbs1026Organrisorseptofs;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1027Performazpersonale> tbs1027Performazpersonales;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1028Attrezzature> tbs1028Attrezzatures;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1029Dotazionemultimediale> tbs1029Dotazionemultimediales;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1030Rapportoente> tbs1030Rapportoentes;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1031Convenzioneforma> tbs1031Convenzioneformas;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1032Pianificattivita> tbs1032Pianificattivitas;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1034Articoclasseinfanzia> tbs1034Articoclasseinfanzias;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1035Articoclasseprimaria> tbs1035Articoclasseprimarias;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1036Articolaclasseigrado> tbs1036Articolaclasseigrados;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1038Articolaclasseiigrado> tbs1038Articolaclasseiigrados;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1037Fabpostocomune> tbs1037Fabpostocomunes;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1039Fabpostosostegno> tbs1039Fabpostosostegnos;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1040Fabpostopotenziamento> tbs1040Fabpostopotenziamentos;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1041Fabfiguraprofessionale> tbs1041Fabfiguraprofessionales;
	
	@OneToMany(mappedBy="tbs1004Datisezionesottosez",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Tbs1042Fabattrezmateriale> tbs1042Fabattrezmateriales;
	
	public Tbs1004Datisezionesottosez() {
	}

	public Long getPrgDatPtf() {
		return this.prgDatPtf;
	}

	public void setPrgDatPtf(Long prgDatPtf) {
		this.prgDatPtf = prgDatPtf;
	}

	public String getCodPgmUltMov() {
		return this.codPgmUltMov;
	}

	public void setCodPgmUltMov(String codPgmUltMov) {
		this.codPgmUltMov = codPgmUltMov;
	}

	public String getCodScuUte() {
		return this.codScuUte;
	}

	public void setCodScuUte(String codScuUte) {
		this.codScuUte = codScuUte;
	}

	public String getCodSta() {
		return this.codSta;
	}

	public void setCodSta(String codSta) {
		this.codSta = codSta;
	}

	public String getCodTipCts() {
		return this.codTipCts;
	}

	public void setCodTipCts(String codTipCts) {
		this.codTipCts = codTipCts;
	}

	public String getCodTipMlt() {
		return this.codTipMlt;
	}

	public void setCodTipMlt(String codTipMlt) {
		this.codTipMlt = codTipMlt;
	}

	public String getCodUteUltMov() {
		return this.codUteUltMov;
	}

	public void setCodUteUltMov(String codUteUltMov) {
		this.codUteUltMov = codUteUltMov;
	}

	public Date getDatOraUltMov() {
		return this.datOraUltMov;
	}

	public void setDatOraUltMov(Date datOraUltMov) {
		this.datOraUltMov = datOraUltMov;
	}

	public Long getPerAnnSco() {
		return this.perAnnSco;
	}

	public void setPerAnnSco(Long perAnnSco) {
		this.perAnnSco = perAnnSco;
	}

	public Long getPrgGesCatDoc() {
		return this.prgGesCatDoc;
	}

	public void setPrgGesCatDoc(Long prgGesCatDoc) {
		this.prgGesCatDoc = prgGesCatDoc;
	}

	public List<Tbs1005Allegato> getTbs1005Allegatos() {
		return tbs1005Allegatos;
	}

	public void setTbs1005Allegatos(List<Tbs1005Allegato> tbs1005Allegatos) {
		this.tbs1005Allegatos = tbs1005Allegatos;
	}

	public List<Tbs1007Testo> getTbs1007Testos() {
		return tbs1007Testos;
	}

	public void setTbs1007Testos(List<Tbs1007Testo> tbs1007Testos) {
		this.tbs1007Testos = tbs1007Testos;
	}

	public Tbs1003Catalogosezione getTbs1003Catalogosezione() {
		return tbs1003Catalogosezione;
	}

	public void setTbs1003Catalogosezione(
			Tbs1003Catalogosezione tbs1003Catalogosezione) {
		this.tbs1003Catalogosezione = tbs1003Catalogosezione;
	}

	public List<Tbs1009Obiettivo> getTbs1009Obiettivos() {
		return tbs1009Obiettivos;
	}

	public void setTbs1009Obiettivos(List<Tbs1009Obiettivo> tbs1009Obiettivos) {
		this.tbs1009Obiettivos = tbs1009Obiettivos;
	}

	public List<Tbs1013Altrobiettivo> getTbs1013Altrobiettivos() {
		return tbs1013Altrobiettivos;
	}

	public void setTbs1013Altrobiettivos(
			List<Tbs1013Altrobiettivo> tbs1013Altrobiettivos) {
		this.tbs1013Altrobiettivos = tbs1013Altrobiettivos;
	}

	public String getCodScuUtePle() {
		return codScuUtePle;
	}

	public void setCodScuUtePle(String codScuUtePle) {
		this.codScuUtePle = codScuUtePle;
	}

	public List<Tbs1021Ambitoprogettoptof> getTbs1021Ambitoprogettoptofs() {
		return tbs1021Ambitoprogettoptofs;
	}

	public void setTbs1021Ambitoprogettoptofs(
			List<Tbs1021Ambitoprogettoptof> tbs1021Ambitoprogettoptofs) {
		this.tbs1021Ambitoprogettoptofs = tbs1021Ambitoprogettoptofs;
	}

	public List<Tbs1022Ambitoprogettoaltro> getTbs1022Ambitoprogettoaltros() {
		return tbs1022Ambitoprogettoaltros;
	}

	public void setTbs1022Ambitoprogettoaltros(
			List<Tbs1022Ambitoprogettoaltro> tbs1022Ambitoprogettoaltros) {
		this.tbs1022Ambitoprogettoaltros = tbs1022Ambitoprogettoaltros;
	}

	public List<Tbs1023Altreinizididattiche> getTbs1023Altreinizididattiches() {
		return tbs1023Altreinizididattiches;
	}

	public void setTbs1023Altreinizididattiches(
			List<Tbs1023Altreinizididattiche> tbs1023Altreinizididattiches) {
		this.tbs1023Altreinizididattiches = tbs1023Altreinizididattiches;
	}

	public List<Tbs1024Attivitasostegnoptof> getTbs1024Attivitasostegnoptofs() {
		return tbs1024Attivitasostegnoptofs;
	}

	public void setTbs1024Attivitasostegnoptofs(
			List<Tbs1024Attivitasostegnoptof> tbs1024Attivitasostegnoptofs) {
		this.tbs1024Attivitasostegnoptofs = tbs1024Attivitasostegnoptofs;
	}

	public List<Tbs1026Organrisorseptof> getTbs1026Organrisorseptofs() {
		return tbs1026Organrisorseptofs;
	}

	public void setTbs1026Organrisorseptofs(
			List<Tbs1026Organrisorseptof> tbs1026Organrisorseptofs) {
		this.tbs1026Organrisorseptofs = tbs1026Organrisorseptofs;
	}

	public String getDesModVer() {
		return desModVer;
	}

	public void setDesModVer(String desModVer) {
		this.desModVer = desModVer;
	}

	public String getDesRapPia() {
		return desRapPia;
	}

	public void setDesRapPia(String desRapPia) {
		this.desRapPia = desRapPia;
	}

	public List<Tbs1027Performazpersonale> getTbs1027Performazpersonales() {
		return tbs1027Performazpersonales;
	}

	public void setTbs1027Performazpersonales(
			List<Tbs1027Performazpersonale> tbs1027Performazpersonales) {
		this.tbs1027Performazpersonales = tbs1027Performazpersonales;
	}

	public List<Tbs1028Attrezzature> getTbs1028Attrezzatures() {
		return tbs1028Attrezzatures;
	}

	public void setTbs1028Attrezzatures(
			List<Tbs1028Attrezzature> tbs1028Attrezzatures) {
		this.tbs1028Attrezzatures = tbs1028Attrezzatures;
	}

	public List<Tbs1029Dotazionemultimediale> getTbs1029Dotazionemultimediales() {
		return tbs1029Dotazionemultimediales;
	}

	public void setTbs1029Dotazionemultimediales(
			List<Tbs1029Dotazionemultimediale> tbs1029Dotazionemultimediales) {
		this.tbs1029Dotazionemultimediales = tbs1029Dotazionemultimediales;
	}

	public List<Tbs1030Rapportoente> getTbs1030Rapportoentes() {
		return tbs1030Rapportoentes;
	}

	public void setTbs1030Rapportoentes(
			List<Tbs1030Rapportoente> tbs1030Rapportoentes) {
		this.tbs1030Rapportoentes = tbs1030Rapportoentes;
	}

	public List<Tbs1031Convenzioneforma> getTbs1031Convenzioneformas() {
		return tbs1031Convenzioneformas;
	}

	public void setTbs1031Convenzioneformas(
			List<Tbs1031Convenzioneforma> tbs1031Convenzioneformas) {
		this.tbs1031Convenzioneformas = tbs1031Convenzioneformas;
	}

	public List<Tbs1032Pianificattivita> getTbs1032Pianificattivitas() {
		return tbs1032Pianificattivitas;
	}

	public void setTbs1032Pianificattivitas(
			List<Tbs1032Pianificattivita> tbs1032Pianificattivitas) {
		this.tbs1032Pianificattivitas = tbs1032Pianificattivitas;
	}

	public List<Tbs1034Articoclasseinfanzia> getTbs1034Articoclasseinfanzias() {
		return tbs1034Articoclasseinfanzias;
	}

	public void setTbs1034Articoclasseinfanzias(
			List<Tbs1034Articoclasseinfanzia> tbs1034Articoclasseinfanzias) {
		this.tbs1034Articoclasseinfanzias = tbs1034Articoclasseinfanzias;
	}

	public List<Tbs1035Articoclasseprimaria> getTbs1035Articoclasseprimarias() {
		return tbs1035Articoclasseprimarias;
	}

	public void setTbs1035Articoclasseprimarias(
			List<Tbs1035Articoclasseprimaria> tbs1035Articoclasseprimarias) {
		this.tbs1035Articoclasseprimarias = tbs1035Articoclasseprimarias;
	}

	public List<Tbs1036Articolaclasseigrado> getTbs1036Articolaclasseigrados() {
		return tbs1036Articolaclasseigrados;
	}

	public void setTbs1036Articolaclasseigrados(
			List<Tbs1036Articolaclasseigrado> tbs1036Articolaclasseigrados) {
		this.tbs1036Articolaclasseigrados = tbs1036Articolaclasseigrados;
	}

	public List<Tbs1038Articolaclasseiigrado> getTbs1038Articolaclasseiigrados() {
		return tbs1038Articolaclasseiigrados;
	}

	public void setTbs1038Articolaclasseiigrados(
			List<Tbs1038Articolaclasseiigrado> tbs1038Articolaclasseiigrados) {
		this.tbs1038Articolaclasseiigrados = tbs1038Articolaclasseiigrados;
	}

	public List<Tbs1037Fabpostocomune> getTbs1037Fabpostocomunes() {
		return tbs1037Fabpostocomunes;
	}

	public void setTbs1037Fabpostocomunes(
			List<Tbs1037Fabpostocomune> tbs1037Fabpostocomunes) {
		this.tbs1037Fabpostocomunes = tbs1037Fabpostocomunes;
	}

	public List<Tbs1039Fabpostosostegno> getTbs1039Fabpostosostegnos() {
		return tbs1039Fabpostosostegnos;
	}

	public void setTbs1039Fabpostosostegnos(
			List<Tbs1039Fabpostosostegno> tbs1039Fabpostosostegnos) {
		this.tbs1039Fabpostosostegnos = tbs1039Fabpostosostegnos;
	}

	public List<Tbs1040Fabpostopotenziamento> getTbs1040Fabpostopotenziamentos() {
		return tbs1040Fabpostopotenziamentos;
	}

	public void setTbs1040Fabpostopotenziamentos(
			List<Tbs1040Fabpostopotenziamento> tbs1040Fabpostopotenziamentos) {
		this.tbs1040Fabpostopotenziamentos = tbs1040Fabpostopotenziamentos;
	}

	public List<Tbs1041Fabfiguraprofessionale> getTbs1041Fabfiguraprofessionales() {
		return tbs1041Fabfiguraprofessionales;
	}

	public void setTbs1041Fabfiguraprofessionales(
			List<Tbs1041Fabfiguraprofessionale> tbs1041Fabfiguraprofessionales) {
		this.tbs1041Fabfiguraprofessionales = tbs1041Fabfiguraprofessionales;
	}

	public List<Tbs1042Fabattrezmateriale> getTbs1042Fabattrezmateriales() {
		return tbs1042Fabattrezmateriales;
	}

	public void setTbs1042Fabattrezmateriales(
			List<Tbs1042Fabattrezmateriale> tbs1042Fabattrezmateriales) {
		this.tbs1042Fabattrezmateriales = tbs1042Fabattrezmateriales;
	}

	public String getDesNotIndStu() {
		return desNotIndStu;
	}

	public void setDesNotIndStu(String desNotIndStu) {
		this.desNotIndStu = desNotIndStu;
	}

	public String getDesNotQuaOra() {
		return desNotQuaOra;
	}

	public void setDesNotQuaOra(String desNotQuaOra) {
		this.desNotQuaOra = desNotQuaOra;
	}

	public String getDesNotTmpScu() {
		return desNotTmpScu;
	}

	public void setDesNotTmpScu(String desNotTmpScu) {
		this.desNotTmpScu = desNotTmpScu;
	}

	public Date getDatRatDirSco() {
		return datRatDirSco;
	}

	public void setDatRatDirSco(Date datRatDirSco) {
		this.datRatDirSco = datRatDirSco;
	}

	public Date getDatPrdPto() {
		return datPrdPto;
	}

	public void setDatPrdPto(Date datPrdPto) {
		this.datPrdPto = datPrdPto;
	}

	public Date getDatAppCdi() {
		return datAppCdi;
	}

	public void setDatAppCdi(Date datAppCdi) {
		this.datAppCdi = datAppCdi;
	}

	public Date getDatInvUsr() {
		return datInvUsr;
	}

	public void setDatInvUsr(Date datInvUsr) {
		this.datInvUsr = datInvUsr;
	}

	public String getDesMotFabAtm() {
		return desMotFabAtm;
	}

	public void setDesMotFabAtm(String desMotFabAtm) {
		this.desMotFabAtm = desMotFabAtm;
	}

	public String getDesFinFabAtm() {
		return desFinFabAtm;
	}

	public void setDesFinFabAtm(String desFinFabAtm) {
		this.desFinFabAtm = desFinFabAtm;
	}

	public String getDesBeaFabAtm() {
		return desBeaFabAtm;
	}

	public void setDesBeaFabAtm(String desBeaFabAtm) {
		this.desBeaFabAtm = desBeaFabAtm;
	}




	
}