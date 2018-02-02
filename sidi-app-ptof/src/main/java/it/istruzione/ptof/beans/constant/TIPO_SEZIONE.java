package it.istruzione.ptof.beans.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import it.istruzione.ptof.beans.BeanValueDTO;
import it.istruzione.ptof.beans.documenti.componenti.ComponenteArticolazioneClassiAADTO;

public enum TIPO_SEZIONE {
	
	SEZIONE_01_INDICE ("gestione-00-sezione-04-organizzazione", "1","COPERTINA ED INDICI"),
	SEZIONE_02_FINITER ("gestione-00-sezione-04-organizzazione", "2","FINALITÀ E ITER"),
	SEZIONE_03_STORIA ("gestione-00-sezione-04-organizzazione", "4","STORIA E IDENTITA' DELL'ISTITUZIONE SCOLASTICA"),
	SEZIONE_04_ORGANIZZAZIONE ("gestione-00-sezione-04-organizzazione", "5","ORGANIZZAZIONE DELL'ISTITUZIONE SCOLASTICA"),
	SEZIONE_05_CONTESTO_SOCIOECO ("gestione-00-sezione-04-organizzazione", "45","CONTESTO SOCIO-ECONOMICO E CULTURALE"), // RF011– Contesto Socio-Economico e Culturale –
	SEZIONE_06_FABBISOGNI ("gestione-00-sezione-04-organizzazione", "7","FABBISOGNI DELLA POPOLAZIONE DI RIFERIMENTO"), // RF012– Fabbisogni della popolazione di riferimento
	SEZIONE_07_MISSIONVISION  ("gestione-00-sezione-04-organizzazione", "9","Mission e Vision"), // RF014– Mission e Vision
	
	SEZIONE_08_OBBIETTIVI  ("gestione-00-sezione-08-obbiettivi", "10","OBIETTIVI PRIORITARI"), // RF015
	SEZIONE_09_OBBIETTIVIMIGLIORAMENTO  ("gestione-00-sezione-09-obbiettivi-miglioramento", "11","OBIETTIVI MIGLIORAMENTO"),// RF016
	
	/** SEZIONE 6 **/
	SEZIONE_29_ARTICOLAZIONE_ORARIA("gestione-00-sezione-21-articolazione-oraria", "13","ARTICOLAZIONE ORARIA "),//RF018– Articolazione Oraria
	SEZIONE_10_DISCIPLINE  ("gestione-00-sezione-04-organizzazione", "14","OBIETTIVI MIGLIORAMENTO"), // RF019
	SEZIONE_11_PROGETTICV  ("gestione-00-sezione-11-progetticv", "15","PROGETTI CURRICULARI"), // RF020 // LA FUNZIONE  e PER PLESSO
	SEZIONE_12_PROGETTI_EXSTRA  ("gestione-00-sezione-11-progetticv", "16","PROGETTI EXSTRA CURRICULARI"), // RF021– Progetti extra-curriculari educativi  // LA FUNZIONE  e PER PLESSO
	SEZIONE_13_PROGETTI_ALTRI_PROGETTI_EXTRA_CURRICULARI ("gestione-00-sezione-12-progetti-exstra", "17","PROGETTI EXSTRA CURRICULARI"),  //	RF022– Altri Progetti extra-curriculari deve contenere un componente S_ALTRI_PROGETTI_CURRICULARI_EXSTRA
	SEZIONE_14_ALTRE_INIZIATIVE_DIDATTICO_EDUCATIVE ("gestione-00-sezione-13-altre-attivita-dida", "19","ALTRE INIZIATIVE DIDATTICO/EDUCATIVE"),  //RF024–  Altre iniziative didattico/educative
	SEZIONE_15_ATTIVITA_SOSTEGNO ("gestione-00-sezione-14-attivita-sostegno", "20","ATTIVITA DI SOSTEGNO"),  //RF025–
	
	SEZIONE_30_ALTERNANZA_SCUOLA_LAVORO("gestione-00-sezione-22-alternanza-scuola-lavoro", "18","ALTERNANZA SCUOLA LAVORO"),//RF023– Alternanza Scuola Lavoro
	
	/** SEZIONE 7 **/
	SEZIONE_16_ORGANIZZAZIONE_RISORSE("gestione-00-sezione-15-organizzazione-risorse", "22","ORGANIZZAZIONE DELLE RISORSE E DEI PROCESSI "),  //RF027–
	SEZIONE_17_ORGANIZZAZIONE_CLASSI("gestione-00-sezione-16-organizzazione-classi", "23","ORGANIZZAZIONE CLASSI"),  //RF028– ( Componenti ComponenteArticolazioneClassiMMDTO ) 
	SEZIONE_18_APPROCCI_METODOLOGIE("gestione-00-sezione-04-organizzazione", "24","APPROCCI E METODOLOGIE"),   //  RF029– Approcci e Metodologie  
	SEZIONE_19_APPROCCI_METODOLOGIE("gestione-00-sezione-04-organizzazione", "25","MODALITA OPERATIVE"),   //  – RF030  Modalità Operative   
	
	/** SEZIONE 8 **/
	SEZIONE_20_PROGRAMMAZIONE_FORMAZIONE_DOC("gestione-00-sezione-17-programmazione-formazione", "27","PROGRAMMAZIONE DELLA FORMAZIONE AL PERSONALE DOCENTE"),//- RF032–  Programmazione Della Formazione Al Personale Docente
	SEZIONE_21_PROGRAMMAZIONE_FORMAZIONE_AMM("gestione-00-sezione-17-programmazione-formazione", "28","PROGRAMMAZIONE DELLA FORMAZIONE AL PERSONALE DOCENTE"),//- RF033–  Programmazione Della Formazione al Personale Amministrativo
	SEZIONE_22_PROGRAMMAZIONE_FORMAZIONE_TEC("gestione-00-sezione-17-programmazione-formazione", "29","PROGRAMMAZIONE DELLA FORMAZIONE AL PERSONALE DOCENTE"),//- RF034–  Programmazione Della Formazione al Personale Tecnico
	SEZIONE_23_PROGRAMMAZIONE_FORMAZIONE_AUS("gestione-00-sezione-17-programmazione-formazione", "30","PROGRAMMAZIONE DELLA FORMAZIONE AL PERSONALE DOCENTE"),//- RF035–  Programmazione Della Formazione al Personale Ausiliario
	SEZIONE_24_ALTRI_INTERVENTI_FORMAZIONE_PERSONALE("gestione-00-sezione-04-organizzazione", "31","ALTRI INTERVENTI PER LA FORMAZIONE DEL PERSONALE"),//- RF036–  Altri interventi per la formazione del personale
	
	/** SEZIONE 9 **/
	SEZIONE_25_STRUMENTI_ATTREZZATURE_TECNOLOGIA   ("gestione-00-sezione-18-strumenti-attrezzature-tecn", "32","STRUMENTI E ATTREZZATURE E TECNOLOGIA"),//- RF037– Strumenti e attrezzature e tecnologia   
	
	/** SEZIONE 10 **/
	SEZIONE_26_COLLABORAZIONI_CON_ENTI_LOCALI_TERRITORIO  ("gestione-00-sezione-19-coll-entilocali-territori", "33","COLLABORAZIONI CON ENTI LOCALI E TERRITORIO"),//-RF038– Collaborazioni con enti locali e territorio   
	
	/** SEZIONE 11 **/
	SEZIONE_27_PIANIFICAZIONE_INTERVENTI_MONITORAGGIO  ("gestione-00-sezione-20-pianificazione-interventi-monitoraggio", "34","PIANIFICAZIONE INTERVENTI E MONITORAGGIO"),//-RF039–  Pianificazione interventi e monitoraggio   
	
	/** SEZIONE 12 **/
	SEZIONE_28_ALTRE_INFORMAZIONI ("gestione-00-sezione-04-organizzazione", "35","ALTRE INFORMAZIONI"), // RF040– Altre Informazioni

	/** SEZIONE 13 **/
	SEZIONE_13_FABBISOGNO ("", "36","FABBISOGNO POSTI"), // RF048– Fabbisogno posti PADRE	
	
	SEZIONE_34_FABBISOGNO_ATTREZZATURE_INFRASTRUTTURE ("gestione-00-sezione-25-fabbisogno-attrezzature-infrastrutture", "41","FABBISOGNO DI ATTREZZATURE E INFRASTRUTTURE"), // RF048– Fabbisogno di attrezzature e infrastrutture	
	SEZIONE_38_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI ("gestione-00-sezione-29-fabbisogno-posti-personale-amm-tec-ausi","47","FABISOGNO DEI POSTI DI PERSONALE AMMINISTRATIVO, TECNICO E AUSILIARE"), // RF045- Fabbisogno dei posti di personale amministrativo, tecnico e ausiliare [AMM] AMMINISTRATORE [TEC] TECNICO [AUSI] AUSILIARE

	SEZIONE_35_FABBISOGNO_POSTI_CATTEDRE ("gestione-00-sezione-26-fabbisogno-posti-cattedre", "37","FABBISOGNO DI ATTREZZATURE E INFRASTRUTTURE"), // RF042– Fabbisogno di posti e cattedre  TODO MICHELE
	SEZIONE_36_FABBISOGNO_POSTI_SOSTEGNO ("gestione-00-sezione-27-fabbisogno-posti-sostegno", "46","FABBISOGNO DI ATTREZZATURE E INFRASTRUTTURE"), // RF043– Fabbisogno di posti di sostegno TODO MICHELE
	SEZIONE_37_FABBISOGNO_POSTI_POTENZIAMENTO ("gestione-00-sezione-28-fabbisogno-posti-potenziamento", "38","FABBISOGNO DI ATTREZZATURE E INFRASTRUTTURE"), // RF044– Fabbisogno di posti di potenziamento  TODO MICHELE

	SEZIONE_39_FABBISOGNO_CONNESSO_PROGETTO("gestione-00-sezione-30-fabbisogno-connesso-progetto","39","FABBISOGNO CONNESSO A PROGETTO"), // RF046–  Fabbisogno connesso a progetto
	SEZIONE_40_FABBISOGNO_CONNESSO_FORMAZIONE("gestione-00-sezione-31-fabbisogno-connesso-formazione","40","FABBISOGNO CONNESSO A FORMAZIONE"), // RF047–  Fabbisogno connesso a formazione
	
	/** SEZIONE 14  **/ 
	SEZIONE_33_PIANO_NAZIONALE_SCUOLA_DIGITALE("gestione-00-sezione-24-piano-nazionale-scuola-digitale","42","PIANO NAZIONALE SCUOLA DIGITALE"), // F049–  Piano Nazionale della Scuola Digitale

	/** SEZIONE 15 **/
	SEZIONE_31_ALLEGATI ("gestione-00-sezione-04-organizzazione", "43","ALLEGATI"), // RF050– Allegati
	
	/** SEZIONE 16 **/
	SEZIONE_32_DATI_FINALI_SCUOLA("gestione-00-sezione-23-dati-finali-scuola","44","DATI FINALI SCUOLA"); // RF051– Dati Finali Scuola
	
	
	/**
	 * utilizza dal fronend per indetificare la funzione da caricare
	 * NON TOCCARE!!
	 */
	private String key;
	
	/**
	 * utilizzato dal bake-end per indetificare la funzione  
	 * il value deve essere il campo PRG_SEZ_SOT_SEZ numerico
	 */
	private BeanValueDTO bean;

	TIPO_SEZIONE(String key ,String value, String label) {
		this.key= key;
		bean = new BeanValueDTO(value, label);
	}
	
	@JsonValue
	public String code() {
		return bean.getValue();
	}

	public BeanValueDTO getBean() {
		return bean;
	}

	public static TIPO_SEZIONE getInstanceFromCode(String value){
		TIPO_SEZIONE instance = null;
		for (TIPO_SEZIONE elem : TIPO_SEZIONE.values()){
			if (elem.getBean().getValue().equals(value)){
				instance = elem;
				break;
			}
		}
		return instance;
	}

	public String key() {
		return key;
	}
 
}
