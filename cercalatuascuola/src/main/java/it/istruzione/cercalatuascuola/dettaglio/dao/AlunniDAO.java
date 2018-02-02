package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.*;

import java.util.List;

public interface AlunniDAO {
	
	List<VOClasse> getDistribuzioneClassiIndirizzi(String codForScuApp)	throws Exception;

	List<VOAndamento> getAlunniIscrittiUltimiDueAnni(String codForScuApp) throws Exception;

	List<VOAndamento> getAlunniRipetenti(String codForScuApp) throws Exception;

	List<VOAndamento> getAlunniIngressoUscita(String codForScuApp) throws Exception;

	List<VOAndamento> getIndicatoriAlunniIstogramma4(String codForScuApp) throws Exception;

	VOAndamento getEsitiScrutini(String codForScuApp, boolean $secondariaSecondoGrado) throws Exception;

	List<VOAndamento> getIndicatoriAlunniIstogramma1_Risultati(String codForScuApp)	throws Exception;

	List<VOEsameAlunni> getIndicatoriAlunniTorta2(String codForScuApp) throws Exception;

	List<VOAndamento> getAmmessiGiugno(String codForScuApp)	throws Exception;

	List<VOAndamento> getAmmessiSettembre(String codForScuApp) throws Exception;

	VOAndamento getIndicatoriAlunniIstogramma3_Risultati(String codForScuApp) throws Exception;

	List<VOAnniCorsoAlunni> getAlunniPerAnnoCorso(String codForScuApp) throws Exception;
	
	List<VOAnniCorsoAlunni> getAlunniPerAnnoCorsoScuoleSerali(String $codForScuApp) throws Exception;

	List<VOEsameVotazione> getVotazioniProveEsami(String codForScuApp) throws Exception;

	List<VOAlunniImmAD> getAlunniImmatricolatiPerAreaDidattica(String codForScuApp)	throws Exception;

	List<VOAlunniImm> getAlunniImmatricolati(String codForScuApp) throws Exception;
	
	List<VORav24b1> getIndicatoriRav24b1(String codScuUt, int datAnnScoRil, int annoCorso) throws Exception;
	
	VORav24C5 getIndicatoriRav24c5(String codScuUt, int datAnnScoRil) throws Exception;
	
	List<VORav24c1> getIndicatoriRav24c1(String codScuUt, int datAnnScoRil)	throws Exception;

	VORav24c2 getIndicatoriRav24c2(String codScuUt, int datAnnScoRil, boolean isIGrado)	throws Exception;
	
	VORav24c2 getIndicatoriRav24c3(String codScuUt, int datAnnScoRil, boolean isIGrado)	throws Exception;
	
	List<VOAlternanzaScuolaLavoro> getDatiAlternanzaScuolaLavoro(String codForScuApp) throws Exception;
	
	List<VOStruttureOspitanti> getStruttureOspitanti(String $codScuUt, Integer $perAnnScoVal, Long $prgStrScu) throws Exception;
	
	List<VOPercorsiAttivati> getPercorsiStruttura(String $codScuUt, Integer $perAnnScoVal, Long $prgStrScu) throws Exception;
	
	List<VOPercorsiAttivati> getPercorsiAttivati(String $codScuUt, Integer $perAnnScoVal, Long $prgPerScu) throws Exception;
	
	List<VOStruttureOspitanti> getStruttureOspitantiPercorso(String $codScuUt, Integer $perAnnScoVal, Long $prgPerScu) throws Exception;
	
	public Integer getNumPercorsiAlternanzaScuolaLavoro(String $codScuUt, Integer $perAnnScoVal) throws Exception;
	
	public Integer getNumStruttureAlternanzaScuolaLavoro(String $codScuUt, Integer $perAnnScoVal) throws Exception;
	
	public List<VOLavoro1> getDatiLavoro1(String codForScuApp) throws Exception;
	
	public List<VOLavoro2> getDatiLavoro2(String codForScuApp) throws Exception;
	
	public List<VOLavoro3> getDatiLavoro3(String codForScuApp) throws Exception;
	
	public List<VOLavoro4> getDatiLavoro4(String codForScuApp ) throws Exception;

	String dat_ann_sco_ril_format(String dataIn);
}
