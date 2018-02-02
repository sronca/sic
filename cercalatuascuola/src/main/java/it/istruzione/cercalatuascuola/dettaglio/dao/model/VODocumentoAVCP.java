package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;
import java.util.Date;

public class VODocumentoAVCP implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4302349917005738234L;

	private String codiceMeccanografico;
		
	private Integer annoScolastico;
	
	private Integer annoBilancio;
	
	private String nomeFile;
	
	private byte[] file;
	
	private Date dataUltimaModifica;

	public String getCodiceMeccanografico() {
		return codiceMeccanografico;
	}

	public void setCodiceMeccanografico(String codiceMeccanografico) {
		this.codiceMeccanografico = codiceMeccanografico;
	}

	public Integer getAnnoScolastico() {
		return annoScolastico;
	}

	public void setAnnoScolastico(Integer annoScolastico) {
		this.annoScolastico = annoScolastico;
	}

	public Integer getAnnoBilancio() {
		return annoBilancio;
	}

	public void setAnnoBilancio(Integer annoBilancio) {
		this.annoBilancio = annoBilancio;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}
	
	public String getPublicDownloadURL(){
		 StringBuffer strb = new StringBuffer();
//		 strb.append(AVCPDownloadServlet.URL_PATTERN_DOWNLOAD);
		 strb.append(getAnnoScolastico());
		 strb.append("/");
		 strb.append(getCodiceMeccanografico());
		 strb.append("/");
		 strb.append(getAnnoBilancio());
//		 strb.append(AVCPDownloadServlet.URI_SUFFIX);
		 return strb.toString();
	}
	
	public String getPublicViewURL(){
		 StringBuffer strb = new StringBuffer();
//		 strb.append(AVCPDownloadServlet.URL_PATTERN_VIEW);
		 strb.append(getAnnoScolastico());
		 strb.append("/");
		 strb.append(getCodiceMeccanografico());
		 strb.append("/");
		 strb.append(getAnnoBilancio());
//		 strb.append(AVCPDownloadServlet.URI_SUFFIX);
		 return strb.toString();
	}
	
	@Override
	public String toString(){
		return getPublicDownloadURL();
	}

}
