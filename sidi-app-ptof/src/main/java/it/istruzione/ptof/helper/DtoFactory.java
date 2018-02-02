package it.istruzione.ptof.helper;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.ConvalidaFabbisognoSingolaScuolaDTO;
import it.istruzione.ptof.beans.FabbisognoDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;
import it.istruzione.ptof.beans.ReportCompletoDTO;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;
import it.istruzione.ptof.beans.documenti.AttivitaSostegnoDTO;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.DocumentoArchivioDTO;
import it.istruzione.ptof.beans.documenti.DocumentoAttivabileDTO;
import it.istruzione.ptof.beans.documenti.DocumentoPubblicazioneDTO;
import it.istruzione.ptof.beans.documenti.IniziativeDidatticheEducativeDTO;
import it.istruzione.ptof.beans.documenti.ObbiettiviFormativiDTO;
import it.istruzione.ptof.model.entity.Tbs1001Gestionecatalogodoc;
import it.istruzione.ptof.model.entity.Tbs1002Gestioneptof;
import it.istruzione.ptof.model.entity.Tbs1009Obiettivo;
import it.istruzione.ptof.model.entity.Tbs1013Altrobiettivo;
import it.istruzione.ptof.model.entity.Tbs1021Ambitoprogettoptof;
import it.istruzione.ptof.model.entity.Tbs1022Ambitoprogettoaltro;
import it.istruzione.ptof.model.entity.Tbs1023Altreinizididattiche;
import it.istruzione.ptof.model.entity.Tbs1024Attivitasostegnoptof;
import it.istruzione.ptof.model.entity.business.Documento;
import it.istruzione.ptof.model.entity.business.Scuola;
import it.istruzione.ptof.service.impl.UtilPtofServiceImpl;

import java.util.Date;

public class DtoFactory {
	
	public static DocumentoAnnoIncorsoDTO getDocumentoAnnoIncorsoDTO(Documento documento){
		
		if (documento == null){
			return null;
		}
		
		DocumentoAnnoIncorsoDTO dto = new DocumentoAnnoIncorsoDTO();
		dto.setAnnoScolastico(documento.getPerAnnSco().toString().substring(0,4)+"/"+documento.getPerAnnSco().toString().substring(4,6));
		dto.setDataFineValidita(documento.getDatFinVal());
		dto.setDataInizioValidita(documento.getDatIniVal());
		dto.setKey(documento.getKey());
		dto.setNomeDocumento(documento.getDesDoc());
		dto.setStatoDocumento((documento.getCodSta()!=null && !documento.getCodSta().isEmpty())?TIPO_STATO_DOC.getInstanceFromCode(documento.getCodSta()):TIPO_STATO_DOC.NON_INSERITO);
		dto.setStatoDocumentoAsString(dto.getStatoDocumento().getBean().getLabel());
		dto.setTriennio(documento.getPerTriRif().toString().substring(0,4)+"/"+documento.getPerTriRif().toString().substring(4,6));
		dto.setVersione(documento.getNumVerDoc().toString());
		
		return dto;
	}

	public static DocumentoArchivioDTO getDocumentoArchivioDTO(Documento documento){
		
		if (documento == null){
			return null;
		}
		
		DocumentoArchivioDTO dto = new DocumentoArchivioDTO();
		dto.setAnnoScolastico(documento.getPerAnnSco().toString().substring(0,4)+"/"+documento.getPerAnnSco().toString().substring(4,6));
		dto.setKey(documento.getKey());
		dto.setNomeDocumento(documento.getDesDoc());
		dto.setStatoDocumento((documento.getCodSta()!=null && !documento.getCodSta().isEmpty())?TIPO_STATO_DOC.getInstanceFromCode(documento.getCodSta()):TIPO_STATO_DOC.NON_INSERITO);
		dto.setStatoDocumentoAsString(dto.getStatoDocumento().getBean().getLabel());
		dto.setTriennio(documento.getPerTriRif().toString().substring(0,4)+"/"+documento.getPerTriRif().toString().substring(4,6));
		dto.setVersione(documento.getNumVerDoc().toString());
		
		return dto;
	}

	public static DocumentoPubblicazioneDTO getDocumentoPubblicazioneDTO(Documento documento){
		
		if (documento == null){
			return null;
		}
		
		DocumentoPubblicazioneDTO dto = new DocumentoPubblicazioneDTO();
		dto.setAnnoScolastico(documento.getPerAnnSco().toString().substring(0,4)+"/"+documento.getPerAnnSco().toString().substring(4,6));
		dto.setKey(documento.getKey());
		dto.setNomeDocumento(documento.getDesDoc());
		dto.setStatoDocumento((documento.getCodSta()!=null && !documento.getCodSta().isEmpty())?TIPO_STATO_DOC.getInstanceFromCode(documento.getCodSta()):TIPO_STATO_DOC.NON_INSERITO);
		dto.setStatoDocumentoAsString(dto.getStatoDocumento().getBean().getLabel());
		dto.setTriennio(documento.getPerTriRif().toString().substring(0,4)+"/"+documento.getPerTriRif().toString().substring(4,6));
		dto.setVersione(documento.getNumVerDoc().toString());
		
		return dto;
	}


	public static ReportCompletoDTO getDocumentoReportCompletoDTO(Tbs1002Gestioneptof in){
		
		if (in == null){
			return null;
		}
		
		ReportCompletoDTO dto = new ReportCompletoDTO();
		dto.setAnnoScolastico(in.getId().getPerAnnSco().toString().substring(0,4)+"/"+in.getId().getPerAnnSco().toString().substring(4,6));
		dto.setKey(UtilPtofServiceImpl.getKeyDocumentoFromTbs1002GestioneptofPK(in.getId()));
		dto.setNomeDocumento(in.getTbs1001Gestionecatalogodoc().getDesDoc());
		dto.setStatoDocumento((in.getTbs1006Tipostato().getCodSta()!=null && !in.getTbs1006Tipostato().getCodSta().isEmpty())?TIPO_STATO_DOC.getInstanceFromCode(in.getTbs1006Tipostato().getCodSta()):TIPO_STATO_DOC.NON_INSERITO);
		dto.setStatoDocumentoAsString(dto.getStatoDocumento().getBean().getLabel());
		dto.setTriennio(in.getTbs1001Gestionecatalogodoc().getPerTriRif().toString().substring(0,4)+"/"+in.getTbs1001Gestionecatalogodoc().getPerTriRif().toString().substring(4,6));
		dto.setVersione(in.getTbs1001Gestionecatalogodoc().getNumVerDoc().toString());
		
		dto.setRegione(in.getMfg1002Anagistscol().getMfg1014Comune().getMfg1029Provnuoist().getMfg1012Regione().getDesReg());
		dto.setSiglaProvincia(in.getMfg1002Anagistscol().getMfg1014Comune().getMfg1029Provnuoist().getCodPrv());
		dto.setComune(in.getMfg1002Anagistscol().getMfg1014Comune().getDesCom());
		dto.setCodiceMecUtente(in.getId().getCodScuUte());
		dto.setDenominazione(in.getMfg1002Anagistscol().getDesNomScu());
		Date dataPubblicazione = null;
		switch (TIPO_STATO_DOC.getInstanceFromCode(in.getTbs1006Tipostato().getCodSta())){
		case PUBBLICATO_PARZIALMENTE :
			dataPubblicazione = in.getDatPubPdfPtoPar();
			break;
		case PUBBLICATO_COMPLETO :
			dataPubblicazione = in.getDatPubPdfPtoCom();
			break;
		case CONVALIDATO :
			dataPubblicazione = in.getDatPdfVisCnv();
			break;
		default:
			throw new RuntimeException("Tipo file non gestito");
		}
		dto.setDataPubblicazione(dataPubblicazione);
		
		return dto;
	}


	public static ConvalidaFabbisognoSingolaScuolaDTO getConvalidaFabbisognoSingolaScuolaDTO(Tbs1002Gestioneptof in){
		
		if (in == null){
			return null;
		}
		
		ConvalidaFabbisognoSingolaScuolaDTO dto = new ConvalidaFabbisognoSingolaScuolaDTO();
		dto.setAnnoScolastico(in.getId().getPerAnnSco().toString().substring(0,4)+"/"+in.getId().getPerAnnSco().toString().substring(4,6));
		dto.setKey(UtilPtofServiceImpl.getKeyDocumentoFromTbs1002GestioneptofPK(in.getId()));
		dto.setNomeDocumento(in.getTbs1001Gestionecatalogodoc().getDesDoc());
		dto.setStatoDocumento((in.getTbs1006Tipostato().getCodSta()!=null && !in.getTbs1006Tipostato().getCodSta().isEmpty())?TIPO_STATO_DOC.getInstanceFromCode(in.getTbs1006Tipostato().getCodSta()):TIPO_STATO_DOC.NON_INSERITO);
		dto.setStatoDocumentoAsString(dto.getStatoDocumento().getBean().getLabel());
		dto.setTriennio(in.getTbs1001Gestionecatalogodoc().getPerTriRif().toString().substring(0,4)+"/"+in.getTbs1001Gestionecatalogodoc().getPerTriRif().toString().substring(4,6));
		dto.setVersione(in.getTbs1001Gestionecatalogodoc().getNumVerDoc().toString());
		
		dto.setRegione(in.getMfg1002Anagistscol().getMfg1014Comune().getMfg1029Provnuoist().getMfg1012Regione().getDesReg());
		dto.setProvincia(in.getMfg1002Anagistscol().getMfg1014Comune().getMfg1029Provnuoist().getDesPrv());
		dto.setComune(in.getMfg1002Anagistscol().getMfg1014Comune().getDesCom());
		dto.setCodiceMecUtente(in.getId().getCodScuUte());
		dto.setDenominazione(in.getMfg1002Anagistscol().getDesNomScu());
		
		
		return dto;
	}

	public static FabbisognoDTO getFabbisognoDTO(Tbs1002Gestioneptof in){
		
		if (in == null){
			return null;
		}
		
		FabbisognoDTO dto = new FabbisognoDTO();
		dto.setAnnoScolastico(in.getId().getPerAnnSco().toString().substring(0,4)+"/"+in.getId().getPerAnnSco().toString().substring(4,6));
		dto.setKey(UtilPtofServiceImpl.getKeyDocumentoFromTbs1002GestioneptofPK(in.getId()));
		dto.setNomeDocumento(in.getTbs1001Gestionecatalogodoc().getDesDoc());
		dto.setStatoDocumento((in.getTbs1006Tipostato().getCodSta()!=null && !in.getTbs1006Tipostato().getCodSta().isEmpty())?TIPO_STATO_DOC.getInstanceFromCode(in.getTbs1006Tipostato().getCodSta()):TIPO_STATO_DOC.NON_INSERITO);
		dto.setStatoDocumentoAsString(dto.getStatoDocumento().getBean().getLabel());
		dto.setTriennio(in.getTbs1001Gestionecatalogodoc().getPerTriRif().toString().substring(0,4)+"/"+in.getTbs1001Gestionecatalogodoc().getPerTriRif().toString().substring(4,6));
		dto.setVersione(in.getTbs1001Gestionecatalogodoc().getNumVerDoc().toString());
		
		dto.setRegione(in.getMfg1002Anagistscol().getMfg1014Comune().getMfg1029Provnuoist().getMfg1012Regione().getDesReg());
		dto.setSiglaProvincia(in.getMfg1002Anagistscol().getMfg1014Comune().getMfg1029Provnuoist().getCodPrv());
		dto.setComune(in.getMfg1002Anagistscol().getMfg1014Comune().getDesCom());
		dto.setCodiceMecUtente(in.getId().getCodScuUte());
		dto.setDenominazione(in.getMfg1002Anagistscol().getDesNomScu());
		
		
		return dto;
	}

	public static IstitutoScolasticoDTO getIstitutoScolasticoDTO(Scuola scuola){
		
		if (scuola == null){
			return null;
		}
		
		IstitutoScolasticoDTO dto = new IstitutoScolasticoDTO();
		dto.setCodiceMecIstPrin(scuola.getId().getCodScuUt());
		dto.setComune(scuola.getDesCom());
		dto.setDenominazione(scuola.getDesNomScu());
		dto.setDirigenteScolastico(scuola.getDirigente());
		dto.setEmail(scuola.getDesIndEml());
		dto.setFax(scuola.getCodFaxScu());
		dto.setIndirizzo(scuola.getDesIndScu());
		dto.setKey(scuola.getId().getCodScuUt());
		dto.setPec(scuola.getDesIndEmaPce());
		dto.setSitoWeb(scuola.getDesIndWeb());
		dto.setTelefono(scuola.getCodTelScu());
		dto.setTipologiaScuola(scuola.getDesTipIst());
		//TODO
		//dto.setNumeroPlessiPerTipologiaScuola(numeroPlessiPerTipologiaScuola);
		//dto.setTipologieScuoleAssociate(tipologieScuoleAssociate);
		
		return dto;
	}
	
	public static PlessiDTO getPlessiDTO(Scuola scuola){
		
		if (scuola == null){
			return null;
		}
		
		PlessiDTO dto = new PlessiDTO();
		dto.setCodiceMecUtente(scuola.getId().getCodScuUt());
		dto.setComune(scuola.getDesCom());
		dto.setKey(scuola.getId().getCodScuUt());
		dto.setDenominazione(scuola.getDesNomScu());
		dto.setEmail(scuola.getDesIndEml());
		dto.setIndirizzo(scuola.getDesIndScu());
		dto.setTelefono(scuola.getCodTelScu());
		dto.setPlessoScuola(scuola.getDesNomScu());
				
		return dto;
	}

	public static ObbiettiviFormativiDTO getObbiettiviFormativiDTO(Tbs1009Obiettivo tbs1009Obiettivo){
		
		if (tbs1009Obiettivo == null){
			return null;
		}
		
		ObbiettiviFormativiDTO obbiettiviFormativiDTO = new ObbiettiviFormativiDTO();
		obbiettiviFormativiDTO.setBenefici(tbs1009Obiettivo.getDesBenAtt());
		obbiettiviFormativiDTO.setFinalita(tbs1009Obiettivo.getDesFin());
		obbiettiviFormativiDTO.setKey(tbs1009Obiettivo.getPrgObi().toString());
		BeanValueDTO obiettivo = new BeanValueDTO();
		obiettivo.setLabel(tbs1009Obiettivo.getTbs1008Tipoobiettivo().getDesTipObi());
		obiettivo.setValue(tbs1009Obiettivo.getTbs1008Tipoobiettivo().getPrgTipObi().toString());
		obbiettiviFormativiDTO.setObbiettivo(obiettivo);
		BeanValueDTO priorita = new BeanValueDTO();
		priorita.setLabel(tbs1009Obiettivo.getTbs1012Tipopriorita().getDesTipPri());
		priorita.setValue(tbs1009Obiettivo.getTbs1012Tipopriorita().getCodTipPri());
		obbiettiviFormativiDTO.setPriorita(priorita);
				
		return obbiettiviFormativiDTO;
	}

	public static ObbiettiviFormativiDTO getObbiettiviFormativiDTO(Tbs1013Altrobiettivo tbs1013Altrobiettivo){
		
		if (tbs1013Altrobiettivo == null){
			return null;
		}
		
		ObbiettiviFormativiDTO obbiettiviFormativiDTO = new ObbiettiviFormativiDTO();
		obbiettiviFormativiDTO.setBenefici(tbs1013Altrobiettivo.getDesBenAtt());
		obbiettiviFormativiDTO.setFinalita(tbs1013Altrobiettivo.getDesFin());
		obbiettiviFormativiDTO.setKey(tbs1013Altrobiettivo.getPrgAltObi().toString());
		BeanValueDTO obiettivo = new BeanValueDTO();
		obiettivo.setLabel(tbs1013Altrobiettivo.getDesAltObi());
		obiettivo.setValue(tbs1013Altrobiettivo.getDesAltObi());
		obbiettiviFormativiDTO.setObbiettivo(obiettivo);
		BeanValueDTO priorita = new BeanValueDTO();
		priorita.setLabel(tbs1013Altrobiettivo.getTbs1012Tipopriorita().getDesTipPri());
		priorita.setValue(tbs1013Altrobiettivo.getTbs1012Tipopriorita().getCodTipPri());
		obbiettiviFormativiDTO.setPriorita(priorita);
				
		return obbiettiviFormativiDTO;
	}

	public static AmbitiProgettiDTO getAmbitiProgettiDTO(Tbs1021Ambitoprogettoptof tbs1021Ambitoprogettoptof){
		
		if (tbs1021Ambitoprogettoptof == null){
			return null;
		}
		
		AmbitiProgettiDTO ambitiProgettiDTO = new AmbitiProgettiDTO();
		ambitiProgettiDTO.setAltreRisorseNecessarie(tbs1021Ambitoprogettoptof.getDesAltRis());
		BeanValueDTO ambito = new BeanValueDTO();
		ambito.setLabel(tbs1021Ambitoprogettoptof.getTbs1020Classifprogetambito().getTbs1019Tipoambito().getDesTipAmb());
		ambito.setValue(tbs1021Ambitoprogettoptof.getTbs1020Classifprogetambito().getTbs1019Tipoambito().getCodTipAmb());
		ambitiProgettiDTO.setAmbito(ambito);
		ambitiProgettiDTO.setAreaTematicaPNSD(tbs1021Ambitoprogettoptof.getCodAreTem());
		ambitiProgettiDTO.setCooperazioneConAltreScuole(tbs1021Ambitoprogettoptof.getDesCooScu());
		ambitiProgettiDTO.setDenominazione(tbs1021Ambitoprogettoptof.getDesDenPgtCur());
		ambitiProgettiDTO.setDestinatari(tbs1021Ambitoprogettoptof.getDesDstPgt());
		ambitiProgettiDTO.setDurataAl(tbs1021Ambitoprogettoptof.getDatFinVal());
		ambitiProgettiDTO.setDurataDal(tbs1021Ambitoprogettoptof.getDatIniVal());
		ambitiProgettiDTO.setKey(tbs1021Ambitoprogettoptof.getPrgAmbPgtPto().toString());
		ambitiProgettiDTO.setModalitApprocciFormativiUtilizzati(tbs1021Ambitoprogettoptof.getDesModForUti());
		ambitiProgettiDTO.setObiettiviAltrePriorita(tbs1021Ambitoprogettoptof.getDesObiPri());
		ambitiProgettiDTO.setPeriodoDiSvolgimento(tbs1021Ambitoprogettoptof.getDesPerSvo());
		ambitiProgettiDTO.setPrincipaliContenuti(tbs1021Ambitoprogettoptof.getDesPrpCnt());
		ambitiProgettiDTO.setRilevanzaPerScuola(tbs1021Ambitoprogettoptof.getDesRilScu());
		ambitiProgettiDTO.setRisorseFinanziareNecessarie(tbs1021Ambitoprogettoptof.getImpRisFin());
		ambitiProgettiDTO.setRisorseUmaneArea(tbs1021Ambitoprogettoptof.getDesRisUma());
		ambitiProgettiDTO.setStatoAvanzamento(tbs1021Ambitoprogettoptof.getDesStaAva());
		BeanValueDTO tipologiaProgetto = new BeanValueDTO();
		tipologiaProgetto.setLabel(tbs1021Ambitoprogettoptof.getTbs1020Classifprogetambito().getDesDenPgt());
		tipologiaProgetto.setValue(tbs1021Ambitoprogettoptof.getTbs1020Classifprogetambito().getId().getPrgPgtAmb().toString());
		ambitiProgettiDTO.setTipologiaProgetto(tipologiaProgetto);
		
		ambitiProgettiDTO.setCancellabile(tbs1021Ambitoprogettoptof.getDesNomBenSer() == null || tbs1021Ambitoprogettoptof.getDesNomBenSer().isEmpty());
		ambitiProgettiDTO.setModificabile(tbs1021Ambitoprogettoptof.getDesNomBenSer() == null || tbs1021Ambitoprogettoptof.getDesNomBenSer().isEmpty());
		ambitiProgettiDTO.setVisualizzabile(true);
				
		return ambitiProgettiDTO;
	}
	
	public static AmbitiProgettiDTO getAmbitiProgettiDTO(Tbs1022Ambitoprogettoaltro tbs1022Ambitoprogettoaltro){
		
		if (tbs1022Ambitoprogettoaltro == null){
			return null;
		}
		
		AmbitiProgettiDTO ambitiProgettiDTO = new AmbitiProgettiDTO();
		ambitiProgettiDTO.setAltreRisorseNecessarie(tbs1022Ambitoprogettoaltro.getDesAltRis());
		BeanValueDTO ambito = new BeanValueDTO();
		ambito.setLabel(tbs1022Ambitoprogettoaltro.getTbs1019Tipoambito().getDesTipAmb());
		ambito.setValue(tbs1022Ambitoprogettoaltro.getTbs1019Tipoambito().getCodTipAmb());
		ambitiProgettiDTO.setAmbito(ambito);
		ambitiProgettiDTO.setAreaTematicaPNSD(tbs1022Ambitoprogettoaltro.getCodAreTem());
		ambitiProgettiDTO.setCooperazioneConAltreScuole(tbs1022Ambitoprogettoaltro.getDesCooScu());
		ambitiProgettiDTO.setDenominazione(tbs1022Ambitoprogettoaltro.getDesDenPgtCur());
		ambitiProgettiDTO.setDestinatari(tbs1022Ambitoprogettoaltro.getDesDstPgt());
		ambitiProgettiDTO.setDurataAl(tbs1022Ambitoprogettoaltro.getDatFinVal());
		ambitiProgettiDTO.setDurataDal(tbs1022Ambitoprogettoaltro.getDatIniVal());
		ambitiProgettiDTO.setKey(tbs1022Ambitoprogettoaltro.getPrgAmbPgtAlt().toString());
		ambitiProgettiDTO.setModalitApprocciFormativiUtilizzati(tbs1022Ambitoprogettoaltro.getDesModForUti());
		ambitiProgettiDTO.setObiettiviAltrePriorita(tbs1022Ambitoprogettoaltro.getDesObiPri());
		ambitiProgettiDTO.setPeriodoDiSvolgimento(tbs1022Ambitoprogettoaltro.getDesPerSvo());
		ambitiProgettiDTO.setPrincipaliContenuti(tbs1022Ambitoprogettoaltro.getDesPrpCnt());
		ambitiProgettiDTO.setRilevanzaPerScuola(tbs1022Ambitoprogettoaltro.getDesRilScu());
		ambitiProgettiDTO.setRisorseFinanziareNecessarie(tbs1022Ambitoprogettoaltro.getImpRisFin());
		ambitiProgettiDTO.setRisorseUmaneArea(tbs1022Ambitoprogettoaltro.getDesRisUma());
		ambitiProgettiDTO.setStatoAvanzamento(tbs1022Ambitoprogettoaltro.getDesStaAva());
		BeanValueDTO tipologiaProgetto = new BeanValueDTO();
		tipologiaProgetto.setLabel(tbs1022Ambitoprogettoaltro.getDesDenPgtAlt());
		tipologiaProgetto.setValue(tbs1022Ambitoprogettoaltro.getDesDenPgtAlt());
		ambitiProgettiDTO.setTipologiaProgetto(tipologiaProgetto);
		
		ambitiProgettiDTO.setCancellabile(tbs1022Ambitoprogettoaltro.getDesNomBenSer() == null || tbs1022Ambitoprogettoaltro.getDesNomBenSer().isEmpty());
		ambitiProgettiDTO.setModificabile(tbs1022Ambitoprogettoaltro.getDesNomBenSer() == null || tbs1022Ambitoprogettoaltro.getDesNomBenSer().isEmpty());

				
		return ambitiProgettiDTO;
	}

	public static IniziativeDidatticheEducativeDTO getIniziativeDidatticheEducativeDTO(Tbs1023Altreinizididattiche tbs1023Altreinizididattiche){
		
		if (tbs1023Altreinizididattiche == null){
			return null;
		}
		
		IniziativeDidatticheEducativeDTO iniziativeDidatticheEducativeDTO = new IniziativeDidatticheEducativeDTO();
		iniziativeDidatticheEducativeDTO.setAreaTematicaPNSD(tbs1023Altreinizididattiche.getDesAreTemPns());
		iniziativeDidatticheEducativeDTO.setContenuti(tbs1023Altreinizididattiche.getDesCnu());
		iniziativeDidatticheEducativeDTO.setDataFine(tbs1023Altreinizididattiche.getDatFinVal());
		iniziativeDidatticheEducativeDTO.setDataInizio(tbs1023Altreinizididattiche.getDatIniVal());
		iniziativeDidatticheEducativeDTO.setKey(tbs1023Altreinizididattiche.getPrgAltIniDid().toString());
		iniziativeDidatticheEducativeDTO.setModalita(tbs1023Altreinizididattiche.getDesMod());
		iniziativeDidatticheEducativeDTO.setNote(tbs1023Altreinizididattiche.getDesNot());
		iniziativeDidatticheEducativeDTO.setObiettivi(tbs1023Altreinizididattiche.getDesObi());
		iniziativeDidatticheEducativeDTO.setTitoli(tbs1023Altreinizididattiche.getDesTit());
				
		return iniziativeDidatticheEducativeDTO;
	}

	public static AttivitaSostegnoDTO getAttivitaSostegnoDTO(Tbs1024Attivitasostegnoptof tbs1024Attivitasostegnoptof){
		
		if (tbs1024Attivitasostegnoptof == null){
			return null;
		}
		
		AttivitaSostegnoDTO attivitaSostegnoDTO = new AttivitaSostegnoDTO();
		attivitaSostegnoDTO.setAspettiSupportoLogistico(tbs1024Attivitasostegnoptof.getDesAspSupLog());
		attivitaSostegnoDTO.setContenutiAttivitaSostegno(tbs1024Attivitasostegnoptof.getDesCntAttSos());
		attivitaSostegnoDTO.setCoopServiziSSAssocSettore(tbs1024Attivitasostegnoptof.getDesCooSssAss());
		attivitaSostegnoDTO.setKey(tbs1024Attivitasostegnoptof.getPrgAttSos().toString());
		attivitaSostegnoDTO.setMetodologieUtilizzate(tbs1024Attivitasostegnoptof.getDesMetUti());
		attivitaSostegnoDTO.setNote(tbs1024Attivitasostegnoptof.getDesNot());
		
		
		
		return attivitaSostegnoDTO;
	}
	
	public static DocumentoAttivabileDTO getDocumentoAttivabiliDTO(Tbs1001Gestionecatalogodoc documento){
		
		if (documento == null){
			return null;
		}
		
		DocumentoAttivabileDTO dto = new DocumentoAttivabileDTO();
		dto.setKey(documento.getPrgGesCatDoc().toString());
		dto.setNomeDocumento(documento.getDesDoc());
		dto.setDataInizioValidita(documento.getDatIniVal());
		dto.setDataFineValidita(documento.getDatFinVal());
		dto.setTriennio(documento.getPerTriRif().toString().substring(0,4)+"/"+documento.getPerTriRif().toString().substring(4,6));
		dto.setVersione(documento.getNumVerDoc().toString());
		
		return dto;
	}
}
