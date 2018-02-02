package it.istruzione.cercalatuascuola.ricerca.dao;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.common.util.Constants.HtmlInput;
import it.istruzione.cercalatuascuola.common.util.Utility;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOAndamento;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOAnniCorsoAlunni;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOAttivitaServizio;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOAttrezzatureMultimediali;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOClasse;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOCommon;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VORicerca;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOScuola;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOServizi;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOServizio;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOSuccursale;
import it.istruzione.cercalatuascuola.ricerca.dao.model.VOTipologiaServizio;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class CercaLaTuaScuolaDAOImpl extends BaseDAO implements CercaLaTuaScuolaDAO
{
	private static Logger log = Logger.getLogger(CercaLaTuaScuolaDAO.class);
	
    private static final String TIPO_RICERCA_RAPIDA = "RAPIDA";
    private static final String TIPO_RICERCA_VICINO_A_TE = "VICINO_A_TE";
    private static final String TIPO_RICERCA_AVANZATA = "AVANZATA";
	
    @Value("${anno.scolastico.indirizzi.studio}")
    private String ANNO_SCOLASTICO_INDIRIZZI_STUDIO;
	
	public static final String LICEI = "Licei";
	public static final String TECNICI = "Tecnici";
	public static final String PROFESSIONALI = "Professionali_-_Diploma_quinquennale";
	public static final String PROFESSIONALI_PQ = "Professionali_-_Diploma_quinquennale_+_qualifica_IeFP";
	public static final String PROFESSIONALI_Q3 = "Professionali_-_Percorso_triennale_(solo_qualifica_IeFP)";
	public static final String PROFESSIONALI_Q4 = "Professionali_-_Percorso_quadriennale_(diploma_IeFP)";
	public static final String CLASSI_SCUOLE = " ('PR','EI','PQ','Q3','Q4') ";

	public CercaLaTuaScuolaDAOImpl(DataSource dataSource) throws SQLException, NamingException
	{
		super(dataSource);
	}
	
	public List<VOScuola> getListaScuoleRicerca(VORicerca voRicerca) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOScuola> lista = new ArrayList<VOScuola>();
		String latitudinePartenza = "";
		String longitudinePartenza = "";
		
		log.debug("in getListaScuoleRicerca : parametri di ricerca");
		log.debug(ReflectionToStringBuilder.toString(voRicerca,ToStringStyle.MULTI_LINE_STYLE));

		try
		{
			/** adeguamento parametri del form - inizio **/
			if(voRicerca.getCodiceRegione().indexOf("_") != -1){
				voRicerca.setCodiceRegione(voRicerca.getCodiceRegione().substring(0,voRicerca.getCodiceRegione().indexOf("_")));
			}
			
			if(voRicerca.getCodiceProvincia().indexOf("_") != -1){
				voRicerca.setCodiceProvincia(voRicerca.getCodiceProvincia().substring(0,voRicerca.getCodiceProvincia().indexOf("_")));
			}
			
			if(voRicerca.getCodiceComune().indexOf("_") != -1){
				voRicerca.setCodiceComune(voRicerca.getCodiceComune().substring(0,voRicerca.getCodiceComune().indexOf("_")));
			}
			
			if(voRicerca.getCodiceOrdine().equals("4")
					&& voRicerca.getRadioTipoScuola().equals("Statale") ){
				voRicerca.setCheckStatale("S");
				voRicerca.setCheckNonStatale("");
			}
			
			if(voRicerca.getCodiceOrdine().equals("4")
					&& voRicerca.getRadioTipoScuola().equals("NonStatale") ){
				voRicerca.setCheckStatale("");
				voRicerca.setCheckNonStatale("S");
			}
			
			/** adeguamento parametri del form - fine **/


			StringBuffer query = new StringBuffer();
			//TODO GESTIRE ANNO ACCADEMICO DA PARAMETRO String annoAccademico = annoAccademico(voRicerca.getAnnoAccademico());
			String annoAccademico = annoAccademico("N");

			if (voRicerca.getLatIndirizzoRiferimento() != null && voRicerca.getLatIndirizzoRiferimento().trim().length() > 0
					&& voRicerca.getLngIndirizzoRiferimento() != null && voRicerca.getLngIndirizzoRiferimento().trim().length() > 0)
			{
				latitudinePartenza = voRicerca.getLatIndirizzoRiferimento();
				longitudinePartenza = voRicerca.getLngIndirizzoRiferimento();				
			}

			//prima query per indirizzi di studio scuole statali
			String codTipologiaDecodificato = voRicerca.getCodiceTipologiaStataleNuovoOrdinamento() != null ? voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().replace("_", " ") : null;
			String codSettoreDecodificato = voRicerca.getCodiceSettoreScuola() != null ? voRicerca.getCodiceSettoreScuola().replace("_", " ") : null;

			List<VOCommon> indirizziStudio = new ArrayList<VOCommon>();
			Object[] campi = new Object[0];
			int[] tipi = new int[0];
						
			//if("S".equals(voRicerca.getCheckStatale()) && voRicerca.getCodiceOrdine().equals("4")){
			if(voRicerca.getCodiceOrdine().equals("4")){
				if (voRicerca.getCodiceTipologiaStataleNuovoOrdinamento() != null && voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().trim().length() > 0){
				
					if(voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().equals(PROFESSIONALI_Q3) || voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().equals(PROFESSIONALI_Q4)){
						if (voRicerca.getCodiceIndirizzo() != null && voRicerca.getCodiceIndirizzo().trim().length() > 0){	
							campi = new Object[] {codTipologiaDecodificato, voRicerca.getCodiceIndirizzo()};
							tipi = new int[] {Types.VARCHAR, Types.VARCHAR };
							
							query.append("select trs.des_ind_cod, trs.cod_ind_min, trs.cod_cla_min from trs1051_indminmodifica trs ")
							.append(" where trs.DES_PER_COD = ? ")
							.append(" and trs.COD_IND_MIN = ? ")
							.append(" and cod_cla_min in ('Q3','Q4') ")
							.append(" and trs.DAT_INI_VAL <= SYSDATE ")
							.append(" and (trs.DAT_FIN_VAL IS NULL OR trs.DAT_FIN_VAL >= SYSDATE) ");
						}
						else{
							campi = new Object[] {codTipologiaDecodificato};
							tipi = new int[] {Types.VARCHAR};
							
							query.append("select trs.des_ind_cod, trs.cod_ind_min, trs.cod_cla_min from trs1051_indminmodifica trs ")
							.append(" where trs.DES_PER_COD = ? ")
							.append(" and cod_cla_min in ('Q3','Q4') ")
							.append(" and trs.DAT_INI_VAL <= SYSDATE ")
							.append(" and (trs.DAT_FIN_VAL IS NULL OR trs.DAT_FIN_VAL >= SYSDATE) ");
						}
					}
					else {				
						String biennioTriennioCondition = "";
		    			if((voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().equals(LICEI) && voRicerca.getCodiceTipologiaStataleNuovoOrdinamento() != null && voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().equals("ARTISTICO")) || 
		    					voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().equals(TECNICI) || voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().equals(PROFESSIONALI)){
			    			if(voRicerca.getRadioBiennioTriennio() != null && voRicerca.getRadioBiennioTriennio().equals("Biennio"))
			    				biennioTriennioCondition = " and per_ann_ini_val in (1, 2) and num_dur_ann in (1, 2, 5, 6) ";	
				     		else if(voRicerca.getRadioBiennioTriennio() != null && voRicerca.getRadioBiennioTriennio().equals("Triennio"))
				     			biennioTriennioCondition = " and ((per_ann_ini_val in (3, 4, 5, 6) and num_dur_ann in (1, 2, 3, 4)) or (per_ann_ini_val in (1) and num_dur_ann in (5, 6))) ";
				    		else
				    			biennioTriennioCondition = " ";
						}
						
						if (voRicerca.getCodiceSettoreScuola() != null && voRicerca.getCodiceSettoreScuola().trim().length() > 0){
							if (voRicerca.getCodiceIndirizzo() != null && voRicerca.getCodiceIndirizzo().trim().length() > 0){	
								campi = new Object[] {codTipologiaDecodificato, codSettoreDecodificato, voRicerca.getCodiceIndirizzo()};
								tipi = new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
								
								query.append("select trs.des_ind_cod, trs.cod_ind_min, trs.cod_cla_min from trs1051_indminmodifica trs ")
								.append(" where trs.DES_PER_COD = ? ")
								.append(" and trs.DES_SET_COD = ? ")
								.append(" and trs.COD_IND_MIN = ? ")
								.append(" and cod_cla_min in ('PR','EI','PQ') ")
								.append(biennioTriennioCondition)
								.append(" and DAT_INI_VAL <= SYSDATE ")
								.append(" and (DAT_FIN_VAL IS NULL OR DAT_FIN_VAL >= SYSDATE) ");
							}
							else{
								campi = new Object[] {codTipologiaDecodificato, codSettoreDecodificato};
								tipi = new int[] {Types.VARCHAR, Types.VARCHAR};
								
								query.append("select trs.des_ind_cod, trs.cod_ind_min, trs.cod_cla_min from trs1051_indminmodifica trs ")
								.append(" where trs.DES_PER_COD = ? ")
								.append(" and trs.DES_SET_COD = ? ")
								.append(" and cod_cla_min in ('PR','EI','PQ') ")
								.append(biennioTriennioCondition)
								.append(" and DAT_INI_VAL <= SYSDATE ")
								.append(" and (DAT_FIN_VAL IS NULL OR DAT_FIN_VAL >= SYSDATE) ");
							}
						}
						else{
							campi = new Object[] {codTipologiaDecodificato};
							tipi = new int[] {Types.VARCHAR};
							
							query.append("select trs.des_ind_cod, trs.cod_ind_min, trs.cod_cla_min from trs1051_indminmodifica trs ")
							.append(" where trs.DES_PER_COD = ? ")
							.append(" and cod_cla_min in ('PR','EI','PQ') ")
							.append(biennioTriennioCondition)
							.append(" and DAT_INI_VAL <= SYSDATE ")
							.append(" and (DAT_FIN_VAL IS NULL OR DAT_FIN_VAL >= SYSDATE) ");
						}
					}

					log.debug("QUERY getListaScuoleRicercaVicinoATe getIndirizziStudioTipologica: " + query.toString());
					log.debug("PARAMETRI getListaScuoleRicercaVicinoATe getIndirizziStudioTipologica: "+
							"codiceTipologia = "+codTipologiaDecodificato+
							" - codiceSettore = "+codSettoreDecodificato+
							" - codiceIndirizzo = "+voRicerca.getCodiceIndirizzo());

					ps = con.prepareStatement(query.toString());
					rs = eseguiPreparedQuery(ps, campi, tipi,false);

					while (rs.next())
					{
						VOCommon vo = new VOCommon();
						vo.setId(rs.getString("cod_cla_min"));
						vo.setCodice(rs.getString("cod_ind_min"));
						vo.setDescrizione(rs.getString("des_ind_cod"));
						indirizziStudio.add(vo);
					}
					log.debug("RISULTATI getListaScuoleRicerca getIndirizziStudioTipologica:");
					for(int i=0; i<indirizziStudio.size(); i++){
						VOCommon vo = indirizziStudio.get(i);
						log.debug("elemento n."+i+" - cod_ind_min : " + vo.getCodice() + " - cod_cla_min : " + vo.getId() + " - des_ind_cod : " + vo.getDescrizione() + "\n");
					}
				}
			}

			query = new StringBuffer();

			query.append("SELECT DISTINCT ist.COD_SCU_UT, ist.COD_SCU_UT_PRI, ist.DAT_ANN_SCO_RIL, ")			     		
			.append("ist.COD_FOR_SCU_APP, reg.DES_REG, prv.DES_PRV, prv.COD_PRV, com.DES_COM, ")
			.append("ist.FLG_IST_STA, ist.FLG_SCU_PAR, ist.COD_TIP_SIT, ist.DAT_ANN_SCO_RIL, ")
			.append("ist.DES_NOM_SCU, ist.COD_CAP_SCU, ist.DES_IND_SCU, ist.DES_IND_EML, ")			     
			.append("ist.COD_TEL_SCU, ist.COD_FAX_SCU, ist.FLG_SED_DIR, infoscu.NUM_LAT_SCU, ist.DES_IND_EMA_PCE, ist.DES_IND_WEB, ");

			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_VICINO_A_TE)){
				if (!latitudinePartenza.equals("") && !longitudinePartenza.equals("")) {

					query.append("(acos(cos((6.28*").append(longitudinePartenza).append("/360)-(6.28*infoscuola.num_lon_scu/360))*cos(6.28*") 
					.append(latitudinePartenza).append("/360)*cos(6.28*infoscuola.num_lat_scu/360)+sin(6.28*") 
					.append(latitudinePartenza).append("/360)*sin(6.28*infoscuola.num_lat_scu/360)))*6360 AS RAGGIO, ");								
				}
			}
			

			query.append("infoscu.NUM_LON_SCU, ist.DEN_SCU_PRI ")
			.append("FROM ")
			.append("MFG1002_ANAGISTSCOL ist ")
			.append("FULL OUTER JOIN TRS1005_INFGENSCUOLA infoscu ON(ist.COD_FOR_SCU_APP = infoscu.COD_SCU_FOR_APP), ")
			.append("MFG1012_REGIONE reg, ")
			.append("MFG1013_PROVINCIA prv, ");

			if (voRicerca.getCodiceOrdine().equals("4")) {
				//if("S".equals(voRicerca.getCheckStatale())) {
					if (voRicerca.getCodiceTipologiaStataleNuovoOrdinamento() != null && voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().trim().length() > 0){
						query.append("MFG1054_SCUINDSCUMIN indirizziStudio, ");
					}
				//}
			}

			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_VICINO_A_TE)){
				if (!latitudinePartenza.equals("") && !longitudinePartenza.equals("")) {

					query.append(" TRS1005_INFGENSCUOLA infoscuola, ");
				}
			}

			query.append("MFG1014_COMUNE com ")
			.append(" WHERE ist.DAT_ANN_SCO_RIL = ").append(annoAccademico)				 
			.append(" AND ist.DAT_INI_VAL <= SYSDATE ")
			.append(" AND (ist.DAT_FIN_VAL IS NULL OR ist.DAT_FIN_VAL >= SYSDATE) ")
			.append(" AND ist.COD_COM = com.COD_COM ")
			.append(" AND com.COD_PRV = prv.COD_PRV ")
			.append(" AND prv.COD_REG = reg.COD_REG ");

			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_AVANZATA)){
				if (voRicerca.getCodiceRegione() != null && voRicerca.getCodiceRegione().trim().length() > 0)
					query.append("AND reg.COD_REG = '" + voRicerca.getCodiceRegione().trim() + "' ");

				if (voRicerca.getCodiceProvincia() != null && voRicerca.getCodiceProvincia().trim().length() > 0)
					query.append("AND prv.COD_PRV = '" + voRicerca.getCodiceProvincia().trim() + "' ");

				if (voRicerca.getCodiceComune() != null && voRicerca.getCodiceComune().trim().length() > 0)
					query.append("AND com.COD_COM = '" + voRicerca.getCodiceComune().trim() + "' ");
			}

			if(!voRicerca.getCheckStatale().equals(voRicerca.getCheckNonStatale())) {
				if ("S".equals(voRicerca.getCheckStatale())) {
					query.append("AND ist.FLG_IST_STA = '0' ");					
				}	
				else if ("S".equals(voRicerca.getCheckNonStatale())) {
					query.append("AND ist.FLG_IST_STA = '1' ");
					query.append("AND ist.FLG_SCU_PAR = '1' ");
				}
			}
			else {
				query.append("AND (ist.FLG_IST_STA = '0' OR ")
				.append("(ist.FLG_IST_STA = '1' AND ist.FLG_SCU_PAR = '1')) ");
			}

			if(voRicerca.getCodiceOrdine().equals("1")) {
				query.append("AND ist.COD_TIP_SIT = 'AA' ");
				query.append("AND (ist.FLG_IST_STA = '1' OR SUBSTR(ist.COD_SCU_UT, 8, 2) > '00') ");
			}	
			else if(voRicerca.getCodiceOrdine().equals("2")) {
				query.append("AND ist.COD_TIP_SIT = 'EE' ");
				query.append("AND (ist.FLG_IST_STA = '1' OR SUBSTR(ist.COD_SCU_UT, 8, 2) > '00') ");
				if(voRicerca.getCodiceTempoPrimaria() != null && !"".equals(voRicerca.getCodiceTempoPrimaria())){
					query.append("AND ist.COD_SCU_UT in (SELECT COD_SCU_UT from MFG1053_SCUTEMPOSCUOLA ")
				    .append("where PRG_TEM_SCU = " + voRicerca.getCodiceTempoPrimaria() + " ")
				    .append("and COD_ORD_SCU = 'EE' ")
					.append("and PER_ANN_SCO_VAL = " + annoAccademico + ") ");
				}
			}	
			else if(voRicerca.getCodiceOrdine().equals("3")) {
				query.append(" AND ist.COD_TIP_SIT = 'MM' ");
				if(voRicerca.getCodiceTempoSecondaria1Grado() != null && !"".equals(voRicerca.getCodiceTempoSecondaria1Grado())){
					query.append("AND ist.COD_SCU_UT in (SELECT COD_SCU_UT from MFG1053_SCUTEMPOSCUOLA ")
				    .append("where PRG_TEM_SCU = " + voRicerca.getCodiceTempoSecondaria1Grado() + " ")
				    .append("and COD_ORD_SCU = 'MM' ")
					.append("and PER_ANN_SCO_VAL = " + annoAccademico  + ") ");
				}
				if(voRicerca.getCheckIndirizzoMusicale() != null){ 
                    if("S".equals(voRicerca.getCheckIndirizzoMusicale())){
				    	query.append("AND ist.COD_SCU_UT in (SELECT COD_SCU_UT from MFG1054_SCUINDSCUMIN ")
				        .append("where COD_IND_MIN = (SELECT COD_IND_MIN from MFG1052_INDSCUMIN ")
				        .append("where COD_CLA_MIN = 'MM' and DES_IND = 'MUSICALE') and PER_ANN_SCO_VAL = " + annoAccademico +") ");
                    }
				}	
			}	
			else if(voRicerca.getCodiceOrdine().equals("4")) {
				query.append(" AND (ist.COD_TIP_SIT > 'MM') ");
			}
			else if(voRicerca.getCodiceOrdine().equals("6")) {
				query.append(" AND (ist.COD_CAR_SCU = '1') ");
			}

			query.append(" AND (COD_CAR_SCU IS NULL OR (COD_CAR_SCU <> 'O' AND COD_CAR_SCU <> 'R')) ");
			query.append(" AND COD_TIP_SIT NOT IN ('IC', 'IS', 'SM', 'SN', 'SQ', 'SR', 'ST', 'CT') ");

			if(voRicerca.getCodiceOrdine().equals("4")) {
//				if("S".equals(voRicerca.getCheckStatale())) {											
					if (voRicerca.getCodiceTipologiaStataleNuovoOrdinamento() != null && voRicerca.getCodiceTipologiaStataleNuovoOrdinamento().trim().length() > 0) {
						String annoAcc = ANNO_SCOLASTICO_INDIRIZZI_STUDIO;
						log.info("annoAcc: " + annoAcc);
						if(annoAcc == null || annoAcc.trim().equals("")){
							annoAcc = annoAccademico;
						}

						
						query.append(" AND indirizziStudio.PER_ANN_SCO_VAL = " + annoAcc)
						.append(" AND indirizziStudio.COD_SCU_UT = ist.COD_SCU_UT")
						.append(" AND ist.DAT_ANN_SCO_RIL = " + annoAccademico);		

						if(indirizziStudio != null && !indirizziStudio.isEmpty()){
							if(indirizziStudio.size() == 1){
								VOCommon vo = indirizziStudio.get(0);
								query.append(" AND (indirizziStudio.COD_IND_MIN = '" + vo.getCodice() + "'")
								.append(" AND indirizziStudio.COD_CLA_MIN = '" + vo.getId() + "')");
							}
							else{
								int maxI = indirizziStudio.size() - 1;
								for(int i=0; i<indirizziStudio.size();i++){
									VOCommon vo = indirizziStudio.get(i);
									if(i==0){
										query.append(" AND ((indirizziStudio.COD_IND_MIN = '" + vo.getCodice() + "'")
										.append(" AND indirizziStudio.COD_CLA_MIN = '" + vo.getId() + "')");
									}
									else if(i==maxI){
										query.append(" OR (indirizziStudio.COD_IND_MIN = '" + vo.getCodice() + "'")
										.append(" AND indirizziStudio.COD_CLA_MIN = '" + vo.getId() + "'))");
									}
									else{
										query.append(" OR (indirizziStudio.COD_IND_MIN = '" + vo.getCodice() + "'")
										.append(" AND indirizziStudio.COD_CLA_MIN = '" + vo.getId() + "')");
									}
								}
							}
						}
					}
//				}
//				else if("S".equals(voRicerca.getCheckNonStatale())) {
//					if (voRicerca.getCodiceTipologiaNonStatale() != null && voRicerca.getCodiceTipologiaNonStatale().trim().length() > 0) {						
//						String codiceTipologiaNonStatale = voRicerca.getCodiceTipologiaNonStatale().substring(0,voRicerca.getCodiceTipologiaNonStatale().indexOf("_"));
//						if("LI".equals(codiceTipologiaNonStatale)) {
//							query.append(" AND (SUBSTR(ist.COD_SCU_UT, 3,1) = '").append("P").append("' ")
//							.append(" OR SUBSTR(ist.COD_SCU_UT, 3,1) = '").append("S").append("') ");
//						}
//						else if("IP".equals(codiceTipologiaNonStatale)) {
//							query.append(" AND SUBSTR(ist.COD_SCU_UT, 3,1) = '").append("R").append("' ");
//						}
//						else if("IT".equals(codiceTipologiaNonStatale)) {
//							query.append(" AND SUBSTR(ist.COD_SCU_UT, 3,1) = '").append("T").append("' ");
//						}
//					}	
//				}
			}

			if (voRicerca.getDenominazione() != null && voRicerca.getDenominazione().trim().length() > 0){
				query.append(" AND UPPER(ist.DES_NOM_SCU) LIKE '%" + voRicerca.getDenominazione().trim().toUpperCase().replace("'", "''") + "%' ");	
			}
			if (voRicerca.getCodMecc() != null && voRicerca.getCodMecc().trim().length() > 0) {
				query.append(" AND ist.COD_SCU_UT = '" + voRicerca.getCodMecc().trim().toUpperCase() + "' ");

				if(voRicerca.getCodMecc().trim().toUpperCase().substring(2, 4).equals("AA") ||
						voRicerca.getCodMecc().trim().toUpperCase().substring(2, 4).equals("EE")) {

					query.append("AND SUBSTR(ist.COD_SCU_UT, 8, 2) > '00' ");
				}	
			}	

			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_VICINO_A_TE)){
				if (!latitudinePartenza.equals("") && !longitudinePartenza.equals("")) {

					query.append(" AND infoscuola.COD_SCU_FOR_APP = ist.COD_FOR_SCU_APP ")
					.append(" AND infoscuola.num_lon_scu is not null ")
					.append(" AND infoscuola.num_lat_scu is not null ")
					.append(" AND (acos(cos((6.28*" + longitudinePartenza + "/360)-(6.28*infoscuola.num_lon_scu/360))*cos(6.28*" + latitudinePartenza + "/360)*cos(6.28*infoscuola.num_lat_scu/360)+sin(6.28*" + latitudinePartenza + "/360)*sin(6.28*infoscuola.num_lat_scu/360)))*6360 <= " + voRicerca.getRaggio());			
				}
			}

			query.append(" ORDER BY ist.DES_NOM_SCU ");

			log.info("QUERY getListaScuoleRicerca: " + query.toString());
			
			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();
			int e = 0;

			VOScuola voScuola = null;
			while (rs.next())
			{
				voScuola = new VOScuola();
				voScuola.setDescrizione(checkNull(rs.getString("DES_NOM_SCU")));
				voScuola.setCodMecc(checkNull(rs.getString("COD_SCU_UT")));
				voScuola.setDatAnnScoRil(checkNull(rs.getString("DAT_ANN_SCO_RIL")));
				voScuola.setDesAnnScoRil(checkNull(Utility.descrizioneAnnoAccademico(rs.getString("DAT_ANN_SCO_RIL"))));
				voScuola.setCodMeccIstRif(checkNull(rs.getString("COD_SCU_UT_PRI")));
				voScuola.setCodForte(checkNull(rs.getString("COD_FOR_SCU_APP")));
				voScuola.setIndirizzo(checkNull(rs.getString("DES_IND_SCU")));
				voScuola.setCap(checkNull(rs.getString("COD_CAP_SCU")));
				voScuola.setComune(checkNull(rs.getString("DES_COM")));
				voScuola.setProvincia(checkNull(rs.getString("DES_PRV")));
				voScuola.setProvinciaBreve(checkNull(rs.getString("COD_PRV")));
				voScuola.setRegione(checkNull(rs.getString("DES_REG")));
				voScuola.setTelefono(checkNull(rs.getString("COD_TEL_SCU")));
				voScuola.setFax(checkNull(rs.getString("COD_FAX_SCU")));
				voScuola.setIndirizzoEmail(checkNull(rs.getString("DES_IND_EML")));
				voScuola.setLatitudine(checkNull(rs.getString("NUM_LAT_SCU")));
				voScuola.setLongitudine(checkNull(rs.getString("NUM_LON_SCU")));
				voScuola.setIndirizzoEmailPEC(checkNull(rs.getString("DES_IND_EMA_PCE")));
				voScuola.setSitoWeb(checkNull(rs.getString("DES_IND_WEB")));
				voScuola.setDenScuPri(checkNull(rs.getString("DEN_SCU_PRI")));
				voScuola.setCodTipSit(checkNull(rs.getString("COD_TIP_SIT")));
				voScuola.setStataleNonStatale(checkNull(rs.getString("FLG_IST_STA")));


				if (voRicerca.getCoordinateIndirizzoRiferimento() != null && voRicerca.getCoordinateIndirizzoRiferimento().trim().length() > 0)
					voScuola.setRaggio(checkNull(rs.getString("RAGGIO")));


				if(checkNull(rs.getString("FLG_IST_STA")).equals("0")) {
					voScuola.setCheckStatale("S");
				}	
				else if (checkNull(rs.getString("FLG_IST_STA")).equals("1")) {
					voScuola.setCheckNonStatale("S");
				}	

				if(checkNull(rs.getString("FLG_SCU_PAR")).equals("1")) {
					voScuola.setCheckParitaria("S");
				}	
				else if(checkNull(rs.getString("FLG_SCU_PAR")).equals("0")) {
					voScuola.setCheckNonParitaria("S");
				}	
				else { 
					voScuola.setCheckNonParitaria("S");
				}	

				if(checkNull(rs.getString("COD_TIP_SIT")).equals("AA")) {
					voScuola.setOrdine("INFANZIA");
				}	
				else if(checkNull(rs.getString("COD_TIP_SIT")).equals("EE")) {
					voScuola.setOrdine("PRIMARIA");
				}	
				else if(checkNull(rs.getString("COD_TIP_SIT")).equals("MM")) {
					voScuola.setOrdine("SEC. I GRADO");
				}	
				else if(checkNull(rs.getString("COD_TIP_SIT")).equals("IC")) {
					voScuola.setOrdine("ISTITUTO COMPRENSIVO");
				}	
				else {
					voScuola.setOrdine("SEC. II GRADO");

					if(voScuola.getSettore() != null){
						if(voScuola.getSettore().equals("PQ")){
							voScuola.setTipologia("Professionale - Diploma quinquennale + qualifica IeFP");
						}
						else if(voScuola.getSettore().equals("Q3")){
							voScuola.setTipologia("Professionale - Percorso triennale (solo qualifica IeFP)");
						}
						else if(voScuola.getSettore().equals("Q4")){
							voScuola.setTipologia("Professionale - Percorso quadriennale (diploma IeFP)");
						}
						else{
							if(voScuola.getCodMecc().substring(2, 3).compareTo("P") == 0 ||
									voScuola.getCodMecc().substring(2, 3).compareTo("S") == 0) {												
								voScuola.setTipologia("Liceo");
							}
							else if(voScuola.getCodMecc().substring(2, 3).compareTo("T") == 0) {												
								voScuola.setTipologia("Istituto Tecnico");
							}
							else if(voScuola.getCodMecc().substring(2, 3).compareTo("R") == 0) {
								voScuola.setTipologia("Istituto Professionale");						
							}
						}
					}		
				}

				voScuola.setTipoIstituzione(voRicerca.getCodiceOrdine());

				voScuola.setIndice(""+e);
				e++;

				lista.add(voScuola);
			}
			log.debug("fine getListaScuoleRicerca");
		}
		catch (Exception e)
		{
			log.error("ERRORE getListaScuole : " + e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();			
			if (con != null)
				con.close();	
		}
		return lista;
	}



	
	
	
	public List<VOScuola> getListaScuoleRicercaRapida(String filter) throws Exception{
		

		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOScuola> lista = new ArrayList<VOScuola>();

		try
		{
			log.debug("QUERY getListaScuoleRicercaRapida INIZIO ");
			log.debug("PARAMETRI getListaScuoleRicercaRapida INIZIO: " + filter);
			List<String> keywords = new ArrayList<String>();
			filter = filter.replaceAll("'", "''").toUpperCase();
			log.debug("PARAMETRI : " + filter);
			StringTokenizer tokenizer = new StringTokenizer(filter," ");
			while (tokenizer.hasMoreTokens()){
				keywords.add(tokenizer.nextToken());
			}
			for (int i=0; i<keywords.size(); i++){
				log.debug("keyword " + i + ": " + keywords.get(i));
			}
			

			StringBuffer query = new StringBuffer();
					
			query.append("SELECT DISTINCT ");
			
			query.append(" SUM( ");		
			
			String piu = "";
			for (int i=0; i<keywords.size(); i++){
				query.append(" " + piu + " ");
				query.append(" (CASE ");
				query.append(" WHEN ((INSTR (MRS1001.CAMPO_RICERCA, CHR(32)||'" + keywords.get(i) + "'||CHR(32))) > 0) THEN 1 ");
				query.append(" WHEN ((INSTR (MRS1001.CAMPO_RICERCA, CHR(32)||'" + keywords.get(i) + "'||CHR(34))) > 0) THEN 1 ");
				query.append(" WHEN ((INSTR (MRS1001.CAMPO_RICERCA, CHR(34)||'" + keywords.get(i) + "'||CHR(32))) > 0) THEN 1 ");
				query.append(" ELSE 0 ");
				query.append(" END) ");
				piu = "+";
			}
			
			query.append(" ) NUMERO_OCCORRENZE_ESATTE, ");
			
			
			query.append("ist.COD_SCU_UT, ist.COD_SCU_UT_PRI, ist.DAT_ANN_SCO_RIL, ")			     		
			.append("ist.COD_FOR_SCU_APP, reg.DES_REG, prv.DES_PRV, prv.COD_PRV, com.DES_COM, ")
			.append("ist.FLG_IST_STA, ist.FLG_SCU_PAR, ist.COD_TIP_SIT, ist.DAT_ANN_SCO_RIL, ")
			.append("ist.DES_NOM_SCU, ist.COD_CAP_SCU, ist.DES_IND_SCU, ist.DES_IND_EML, ")			     
			.append("ist.COD_TEL_SCU, ist.COD_FAX_SCU, ist.FLG_SED_DIR, infoscu.NUM_LAT_SCU, ist.DES_IND_EMA_PCE, ist.DES_IND_WEB, ")
			.append("infoscu.NUM_LON_SCU, ist.DEN_SCU_PRI, mrs1001.FLG_CFP ")
			.append("FROM ")
			.append("MRS1001_RICERCARAPIDA MRS1001 ")
			.append("INNER JOIN MFG1002_ANAGISTSCOL ist ON (MRS1001.COD_SCU_UT = ist.COD_SCU_UT AND MRS1001.DAT_ANN_SCO_RIL = ist.DAT_ANN_SCO_RIL) ")
			.append("FULL OUTER JOIN TRS1005_INFGENSCUOLA infoscu ON(ist.COD_FOR_SCU_APP = infoscu.COD_SCU_FOR_APP), ")
			.append("MFG1012_REGIONE reg, ")
			.append("MFG1013_PROVINCIA prv, ")
			.append("MFG1014_COMUNE com ")
			.append(" WHERE ")				 
			.append(" ist.COD_COM = com.COD_COM ")
			.append(" AND com.COD_PRV = prv.COD_PRV ")
			.append(" AND prv.COD_REG = reg.COD_REG ")
			.append(" AND mrs1001.FLG_CFP = '0' ");			

			for (int i=0; i<keywords.size(); i++){
				query.append(" AND INSTR(MRS1001.CAMPO_RICERCA,'" + keywords.get(i) + "') > 0 ");	
			}
			query.append(" AND ( ");
			query.append(" ist.FLG_IST_STA = 1 OR ");
			query.append(" (SUBSTR (ist.cod_scu_ut, 3, 2) = 'AA' AND SUBSTR (ist.cod_scu_ut, 8, 2) > '00') OR ");
			query.append(" (SUBSTR (ist.cod_scu_ut, 3, 2) = 'EE' AND SUBSTR (ist.cod_scu_ut, 8, 2) > '00') OR ");
			query.append(" (SUBSTR (ist.cod_scu_ut, 3, 2) <> 'AA' AND SUBSTR (ist.cod_scu_ut, 3, 2) <> 'EE') ");
			query.append(" ) ");
			
			query.append(" group by ");
			query.append(" ist.cod_scu_ut, ist.cod_scu_ut_pri, ist.dat_ann_sco_ril, ");
			query.append(" ist.cod_for_scu_app, reg.des_reg, prv.des_prv, prv.cod_prv, ");
			query.append(" com.des_com, ist.flg_ist_sta, ist.flg_scu_par, ");
			query.append(" ist.cod_tip_sit, ist.dat_ann_sco_ril, ist.des_nom_scu, ");
			query.append(" ist.cod_cap_scu, ist.des_ind_scu, ist.des_ind_eml, ");
			query.append(" ist.cod_tel_scu, ist.cod_fax_scu, ist.flg_sed_dir, ");
			query.append(" infoscu.num_lat_scu, ist.des_ind_ema_pce, ist.des_ind_web, ");
			query.append(" infoscu.num_lon_scu, ist.den_scu_pri, mrs1001.flg_cfp ");
			
			query.append(" UNION ");
			
			query.append("SELECT DISTINCT ");
			
			query.append(" SUM( ");		
			
			piu = "";
			for (int i=0; i<keywords.size(); i++){
				query.append(" " + piu + " ");
				query.append(" (CASE ");
				query.append(" WHEN ((INSTR (MRS1001.CAMPO_RICERCA, CHR(32)||'" + keywords.get(i) + "'||CHR(32))) > 0) THEN 1 ");
				query.append(" WHEN ((INSTR (MRS1001.CAMPO_RICERCA, CHR(32)||'" + keywords.get(i) + "'||CHR(34))) > 0) THEN 1 ");
				query.append(" WHEN ((INSTR (MRS1001.CAMPO_RICERCA, CHR(34)||'" + keywords.get(i) + "'||CHR(32))) > 0) THEN 1 ");
				query.append(" ELSE 0 ");
				query.append(" END) ");
				piu = "+";
			}
			
			query.append(" ) NUMERO_OCCORRENZE_ESATTE, ");
			
			query.append(" ist.COD_CEN_FOR_PRO COD_SCU_UT, ist.COD_CEN_FOR_PRO COD_SCU_UT_PRI, ist.DAT_ANN_SCO_RIF DAT_ANN_SCO_RIL, ")			     		
			.append("ist.COD_CEN_FOR_PRO COD_FOR_SCU_APP, reg.DES_REG, prv.DES_PRV, prv.COD_PRV, com.DES_COM, ")
			.append("'0' FLG_IST_STA, '0' FLG_SCU_PAR, 'CP' COD_TIP_SIT, ist.DAT_ANN_SCO_RIF DAT_ANN_SCO_RIL, ")
			.append("ist.DEN_CFP DES_NOM_SCU, ist.COD_CAP_CFP COD_CAP_SCU, ist.DES_IND_CFP DES_IND_SCU, ist.DES_IND_EMA_CFP DES_IND_EML, ")			     
			.append("ist.COD_NUM_TEL_CFP COD_TEL_SCU, ist.COD_FAX_CFP COD_FAX_SCU, '0' FLG_SED_DIR, infoscu.NUM_LAT_SCU, ist.DES_IND_EMA_CER_CFP DES_IND_EMA_PCE, ist.DES_SIT_WEB_CFP DES_IND_WEB, ")
			.append("infoscu.NUM_LON_SCU, ist.DEN_CFP, mrs1001.FLG_CFP ")
			.append("FROM ")
			.append("MRS1001_RICERCARAPIDA MRS1001 ")
			.append("INNER JOIN MFG1047_CENTRIFORPRO ist ON (MRS1001.COD_SCU_UT = ist.COD_CEN_FOR_PRO AND MRS1001.DAT_ANN_SCO_RIL = ist.DAT_ANN_SCO_RIF) ")
			.append("FULL OUTER JOIN TRS1005_INFGENSCUOLA infoscu ON(ist.COD_CEN_FOR_PRO = infoscu.COD_SCU_FOR_APP), ")
			.append("MFG1012_REGIONE reg, ")
			.append("MFG1013_PROVINCIA prv, ")
			.append("MFG1014_COMUNE com ")
			.append(" WHERE ")				 
			.append(" ist.COD_COM_CFP = com.COD_COM ")
			.append(" AND com.COD_PRV = prv.COD_PRV ")
			.append(" AND prv.COD_REG = reg.COD_REG ")
			.append(" AND mrs1001.FLG_CFP = '1' ");	

			for (int i=0; i<keywords.size(); i++){
				query.append(" AND INSTR(MRS1001.CAMPO_RICERCA,'" + keywords.get(i) + "') > 0 ");	
			}

			query.append(" group by ");
			query.append(" ist.cod_cen_for_pro, ");
			query.append(" ist.cod_cen_for_pro, ");
			query.append(" ist.dat_ann_sco_rif , ");
			query.append(" ist.cod_cen_for_pro , ");
			query.append(" reg.des_reg , ");
			query.append(" prv.des_prv , ");
			query.append(" prv.cod_prv , ");
			query.append(" com.des_com , ");
			query.append(" ist.dat_ann_sco_rif, ");
			query.append(" ist.den_cfp ,  ");
			query.append(" ist.cod_cap_cfp , ");
			query.append(" ist.des_ind_cfp , ");
			query.append(" ist.des_ind_ema_cfp , ");
			query.append(" ist.cod_num_tel_cfp, ");
			query.append(" ist.cod_fax_cfp , ");
			query.append(" infoscu.num_lat_scu, ");
			query.append(" ist.des_ind_ema_cer_cfp , ");
			query.append(" ist.des_sit_web_cfp , ");
			query.append(" infoscu.num_lon_scu , ");
			query.append(" mrs1001.flg_cfp ");
						
			query.append(" ORDER BY 1 DESC ");

			log.info("QUERY getListaScuoleRicercaRapida: " + query.toString());
			
			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();
			int e = 0;

			VOScuola voScuola = null;
			while (rs.next())
			{
				voScuola = new VOScuola();
				voScuola.setDescrizione(checkNull(rs.getString("DES_NOM_SCU")));
				voScuola.setCodMecc(checkNull(rs.getString("COD_SCU_UT")));
				voScuola.setDatAnnScoRil(checkNull(rs.getString("DAT_ANN_SCO_RIL")));
				voScuola.setDesAnnScoRil(checkNull(Utility.descrizioneAnnoAccademico(rs.getString("DAT_ANN_SCO_RIL"))));
				voScuola.setCodForte(checkNull(rs.getString("COD_FOR_SCU_APP")));
				voScuola.setIndirizzo(checkNull(rs.getString("DES_IND_SCU")));
				voScuola.setCap(checkNull(rs.getString("COD_CAP_SCU")));
				voScuola.setComune(checkNull(rs.getString("DES_COM")));
				voScuola.setProvincia(checkNull(rs.getString("DES_PRV")));
				voScuola.setProvinciaBreve(checkNull(rs.getString("COD_PRV")));
				voScuola.setRegione(checkNull(rs.getString("DES_REG")));
				voScuola.setTelefono(checkNull(rs.getString("COD_TEL_SCU")));
				voScuola.setFax(checkNull(rs.getString("COD_FAX_SCU")));
				voScuola.setIndirizzoEmail(checkNull(rs.getString("DES_IND_EML")));
				voScuola.setLatitudine(checkNull(rs.getString("NUM_LAT_SCU")));
				voScuola.setLongitudine(checkNull(rs.getString("NUM_LON_SCU")));
				voScuola.setIndirizzoEmailPEC(checkNull(rs.getString("DES_IND_EMA_PCE")));
				voScuola.setSitoWeb(checkNull(rs.getString("DES_IND_WEB")));
				voScuola.setCodMeccIstRif(!checkNull(rs.getString("COD_SCU_UT_PRI")).equals("")?checkNull(rs.getString("COD_SCU_UT_PRI")):checkNull(rs.getString("COD_SCU_UT")));
				voScuola.setDenScuPri(!checkNull(rs.getString("DEN_SCU_PRI")).equals("")?checkNull(rs.getString("DEN_SCU_PRI")):checkNull(rs.getString("DES_NOM_SCU")));
				voScuola.setCodTipSit(checkNull(rs.getString("COD_TIP_SIT")));
				voScuola.setStataleNonStatale(checkNull(rs.getString("FLG_IST_STA")));
				
				if(checkNull(rs.getString("FLG_IST_STA")).equals("0")) {
					voScuola.setCheckStatale("S");
				}	
				else if (checkNull(rs.getString("FLG_IST_STA")).equals("1")) {
					voScuola.setCheckNonStatale("S");
				}	

				if(checkNull(rs.getString("FLG_SCU_PAR")).equals("1")) {
					voScuola.setCheckParitaria("S");
				}	
				else if(checkNull(rs.getString("FLG_SCU_PAR")).equals("0")) {
					voScuola.setCheckNonParitaria("S");
				}	
				else { 
					voScuola.setCheckNonParitaria("S");
				}	

				if(checkNull(rs.getString("COD_TIP_SIT")).equals("AA")) {
					voScuola.setOrdine("INFANZIA");
				}	
				else if(checkNull(rs.getString("COD_TIP_SIT")).equals("EE")) {
					voScuola.setOrdine("PRIMARIA");
				}	
				else if(checkNull(rs.getString("COD_TIP_SIT")).equals("MM")) {
					voScuola.setOrdine("SEC. I GRADO");
				}	
				else if(checkNull(rs.getString("COD_TIP_SIT")).equals("IC")) {
					voScuola.setOrdine("ISTITUTO COMPRENSIVO");
				}	
				else {
					voScuola.setOrdine("SEC. II GRADO");

					if(voScuola.getSettore() != null){
						if(voScuola.getSettore().equals("PQ")){
							voScuola.setTipologia("Professionale - Diploma quinquennale + qualifica IeFP");
						}
						else if(voScuola.getSettore().equals("Q3")){
							voScuola.setTipologia("Professionale - Percorso triennale (solo qualifica IeFP)");
						}
						else if(voScuola.getSettore().equals("Q4")){
							voScuola.setTipologia("Professionale - Percorso quadriennale (diploma IeFP)");
						}
						else{
							if(voScuola.getCodMecc().substring(2, 3).compareTo("P") == 0 ||
									voScuola.getCodMecc().substring(2, 3).compareTo("S") == 0) {												
								voScuola.setTipologia("Liceo");
							}
							else if(voScuola.getCodMecc().substring(2, 3).compareTo("T") == 0) {												
								voScuola.setTipologia("Istituto Tecnico");
							}
							else if(voScuola.getCodMecc().substring(2, 3).compareTo("R") == 0) {
								voScuola.setTipologia("Istituto Professionale");						
							}
						}
					}		
				}

			boolean cfp = checkNull(rs.getString("FLG_CFP")).equals("1");
			if (cfp){
					voScuola.setCheckStatale("");
					voScuola.setCheckNonStatale("");
					voScuola.setCheckParitaria("");
					voScuola.setCheckNonParitaria("");
					voScuola.setOrdine("CENTRO FORMAZIONE PROFESSIONALE");
					voScuola.setTipoIstituzione("5");
					voScuola.setStataleNonStatale("3");
				}
				
				

				voScuola.setIndice(""+e);
				e++;

				lista.add(voScuola);
			}
		}
		catch (Exception e)
		{
			log.error("ERRORE getListaScuoleRicercaRapida : " + e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();			
			if (con != null)
				con.close();	
		}
		return lista;
	
		
	}

	public int getListaScuoleRicercaRapidaCount(String filter) throws Exception{
		

		Connection con = getConnection();
		PreparedStatement psAnag = null;
		ResultSet rsAnag = null;
		PreparedStatement psCfp = null;
		ResultSet rsCfp = null;
		int esito = 0;

		try
		{
			log.debug("QUERY getListaScuoleRicercaRapidaCount INIZIO ");
			log.debug("PARAMETRI getListaScuoleRicercaRapidaCount INIZIO: " + filter);
			List<String> keywords = new ArrayList<String>();
			filter = filter.replaceAll("'", "''").toUpperCase();
			log.debug("PARAMETRI : " + filter);
			StringTokenizer tokenizer = new StringTokenizer(filter," ");
			while (tokenizer.hasMoreTokens()){
				keywords.add(tokenizer.nextToken());
			}
			for (int i=0; i<keywords.size(); i++){
				log.debug("keyword " + i + ": " + keywords.get(i));
			}
			

			StringBuffer queryAnag = new StringBuffer();
			
			queryAnag.append("SELECT count (DISTINCT ist.COD_SCU_UT) NUM ")			     		
			.append("FROM ")
			.append("MRS1001_RICERCARAPIDA MRS1001 ")
			.append("INNER JOIN MFG1002_ANAGISTSCOL ist ON (MRS1001.COD_SCU_UT = ist.COD_SCU_UT AND MRS1001.DAT_ANN_SCO_RIL = ist.DAT_ANN_SCO_RIL) ")
			.append("FULL OUTER JOIN TRS1005_INFGENSCUOLA infoscu ON(ist.COD_FOR_SCU_APP = infoscu.COD_SCU_FOR_APP), ")
			.append("MFG1012_REGIONE reg, ")
			.append("MFG1013_PROVINCIA prv, ")
			.append("MFG1014_COMUNE com ")
			.append(" WHERE ")				 
			.append(" ist.COD_COM = com.COD_COM ")
			.append(" AND com.COD_PRV = prv.COD_PRV ")
			.append(" AND prv.COD_REG = reg.COD_REG ")
			.append(" AND mrs1001.FLG_CFP = '0' ");			

			for (int i=0; i<keywords.size(); i++){
				queryAnag.append(" AND INSTR(MRS1001.CAMPO_RICERCA,'" + keywords.get(i) + "') > 0 ");	
			}
			queryAnag.append(" AND ( ");
			queryAnag.append(" ist.FLG_IST_STA = 1 OR ");
			queryAnag.append(" (SUBSTR (ist.cod_scu_ut, 3, 2) = 'AA' AND SUBSTR (ist.cod_scu_ut, 8, 2) > '00') OR ");
			queryAnag.append(" (SUBSTR (ist.cod_scu_ut, 3, 2) = 'EE' AND SUBSTR (ist.cod_scu_ut, 8, 2) > '00') OR ");
			queryAnag.append(" (SUBSTR (ist.cod_scu_ut, 3, 2) <> 'AA' AND SUBSTR (ist.cod_scu_ut, 3, 2) <> 'EE') ");
			queryAnag.append(" ) ");
			
			StringBuffer queryCfp = new StringBuffer();
			queryCfp.append("SELECT count(DISTINCT ist.COD_CEN_FOR_PRO) NUM ")			     		
			.append("FROM ")
			.append("MRS1001_RICERCARAPIDA MRS1001 ")
			.append("INNER JOIN MFG1047_CENTRIFORPRO ist ON (MRS1001.COD_SCU_UT = ist.COD_CEN_FOR_PRO AND MRS1001.DAT_ANN_SCO_RIL = ist.DAT_ANN_SCO_RIF) ")
			.append("FULL OUTER JOIN TRS1005_INFGENSCUOLA infoscu ON(ist.COD_CEN_FOR_PRO = infoscu.COD_SCU_FOR_APP), ")
			.append("MFG1012_REGIONE reg, ")
			.append("MFG1013_PROVINCIA prv, ")
			.append("MFG1014_COMUNE com ")
			.append(" WHERE ")				 
			.append(" ist.COD_COM_CFP = com.COD_COM ")
			.append(" AND com.COD_PRV = prv.COD_PRV ")
			.append(" AND prv.COD_REG = reg.COD_REG ")
			.append(" AND mrs1001.FLG_CFP = '1' ");	

			for (int i=0; i<keywords.size(); i++){
				queryCfp.append(" AND INSTR(MRS1001.CAMPO_RICERCA,'" + keywords.get(i) + "') > 0 ");	
			}

			log.info("queryAnag getListaScuoleRicercaRapidaCount: " + queryAnag.toString());
			
			psAnag = con.prepareStatement(queryAnag.toString());
			rsAnag = psAnag.executeQuery();

			if (rsAnag.next())
			{
				esito = rsAnag.getInt("NUM");
			}
			
			log.info("NUM queryAnag " + esito);
			log.info("queryCfp getListaScuoleRicercaRapidaCount: " + queryCfp.toString());
			
			psCfp = con.prepareStatement(queryCfp.toString());
			rsCfp = psCfp.executeQuery();

			if (rsCfp.next())
			{
				esito = esito + rsCfp.getInt("NUM");
			}
			log.info("NUM totale " + esito);

		}
		catch (Exception e)
		{
			log.error("ERRORE getListaScuoleRicercaRapidaCount : " + e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rsAnag != null)
				rsAnag.close();
			if (psAnag != null)
				psAnag.close();	
			if (rsCfp != null)
				rsCfp.close();
			if (psCfp != null)
				psCfp.close();			
			if (con != null)
				con.close();	
		}
		return esito;
	
		
	}

	
	/**
	 * Restituisce la lista delle tipologie di scuole secondarie di II grado
	 * 
	 * @return List
	 * @throws Exception
	 */
	public List<VOCommon> getTipologieParitarie() throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();
		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			String sql = "SELECT 'LI' COD_TIP_SIT, 'Licei' DES_TIP_SIT FROM DUAL " +
					" UNION " +
					" SELECT 'IP' COD_TIP_SIT, 'Professionali' DES_TIP_SIT FROM DUAL " +
					" UNION " +
					" SELECT 'IT' COD_TIP_SIT, 'Tecnici' DES_TIP_SIT FROM DUAL";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();
				voCommon.setCodice(rs.getString("COD_TIP_SIT") + "_" + rs.getString("DES_TIP_SIT"));
				voCommon.setDescrizione(rs.getString("DES_TIP_SIT"));
				lista.add(voCommon);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOCommon> getTipologie() throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();

		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];
			
			String sql = "select min(trs.num_ord_vis) as ordineVisualizzazione, trs.des_per_cod, replace(trs.des_per_cod,' ','_') as codice " +
			"from trs1051_indminmodifica trs " + 
			"where trs.cod_cla_min in " + CLASSI_SCUOLE +
			"and trs.des_per in (select distinct des_per from mfg1052_indscumin where cod_cla_min in " + CLASSI_SCUOLE +
			"and dat_ini_val <= SYSDATE and (dat_fin_val is null or dat_fin_val >= SYSDATE)) " +
			"group by trs.des_per_cod " +
			"order by ordineVisualizzazione";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();				
				voCommon.setCodice(rs.getString("codice"));
				voCommon.setDescrizione(rs.getString("des_per_cod"));
				lista.add(voCommon);
			}		
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	/**
	 * Restituisce la lista degli Indirizzi di studio per tipologia
	 * 
	 * @param codTipologia
	 * @return List
	 * @throws Exception
	 */
	public List<VOCommon> getIndirizziDiStudio(String codIstituto, String codSettore, String biennioTriennio) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();
		try
		{		
			String annoScolastico = Utility.getAnnoScolastico();
	
			Object[] campi = new Object[0];
			int[] tipi = new int[0];
			
			String codiceIstitutoDecodificato = codIstituto.replace("_"," ");
			String codiceSettoreDecodificato = null;
			if(codSettore != null && !codSettore.equals("")) 
				codiceSettoreDecodificato = codSettore.replace("_"," ");
			
			String biennioTriennioCondition = "";
			if(biennioTriennio != null && biennioTriennio.equals("Biennio")){
				biennioTriennioCondition = "and trs.per_ann_ini_val in (1, 2) and trs.num_dur_ann in (1, 2, 5, 6) ";
			}
			else if(biennioTriennio != null && biennioTriennio.equals("Triennio")){
				biennioTriennioCondition = "and ((trs.per_ann_ini_val in (3, 4, 5, 6) and trs.num_dur_ann in (1, 2, 3, 4)) or (trs.per_ann_ini_val in (1) and trs.num_dur_ann in (5, 6))) ";
			}
			else{
				biennioTriennioCondition = "";
			}
			
			StringBuffer sql = null;
			if(codiceSettoreDecodificato == null){
				campi = new Object[] {codiceIstitutoDecodificato};
				tipi = new int[] {Types.VARCHAR };
				
				sql = new StringBuffer("select trs.num_ord_vis, trs.des_ind_cod, trs.cod_ind_min, trs.cod_cla_min ")
				.append("from trs1051_indminmodifica trs ")
				.append("where trs.des_per_cod = ? ")
				.append("and trs.des_per = (select distinct des_per from mfg1052_indscumin where des_per = trs.des_per ")
				.append("and dat_ini_val <= SYSDATE and (dat_fin_val is null or dat_fin_val >= SYSDATE)) ")
				.append(biennioTriennioCondition)
				.append("order by trs.num_ord_vis");
			}
			else{	
				campi = new Object[] {codiceIstitutoDecodificato, codiceSettoreDecodificato};
				tipi = new int[] {Types.VARCHAR, Types.VARCHAR };
				
				sql = new StringBuffer("select trs.num_ord_vis, trs.des_ind_cod, trs.cod_ind_min, trs.cod_cla_min ")
		    	.append("from trs1051_indminmodifica trs ")
		    	.append("where trs.des_per_cod = ? ")
	    		.append("and trs.des_set_cod = ? ")
	    		.append("and trs.des_per = (select distinct des_per from mfg1052_indscumin where des_per = trs.des_per ")
		    	.append("and dat_ini_val <= SYSDATE and (dat_fin_val is null or dat_fin_val >= SYSDATE) and des_set is not null) ")
		    	.append("and trs.des_set = (select distinct des_set from mfg1052_indscumin where des_set = trs.des_set ")
		    	.append("and dat_ini_val <= SYSDATE and (dat_fin_val is null or dat_fin_val >= SYSDATE) and des_set is not null) ")
		    	.append("and trs.des_set is not null ")
		    	.append(biennioTriennioCondition)
		    	.append("order by trs.num_ord_vis");
				
			}

			log.debug("********** getIndirizziDiStudio DEBUG **********");
			log.debug(sql);
			log.debug("codice Istituto: " + codiceIstitutoDecodificato + ", " + "codice Settore: " + codiceSettoreDecodificato + ", " + "annoScolastico: " + annoScolastico  + ", " + "biennioTriennio: " + biennioTriennio);

			ps = con.prepareStatement(sql.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();
				voCommon.setId(rs.getString("cod_cla_min"));
				voCommon.setCodice(rs.getString("cod_ind_min"));
				voCommon.setDescrizione(rs.getString("des_ind_cod"));
				lista.add(voCommon);
			}
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}
	
	public List<VOCommon> getSettoriScuola(String codiceIstituto) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();

		try
		{
			String codiceIstitutoDecodificato = codiceIstituto.replace("_"," ");
			Object[] campi = new Object[] {codiceIstitutoDecodificato};
			int[] tipi = new int[] {Types.VARCHAR };
			
			String sql = "select min(trs.num_ord_vis) as ordineVisualizzazione, trs.des_set_cod, replace(trs.des_set_cod,' ','_') as codice " +
		    	  "from trs1051_indminmodifica trs " +
                  "where " +
		          "trs.des_per_cod = ? " + 
		          "and trs.des_per = (select distinct des_per from mfg1052_indscumin where des_per = trs.des_per " +
     			  "and dat_ini_val <= SYSDATE and (dat_fin_val is null or dat_fin_val >= SYSDATE) and des_set is not null) " +
			      "and trs.des_set is not null " + 
			      "group by trs.des_set_cod " +
			      "order by ordineVisualizzazione";

			log.debug("********** getSettoriScuola DEBUG **********");
			log.debug(sql);
			log.debug("codice Istituto: " + codiceIstitutoDecodificato);

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();				
			   	voCommon.setCodice(rs.getString("codice"));
			   	voCommon.setDescrizione(rs.getString("des_set_cod"));
		   		lista.add(voCommon);
			}
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}


	/**
	 * Restituisce la lista dei tempi scuola primaria
	 * 
	 * @param codTipologia
	 * @return List
	 * @throws Exception
	 */
	public List<VOCommon> getTempiScuolaPrimaria() throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();
		String annoAccademico = Utility.getAnnoScolastico();

		try
		{
			StringBuffer sql;

			sql = new StringBuffer("SELECT PRG_TEM_SCU, DES_TEM_SCU ")
			.append(" FROM MFG1051_TEMPOSCUOLA") 
			.append(" WHERE COD_ORD_SCU = 'EE' ") 
			.append(" AND PER_ANN_SCO_VAL = "+annoAccademico)
			.append(" ORDER BY PRG_TEM_SCU");

			log.debug("********** getTempiScuolaPrimaria DEBUG **********");
			log.debug(sql);
			log.debug("annoAccademico: " + annoAccademico);

			ps = con.prepareStatement(sql.toString());
			rs = eseguiPreparedQuery(ps, null, null,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();
				voCommon.setCodice(rs.getString("PRG_TEM_SCU").trim());
				voCommon.setDescrizione(rs.getString("DES_TEM_SCU"));
				lista.add(voCommon);
			}
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	/**
	 * Restituisce la lista dei tempi scuola secondaria di primo grado
	 * 
	 * @param codTipologia
	 * @return List
	 * @throws Exception
	 */
	public List<VOCommon> getTempiScuolaSecondariaDiPrimoGrado() throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();
		String annoAccademico = Utility.getAnnoScolastico();

		try
		{
			StringBuffer sql;

			sql = new StringBuffer("SELECT PRG_TEM_SCU, DES_TEM_SCU ")
			.append(" FROM MFG1051_TEMPOSCUOLA") 
			.append(" WHERE COD_ORD_SCU = 'MM' ") 
			.append(" AND PER_ANN_SCO_VAL = "+annoAccademico)
			.append(" ORDER BY PRG_TEM_SCU");

			log.debug("********** getTempiScuolaSecondariaDiPrimoGrado DEBUG **********");
			log.debug(sql);
			log.debug("annoAccademico: " + annoAccademico);

			ps = con.prepareStatement(sql.toString());
			rs = eseguiPreparedQuery(ps, null, null,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();
				voCommon.setCodice(rs.getString("PRG_TEM_SCU").trim());
				voCommon.setDescrizione(rs.getString("DES_TEM_SCU"));
				lista.add(voCommon);
			}
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	private String annoAccademico(String annoAccademicoParameter)
	{
		String annoRif = this.formattaDataSistema2(annoAccademicoParameter);
		StringTokenizer data = new StringTokenizer(annoRif, "/");

		String gg = data.nextToken();
		int mm = (new Integer(data.nextToken())).intValue();
		int aaaa = (new Integer(data.nextToken())).intValue();
		String annoAccademico = new Integer(aaaa).toString();
		int annoAppo = (new Integer(annoAccademico.substring(2, 4))).intValue();
		annoAppo = annoAppo + 1;// 10
		String appo = "" + annoAppo;

		if (annoAppo < 10)
			appo = "0" + annoAppo;

		annoAccademico = annoAccademico + appo;

		if (1 <= mm && 8 >= mm)
		{
			String anno = (new Integer(aaaa - 1)).toString();
			annoAppo = annoAppo - 1;

			if (annoAppo < 10)
				appo = "0" + annoAppo;
			else
				appo = "" + annoAppo;

			annoAccademico = anno + appo;
		}

		return annoAccademico;
	}

	public String formattaDataSistema2(String annoAccademicoParameter)
	{
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
		if(annoAccademicoParameter.equals("S"))
			annoCorrenteInt++;
		meseCorrenteInt = gcalendar.get(GregorianCalendar.MONTH) + 1;
		giornoCorrenteInt = gcalendar.get(GregorianCalendar.DAY_OF_MONTH);

		annoCorrente = String.valueOf(annoCorrenteInt);

		if (meseCorrenteInt < 10)
			meseCorrente = "0" + String.valueOf(meseCorrenteInt);
		else
			meseCorrente = String.valueOf(meseCorrenteInt);

		if (giornoCorrenteInt < 10)
			giornoCorrente = "0" + String.valueOf(giornoCorrenteInt);
		else
			giornoCorrente = String.valueOf(giornoCorrenteInt);

		dataSistema = giornoCorrente + "/" + meseCorrente + "/" + annoCorrente;

		return dataSistema;
	}


	public VOScuola getScuola(String codiceMeccanografico) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		VOScuola voScuola = null;

		try
		{
			StringBuffer query = new StringBuffer();
			//TODO GESTIRE ANNO ACCADEMICO DA PARAMETRO String annoAccademico = annoAccademico(voRicerca.getAnnoAccademico());
			String annoAccademico = annoAccademico("N");
			log.debug("ANNO ACCADEMICO : " + annoAccademico);

			query.append(" SELECT DISTINCT ")
			.append(" ist.COD_SCU_UT, ")
			.append(" ist.COD_SCU_UT_PRI, ")
			.append(" ist.COD_FOR_SCU_APP, ")
			.append(" reg.DES_REG, ")
			.append(" prv.DES_PRV, ")
			.append(" prv.COD_PRV, ")
			.append(" com.DES_COM, ")
			.append(" ist.FLG_IST_STA, ")
			.append(" ist.FLG_SCU_PAR, ")
			.append(" ist.COD_TIP_SIT, ")
			.append(" ist.DAT_ANN_SCO_RIL, ")
			.append(" ist.DES_NOM_SCU, ")
			.append(" ist.DES_LOC_SCU, ")
			.append(" ist.COD_CAP_SCU, ")
			.append(" ist.DES_IND_SCU, ")
			.append(" ist.COD_TEL_SCU, ")
			.append(" ist.COD_FAX_SCU, ")
			.append(" ist.DES_IND_EML, ")
			.append(" ist.DES_IND_WEB, ")
			.append(" infoscu.NUM_LAT_SCU, ")
			.append(" infoscu.NUM_LON_SCU, ")
			.append(" TO_CHAR (nvl(infoscu.dat_ora_ult_mov,to_date('01/09/2010', 'DD/MM/YYYY')), 'DD/MM/YYYY') dat_ora_ult_mov, ")
			.append(" ist.DES_IND_EMA_PCE ")
			.append(" FROM ")
			.append(" MFG1002_ANAGISTSCOL ist ")
			.append(" FULL OUTER JOIN TRS1005_INFGENSCUOLA infoscu ON(ist.COD_FOR_SCU_APP = infoscu.COD_SCU_FOR_APP), ")
			.append(" MFG1012_REGIONE reg, ")
			.append(" MFG1013_PROVINCIA prv, ")
			.append(" MFG1014_COMUNE com ")
			.append(" WHERE ")
			.append(" ist.DAT_ANN_SCO_RIL = " + annoAccademico)
			.append(" AND ist.DAT_INI_VAL <= SYSDATE ")
			.append(" AND (ist.DAT_FIN_VAL IS NULL OR ist.DAT_FIN_VAL >= SYSDATE) ")
			.append(" AND ist.COD_COM = com.COD_COM ")
			.append(" AND com.COD_PRV = prv.COD_PRV ")
			.append(" AND prv.COD_REG = reg.COD_REG ")
			.append(" AND ist.COD_SCU_UT = '" + codiceMeccanografico.trim().toUpperCase() + "' ");

			query.append(" AND (COD_CAR_SCU IS NULL OR COD_CAR_SCU <> 'O' OR COD_CAR_SCU <> 'R') ");

			log.debug("QUERY getScuola: " + query.toString());

			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();

			if (rs.next()) {

				boolean scuola2Grado = false;				
				voScuola = new VOScuola();

				voScuola.setCodMecc(checkNull(rs.getString("COD_SCU_UT")));
				voScuola.setDatAnnScoRil(checkNull(rs.getString("DAT_ANN_SCO_RIL")));
				voScuola.setDescrizione(checkNull(rs.getString("DES_NOM_SCU")));
				voScuola.setCodForte(checkNull(rs.getString("COD_FOR_SCU_APP")));
				voScuola.setIndirizzo(checkNull(rs.getString("DES_IND_SCU")));
				voScuola.setLocalita(checkNull(rs.getString("DES_LOC_SCU")));
				voScuola.setCap(checkNull(rs.getString("COD_CAP_SCU")));
				voScuola.setComune(checkNull(rs.getString("DES_COM")));
				voScuola.setProvincia(checkNull(rs.getString("DES_PRV")));
				voScuola.setRegione(checkNull(rs.getString("DES_REG")));
				voScuola.setCodMeccIstRif(checkNull(rs.getString("COD_SCU_UT_PRI")));
				voScuola.setTelefono(checkNull(rs.getString("COD_TEL_SCU")));
				voScuola.setFax(checkNull(rs.getString("COD_FAX_SCU")));
				voScuola.setIndirizzoEmail(checkNull(rs.getString("DES_IND_EML")));
				voScuola.setSitoWeb(checkNull(rs.getString("DES_IND_WEB")));
				voScuola.setLatitudine(checkNull(rs.getString("NUM_LAT_SCU")));
				voScuola.setLongitudine(checkNull(rs.getString("NUM_LON_SCU")));
				voScuola.setDataUltimoAggiornamento(checkNull(rs.getString("DAT_ORA_ULT_MOV")));
				voScuola.setStataleNonStatale(checkNull(rs.getString("FLG_IST_STA")));
				voScuola.setParitariaNonParitaria(checkNull(rs.getString("FLG_SCU_PAR")));
				voScuola.setIndirizzoEmailPEC(checkNull(rs.getString("DES_IND_EMA_PCE")));
				voScuola.setProvinciaBreve(checkNull(rs.getString("COD_PRV")));

				if(checkNull(rs.getString("COD_TIP_SIT")).equals("AA")) {
					voScuola.setOrdine("INFANZIA");
				}	
				else if(checkNull(rs.getString("COD_TIP_SIT")).equals("EE")) {
					voScuola.setOrdine("PRIMARIA");
				}	
				else if(checkNull(rs.getString("COD_TIP_SIT")).equals("MM")) {
					voScuola.setOrdine("SEC. I GRADO");
				}	
				else if(checkNull(rs.getString("COD_TIP_SIT")).equals("IC")) {
					voScuola.setOrdine("ISTITUTO COMPRENSIVO");
				}	
				else {
					voScuola.setOrdine("SEC. II GRADO");

					if(voScuola.getCodMecc().substring(2, 3).compareTo("P") == 0 ||
							voScuola.getCodMecc().substring(2, 3).compareTo("S") == 0) {												
						voScuola.setTipologia("Liceo");
					}
					else if(voScuola.getCodMecc().substring(2, 3).compareTo("T") == 0) {												
						voScuola.setTipologia("Istituto Tecnico");						
					}
					else if(voScuola.getCodMecc().substring(2, 3).compareTo("R") == 0) {
						voScuola.setTipologia("Istituto Professionale");						
					}
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return voScuola;
	}


	public VOScuola getServiziScuola(VOScuola voScuola) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			StringBuffer query = new StringBuffer();
			//String annoAccademico = annoAccademico(annoAccademicoParam);
			//TODO GESTIRE ANNO ACCADEMICO DA PARAMETRO String annoAccademico = annoAccademico(voRicerca.getAnnoAccademico());
			String annoAccademico = annoAccademico("N");


			String dataUltimoAggiornamento = "";
			if(voScuola.getDataUltimoAggiornamento() != null && !"".equals(voScuola.getDataUltimoAggiornamento()) && !"null".equals(voScuola.getDataUltimoAggiornamento()))
				dataUltimoAggiornamento = voScuola.getDataUltimoAggiornamento();
			else
				dataUltimoAggiornamento = "01/09/2010";


			query.append(" SELECT ")
			.append(" C.DES_TIP_SER, B.DES_SER, A.DES_ATT_SER, C.COD_TIP_SER, B.COD_SER, A.COD_ATT_SER, ")
			.append(" D.DES_NOT ,D.DES_VAL,")
			.append(" TO_CHAR (GREATEST(D.dat_ora_ult_mov,to_date('"+ dataUltimoAggiornamento +"', 'DD/MM/YYYY')), 'DD/MM/YYYY') dat_ora_ult_mov ")
			.append(" FROM MFG1002_ANAGISTSCOL anagistscol, ")
			.append(" TRS1003_ATTIVPERSERV A, ")
			.append(" TRS1002_SERVIZI B, ")
			.append(" TRS1001_TIPOLTIPISERV C, ")
			.append(" TRS1015_SERVIZIOSCUOLA D ")
			.append(" WHERE ")
			.append(" B.COD_TIP_SER = C.COD_TIP_SER ")
			.append(" AND A.COD_SER = B.COD_SER ")
			.append(" AND B.COD_TIP_SER = A.COD_TIP_SER ")
			.append(" AND anagistscol.DAT_ANN_SCO_RIL = " + annoAccademico + "")
			.append(" AND anagistscol.COD_FOR_SCU_APP = '" + voScuola.getCodForte().trim().toUpperCase() + "'")				 
			.append(" AND anagistscol.DAT_ANN_SCO_RIL = D.DAT_ANN_SCO_RIL ")
			.append(" AND anagistscol.COD_SCU_UT = D.COD_SCU_UT ")				 
			.append(" AND D.COD_TIP_SER = A.COD_TIP_SER ")
			.append(" AND D.COD_SER = A.COD_SER ")
			.append(" AND D.COD_ATT_SER = A.COD_ATT_SER ")				 
			.append(" AND NVL(UPPER(D.DES_VAL), 'SI') <> 'NO' ")				
			.append(" ORDER BY C.COD_TIP_SER, B.COD_SER, A.COD_ATT_SER ");

			log.debug("SERVIZI SCUOLA: " + query.toString());

			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();

			VOServizi voServizio = null;

			while (rs.next())
			{
				voServizio = new VOServizi();

				voServizio.setDescrizione1Livello(checkNull(rs.getString("DES_TIP_SER")));
				voServizio.setDescrizione2Livello(checkNull(rs.getString("DES_SER")));
				voServizio.setDescrizione3Livello(checkNull(rs.getString("DES_ATT_SER")));
				voServizio.setCodiceServizio1lv(checkNull(rs.getString("COD_TIP_SER")));
				voServizio.setCodiceServizio2lv(checkNull(rs.getString("COD_SER")));
				voServizio.setCodiceServizio3lv(checkNull(rs.getString("COD_ATT_SER")));
				voServizio.setCodice(voServizio.getCodiceServizio1lv() + "*" + voServizio.getCodiceServizio2lv() + "*" + voServizio.getCodiceServizio3lv());
				voServizio.setInformazioniAggiuntive(checkNull(rs.getString("DES_NOT")));

				voScuola.getServizi().add(voServizio);

				String dataScheda = checkNull(rs.getString("DAT_ORA_ULT_MOV"));
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				if (sdf.parse(dataScheda).after(sdf.parse(dataUltimoAggiornamento)))
					dataUltimoAggiornamento = dataScheda;
			}

			voScuola.setDataUltimoAggiornamento(dataUltimoAggiornamento);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return voScuola;
	}	

	public VOScuola getSuccursaliScuola(VOScuola voScuola, String annoAccademicoParam) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			StringBuffer query = new StringBuffer();
			String annoAccademico = annoAccademico(annoAccademicoParam);

			String dataUltimoAggiornamento = "";
			if(voScuola.getDataUltimoAggiornamento() != null && !"".equals(voScuola.getDataUltimoAggiornamento()) && !"null".equals(voScuola.getDataUltimoAggiornamento()))
				dataUltimoAggiornamento = voScuola.getDataUltimoAggiornamento();
			else
				dataUltimoAggiornamento = "01/09/2010";

			query.append(" SELECT DISTINCT ")
			.append(" succ.COD_SCU_UT, ") 
			.append(" succ.DAT_ANN_SCO_RIF, ") 
			.append(" succ.PRG_SCU_SUC, ") 
			.append(" succ.DES_IND_SUC, ") 
			.append(" succ.NUM_LAT_SUC, ") 
			.append(" succ.NUM_LON_SUC, ")
			.append(" TO_CHAR (GREATEST(succ.dat_ora_ult_mov,to_date('"+ dataUltimoAggiornamento +"', 'DD/MM/YYYY')), 'DD/MM/YYYY') dat_ora_ult_mov ")
			.append(" FROM ")
			.append(" TRS1009_INFSCUOLESUCC succ ")
			.append(" WHERE ")
			.append(" succ.COD_SCU_UT = '" + voScuola.getCodMecc().trim().toUpperCase() + "'")
			.append(" AND succ.DAT_ANN_SCO_RIF = '" + annoAccademico + "'")
			.append(" ORDER BY 7 ASC ");

			log.debug("QUERY SUCCURSALI:" + query.toString());

			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();

			VOSuccursale voSuccursale = null;
			while (rs.next())
			{
				voSuccursale = new VOSuccursale();

				voSuccursale.setCodiceMeccanografico(checkNull(rs.getString("COD_SCU_UT")));
				voSuccursale.setAnnoRiferimento(checkNull(rs.getString("DAT_ANN_SCO_RIF")));
				voSuccursale.setDesAnnScoRil(checkNull(Utility.descrizioneAnnoAccademico(rs.getString("DAT_ANN_SCO_RIF"))));
				voSuccursale.setProgressivo(checkNull(rs.getString("PRG_SCU_SUC")));
				voSuccursale.setDescrizione(checkNull(rs.getString("DES_IND_SUC")));
				voSuccursale.setLatitudine(checkNull(rs.getString("NUM_LAT_SUC")));
				voSuccursale.setLongitudine(checkNull(rs.getString("NUM_LON_SUC")));
				voSuccursale.setCodice(voSuccursale.getCodiceMeccanografico() + "*" + voSuccursale.getAnnoRiferimento() + "*" + voSuccursale.getProgressivo());
				voScuola.setDataUltimoAggiornamento(checkNull(rs.getString("DAT_ORA_ULT_MOV")));

				voScuola.getSuccursali().add(voSuccursale);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return voScuola;
	}		

	public List<VOServizi> getServizi() throws Exception
	{
		List<VOServizi> servizi = new ArrayList<VOServizi>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			StringBuffer query = new StringBuffer();

			query.append(" SELECT DES_ATT_SER, ")
			.append(" COD_TIP_SER, COD_SER, COD_ATT_SER")
			.append(" FROM ")
			.append(" TRS1003_ATTIVPERSERV ")
			.append(" ORDER BY COD_TIP_SER, COD_SER, COD_ATT_SER ");

			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			ps = con.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOServizi voServizi = null;
			String descrizioneServizio;
			while (rs.next())
			{
				voServizi = new VOServizi();
				descrizioneServizio = checkNull(rs.getString("DES_ATT_SER"));

				voServizi.setCodice(descrizioneServizio);
				voServizi.setDescrizione(descrizioneServizio);
				voServizi.setCodiceServizio1lv(checkNull(rs.getString("COD_TIP_SER")));
				voServizi.setCodiceServizio2lv(checkNull(rs.getString("COD_SER")));
				voServizi.setCodiceServizio3lv(checkNull(rs.getString("COD_ATT_SER")));

				servizi.add(voServizi);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return servizi;
	}	

	public List<VOServizi> getServiziRicercaAvanzataEPopupConfronta() throws Exception
	{
		List<VOServizi> servizi = new ArrayList<VOServizi>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			StringBuffer query = new StringBuffer();

			query.append(" SELECT ")
			.append(" C.DES_TIP_SER, B.DES_SER, A.DES_ATT_SER, C.COD_TIP_SER, B.COD_SER, A.COD_ATT_SER ")
			.append(" FROM TRS1003_ATTIVPERSERV A, ")
			.append(" TRS1002_SERVIZI B, ")
			.append(" TRS1001_TIPOLTIPISERV C ")
			.append(" WHERE ")
			.append(" B.COD_TIP_SER = C.COD_TIP_SER ")
			.append(" AND A.COD_SER = B.COD_SER ")
			.append(" AND B.COD_TIP_SER = A.COD_TIP_SER ")
			.append(" AND A.FLG_VIS = 'S' ")
			.append(" AND TRIM(DES_ATT_SER) IS NOT NULL ")
			.append(" ORDER BY 4, 5, 6 ");

			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			ps = con.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOServizi voServizio = null;
			while (rs.next())
			{
				voServizio = new VOServizi();

				voServizio.setDescrizione1Livello(checkNull(rs.getString("DES_TIP_SER")));
				voServizio.setDescrizione2Livello(checkNull(rs.getString("DES_SER")));
				voServizio.setDescrizione3Livello(checkNull(rs.getString("DES_ATT_SER")));
				voServizio.setCodiceServizio1lv(checkNull(rs.getString("COD_TIP_SER")));
				voServizio.setCodiceServizio2lv(checkNull(rs.getString("COD_SER")));
				voServizio.setCodiceServizio3lv(checkNull(rs.getString("COD_ATT_SER")));
				voServizio.setCodice(voServizio.getCodiceServizio1lv() + "*" + voServizio.getCodiceServizio2lv() + "*" + voServizio.getCodiceServizio3lv());


				servizi.add(voServizio);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return servizi;
	}		

	public byte[] getAllegatoIndirizzoStudio(String codiceIndirizzoStudio) throws Exception
	{
		Connection con = getConnection();		
		PreparedStatement ps = null;
		ResultSet rs = null;
		byte[] datiAllegato = null;

		try
		{
			StringBuffer query = new StringBuffer(" SELECT A.OGG_ALL_IND ")
			.append(" FROM   TWB1002_INDIRSTUDIO A ")
			.append(" WHERE  A.COD_IND_STU = ? ");

			Object[] campi = new Object[]
					{ codiceIndirizzoStudio.trim() };
			int[] tipi = new int[]
					{ Types.VARCHAR };

			ps = con.prepareStatement(query.toString());

			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				Blob blob = rs.getBlob("OGG_ALL_IND");
				InputStream in = blob.getBinaryStream();
				int len = (int) blob.length();
				datiAllegato = blob.getBytes(1, len);
				in.close();
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return datiAllegato;
	}

	public List<VOCommon> getListaRegioni() throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();
		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];
			String sql = " SELECT COD_REG CODICE, DES_REG DESCR " + 
					" FROM MFG1012_REGIONE " +
					" WHERE COD_CIT = 200 " +
					" ORDER BY 2 "; 

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();
				voCommon.setCodice(rs.getString("CODICE").trim() + "_" + rs.getString("DESCR").trim());
				voCommon.setDescrizione(rs.getString("DESCR").trim());
				lista.add(voCommon);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOCommon> getListaProvincePerRegione(String codiceRegione) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();
		try
		{
			Object[] campi = new Object[]{ codiceRegione.trim() };
			int[] tipi = new int[]{ Types.VARCHAR };

			String sql = " SELECT COD_PRV CODICE, DES_PRV DESCR " + 
					" FROM MFG1013_PROVINCIA " +
					" WHERE TRIM(COD_REG) = ? " +
					" ORDER BY 2 "; 

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();
				voCommon.setCodice(rs.getString("CODICE").trim() + "_" + rs.getString("DESCR").trim());
				voCommon.setDescrizione(rs.getString("DESCR").trim());
				lista.add(voCommon);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOCommon> getListaComuniPerProvincia(String codiceProvincia) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();
		try
		{
			Object[] campi = new Object[]{ codiceProvincia.trim() };
			int[] tipi = new int[]{ Types.VARCHAR };

			String sql = " SELECT COD_COM CODICE, DES_COM DESCR " + 
					" FROM MFG1014_COMUNE " +
					" WHERE COD_PRV = ? " +
					" ORDER BY 2 "; 

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();
				voCommon.setCodice(rs.getString("CODICE").trim() + "_" + rs.getString("DESCR").trim());
				voCommon.setDescrizione(rs.getString("DESCR").trim());
				lista.add(voCommon);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOClasse> getDistribuzioneClassiIndirizzi(String codForScuApp) throws Exception{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOClasse> result = new ArrayList<VOClasse>();
		try
		{
			Object[] campi = new Object[]{ codForScuApp,  codForScuApp };
			int[] tipi = new int[]{ Types.VARCHAR, Types.VARCHAR };

			StringBuffer query = new StringBuffer("SELECT COD_IND, SUM(NVL(NUM_CLA_IND, 0)) as NUM_CLASSI, DES_IND ")
			.append("FROM TRS1017_ANANAZPRIANN ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?) ")
			.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
			.append("FROM TRS1017_ANANAZPRIANN ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?)) ")
			.append("GROUP BY COD_IND, DES_IND ")
			.append("ORDER BY 1");

			ps = con.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			while(rs.next()) {
				VOClasse voClasse = new VOClasse();
				voClasse.setDescrizione(rs.getString("DES_IND"));
				voClasse.setNumeroClassi(rs.getString("NUM_CLASSI"));

				result.add(voClasse);
			}		
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return result;
	}

	public VOAttrezzatureMultimediali getAttrezzatureMultimediali(String codForScuApp) throws Exception {

		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBuffer query = new StringBuffer("SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, COD_SCU_UT_PRI,")
			.append("NVL(NUM_TOT_PC_DES, 0) NUM_TOT_PC_DES, NVL(NUM_TOT_PC_LAP, 0) NUM_TOT_PC_LAP,") 
			.append("NVL(NUM_TOT_LIM_AU, 0) NUM_TOT_LIM_AU, NVL(NUM_TOT_LIM_MO, 0) NUM_TOT_LIM_MO,")
			.append("NVL(NUM_TOT_LIM_LB, 0) NUM_TOT_LIM_LB, NVL(NUM_TOT_AUL, 0) NUM_TOT_AUL,") 
			.append("NVL(NUM_TOT_AUL_LAN, 0) NUM_TOT_AUL_LAN, NVL(NUM_TOT_AUL_WI, 0) NUM_TOT_AUL_WI ")
			.append("FROM TRS1013_INDOSSTECN ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?) ")
			.append("ORDER BY DAT_ANN_SCO_RIL DESC");

			Object[] campi = new Object[]{codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR};

			log.debug("getAttrezzatureMultimediali query: " + query.toString());

			ps = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOAttrezzatureMultimediali voAttr = null;
			if(rs.next()) {
				voAttr = new VOAttrezzatureMultimediali();
				voAttr.setCodScuUt(rs.getString("COD_SCU_UT"));
				voAttr.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				voAttr.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voAttr.setNumTotAul(rs.getInt("NUM_TOT_AUL"));
				voAttr.setNumTotAulLan(rs.getInt("NUM_TOT_AUL_LAN"));
				voAttr.setNumTotAulWi(rs.getInt("NUM_TOT_AUL_WI"));
				voAttr.setNumTotLimAu(rs.getInt("NUM_TOT_LIM_AU"));
				voAttr.setNumTotLimLb(rs.getInt("NUM_TOT_LIM_LB"));
				voAttr.setNumTotLimMo(rs.getInt("NUM_TOT_LIM_MO"));
				voAttr.setNumTotPcDes(rs.getInt("NUM_TOT_PC_DES"));
				voAttr.setNumTotPcLap(rs.getInt("NUM_TOT_PC_LAP"));				
			}

			return voAttr;
		}
		finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}

			if(ps != null) {
				ps.close();
				ps = null;
			}

			if(con != null) {
				con.close();
				con = null;
			}
		}
	}

	public Integer getNumeroAlunni(String codForScuApp) throws Exception {

		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		Integer numAlu = null;
		try {
			StringBuffer query = new StringBuffer("SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, SUM(NVL(NUM_ALU, 0)) TOT_ALU ")
			.append("FROM TRS1027_RILEINTE RI ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?) ")
			.append("GROUP BY COD_SCU_UT, DAT_ANN_SCO_RIL ")
			.append("ORDER BY DAT_ANN_SCO_RIL DESC");

			Object[] campi = new Object[]{codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR};

			log.debug("getNumeroAlunni query: " + query.toString());

			ps = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if(rs.next()) {
				numAlu = rs.getInt("TOT_ALU");
			}

			return numAlu;
		}
		finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}

			if(ps != null) {
				ps.close();
				ps = null;
			}

			if(con != null) {
				con.close();
				con = null;
			}
		}
	}

	public List<VOTipologiaServizio> getServiziAttiviPerTipologia(String codTipSer, String codScuUt, String datAnnScoRil) throws Exception {
		Connection con = getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOTipologiaServizio> servizi = new ArrayList<VOTipologiaServizio>();
		try {
			StringBuffer query = new StringBuffer("SELECT TS.COD_TIP_SER, TS.DES_TIP_SER, S.COD_SER, S.DES_SER,")
			.append("APS.COD_ATT_SER, APS.DES_ATT_SER, APS.DES_FON_PRO, APS.COD_TIP_CAM,")				
			.append("SS.DES_NOT, SS.DES_VAL ")
			.append("FROM TRS1001_TIPOLTIPISERV TS, TRS1002_SERVIZI S,")
			.append("TRS1003_ATTIVPERSERV APS, TRS1015_SERVIZIOSCUOLA SS ")
			.append("WHERE TS.COD_TIP_SER = S.COD_TIP_SER ")
			.append("AND S.COD_SER = APS.COD_SER ")
			.append("AND TS.COD_TIP_SER = APS.COD_TIP_SER ")
			.append("AND APS.COD_TIP_SER = SS.COD_TIP_SER(+) ")
			.append("AND APS.COD_SER = SS.COD_SER(+) ")
			.append("AND APS.COD_ATT_SER = SS.COD_ATT_SER(+) ")
			.append("AND TS.DAT_INI_VAL <= SYSDATE ")
			.append("AND (TS.DAT_FIN_VAL IS NULL OR TS.DAT_FIN_VAL >= SYSDATE) ")
			.append("AND TS.FLG_VIS = 'S' ")
			.append("AND S.DAT_INI_VAL <= SYSDATE ")
			.append("AND (S.DAT_FIN_VAL IS NULL OR S.DAT_FIN_VAL >= SYSDATE) ")
			.append("AND S.FLG_VIS = 'S' ")
			.append("AND APS.DAT_INI_VAL <= SYSDATE ")
			.append("AND (APS.DAT_FIN_VAL IS NULL OR APS.DAT_FIN_VAL >= SYSDATE) ")
			.append("AND APS.FLG_VIS = 'S' ")
			.append("AND SS.COD_SCU_UT = ? ")
			.append("AND SS.DAT_ANN_SCO_RIL = ? ")
			.append("AND TS.COD_TIP_SER = ? ")
			.append("ORDER BY TS.NUM_ORD_VIS, S.NUM_ORD_VIS, APS.NUM_ORD_VIS ");

			Object[] campi = new Object[]{codScuUt, datAnnScoRil, codTipSer};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.INTEGER};

			log.debug("getServiziAttiviPerTipologia query: " + query.toString());

			ps = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOTipologiaServizio voTipSer = null;
			VOServizio voSer = null;
			VOAttivitaServizio voAttSer = null;
			while (rs.next()) {

				if(!HtmlInput.INPUT_RADIO.toString().equals(rs.getString("COD_TIP_CAM")) ||
						!"NO".equalsIgnoreCase(rs.getString("DES_VAL"))) {

					if(voTipSer == null || !voTipSer.getCodTipSer().equals(rs.getString("COD_TIP_SER"))) {				
						voTipSer = new VOTipologiaServizio();
						voTipSer.setCodTipSer(rs.getString("COD_TIP_SER"));
						voTipSer.setDesTipSer(rs.getString("DES_TIP_SER"));

						voSer = new VOServizio();
						voSer.setCodSer(rs.getString("COD_SER"));
						voSer.setDesSer(rs.getString("DES_SER"));					

						voAttSer = new VOAttivitaServizio();
						voAttSer.setCodAttSer(rs.getString("COD_ATT_SER"));						
						voAttSer.setCodTipCam(rs.getString("COD_TIP_CAM"));
						voAttSer.setDesAttSer(rs.getString("DES_ATT_SER"));
						voAttSer.setDesFonPro(rs.getString("DES_FON_PRO"));					
						voAttSer.setDesNot(rs.getString("DES_NOT"));			
						voAttSer.setDesVal(rs.getString("DES_VAL"));

						voSer.getAttivitaServizio().add(voAttSer);
						voTipSer.getServizi().add(voSer);
						servizi.add(voTipSer);
					}

					if(voSer == null || !voSer.getCodSer().equals(rs.getString("COD_SER"))) {

						voSer = new VOServizio();
						voSer.setCodSer(rs.getString("COD_SER"));
						voSer.setDesSer(rs.getString("DES_SER"));

						voTipSer.getServizi().add(voSer);	
					}	

					if(voAttSer == null || !voAttSer.getCodAttSer().equals(rs.getString("COD_ATT_SER"))) {

						voAttSer = new VOAttivitaServizio();
						voAttSer.setCodAttSer(rs.getString("COD_ATT_SER"));						
						voAttSer.setCodTipCam(rs.getString("COD_TIP_CAM"));
						voAttSer.setDesAttSer(rs.getString("DES_ATT_SER"));
						voAttSer.setDesFonPro(rs.getString("DES_FON_PRO"));
						voAttSer.setDesNot(rs.getString("DES_NOT"));			
						voAttSer.setDesVal(rs.getString("DES_VAL"));

						voSer.getAttivitaServizio().add(voAttSer);
					}
				}	
			}

			return servizi;
		}
		finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}

			if(ps != null) {
				ps.close();
				ps = null;
			}

			if(con != null) {
				con.close();
				con = null;
			}
		}
	}

	public List<VOAndamento> getAlunniIscrittiUltimiDueAnni(String codForScuApp) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOAndamento> result = new ArrayList<VOAndamento>();

		try {
			StringBuffer query = new StringBuffer("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL, NUM_ALU FROM( ")
			.append("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL, NVL(NUM_ALU, 0) NUM_ALU ")
			.append("FROM TRS1027_RILEINTE ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?) ")
			.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
			.append("FROM TRS1027_RILEINTE ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?)) ")         
			.append("UNION ")
			.append("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL, NVL(NUM_ALU, 0) NUM_ALU ") 
			.append("FROM TRS1027_RILEINTE ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?) ")
			.append("AND DAT_ANN_SCO_RIL = (SELECT (SUBSTR(MAX(DAT_ANN_SCO_RIL), 0, 4) - 1 || SUBSTR(MAX(DAT_ANN_SCO_RIL), 5, 2) - 1) DAT_ANN_SCO_RIL ")
			.append("FROM TRS1027_RILEINTE ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?))) ")
			.append("ORDER BY 1,2");  

			Object[] campi = new Object[]{codForScuApp, codForScuApp, codForScuApp, codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

			ps = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			while(rs.next()) {
				VOAndamento voAndamento = new VOAndamento();
				voAndamento.setAnnoCorso(rs.getString("PER_ANN_COR"));
				voAndamento.setAnnoScolastico(dat_ann_sco_ril_format(rs.getString("DAT_ANN_SCO_RIL")));
				voAndamento.setNumeroAlunni(rs.getString("NUM_ALU"));

				result.add(voAndamento);
			}			
		}		
		finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(ps != null) {
				ps.close();
				ps = null;
			}						
			if(con != null) {
				con.close();
				con = null;
			}
		}
		return result;
	}

	public VOAndamento getEsitiScrutini(String codForScuApp) throws Exception {

		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VOAndamento voAndamento = null;
		try {
			StringBuffer query = new StringBuffer("SELECT DECODE(SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)), 0, 0,")  
			.append("(SUM(NVL(NUM_ALU_AMM, 0))/SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)))*100) ALU_AMM,")
			.append("DECODE(SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)), 0, 0,")
			.append("(SUM(NVL(NUM_ALU_NON_AMM, 0))/SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)))*100) ALU_NON_AMM,")
			.append("DECODE(SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)), 0, 0,")
			.append("(SUM(NVL(NUM_ALU_SOS, 0))/SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)))*100) ALU_SOS,")
			.append("DECODE(SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_NON_AMM_SET, 0)), 0, 0,")
			.append("(SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0))/SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_NON_AMM_SET, 0)))*100) ALU_AMM_SET,") 
			.append("DECODE(SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_NON_AMM_SET, 0)), 0, 0,")
			.append("(SUM(NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_NON_AMM_SET, 0))/SUM(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_NON_AMM_SET, 0)))*100) ALU_NON_AMM_SET ")
			.append("FROM TRS1018_RILEVESITI ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?) ")
			.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
			.append("FROM TRS1018_RILEVESITI ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?)) ")
			.append("ORDER BY 1");

			Object[] campi = new Object[]{codForScuApp, codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR, Types.VARCHAR};

			pstmt = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(pstmt, campi, tipi,false);

			log.debug("getEsitiScrutini query: " + query);

			if(rs.next()) {
				voAndamento = new VOAndamento();

				voAndamento.setAlunniAmmessi(rs.getFloat("ALU_AMM"));
				voAndamento.setAlunniNonAmmessi(rs.getFloat("ALU_NON_AMM"));
				voAndamento.setAlunniSospesi(rs.getFloat("ALU_SOS"));
				voAndamento.setAlunniAmmessiSett(rs.getFloat("ALU_AMM_SET"));
				voAndamento.setAlunniNonAmmessiSett(rs.getFloat("ALU_NON_AMM_SET"));				
			}			
		}		
		finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}						
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
		return voAndamento;
	}

	public String dat_ann_sco_ril_format(String dataIn){
		return dataIn.substring(0, 4)+"/"+dataIn.substring(4);
	}


	public List<VOScuola> getListaCentriFormazioneProfessionale(VORicerca voRicerca) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOScuola> lista = new ArrayList<VOScuola>();
		String coordinate = "";
		String latitudinePartenza = "";
		String longitudinePartenza = "";
		
		log.debug("in  getListaCentriFormazioneProfessionale : parametri di ricerca");
		log.debug(ReflectionToStringBuilder.toString(voRicerca,ToStringStyle.MULTI_LINE_STYLE));

		try
		{
			/** adeguamento parametri del form - inizio **/
			if(voRicerca.getCodiceRegione().indexOf("_") != -1){
				voRicerca.setCodiceRegione(voRicerca.getCodiceRegione().substring(0,voRicerca.getCodiceRegione().indexOf("_")));
			}
			
			if(voRicerca.getCodiceProvincia().indexOf("_") != -1){
				voRicerca.setCodiceProvincia(voRicerca.getCodiceProvincia().substring(0,voRicerca.getCodiceProvincia().indexOf("_")));
			}
			
			if(voRicerca.getCodiceComune().indexOf("_") != -1){
				voRicerca.setCodiceComune(voRicerca.getCodiceComune().substring(0,voRicerca.getCodiceComune().indexOf("_")));
			}
			/** adeguamento parametri del form - fine **/
			
			StringBuffer query = new StringBuffer();
			//TODO GESTIRE ANNO ACCADEMICO DA PARAMETRO String annoAccademico = annoAccademico(voRicerca.getAnnoAccademico());
			String annoAccademico = annoAccademico("N");
			
			String cod_cla_min = voRicerca.getCodiceCFPPercorso();
			String cod_cla_min_des_set = voRicerca.getCodiceCFPSettore();
			//String cod_cla_min = cod_cla_min_des_set.substring(0,cod_cla_min_des_set.indexOf("_"));
			String des_set = cod_cla_min_des_set.substring(cod_cla_min_des_set.indexOf("_")+1).replaceAll("blank", " ");
			String cod_ind_min = voRicerca.getCodiceCFPIndirizzo();
			log.debug("CFPPercorso - cod_cla_min : " + cod_cla_min);
			log.debug("CFPSettore - des_set : " + des_set);
			log.debug("CFPIndirizzo - cod_ind_min : " + cod_ind_min);
			
			if (voRicerca.getLatIndirizzoRiferimento() != null && voRicerca.getLatIndirizzoRiferimento().trim().length() > 0
					&& voRicerca.getLngIndirizzoRiferimento() != null && voRicerca.getLngIndirizzoRiferimento().trim().length() > 0)
			{
				latitudinePartenza = voRicerca.getLatIndirizzoRiferimento();
				longitudinePartenza = voRicerca.getLngIndirizzoRiferimento();				
			}

			query.append("SELECT DISTINCT ist.COD_CEN_FOR_PRO COD_SCU_UT, ist.COD_CEN_FOR_PRO COD_SCU_UT_PRI, ist.DAT_ANN_SCO_RIF DAT_ANN_SCO_RIL,")			     		
			.append("ist.COD_CEN_FOR_PRO COD_FOR_SCU_APP, reg.DES_REG, prv.DES_PRV, prv.COD_PRV, com.DES_COM,")
			.append("'0' FLG_IST_STA, '0' FLG_SCU_PAR, 'CP' COD_TIP_SIT, ist.DAT_ANN_SCO_RIF DAT_ANN_SCO_RIL,")
			.append("ist.DEN_CFP DES_NOM_SCU, ist.COD_CAP_CFP COD_CAP_SCU, ist.DES_IND_CFP DES_IND_SCU, ist.DES_IND_EMA_CFP DES_IND_EML,")			     
			.append("ist.COD_NUM_TEL_CFP COD_TEL_SCU, ist.COD_FAX_CFP COD_FAX_SCU, '0' FLG_SED_DIR, infoscu.NUM_LAT_SCU, infoscu.NUM_LON_SCU, ist.DES_IND_EMA_CER_CFP DES_IND_EMA_PCE, ist.DES_SIT_WEB_CFP DES_IND_WEB,");

			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_VICINO_A_TE)){
				if (!latitudinePartenza.equals("") && !longitudinePartenza.equals("")) {

					query.append("(acos(cos((6.28*").append(longitudinePartenza).append("/360)-(6.28*infoscuola.num_lon_scu/360))*cos(6.28*") 
					.append(latitudinePartenza).append("/360)*cos(6.28*infoscuola.num_lat_scu/360)+sin(6.28*") 
					.append(latitudinePartenza).append("/360)*sin(6.28*infoscuola.num_lat_scu/360)))*6360 AS RAGGIO,");								
				}
			}

			query.append("infoscu.NUM_LON_SCU ")
			.append("FROM ")
			.append("MFG1047_CENTRIFORPRO ist ")
			.append("FULL OUTER JOIN TRS1005_INFGENSCUOLA infoscu ON(ist.COD_CEN_FOR_PRO = infoscu.COD_SCU_FOR_APP),")
			.append("MFG1012_REGIONE reg,")
			.append("MFG1013_PROVINCIA prv,");


			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_VICINO_A_TE)){
				if (!latitudinePartenza.equals("") && !longitudinePartenza.equals("")) {


					query.append(" TRS1005_INFGENSCUOLA infoscuola,");
				}
			}
			
			if (cod_cla_min != null && !cod_cla_min.equals("")){
				
				query.append(" MFG1048_ASSCFPCORSO ASSCFPCORSO, ");
				query.append(" MFG1055_INDSCUISCR INDSCUISCR, ");
			}

			query.append("MFG1014_COMUNE com ")
			.append(" WHERE ist.DAT_ANN_SCO_RIF = ").append(annoAccademico)				 
			.append(" AND ist.COD_COM_CFP = com.COD_COM ")
			.append(" AND com.COD_PRV = prv.COD_PRV ")
			.append(" AND prv.COD_REG = reg.COD_REG ");

			if (voRicerca.getCodiceRegione() != null && voRicerca.getCodiceRegione().trim().length() > 0)
				query.append("AND reg.COD_REG = '" + voRicerca.getCodiceRegione().trim() + "' ");

			if (voRicerca.getCodiceProvincia() != null && voRicerca.getCodiceProvincia().trim().length() > 0)
				query.append("AND prv.COD_PRV = '" + voRicerca.getCodiceProvincia().trim() + "' ");

			if (voRicerca.getCodiceComune() != null && voRicerca.getCodiceComune().trim().length() > 0)
				query.append("AND com.COD_COM = '" + voRicerca.getCodiceComune().trim() + "' ");			



			if (voRicerca.getDenominazione() != null && voRicerca.getDenominazione().trim().length() > 0)
				query.append(" AND ist.DEN_CFP LIKE '%" + voRicerca.getDenominazione().trim().toUpperCase().replace("'", "''") + "%' ");	

			if (voRicerca.getCodMecc() != null && voRicerca.getCodMecc().trim().length() > 0) {
				query.append(" AND ist.COD_CEN_FOR_PRO = '" + voRicerca.getCodMecc().trim().toUpperCase() + "' ");

			}	

			if (voRicerca.getTipoRicerca().equals(TIPO_RICERCA_VICINO_A_TE)){
				if (!latitudinePartenza.equals("") && !longitudinePartenza.equals("")) {


					query.append(" AND infoscuola.COD_SCU_FOR_APP = ist.COD_CEN_FOR_PRO ")
					.append(" AND infoscuola.num_lon_scu is not null ")
					.append(" AND infoscuola.num_lat_scu is not null ")
					.append(" AND (acos(cos((6.28*" + longitudinePartenza + "/360)-(6.28*infoscuola.num_lon_scu/360))*cos(6.28*" + latitudinePartenza + "/360)*cos(6.28*infoscuola.num_lat_scu/360)+sin(6.28*" + latitudinePartenza + "/360)*sin(6.28*infoscuola.num_lat_scu/360)))*6360 <= " + voRicerca.getRaggio());			
				}
			}

//			if (voRicerca.getCoordinateIndirizzoDiretto() != null && 
//					voRicerca.getCoordinateIndirizzoDiretto().trim().length() > 0) {
//
//				coordinate = voRicerca.getCoordinateIndirizzoDiretto();
//
//				String parteAlta = coordinate.substring(2,coordinate.indexOf(')'));
//				String parteBassa = coordinate.substring(coordinate.indexOf(')') + 4, coordinate.length()-2);
//
//				String latitudineAlta = parteAlta.substring(0,parteAlta.indexOf(','));
//				String longitudineAlta = parteAlta.substring(parteAlta.indexOf(',') + 2 , parteAlta.length());
//				String latitudineBassa = parteBassa.substring(0,parteBassa.indexOf(','));
//				String longitudineBassa = parteBassa.substring(parteBassa.indexOf(',') + 2 , parteBassa.length());
//
//				query.append(" AND infoscuola.COD_SCU_FOR_APP = ist.COD_CEN_FOR_PRO ")
//				.append(" AND infoscuola.num_lon_scu is not null ")
//				.append(" AND infoscuola.num_lat_scu is not null ")
//				.append(" AND to_number(infoscuola.num_lat_scu) >= to_number(" + latitudineAlta + ")" )
//				.append(" AND to_number(infoscuola.num_lat_scu) <= to_number(" + latitudineBassa + ")" )
//				.append(" AND to_number(infoscuola.num_lon_scu) >= to_number(" + longitudineAlta + ")" )
//				.append(" AND to_number(infoscuola.num_lon_scu) <= to_number(" + longitudineBassa + ")" );	            			
//			}
			
			if (cod_cla_min != null && !cod_cla_min.equals("") && !cod_cla_min.equals("undefined")){
				
				query.append(" AND UPPER(ASSCFPCORSO.COD_CEN_FOR_PRO) = ist.COD_CEN_FOR_PRO ");
				query.append(" AND ASSCFPCORSO.DAT_ANN_SCO_RIF = ist.DAT_ANN_SCO_RIF ");
				query.append(" AND ASSCFPCORSO.COD_IND_MIN = INDSCUISCR.COD_IND_MIN ");
				query.append(" AND ASSCFPCORSO.FLG_CLA_MIN = INDSCUISCR.COD_CLA_MIN ");
				
				query.append(" AND INDSCUISCR.COD_CLA_MIN = '" + cod_cla_min + "' ");
				
				if (cod_ind_min != null && !cod_ind_min.equals("") && !cod_ind_min.equals("undefined")){
					query.append(" AND INDSCUISCR.COD_IND_MIN = '" + cod_ind_min + "' ");	
				}else if (des_set != null && !des_set.equals("") && !des_set.equals("undefined")){
					query.append(" AND INDSCUISCR.DES_SET = '" + des_set + "' ");	
				}
			}
			


			query.append(" ORDER BY ist.DEN_CFP ");

			log.debug("QUERY getListaCFP: " + query.toString());

			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();
			int e = 0;

			VOScuola voScuola = null;
			while (rs.next())
			{
				voScuola = new VOScuola();
				voScuola.setDescrizione(checkNull(rs.getString("DES_NOM_SCU")));
				voScuola.setCodMecc(checkNull(rs.getString("COD_SCU_UT")));
				voScuola.setDatAnnScoRil(checkNull(rs.getString("DAT_ANN_SCO_RIL")));
				voScuola.setDesAnnScoRil(checkNull(Utility.descrizioneAnnoAccademico(rs.getString("DAT_ANN_SCO_RIL"))));
				voScuola.setCodMeccIstRif(checkNull(rs.getString("COD_SCU_UT_PRI")));
				voScuola.setCodForte(checkNull(rs.getString("COD_FOR_SCU_APP")));
				voScuola.setIndirizzo(checkNull(rs.getString("DES_IND_SCU")));
				voScuola.setCap(checkNull(rs.getString("COD_CAP_SCU")));
				voScuola.setComune(checkNull(rs.getString("DES_COM")));
				voScuola.setProvincia(checkNull(rs.getString("DES_PRV")));
				voScuola.setProvinciaBreve(checkNull(rs.getString("COD_PRV")));
				voScuola.setRegione(checkNull(rs.getString("DES_REG")));
				voScuola.setTelefono(checkNull(rs.getString("COD_TEL_SCU")));
				voScuola.setFax(checkNull(rs.getString("COD_FAX_SCU")));
				voScuola.setIndirizzoEmail(checkNull(rs.getString("DES_IND_EML")));
				voScuola.setLatitudine(checkNull(rs.getString("NUM_LAT_SCU")));
				voScuola.setLongitudine(checkNull(rs.getString("NUM_LON_SCU")));
				voScuola.setIndirizzoEmailPEC(checkNull(rs.getString("DES_IND_EMA_PCE")));
				voScuola.setSitoWeb(checkNull(rs.getString("DES_IND_WEB")));
				voScuola.setDenScuPri(checkNull(rs.getString("DES_NOM_SCU")));
				voScuola.setCodTipSit(checkNull(rs.getString("COD_TIP_SIT")));
				voScuola.setStataleNonStatale("3");


				if (voRicerca.getCoordinateIndirizzoRiferimento() != null && voRicerca.getCoordinateIndirizzoRiferimento().trim().length() > 0)
					voScuola.setRaggio(checkNull(rs.getString("RAGGIO")));


				voScuola.setCheckStatale("");
				voScuola.setCheckNonStatale("");
				voScuola.setCheckParitaria("");
				voScuola.setCheckNonParitaria("");


				voScuola.setOrdine("CENTRO FORMAZIONE PROFESSIONALE");

				voScuola.setTipoIstituzione(voRicerca.getCodiceOrdine());


				voScuola.setIndice(""+e);
				e++;

				lista.add(voScuola);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();			
			if (con != null)
				con.close();	
		}
		return lista;
	}	

	
	public VOScuola getCentroFormazioneProfessionale(String codiceMeccanografico, VORicerca voRicerca,String parametroRicercaServizi) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		VOScuola voScuola = null;

		try
		{
			StringBuffer query = new StringBuffer();
			//TODO GESTIRE ANNO ACCADEMICO DA PARAMETRO String annoAccademico = annoAccademico(voRicerca.getAnnoAccademico());
			String annoAccademico = annoAccademico("N");

			query.append(" SELECT DISTINCT ")
			.append(" ist.COD_CEN_FOR_PRO COD_SCU_UT, ")
			.append(" ist.COD_CEN_FOR_PRO COD_SCU_UT_PRI, ")
			.append(" ist.COD_CEN_FOR_PRO COD_FOR_SCU_APP, ")
			.append(" reg.DES_REG, ")
			.append(" prv.DES_PRV, ")
			.append(" prv.COD_PRV, ")
			.append(" com.DES_COM, ")
			.append(" '0' FLG_IST_STA, ")
			.append(" '0' FLG_SCU_PAR, ")
			.append(" 'CP' COD_TIP_SIT, ")
			.append(" ist.DAT_ANN_SCO_RIF DAT_ANN_SCO_RIL, ")
			.append(" ist.DEN_CFP DES_NOM_SCU, ")
			.append(" ist.DES_LOC_CFP DES_LOC_SCU, ")
			.append(" ist.COD_CAP_CFP COD_CAP_SCU, ")
			.append(" ist.DES_IND_CFP DES_IND_SCU, ")
			.append(" ist.COD_NUM_TEL_CFP COD_TEL_SCU, ")
			.append(" ist.COD_FAX_CFP COD_FAX_SCU, ")
			.append(" ist.DES_IND_EMA_CFP DES_IND_EML, ")
			.append(" ist.DES_SIT_WEB_CFP DES_IND_WEB, ")
			.append(" infoscu.NUM_LAT_SCU, ")
			.append(" infoscu.NUM_LON_SCU, ")
			.append(" TO_CHAR (nvl(infoscu.dat_ora_ult_mov,to_date('01/09/2010', 'DD/MM/YYYY')), 'DD/MM/YYYY') dat_ora_ult_mov, ")
			.append(" ist.DES_IND_EMA_CER_CFP DES_IND_EMA_PCE ")
			.append(" FROM ")
			.append(" MFG1047_CENTRIFORPRO ist ")
			.append(" FULL OUTER JOIN TRS1005_INFGENSCUOLA infoscu ON(ist.COD_CEN_FOR_PRO = infoscu.COD_SCU_FOR_APP), ")
			.append(" MFG1012_REGIONE reg, ")
			.append(" MFG1013_PROVINCIA prv, ")
			.append(" MFG1014_COMUNE com ")
			.append(" WHERE ")
			.append(" ist.DAT_ANN_SCO_RIF = " + annoAccademico)
			.append(" AND ist.COD_COM_CFP = com.COD_COM ")
			.append(" AND com.COD_PRV = prv.COD_PRV ")
			.append(" AND prv.COD_REG = reg.COD_REG ")
			.append(" AND ist.COD_CEN_FOR_PRO = '" + codiceMeccanografico.trim().toUpperCase() + "' ");


			log.debug("QUERY getCentroFormazioneProfessionale: " + query.toString());

			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();

			if (rs.next()) {

				voScuola = new VOScuola();

				voScuola.setCodMecc(checkNull(rs.getString("COD_SCU_UT")));
				voScuola.setDatAnnScoRil(checkNull(rs.getString("DAT_ANN_SCO_RIL")));
				voScuola.setDescrizione(checkNull(rs.getString("DES_NOM_SCU")));
				voScuola.setCodForte(checkNull(rs.getString("COD_FOR_SCU_APP")));
				voScuola.setIndirizzo(checkNull(rs.getString("DES_IND_SCU")));
				voScuola.setLocalita(checkNull(rs.getString("DES_LOC_SCU")));
				voScuola.setCap(checkNull(rs.getString("COD_CAP_SCU")));
				voScuola.setComune(checkNull(rs.getString("DES_COM")));
				voScuola.setProvincia(checkNull(rs.getString("DES_PRV")));
				voScuola.setRegione(checkNull(rs.getString("DES_REG")));
				voScuola.setCodMeccIstRif(checkNull(rs.getString("COD_SCU_UT_PRI")));
				voScuola.setTelefono(checkNull(rs.getString("COD_TEL_SCU")));
				voScuola.setFax(checkNull(rs.getString("COD_FAX_SCU")));
				voScuola.setIndirizzoEmail(checkNull(rs.getString("DES_IND_EML")));
				voScuola.setSitoWeb(checkNull(rs.getString("DES_IND_WEB")));
				voScuola.setLatitudine(checkNull(rs.getString("NUM_LAT_SCU")));
				voScuola.setLongitudine(checkNull(rs.getString("NUM_LON_SCU")));
				voScuola.setDataUltimoAggiornamento(checkNull(rs.getString("DAT_ORA_ULT_MOV")));
				voScuola.setStataleNonStatale(checkNull(rs.getString("FLG_IST_STA")));
				voScuola.setParitariaNonParitaria(checkNull(rs.getString("FLG_SCU_PAR")));
				voScuola.setIndirizzoEmailPEC(checkNull(rs.getString("DES_IND_EMA_PCE")));

				voScuola.setOrdine("CENTRO FORMAZIONE PROFESSIONALE");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return voScuola;
	}

	public List<VOCommon> getPercorsiCFP() throws Exception
	{
		log.debug("getPercorsiCFP");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();

		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];
			
			String sql = " SELECT DISTINCT COD_CLA_MIN CODICE, DES_PER_CFP DESCRIZIONE " +
						 " FROM MFG1055_INDSCUISCR " + 
						 " WHERE COD_CLA_MIN IN ('Q3','Q4') ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();				
				voCommon.setCodice(rs.getString("CODICE"));
				voCommon.setDescrizione(rs.getString("DESCRIZIONE"));
				lista.add(voCommon);
			}		
		}
		catch (Exception e)
		{
			log.debug(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOCommon> getSettoriCFP(String cod_cla_min) throws Exception
	{
		log.debug("getSettoriCFP : " + cod_cla_min);
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();

		try
		{
			Object[] campi = new Object[] {cod_cla_min};
			int[] tipi = new int[] {Types.VARCHAR };
			
			String sql = " SELECT DISTINCT COD_CLA_MIN || '_' || DES_SET CODICE, DES_SET DESCRIZIONE " +
						 " FROM MFG1055_INDSCUISCR " + 
						 " WHERE COD_CLA_MIN = ? ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();				
				voCommon.setCodice(rs.getString("CODICE").replaceAll(" ", "blank"));
				voCommon.setDescrizione(rs.getString("DESCRIZIONE"));
				lista.add(voCommon);
			}		
		}
		catch (Exception e)
		{
			log.debug(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOCommon> getIndirizziDiStudioCFP(String cod_cla_min_des_set) throws Exception
	{
		log.debug("getIndirizziDiStudioCFP : " + cod_cla_min_des_set);
		String cod_cla_min = cod_cla_min_des_set.substring(0,cod_cla_min_des_set.indexOf("_"));
		String des_set = cod_cla_min_des_set.substring(cod_cla_min_des_set.indexOf("_")+1).replaceAll("blank", " ");
		log.debug("des_set : " + des_set);
		log.debug("cod_cla_min : " + cod_cla_min);
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCommon> lista = new ArrayList<VOCommon>();

		try
		{
			Object[] campi = new Object[] {cod_cla_min.trim(), des_set.trim()};
			int[] tipi = new int[] {Types.VARCHAR, Types.VARCHAR};
			
			String sql = " SELECT DISTINCT COD_IND_MIN CODICE, DES_IND DESCRIZIONE " +
						 " FROM MFG1055_INDSCUISCR " + 
						 " WHERE TRIM(COD_CLA_MIN) = ? " +
						 " AND TRIM(DES_SET) = ? ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOCommon voCommon = null;
			while (rs.next())
			{
				voCommon = new VOCommon();				
				voCommon.setCodice(rs.getString("CODICE"));
				voCommon.setDescrizione(rs.getString("DESCRIZIONE"));
				lista.add(voCommon);
			}		
		}
		catch (Exception e)
		{
			log.debug(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOAnniCorsoAlunni> getAlunniPerAnnoCorso(String codForScuApp) 
	throws Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConnection();
		List<VOAnniCorsoAlunni> result = new ArrayList<VOAnniCorsoAlunni>();
		log.debug("getAlunniPerAnnoCorso("+codForScuApp+")");
		
		try {
			StringBuffer query = new StringBuffer("SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, PER_ANN_COR,")
				.append("NVL(NUM_ALU, 0) NUM_ALU, NVL(NUM_CLA, 0) NUM_CLA ")				
				.append("FROM TRS1016_ANANAZANNCOR ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")				
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1016_ANANAZANNCOR ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")
				.append("ORDER BY PER_ANN_COR");
							
			Object[] campi = new Object[]{codForScuApp, codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR, Types.VARCHAR};
			
			log.debug("getAlunniPerAnnoCorso query: " + query.toString());
			
			ps = con.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi,false);
			
			VOAnniCorsoAlunni vo = null;
			while(rs.next()) {
				vo = new VOAnniCorsoAlunni();
				vo.setCodScuUt(rs.getString("COD_SCU_UT"));				
				vo.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				vo.setPerAnnCor(rs.getString("PER_ANN_COR"));
				vo.setNumAlu(rs.getString("NUM_ALU"));
				vo.setNumCla(rs.getString("NUM_CLA"));				
				result.add(vo);
			}
						
		}catch (Exception e)
		{
			log.debug(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return result;
	}

	public Integer getNumTotDocenti(String codForScuApp) throws Exception {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer result = null;
		
		try {
			StringBuffer query = new StringBuffer("SELECT SUM(NVL(NUM_DOC_RUO_MAS, 0)) + SUM(NVL(NUM_DOC_RUO_FEM, 0)) + ")
				.append("SUM(NVL(NUM_DOC_NRU_MAS, 0)) + SUM(NVL(NUM_DOC_NRU_FEM, 0)) TOT_DOC ")
				.append("FROM TRS1022_CONANNDOC ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1022_CONANNDOC ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT  ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?))");
			
			log.debug("getNumTotDocenti query: " + query);
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("TOT_DOC");
			}
			
			
		}catch (Exception e)
		{
			log.debug(e.getMessage(),e);
			throw e;
		}
		finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}						
			if(con != null) {
				con.close();

			}
		}
		return result;
	}
	
}

