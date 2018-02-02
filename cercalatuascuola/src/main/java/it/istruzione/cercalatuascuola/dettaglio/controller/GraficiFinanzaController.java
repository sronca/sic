package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.FinanzaDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.GraficiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.*;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GraficiFinanzaController {
    Logger logger = Logger.getLogger(GraficiFinanzaController.class);
    private AnagraficaScuolaService anagraficaScuolaService;
    private FinanzaDAO finanzaDAO;
    private GraficiDAO graficiDAO;

    @Autowired
    public GraficiFinanzaController(AnagraficaScuolaService anagraficaScuolaService,
                                    FinanzaDAO finanzaDAO,
                                    GraficiDAO graficiDAO) {

        this.anagraficaScuolaService = anagraficaScuolaService;
        this.finanzaDAO = finanzaDAO;
        this.graficiDAO = graficiDAO;
    }

    @RequestMapping(value = "/istituti/{codScuUt}/{desNomScuNorm}//finanza/grafici/entrate-fonti-finanziamento")
    public @ResponseBody HighChartsOptions getGraficiEntrateFontiFin(@PathVariable(value = "codScuUt") String codScuUt,
                                                                     String desNomScuNorm) {
        try {
            VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

            VOGrafico grafico = graficiDAO.getGraficoScuola(scuola.getCodScuUt(),
                    scuola.getDatAnnScoRil(), Constants.SCHEDA_GRA_FIN, Constants.SEZIONE_GRA_FIN, "1");
            
            List<HighChartsSeriesElement<Float>> highChartsSeriesElementList = new ArrayList<>();
            
    		if(grafico.getCodStaPubbUff().equals("S") ||
    				(grafico.getCodStaPubbUff().equals("F") && grafico.getCodStaPubbScu().equals("S") && scuola.getFlgIstSta().equals("1")) ||
    				(grafico.getCodStaPubbUff().equals("F") && scuola.getFlgIstSta().equals("0"))) {


    			List<VOFinanza> listaEntrate = finanzaDAO.getEntratePerFontiFin(scuola.getCodForScuApp());

    			if (listaEntrate != null && !listaEntrate.isEmpty()){

    				List<Object[]> fontiFinanziamento = new ArrayList<>();
    				logger.debug("EntratePerFontiFin size: " + listaEntrate.size());
    				for (VOFinanza voFinanza: listaEntrate) {
    					Object[] arr = new Object[2];
    					arr[0] = voFinanza.getDesAggEnt();
    					arr[1] = voFinanza.getPrcAggEnt();

    					fontiFinanziamento.add(arr);

    					HighChartsSeriesElement<Float> highChartsSeriesElement = new HighChartsSeriesElement<>(voFinanza.getDesAggEnt(), voFinanza.getPrcAggEnt());
    					highChartsSeriesElementList.add(highChartsSeriesElement);

    				}

    			}
    		}

            return new HighChartsOptions<>(grafico.getDesTitGra(), grafico.getDesInfGra(), grafico.getDesNotGra(), highChartsSeriesElementList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
