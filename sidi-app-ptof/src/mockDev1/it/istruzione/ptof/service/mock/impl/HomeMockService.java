package it.istruzione.ptof.service.mock.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.PlessiDTO;
import it.istruzione.ptof.service.HomeService;


@Service
@Primary
public class HomeMockService implements HomeService {

	@Override
	public IstitutoScolasticoDTO loadIstitutoScolasticoDTO(String cmf) {
		Populator p = new PopulatorBuilder().build();
		IstitutoScolasticoDTO populateBean = p.populateBean(IstitutoScolasticoDTO.class);
		populateBean.setCodiceMecIstPrin(cmf);
		return populateBean;
	}

	@Override
	public PlessiDTO findPlessoByCodiceMeccanografico(String cmf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IstitutoScolasticoDTO loadIstitutoScolasticoDTO(String codiceMeccanografico, Long annoScolastico) {
		// TODO Auto-generated method stub
		return null;
	}
 }
