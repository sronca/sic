package it.istruzione.poninchiaro.dao;

import it.istruzione.poninchiaro.common.util.LabelValueBean;

import java.util.List;
import java.util.Map;

public interface GestionaleDAO {

	public Map<String,List<LabelValueBean>> getGlossario() throws Exception;
	
}