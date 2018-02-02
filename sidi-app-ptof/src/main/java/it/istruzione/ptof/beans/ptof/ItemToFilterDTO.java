package it.istruzione.ptof.beans.ptof;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.constant.TIPO_TIPOLOGICHE_SEZIONE;

public class ItemToFilterDTO extends BeanValueDTO {
   private TIPO_TIPOLOGICHE_SEZIONE tipo ;

public TIPO_TIPOLOGICHE_SEZIONE getTipo() {
	return tipo;
}

public void setTipo(TIPO_TIPOLOGICHE_SEZIONE tipo) {
	this.tipo = tipo;
}
}
