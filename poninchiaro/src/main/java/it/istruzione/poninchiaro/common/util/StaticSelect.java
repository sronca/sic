package it.istruzione.poninchiaro.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class StaticSelect
{

	public static Map<String, String> caricaOrdini()
	{
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "Seleziona");
		map.put("1", "Scuola dell'infanzia");
		map.put("2", "Scuola primaria");
		map.put("3", "Scuola secondaria di I grado");
		map.put("4", "Scuola secondaria di II grado");
		map.put("5", "Centro di formazione professionale");
		map.put("6", "Centro Provinciale Istruzione Adulti");
		
		return map;
	}
	
	
	public static Map<String, String> caricaRaggi()
	{
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "Seleziona");
		map.put("1", "1 Km");
		map.put("3", "3 Km");
		map.put("5", "5 Km");
		map.put("10", "10 Km");
		map.put("20", "20 Km");
		map.put("30", "30 Km");
		
		return map;
	}
	
}

