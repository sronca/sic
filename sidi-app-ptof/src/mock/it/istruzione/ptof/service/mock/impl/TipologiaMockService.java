package it.istruzione.ptof.service.mock.impl;

import java.util.LinkedList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.constant.TIPO_STATO_DOC;
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
//		Populator p = new PopulatorBuilder().build();
//		BeanValueDTO empty = new BeanValueDTO();
//		empty.setLabel("Tutte le regioni");
//		empty.setValue("");
		LinkedList<BeanValueDTO> tipo = new LinkedList<BeanValueDTO>();
//		tipo.add(empty);
//		for ( int i=0;i<5;i++){
//			BeanValueDTO populateBean = p.populateBean(BeanValueDTO.class);
//			tipo.add(populateBean);
//		}
//		
		Populator p = new PopulatorBuilder().build();
		
		BeanValueDTO empty0 = new BeanValueDTO();
		empty0.setLabel("TUTTE LE REGIONI");
		empty0.setValue("");
		tipo.add(empty0);
		
		BeanValueDTO empty = new BeanValueDTO();
		empty.setLabel("LAZIO");
		empty.setValue("LA");
		tipo.add(empty);
		
		BeanValueDTO empty2 = new BeanValueDTO();
		empty2.setLabel("CALABRIA");
		empty2.setValue("CA");
		tipo.add(empty2);
		
		return tipo;
	}

	@Override
	public LinkedList<BeanValueDTO> getProvince(String codiceRegione) {
		Populator p = new PopulatorBuilder().build();
		LinkedList<BeanValueDTO> tipo = new LinkedList<BeanValueDTO>();
		for ( int i=0;i<5;i++){
			BeanValueDTO populateBean = p.populateBean(BeanValueDTO.class);
			tipo.add(populateBean);
		}
		return tipo;
	}

	@Override
	public LinkedList<BeanValueDTO> getComuni(String codiceProvincia) {
		Populator p = new PopulatorBuilder().build();
		LinkedList<BeanValueDTO> tipo = new LinkedList<BeanValueDTO>();
		for ( int i=0;i<5;i++){
			BeanValueDTO populateBean = p.populateBean(BeanValueDTO.class);
			tipo.add(populateBean);
		}
		return tipo;
	}

	
	@Override
	public LinkedList<BeanValueDTO> getStatoPtof() {
		LinkedList<BeanValueDTO> out = new LinkedList<>();
		BeanValueDTO statoPubblicatoParzialmente = new BeanValueDTO();
		statoPubblicatoParzialmente.setLabel(TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.getBean().getLabel());
		statoPubblicatoParzialmente.setValue(TIPO_STATO_DOC.PUBBLICATO_PARZIALMENTE.getBean().getValue());
		out.add(statoPubblicatoParzialmente);
		BeanValueDTO statoPubblicatoTotalmente = new BeanValueDTO();
		statoPubblicatoTotalmente.setLabel(TIPO_STATO_DOC.PUBBLICATO_COMPLETO.getBean().getLabel());
		statoPubblicatoTotalmente.setValue(TIPO_STATO_DOC.PUBBLICATO_COMPLETO.getBean().getValue());
		out.add(statoPubblicatoTotalmente);
		return out;
	}

	@Override
	public LinkedList<BeanValueDTO> getTipologiaScuolaPtof() {
		LinkedList<BeanValueDTO> out = new LinkedList<>();
		BeanValueDTO infanzia = new BeanValueDTO();
		infanzia.setLabel("Infanzia");
		infanzia.setValue("AA");
		out.add(infanzia);
		BeanValueDTO primaria = new BeanValueDTO();
		primaria.setLabel("Primaria");
		primaria.setValue("EE");
		out.add(primaria);
		BeanValueDTO primogrado = new BeanValueDTO();
		primogrado.setLabel("Secondaria di I grado");
		primogrado.setValue("MM");
		out.add(primogrado);
		BeanValueDTO secondogrado = new BeanValueDTO();
		secondogrado.setLabel("Secondaria di II grado");
		secondogrado.setValue("SS");
		out.add(secondogrado);
		return out;
	}

}
