package it.istruzione.ptof.service.mock.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import it.istruzione.ptof.beans.BaseDTO;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.ValidationErrorFiedDTO;
import it.istruzione.ptof.beans.constant.TIPO_COMPONENTE;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;
import it.istruzione.ptof.beans.constant.TIPO_FILE_ACCETTATO;
import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.constant.TIPO_STATO_SEZIONE;
import it.istruzione.ptof.beans.documenti.AlternanzaScuolaLavoroDTO;
import it.istruzione.ptof.beans.documenti.AmbitiProgettiDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiEEDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiIMMIDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneClassiMMIIDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneQuadriOrariDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneQuadriOrariMateriaDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneTempiScuolaDTO;
import it.istruzione.ptof.beans.documenti.ArticolazioneTempiScuolaTipoTempoScuolaDTO;
import it.istruzione.ptof.beans.documenti.AttivitaSostegnoDTO;
import it.istruzione.ptof.beans.documenti.ConvPotenzFormativoDTO;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.DotazioneIstPriDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoAttrezzatureInfraDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoConnessoFormazioneDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoConnessoProgettoDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPersonaleAmmTecAusiDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoEEAADTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiPotenziamentoMMDTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiSostegnoEEAADTO;
import it.istruzione.ptof.beans.documenti.FabbisognoPostiSostegnoMMIIDTO;
import it.istruzione.ptof.beans.documenti.IniziativeDidatticheEducativeDTO;
import it.istruzione.ptof.beans.documenti.ObbiettiviFormativiDTO;
import it.istruzione.ptof.beans.documenti.OrganigrammaDTO;
import it.istruzione.ptof.beans.documenti.PianificazioneInterventiMonitoraggioDTO;
import it.istruzione.ptof.beans.documenti.PianoNazionaleScuolaDigitaleDTO;
import it.istruzione.ptof.beans.documenti.PrioritaTraguardiDTO;
import it.istruzione.ptof.beans.documenti.ProgrammazioneFormDTO;
import it.istruzione.ptof.beans.documenti.PromozioneRapportiEntiTerritorioDTO;
import it.istruzione.ptof.beans.documenti.SezioneBaseDTO;
import it.istruzione.ptof.beans.documenti.SezioneDTO;
import it.istruzione.ptof.beans.documenti.SezioneExtDTO;
import it.istruzione.ptof.beans.documenti.SintesiProcessoDTO;
import it.istruzione.ptof.beans.documenti.StrumentiAttrezzatureTecnologicheDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAllegatoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAlternzaScuolaLavoroDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAltreIniziativeDidaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAltriProgettiCurriculariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiEEDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiMMIDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiMMIIDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneOrariaIndirizziStudioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneQuadriOrariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneTempiScuolaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteAttivitaSostegnoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteBaseListDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteComboBoxDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteConvPotenzFormativoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDatiFinaliScuolaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteDotazioneIstPriDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoAttrezzatureInfraDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoConnessoFormazioneDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoConnessoProgettoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiPotenziamentoMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiSostegnoEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiSostegnoMMIIDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteIstitutoPrincipaleDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiCattedreEEAADTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteFabbisognoPostiCattedreMMDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteMonitoraggioPianificazioneDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteMultiBoxDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviMiglioramentoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariAltriDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteObbiettiviPrioritariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteOrganigrammaAltriRuoliDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteOrganigrammaDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePianificazioneInterventiMonitoraggioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePianoNazionaleScuolaDigitaleDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePlessoDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgettiCurriculariDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormAmmDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteProgrammazioneFormDocDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponentePromozioneRapportiEntiTerritorioDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteStrumentiAttrezzatureTecnologicheDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteTextAreaDTO;
import it.istruzione.ptof.beans.ptof.GestionePtofDTO;
import it.istruzione.ptof.service.GestionePtofService;

/**
 * @author mcatanzaro servizi per RF003– Gestione PTOF
 */
@Service
@Primary
public class GestionePtofMockService implements GestionePtofService {

	@Override
	public DocumentoAnnoIncorsoDTO loadDocumentoAnnoIncorso(String keyDocumento) {
		Populator p = new PopulatorBuilder().build();
		DocumentoAnnoIncorsoDTO populateBean = p.populateBean(DocumentoAnnoIncorsoDTO.class);
		populateBean.setKey("key_doc_001");
		return populateBean;
	}

	@Override
	public LinkedList<SezioneDTO> loadSezioni(GestionePtofDTO gestionePtofDTO) {
		 return testRealistico();
		// return test1();
	}

	private LinkedList<SezioneDTO> testRealistico() {
		Populator p = new PopulatorBuilder().build();
		LinkedList<SezioneDTO> list = new LinkedList<SezioneDTO>();
		SezioneDTO populateBean = getSottoSezione(p, 1, "", 0, 1);
		populateBean.setNome(TIPO_SEZIONE.SEZIONE_01_INDICE.name());
		populateBean.setTipoSezione(TIPO_SEZIONE.SEZIONE_01_INDICE);
		populateBean.setKey(TIPO_SEZIONE.SEZIONE_01_INDICE.name());
		list.add(populateBean);

		
		
		SezioneDTO populateBean2 = getSottoSezione(p, 2, "", 1, 1);
		populateBean2.setNome(TIPO_SEZIONE.SEZIONE_02_FINITER.name());
		populateBean2.setTipoSezione(TIPO_SEZIONE.SEZIONE_02_FINITER);
		populateBean2.setKey(TIPO_SEZIONE.SEZIONE_02_FINITER.name());
		list.add(populateBean2);
 
//		if (true) {
//		for ( SezioneDTO sez : list){
//			sez.setDisabled(false);
//		}
//		return list;
//	}
		
		SezioneDTO populateBean3 = getSottoSezione(p, 3, "", 1, 1);
		populateBean3.setNome(TIPO_SEZIONE.SEZIONE_03_STORIA.name());
		populateBean3.setTipoSezione(TIPO_SEZIONE.SEZIONE_03_STORIA);
		populateBean3.setKey(TIPO_SEZIONE.SEZIONE_03_STORIA.name());
		list.add(populateBean3);
		

		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_04_ORGANIZZAZIONE.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_04_ORGANIZZAZIONE);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_04_ORGANIZZAZIONE.name());
			list.add(populateBean4);
		}

				
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_05_CONTESTO_SOCIOECO.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_05_CONTESTO_SOCIOECO);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_05_CONTESTO_SOCIOECO.name());
			
			LinkedList<SezioneDTO> sottoSezione = new LinkedList<>();
			
			
			{
				SezioneDTO populateBean44 = getSottoSezione(p, 4, "", 1, 1);
				populateBean44.setNome(TIPO_SEZIONE.SEZIONE_06_FABBISOGNI.name());
				populateBean44.setTipoSezione(TIPO_SEZIONE.SEZIONE_06_FABBISOGNI);
				populateBean44.setKey(TIPO_SEZIONE.SEZIONE_06_FABBISOGNI.name());
				sottoSezione.add(populateBean44);
				LinkedList<SezioneDTO> sottoSezione22 = new LinkedList<>();
				populateBean44.setSottoSezione(sottoSezione22);
				
				{
					SezioneDTO populateBean45 = getSottoSezione(p, 4, "", 1, 1);
					populateBean45.setNome(TIPO_SEZIONE.SEZIONE_08_OBBIETTIVI.name());
					populateBean45.setTipoSezione(TIPO_SEZIONE.SEZIONE_08_OBBIETTIVI);
					populateBean45.setKey(TIPO_SEZIONE.SEZIONE_08_OBBIETTIVI.name());
					sottoSezione22.add(populateBean45);
				}
				
			}

			
			
		
			
			populateBean4.setSottoSezione(sottoSezione);
			
			list.add(populateBean4);
		}

		


		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_09_OBBIETTIVIMIGLIORAMENTO.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_09_OBBIETTIVIMIGLIORAMENTO);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_09_OBBIETTIVIMIGLIORAMENTO.name());
			list.add(populateBean4);
		}

		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_10_DISCIPLINE.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_10_DISCIPLINE);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_10_DISCIPLINE.name());
			list.add(populateBean4);
		}
		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_11_PROGETTICV.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_11_PROGETTICV);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_11_PROGETTICV.name());
			list.add(populateBean4);
		}

		
		

		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_12_PROGETTI_EXSTRA.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_12_PROGETTI_EXSTRA);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_12_PROGETTI_EXSTRA.name());
			list.add(populateBean4);
		}
		

		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_13_PROGETTI_ALTRI_PROGETTI_EXTRA_CURRICULARI.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_13_PROGETTI_ALTRI_PROGETTI_EXTRA_CURRICULARI);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_13_PROGETTI_ALTRI_PROGETTI_EXTRA_CURRICULARI.name());
			list.add(populateBean4);
		}


		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_15_ATTIVITA_SOSTEGNO.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_15_ATTIVITA_SOSTEGNO);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_15_ATTIVITA_SOSTEGNO.name());
			list.add(populateBean4);
		}
		
	
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_14_ALTRE_INIZIATIVE_DIDATTICO_EDUCATIVE.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_14_ALTRE_INIZIATIVE_DIDATTICO_EDUCATIVE);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_14_ALTRE_INIZIATIVE_DIDATTICO_EDUCATIVE.name());
			list.add(populateBean4);
		}

		


		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_16_ORGANIZZAZIONE_RISORSE.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_16_ORGANIZZAZIONE_RISORSE);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_16_ORGANIZZAZIONE_RISORSE.name());
			list.add(populateBean4);
		}
	
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_17_ORGANIZZAZIONE_CLASSI.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_17_ORGANIZZAZIONE_CLASSI);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_17_ORGANIZZAZIONE_CLASSI.name());
			list.add(populateBean4);
		}



		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_20_PROGRAMMAZIONE_FORMAZIONE_DOC.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_20_PROGRAMMAZIONE_FORMAZIONE_DOC);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_20_PROGRAMMAZIONE_FORMAZIONE_DOC.name());
			list.add(populateBean4);
		}
		
		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_21_PROGRAMMAZIONE_FORMAZIONE_AMM.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_21_PROGRAMMAZIONE_FORMAZIONE_AMM);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_21_PROGRAMMAZIONE_FORMAZIONE_AMM.name());
			list.add(populateBean4);
		}

		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_22_PROGRAMMAZIONE_FORMAZIONE_TEC.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_22_PROGRAMMAZIONE_FORMAZIONE_TEC);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_22_PROGRAMMAZIONE_FORMAZIONE_TEC.name());
			list.add(populateBean4);
		}

		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_23_PROGRAMMAZIONE_FORMAZIONE_AUS.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_23_PROGRAMMAZIONE_FORMAZIONE_AUS);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_23_PROGRAMMAZIONE_FORMAZIONE_AUS.name());
			list.add(populateBean4);
		}
		

		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_24_ALTRI_INTERVENTI_FORMAZIONE_PERSONALE.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_24_ALTRI_INTERVENTI_FORMAZIONE_PERSONALE);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_24_ALTRI_INTERVENTI_FORMAZIONE_PERSONALE.name());
			list.add(populateBean4);
		}
		

		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_25_STRUMENTI_ATTREZZATURE_TECNOLOGIA.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_25_STRUMENTI_ATTREZZATURE_TECNOLOGIA);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_25_STRUMENTI_ATTREZZATURE_TECNOLOGIA.name());
			list.add(populateBean4);
		}
		


		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_26_COLLABORAZIONI_CON_ENTI_LOCALI_TERRITORIO.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_26_COLLABORAZIONI_CON_ENTI_LOCALI_TERRITORIO);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_26_COLLABORAZIONI_CON_ENTI_LOCALI_TERRITORIO.name());
			list.add(populateBean4);
		}
		

		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_27_PIANIFICAZIONE_INTERVENTI_MONITORAGGIO.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_27_PIANIFICAZIONE_INTERVENTI_MONITORAGGIO);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_27_PIANIFICAZIONE_INTERVENTI_MONITORAGGIO.name());
			list.add(populateBean4);
		}

		
	
		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_29_ARTICOLAZIONE_ORARIA.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_29_ARTICOLAZIONE_ORARIA);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_29_ARTICOLAZIONE_ORARIA.name());
			list.add(populateBean4);
		}

		

		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_34_FABBISOGNO_ATTREZZATURE_INFRASTRUTTURE.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_34_FABBISOGNO_ATTREZZATURE_INFRASTRUTTURE);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_34_FABBISOGNO_ATTREZZATURE_INFRASTRUTTURE.name());
			list.add(populateBean4);
		}
		

		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_30_ALTERNANZA_SCUOLA_LAVORO.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_30_ALTERNANZA_SCUOLA_LAVORO);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_30_ALTERNANZA_SCUOLA_LAVORO.name());
			list.add(populateBean4);
		}

	
		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA.name());
			populateBean4.setModificabile(false);
			populateBean4.setCancellabile(false);
			list.add(populateBean4);
		}
		
		
	
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_33_PIANO_NAZIONALE_SCUOLA_DIGITALE.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_33_PIANO_NAZIONALE_SCUOLA_DIGITALE);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_33_PIANO_NAZIONALE_SCUOLA_DIGITALE.name());
			list.add(populateBean4);
		}
	
		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI);
			populateBean4.setStatoDocumento(getStatoDocumento());
			list.add(populateBean4);
		}
			
		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE.name());
			list.add(populateBean4);
		}
		


		
		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_39_FABBISOGNO_CONNESSO_PROGETTO.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_39_FABBISOGNO_CONNESSO_PROGETTO);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_39_FABBISOGNO_CONNESSO_PROGETTO.name());
			populateBean4.setModificabile(true);
			populateBean4.setVisualizzabile(true);
			populateBean4.setModificabile(true);
			list.add(populateBean4);
		}

		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_40_FABBISOGNO_CONNESSO_FORMAZIONE.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_40_FABBISOGNO_CONNESSO_FORMAZIONE);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_40_FABBISOGNO_CONNESSO_FORMAZIONE.name());
			list.add(populateBean4);
		}

		
		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO.name());
			list.add(populateBean4);
		}

	

		{
			SezioneDTO populateBean4 = getSottoSezione(p, 4, "", 1, 1);
			populateBean4.setNome(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO.name());
			populateBean4.setTipoSezione(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO);
			populateBean4.setKey(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO.name());
			list.add(populateBean4);
		} 
		
		
		
		
		for ( SezioneDTO sez : list){
			sez.setDisabled(false);
		}
		
		return list;
	}

	private LinkedList<SezioneDTO> test1() {
		Populator p = new PopulatorBuilder().build();

		LinkedList<SezioneDTO> list = new LinkedList<SezioneDTO>();
		int key = 0;
		for (int i = 0; i < 2; i++) {
			SezioneDTO populateBean = getSottoSezione(p, key, "", i, 3);

			list.add(populateBean);
		}

		for ( SezioneDTO sez : list){
			sez.setDisabled(false);
		}
		return list;
	}

	public SezioneDTO getSottoSezione(Populator p, int key, String codice, int ii, int liv) {
		if (liv == 0)
			return null;
		SezioneDTO populateBean2 = p.populateBean(SezioneDTO.class, "sottoSezione", "isRamo");
		LinkedList<SezioneDTO> sottoSezione2 = new LinkedList<SezioneDTO>();
		populateBean2.setSottoSezione(sottoSezione2);
		populateBean2.setCodice(codice + "-" + ii);
		populateBean2.setKey(codice + "-" + ii);

		for (int iii = 0; iii < 3; iii++) {
			SezioneDTO populateBean3 = getSottoSezione(p, key, codice + "-" + iii, ii, liv - 1);
			if (null != populateBean3)
				sottoSezione2.add(populateBean3);
		}
		populateBean2.setSottoSezione(sottoSezione2);
		for ( SezioneDTO sez : sottoSezione2){
			sez.setDisabled(false);
		}
		return populateBean2;
	}

	@Override
	public DocumentoAnnoIncorsoDTO loadDocumentoAnnoIncorso(String keyDocumento,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		Populator p = new PopulatorBuilder().build();
		DocumentoAnnoIncorsoDTO populateBean = p.populateBean(DocumentoAnnoIncorsoDTO.class);
		populateBean.setKey("key_doc_001");
		populateBean.setStatoDocumento(getStatoDocumento());
		return populateBean;

	}

	@Override
	public SezioneExtDTO loadSezione(String keySezione, IstitutoScolasticoDTO istitutoScolasticoDTO) {
		Populator p = new PopulatorBuilder().build();
		SezioneExtDTO sesioneEx = p.populateBean(SezioneExtDTO.class, "componenti");
		sesioneEx.setKey(keySezione);
		if (TIPO_SEZIONE.SEZIONE_01_INDICE.name().equalsIgnoreCase(keySezione)) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_01_INDICE);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getComponenteAllegato(true));
			sesioneEx.setComponenti(componenti);
			sesioneEx.setNome(TIPO_SEZIONE.SEZIONE_01_INDICE.name());
			sesioneEx.setObbInsAlmenoUnCom(false);
		} else if (TIPO_SEZIONE.SEZIONE_02_FINITER.name().equalsIgnoreCase(keySezione)) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_02_FINITER);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getTextArea(true));
			sesioneEx.setComponenti(componenti);
			sesioneEx.setNome(TIPO_SEZIONE.SEZIONE_02_FINITER.name());
			sesioneEx.setObbInsAlmenoUnCom(true);

		} else if (TIPO_SEZIONE.SEZIONE_03_STORIA.name().equalsIgnoreCase(keySezione)) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_03_STORIA);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getTextArea(false));
			componenti.add(getComponenteAllegato(false));
			sesioneEx.setObbInsAlmenoUnCom(true);
			sesioneEx.setNome(TIPO_SEZIONE.SEZIONE_03_STORIA.name());
			sesioneEx.setComponenti(componenti);

		} else if (TIPO_SEZIONE.SEZIONE_04_ORGANIZZAZIONE.name().equalsIgnoreCase(keySezione)
				|| TIPO_SEZIONE.SEZIONE_06_FABBISOGNI.name().equalsIgnoreCase(keySezione)) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_03_STORIA);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getComponenteComboBoxDTO(true, "Sono presenti Succursali?"));

			componenti.add(getTextArea(false));
			componenti.add(getComponenteAllegato(false));
			componenti.add(getComponenteIstitutoPrincipaleDTO());
			// TODO INSERIE COMPONENTE DOMANDA
			sesioneEx.setObbInsAlmenoUnCom(false);
			sesioneEx.setComponenti(componenti);
			sesioneEx.setNome(TIPO_SEZIONE.SEZIONE_04_ORGANIZZAZIONE.name());
		} else if (TIPO_SEZIONE.SEZIONE_05_CONTESTO_SOCIOECO.name().equalsIgnoreCase(keySezione)) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_05_CONTESTO_SOCIOECO);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();

			componenti.add(getComponenteMultiBoxDTO(true));
			componenti.add(getTextArea(false));
			componenti.add(getComponenteAllegato(false));
			componenti.add(getComponenteIstitutoPrincipaleDTO());
			// TODO INSERIE COMPONENTE DOMANDA
			sesioneEx.setObbInsAlmenoUnCom(false);
			sesioneEx.setComponenti(componenti);
			sesioneEx.setNome(TIPO_SEZIONE.SEZIONE_05_CONTESTO_SOCIOECO.name());
		} else if (TIPO_SEZIONE.SEZIONE_08_OBBIETTIVI.name().equalsIgnoreCase(keySezione)) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_08_OBBIETTIVI);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getTextArea(false));
			componenti.add(getObbiettivi(true));
			componenti.add(getObbiettiviAltri(true));
				sesioneEx.setObbInsAlmenoUnCom(false);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_09_OBBIETTIVIMIGLIORAMENTO.name().equalsIgnoreCase(keySezione)) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_09_OBBIETTIVIMIGLIORAMENTO);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getTextArea(true));
			componenti.add(getObbiettiviMiglioramento(true));
			

			sesioneEx.setObbInsAlmenoUnCom(false);
			sesioneEx.setComponenti(componenti);
		}  else if (TIPO_SEZIONE.SEZIONE_10_DISCIPLINE.name().equalsIgnoreCase(keySezione)) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_10_DISCIPLINE);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getPlesso(true));
			
			componenti.add(getTextArea(true));
			
			sesioneEx.setObbInsAlmenoUnCom(false);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_11_PROGETTICV.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_11_PROGETTICV);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			
			componenti.add(getPlesso(true));
			componenti.add(getTextArea(false));
			componenti.add(getComponenteAllegato(false));
			componenti.add(getComponenteProgettiCV(true));
					
			sesioneEx.setObbInsAlmenoUnCom(true);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_12_PROGETTI_EXSTRA.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_12_PROGETTI_EXSTRA);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getPlesso(true));
			componenti.add(getTextArea(false));
			componenti.add(getComponenteAllegato(false));
			componenti.add(getComponenteProgettiCV(false));
			
			
			sesioneEx.setObbInsAlmenoUnCom(true);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_13_PROGETTI_ALTRI_PROGETTI_EXTRA_CURRICULARI.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_13_PROGETTI_ALTRI_PROGETTI_EXTRA_CURRICULARI);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getPlesso(true));
			componenti.add(getTextArea(false));
			componenti.add(getComponenteAllegato(false));
			componenti.add(getAltriComponenteProgettiExstra(false));
			
			
			sesioneEx.setObbInsAlmenoUnCom(true);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_14_ALTRE_INIZIATIVE_DIDATTICO_EDUCATIVE.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_14_ALTRE_INIZIATIVE_DIDATTICO_EDUCATIVE);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getPlesso(true));
			componenti.add(getAltreIniziativeDidattiche(false));
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_15_ATTIVITA_SOSTEGNO.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_15_ATTIVITA_SOSTEGNO );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getPlesso(true));
			componenti.add(getAttivitaSostegno(false));
			componenti.add(getComponenteAllegato(false));
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_16_ORGANIZZAZIONE_RISORSE.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_16_ORGANIZZAZIONE_RISORSE );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getComponenteAllegato(false));
			componenti.add(getOrganigramma(false));
			componenti.add(getOrganigrammaAltri(false));
			
			sesioneEx.setComponenti(componenti);
		}  else if (TIPO_SEZIONE.SEZIONE_17_ORGANIZZAZIONE_CLASSI.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_17_ORGANIZZAZIONE_CLASSI );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getTextArea(true));
			componenti.add(getComponenteArticolazioneClassiAA(false));
			
			componenti.add(getComponenteArticolazioneClassiEE(false));
			
			componenti.add(getComponenteArticolazioneClassiMMII(false));
			
			componenti.add(getComponenteArticolazioneClassiMMI(false));
			
			
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_20_PROGRAMMAZIONE_FORMAZIONE_DOC.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_20_PROGRAMMAZIONE_FORMAZIONE_DOC );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			 
			componenti.add(getComponenteProgrammazioneFormazione(false));
			 
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_21_PROGRAMMAZIONE_FORMAZIONE_AMM.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_21_PROGRAMMAZIONE_FORMAZIONE_AMM );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
		 
 			componenti.add(getComponenteProgrammazioneFormazioneAMM(false));
 			sesioneEx.setComponenti(componenti);
		} 
		 else if (TIPO_SEZIONE.SEZIONE_22_PROGRAMMAZIONE_FORMAZIONE_TEC.name().equalsIgnoreCase(keySezione) ) {
				sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_22_PROGRAMMAZIONE_FORMAZIONE_TEC );
				LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			 
	 			componenti.add(getComponenteProgrammazioneFormazioneAMM(false));
	 			sesioneEx.setComponenti(componenti);
			}
		 else if (TIPO_SEZIONE.SEZIONE_23_PROGRAMMAZIONE_FORMAZIONE_AUS.name().equalsIgnoreCase(keySezione) ) {
				sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_23_PROGRAMMAZIONE_FORMAZIONE_AUS );
				LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			 
	 			componenti.add(getComponenteProgrammazioneFormazioneAMM(false));
	 			sesioneEx.setComponenti(componenti);
			}
		 else if (TIPO_SEZIONE.SEZIONE_24_ALTRI_INTERVENTI_FORMAZIONE_PERSONALE.name().equalsIgnoreCase(keySezione) ) {
				sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_24_ALTRI_INTERVENTI_FORMAZIONE_PERSONALE );
				LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			 
	 			componenti.add(getComponenteProgrammazioneFormazioneAMM(false));
	 			sesioneEx.setComponenti(componenti);
			}
		
		else if (TIPO_SEZIONE.SEZIONE_25_STRUMENTI_ATTREZZATURE_TECNOLOGIA.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_25_STRUMENTI_ATTREZZATURE_TECNOLOGIA );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getTextArea(false));
 			componenti.add(getComponenteStrumentiEAttrezzature(false));
 			componenti.add(getComponenteDotazioniMultimediali(false));
 			sesioneEx.setComponenti(componenti);
		}  else if (TIPO_SEZIONE.SEZIONE_26_COLLABORAZIONI_CON_ENTI_LOCALI_TERRITORIO.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_26_COLLABORAZIONI_CON_ENTI_LOCALI_TERRITORIO );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getTextArea(false));
 			componenti.add(getComponentePROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO(false));
 			componenti.add(getComponenteS_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO(false));
 			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_27_PIANIFICAZIONE_INTERVENTI_MONITORAGGIO.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_27_PIANIFICAZIONE_INTERVENTI_MONITORAGGIO );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getTextArea(false));
 			componenti.add(getComponenteSEZIONE_27_PIANIFICAZIONE_INTERVENTI_MONITORAGGIO(false));
 			componenti.add(getComponenteS_MONITORAGGIO_PIANIFICAZIONE(true));
 			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_30_ALTERNANZA_SCUOLA_LAVORO.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_30_ALTERNANZA_SCUOLA_LAVORO);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			
			componenti.add(getPlesso(true));
			componenti.add(getTextArea(false));
			componenti.add(getComponenteAllegato(false));
			componenti.add(getComponenteAlternanzaScuolaLavoro(true));
					
			sesioneEx.setObbInsAlmenoUnCom(true);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			
			componenti.add(getComponenteDatiFinaliScuola(true));
					
			sesioneEx.setObbInsAlmenoUnCom(true);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_33_PIANO_NAZIONALE_SCUOLA_DIGITALE.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_33_PIANO_NAZIONALE_SCUOLA_DIGITALE);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			
			componenti.add(getComponentePianoNazionaleScuolaDigitale(true));
			
			sesioneEx.setObbInsAlmenoUnCom(false);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_29_ARTICOLAZIONE_ORARIA.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_29_ARTICOLAZIONE_ORARIA );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getComponenteSEZIONE_29_ARTICOLAZIONE_ORARIAOrariaIndirizziStudio(false));
			componenti.add(getComponenteSEZIONE_29_ARTICOLAZIONE_ORARIA_QuadriOrari(false));
			componenti.add(getComponenteSEZIONE_29_ARTICOLAZIONE_ORARIA_TempiScuola(false));
 			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_34_FABBISOGNO_ATTREZZATURE_INFRASTRUTTURE.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_34_FABBISOGNO_ATTREZZATURE_INFRASTRUTTURE );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getComponenteSEZIONE_34_fabisogno_attrezzature(false,1));
			componenti.add(getComponenteSEZIONE_34_fabisogno_attrezzature(false,2));
			componenti.add(getComponenteSEZIONE_34_fabisogno_attrezzature(false,3));
			componenti.add(getComponenteSEZIONE_34_fabisogno_attrezzature_altre_info(false));
 			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getComponenteFabbisognoPostiPersonaleAmmTecAusi(false));
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE );
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getComponenteSEZIONE_35_fabisogno_posti_cattedre(false,"EE"));
			componenti.add(getComponenteSEZIONE_35_fabisogno_posti_cattedre(false,"AA"));
			componenti.add(getComponenteSEZIONE_35_fabisogno_posti_cattedreMM(false,"MM1"));
			componenti.add(getComponenteSEZIONE_35_fabisogno_posti_cattedreMM(false,"MM2"));
			
 			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_39_FABBISOGNO_CONNESSO_PROGETTO.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_39_FABBISOGNO_CONNESSO_PROGETTO);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			
			componenti.add(getComponenteFabbisognoConnessoProgetto(true));
			sesioneEx.setObbInsAlmenoUnCom(true);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_40_FABBISOGNO_CONNESSO_FORMAZIONE.name().equalsIgnoreCase(keySezione) ) {

			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_40_FABBISOGNO_CONNESSO_FORMAZIONE);
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			
			componenti.add(getComponenteFabbisognoConnessoFormazione(true));
					
			sesioneEx.setObbInsAlmenoUnCom(true);
			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO );
			
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getComponenteSEZIONE_36_fabisogno_posti_sostegnoEE(false,"EE"));
			componenti.add(getComponenteSEZIONE_36_fabisogno_posti_sostegnoEE(false,"AA"));
			componenti.add(getComponenteSEZIONE_36_fabisogno_posti_sostegnoEE(false,"MM1"));
			componenti.add(getComponenteSEZIONE_36_fabisogno_posti_sostegnoMM(false,"MM2"));
			
 			sesioneEx.setComponenti(componenti);
		} else if (TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO.name().equalsIgnoreCase(keySezione) ) {
			sesioneEx.setTipoSezione(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO );
			
			LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();
			componenti.add(getComponenteSEZIONE_37_fabisogno_posti_potenziamentoEEAA(false,"EE"));
			componenti.add(getComponenteSEZIONE_37_fabisogno_posti_potenziamentoEEAA(false,"AA"));
			componenti.add(getComponenteSEZIONE_37_fabisogno_posti_potenziamentoMM(false,"MM1"));
			componenti.add(getComponenteSEZIONE_37_fabisogno_posti_potenziamentoMM(false,"MM2"));
			
			sesioneEx.setComponenti(componenti);
		}
 
		if ( sesioneEx.getComponenti()!=null ){
			for( ComponenteDTO com : sesioneEx.getComponenti()){
				com.setStatoDocumento(getStatoDocumento());
				com.setStatoSezione(TIPO_STATO_SEZIONE.COMPILATA);
			}
		}
		
		sesioneEx.setStatoDocumento(getStatoDocumento() );
		sesioneEx.setStatoSezione(TIPO_STATO_SEZIONE.COMPILATA);
		return sesioneEx;
	}
	private ComponenteDTO getComponenteSEZIONE_35_fabisogno_posti_cattedreMM(boolean b,String i) {
		ComponenteFabbisognoPostiCattedreMMDTO comp = new ComponenteFabbisognoPostiCattedreMMDTO();
		comp.setNome(TIPO_COMPONENTE.S_FABBISOGNO_POSTI_CATTEDRE_MM.name() + " -> "+i);
		comp.setKey("key_obbMigli.we"+i);
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE);
		comp.setStatoDocumento(getStatoDocumento());
		comp.setVersione(1);
		comp.setLabelAnno0("2016/17");
		comp.setLabelAnno1("2017/18");
		comp.setLabelAnno2("2018/19");
		
		comp.setItems(new LinkedList<FabbisognoPostiCattedreMMDTO>());
		{
			FabbisognoPostiCattedreMMDTO populateBean = p.populateBean(FabbisognoPostiCattedreMMDTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setCattedrePrimoAnnoTriennio(123132);
			populateBean.setCattedreSecondoAnnoTriennio(23);
			populateBean.setCattedreTerzoAnnoTriennio(56);
			comp.getItems().add(populateBean);
		}
		{
			FabbisognoPostiCattedreMMDTO populateBean = p.populateBean(FabbisognoPostiCattedreMMDTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setCattedrePrimoAnnoTriennio(433);
			populateBean.setCattedreSecondoAnnoTriennio(7876);
			populateBean.setCattedreTerzoAnnoTriennio(98);
			comp.getItems().add(populateBean);
		}
		
		
		return comp;
	}
	private ComponenteDTO getComponenteSEZIONE_35_fabisogno_posti_cattedre(boolean b,String i) {
		ComponenteFabbisognoPostiCattedreEEAADTO comp = new ComponenteFabbisognoPostiCattedreEEAADTO();
		comp.setNome(TIPO_COMPONENTE.S_FABBISOGNO_POSTI_CATTEDRE_AA_EE.name() + " -> "+i);
		comp.setKey("key_obbMigli.we"+i);
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<FabbisognoPostiCattedreDTO>());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE);
		comp.setStatoDocumento(getStatoDocumento());
		comp.setVersione(1);
		comp.setLabelAnno0("2016/17");
		comp.setLabelAnno1("2017/18");
		comp.setLabelAnno2("2018/19");
		{
			FabbisognoPostiCattedreDTO populateBean = p.populateBean(FabbisognoPostiCattedreDTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setPostiComuniPrimoAnnoTriennio(12);
			populateBean.setPostiComuniSecondoAnnoTriennio(112);
			populateBean.setPostiComuniTerzoAnnoTriennio(132);
			
			comp.getItems().add(populateBean);
		}
		{
			FabbisognoPostiCattedreDTO populateBean = p.populateBean(FabbisognoPostiCattedreDTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setPostiComuniPrimoAnnoTriennio(62);
			populateBean.setPostiComuniSecondoAnnoTriennio(142);
			populateBean.setPostiComuniTerzoAnnoTriennio(152);
			comp.getItems().add(populateBean);
		}
		
		
		return comp;
	}
	
	private ComponenteDTO getComponenteSEZIONE_34_fabisogno_attrezzature(boolean b,int i) {
		ComponenteFabbisognoAttrezzatureInfraDTO comp = new ComponenteFabbisognoAttrezzatureInfraDTO();
		comp.setNome(TIPO_COMPONENTE.S_FABBISOGNO_ATTREZZATURE_INFRA.name() + " -> "+i);
		comp.setKey("key_obbMigli.we"+i);
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_34_FABBISOGNO_ATTREZZATURE_INFRASTRUTTURE);
		comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<FabbisognoAttrezzatureInfraDTO>());
		comp.getItems().add(p.populateBean(FabbisognoAttrezzatureInfraDTO.class));
		comp.getItems().add(p.populateBean(FabbisognoAttrezzatureInfraDTO.class));
		
		
		return comp;
	}
	
	private ComponenteDTO getComponenteSEZIONE_34_fabisogno_attrezzature_altre_info(boolean b) {
		ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO comp = p.populateBean(ComponenteFabbisognoAttrezzatureInfraAltreInfoDTO.class, "items","isObbligatorio"); 
		comp.setNome(TIPO_COMPONENTE.S_FABBISOGNO_ATTREZZATURE_INFRA_ALTRE_INFO.name());
		comp.setKey("key_obbMigli.we");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		return comp;
	}

	
	private ComponenteDTO getComponenteSEZIONE_29_ARTICOLAZIONE_ORARIAOrariaIndirizziStudio(boolean b) {
		ComponenteArticolazioneOrariaIndirizziStudioDTO comp = new ComponenteArticolazioneOrariaIndirizziStudioDTO();
		comp.setNome(TIPO_COMPONENTE.S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO.name());
		comp.setKey("key_obbMigli");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_29_ARTICOLAZIONE_ORARIA);
		comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<ArticolazioneOrariaIndirizziStudioDTO>());
		comp.getItems().add(p.populateBean(ArticolazioneOrariaIndirizziStudioDTO.class));
		comp.getItems().add(p.populateBean(ArticolazioneOrariaIndirizziStudioDTO.class));
		comp.setNota("nota" + TIPO_COMPONENTE.S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO.name());
		return comp;
	}
	private ComponenteDTO getComponenteSEZIONE_29_ARTICOLAZIONE_ORARIA_QuadriOrari(boolean b) {
		ComponenteArticolazioneQuadriOrariDTO comp = new ComponenteArticolazioneQuadriOrariDTO();
		comp.setNome(TIPO_COMPONENTE.S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO.name());
		comp.setItems(new LinkedList<ArticolazioneQuadriOrariDTO>());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_29_ARTICOLAZIONE_ORARIA);
		comp.setStatoDocumento(getStatoDocumento());
		ArticolazioneQuadriOrariDTO populateBean = p.populateBean(ArticolazioneQuadriOrariDTO.class,"items");
		populateBean.setItems(new LinkedList<ArticolazioneQuadriOrariMateriaDTO>());
		populateBean.getItems().add(p.populateBean(ArticolazioneQuadriOrariMateriaDTO.class,"items"));
		populateBean.getItems().add(p.populateBean(ArticolazioneQuadriOrariMateriaDTO.class,"items"));
		populateBean.getItems().add(p.populateBean(ArticolazioneQuadriOrariMateriaDTO.class,"items"));
		comp.getItems().add(populateBean);
		comp.setNota("nota" + TIPO_COMPONENTE.S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO.name());
		return comp;
	}
	
	private ComponenteDTO getComponenteSEZIONE_29_ARTICOLAZIONE_ORARIA_TempiScuola(boolean b) {
		ComponenteArticolazioneTempiScuolaDTO comp = new ComponenteArticolazioneTempiScuolaDTO();
		comp.setNome(TIPO_COMPONENTE.S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA.name());
		comp.setItems(new LinkedList<ArticolazioneTempiScuolaDTO>());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_29_ARTICOLAZIONE_ORARIA);
		comp.setStatoDocumento(getStatoDocumento());
		
		comp.getItems().add(p.populateBean(ArticolazioneTempiScuolaDTO.class,"items"));
		
		ArticolazioneTempiScuolaDTO populateBean = p.populateBean(ArticolazioneTempiScuolaDTO.class);
		comp.getItems().add(populateBean);
		
		comp.setNota("nota" + TIPO_COMPONENTE.S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA.name());
		return comp;
	}
	
	
	
	private ComponenteDTO getComponenteS_MONITORAGGIO_PIANIFICAZIONE(boolean b) {
		ComponenteMonitoraggioPianificazioneDTO comp = new ComponenteMonitoraggioPianificazioneDTO();
		comp.setNome(TIPO_COMPONENTE.S_MONITORAGGIO_PIANIFICAZIONE.name());
		comp.setKey("key_pianificazione");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setSoluzioniAdottateRispettoPianificazione("setSoluzioniAdottateRispettoPianificazione");
		comp.setModalitaVerifica("setModalitaVerifica");
		
		return comp;
	}
	
	private ComponenteDTO getComponenteSEZIONE_27_PIANIFICAZIONE_INTERVENTI_MONITORAGGIO(boolean b) {
		ComponentePianificazioneInterventiMonitoraggioDTO comp = new ComponentePianificazioneInterventiMonitoraggioDTO();
		comp.setNome(TIPO_COMPONENTE.S_PIANIFICAZIONE_ATTIVITA.name());
		comp.setKey("key_pianificazione");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<PianificazioneInterventiMonitoraggioDTO>());
		comp.getItems().add(p.populateBean(PianificazioneInterventiMonitoraggioDTO.class));
		comp.getItems().add(p.populateBean(PianificazioneInterventiMonitoraggioDTO.class));
		return comp;
	}
	
	private ComponenteDTO getComponenteS_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO(boolean b) {
		ComponenteConvPotenzFormativoDTO comp = new ComponenteConvPotenzFormativoDTO();
		comp.setNome(TIPO_COMPONENTE.S_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO.name());
		comp.setKey("key_obbMigli");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<ConvPotenzFormativoDTO>());
		comp.getItems().add(p.populateBean(ConvPotenzFormativoDTO.class));
		comp.getItems().add(p.populateBean(ConvPotenzFormativoDTO.class));
		return comp;
	}
	
	private ComponenteDTO getComponentePROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO(boolean b) {
		ComponentePromozioneRapportiEntiTerritorioDTO comp = new ComponentePromozioneRapportiEntiTerritorioDTO();
		comp.setNome(TIPO_COMPONENTE.S_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO.name());
		comp.setKey("key_obbMigli");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<PromozioneRapportiEntiTerritorioDTO>());
		comp.getItems().add(p.populateBean(PromozioneRapportiEntiTerritorioDTO.class));
		comp.getItems().add(p.populateBean(PromozioneRapportiEntiTerritorioDTO.class));
		return comp;
	}
	
	private ComponenteDTO getComponenteDotazioniMultimediali(boolean b) {
		ComponenteDotazioneIstPriDTO comp = new ComponenteDotazioneIstPriDTO();
		comp.setNome(TIPO_COMPONENTE.S_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE.name());
		comp.setKey("key_obbMigli");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<DotazioneIstPriDTO>());
		comp.getItems().add(p.populateBean(DotazioneIstPriDTO.class));
		comp.getItems().add(p.populateBean(DotazioneIstPriDTO.class));
		return comp;
	}
	
	private ComponenteDTO getComponenteStrumentiEAttrezzature(boolean b) {
		ComponenteStrumentiAttrezzatureTecnologicheDTO comp = new ComponenteStrumentiAttrezzatureTecnologicheDTO();
		comp.setNome(TIPO_COMPONENTE.S_STRUMENTI_ATTREZZATURE_TECNOLOGIA.name());
		comp.setKey("key_obbMigli");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setTipoComponente(TIPO_COMPONENTE.S_PROGRAMMAZIONE_FORMAZIONE_AMM);
		comp.setItems(new LinkedList<StrumentiAttrezzatureTecnologicheDTO>());
		comp.getItems().add(p.populateBean(StrumentiAttrezzatureTecnologicheDTO.class));
		comp.getItems().add(p.populateBean(StrumentiAttrezzatureTecnologicheDTO.class));
		return comp;
	}
	
	private ComponenteDTO getComponenteProgrammazioneFormazioneAMM(boolean b) {
		ComponenteProgrammazioneFormAmmDTO comp = new ComponenteProgrammazioneFormAmmDTO();
		comp.setNome(TIPO_COMPONENTE.S_PROGRAMMAZIONE_FORMAZIONE_AMM.name());
		comp.setKey("key_obbMigli");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		
		comp.setTipoComponente(TIPO_COMPONENTE.S_PROGRAMMAZIONE_FORMAZIONE_AMM);
		comp.setItems(new LinkedList<ProgrammazioneFormDTO>());
		comp.getItems().add(p.populateBean(ProgrammazioneFormDTO.class));
		comp.getItems().add(p.populateBean(ProgrammazioneFormDTO.class));
		return comp;
	}
	
	
	private ComponenteDTO getComponenteProgrammazioneFormazione(boolean b) {
		ComponenteProgrammazioneFormDocDTO comp = new ComponenteProgrammazioneFormDocDTO();
		comp.setKey("key_obbMigli");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setStatoDocumento(getStatoDocumento());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_20_PROGRAMMAZIONE_FORMAZIONE_DOC);
		
		comp.setNome(TIPO_COMPONENTE.S_PROGRAMMAZIONE_FORMAZIONE_DOC.name());
		comp.setTipoComponente(TIPO_COMPONENTE.S_PROGRAMMAZIONE_FORMAZIONE_DOC);
		comp.setItems(new LinkedList<ProgrammazioneFormDTO>());
		comp.getItems().add(p.populateBean(ProgrammazioneFormDTO.class));
		comp.getItems().add(p.populateBean(ProgrammazioneFormDTO.class));
		return comp;
	}

	public TIPO_STATO_DOC getStatoDocumento() {
		return TIPO_STATO_DOC.IN_COMPILAZIONE;
	}

	Populator p = new PopulatorBuilder().build();
	
	private ComponenteDTO getObbiettiviMiglioramento(boolean b) {
		ComponenteObbiettiviMiglioramentoDTO comp = new ComponenteObbiettiviMiglioramentoDTO();
		comp.setKey("key_obbMigli");
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setTipoComponente(TIPO_COMPONENTE.S_OBBIETTIVI_MIGLIORAMENTO);
        comp.setMotivazioneSceltaPriorita("setMotivazioneSceltaPriorita");
        comp.setPrioritaTraguardi(new LinkedList<PrioritaTraguardiDTO>());
        comp.getPrioritaTraguardi().add(p.populateBean(PrioritaTraguardiDTO.class));
        comp.getPrioritaTraguardi().add(p.populateBean(PrioritaTraguardiDTO.class));
        comp.getPrioritaTraguardi().add(p.populateBean(PrioritaTraguardiDTO.class));
        comp.getPrioritaTraguardi().add(p.populateBean(PrioritaTraguardiDTO.class));
        
        comp.setSintesiProcesso(new LinkedList<SintesiProcessoDTO>());
        comp.getSintesiProcesso().add(p.populateBean(SintesiProcessoDTO.class));
        comp.getSintesiProcesso().add(p.populateBean(SintesiProcessoDTO.class));
        comp.getSintesiProcesso().add(p.populateBean(SintesiProcessoDTO.class));
        comp.getSintesiProcesso().add(p.populateBean(SintesiProcessoDTO.class));
        comp.getSintesiProcesso().add(p.populateBean(SintesiProcessoDTO.class));
        
		
		return comp;
	}

	public ComponenteIstitutoPrincipaleDTO getComponenteIstitutoPrincipaleDTO() {
		Populator p = new PopulatorBuilder().build();
		ComponenteIstitutoPrincipaleDTO istituto = new ComponenteIstitutoPrincipaleDTO();

		IstitutoScolasticoDTO populateBean = p.populateBean(IstitutoScolasticoDTO.class, "file", "isObbligatorio");

		populateBean.setPlessi(new LinkedList<PlessiDTO>());
		populateBean.getPlessi().add(p.populateBean(PlessiDTO.class));
		populateBean.getPlessi().add(p.populateBean(PlessiDTO.class));
		populateBean.getPlessi().add(p.populateBean(PlessiDTO.class));
		populateBean.getPlessi().add(p.populateBean(PlessiDTO.class));
		populateBean.getPlessi().add(p.populateBean(PlessiDTO.class));

		istituto.setIstitutoScolasticoDTO(populateBean);
		istituto.setKey("key_ComponenteIstitutoPrincipaleDTO011");
		istituto.setObbligatorio(false);
		istituto.setTipoComponente(TIPO_COMPONENTE.DATI_ISTITUTO_PRINCIPALE);

		return istituto;
	}

	public ComponenteObbiettiviPrioritariAltriDTO getObbiettiviAltri(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();

		ComponenteObbiettiviPrioritariAltriDTO obbiettivi = p.populateBean(ComponenteObbiettiviPrioritariAltriDTO.class, "items",
				"isObbligatorio");
		obbiettivi.setKey(System.currentTimeMillis()+"_ALTRI");
		obbiettivi.setObbligatorio(obbligatorio);
		obbiettivi.setTipoComponente(TIPO_COMPONENTE.S_OBBIETTIVI_FORMATIVI_ALTRI );
		obbiettivi.setNome(TIPO_COMPONENTE.S_OBBIETTIVI_FORMATIVI_ALTRI.name());
		LinkedList<ObbiettiviFormativiDTO> obbiettiviDaDEc = new LinkedList<ObbiettiviFormativiDTO>();

		{
			ObbiettiviFormativiDTO e = p.populateBean(ObbiettiviFormativiDTO.class);
			e.setKey("1");
			e.setFinalita("finialita");

			e.setObbiettivo(new BeanValueDTO("1", "obbiettivo1 S_OBBIETTIVI_FORMATIVI_ALTRI'"));
			e.setPriorita(new BeanValueDTO("1", "priorita'1 S_OBBIETTIVI_FORMATIVI_ALTRI"));
			obbiettiviDaDEc.add(e);
		}

		
		obbiettivi.setItems(obbiettiviDaDEc);

		return obbiettivi;
	}
	
	public ComponenteObbiettiviPrioritariDTO getObbiettivi(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();

		ComponenteObbiettiviPrioritariDTO obbiettivi = p.populateBean(ComponenteObbiettiviPrioritariDTO.class, "items",
				"isObbligatorio");
		obbiettivi.setKey(System.currentTimeMillis()+"");
		obbiettivi.setObbligatorio(obbligatorio);
		obbiettivi.setTipoComponente(TIPO_COMPONENTE.S_OBBIETTIVI_FORMATIVI);
		obbiettivi.setNome(TIPO_COMPONENTE.S_OBBIETTIVI_FORMATIVI.name());
		LinkedList<ObbiettiviFormativiDTO> obbiettiviDaDEc = new LinkedList<ObbiettiviFormativiDTO>();

		{
			ObbiettiviFormativiDTO e = p.populateBean(ObbiettiviFormativiDTO.class);
			e.setKey("1");
			e.setFinalita("finialita");

			e.setObbiettivo(new BeanValueDTO("1", "obbiettivo1 S_OBBIETTIVI_FORMATIVI'"));
			e.setPriorita(new BeanValueDTO("1", "priorita'1"));
			obbiettiviDaDEc.add(e);
		}

		{
			ObbiettiviFormativiDTO e = new ObbiettiviFormativiDTO();
			e.setKey("2");
			e.setFinalita("finialita");
			e.setObbiettivo(new BeanValueDTO("2", "obbiettivo2'"));
			e.setPriorita(new BeanValueDTO("3", "priorita'3"));
			obbiettiviDaDEc.add(e);
		}

		obbiettivi.setItems(obbiettiviDaDEc);

		return obbiettivi;
	}

	public ComponentePlessoDTO getPlesso(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponentePlessoDTO plesso = p.populateBean(ComponentePlessoDTO.class, "file", "isObbligatorio");
		plesso.setPlesso(p.populateBean(PlessiDTO.class));
		plesso.setTipoComponente(TIPO_COMPONENTE.DATI_PLESSO);
		return plesso;
	}

	
	public ComponenteTextAreaDTO getTextArea(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteTextAreaDTO textArea = p.populateBean(ComponenteTextAreaDTO.class, "file", "isObbligatorio");
		textArea.setKey("000003");
		textArea.setValore(" textArea.setValore testo mock prova testo mock prova  testo mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock prova testo mock prova testo mock prova  testo mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock prova textArea.setValore testo mock prova testo mock prova  testo mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock prova testo mock prova testo mock prova  testo mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock provatesto mock prova");
		
		//String t1= "<table style=\"height: 111px;\" width=\"519\"> <tbody> <tr> <td style=\"width: 165px;\">123</td> <td style=\"width: 166px;\">123</td> <td style=\"width: 166px;\">&nbsp;</td> </tr> <tr> <td style=\"width: 165px;\">&nbsp;</td> <td style=\"width: 166px;\">123</td> <td style=\"width: 166px;\">&nbsp;</td> </tr> <tr> <td style=\"width: 165px;\">&nbsp;</td> <td style=\"width: 166px;\">123</td> <td style=\"width: 166px;\">123sadsafdasdfasd</td> </tr> </tbody> </table> <p>testo mock prova</p> <p>&nbsp;</p> <ol> <li>a</li> <li>v</li> <li>g</li> <li>miaoa</li> </ol>";
		
		//textArea.setValore(t1);
		
		textArea.setObbligatorio(obbligatorio);
		textArea.setTipoComponente(TIPO_COMPONENTE.TEXTEDITOR);
		return textArea;
	}

	public ComponenteComboBoxDTO getComponenteComboBoxDTO(boolean obbligatorio, String text) {
		Populator p = new PopulatorBuilder().build();
		ComponenteComboBoxDTO combo = p.populateBean(ComponenteComboBoxDTO.class, "itemSelezionabili",
				"isObbligatorio");

		combo.setKey(combo.getKey() + "_combo");
		combo.setNome(text);
		combo.setObbligatorio(obbligatorio);
		combo.setTipoComponente(TIPO_COMPONENTE.COMBO_BOX);
		BeanValueDTO t1 = p.populateBean(BeanValueDTO.class);
		combo.setSelected(new BeanValueDTO("1", "SI"));
		LinkedList<BeanValueDTO> item = new LinkedList<>();
		item.add(p.populateBean(BeanValueDTO.class));
		item.add(t1);
		item.add(p.populateBean(BeanValueDTO.class));

		combo.setItemSelezionabili(item);

		return combo;
	}

	public ComponenteMultiBoxDTO getComponenteMultiBoxDTO(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteMultiBoxDTO multiBox = p.populateBean(ComponenteMultiBoxDTO.class, "comboBox", "isObbligatorio");

		multiBox.setKey("0000234_mluti");
		multiBox.setNome("Quadro di sintesi contesto");
		multiBox.setDescrizione("Quadro di sintesi contesto");
		multiBox.setObbligatorio(obbligatorio);
		multiBox.setTipoComponente(TIPO_COMPONENTE.MULTI_BOX);

		LinkedList<ComponenteComboBoxDTO> comboBox = new LinkedList<ComponenteComboBoxDTO>();
		comboBox.add(getComponenteComboBoxDTO(true, "Tipologia di aggregazione Contesto"));
		comboBox.add(getComponenteComboBoxDTO(true, "Livello di Multiculturalita'"));
		multiBox.setComboBox(comboBox);
		return multiBox;
	}
	public ComponenteDTO getAltreIniziativeDidattiche(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteAltreIniziativeDidaDTO cv = p.populateBean(ComponenteAltreIniziativeDidaDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setItems(new LinkedList<IniziativeDidatticheEducativeDTO>());
		cv.getItems().add(p.populateBean(IniziativeDidatticheEducativeDTO.class));
		cv.getItems().add(p.populateBean(IniziativeDidatticheEducativeDTO.class));
    	
		return cv;
	}
	
	public ComponenteDTO getComponenteArticolazioneClassiEE( boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteArticolazioneClassiEEDTO cv = p.populateBean(ComponenteArticolazioneClassiEEDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setItems(new LinkedList<ArticolazioneClassiEEDTO>());
		cv.getItems().add(p.populateBean(ArticolazioneClassiEEDTO.class));
		cv.getItems().add(p.populateBean(ArticolazioneClassiEEDTO.class));
		cv.setNome("EE-Primarie");	
		return cv;
	}
	
	
	public ComponenteDTO getComponenteArticolazioneClassiMMI( boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteArticolazioneClassiMMIDTO cv = p.populateBean(ComponenteArticolazioneClassiMMIDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setNome("MMI");
		cv.setObbligatorio(obbligatorio);
		cv.setItems(new LinkedList<ArticolazioneClassiIMMIDTO>());
		 
		ArticolazioneClassiIMMIDTO populateBean = p.populateBean(ArticolazioneClassiIMMIDTO.class,"datiLingua");
		populateBean.setDatiLingua(new LinkedList<String>());
		populateBean.getDatiLingua().add("l1");
		populateBean.getDatiLingua().add("l2");
		
		cv.getItems().add(populateBean);
		 
    	
		return cv;
	}
	
	public ComponenteDTO getComponenteArticolazioneClassiMMII( boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteArticolazioneClassiMMIIDTO cv = p.populateBean(ComponenteArticolazioneClassiMMIIDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setNome("MMII");
		cv.setObbligatorio(obbligatorio);
		cv.setItems(new LinkedList<ArticolazioneClassiMMIIDTO>());
		cv.getItems().add(p.populateBean(ArticolazioneClassiMMIIDTO.class,"datiLingua"));
		cv.getItems().add(p.populateBean(ArticolazioneClassiMMIIDTO.class,"datiLingua"));
		
		ArticolazioneClassiMMIIDTO populateBean = p.populateBean(ArticolazioneClassiMMIIDTO.class,"datiLingua");
		populateBean.setDatiLingua(new LinkedList<String>());
		populateBean.getDatiLingua().add("l1");
		populateBean.getDatiLingua().add("l2");
		
		cv.getItems().add(populateBean);
    	
		return cv;
	}
	
	public ComponenteDTO getComponenteArticolazioneClassiAA( boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteArticolazioneClassiAADTO cv = p.populateBean(ComponenteArticolazioneClassiAADTO.class, "file", "isObbligatorio","items");
		cv.setNome("AA-infanzia");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setItems(new LinkedList<ArticolazioneClassiAADTO>());
		cv.getItems().add(p.populateBean(ArticolazioneClassiAADTO.class));
		cv.getItems().add(p.populateBean(ArticolazioneClassiAADTO.class));
    	
		return cv;
	}
	
 
	
	public ComponenteDTO getOrganigramma(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteOrganigrammaDTO cv = p.populateBean(ComponenteOrganigrammaDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setNome(cv.getTipoComponente().name());
		cv.setObbligatorio(obbligatorio);
		cv.setItems(new LinkedList<OrganigrammaDTO>());
		cv.getItems().add(p.populateBean(OrganigrammaDTO.class));
		cv.getItems().add(p.populateBean(OrganigrammaDTO.class));
    	
		return cv;
	}
	
	public ComponenteDTO getOrganigrammaAltri(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteOrganigrammaAltriRuoliDTO cv = p.populateBean(ComponenteOrganigrammaAltriRuoliDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setNome(cv.getTipoComponente().name());
		cv.setObbligatorio(obbligatorio);
		cv.setItems(new LinkedList<OrganigrammaDTO>());
		cv.getItems().add(p.populateBean(OrganigrammaDTO.class));
		cv.getItems().add(p.populateBean(OrganigrammaDTO.class));
    	
		return cv;
	}
	public ComponenteDTO getAttivitaSostegno(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteAttivitaSostegnoDTO cv = p.populateBean(ComponenteAttivitaSostegnoDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setItems(new LinkedList<AttivitaSostegnoDTO>());
		cv.getItems().add(p.populateBean(AttivitaSostegnoDTO.class));
		cv.getItems().add(p.populateBean(AttivitaSostegnoDTO.class));
    	
		return cv;
	}

	public ComponenteDTO getAltriComponenteProgettiExstra(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteAltriProgettiCurriculariDTO cv = p.populateBean(ComponenteAltriProgettiCurriculariDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setTipoComponente(TIPO_COMPONENTE.S_ALTRI_PROGETTI_CURRICULARI_EXSTRA);
		cv.setItems(new LinkedList<AmbitiProgettiDTO>());
		cv.getItems().add(p.populateBean(AmbitiProgettiDTO.class));
		cv.getItems().add(p.populateBean(AmbitiProgettiDTO.class));
    	
		return cv;
	}
	
	public ComponenteDTO getComponenteProgettiCV(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteProgettiCurriculariDTO cv = p.populateBean(ComponenteProgettiCurriculariDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setTipoComponente(TIPO_COMPONENTE.S_PROGETTI_CURRICULARI);
		cv.setItems(new LinkedList<AmbitiProgettiDTO>());
		cv.getItems().add(p.populateBean(AmbitiProgettiDTO.class));
		cv.getItems().add(p.populateBean(AmbitiProgettiDTO.class));
    	
		return cv;
	}
	
	
	public ComponenteDTO getComponenteAllegato(boolean isJpeg) {
		Populator p = new PopulatorBuilder().build();
		ComponenteAllegatoDTO allegato = p.populateBean(ComponenteAllegatoDTO.class, "file", "isObbligatorio");
		allegato.setKey("000001");
		allegato.setObbligatorio(false);
		allegato.setTipoFileAccettato(isJpeg ? TIPO_FILE_ACCETTATO.JPEG : TIPO_FILE_ACCETTATO.PDF);
		FileDTO file = new FileDTO();
		file.setFileName("miaaoo.pdf");
		try {
 			if ( !isJpeg ) {
			 file.setFile(new FileInputStream(new File("C:/reportOUT/in/testPage.pdf")));
			} else {
			 file.setFile(new FileInputStream(new File("C:/reportOUT/in/testImg.jpeg")));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allegato.setFile(file);
		allegato.setTipoComponente(TIPO_COMPONENTE.ALLEGATO);
		LinkedList<ComponenteDTO> componenti = new LinkedList<ComponenteDTO>();

		return allegato;
	}

	public ComponenteDTO getComponenteAlternanzaScuolaLavoro(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteAlternzaScuolaLavoroDTO cv = p.populateBean(ComponenteAlternzaScuolaLavoroDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setTipoComponente(TIPO_COMPONENTE.S_ALTERNANZA_SCUOLA_LAVORO);
		cv.setItems(new LinkedList<AlternanzaScuolaLavoroDTO>());
		cv.getItems().add(p.populateBean(AlternanzaScuolaLavoroDTO.class));
		cv.getItems().add(p.populateBean(AlternanzaScuolaLavoroDTO.class));
		cv.getItems().add(p.populateBean(AlternanzaScuolaLavoroDTO.class));
    	
		return cv;
	}
	
	public ComponenteDTO getComponenteDatiFinaliScuola(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteDatiFinaliScuolaDTO cv = p.populateBean(ComponenteDatiFinaliScuolaDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setTipoComponente(TIPO_COMPONENTE.S_DATI_FINALI_SCUOLA);
		
		return cv;
	}
	
	public ComponenteDTO getComponentePianoNazionaleScuolaDigitale(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponentePianoNazionaleScuolaDigitaleDTO cv = p.populateBean(ComponentePianoNazionaleScuolaDigitaleDTO.class, "file", "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setTipoComponente(TIPO_COMPONENTE.S_PIANO_NAZIONALE_SCUOLA_DIGITALE);
		cv.setItems(new LinkedList<PianoNazionaleScuolaDigitaleDTO>());
		cv.getItems().add(p.populateBean(PianoNazionaleScuolaDigitaleDTO.class));
		cv.getItems().add(p.populateBean(PianoNazionaleScuolaDigitaleDTO.class));
		cv.getItems().add(p.populateBean(PianoNazionaleScuolaDigitaleDTO.class));
    	
		return cv;
	}
	
	private ComponenteDTO getComponenteFabbisognoPostiPersonaleAmmTecAusi(boolean obbligatorio) {
		ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO comp = new ComponenteFabbisognoPostiPersonaleAmmTecAusiDTO();
		comp.setNome(TIPO_COMPONENTE.S_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI.name());
		comp.setKey("000001");
		comp.setObbligatorio(obbligatorio);
		comp.setItems(new LinkedList<FabbisognoPostiPersonaleAmmTecAusiDTO>());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI);
		comp.setStatoDocumento(getStatoDocumento());
		comp.setVersione(1);
		comp.setLabelAnno0("2016/17");
		comp.setLabelAnno1("2017/18");
		comp.setLabelAnno2("2018/19");
		
		FabbisognoPostiPersonaleAmmTecAusiDTO fabbisognoPostiPersonaleAmmTecAusiDTO_1 = p.populateBean(FabbisognoPostiPersonaleAmmTecAusiDTO.class);
		fabbisognoPostiPersonaleAmmTecAusiDTO_1.setNumeroPostiPrimoAnnoTriennio(123);
		fabbisognoPostiPersonaleAmmTecAusiDTO_1.setNumeroPostiSecondoAnnoTriennio(73);
		fabbisognoPostiPersonaleAmmTecAusiDTO_1.setNumeroPostiTerzoAnnoTriennio(23);
		fabbisognoPostiPersonaleAmmTecAusiDTO_1.setModificabile(true);
		fabbisognoPostiPersonaleAmmTecAusiDTO_1.setCancellabile(true);
		fabbisognoPostiPersonaleAmmTecAusiDTO_1.setVisualizzabile(true);
		
		comp.getItems().add(fabbisognoPostiPersonaleAmmTecAusiDTO_1);
		FabbisognoPostiPersonaleAmmTecAusiDTO fabbisognoPostiPersonaleAmmTecAusiDTO_2 = p.populateBean(FabbisognoPostiPersonaleAmmTecAusiDTO.class);
		fabbisognoPostiPersonaleAmmTecAusiDTO_2.setNumeroPostiPrimoAnnoTriennio(432);
		fabbisognoPostiPersonaleAmmTecAusiDTO_2.setNumeroPostiSecondoAnnoTriennio(98);
		fabbisognoPostiPersonaleAmmTecAusiDTO_2.setNumeroPostiTerzoAnnoTriennio(543);
		fabbisognoPostiPersonaleAmmTecAusiDTO_2.setModificabile(false);
		fabbisognoPostiPersonaleAmmTecAusiDTO_2.setCancellabile(false);
		fabbisognoPostiPersonaleAmmTecAusiDTO_2.setVisualizzabile(false);
		comp.getItems().add(fabbisognoPostiPersonaleAmmTecAusiDTO_2);
		
		
		return comp;
	}
	
	public ComponenteDTO getComponenteFabbisognoConnessoProgetto(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteFabbisognoConnessoProgettoDTO cv = p.populateBean(ComponenteFabbisognoConnessoProgettoDTO.class, "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setTipoComponente(TIPO_COMPONENTE.S_FABBISOGNO_CONNESSO_PROGETTO);
		cv.setItems(new LinkedList<FabbisognoConnessoProgettoDTO>());
		cv.setItemsAggiungibili(true);
		FabbisognoConnessoProgettoDTO fabbisognoConnessoProgettoDTO_1 = p.populateBean(FabbisognoConnessoProgettoDTO.class, "numeroDocentiATACoinvolti");
		fabbisognoConnessoProgettoDTO_1.setNumeroDocentiATACoinvolti(15);
		fabbisognoConnessoProgettoDTO_1.setModificabile(true);
		fabbisognoConnessoProgettoDTO_1.setVisualizzabile(true);
		fabbisognoConnessoProgettoDTO_1.setCancellabile(true);
		cv.getItems().add(fabbisognoConnessoProgettoDTO_1);
		
		FabbisognoConnessoProgettoDTO fabbisognoConnessoProgettoDTO_2 = p.populateBean(FabbisognoConnessoProgettoDTO.class, "numeroDocentiATACoinvolti");
		fabbisognoConnessoProgettoDTO_2.setNumeroDocentiATACoinvolti(4);
		
		cv.getItems().add(fabbisognoConnessoProgettoDTO_2);
    	
		return cv;
	}
	
	public ComponenteDTO getComponenteFabbisognoConnessoFormazione(boolean obbligatorio) {
		Populator p = new PopulatorBuilder().build();
		ComponenteFabbisognoConnessoFormazioneDTO cv = p.populateBean(ComponenteFabbisognoConnessoFormazioneDTO.class, "isObbligatorio","items");
		cv.setKey("000001");
		cv.setObbligatorio(obbligatorio);
		cv.setTipoComponente(TIPO_COMPONENTE.S_FABBISOGNO_CONNESSO_FORMAZIONE);
		cv.setItems(new LinkedList<FabbisognoConnessoFormazioneDTO>());
		
		FabbisognoConnessoFormazioneDTO fabbisognoConnessoFormazioneDTO_1 = p.populateBean(FabbisognoConnessoFormazioneDTO.class, "numeroDocentiATACoinvolti");
		fabbisognoConnessoFormazioneDTO_1.setNumeroDocentiATACoinvolti(15);
		cv.getItems().add(fabbisognoConnessoFormazioneDTO_1);
		
		FabbisognoConnessoFormazioneDTO fabbisognoConnessoFormazioneDTO_2 = p.populateBean(FabbisognoConnessoFormazioneDTO.class, "numeroDocentiATACoinvolti");
		fabbisognoConnessoFormazioneDTO_2.setNumeroDocentiATACoinvolti(3);
		cv.getItems().add(fabbisognoConnessoFormazioneDTO_2);
    	
		return cv;
	}
	
	private ComponenteDTO getComponenteSEZIONE_36_fabisogno_posti_sostegnoEE(boolean b,String i) {
		ComponenteFabbisognoPostiSostegnoEEAADTO comp = new ComponenteFabbisognoPostiSostegnoEEAADTO();
		comp.setNome(TIPO_COMPONENTE.S_FABBISOGNO_POSTI_SOSTEGNO_AA_EE_MMI.name() + " -> "+i);
		comp.setKey("key_obbMigli.we"+i);
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO);
		comp.setStatoDocumento(getStatoDocumento());
		comp.setVersione(2);
		comp.setLabelAnno0("2016/17");
		comp.setLabelAnno1("2017/18");
		comp.setLabelAnno2("2018/19");
		
		comp.setItems(new LinkedList<FabbisognoPostiSostegnoEEAADTO>());
		{
			FabbisognoPostiSostegnoEEAADTO populateBean = p.populateBean(FabbisognoPostiSostegnoEEAADTO.class);
			populateBean.setVisualizzabile(true);
			
			populateBean.setPostiSostegnoUditoPrimoTriennio(23);
			populateBean.setPostiSostegnoUditoSecondoTriennio(12);
			populateBean.setPostiSostegnoUditoTerzoTriennio(123);
			populateBean.setPostiSostegnoVistaPrimoTriennio(24545);
			populateBean.setPostiSostegnoVistaSecondoTriennio(45);
			populateBean.setPostiSostegnoVistaTerzoTriennio(123);
			populateBean.setPostiSostegnoPsicofisicoPrimoTriennio(12);
			populateBean.setPostiSostegnoPsicofisicoSecondoTriennio(1212);
			populateBean.setPostiSostegnoPsicofisicoTerzoTriennio(412);
			
			comp.getItems().add(populateBean);
		}
		{
			FabbisognoPostiSostegnoEEAADTO populateBean = p.populateBean(FabbisognoPostiSostegnoEEAADTO.class);
			populateBean.setVisualizzabile(true);
			
			populateBean.setPostiSostegnoUditoPrimoTriennio(12);
			populateBean.setPostiSostegnoUditoSecondoTriennio(122);
			populateBean.setPostiSostegnoUditoTerzoTriennio(1236);
			populateBean.setPostiSostegnoVistaPrimoTriennio(12);
			populateBean.setPostiSostegnoVistaSecondoTriennio(67);
			populateBean.setPostiSostegnoVistaTerzoTriennio(9);
			populateBean.setPostiSostegnoPsicofisicoPrimoTriennio(65);
			populateBean.setPostiSostegnoPsicofisicoSecondoTriennio(789);
			populateBean.setPostiSostegnoPsicofisicoTerzoTriennio(412);
			comp.getItems().add(populateBean);
		}
 		return comp;
	}
	
	private ComponenteDTO getComponenteSEZIONE_36_fabisogno_posti_sostegnoMM(boolean b,String i) {
		ComponenteFabbisognoPostiSostegnoMMIIDTO comp = new ComponenteFabbisognoPostiSostegnoMMIIDTO();
		comp.setNome(TIPO_COMPONENTE.S_FABBISOGNO_POSTI_SOSTEGNO_MMII.name() + " -> "+i);
		comp.setKey("key_obbMigli.we"+i);
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO);
		comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<FabbisognoPostiSostegnoMMIIDTO>());
		comp.setVersione(2);
		comp.setLabelAnno0("2016/17");
		comp.setLabelAnno1("2017/18");
		comp.setLabelAnno2("2018/19");
		{
			FabbisognoPostiSostegnoMMIIDTO populateBean = p.populateBean(FabbisognoPostiSostegnoMMIIDTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setNumeroPostiPrimoAnnoTriennio(121234);
			populateBean.setNumeroPostiSecondoAnnoTriennio(43);
			populateBean.setNumeroPostiTerzoAnnoTriennio(887);
			comp.getItems().add(populateBean);
		}
		{
			FabbisognoPostiSostegnoMMIIDTO populateBean = p.populateBean(FabbisognoPostiSostegnoMMIIDTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setNumeroPostiPrimoAnnoTriennio(35566);
			populateBean.setNumeroPostiSecondoAnnoTriennio(35566);
			populateBean.setNumeroPostiTerzoAnnoTriennio(35566);
			comp.getItems().add(populateBean);
		}
 		return comp;
	}
	
	private ComponenteDTO getComponenteSEZIONE_37_fabisogno_posti_potenziamentoEEAA(boolean b,String i) {
		ComponenteFabbisognoPostiPotenziamentoEEAADTO comp = new ComponenteFabbisognoPostiPotenziamentoEEAADTO();
		comp.setNome(TIPO_COMPONENTE.S_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE.name() + " -> "+i);
		comp.setKey("key_obbMigli.we"+i);
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<FabbisognoPostiPotenziamentoEEAADTO>());
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO);
		comp.setStatoDocumento(getStatoDocumento());
		
		comp.setVersione(1);
		comp.setLabelAnno0("2016/17");
		comp.setLabelAnno1("2017/18");
		comp.setLabelAnno2("2018/19");
		
		{
			FabbisognoPostiPotenziamentoEEAADTO populateBean = p.populateBean(FabbisognoPostiPotenziamentoEEAADTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setPostiComuniPrimoAnno(123);
			populateBean.setPostiComuniSecondoAnno(763);
			populateBean.setPostiComuniTerzoAnno(43);
			populateBean.setPostiSostegnoPrimoAnno(13);
			populateBean.setPostiSostegnoSecondoAnno(103);
			populateBean.setPostiSostegnoTerzoAnno(1093);
			comp.getItems().add(populateBean);
		}
		{
			FabbisognoPostiPotenziamentoEEAADTO populateBean = p.populateBean(FabbisognoPostiPotenziamentoEEAADTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setPostiComuniPrimoAnno(42);
			populateBean.setPostiComuniSecondoAnno(678);
			populateBean.setPostiComuniTerzoAnno(98);
			populateBean.setPostiSostegnoPrimoAnno(54);
			populateBean.setPostiSostegnoSecondoAnno(43);
			populateBean.setPostiSostegnoTerzoAnno(23);
			comp.getItems().add(populateBean);
		}
 		return comp;
	}
	
	private ComponenteDTO getComponenteSEZIONE_37_fabisogno_posti_potenziamentoMM(boolean b,String i) {
		ComponenteFabbisognoPostiPotenziamentoMMDTO comp = new ComponenteFabbisognoPostiPotenziamentoMMDTO();
		comp.setNome(TIPO_COMPONENTE.S_FABBISOGNO_POSTI_POTENZIAMENTO_MM.name() + " -> "+i);
		comp.setKey("key_obbMigli.we"+i);
		comp.setObbligatorio(true);comp.setStatoDocumento(getStatoDocumento());
		comp.setItems(new LinkedList<FabbisognoPostiPotenziamentoMMDTO>());
		
		comp.setTipoSezione(TIPO_SEZIONE.SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO);
		comp.setStatoDocumento(getStatoDocumento());
		
		comp.setVersione(1);
		comp.setLabelAnno0("2016/17");
		comp.setLabelAnno1("2017/18");
		comp.setLabelAnno2("2018/19");
		{
			FabbisognoPostiPotenziamentoMMDTO populateBean = p.populateBean(FabbisognoPostiPotenziamentoMMDTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setPostiPotenziamentoPrimoAnno(32);
			populateBean.setPostiPotenziamentoSecondoAnno(432);
			populateBean.setPostiPotenziamentoTerzoAnno(532);
			comp.getItems().add(populateBean);
		}
		{
			FabbisognoPostiPotenziamentoMMDTO populateBean = p.populateBean(FabbisognoPostiPotenziamentoMMDTO.class);
			populateBean.setVisualizzabile(true);
			populateBean.setPostiPotenziamentoPrimoAnno(11);
			populateBean.setPostiPotenziamentoSecondoAnno(22);
			populateBean.setPostiPotenziamentoTerzoAnno(33);
			comp.getItems().add(populateBean);
		}
 		return comp;
	}
	
	@Override
	public void saveSezione(SezioneExtDTO sezione, IstitutoScolasticoDTO istitutoScolasticoDTO) {
		// TODO Auto-generated method stub
		// int i=1/0;
		int i=0;
		i++;
	}

	@Override
	public void deleteSezione(SezioneExtDTO sezione, IstitutoScolasticoDTO istitutoScolasticoDTO) {
		// TODO Auto-generated method stub
		int i=0;
		i++;
	}

	@Override
	public void saveSezione(SezioneExtDTO sezione, IstitutoScolasticoDTO istitutoScolasticoDTO,
			TIPO_STATO_SEZIONE statoSezione) {
		// TODO Auto-generated method stub
		int i=0;
		i++; 
	}

	@Override
	public void deleteComponentiInSezione(SezioneBaseDTO sezione, LinkedList<ComponenteDTO> componeti,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteComponentiInSezione(SezioneBaseDTO sezione, LinkedList<ComponenteDTO> componeti,
			IstitutoScolasticoDTO istitutoScolasticoDTO, TIPO_STATO_SEZIONE statoSezione) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteItemsInComponenteInSezione(SezioneBaseDTO sezione, ComponenteDTO componete,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public ComponenteDTO saveItemsInComponenteInSezione(SezioneBaseDTO sezione, ComponenteDTO componete,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		Populator p = new PopulatorBuilder().build();
		 
		if (componete.getTipoComponente().compareTo(TIPO_COMPONENTE.S_OBBIETTIVI_FORMATIVI) == 0) {
			ComponenteObbiettiviPrioritariDTO componeteP = (ComponenteObbiettiviPrioritariDTO) componete;
			
			componeteP.getItems().getFirst().setKey(System.currentTimeMillis()+"");
			return componeteP;
		}
		

		return componete;
		// TODO Auto-generated method stub

	}

	@Override
	public ComponenteDTO saveComponenteInSezione(SezioneBaseDTO sezione, ComponenteDTO componente,
			IstitutoScolasticoDTO istitutoScolasticoDTO, TIPO_STATO_SEZIONE statoSezione) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO<Boolean> validaECambiaStatoPtof(String keyDocumento,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		// TODO Auto-generated method stub
		
		ResponseDTO<Boolean> out = new ResponseDTO<>();
		out.setResult(false);
		ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
		validationErrorDTO.setMessage("E’ obbligatorio compilare tutti i dati delle sezioni/sottosezioni presenti nella lista delle sezioni contraddistinte come obbligatorie");
		validationErrorDTO.setKey("01");
		validationErrorDTO.setTipoErrore(TIPO_ERROR.BLOCCANTE);
		validationErrorDTO.setFieldErrors(new LinkedList<ValidationErrorFiedDTO>());
		validationErrorDTO.getFieldErrors().add( p.populateBean(ValidationErrorFiedDTO.class));
		validationErrorDTO.getFieldErrors().add( p.populateBean(ValidationErrorFiedDTO.class));
		validationErrorDTO.getFieldErrors().add( p.populateBean(ValidationErrorFiedDTO.class));
		//out.setValidationError(validationErrorDTO);
		return out;
	}

	@Override
	public ResponseDTO<Boolean> setAnnullaConvalidaPtof(String keyDocumento,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		ResponseDTO<Boolean> out = new ResponseDTO<>();
		out.setResult(false);
		return out;
	}

}
