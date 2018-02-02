package it.istruzione.cercalatuascuola.common.util;

import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Classe di utilità
 * @author aed1426
 *
 */
public class Utility {
	private static Logger logger = Logger.getLogger(Utility.class);
	
	private static String[] valueMese = {"09","10","11","12","01","02","03","04","05","06","07","08"};
	private static String[] labelMese = {"SETTEMBRE", "OTTOBRE", "NOVEMBRE", "DICEMBRE", "GENNAIO", "FEBBRAIO",
			"MARZO", "APRILE", "MAGGIO", "GIUGNO", "LUGLIO", "AGOSTO"};	
	
	private static String[] FASCIA_VOTO_PRIMO_GRADO = new String[]{"", "", "", "", "",
		"6", "7", "8", "9",	"10", "10 e lode"};
	
	private static String[] FASCIA_VOTO_SECONDO_GRADO = new String[]{"", "", "", "", "60",
		"61-70", "71-80", "81-90", "91-99",	"100", "100 e lode"};

	@Value("${anno.scolastico.indirizzi.studio}")
	private static String annoScolastico;
	/**
	 * Restituisce la lista dei mesi
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getListaMesiAnnoScol() throws Exception {
		List<LabelValueBean> mesi = new ArrayList<LabelValueBean>();
		
		for(int i = 0; i < valueMese.length; i++) {
			LabelValueBean mese = new LabelValueBean();
			
			mese.setLabel(labelMese[i]);			
			mese.setValue(valueMese[i]);
			
			mesi.add(mese);
		}
		
		return mesi;
	}
	
	/**
	 * Restituisce la descrizione del mese in input
	 * @param codMese
	 * @return
	 * @throws Exception
	 */
	public static String getDescMeseAnnoScol(String codMese) throws Exception {
		String descMese = "";
		
		for(int i = 0; i < valueMese.length; i++) {
			if(valueMese[i].equals(codMese)) {
				descMese = labelMese[i];
				break;
			}
		}
		return descMese;
	}
	
	/**
	 * Restituisce il numero di giorni del mese in input escludendo le festività nazionali e
	 * le domeniche
	 * @param codMese
	 * @return
	 * @throws Exception
	 */
	public static int getGiorniLavorativiMese(String codMese, String anno) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		int giorniMese = 0;
		int giorniLavorativiMese = 0;
		int[] festivitaNazionali = null;
		int festiviMese = 0;
		
		String anno12 = anno.substring(0, 2);
		String anno34 = anno.substring(2, 4);
		String anno56 = anno.substring(4, 6);
		
		for(int i = 0; i < valueMese.length; i++) {
			if(valueMese[i].equals(codMese)) {
				if(i < 4) {
					anno = anno12 + anno34;
				}
				else {
					anno = anno12 + anno56;
				}
				break;
			}
		}
		
		String date = "01/" + codMese + "/" + anno;
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(date));
		
		// giorni del mese
		giorniMese = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// giorni festivi del mese
		festivitaNazionali = getFestivitaNazionali(cal.get(Calendar.MONTH));
		
		for(int i = 1; i <= giorniMese; i++) {
			cal.set(Calendar.DAY_OF_MONTH, i);
			
			// calcolo le domeniche del mese
			if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				festiviMese++;
			}
			else {
				//calcolo le festività rosse sul calendario se
				// nell'anno corrente non vengono di domenica
				for(int j = 0; j < festivitaNazionali.length; j++) {
					if(cal.get(Calendar.DAY_OF_MONTH) == festivitaNazionali[j]) {
						festiviMese++;
					}
				}
			}
		}
		
		Date pasqua = getDataPasqua(cal.get(Calendar.YEAR));		
		cal.setTime(pasqua);
		cal.add(Calendar.DAY_OF_MONTH, 1);// lunedì dell'angelo
		
		// aggiungo ai festivi, eventualmente, il lunedì dell'angelo
		if((cal.get(Calendar.MONTH) + 1) == Integer.parseInt(codMese)) {
			festiviMese++;
		}
		
		// sottraggo dai giorni del mese le domeniche e le festività nazionali
		giorniLavorativiMese = giorniMese - festiviMese;
		
		return giorniLavorativiMese;
	}
	
	/**
	 * Restituisce le festività nazionali nel mese in input
	 * @param mese
	 * @return
	 * @throws Exception
	 */
	public static int[] getFestivitaNazionali(int mese) throws Exception {
		int[] giorniFestivi = null;
				
		switch(mese) {
			case Calendar.JANUARY:
				giorniFestivi = new int[]{1}; //capodanno
			break;
		
			case Calendar.APRIL:
				giorniFestivi = new int[]{25}; //liberazione
			break;
			
			case Calendar.MAY:
				giorniFestivi = new int[]{1}; //lavoratori
			break;
			
			case Calendar.JUNE:
				giorniFestivi = new int[]{2}; //repubblica
			break;
			
			case Calendar.AUGUST:
				giorniFestivi = new int[]{15}; //ferragosto
			break;
			
			case Calendar.NOVEMBER:
				giorniFestivi = new int[]{1}; //tutti i santi
			break;
			
			case Calendar.DECEMBER:
				giorniFestivi = new int[]{8,25,26}; //immacolata,natale,s. stefano
			break;
			
			default:
				giorniFestivi = new int[0];
			break;	
		
		}
		
		return giorniFestivi;
	}
	
	/**
	 * Restituisce la data della Pasqua nell'anno in input
	 * @param anno
	 * @return
	 * @throws Exception
	 */
	public static Date getDataPasqua(int anno) throws Exception {
		// calcolo della pasqua mediante l'algoritmo di gauss
		String giorno = "";
		String mese = "";
		
		int m = 24;
		int n = 5;
		int a = anno % 19;
		int b = anno % 4;
		int c = anno % 7;
		
		int d = ((a * 19) + m) % 30;
		int e = ((2 * b) + (4 * c) + (6 * d) + n) % 7;
		
		if((d + e) < 10) {
			giorno = String.valueOf(d + e + 22);
			mese = "03";
		}
		else {
			giorno = String.valueOf(d + e - 9);
			mese = "04";
		}
		
		if(giorno.length() == 1) {
			giorno = "0" + giorno;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
		return sdf.parse(giorno + "/" + mese + "/" + anno);
	}
		
	/**
	 * Effettua l'arrotondamento del valore in input
	 * @param val valore da arrotondare
	 * @param places cifre decimali
	 * @return
	 */
	public static double round(double val, int places) {
		long factor = (long)Math.pow(10, places);
		val = val * factor;

		long tmp = Math.round(val);

		return (double)tmp / factor;
	}
	
	public static String getJsonDataTable(String[][] cols, String[][] values) {
		StringBuffer json = new StringBuffer("");
		
		if(cols != null && cols.length > 0) {
			json.append("{ cols: [");
			
			for(int i = 0; i < cols.length; i++) {
				String id = cols[i][0];
				String label = cols[i][1];	
				String type = cols[i][2];	
				
				json.append("{id: '").append(id).append("', label: '").append(label).append("', type: '").append(type).append("'},");
			}
			
			json.replace(json.length() -1, json.length(), "]}");
			
			if(values != null && values.length > 0) {
				json.replace(json.length() -1, json.length(), ", rows: [");
				
				for(int i = 0; i < values.length; i++) {
					json.append("{c:[");
					for(int j = 0; j < values[i].length; j++) {
						String value = values[i][j];	
						
						logger.debug("value: " + value + " type: " + cols[j][2]);
						
						if("string".equals(cols[j][2])) {
							json.append("{v: '").append(value).append("'},");
						}
						else if("number".equals(cols[j][2])) {
							json.append("{v:").append(value).append("},");
						}
					}
					json.replace(json.length() -1, json.length(), "]},");
				}
				json.replace(json.length() -1, json.length(), "]}");
			}
			
			json.replace(json.length() -1, json.length(), ",p:{style: 'height: 200px;'}}");
		}
				
		return json.toString();
	}
	
	public static String decodificaFasciaVoto(String codScuUt, String codFasciaVoto) {
		String result = "";
		
		logger.debug("codScuUt: " + codScuUt);
		logger.debug("codFasciaVoto: " + codFasciaVoto);
		
		if(codFasciaVoto != null && codFasciaVoto.trim().length() > 0 && 
				codScuUt != null && codScuUt.trim().length() == 10) {
			
			logger.debug("codTipSit: " + codScuUt.substring(2, 4));
			
			if(codScuUt.substring(2, 4).compareTo("IS") == 0 ||
					codScuUt.substring(2, 4).compareTo("MM") > 0) {
				
				result = FASCIA_VOTO_SECONDO_GRADO[Integer.parseInt(codFasciaVoto) - 1];
			}
			else if(codScuUt.substring(2, 4).compareTo("MM") == 0) {
				result = FASCIA_VOTO_PRIMO_GRADO[Integer.parseInt(codFasciaVoto) - 1];
			}
		}
		
		return result;
	}
	

	public static String getDescrizioneOrdineScuola(String codOrdine){
		
		if ( codOrdine == null || "".equals(codOrdine.trim()) )
			return "";
		else if("AA".equals(codOrdine))
			return "Scuola dell'infanzia";
		else if("EE".equals(codOrdine))
			return "Scuola primaria";
		else if("MM".equals(codOrdine))
			return "Scuola secondaria di I� grado";
		else if("IC".equals(codOrdine))
			return "Istituto comprensivo";
		else
			return"Scuola secondaria di II� grado";
	}	

	
	public static boolean isScuolaSecondariaSecondoGrado(String codOrdine){
		if(   codOrdine != null        &&  !"".equals(codOrdine.trim())  &&
			  !"AA".equals(codOrdine)  &&  !"EE".equals(codOrdine)       &&
			  !"MM".equals(codOrdine)  &&  !"IC".equals(codOrdine) )
			return true;
		else 
			return false;
	}

	/**
	 * Metodo per la restituzione dell'anno scolatico successivo in formato yyyyyy (es:201415)
	 * a partire dall'anno inserito
	 * @return annoAccademico
	 */
	public static String annoAccademicoSuccessivo(String annoAccademico) {
		
		String annoAccademicoSuccessivo = null;
		
		String a = annoAccademico.substring(0, 4); 
		String b = annoAccademico.substring(4, 6);
		
		Integer iA = new Integer(a);
		Integer iB = new Integer(b);

		iA = new Integer(iA.intValue() +1);
		iB = new Integer(iB.intValue() +1);
		
		annoAccademicoSuccessivo = iA.toString() + iB.toString();
		
		return annoAccademicoSuccessivo;
		
	}

	public static String formattaDataSistema() {    
		String dataSistema;
		int annoCorrenteInt;
		int meseCorrenteInt;
		int giornoCorrenteInt;
		String annoCorrente;
		String meseCorrente;
		String giornoCorrente;
		
		Date trialTime = new Date();
		
		GregorianCalendar gcalendar = new GregorianCalendar();
		gcalendar.setTime(trialTime);
		annoCorrenteInt = gcalendar.get(GregorianCalendar.YEAR);
		meseCorrenteInt = gcalendar.get(GregorianCalendar.MONTH) + 1;
		giornoCorrenteInt = gcalendar.get(GregorianCalendar.DAY_OF_MONTH);
		
		annoCorrente = String.valueOf(annoCorrenteInt);
		if (meseCorrenteInt < 10) {
			meseCorrente = "0" + String.valueOf(meseCorrenteInt);
		} else {
			meseCorrente = String.valueOf(meseCorrenteInt);    
		}
		if (giornoCorrenteInt < 10) {
			giornoCorrente = "0" + String.valueOf(giornoCorrenteInt);
		} else {
			giornoCorrente = String.valueOf(giornoCorrenteInt);
		}
		
		dataSistema = giornoCorrente + "/" + meseCorrente + "/" + annoCorrente;
		
		return dataSistema;
	}

	/**
	 * Metodo per la restituzione dell'anno scolatico corrente in formato yyyyyy (es:201314)
	 * @return annoAccademico
	 */
	public static String annoAccademico() {
		// mod 05-09-2014
		String annoAccademico = null;
		if(annoScolastico != null && !annoScolastico.trim().equals("")){
			annoAccademico = annoScolastico;
		}
		else{
			String dataRif = formattaDataSistema();
			StringTokenizer data = new StringTokenizer(dataRif,"/");
			String gg = data.nextToken();//giorno
			int mm = (new Integer(data.nextToken())).intValue();//mese
			int aaaa = (new Integer(data.nextToken())).intValue();//anno
			
			annoAccademico = new Integer(aaaa).toString();//2009
			
			int annoAppo = (new Integer(annoAccademico.substring(2,4))).intValue();//9
			
			annoAppo = annoAppo+1;//10
			String appo = "" + annoAppo;
			if(annoAppo < 10){
				appo = "0" + annoAppo;
			}
			annoAccademico = annoAccademico+appo;
			
			if(1<=mm && 8>=mm){
				String anno = (new Integer(aaaa-1)).toString();//2008
				annoAppo = annoAppo - 1;//09
				
				if(annoAppo < 10){
					appo = "0" + annoAppo;
				}else{
					appo = "" + annoAppo;
				}
				
				annoAccademico = anno + appo;
			}
		}
		logger.debug("METODO GET ANNO ACCADEMICO : " + annoAccademico);
		return annoAccademico;
	}

	/**
	 * Metodo per la restituzione dell'anno scolatico corrente in formato yyyy/yyyy (es:2013/2014)
	 * @return annoAccademico
	 */
	public static String annoScolasticoCorrente() {
		String annoScolastico = annoAccademico();
		String annoScolasticoFormattato = "";
		
		if("1998".compareTo(annoScolastico.substring(0, 4)) >= 0){
			annoScolasticoFormattato = annoScolastico.substring(0, 4) + "/" + "19" + annoScolastico.substring(4);
		}
		else if("1998".compareTo(annoScolastico.substring(0, 4)) < 0){
			annoScolasticoFormattato = annoScolastico.substring(0, 4) + "/" + "20" + annoScolastico.substring(4);
		}
			
		return annoScolasticoFormattato;
	}
	
	/**
	 * Metodo per la restituzione dell'anno scolatico successivo in formato yyyy/yyyy (es:2014/2015)
	 * @return annoAccademico
	 */
	public static String annoScolasticoSuccessivo() {
		String annoScolastico = annoAccademicoSuccessivo(annoAccademico());
        String annoScolasticoFormattato = "";
		
		if("1998".compareTo(annoScolastico.substring(0, 4)) >= 0){
			annoScolasticoFormattato = annoScolastico.substring(0, 4) + "/" + "19" + annoScolastico.substring(4);
		}
		else if("1998".compareTo(annoScolastico.substring(0, 4)) < 0){
			annoScolasticoFormattato = annoScolastico.substring(0, 4) + "/" + "20" + annoScolastico.substring(4);
		}
			
		return annoScolasticoFormattato;
	}
	
	public static boolean isAnnoScolasticoAttuale() throws Exception {
		
		String anno = annoAccademico();
		
		String dataInizioAnnoSuccessivo = "01/09/"+anno.substring(0, 4);
		String dataFineAnnoSuccessivo = "30/04/20"+anno.substring(4, 6);
		
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dataInizioAnnoSuccessivoD = simple.parse(dataInizioAnnoSuccessivo);
		Date dataFineAnnoSuccessivoD = simple.parse(dataFineAnnoSuccessivo);
		Date dataOdierna = simple.parse(simple.format(new Date()));
		
		if((dataInizioAnnoSuccessivoD.before(dataOdierna) || dataOdierna.equals(dataInizioAnnoSuccessivoD)) && (dataFineAnnoSuccessivoD.after(dataOdierna) || dataFineAnnoSuccessivoD.equals(dataOdierna)))
			return true;
		
		return false;
	}
		
	public static String descrizioneAnnoAccademico(String annoAccademico) {
		
		String annoAccademicoEsteso = null;
		
		String a = annoAccademico.substring(0, 4); 
		String b = annoAccademico.substring(4, 6);
		
		Integer iA = new Integer(a);
		Integer iB = new Integer(b);

		iA = new Integer(iA.intValue() );
		iB = new Integer(iB.intValue() +2000);
		
		annoAccademicoEsteso = iA.toString() + " - "  + iB.toString();
		
		return annoAccademicoEsteso;	
	}

	public static boolean mostraMenuLibriDiTesto(VOAnagraficaScuola scuola){
		boolean showMenu = true;
		if(scuola == null || scuola.getCodScuUt() == null || scuola.getCodScuUt().trim().length() != 10){
			// errore formale nel codice scolastico
			showMenu = false;
		}
		else {
			try {
				if(scuola.isSerale()){
					showMenu = false;
				}
				else if(scuola.isIstitutoPrincipale()){
					if("IC".equals(scuola.getCodScuUt().substring(2, 4))){
						showMenu = false;
					}
					if("IS".equals(scuola.getCodScuUt().substring(2,4) )){
						showMenu = false;
					}
				}
			}
			catch(NumberFormatException nfex){
				showMenu = false;
			}
		}
		return showMenu;
	}

	public static String normalizzaNomeScuola(String nomeScuola) {
		if (nomeScuola == null || nomeScuola.trim().length() == 0) return "";

		String normalizedString = Normalizer.normalize(nomeScuola.trim(), Normalizer.Form.NFD)
				.replaceAll("[^a-zA-Z\\s]", "")
				.replaceAll("\\s+", "-")
				.toLowerCase();

		if (normalizedString.length() > 50) {
			normalizedString = normalizedString.substring(0, 50);
		}

		return normalizedString;
	}

	public static String getAnnoScolastico() {
		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("ddMMyyyy");
		String dataPubl = simpledateformat.format(date);
		int ann = Integer.parseInt(dataPubl.substring(4, 8));
		int mes = Integer.parseInt(dataPubl.substring(2, 4));
		int gio = Integer.parseInt(dataPubl.substring(0, 2));
		String nuovoAnno = "";
		if (mes > 8) {
			int annScol = ann + 1;
			nuovoAnno = String.valueOf(ann) + String.valueOf(annScol).substring(2, 4);
		} else {
			int annScol = ann - 1;
			nuovoAnno = String.valueOf(annScol) + String.valueOf(ann).substring(2, 4);
		}
		return nuovoAnno;
	}

	public static String trimValue(String in){
		return in != null ? in.trim() : "";
	}

	public static String numberRnd(){
		String rnd = "";
		
		for(int i = 0; i < 6; i++){
			rnd += (Math.round(Math.random() * 9));
		}
		
		return rnd;
	}
	
	public static String getTipoScuola(String codScuUt)
	{
		return codScuUt.substring(2, 4);
	}
	
}
