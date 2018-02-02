package it.istruzione.ptof.beans;
 
 
public class ProvinciaDTO extends BaseDTO{
	
	String value, label, sigla;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
}