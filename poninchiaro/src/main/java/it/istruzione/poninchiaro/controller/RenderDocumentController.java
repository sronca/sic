package it.istruzione.poninchiaro.controller;

import it.istruzione.poninchiaro.common.util.Constants;
import it.istruzione.poninchiaro.model.VODocumento;
import it.istruzione.poninchiaro.model.VOEvento;
import it.istruzione.poninchiaro.model.VOProgettoOpendata;
import it.istruzione.poninchiaro.service.DocumentiService;
import it.istruzione.poninchiaro.service.PoninchiaroService;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RenderDocumentController {
    private Logger logger = Logger.getLogger(RenderDocumentController.class);

    @Autowired
    private DocumentiService documentiService;
    
    @Autowired
    private PoninchiaroService poninchiaroService;
    
    private final static String COL_SEPARATOR = "|";

    @RequestMapping(value = "/render/document/{codScuUt}")
    public void renderDocument(@PathVariable(value = "codScuUt") String codScuUt, String datAnnScoRil,
                               String codTipFil,
                               String prgDoc,
                               String contentDisp,
                               HttpServletResponse response) {

        BufferedOutputStream out = null;
        InputStream inputStream = null;
        try {

            if(codTipFil != null && codTipFil.trim().length() > 0 &&
                    prgDoc != null && prgDoc.trim().length() > 0) {


                VODocumento documento = documentiService.getDocumentoScuola(codScuUt,
                        datAnnScoRil,
                        codTipFil,
                        prgDoc);

                if (documento != null && documento.getOggFil() != null) {
                    out = new BufferedOutputStream(response.getOutputStream());
                    inputStream = documento.getOggFil();

                    response.reset();
                    response.setContentType(documento.getDesNot());

                    if("attach".equals(contentDisp)) {
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + documento.getDesNomFil() + "\"");
                    } else {
                        response.setHeader("Content-disposition", "inline; filename=\"" + documento.getDesNomFil() + "\"");
                    }

                    int data;
                    while ((data = inputStream.read()) != -1) {
                        out.write(data);
                    }

                    out.flush();
                }
            }

            response.flushBuffer();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    
    @RequestMapping(value = "/opendata/progetti/istituti")
    public void getProgettiIstitutiBeneficiariOpendata(
                               HttpServletResponse response) {

		String fileName = "Istituti_Beneficiari.csv";

		response.setContentType("text/csv");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);

		
        try {
        	VOEvento evento = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_ISTITUTI);
			List<VOProgettoOpendata> lista = poninchiaroService.getProgettiIstitutiBeneficiariOpendata(evento.getPrgEve());
			PrintWriter out = response.getWriter();
			
			out.println("PROGRAMMA" + COL_SEPARATOR + 
					"FONDO" + COL_SEPARATOR + 
					"ANNO" + COL_SEPARATOR + 
					"AREA_TERRITORIALE" + COL_SEPARATOR +
					"REGIONE" + COL_SEPARATOR +
					"PROVINCIA" + COL_SEPARATOR +
					"BENEFICIARIO" + COL_SEPARATOR +
					"CODICE_FISCALE_BENEFICIARIO" + COL_SEPARATOR +
					"CODICE_PROGETTO" + COL_SEPARATOR +
					"CODICE_UNICO_PROGETTO" + COL_SEPARATOR +
					"DENOMINAZIONE_OPERAZIONE" + COL_SEPARATOR +
					"SINTESI_OPERAZIONE" + COL_SEPARATOR +
					"DATA_INIZIO_OPERAZIONE" + COL_SEPARATOR +
					"DATA_FINE_OPERAZIONE" + COL_SEPARATOR +
					"SPESA_AMMISSIBILE" + COL_SEPARATOR +
					"TASSO_DI_COFINANZIAMENTO_UE" + COL_SEPARATOR +
					"TASSO_DI_COFINANZIAMENTO_NAZ" + COL_SEPARATOR +
					"CAP");
			
			for (VOProgettoOpendata vo : lista){

				out.println(vo.getProgramma() + COL_SEPARATOR + 
						vo.getFondo() + COL_SEPARATOR + 
						vo.getAnno() + COL_SEPARATOR + 
						vo.getAreaTerritoriale() + COL_SEPARATOR + 
						vo.getRegione() + COL_SEPARATOR +
						vo.getProvincia() + COL_SEPARATOR + 
						vo.getBeneficiario() + COL_SEPARATOR + 
						vo.getCodiceFiscale() + COL_SEPARATOR +
						vo.getCodiceProgetto() + COL_SEPARATOR + 
						vo.getCupProgetto() + COL_SEPARATOR + 
						vo.getDenomOperazione() + COL_SEPARATOR + 
						vo.getSintesiOperazione() + COL_SEPARATOR +
						vo.getDataInizio() + COL_SEPARATOR + 
						//vo.getDataFine() + COL_SEPARATOR + 
						"" + COL_SEPARATOR +
						vo.getSpesaAmmissibile() + COL_SEPARATOR + 
						vo.getTassoUE() + COL_SEPARATOR +
						vo.getTassoNAZ() + COL_SEPARATOR + 
						vo.getCap());

			}

			out.close();

			


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {

        }
    }

    @RequestMapping(value = "/opendata/progetti/fornitori")
    public void getProgettiFornitoriBeneficiariOpendata(
                               HttpServletResponse response) {

		String fileName = "Fornitori_Beneficiari.csv";

		response.setContentType("text/csv");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);

		
        try {
        	VOEvento evento = poninchiaroService.getPrgEvePubblicato(Constants.TIPO_EVENTO_PUBBLICAZIONE_FORNITORI);
			List<VOProgettoOpendata> lista = poninchiaroService.getProgettiFornitoriBeneficiariOpendata(evento.getPrgEve());
			PrintWriter out = response.getWriter();
			
			out.println("PROGRAMMA" + COL_SEPARATOR + 
					"FONDO" + COL_SEPARATOR + 
					"ANNO" + COL_SEPARATOR + 
					"BENEFICIARIO" + COL_SEPARATOR +
					"PARTITA_IVA/CODICE_FISCALE" + COL_SEPARATOR +
					"CODICE_PROGETTO" + COL_SEPARATOR +
					"CODICE_UNICO_PROGETTO" + COL_SEPARATOR +
					"DENOMINAZIONE_OPERAZIONE" + COL_SEPARATOR +
					"SINTESI_OPERAZIONE" + COL_SEPARATOR +
					"DATA_INIZIO_OPERAZIONE" + COL_SEPARATOR +
					"DATA_FINE_OPERAZIONE" + COL_SEPARATOR +
					"SPESA_AMMISSIBILE" + COL_SEPARATOR +
					"TASSO_DI_COFINANZIAMENTO_UE" + COL_SEPARATOR +
					"TASSO_DI_COFINANZIAMENTO_NAZ" + COL_SEPARATOR +
					"CAP");
			
			for (VOProgettoOpendata vo : lista){

				out.println(vo.getProgramma() + COL_SEPARATOR + 
						vo.getFondo() + COL_SEPARATOR + 
						vo.getAnno() + COL_SEPARATOR + 
						vo.getBeneficiario() + COL_SEPARATOR + 
						vo.getCodiceFiscale() + COL_SEPARATOR +
						vo.getCodiceProgetto() + COL_SEPARATOR + 
						vo.getCupProgetto() + COL_SEPARATOR + 
						vo.getDenomOperazione() + COL_SEPARATOR + 
						vo.getSintesiOperazione() + COL_SEPARATOR +
						vo.getDataInizio() + COL_SEPARATOR + 
						//vo.getDataFine() + COL_SEPARATOR +
						"" + COL_SEPARATOR +
						vo.getSpesaAmmissibile() + COL_SEPARATOR + 
						vo.getTassoUE() + COL_SEPARATOR +
						vo.getTassoNAZ() + COL_SEPARATOR + 
						vo.getCap());

			}

			out.close();

			


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {

        }
    }

    @RequestMapping(value = "/opendata/bandi/scuole")
    public void getBandiScuoleOpendata(
                               HttpServletResponse response) {

		String fileName = "Bandi_delle_Scuole.csv";

		response.setContentType("text/csv");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);

		
        try {
			List<VOProgettoOpendata> lista = poninchiaroService.getBandiScuoleOpendata();
			PrintWriter out = response.getWriter();
			
			out.println("PROGRAMMA" + COL_SEPARATOR + 
					"FONDO" + COL_SEPARATOR + 
					"REGIONE" + COL_SEPARATOR +
					"PROVINCIA" + COL_SEPARATOR +
					"ISTITUTO" + COL_SEPARATOR +
					"CODICE_PROGETTO" + COL_SEPARATOR +
					"CODICE_UNICO_PROGETTO" + COL_SEPARATOR +
					"CIG" + COL_SEPARATOR +
					"PROCEDURA" + COL_SEPARATOR +
					"OGGETTO" + COL_SEPARATOR +
					"DATA_PUBBLICAZIONE" + COL_SEPARATOR +
					"DATA_SCADENZA" + COL_SEPARATOR +
					"URL" + COL_SEPARATOR +
					"RIFERIMENTI" + COL_SEPARATOR +
					"CONTATTI");
			
			for (VOProgettoOpendata vo : lista){

				out.println(vo.getProgramma() + COL_SEPARATOR + 
						vo.getFondo() + COL_SEPARATOR + 
						vo.getRegione() + COL_SEPARATOR +
						vo.getProvincia() + COL_SEPARATOR + 
						vo.getBeneficiario() + COL_SEPARATOR + 
						vo.getCodiceProgetto() + COL_SEPARATOR + 
						vo.getCupProgetto() + COL_SEPARATOR + 
						vo.getCig() + COL_SEPARATOR + 
						vo.getProcedura() + COL_SEPARATOR +
						vo.getOggetto() + COL_SEPARATOR + 
						vo.getDataPubblicazione() + COL_SEPARATOR + 
						vo.getDataScadenza() + COL_SEPARATOR + 
						vo.getUrl() + COL_SEPARATOR +
						vo.getRiferimenti() + COL_SEPARATOR + 
						vo.getContatti());

			}

			out.close();

			


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {

        }
    }

    @RequestMapping(value = "/opendata/bandi/amministrazione")
    public void getBandiAmministrazioneOpendata(
                               HttpServletResponse response) {

		String fileName = "Opportunita_di_Finanziamento.csv";

		response.setContentType("text/csv");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);

		
        try {
			List<VOProgettoOpendata> lista = poninchiaroService.getBandiAmministrazioneOpendata();
			PrintWriter out = response.getWriter();
			
			out.println("PROGRAMMA" + COL_SEPARATOR + 
					"FONDO" + COL_SEPARATOR + 
					"PROCEDURA_DI_ATTIVAZIONE" + COL_SEPARATOR +
					"OGGETTO" + COL_SEPARATOR +
					"TIPOLOGIA_DI_BENEFICIARI" + COL_SEPARATOR +
					"DATA_PUBBLICAZIONE" + COL_SEPARATOR +
					"DATA_SCADENZA_PRESENTAZIONE_PROPOSTE" + COL_SEPARATOR +
					"URL_DOCUMENTI");
			
			for (VOProgettoOpendata vo : lista){

				out.println(vo.getProgramma() + COL_SEPARATOR + 
						vo.getFondo() + COL_SEPARATOR + 
						vo.getProcedura() + COL_SEPARATOR +
						vo.getOggetto() + COL_SEPARATOR + 
						vo.getTipologiaBeneficiari() + COL_SEPARATOR +
						vo.getDataPubblicazione() + COL_SEPARATOR + 
						vo.getDataScadenza() + COL_SEPARATOR + 
						"http://hubmiur.pubblica.istruzione.it/web/istruzione/pon/2014_2020/avvisi2016");

			}

			out.close();

			


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {

        }
    }


}

