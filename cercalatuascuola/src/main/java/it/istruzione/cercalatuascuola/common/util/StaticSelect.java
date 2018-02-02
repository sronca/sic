package it.istruzione.cercalatuascuola.common.util;


import it.istruzione.cercalatuascuola.ricerca.dao.model.VOCommon;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StaticSelect
{
//	public static List<VOCommon> caricaListaOrdini()
//	{
//		List<VOCommon> ordini = new ArrayList<VOCommon>();
//		
//		VOCommon voCommon = new VOCommon();
//		
//		voCommon.setCodice("1");
//		voCommon.setDescrizione("SCUOLA DELL'INFANZIA");
//		ordini.add(voCommon);
//		
//		voCommon = new VOCommon();
//		voCommon.setCodice("2");
//		voCommon.setDescrizione("SCUOLA PRIMARIA");
//		ordini.add(voCommon);
//		
//		voCommon = new VOCommon();
//		voCommon.setCodice("3");
//		voCommon.setDescrizione("SCUOLA SECONDARIA DI I GRADO");
//		ordini.add(voCommon);
//		
//		voCommon = new VOCommon();
//		voCommon.setCodice("4");
//		voCommon.setDescrizione("SCUOLA SECONDARIA DI II GRADO");
//		ordini.add(voCommon);
//		
//		voCommon = new VOCommon();
//		voCommon.setCodice("5");
//		voCommon.setDescrizione("CENTRO FORMAZIONE PROFESSIONALE");
//		ordini.add(voCommon);
//		
//		return ordini;
//	}

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
	
//	public static List<VOCommon> caricaListaRaggi()
//	{
//		List<VOCommon> lista = new ArrayList<VOCommon>();
//		
//		VOCommon voCommon = new VOCommon();
//		
//		voCommon.setCodice("1");
//		voCommon.setDescrizione("1 KM");
//		lista.add(voCommon);
//		
//		voCommon = new VOCommon();
//		voCommon.setCodice("3");
//		voCommon.setDescrizione("3 KM");
//		lista.add(voCommon);
//		
//		voCommon = new VOCommon();
//		voCommon.setCodice("5");
//		voCommon.setDescrizione("5 KM");
//		lista.add(voCommon);
//		
//		voCommon = new VOCommon();
//		voCommon.setCodice("10");
//		voCommon.setDescrizione("10 KM");
//		lista.add(voCommon);
//		
//		voCommon = new VOCommon();
//		voCommon.setCodice("20");
//		voCommon.setDescrizione("20 KM");
//		lista.add(voCommon);
//		
//		voCommon = new VOCommon();
//		voCommon.setCodice("30");
//		voCommon.setDescrizione("30 KM");
//		lista.add(voCommon);	
//		
//		return lista;
//	}

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

