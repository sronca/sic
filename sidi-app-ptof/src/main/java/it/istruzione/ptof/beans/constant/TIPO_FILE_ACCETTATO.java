package it.istruzione.ptof.beans.constant;

import java.util.regex.Pattern;

public enum TIPO_FILE_ACCETTATO{
	
   PDF("((?i)(pdf))$","pdf"),JPEG("((?i)(jpg|jpeg))$","jpg o jpeg"), ZIP("((?i)(zip))$","zip");
	
   // valori di default	 
   private String rex, desc;
   
   private Pattern pat ; 
   
   TIPO_FILE_ACCETTATO(String rex , String desc ){
	  this.rex = rex;
	  this.desc = desc;
	  pat= Pattern.compile(rex);
	}

	public String getRex() {
		return rex;
	}

	public void setRex(String rex) {
		this.rex = rex;
	}
   
	public boolean isValidFileName ( String estenzioneFile) {
		if ( estenzioneFile== null ) {
			return false;
		}
		
		return pat.matcher(estenzioneFile).matches();
	}

	public String getDesc() {
		return desc;
	}
    
}
