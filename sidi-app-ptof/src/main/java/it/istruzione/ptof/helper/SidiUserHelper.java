package it.istruzione.ptof.helper;

import it.istruzione.commons.security.SidiContesto;
import it.istruzione.ptof.constant.AppConstant;

public class SidiUserHelper {
	public static boolean isContestoAmministrativo(SidiContesto sidiContesto){
		return sidiContesto.getCodice().equals(AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE) ||
				sidiContesto.getCodice().equals(AppConstant.TIPO_CONTESTO_NAZIONE);
	}
	
	public static boolean isContestoUfficioCentrale(SidiContesto sidiContesto){
		return sidiContesto.getCodice().equals(AppConstant.TIPO_CONTESTO_UFFICIO_CENTRALE);
	}
}
