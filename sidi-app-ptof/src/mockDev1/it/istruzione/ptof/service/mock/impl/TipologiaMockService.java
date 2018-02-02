package it.istruzione.ptof.service.mock.impl;

import java.util.LinkedList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.ptof.TipologicaDTO;
import it.istruzione.ptof.service.TipologicheService;

@Service
@Primary
public class TipologiaMockService implements TipologicheService{

	@Override
	public LinkedList<BeanValueDTO> getTipologica(TipologicaDTO tipologica, IstitutoScolasticoDTO istitutoScolastico) {
		Populator p = new PopulatorBuilder().build();
		LinkedList<BeanValueDTO> tipo = new LinkedList<BeanValueDTO>();
		for ( int i=0;i<5;i++){
			BeanValueDTO populateBean = p.populateBean(BeanValueDTO.class);
			populateBean.setLabel(istitutoScolastico.getDenominazione()+" " +populateBean.getLabel()+" "+" "+" "+populateBean.getLabel()+" "+" "+populateBean.getLabel()+" "+" "+populateBean.getLabel()+" "+" "+populateBean.getLabel()+" "+" "+populateBean.getLabel()+" "+" "+populateBean.getLabel()+" "+" "+populateBean.getLabel()+" "+" "+populateBean.getLabel()+" "+" "+populateBean.getLabel()+" "+" "+populateBean.getLabel());
			populateBean.setValue(""+i);
			tipo.add(populateBean);
		}
		return tipo;
	}

	@Override
	public LinkedList<BeanValueDTO> getRegioni(SidiContesto contesto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<BeanValueDTO> getProvince(String codiceRegione) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<BeanValueDTO> getComuni(String codiceProvincia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<BeanValueDTO> getStatoPtof() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<BeanValueDTO> getTipologiaScuolaPtof() {
		// TODO Auto-generated method stub
		return null;
	}

}
