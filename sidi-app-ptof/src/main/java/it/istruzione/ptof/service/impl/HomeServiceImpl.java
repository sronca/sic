package it.istruzione.ptof.service.impl;

import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;
import it.istruzione.ptof.helper.CommonsUtility;
import it.istruzione.ptof.helper.DtoFactory;
import it.istruzione.ptof.model.entity.business.Scuola;
import it.istruzione.ptof.model.repository.business.ScuolaRepository;
import it.istruzione.ptof.service.HomeService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HomeServiceImpl extends BaseServiceImpl implements HomeService{
	
	@Autowired
	private ScuolaRepository scuolaRepository;

	/**
	 * Il metodo estrae l'IstitutoScolasticoDTO (istituto principale) per il codice forte di input
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public IstitutoScolasticoDTO  loadIstitutoScolasticoDTO(String cmf ){
		logger.debug("in loadIstitutoScolasticoDTO ... " + cmf);
		IstitutoScolasticoDTO out = null;

		Scuola istitutoPrincipale = scuolaRepository.findIstitutoPrincipaleByCodiceForteAndAnnoScolastico(cmf,
																										  CommonsUtility.getAnnoScolasticoCorrente());
		out = DtoFactory.getIstitutoScolasticoDTO(istitutoPrincipale);
		
		if (out != null){
			List<Scuola> plessi = scuolaRepository.findPlessiByCodiceMeccanograficoIstitutoPrincipaleAndAnnoScolastico(istitutoPrincipale.getId().getCodScuUt(),
																													   CommonsUtility.getAnnoScolasticoCorrente());
			
			List<PlessiDTO> plessiDTO = new ArrayList<>();
			for (Scuola plesso : plessi){
				if (!plesso.getId().getCodScuUt().equals(plesso.getCodScuUtPri())
						|| (
						!plesso.getId().getCodScuUt().substring(2,4).equals("EE")
						&& !plesso.getId().getCodScuUt().substring(2,4).equals("IS")
						&& !plesso.getId().getCodScuUt().substring(2,4).equals("IC") 
						)
					){
					if (!(plesso.getId().getCodScuUt().substring(2,4).equals("AA") && plesso.getId().getCodScuUt().substring(7,9).equals("00"))){
						plessiDTO.add(DtoFactory.getPlessiDTO(plesso));
					}
				}
			}
			out.setPlessi(plessiDTO);
			
			out.setNumeroPlessi(plessiDTO.size());

		}
		 
		return out;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public PlessiDTO  findPlessoByCodiceMeccanografico(String cmf ){
		logger.debug("in findPlessoByCodiceMeccanografico ... " + cmf);
		PlessiDTO out = null;

		Scuola plesso = scuolaRepository.findPlessoByCodiceMeccanograficoAndAnnoScolastico(cmf, CommonsUtility.getAnnoScolasticoCorrente());
		out = DtoFactory.getPlessiDTO(plesso);
		 
		return out;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public IstitutoScolasticoDTO  loadIstitutoScolasticoDTO(String codiceMeccanografico, Long annoScolastico){
		logger.debug("in loadIstitutoScolasticoDTO ... " + codiceMeccanografico + " " + annoScolastico);
		IstitutoScolasticoDTO out = null;

		Scuola istitutoPrincipale = scuolaRepository.findPlessoByCodiceMeccanograficoAndAnnoScolastico(codiceMeccanografico, annoScolastico.intValue());
		out = DtoFactory.getIstitutoScolasticoDTO(istitutoPrincipale);
		 
		return out;
	}
	

}
