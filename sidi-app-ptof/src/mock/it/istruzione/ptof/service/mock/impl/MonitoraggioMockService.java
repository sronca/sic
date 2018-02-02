package it.istruzione.ptof.service.mock.impl;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiComuniItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiDiPotenziamentoItem;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoDTO;
import it.istruzione.ptof.beans.CruscottoFabbisogniPostiSostegnoItem;
import it.istruzione.ptof.beans.CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO;
import it.istruzione.ptof.beans.CruscottoRiepilogoDotazioniOrganicheAutonomiaItem;
import it.istruzione.ptof.beans.FabbisognoDTO;
import it.istruzione.ptof.beans.FabbisognoPostiComuniDTO;
import it.istruzione.ptof.beans.FabbisognoPostiComuniItem;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoDTO;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoDiSostegnoItem;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoItem;
import it.istruzione.ptof.beans.FabbisognoPostiPotenziamentoPrimariaItem;
import it.istruzione.ptof.beans.FabbisognoPostiSostegnoDTO;
import it.istruzione.ptof.beans.FabbisognoPostiSostegnoIIGradoItem;
import it.istruzione.ptof.beans.FabbisognoPostiSostegnoItem;
import it.istruzione.ptof.beans.FileDTO;
import it.istruzione.ptof.beans.GestioneCatalogoDTO;
import it.istruzione.ptof.beans.IstitutoScolasticoDTO;
import it.istruzione.ptof.beans.MonitoraggioStatisticheDTO;
import it.istruzione.ptof.beans.ReportCompletoDTO;
import it.istruzione.ptof.beans.ResponseDTO;
import it.istruzione.ptof.beans.ResponsePageDTO;
import it.istruzione.ptof.beans.constant.TIPO_FILE_PDF;
import it.istruzione.ptof.monitoraggio.RicercaConsultazionePtofDTO;
import it.istruzione.ptof.monitoraggio.RicercaReportDTO;
import it.istruzione.ptof.service.GestioneDocumentiService;
import it.istruzione.ptof.service.MonitoraggioService;


/**
 * @author peruvianit RF071– Gestione Catalogo Reportistica
 */
@Service
@Primary
public class MonitoraggioMockService implements MonitoraggioService {
	
	@Autowired
	private GestioneDocumentiService tester;
	
	private final static String COD_SCU_UT = "RMIC81600D";

	@Override
	public LinkedList<MonitoraggioStatisticheDTO> getStatistiche(SidiContesto sidiContesto,
			GestioneCatalogoDTO gestioneCatalogoDTO) {
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<MonitoraggioStatisticheDTO> list = new LinkedList<MonitoraggioStatisticheDTO>();

		for (int i = 0; i < 9; i++) {
			list.add(p.populateBean(MonitoraggioStatisticheDTO.class));
		}

		return list;
	}
	

	@Override
	public ResponsePageDTO<ReportCompletoDTO> getReportCompleto(RicercaReportDTO form) {
		Populator p = new PopulatorBuilder().build();
		
		LinkedList<ReportCompletoDTO> items = new LinkedList<ReportCompletoDTO>();

		for (int i = 0; i < 10; i++) {
			items.add(p.populateBean(ReportCompletoDTO.class));
		}
		 
		 ResponsePageDTO<ReportCompletoDTO> out = new ResponsePageDTO<ReportCompletoDTO>();
		 out.setPagineTotali(2);
  		 out.setRigheTotali( ( new Long ( 10 ) ).intValue() );
		 out.setPaginaCorrente(1+1);
		 out.setSort( form.getSort() );
		 ResponseDTO<LinkedList<ReportCompletoDTO>> out2= new  ResponseDTO<LinkedList<ReportCompletoDTO>>();
		 out2.setResult(items);
		 out.setItems(out2);
 
		return out;
	}


//	@Override
//	public LinkedList<ReportCompletoDTO> getReportCompleto(
//			ReportCompletoFiltroDTO reportCompletoFiltroDTO) {
//		Populator p = new PopulatorBuilder().build();
//
//		LinkedList<ReportCompletoDTO> list = new LinkedList<ReportCompletoDTO>();
//	
//		for (int i = 0; i < 5; i++) {
//			list.add(p.populateBean(ReportCompletoDTO.class));
//		}
//
//		return list;
//	}
	
	
//	@Override
//	public ResponsePageDTO<ReportCompletoDTO> getReportCompleto(ReportCompletoFiltroDTO reportCompletoFiltroDTO,
//			PageDTO page) {
//		
//		Populator p = new PopulatorBuilder().build();
//
//		LinkedList<ReportCompletoDTO> items = new LinkedList<ReportCompletoDTO>();
//
//		for (int i = 0; i < 15; i++) {
//			items.add(p.populateBean(ReportCompletoDTO.class));
//		}
//		 
//		 ResponsePageDTO<ReportCompletoDTO> out = new ResponsePageDTO<ReportCompletoDTO>();
//		 out.setPagineTotali(1);
//  		 out.setRigheTotali( ( new Long ( 2 ) ).intValue() );
//		 out.setPaginaCorrente(0+1);
//		 out.setSort( page.getSort() );
//		 ResponseDTO<LinkedList<ReportCompletoDTO>> out2= new  ResponseDTO<LinkedList<ReportCompletoDTO>>();
//		 out2.setResult(items);
//		 out.setItems(out2);
// 
//		return out;
//	}

	 
	@Override
	public FileDTO loadPtofFile(String keyPtof, TIPO_FILE_PDF tipoFileDaScaricare) {
		// TODO Auto-generated method stub
		FileDTO dto = new FileDTO(); 
		dto.setFileName("ptofMock.pdf");
		try {
			 
			dto.setFile(new FileInputStream(new File("c:/aaa.pdf")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		return dto;
	}


	@Override
	public CruscottoFabbisogniPostiComuniDTO getCruscottoFabbisogniPostiComuni(String codiceRegione,GestioneCatalogoDTO gestioneCatalogoDTO) {
			
		CruscottoFabbisogniPostiComuniDTO cruscottoFabbisogniPostiComuniDTO = new CruscottoFabbisogniPostiComuniDTO();
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<CruscottoFabbisogniPostiComuniItem> list = new LinkedList<CruscottoFabbisogniPostiComuniItem>();

		for (int i = 0; i < 10; i++) {
			list.add(p.populateBean(CruscottoFabbisogniPostiComuniItem.class));
		}

		cruscottoFabbisogniPostiComuniDTO.setGestioneCatalogoDTO(p.populateBean(GestioneCatalogoDTO.class));
		cruscottoFabbisogniPostiComuniDTO.setItems(list);
		return cruscottoFabbisogniPostiComuniDTO;
	}


	@Override
	public CruscottoFabbisogniPostiSostegnoDTO getCruscottoFabbisogniPostiSostegno(String codiceRegione,
			GestioneCatalogoDTO gestioneCatalogoDTO) {
		CruscottoFabbisogniPostiSostegnoDTO cruscottoFabbisogniPostiSostegnoDTO = new CruscottoFabbisogniPostiSostegnoDTO();
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<CruscottoFabbisogniPostiSostegnoItem> list = new LinkedList<CruscottoFabbisogniPostiSostegnoItem>();

		for (int i = 0; i < 10; i++) {
			list.add(p.populateBean(CruscottoFabbisogniPostiSostegnoItem.class));
		}

		cruscottoFabbisogniPostiSostegnoDTO.setGestioneCatalogoDTO(p.populateBean(GestioneCatalogoDTO.class));
		cruscottoFabbisogniPostiSostegnoDTO.setItems(list);
		return cruscottoFabbisogniPostiSostegnoDTO;
	}


	@Override
	public CruscottoFabbisogniPostiDiPotenziamentoDTO getCruscottoFabbisogniPostiPotenziamento(String codiceRegione,
			GestioneCatalogoDTO gestioneCatalogoDTO) {
		CruscottoFabbisogniPostiDiPotenziamentoDTO cruscottoFabbisogniPostiDiPotenziamentoDTO = new CruscottoFabbisogniPostiDiPotenziamentoDTO();
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<CruscottoFabbisogniPostiDiPotenziamentoItem> list = new LinkedList<CruscottoFabbisogniPostiDiPotenziamentoItem>();

		for (int i = 0; i < 10; i++) {
			list.add(p.populateBean(CruscottoFabbisogniPostiDiPotenziamentoItem.class));
		}

		cruscottoFabbisogniPostiDiPotenziamentoDTO.setGestioneCatalogoDTO(p.populateBean(GestioneCatalogoDTO.class));
		cruscottoFabbisogniPostiDiPotenziamentoDTO.setItems(list);
		return cruscottoFabbisogniPostiDiPotenziamentoDTO;
	}


	@Override
	public CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO getCruscottoFabbisogniTotaleOrganica(String codiceRegione,
			GestioneCatalogoDTO gestioneCatalogoDTO) {
		CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO = new CruscottoRiepilogoDotazioniOrganicheAutonomiaDTO();
		// TODO Auto-generated method stub
		Populator p = new PopulatorBuilder().build();

		LinkedList<CruscottoRiepilogoDotazioniOrganicheAutonomiaItem> list = new LinkedList<CruscottoRiepilogoDotazioniOrganicheAutonomiaItem>();

		for (int i = 0; i < 10; i++) {
			list.add(p.populateBean(CruscottoRiepilogoDotazioniOrganicheAutonomiaItem.class));
		}

		cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.setGestioneCatalogoDTO(p.populateBean(GestioneCatalogoDTO.class));
		cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO.setItems(list);
		return cruscottoRiepilogoDotazioniOrganicheAutonomiaDTO;
	}


	@Override
	public ResponsePageDTO<FabbisognoDTO> getConsultazionFabbisogno(RicercaConsultazionePtofDTO form) {
		Populator p = new PopulatorBuilder().build();
		
		LinkedList<FabbisognoDTO> items = new LinkedList<FabbisognoDTO>();

		for (int i = 0; i < 10; i++) {
			items.add(p.populateBean(FabbisognoDTO.class));
		}
		 
		 ResponsePageDTO<FabbisognoDTO> out = new ResponsePageDTO<FabbisognoDTO>();
		 out.setPagineTotali(2);
  		 out.setRigheTotali( ( new Long ( 10 ) ).intValue() );
		 out.setPaginaCorrente(1+1);
		 out.setSort( form.getSort() );
		 ResponseDTO<LinkedList<FabbisognoDTO>> out2= new  ResponseDTO<LinkedList<FabbisognoDTO>>();
		 out2.setResult(items);
		 out.setItems(out2);
 
		return out;
	}


	@Override
	public FabbisognoPostiComuniDTO getFabbisognoPostiComuni(String key) {
		FabbisognoPostiComuniDTO fabbisognoPostiComuniDTO = new FabbisognoPostiComuniDTO();
		Populator p = new PopulatorBuilder().build();

		LinkedList<FabbisognoPostiComuniItem> list = new LinkedList<FabbisognoPostiComuniItem>();

		for (int i = 0; i < 10; i++) {
			list.add(p.populateBean(FabbisognoPostiComuniItem.class));
		}

		fabbisognoPostiComuniDTO.setLabelAnno0("labelAnno0");
		fabbisognoPostiComuniDTO.setLabelAnno1("labelAnno1");
		fabbisognoPostiComuniDTO.setLabelAnno2("labelAnno2");
		
		fabbisognoPostiComuniDTO.setFabbisognoPostiComuniInfanzia(p.populateBean(FabbisognoPostiComuniItem.class));
		fabbisognoPostiComuniDTO.setFabbisognoPostiComuniPrimaria(p.populateBean(FabbisognoPostiComuniItem.class));
		fabbisognoPostiComuniDTO.setFabbisognoPostiComuniIGrado(list);
		
		fabbisognoPostiComuniDTO.setFabbisognoPostiComuniIIGrado(list);

		return fabbisognoPostiComuniDTO;
	}


	@Override
	public FabbisognoPostiSostegnoDTO getPostiSostegno(String key) {
		FabbisognoPostiSostegnoDTO fabbisognoPostiSostegnoDTO = new FabbisognoPostiSostegnoDTO();
		Populator p = new PopulatorBuilder().build();

		fabbisognoPostiSostegnoDTO.setLabelAnno0("labelAnno0");
		fabbisognoPostiSostegnoDTO.setLabelAnno1("labelAnno1");
		fabbisognoPostiSostegnoDTO.setLabelAnno2("labelAnno2");

		fabbisognoPostiSostegnoDTO.setFabbisognoPostiSostegnoInfanzia(p.populateBean(FabbisognoPostiSostegnoItem.class));
		fabbisognoPostiSostegnoDTO.setFabbisognoPostiSostegnoPrimaria(p.populateBean(FabbisognoPostiSostegnoItem.class));
		fabbisognoPostiSostegnoDTO.setFabbisognoPostiSostegnoIGrado(p.populateBean(FabbisognoPostiSostegnoItem.class));
		fabbisognoPostiSostegnoDTO.setFabbisognoPostiSostegnoIIGrado(p.populateBean(FabbisognoPostiSostegnoIIGradoItem.class));
		
		return fabbisognoPostiSostegnoDTO;
	}


	@Override
	public FabbisognoPostiPotenziamentoDTO getPostiPotenziamento(String key) {
		FabbisognoPostiPotenziamentoDTO fabbisognoPostiPotenziamentoDTO = new FabbisognoPostiPotenziamentoDTO();
		Populator p = new PopulatorBuilder().build();

		fabbisognoPostiPotenziamentoDTO.setLabelAnno0("labelAnno0");
		fabbisognoPostiPotenziamentoDTO.setLabelAnno1("labelAnno1");
		fabbisognoPostiPotenziamentoDTO.setLabelAnno2("labelAnno2");
		
		fabbisognoPostiPotenziamentoDTO.setFabbisognoPostiPotenziamentoPrimaria(p.populateBean(FabbisognoPostiPotenziamentoPrimariaItem.class));
		
		LinkedList<FabbisognoPostiPotenziamentoItem> listFabbisognoPostiPotenziamentoItem = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			listFabbisognoPostiPotenziamentoItem.add(p.populateBean(FabbisognoPostiPotenziamentoItem.class));
		}
		
		fabbisognoPostiPotenziamentoDTO.setFabbisognoPostiPotenziamentoIGrado(listFabbisognoPostiPotenziamentoItem);
		
		
		LinkedList<FabbisognoPostiPotenziamentoItem> listFabbisognoPostiPotenziamentoItem_II = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			listFabbisognoPostiPotenziamentoItem_II.add(p.populateBean(FabbisognoPostiPotenziamentoItem.class));
		}
		
		fabbisognoPostiPotenziamentoDTO.setFabbisognoPostiPotenziamentoDiSostegnoIGrado(p.populateBean(FabbisognoPostiPotenziamentoDiSostegnoItem.class));
		fabbisognoPostiPotenziamentoDTO.setFabbisognoPostiPotenziamentoIIGrado(listFabbisognoPostiPotenziamentoItem_II);
		fabbisognoPostiPotenziamentoDTO.setFabbisognoPostiPotenziamentoDiSostegnoIIGrado(p.populateBean(FabbisognoPostiPotenziamentoDiSostegnoItem.class));
		
		return fabbisognoPostiPotenziamentoDTO;
	}


	@Override
	public IstitutoScolasticoDTO getScuolaByKeyDocumento(String key) {
		 Populator p = new PopulatorBuilder().build();

		 IstitutoScolasticoDTO iscolastico = new IstitutoScolasticoDTO();
		 iscolastico.setCodiceMecIstPrin("RMAA02700R");
		 iscolastico.setDenominazione("ISTITUTO SALESIANO PIO XI");
		 
		return iscolastico;
	}


 
}
