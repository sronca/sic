package it.istruzione.ptof.service.mock.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.constant.TIPO_FILE_PDF;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
import it.istruzione.ptof.beans.constant.TIPO_STATO_RICHIESTA_CREAZIONE_PDF;
import it.istruzione.ptof.beans.documenti.DocumentoAnnoIncorsoDTO;
import it.istruzione.ptof.beans.documenti.DocumentoArchivioDTO;
import it.istruzione.ptof.beans.documenti.DocumentoAttivabileDTO;
import it.istruzione.ptof.beans.documenti.DocumentoPubblicazioneDTO;
import it.istruzione.ptof.beans.documenti.GestioneDocumentiDTO;
import it.istruzione.ptof.beans.ptof.GestionePtofDTO;
import it.istruzione.ptof.service.GestioneDocumentiService;
 

/**
 * @author mcatanzaro RF002– Gestione Documenti
 */
@Service
@Primary
public class GestioneDocumentiMockService implements GestioneDocumentiService {

	@Override
	public LinkedList<DocumentoAnnoIncorsoDTO> loadDocumentiAnnoIncorso(
			GestioneDocumentiDTO gestioneDocumentiServiceDTO) {
		Populator p = new PopulatorBuilder().build();

		LinkedList<DocumentoAnnoIncorsoDTO> list = new LinkedList<DocumentoAnnoIncorsoDTO>();

		for (int i = 0; i < 1; i++) {

			DocumentoAnnoIncorsoDTO populateBean = p.populateBean(DocumentoAnnoIncorsoDTO.class);
			populateBean.setKey("1");
			list.add(populateBean);
		}

		return list;
	}

	@Override
	public LinkedList<DocumentoArchivioDTO> loadDocumentiArchivio(GestioneDocumentiDTO gestioneDocumentiServiceDTO) {
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<DocumentoArchivioDTO> list = new LinkedList<DocumentoArchivioDTO>();
		{
		DocumentoArchivioDTO doc = p.populateBean(DocumentoArchivioDTO.class);
		doc.setNomeDocumento("PUBBLICATO_COMPLETO");
		doc.setStatoDocumento(TIPO_STATO_DOC.PUBBLICATO_COMPLETO);
		list.add(doc);
		}

		{
		DocumentoArchivioDTO doc = p.populateBean(DocumentoArchivioDTO.class);
		doc.setNomeDocumento("PUBBLICATO_PARZIALMENTE");
		doc.setStatoDocumento(TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE);
		list.add(doc);
		}

		
		return list;
	}

 

	@Override
	public LinkedList<DocumentoAttivabileDTO> loadDocumentiAttivabili() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GestioneCatalogoDTO getTbs1001GestionecatalogodocById(Long prgGesCatDoc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<GestionePtofDTO> getListaPtofGeneraPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPtofFile(GestionePtofDTO tof, ByteArrayOutputStream ptof) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<DocumentoPubblicazioneDTO> loadDocumentiPubblicazione(
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		
		Populator p = new PopulatorBuilder().build();

		LinkedList<DocumentoPubblicazioneDTO> list = new LinkedList<DocumentoPubblicazioneDTO>();

	
		{// STEP1 
			DocumentoPubblicazioneDTO populateBean = p.populateBean(DocumentoPubblicazioneDTO.class);
		
			populateBean.setNomeDocumento("CONVALIDATO - NESSUNA_RICHIESTA -> SOLO ANTEPRIMA" );
			populateBean.setStatoDocumento(TIPO_STATO_DOC.CONVALIDATO);
			populateBean.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.NESSUNA_RICHIESTA);
			populateBean.setVersione("VER");
			list.add(populateBean);
			// SOLO ANTEPRIMA
		}


		{ //SPEP2
			DocumentoPubblicazioneDTO populateBean = p.populateBean(DocumentoPubblicazioneDTO.class);
			populateBean.setNomeDocumento("CONVALIDATO - RICHIESTA_DA_EVADERE -> ATTESA" );
			populateBean.setStatoDocumento(TIPO_STATO_DOC.CONVALIDATO);
			populateBean.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_DA_EVADERE);
			list.add(populateBean);
		} //ATTESA
	
		
		{// STEP 3
			DocumentoPubblicazioneDTO populateBean = p.populateBean(DocumentoPubblicazioneDTO.class);
			populateBean.setNomeDocumento("DOCUMENTO_IN_ANTEPRIMA - RICHIESTA_EVASA_CON_SUCCESSO -> Pubblica parziale e scarico file" );
			populateBean.setStatoDocumento(TIPO_STATO_DOC.DOCUMENTO_IN_ANTEPRIMA);
			populateBean.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_EVASA_CON_SUCCESSO);
			populateBean.setKey("key!");
			list.add(populateBean);
		}// Pubblica parziale e scarico file

		{// SPEP 4
			DocumentoPubblicazioneDTO populateBean = p.populateBean(DocumentoPubblicazioneDTO.class);
			populateBean.setNomeDocumento("DOCUMENTO_IN_ANTEPRIMA - RICHIESTA_DA_EVADERE ->ATTESA" );
			populateBean.setStatoDocumento(TIPO_STATO_DOC.DOCUMENTO_IN_ANTEPRIMA);
			populateBean.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_DA_EVADERE);
			list.add(populateBean);
		}// ATTESA
		
		
		{// SPEP 5
			DocumentoPubblicazioneDTO populateBean = p.populateBean(DocumentoPubblicazioneDTO.class);
			populateBean.setNomeDocumento("PUBBLICATO_PARZIALMENTE - NESSUNA_RICHIESTA -  solo scarico file" );
			populateBean.setStatoDocumento(TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE);
			populateBean.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.NESSUNA_RICHIESTA);
			list.add(populateBean);
		}// solo scarico file
	

		{// SPEP 6

			DocumentoPubblicazioneDTO populateBean = p.populateBean(DocumentoPubblicazioneDTO.class);
			populateBean.setNomeDocumento("FABBISOGNO_VALIDATO - NESSUNA_RICHIESTA -> bottone richiesta pubblicazione definitiva " );
			populateBean.setStatoDocumento(TIPO_STATO_DOC.FABBISOGNO_VALIDATO);
			populateBean.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.NESSUNA_RICHIESTA);
			list.add(populateBean);
		
		}// bottone richista pubblicazione definitiva
		
		
		
		{// SPEP 7

			DocumentoPubblicazioneDTO populateBean = p.populateBean(DocumentoPubblicazioneDTO.class);
			populateBean.setNomeDocumento("FABBISOGNO_VALIDATO - RICHIESTA_DA_EVADERE -> attesa" );
			populateBean.setStatoDocumento(TIPO_STATO_DOC.FABBISOGNO_VALIDATO);
			populateBean.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.RICHIESTA_DA_EVADERE);
			list.add(populateBean);
		
		}// attesa
		
		{// SPEP 8

			DocumentoPubblicazioneDTO populateBean = p.populateBean(DocumentoPubblicazioneDTO.class);
			populateBean.setNomeDocumento("PUBBLICATO_COMPLETO - NESSUNA_RICHIESTA -> scarica allegato" );
			populateBean.setStatoDocumento(TIPO_STATO_DOC.PUBBLICATO_COMPLETO);
			populateBean.setStatoRichiestaCreazione(TIPO_STATO_RICHIESTA_CREAZIONE_PDF.NESSUNA_RICHIESTA);
			list.add(populateBean);
		
		}// scarica allegato
		
		

				
		return list;
	}

	@Override
	public FileDTO loadFilePtofPubblicato(String keyPtof, IstitutoScolasticoDTO istitutoScolasticoDTO) {
		// TODO Auto-generated method stub
		FileDTO dto = new FileDTO(); 
		dto.setFileName("miaaoo.pdf");
		try {
			 
			dto.setFile(new FileInputStream(new File("c:/aaa.pdf")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return dto;
	}

	@Override
	public ResponseDTO<Boolean> richiestaPubblicazionePtof(String keyPtof,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO<Boolean> salvaCatalogoDocumento(GestioneCatalogoDTO gestioneCatalogoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GestioneCatalogoDTO generaNuovoCatalogo() {
		// TODO Auto-generated method stub
		return null;
	}

}
