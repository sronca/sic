package it.istruzione.cercalatuascuola.ricerca.dao;

import it.istruzione.cercalatuascuola.ricerca.dao.model.VOAndamento;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOAnniCorsoAlunni;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOAttrezzatureMultimediali;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOClasse;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOCommon;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VORicerca;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOScuola;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOServizi;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOTipologiaServizio;

import java.util.List;

public interface CercaLaTuaScuolaDAO {

	public List<VOScuola> getListaScuoleRicerca(VORicerca voRicerca) throws Exception;

	public List<VOScuola> getListaScuoleRicercaRapida(String filter)
			throws Exception;

	public int getListaScuoleRicercaRapidaCount(String filter) throws Exception;

	public List<VOCommon> getTipologieParitarie() throws Exception;

	public List<VOCommon> getTipologie() throws Exception;

	public List<VOCommon> getIndirizziDiStudio(String codIstituto, String codSettore,
			String biennioTriennio) throws Exception;

	public List<VOCommon> getSettoriScuola(String codiceIstituto) throws Exception;

	public List<VOCommon> getTempiScuolaPrimaria() throws Exception;

	public List<VOCommon> getTempiScuolaSecondariaDiPrimoGrado() throws Exception;

	public VOScuola getScuola(String codiceMeccanografico) throws Exception;

	public VOScuola getServiziScuola(VOScuola voScuola) throws Exception;

	public VOScuola getSuccursaliScuola(VOScuola voScuola,
			String annoAccademicoParam) throws Exception;

	public List getServizi() throws Exception;

	public List<VOServizi> getServiziRicercaAvanzataEPopupConfronta() throws Exception;

	public byte[] getAllegatoIndirizzoStudio(String codiceIndirizzoStudio)
			throws Exception;

	public List<VOCommon> getListaRegioni() throws Exception;

	public List<VOCommon> getListaProvincePerRegione(String codiceRegione)
			throws Exception;

	public List<VOCommon> getListaComuniPerProvincia(String codiceProvincia)
			throws Exception;

	public List<VOClasse> getDistribuzioneClassiIndirizzi(String codForScuApp)
			throws Exception;

	public VOAttrezzatureMultimediali getAttrezzatureMultimediali(
			String codForScuApp) throws Exception;

	public Integer getNumeroAlunni(String codForScuApp) throws Exception;

	public List<VOTipologiaServizio> getServiziAttiviPerTipologia(
			String codTipSer, String codScuUt, String datAnnScoRil)
			throws Exception;

	public List<VOAndamento> getAlunniIscrittiUltimiDueAnni(String codForScuApp)
			throws Exception;

	public VOAndamento getEsitiScrutini(String codForScuApp) throws Exception;

	public List<VOScuola> getListaCentriFormazioneProfessionale(
			VORicerca voRicerca)
			throws Exception;

	public VOScuola getCentroFormazioneProfessionale(
			String codiceMeccanografico, VORicerca voRicerca,
			String parametroRicercaServizi) throws Exception;

	public List<VOCommon> getPercorsiCFP() throws Exception;

	public List<VOCommon> getSettoriCFP(String cod_cla_min) throws Exception;

	public List<VOCommon> getIndirizziDiStudioCFP(String cod_cla_min_des_set)
			throws Exception;

	public List<VOAnniCorsoAlunni> getAlunniPerAnnoCorso(String codForScuApp)
			throws Exception;

	public Integer getNumTotDocenti(String codForScuApp) throws Exception;

}