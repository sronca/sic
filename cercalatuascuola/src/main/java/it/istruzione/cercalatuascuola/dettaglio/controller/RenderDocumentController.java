package it.istruzione.cercalatuascuola.dettaglio.controller;

import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.DocumentiDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VODocumento;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOResponseJson;
import it.istruzione.cercalatuascuola.dettaglio.service.AnagraficaScuolaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class RenderDocumentController {
    private Logger logger = Logger.getLogger(RenderDocumentController.class);

    @Autowired
    private DocumentiDAO documentiDAO;

    @Autowired
    private AnagraficaScuolaService anagraficaScuolaService;

    @RequestMapping(value = "/render/document/{codScuUt}")
    public void renderDocument(@PathVariable(value = "codScuUt") String codScuUt,
                               String codTipFil,
                               String prgDoc,
                               String contentDisp,
                               HttpServletResponse response) {

        BufferedOutputStream out = null;
        InputStream inputStream = null;
        try {

            if(codTipFil != null && codTipFil.trim().length() > 0 &&
                    prgDoc != null && prgDoc.trim().length() > 0) {

                VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

                String codScuUtQuery;
                if(Constants.TIPO_DOC_FOTO_SCUOLA.equals(codTipFil)) {
                    codScuUtQuery = scuola.getCodScuUt();
                }
                else {
                    codScuUtQuery = scuola.getCodScuUtPri();
                }

                VODocumento documento = documentiDAO.getDocumentoScuola(codScuUtQuery,
                        scuola.getDatAnnScoRil(),
                        codTipFil,
                        prgDoc);

                if (documento != null && documento.getOggFil() != null) {
                    out = new BufferedOutputStream(response.getOutputStream());
                    inputStream = documento.getOggFil();

                    response.reset();
                    response.setContentType(documento.getDesNot());

                    if(Constants.CONTENT_DISPOSITION_ATTACHMENT.equals(contentDisp)) {
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

//    link che viene chiamato dall'applicazione SNV Cloud
    @RequestMapping(value = "/render/ptof/{codScuUt}/{periodoRif}")
    public void renderPtof(@PathVariable(value = "codScuUt") String codScuUt,
    						@PathVariable(value = "periodoRif") String periodoRif,
                               HttpServletResponse response) {

    	String codTipFil = "14";
    	String prgDoc = "1";
        BufferedOutputStream out = null;
        InputStream inputStream = null;
        try {

            if(codTipFil != null && codTipFil.trim().length() > 0 &&
                    prgDoc != null && prgDoc.trim().length() > 0) {

                VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

                VODocumento documento = documentiDAO.getDocumentoScuola(scuola.getCodScuUt(),
                		periodoRif,
                        codTipFil,
                        prgDoc);

                if (documento != null && documento.getOggFil() != null) {
                    out = new BufferedOutputStream(response.getOutputStream());
                    inputStream = documento.getOggFil();

                    response.reset();
                    response.setContentType(documento.getDesNot());

                    response.setHeader("Content-Disposition", "attachment; filename=\"" + documento.getDesNomFil() + "\"");

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
    
//    link che viene chiamato dall'applicazione SNV Cloud
    @RequestMapping(value = "/exist/ptof/{codScuUt}/{periodoRif}")
    public @ResponseBody VOResponseJson existPtof(@PathVariable(value = "codScuUt") String codScuUt,
    						@PathVariable(value = "periodoRif") String periodoRif,
                               HttpServletResponse response) {

    	String codTipFil = "14";
    	String prgDoc = "1";
    	VOResponseJson responseJson = new VOResponseJson();
    	responseJson.setExist(false);
    	
        try {


                VOAnagraficaScuola scuola = anagraficaScuolaService.getAnagraficaScuola(codScuUt);

                int countDocumento = documentiDAO.countDocumentoScuola(scuola.getCodScuUt(),
                		periodoRif,
                        codTipFil,
                        prgDoc);
                
                if( countDocumento > 0 )
            		responseJson.setExist(true);


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
		return responseJson;
    }

    @RequestMapping(value = "/render/document/{codScuUt}/libro")
    public void renderDocumentLibro(@PathVariable(value = "codScuUt") String codScuUt,
                               String prgDoc,
                               String contentDisp,
                               HttpServletResponse response) {

        BufferedOutputStream out = null;
        InputStream inputStream = null;
        try {

            if(prgDoc != null && prgDoc.trim().length() > 0) {

                VODocumento documento = documentiDAO.getBlobLibro(prgDoc);

                if (documento != null && documento.getOggFil() != null) {
                    out = new BufferedOutputStream(response.getOutputStream());
                    inputStream = documento.getOggFil();

                    response.reset();
                    response.setContentType(documento.getDesNot());

                    if(Constants.CONTENT_DISPOSITION_ATTACHMENT.equals(contentDisp)) {
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
}
