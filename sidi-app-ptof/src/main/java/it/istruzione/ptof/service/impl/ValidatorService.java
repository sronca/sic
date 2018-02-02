package it.istruzione.ptof.service.impl;

import it.istruzione.ptof.beans.constant.TIPO_SEZIONE;
import it.istruzione.ptof.model.entity.Tbs1004Datisezionesottosez;
import it.istruzione.ptof.model.entity.business.Sezione;
import it.istruzione.ptof.model.repository.Tbs1004DatisezionesottosezRepository;
import it.istruzione.ptof.model.repository.business.SezioneRepository;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ValidatorService extends BaseServiceImpl{
	
	@Autowired
	private SezioneRepository sezioneRepository;
	
	@Autowired
	private Tbs1004DatisezionesottosezRepository tbs1004DatisezionesottosezRepository;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Tbs1004Datisezionesottosez getSezioneScuola(Long prgSezSotSez, String codScuUte, Long prgGesCatDoc, Long perAnnSco){
		return tbs1004DatisezionesottosezRepository.findByTbs1003CatalogosezionePrgSezSotSezAndCodScuUteAndPrgGesCatDocAndPerAnnSco(Long.valueOf(TIPO_SEZIONE.SEZIONE_32_DATI_FINALI_SCUOLA.code()), codScuUte, prgGesCatDoc, perAnnSco);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public LinkedList<Sezione> findSezioniScuolaObbligatorieNonCompilate(String codScuUte, Long perAnnSco,Long prgGesCatDoc){
		return sezioneRepository.findSezioniScuolaObbligatorieNonCompilate(codScuUte, perAnnSco, prgGesCatDoc);
	}

}
