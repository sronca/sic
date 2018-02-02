package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.io.Serializable;

public class VOAnnoWrapper implements Serializable, Comparable<VOAnnoWrapper>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9113433001973197868L;
	private Integer anno;

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	
	public VOAnnoWrapper(){
		
	}
	
	public VOAnnoWrapper(Integer $anno){
		setAnno($anno);
	}
	
	public String getAnnoScolasticoDesc(){
		StringBuilder strbAnno = new StringBuilder("");
		if(anno != null){
			String strAnno = anno.toString().trim();
			if(strAnno.length() == 6){
				strbAnno.append(strAnno.substring(0,4));
				strbAnno.append("-");
				strbAnno.append(strAnno.substring(4,6));
			}
		}
		return strbAnno.toString();
	}
	
	public String getAnnoBilancioDesc(){
		StringBuilder strbAnno = new StringBuilder("");
		if(anno != null){
			strbAnno.append(anno);
		}
		return strbAnno.toString();
	}
	
	public static void main(String[] args){
		VOAnnoWrapper wrapper = new VOAnnoWrapper(201314);
		System.out.println(wrapper.getAnnoScolasticoDesc());
		VOAnnoWrapper wrapper2 = new VOAnnoWrapper(2013);
		System.out.println(wrapper2.getAnnoBilancioDesc());
	}

	public int compareTo(VOAnnoWrapper o) {
		return this.getAnno().compareTo(o.getAnno());
	}

}
