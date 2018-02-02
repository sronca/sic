package it.istruzione.ptof.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class SidiAppCommonsAppProperties {

	@Value("${link.esci}")
    private String linkEsci;

	public String getLinkEsci() {
		return linkEsci;
	}
	 
	
	
}
