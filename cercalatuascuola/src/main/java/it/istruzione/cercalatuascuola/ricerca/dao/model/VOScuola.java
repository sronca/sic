package it.istruzione.cercalatuascuola.ricerca.dao.model;

import it.istruzione.cercalatuascuola.common.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class VOScuola 
{
	private String descrizione = "";
	private String codMecc = "";
	private String codForte = "";
	private String datAnnScoRil = "";
	private String desAnnScoRil = "";		
	private String ordine = "";
	private String checkStatale = "";
	private String checkNonStatale = "";
	private String checkParitaria = "";
	private String checkNonParitaria = "";
	private String tipologia = "";
	private String indirizzo = "";
	private String localita = "";
	private String cap = "";
	private String comune = "";
	private String provincia = "";
	private String provinciaBreve = "";
	private String regione = "";
	private String telefono = "";
	private String fax = "";
	private String indirizzoEmail = "";
	private String sitoWeb = "";
	private String latitudine = "";
	private String longitudine = "";
	private String raggio = "";
	private String codMeccIstRif = "";
	private String settore = "";
	private String indirizzoStudio = "";
	private String descrizionePercorso = "";
	private String descrizioneSettore = "";
	private String descrizioneIndirizzoStudio = "";
	//private List tipologieScuole = new ArrayList();
	//private List indirizziDiStudio = new ArrayList();
	//private List serviziAttivi = new ArrayList();
	private List servizi = new ArrayList();
	private List succursali = new ArrayList();
	private String dataUltimoAggiornamento = null;
	private String selezionato = "";
	private String indice = "";
	private String dirigenteScolastico = "";
	private String stataleNonStatale = "";
	private String paritariaNonParitaria = "";
	private String indirizzoEmailPEC = "";	
	
	private String tipoIstituzione = "";
	private String denScuPri = "";
	
	private String numeroAlunni = "";
	private String numeroClassi = "";
	private String mediaAlunniClassi = "";
	private String numeroPersonaleDocente = "";
	
	private String codTipSit = "";
	
	public String getNumeroAlunni() {
		return numeroAlunni;
	}
	public void setNumeroAlunni(String numeroAlunni) {
		this.numeroAlunni = numeroAlunni;
	}
	public String getNumeroClassi() {
		return numeroClassi;
	}
	public void setNumeroClassi(String numeroClassi) {
		this.numeroClassi = numeroClassi;
	}
	public String getMediaAlunniClassi() {
		return mediaAlunniClassi;
	}
	public void setMediaAlunniClassi(String mediaAlunniClassi) {
		this.mediaAlunniClassi = mediaAlunniClassi;
	}
	public String getNumeroPersonaleDocente() {
		return numeroPersonaleDocente;
	}
	public void setNumeroPersonaleDocente(String numeroPersonaleDocente) {
		this.numeroPersonaleDocente = numeroPersonaleDocente;
	}	
	public void setDenScuPri(String denScuPri) {
		this.denScuPri = denScuPri;
	}
	public void setIndirizzoEmailPEC(String indirizzoEmailPEC) {
		this.indirizzoEmailPEC = indirizzoEmailPEC;
	}
	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}
	public String getCodMecc()
	{
		return codMecc;
	}
	public void setCodMecc(String codMecc)
	{
		this.codMecc = codMecc;
	}
	public String getOrdine()
	{
		return ordine;
	}
	public void setOrdine(String ordine)
	{
		this.ordine = ordine;
	}
	public String getIndirizzo()
	{
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo)
	{
		this.indirizzo = indirizzo;
	}
	public String getLocalita()
	{
		return localita;
	}
	public void setLocalita(String localita)
	{
		this.localita = localita;
	}
	public String getCap()
	{
		return cap;
	}
	public void setCap(String cap)
	{
		this.cap = cap;
	}
	public String getComune()
	{
		return comune;
	}
	public void setComune(String comune)
	{
		this.comune = comune;
	}
	public String getProvincia()
	{
		return provincia;
	}
	public void setProvincia(String provincia)
	{
		this.provincia = provincia;
	}
	public String getRegione()
	{
		return regione;
	}
	public void setRegione(String regione)
	{
		this.regione = regione;
	}
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}
	public void setFax(String fax)
	{
		this.fax = fax;
	}
	public void setIndirizzoEmail(String indirizzoEmail)
	{
		this.indirizzoEmail = indirizzoEmail;
	}
	public void setSitoWeb(String sitoWeb)
	{
		this.sitoWeb = sitoWeb;
	}
	public String getLatitudine()
	{
		return latitudine;
	}
	public void setLatitudine(String latitudine)
	{
		this.latitudine = latitudine;
	}
	public String getLongitudine()
	{
		return longitudine;
	}
	public void setLongitudine(String longitudine)
	{
		this.longitudine = longitudine;
	}
	public String getCodMeccIstRif()
	{
		return codMeccIstRif;
	}
	public void setCodMeccIstRif(String codMeccIstRif)
	{
		this.codMeccIstRif = codMeccIstRif;
	}
	/*
	public List getIndirizziDiStudio()
	{
		return indirizziDiStudio;
	}
	public void setIndirizziDiStudio(List indirizziDiStudio)
	{
		this.indirizziDiStudio = indirizziDiStudio;
	}
	*/
	public String getDataUltimoAggiornamento()
	{
		return dataUltimoAggiornamento;
	}
	public void setDataUltimoAggiornamento(String dataUltimoAggiornamento)
	{
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}
	public String getSelezionato()
	{
		return selezionato;
	}
	public void setSelezionato(String selezionato)
	{
		this.selezionato = selezionato;
	}
	public String getIndice()
	{
		return indice;
	}
	public void setIndice(String indice)
	{
		this.indice = indice;
	}
	public void setDirigenteScolastico(String dirigenteScolastico)
	{
		this.dirigenteScolastico = dirigenteScolastico;
	}
	public String getProvinciaBreve()
	{
		return provinciaBreve;
	}
	public void setProvinciaBreve(String provinciaBreve)
	{
		this.provinciaBreve = provinciaBreve;
	}
	public String getTipologia()
	{
		return tipologia;
	}
	public void setTipologia(String tipologia)
	{
		this.tipologia = tipologia;
	}
	
	public String getSettore() {
		return settore;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}
	public String getIndirizzoStudio()
	{
		return indirizzoStudio;
	}
	public void setIndirizzoStudio(String indirizzoStudio)
	{
		this.indirizzoStudio = indirizzoStudio;
	}
	public String getDescrizionePercorso() {
		return descrizionePercorso;
	}
	public void setDescrizionePercorso(String descrizionePercorso) {
		this.descrizionePercorso = descrizionePercorso;
	}
	public String getDescrizioneSettore() {
		return descrizioneSettore;
	}
	public void setDescrizioneSettore(String descrizioneSettore) {
		this.descrizioneSettore = descrizioneSettore;
	}
	public String getDescrizioneIndirizzoStudio() {
		return descrizioneIndirizzoStudio;
	}
	public void setDescrizioneIndirizzoStudio(String descrizioneIndirizzoStudio) {
		this.descrizioneIndirizzoStudio = descrizioneIndirizzoStudio;
	}
	/*
	public List getTipologieScuole()
	{
		return tipologieScuole;
	}
	public void setTipologieScuole(List tipologieScuole)
	{
		this.tipologieScuole = tipologieScuole;
	}
	public List getServiziAttivi()
	{
		return serviziAttivi;
	}
	public void setServiziAttivi(List serviziAttivi)
	{
		this.serviziAttivi = serviziAttivi;
	}
	*/
	public List getServizi()
	{
		return servizi;
	}
	public void setServizi(List servizi)
	{
		this.servizi = servizi;
	}
	public String getCheckStatale()
	{
		return checkStatale;
	}
	public void setCheckStatale(String checkStatale)
	{
		this.checkStatale = checkStatale;
	}
	public String getCheckNonStatale()
	{
		return checkNonStatale;
	}
	public void setCheckNonStatale(String checkNonStatale)
	{
		this.checkNonStatale = checkNonStatale;
	}
	public String getCheckParitaria()
	{
		return checkParitaria;
	}
	public void setCheckParitaria(String checkParitaria)
	{
		this.checkParitaria = checkParitaria;
	}
	public String getCheckNonParitaria()
	{
		return checkNonParitaria;
	}
	public void setCheckNonParitaria(String checkNonParitaria)
	{
		this.checkNonParitaria = checkNonParitaria;
	}
	public String getRaggio()
	{
		return raggio;
	}
	public void setRaggio(String raggio)
	{
		this.raggio = raggio;
	}
	public String getCodForte()
	{
		return codForte;
	}
	public void setCodForte(String codForte)
	{
		this.codForte = codForte;
	}
	
	public List getSuccursali()
	{
		return succursali;
	}
	public void setSuccursali(List succursali)
	{
		this.succursali = succursali;
	}
	
	public String getStataleNonStatale()
	{
		return stataleNonStatale;
	}
	public void setStataleNonStatale(String stataleNonStatale)
	{
		this.stataleNonStatale = stataleNonStatale;
	}
	public String getParitariaNonParitaria()
	{
		return paritariaNonParitaria;
	}
	public void setParitariaNonParitaria(String paritariaNonParitaria)
	{
		this.paritariaNonParitaria = paritariaNonParitaria;
	}
	public String getDatAnnScoRil() {
		return datAnnScoRil;
	}
	public void setDatAnnScoRil(String datAnnScoRil) {
		this.datAnnScoRil = datAnnScoRil;
	}
	
	public void setDesAnnScoRil(String desAnnScoRil) {
		this.desAnnScoRil = desAnnScoRil;
	}
	
	public String getDesAnnScoRil() {
		return desAnnScoRil;
	}
	public String getTipoIstituzione() {
		return tipoIstituzione;
	}
	public void setTipoIstituzione(String tipoIstituzione) {
		this.tipoIstituzione = tipoIstituzione;
	}
	
	public String getCodTipSit() {
		return codTipSit;
	}
	public void setCodTipSit(String codTipSit) {
		this.codTipSit = codTipSit;
	}
	
	//Viale Lorem Ipsum Dolor 140/4, 20155 Milano (MI)
	public String getIndirizzoCompleto() {
		String out = Utility.trimValue(indirizzo) +
				(Utility.trimValue(indirizzo).equals("")?" ":", ") + Utility.trimValue(cap) +
			    " " + Utility.trimValue(comune) +
			    " (" + Utility.trimValue(provinciaBreve) + ")"
			   ;
		
		out = out.replaceAll("\"", "").replaceAll("'", "");
		return out;
	}
	
	public boolean isCfp(){
		return "CF".equals(codMecc.substring(2, 4));
	}
	
	public String getIconUrl(){
		String iconprefix = "/cercalatuascuola/resources/css/images/pin/pin-small-";
		String iconsuffix = "";
		if (this.isCfp()){
			iconsuffix="3.png";
		}else if (this.stataleNonStatale.equals("0")){
			iconsuffix="1.png";
		}else if (this.stataleNonStatale.equals("1")){
			iconsuffix="2.png";
		}
		return iconprefix + iconsuffix;
	}

	
	/** TODO RIPRISTINARE **/
	public String getDescrizione()
	{
		String out = "";
		if (descrizione != null){
			out = descrizione.replaceAll("\"", "").replaceAll("'", "");
		}
		return out;
	}
	public String getNormalizedName() {
		return Utility.normalizzaNomeScuola(descrizione);
	}
	public String getTelefono()
	{
		return telefono;
	}
	public String getFax()
	{
		return fax;
	}
	public String getIndirizzoEmail()
	{
		return indirizzoEmail;
	}
	public String getSitoWeb()
	{
		return sitoWeb;
	}
	public String getDirigenteScolastico()
	{
		return dirigenteScolastico;
	}
	public String getIndirizzoEmailPEC() {
		return indirizzoEmailPEC;
	}
	public String getDenScuPri() {
		String out = "";
		if (denScuPri != null){
			out = denScuPri.replaceAll("\"", "").replaceAll("'", "");
		}
		return out;
	}
	public String getNormalizedNameIstRif() {
		return Utility.normalizzaNomeScuola(denScuPri);
	}
	/** TODO FINE **/
	/** TODO ELIMINARE **/
//	public String getDescrizione()
//	{
//		return "Istituto di prova";
//	}
//	public String getNormalizedName() {
//		return "Istituto-di-prova";
//	}
//	public String getTelefono()
//	{
//		return "00000000000";
//	}
//	public String getFax()
//	{
//		return "00000000000";
//	}
//	public String getIndirizzoEmail()
//	{
//		return "istituto_di_prova@istruzione.it";
//	}
//	public String getSitoWeb()
//	{
//		return "www.istituto_di_prova.it";
//	}
//	public String getDirigenteScolastico()
//	{
//		return "Mario Rossi";
//	}
//	public String getIndirizzoEmailPEC() {
//		return "istituto_di_prova@pec.it";
//	}
//	public String getDenScuPri() {
//		return "Istituto principale di prova";
//	}
//	public String getNormalizedNameIstRif() {
//		return "Istituto-principale-di-prova";
//	}
	
	/** TODO FINE **/
	
}
