package it.istruzione.ptof.beans.constant;

import it.istruzione.ptof.beans.BeanValueDTO;

public enum TIPO_COMPONENTE {

	/**
	 * TEXTEDITOR : inserimento testo e tabelle ( key , testo )
	 * ALLEGATO : e' un possibile up load
	 * DATI_ISTITUTO_PRINCIPALE :  tabella ( itestazione/riga/colonna )di valori da prospettare in sola lettura a video
	 * DATI_PLESSI : lista di valori ( solo righe itestazione/label/value ) da prospettare in sola lettura a video
	 * COMBO_BOX : key, label , lista di ( key/ desc / valore )
	 * 
	 * SE IL NOME COMINCIA PER S_% E' UN COMPONENTE SPECIFICIFIO PER UNA SEZIONE ( NON RIUTILIZABILE!!!)
	 * 
	 * S_OBBIETTIVI_FORMATIVI = USATO NEL RF015– Obiettivi prioritari  
	 * 
	 */
	TEXTEDITOR ("TX","Editor di testo"), 
	ALLEGATO ("UP","UP"), 
	DATI_ISTITUTO_PRINCIPALE ("IP","IP"), 
	COMBO_BOX ("CB","CB"), 
	MULTI_BOX ("MB","MB"),
	S_OBBIETTIVI_FORMATIVI ("OB","OB") , // RF015 : Sottosezione della Sezione 5 - Obiettivi prioritari ( FORMATIVI OBIETTIVI DI CUI AL COMMA 7 DELLA LEGGE 107 ) 
	S_OBBIETTIVI_FORMATIVI_ALTRI ("OBAL","OBAL"), // RF015 : Sottosezione della Sezione 5 - Obiettivi prioritari (ALTRI OBIETTIVI FORMATIVI )
	S_OBBIETTIVI_MIGLIORAMENTO("OBMI","OBMI"), // RF016– Obiettivi del piano di miglioramento–  Sottosezione della Sezione 5 -   
	S_PROGETTI_CURRICULARI("AP","AP") ,// RF020 - Progetti curricolari && RF021 - Progetti curricolari exstra
	S_ALTRI_PROGETTI_CURRICULARI_EXSTRA("APEC","APEC") ,//  RF022– Altri Progetti extra-curriculari
	
	S_ALTRE_INIZIATIVE_DIDATTICO("ALDI","ALDI") ,//   RF024–  Altre iniziative didattico/educative
	S_ATTIVITA_SOSTEGNO("ATTS","ATTS") ,//   RF025– Attività di sostegno 
	
	S_ORGANIZZAZIONE_RISORSE ("ORFU","ORFU") ,//   RF027– Organizzazione delle risorse 
	S_ORGANIZZAZIONE_RISORSE_ALTRI_RUOLI ("ORAP","ORAP") ,//   RF027– Organizzazione delle risorse 
	
	S_ORGANIZZAZIONE_CLASSI_MMII ("ACSS","ACSS") ,//   RF028– Organizzazione delle classi
	S_ORGANIZZAZIONE_CLASSI_MMI ("ACMM","ACMM") ,//   RF028– Organizzazione delle classi 
	S_ORGANIZZAZIONE_CLASSI_EE ("ACEE","ACEE") ,//   RF028– Organizzazione delle classi
	S_ORGANIZZAZIONE_CLASSI_AA ("ACAA","ACAA") ,//   RF028– Organizzazione delle classi
	
	
	S_PROGRAMMAZIONE_FORMAZIONE_DOC ("PFPD","PFPD") ,//   RF032–  Programmazione Della Formazione Al Personale Docente ( una combo Ambito formativo )
	S_PROGRAMMAZIONE_FORMAZIONE_AMM ("PFPA","PFPA") ,//   RF032–  Programmazione Della Formazione Al Personale Amministrativo ( una combo Ambito formativo )
	S_PROGRAMMAZIONE_FORMAZIONE_TEC ("PFPT","PFPT") ,//   RF032–  Programmazione Della Formazione Al Personale Tecnico( una combo Ambito formativo )
	S_PROGRAMMAZIONE_FORMAZIONE_AUS ("PFPU","PFPU") ,//   RF032–  Programmazione Della Formazione Al Personale Ausiliario( una combo Ambito formativo )

	S_STRUMENTI_ATTREZZATURE_TECNOLOGIA ("ATTR","ATTR") ,//   RF037– Strumenti e attrezzature e tecnologia   ( STRUMENTI ED ATTREZZATURE NECESSARIE AL RAGGIUNGIMENTO DEGLI OBIETTIVI ISTITUZIONALI )
    S_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE ("DOTA","DOTA") ,//   RF037– Strumenti e attrezzature e tecnologia  ( DOTAZIONI DELL’ISTITUTO PRINCIPALE  )

    S_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO ("PREL","PREL") ,//   RF038– Collaborazioni con enti locali e territorio
    S_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO ("CSPF","CSPF") ,//   RF038– Collaborazioni con enti locali e territorio
    
    S_PIANIFICAZIONE_ATTIVITA ("PIAT","PIAT") , // RF039–  Pianificazione interventi e monitoraggio ( PIANIFICAZIONE ATTIVITA’ )
    S_MONITORAGGIO_PIANIFICAZIONE ("MPIN","MPIN") , // RF039–  Pianificazione interventi e monitoraggio ( MONITORAGGIO PIANIFICAZIONE INSERITA )
    
    S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO("INDS","INDS") , // RF018– Articolazione Oraria - indirizzo studio
    S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO("QUAO","QUAO") , // RF018– Articolazione Oraria - i Quadri Orari
    S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA("TEMS","TEMS") , // RF018– Articolazione Oraria - i Tempi della Scuola
    
    S_ALTERNANZA_SCUOLA_LAVORO("ALSL","ALSL") , // RF023– Alternanza Scuola Lavoro  
    S_DATI_FINALI_SCUOLA("DFSC","DFSC") , // RF051– Dati Finali Scuola,
    S_PIANO_NAZIONALE_SCUOLA_DIGITALE("PNSD","PNSD"), // RF049- Piano Nazionale della Sciola Digitle
  
    S_FABBISOGNO_ATTREZZATURE_INFRA ("MICIOMICIO","BAUBAU") , //RF048– Fabbisogno di attrezzature e infrastrutture - Componente generico di gestione
    S_FABBISOGNO_ATTREZZATURE_INFRA_AA_EE_ATTR ("FAAE","FAAE") , //RF048
    S_FABBISOGNO_ATTREZZATURE_INFRA_AA_EE_INFR ("FIAE","FIAE") , //RF048
    S_FABBISOGNO_ATTREZZATURE_INFRA_MM_ATTR ("FAMM","FAMM") , //RF048
    S_FABBISOGNO_ATTREZZATURE_INFRA_MM_INFR ("FIMM","FIMM") , //RF048
    S_FABBISOGNO_ATTREZZATURE_INFRA_SS_ATTR ("FASS","FASS") , //RF048
    S_FABBISOGNO_ATTREZZATURE_INFRA_SS_INFR ("FISS","FISS") , //RF048
    S_FABBISOGNO_ATTREZZATURE_INFRA_ALTRE_INFO ("FAAI","FAAI") , //RF048– Fabbisogno di attrezzature e infrastrutture ( motivazione, finalita .... )
    
    S_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI("FPAT","FPAT"), // RF045–  Fabbisogno dei posti di personale amministrativo, tecnico e ausiliare 
   
    S_FABBISOGNO_POSTI_CATTEDRE_AA_EE ("FPCAE","FPCAE") , // RF042– Fabbisogno di posti e cattedre  ( primaria e infazia )
    S_FABBISOGNO_POSTI_CATTEDRE_MM ("FPCMS","FPCMS") , // RF042– Fabbisogno di posti e cattedre  ( secondario I e II )
    S_FABBISOGNO_POSTI_CATTEDRE_AA ("FPCA","FPCA") ,
    S_FABBISOGNO_POSTI_CATTEDRE_EE ("FPCE","FPCE") ,
    S_FABBISOGNO_POSTI_CATTEDRE_IG ("FPCM","FPCM") ,
    S_FABBISOGNO_POSTI_CATTEDRE_IIG ("FPCS","FPCS") ,
    
    S_FABBISOGNO_CONNESSO_PROGETTO ("FCAP","FCAP") , // RF046–  Fabbisogno connesso a progetto
    
    S_FABBISOGNO_POSTI_SOSTEGNO_AA_EE_MMI ("MICIOMICIO","BAUBAU") , //  RF043– Fabbisogno di posti di sostegno ( infanzia , primaria  , primaria I) componente generico
    S_FABBISOGNO_POSTI_SOSTEGNO_AA ("FPSA","FPSA") ,
    S_FABBISOGNO_POSTI_SOSTEGNO_EE ("FPSE","FPSE") ,
    S_FABBISOGNO_POSTI_SOSTEGNO_MM ("FPSM","FPSM") ,
    S_FABBISOGNO_POSTI_SOSTEGNO_MMII ("FPSS","FPSS") ,
 
    S_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE ("FPPE","FPPE") , //  RF044– Fabbisogno di posti di Potenziamento ( primaria  )
    S_FABBISOGNO_POSTI_POTENZIAMENTO_MM ("MICIOMICIO","BAUBAU") , // RF044– Fabbisogno di posti di Potenziamento  ( primaria II , primaria I ) componente generico
    S_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SOSTEGNO ("MICIOMICIO","BAUBAU") , // RF044– Fabbisogno di posti di Potenziamento DI SOSTEGNO ( primaria II , primaria I ) componente generico
    S_FABBISOGNO_POSTI_POTENZIAMENTO_MMI ("FPPM","FPPM") ,
    S_FABBISOGNO_POSTI_POTENZIAMENTO_SS ("FPPS","FPPS") ,
    S_FABBISOGNO_POSTI_POTENZIAMENTO_MMI_SOSTEGNO ("SPPM","SPPM") ,
    S_FABBISOGNO_POSTI_POTENZIAMENTO_SS_SOSTEGNO ("SPPS","SPPS") ,
    
    S_FABBISOGNO_CONNESSO_FORMAZIONE ("FCAF","FCAF") , // RF047–  Fabbisogno connesso a formazione
	DATI_PLESSO("PL","PL"); // componente generico per usato in per es: RF019   
	
	
	
	private BeanValueDTO bean;

	TIPO_COMPONENTE(String value, String label) {

		bean = new BeanValueDTO(value, label);
	}

	public String code() {
		return bean.getValue();
	}

	public BeanValueDTO getBean() {
		return bean;
	}

	public static TIPO_COMPONENTE getInstanceFromCode(String value){
		TIPO_COMPONENTE instance = null;
		for (TIPO_COMPONENTE elem : TIPO_COMPONENTE.values()){
			if (elem.getBean().getValue().equals(value)){
				instance = elem;
				break;
			}
		}
		return instance;
	}
	
}
