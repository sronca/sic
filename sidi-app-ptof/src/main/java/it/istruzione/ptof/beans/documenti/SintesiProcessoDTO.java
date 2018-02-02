package it.istruzione.ptof.beans.documenti;

import it.istruzione.ptof.beans.BaseDTO;

public class SintesiProcessoDTO extends BaseDTO {
	
	
   private String areaProcesso , descObiettivoProcesso;

public String getAreaProcesso() {
	return areaProcesso;
}

public void setAreaProcesso(String areaProcesso) {
	this.areaProcesso = areaProcesso;
}

public String getDescObiettivoProcesso() {
	return descObiettivoProcesso;
}

public void setDescObiettivoProcesso(String descObiettivoProcesso) {
	this.descObiettivoProcesso = descObiettivoProcesso;
}
}
