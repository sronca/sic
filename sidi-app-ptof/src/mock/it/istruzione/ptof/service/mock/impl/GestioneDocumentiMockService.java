package it.istruzione.ptof.service.mock.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import it.istruzione.ptof.beans.DatiDecretoDTO;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ValidationErrorDTO;
import it.istruzione.ptof.beans.constant.TIPO_ERROR;
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

		for (int i = 0; i < 10; i++) {

			list.add(p.populateBean(DocumentoArchivioDTO.class));
		}

		return list;
	}

	@Override
	public LinkedList<DocumentoAttivabileDTO> loadDocumentiAttivabili() {
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<DocumentoAttivabileDTO> list = new LinkedList<DocumentoAttivabileDTO>();

		Random random = new Random();
		for (int i = 0; i < 10; i++) {

			DocumentoAttivabileDTO documentoAttivabileDTO = p.populateBean(DocumentoAttivabileDTO.class);
			documentoAttivabileDTO.setNomeDocumento("Ioioioioi oi o io ioioioioioioioioio ioiooiio iooi oi oioioi oioi oioioo ioi oiiooioioioioioioi ooiioiooioioiooioi iooiio iooioioioioio ooioio.");
			documentoAttivabileDTO.setVersione(String.valueOf(random.nextInt(3)));
			documentoAttivabileDTO.setKey(String.valueOf( ++i));
			list.add(documentoAttivabileDTO);
		}

		return list;
	}

	@Override
	public GestioneCatalogoDTO getTbs1001GestionecatalogodocById(Long prgGesCatDoc) {
		Populator p = new PopulatorBuilder().build();

		return p.populateBean(GestioneCatalogoDTO.class);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDTO loadFilePtofPubblicato(String keyPtof, IstitutoScolasticoDTO istitutoScolasticoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO<Boolean> richiestaPubblicazionePtof(String keyPtof,
			IstitutoScolasticoDTO istitutoScolasticoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO<Boolean> salvaCatalogoDocumento(GestioneCatalogoDTO gestioneCatalogoDTO) {
		ResponseDTO<Boolean> res = new ResponseDTO<Boolean>();
		
		res.setResult(true);
		return res;
	}

	@Override
	public GestioneCatalogoDTO generaNuovoCatalogo() {
		Populator p = new PopulatorBuilder().build();

		return p.populateBean(GestioneCatalogoDTO.class);
	}

	@Override
	public LinkedList<DatiDecretoDTO> getDatiDecreto(GestioneCatalogoDTO gestioneCatalogoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void importDatiDecreto(List<DatiDecretoDTO> items, GestioneCatalogoDTO gestioneCatalogoDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<DocumentoAttivabileDTO> loadDocumentiAttivabiliInCorso() {
		// TODO Auto-generated method stub
				Populator p = new PopulatorBuilder().build();

				LinkedList<DocumentoAttivabileDTO> list = new LinkedList<DocumentoAttivabileDTO>();

				Random random = new Random();
				for (int i = 0; i < 1; i++) {

					DocumentoAttivabileDTO documentoAttivabileDTO = p.populateBean(DocumentoAttivabileDTO.class);
					documentoAttivabileDTO.setNomeDocumento("Ioioioioi oi o io ioioioioioioioioio ioiooiio iooi oi oioioi oioi oioioo ioi oiiooioioioioioioi ooiioiooioioiooioi iooiio iooioioioioio ooioio.");
					documentoAttivabileDTO.setVersione(String.valueOf(random.nextInt(3)));
					documentoAttivabileDTO.setKey(String.valueOf( ++i));
					list.add(documentoAttivabileDTO);
				}

				return list;
	}
}
