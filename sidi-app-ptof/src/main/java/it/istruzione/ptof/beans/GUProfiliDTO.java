package it.istruzione.ptof.beans;

import java.util.LinkedList;

public class GUProfiliDTO extends BaseDTO {
   
    private	LinkedList<GUContesti>  contestoUtenteL ;
	


    private String id , value , desc ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LinkedList<GUContesti> getContestoUtenteL() {
		return contestoUtenteL;
	}

	public void setContestoUtenteL(LinkedList<GUContesti> contestoUtenteL) {
		this.contestoUtenteL = contestoUtenteL;
	}
	

	
}
