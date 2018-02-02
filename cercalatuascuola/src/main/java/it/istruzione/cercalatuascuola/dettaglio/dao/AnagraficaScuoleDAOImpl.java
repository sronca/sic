package it.istruzione.cercalatuascuola.dettaglio.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.common.util.Utility;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnagraficaScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOClasse;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOCorsiCFP;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VONews;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOOffertaFormativa;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOPon;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOSuccursale;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTempiScuola;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipologia;

public class AnagraficaScuoleDAOImpl extends BaseDAO implements AnagraficaScuoleDAO {
	private Logger log = Logger.getLogger(AnagraficaScuoleDAOImpl.class);
		
	public AnagraficaScuoleDAOImpl(DataSource dataSource) {
		super(dataSource);
	}
	
/*	
 	VECCHIA GESTIONE
 	public List<VOCorsiCFP> getCorsiCP(String codMeccanografico, String datAnnScoRil) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOCorsiCFP> listaCorsi = new ArrayList<VOCorsiCFP>(); 
		
		try {
			StringBuffer query = new StringBuffer("SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, COD_COR, DES_TIT_COR, DES_CIC_FOR, DES_SET, DES_CMP, ")
				.append(" NUM_TOT_ORE, NUM_TOT_ORE_STG ")
				.append(" FROM TRS1058_SCUOLACORSO SC ")
				.append(" WHERE UPPER(SC.COD_SCU_UT) = UPPER(?) ")
				.append(" AND DAT_ANN_SCO_RIL = ? ")
				.append(" ORDER BY DES_TIT_COR ");
				
			log.debug("getCorsiCP query: " + query.toString());
			
			Object[] campi = new Object[]{codMeccanografico, datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			conn = this.getConnection();
					
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			
			VOCorsiCFP voCorso = null;
			while(rs.next()) {
				voCorso = new VOCorsiCFP();
				
				voCorso.setCodScuUt(rs.getString("COD_SCU_UT"));
				voCorso.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voCorso.setCpCodiceCorso(rs.getString("COD_COR"));
				voCorso.setCpTitoloCorso(rs.getString("DES_TIT_COR"));
				voCorso.setCpCicloFormativo(rs.getString("DES_CIC_FOR"));
				voCorso.setCpSettore(rs.getString("DES_SET"));
				voCorso.setCpComparto(rs.getString("DES_CMP"));
				voCorso.setCpTotaleOre(rs.getString("NUM_TOT_ORE"));
				voCorso.setCpTotaleOreStage(rs.getString("NUM_TOT_ORE_STG"));
				listaCorsi.add(voCorso);
			}
			return listaCorsi;
			
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
		}	
		
	}*/

	public List<VOCorsiCFP> getCorsiCP(String codMeccanografico, String datAnnScoRil) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOCorsiCFP> listaCorsi = new ArrayList<VOCorsiCFP>(); 
		
		try {
			StringBuffer query = new StringBuffer("SELECT A.COD_CEN_FOR_PRO COD_SCU_UT, A.DAT_ANN_SCO_RIF DAT_ANN_SCO_RIL, '' COD_COR, B.DES_IND DES_TIT_COR, B.DES_PER_CFP DES_CIC_FOR, B.DES_SET DES_SET, '' DES_CMP, ")
				.append(" B.NUM_TOT_ORE NUM_TOT_ORE, B.NUM_TOT_ORE_STA NUM_TOT_ORE_STG ")
				.append(" FROM MFG1048_ASSCFPCORSO A, MFG1055_INDSCUISCR  B ")
				.append(" WHERE UPPER(A.COD_CEN_FOR_PRO) = UPPER(?) ")
				.append(" AND A.DAT_ANN_SCO_RIF = ? ")
				.append(" AND A.COD_IND_MIN = B.COD_IND_MIN ")
				.append(" AND A.FLG_CLA_MIN = B.COD_CLA_MIN ")
				.append(" ORDER BY DES_IND ");
				
			log.debug("getCorsiCP query: " + query.toString());
			
			Object[] campi = new Object[]{codMeccanografico, datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			conn = this.getConnection();
					
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			
			VOCorsiCFP voCorso = null;
			while(rs.next()) {
				voCorso = new VOCorsiCFP();
				
				voCorso.setCodScuUt(rs.getString("COD_SCU_UT"));
				voCorso.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voCorso.setCpCodiceCorso(rs.getString("COD_COR"));
				voCorso.setCpTitoloCorso(rs.getString("DES_TIT_COR"));
				voCorso.setCpCicloFormativo(rs.getString("DES_CIC_FOR"));
				voCorso.setCpSettore(rs.getString("DES_SET"));
				voCorso.setCpComparto(rs.getString("DES_CMP"));
				voCorso.setCpTotaleOre(rs.getString("NUM_TOT_ORE"));
				voCorso.setCpTotaleOreStage(rs.getString("NUM_TOT_ORE_STG"));
				listaCorsi.add(voCorso);
			}
			return listaCorsi;
			
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
		}	
		
	}
	
	public VOAnagraficaScuola getScuolaByPrimaryKey(String codMeccanografico, String datAnnScoRil) throws Exception {
				
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
				
		try {
			StringBuffer query = new StringBuffer("SELECT I.COD_SCU_UT, I.DAT_ANN_SCO_RIL, I.COD_SCU_UT_PRI, I.DES_NOM_SCU, I.DEN_SCU_PRI, ")
				.append("I.DES_IND_SCU, I.DES_IND_EML, I.COD_TEL_SCU, I.COD_FAX_SCU, ")
				.append("R.COD_REG, R.DES_REG, P.COD_PRV, P.DES_PRV, C.COD_COM, C.DES_COM, C.COD_CAP,")
				.append("TS.COD_TIP_SIT, TS.DES_TIP_SIT, I.COD_FOR_SCU_APP, INFAGG.DES_IND_WEB,")
				.append("I.FLG_IST_STA, I.FLG_SED_DIR, I.DES_IND_EMA_PCE, I.COD_FIS, I.COD_CAR_SCU ")
				.append("FROM MFG1002_ANAGISTSCOL I, MFG1012_REGIONE R, MFG1013_PROVINCIA P,")
				.append("MFG1014_COMUNE C, MFG1028_TIPOSITO TS, TRS1046_INFAGGSCUOLA INFAGG ")			
				.append("WHERE I.COD_COM = C.COD_COM ")
				.append("AND C.COD_PRV = P.COD_PRV ")
				.append("AND P.COD_REG = R.COD_REG ")
				.append("AND I.COD_TIP_SIT = TS.COD_TIP_SIT ")
				.append("AND (I.DAT_INI_VAL IS NULL OR I.DAT_INI_VAL <= SYSDATE) ")
				.append("AND (I.DAT_FIN_VAL IS NULL OR I.DAT_FIN_VAL >= SYSDATE) ")
				.append("AND UPPER(I.COD_SCU_UT) = UPPER(?) ")
				.append("AND I.DAT_ANN_SCO_RIL = ? ")
				.append("AND I.COD_SCU_UT = INFAGG.COD_SCU_UT (+) ")
				.append("AND I.DAT_ANN_SCO_RIL = INFAGG.DAT_ANN_SCO_RIL (+) ");
			
			log.debug("getScuolaByPrimaryKey query: " + query.toString());
						
			Object[] campi = new Object[]{codMeccanografico, datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			conn = this.getConnection();
					
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAnagraficaScuola voAnag = null;
			if(rs.next()) {
				voAnag = new VOAnagraficaScuola();
				
				voAnag.setCodScuUt(rs.getString("COD_SCU_UT"));
				voAnag.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voAnag.setDesAnnScoRil(Utility.descrizioneAnnoAccademico(rs.getString("DAT_ANN_SCO_RIL")));
				voAnag.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				voAnag.setDenScuPri(rs.getString("DEN_SCU_PRI"));
				voAnag.setCodForScuApp(rs.getString("COD_FOR_SCU_APP"));
				voAnag.setDesNomScu(rs.getString("DES_NOM_SCU"));
				voAnag.setDesIndScu(rs.getString("DES_IND_SCU"));
				voAnag.setDesIndEml(rs.getString("DES_IND_EML"));
				voAnag.setCodTelScu(rs.getString("COD_TEL_SCU"));
				voAnag.setCodFaxScu(rs.getString("COD_FAX_SCU"));
				voAnag.setCodReg(rs.getString("COD_REG"));
				voAnag.setDesReg(rs.getString("DES_REG"));
				voAnag.setCodPrv(rs.getString("COD_PRV"));
				voAnag.setDesPrv(rs.getString("DES_PRV"));
				voAnag.setCodCom(rs.getString("COD_COM"));
				voAnag.setDesCom(rs.getString("DES_COM"));
				voAnag.setCodCap(rs.getString("COD_CAP"));
				voAnag.setCodTipSit(rs.getString("COD_TIP_SIT"));
				voAnag.setDesTipSit(rs.getString("DES_TIP_SIT"));
				voAnag.setFlgIstSta(rs.getString("FLG_IST_STA"));
				voAnag.setFlgSedDir(rs.getString("FLG_SED_DIR"));
				voAnag.setDesIndWeb(rs.getString("DES_IND_WEB"));
				voAnag.setDesIndEmaPce(rs.getString("DES_IND_EMA_PCE"));
				voAnag.setCodFis(rs.getString("COD_FIS"));
				voAnag.setCodCarScu(rs.getString("COD_CAR_SCU"));
				
			}
			
			return voAnag;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	public List<VOAnagraficaScuola> getScuoleByIstScol(String codMeccanografico,
			String datAnnScoRil) 
	throws Exception {
				
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOAnagraficaScuola> result = new ArrayList<VOAnagraficaScuola>();
				
		try {
			StringBuffer query = new StringBuffer("SELECT I.COD_SCU_UT, I.COD_SCU_UT_PRI, I.DES_NOM_SCU,")
				.append("I.DES_IND_SCU, I.DES_IND_EML, I.COD_TEL_SCU, I.COD_FAX_SCU, I.DAT_ANN_SCO_RIL,")
				.append("R.COD_REG, R.DES_REG, P.COD_PRV, P.DES_PRV, C.COD_COM, C.DES_COM, C.COD_CAP,")
				.append("TS.COD_TIP_SIT, TS.DES_TIP_SIT, I.COD_FOR_SCU_APP, I.DES_IND_WEB,")
				.append("I.FLG_IST_STA, I.FLG_SED_DIR, I.DES_IND_EMA_PCE, I.COD_FIS ")
				.append("FROM MFG1002_ANAGISTSCOL I, MFG1012_REGIONE R, MFG1013_PROVINCIA P,")
				.append("MFG1014_COMUNE C, MFG1028_TIPOSITO TS ")			
				.append("WHERE I.COD_COM = C.COD_COM ")
				.append("AND C.COD_PRV = P.COD_PRV ")
				.append("AND P.COD_REG = R.COD_REG ")
				.append("AND I.COD_TIP_SIT = TS.COD_TIP_SIT ")
				.append("AND (I.DAT_INI_VAL IS NULL OR I.DAT_INI_VAL <= SYSDATE) ")
				.append("AND (I.DAT_FIN_VAL IS NULL OR I.DAT_FIN_VAL >= SYSDATE) ")
				.append("AND UPPER(I.COD_SCU_UT_PRI) = UPPER(?) ")
				.append("AND I.COD_SCU_UT_PRI <> I.COD_SCU_UT ")                
			    .append("AND NOT (SUBSTR(I.COD_SCU_UT,3,2) = 'AA' AND SUBSTR(I.COD_SCU_UT,8,2) = '00') ")//modifica eliminazione codici organico fuzionale
				.append("AND I.DAT_ANN_SCO_RIL = ? ")
				.append("AND (I.COD_CAR_SCU IS NULL OR (I.COD_CAR_SCU <> 'O' AND I.COD_CAR_SCU <> 'R')) ");
				
			if(codMeccanografico.trim().toUpperCase().substring(2, 4).equals("AA") ||
					codMeccanografico.trim().toUpperCase().substring(2, 4).equals("EE")) {
				 
				query.append("AND SUBSTR(I.COD_SCU_UT, 8, 2) > '00' ");
			}		
				
			query.append("ORDER BY I.COD_SCU_UT ");
			
			log.debug("getScuoleByIstScol query: " + query.toString());
			
			Object[] campi = new Object[]{codMeccanografico, datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			conn = this.getConnection();
					
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAnagraficaScuola voAnag = null;
			while(rs.next()) {
				voAnag = new VOAnagraficaScuola();
				
				voAnag.setCodScuUt(rs.getString("COD_SCU_UT"));
				voAnag.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voAnag.setDesAnnScoRil(Utility.descrizioneAnnoAccademico(rs.getString("DAT_ANN_SCO_RIL")));
				voAnag.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				voAnag.setCodForScuApp(rs.getString("COD_FOR_SCU_APP"));
				voAnag.setDesNomScu(rs.getString("DES_NOM_SCU"));
				voAnag.setDesIndScu(rs.getString("DES_IND_SCU"));
				voAnag.setDesIndEml(rs.getString("DES_IND_EML"));
				voAnag.setCodTelScu(rs.getString("COD_TEL_SCU"));
				voAnag.setCodFaxScu(rs.getString("COD_FAX_SCU"));
				voAnag.setCodReg(rs.getString("COD_REG"));
				voAnag.setDesReg(rs.getString("DES_REG"));
				voAnag.setCodPrv(rs.getString("COD_PRV"));
				voAnag.setDesPrv(rs.getString("DES_PRV"));
				voAnag.setCodCom(rs.getString("COD_COM"));
				voAnag.setDesCom(rs.getString("DES_COM"));
				voAnag.setCodCap(rs.getString("COD_CAP"));
				voAnag.setCodTipSit(rs.getString("COD_TIP_SIT"));
				voAnag.setDesTipSit(rs.getString("DES_TIP_SIT"));
				voAnag.setFlgIstSta(rs.getString("FLG_IST_STA"));
				voAnag.setFlgSedDir(rs.getString("FLG_SED_DIR"));
				voAnag.setDesIndWeb(rs.getString("DES_IND_WEB"));
				voAnag.setDesIndEmaPce(rs.getString("DES_IND_EMA_PCE"));
				voAnag.setCodFis(rs.getString("COD_FIS"));
				
				result.add(voAnag);
			}
			
			return result;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	
	public List<VONews> getListaNews(String codUt, String annoRif) throws Exception
	{
		log.debug("INIZIO getListaNews");
		log.debug("codUt="+codUt);
		log.debug("annoRif="+annoRif);

		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<VONews> listaNews = new ArrayList<VONews>();
		
		try
		{
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, ")        
				 .append(" TO_CHAR(DAT_BAC, 'DD/MM/YYYY HH24:MI:SS') DAT_BAC, ")
				 .append(" DES_TIT_BAC, DES_OGG_BAC, FLG_STA_PUB, TO_CHAR(DAT_ORA_ULT_MOV, 'DD/MM/YYYY HH24:MI:SS') DAT_ORA_ULT_MOV ")       
				 .append(" FROM TRS1036_INFBACHECASCU ")
				 .append(" WHERE COD_SCU_UT = ? AND DAT_ANN_SCO_RIL = ? ")        
				 .append(" AND FLG_STA_PUB = 'S' ")
			 	 .append(" ORDER BY DAT_BAC DESC");
			
			log.debug("QUERY LISTA NEWS:" + query.toString());
			
			ps = con.prepareStatement(query.toString());
			ps.setString(1, codUt);
			ps.setString(2, annoRif);
			
			rs = ps.executeQuery();
			
			VONews voNews = null;
			while (rs.next())
			{
				voNews = new VONews();
				voNews.setCodScuUt(rs.getString("COD_SCU_UT"));
				voNews.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voNews.setDatBac(rs.getString("DAT_BAC"));
				voNews.setDesTitBac(rs.getString("DES_TIT_BAC"));
				voNews.setDesOggBac(rs.getString("DES_OGG_BAC"));
				voNews.setFlgStaPub(rs.getString("FLG_STA_PUB"));
				voNews.setDatOraUltMov(rs.getString("DAT_ORA_ULT_MOV"));
				listaNews.add(voNews);
			}
		}
		catch (Exception e)
		{
			log.error(e);
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
		log.debug("list.size="+listaNews.size());
		log.debug("FINE getListaNews");
		return listaNews;
	}			

	
	public List<VOSuccursale> getSuccursaliScuola(String codiceMeccanografico, String annoAccademico) throws Exception
	{
		log.debug("INIZIO getSuccursaliScuola");
		log.debug("codiceMeccanografico="+codiceMeccanografico);
		log.debug("annoAccademico="+annoAccademico);
		
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<VOSuccursale> succursaliScuola = new ArrayList<VOSuccursale>();

		try
		{
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT ")
			     .append("  COD_SCU_UT, DAT_ANN_SCO_RIF, PRG_SCU_SUC, DES_IND_SUC, NUM_LAT_SUC, NUM_LON_SUC, COD_TIP_SCU, COD_SET, ") 
			     .append("  PRG_PER_FOR, FLG_SCU_STA, FLG_SCU_PAR, DES_DEN_SUC, COD_TEL_SUC, COD_FAX_SUC, DES_IND_EMA  ")
			     .append(" FROM ")
			     .append(" TRS1009_INFSCUOLESUCC succ ") 
			     .append(" WHERE  ")
			     .append(" succ.COD_SCU_UT = '" + codiceMeccanografico +"' ")
			     .append(" AND succ.DAT_ANN_SCO_RIF = '"+annoAccademico+"' ");
			
			log.debug("query="+query);
			
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
				voSuccursale.setIndirizzo(checkNull(rs.getString("DES_IND_SUC")));
				voSuccursale.setLatitudine(checkNull(rs.getString("NUM_LAT_SUC")));
				voSuccursale.setLongitudine(checkNull(rs.getString("NUM_LON_SUC")));
				voSuccursale.setTipologiaScuola(checkNull(rs.getString("COD_TIP_SCU")));
				voSuccursale.setCodiceSettore(checkNull(rs.getString("COD_SET")));
				voSuccursale.setPercorsoFormativo(checkNull(rs.getString("PRG_PER_FOR")));
				voSuccursale.setFlagScuolaStatale(checkNull(rs.getString("FLG_SCU_STA")));
				voSuccursale.setFlagScuolaParitaria(checkNull(rs.getString("FLG_SCU_PAR")));
				voSuccursale.setDenominazione(checkNull(rs.getString("DES_DEN_SUC")));
				voSuccursale.setTelefono(checkNull(rs.getString("COD_TEL_SUC")));
				voSuccursale.setFax(checkNull(rs.getString("COD_FAX_SUC")));
				voSuccursale.setEmail(checkNull(rs.getString("DES_IND_EMA"))); 

				String descrizioneTipologiaScuola = Utility.getDescrizioneOrdineScuola(voSuccursale.getTipologiaScuola());
				if (Utility.isScuolaSecondariaSecondoGrado(voSuccursale.getTipologiaScuola())){
					if ("0".equals(voSuccursale.getFlagScuolaStatale()))
						descrizioneTipologiaScuola += " - " + getDescrizioneIndirizzoDiStudioScuoleStatali(voSuccursale.getCodiceSettore());
					else
						descrizioneTipologiaScuola += " - " + getDescrizioneTipoSito(voSuccursale.getTipologiaScuola());
				}  
				
				voSuccursale.setDescrizioneTipologiaScuola(descrizioneTipologiaScuola);
				succursaliScuola.add(voSuccursale);
			}
			
			log.debug("succursaliScuola.size()="+succursaliScuola.size());
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
		log.debug("FINE getSuccursaliScuola");
		return succursaliScuola;
	}		
	
	public String getDescrizioneIndirizzoDiStudioScuoleStatali(String codSet) throws Exception{
		
		log.debug("INIZIO getDescrizioneIndirizzoDiStudio");
		log.debug("codSet="+codSet);
		
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String desc = "";
		
		try
		{
			StringBuffer query = new StringBuffer();
			/*
			query.append(" SELECT COD_IND, DES_A ")
			     .append(" FROM MFGHB5IND IND, MFGHB5PFO PFO  ")
			     .append(" WHERE PFO.COD_SET = ?  ")
			     .append(" AND PFO.PRG_PER_FOR = IND.PRG_PER_FOR ") 
			     .append(" AND FLG_IND_NUO_ORD = 'S'  ")
			     .append(" AND ANSC_FINE > '201213'  ")
			     .append(" AND EXISTS (SELECT 1  ")
			     .append(" FROM MFGHB5ORM ORM  ")
			     .append(" WHERE IND.COD_IND = ORM.COD_IND ") 
			     .append(" AND ORM.ANSC_FINE > '201213') ");	
			*/
			
			query.append(" SELECT PFO.PRG_PER_FOR, PFO.DES_PER_FOR  FROM  MFGHB5PFO PFO WHERE PFO.COD_SET = ? ");
			
			log.debug("QUERY :" + query.toString());
			
			ps = con.prepareStatement(query.toString());
			ps.setString(1, codSet);

			rs = ps.executeQuery();
			
			
			if (rs.next())
				//desc = rs.getString("DES_A");
				desc = rs.getString("DES_PER_FOR");
			
			log.debug("desc :" + desc);
			
		}
		catch (Exception e)
		{
			log.error(e);
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

		log.debug("FINE getDescrizioneIndirizzoDiStudio");
		return desc;
		
	}

	public String getDescrizioneTipoSito(String codTipSit) throws Exception
	{
		log.debug("INIZIO getListaSettori");
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String desTipSit = null;
		try{
			String query = " SELECT DES_TIP_SIT FROM MFG1028_TIPOSITO where cod_tip_sit = '"+codTipSit+"' ";
			log.debug("query="+query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()){
				desTipSit =rs.getString("DES_TIP_SIT");
			}
			log.debug("desTipSit="+desTipSit);
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
		log.debug("FINE getListaSettori");
		return desTipSit;
	}		
	
	
	public List<VOClasse> getTempoScuola(String codiceMeccanografico, String annoAccademico,
			String codTipSer, String codSer	) throws Exception
	{
		log.debug("INIZIO getTempoScuola");
		log.debug("codiceMeccanografico="+codiceMeccanografico);
		log.debug("annoAccademico="+annoAccademico);
		log.debug("codTipSer="+codTipSer);
		log.debug("codSer="+codSer);
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String desAttSer = null;
		List<VOClasse> list = new ArrayList<VOClasse>();
		try{
			String query = " SELECT APS.DES_ATT_SER FROM TRS1015_SERVIZIOSCUOLA SS , TRS1003_ATTIVPERSERV APS WHERE " + 
							" SS.COD_SCU_UT(+) = '"+ codiceMeccanografico + "' " +  
							" AND SS.DAT_ANN_SCO_RIL(+) = '" + annoAccademico +"' " + 
							" AND SS.COD_TIP_SER = " + codTipSer + " AND SS.COD_SER = "+ codSer +" " + 
							" AND SS.COD_TIP_SER = APS.COD_TIP_SER AND SS.COD_SER = APS.COD_SER AND SS.COD_ATT_SER = APS.COD_ATT_SER " ;
			log.debug("query="+query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()){
				desAttSer =rs.getString("DES_ATT_SER");
				log.debug("desAttSer="+desAttSer);
				VOClasse vo = new VOClasse();
				vo.setDescrizione(desAttSer);
				list.add(vo);
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
		log.debug("FINE getTempoScuola");
		return list;
	}		


	public String getUrlHomePageIol(String desTipSer) throws Exception
	{
		log.debug("INIZIO getUrlHomePageIol");
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String out = null;
		try{
			String query = " SELECT DES_IND_URL FROM TRS1047_CONGSERVAPPLIC WHERE DES_TIP_SER = ? AND TRUNC(SYSDATE) BETWEEN DAT_INI_VAL AND DAT_FIN_VAL ";
			log.debug("query="+query);
			ps = con.prepareStatement(query);
			ps.setString(1, desTipSer);
			
			rs = ps.executeQuery();
			if (rs.next()){
				out =rs.getString("DES_IND_URL");
			}
			log.debug("out="+out);
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
		log.debug("FINE getUrlHomePageIol");
		return out;
	}	
	
	
/*	
 	VECCHIA GESTIONE
 	public VOAnagraficaScuola getCentroFormazioneProfessionaleByPrimaryKey(String codMeccanografico, String datAnnScoRil) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
				
		try {
			StringBuffer query = new StringBuffer("SELECT I.COD_SCU_UT, I.DAT_ANN_SCO_RIL, I.COD_SCU_UT_PRI, I.DES_NOM_SCU,")
				.append("I.DES_IND_SCU, I.DES_IND_EML, I.COD_TEL_SCU, I.COD_FAX_SCU, ")
				.append("R.COD_REG, R.DES_REG, P.COD_PRV, P.DES_PRV, C.COD_COM, C.DES_COM, C.COD_CAP,")
				.append("'CP' as cod_tip_sit, 'CENTRO FORMAZIONE PROFESSIONALE' as des_tip_sit, I.COD_FOR_SCU_APP, INFAGG.DES_IND_WEB,")
				.append("I.FLG_IST_STA, I.FLG_SED_DIR, I.DES_IND_EMA_PCE, I.COD_FIS ")
				.append("FROM ursricscu_own.TRS1048_ANAGRAFECPA I, MFG1012_REGIONE R, MFG1013_PROVINCIA P,")
				.append("MFG1014_COMUNE C, TRS1046_INFAGGSCUOLA INFAGG ")			
				.append("WHERE I.COD_COM = C.COD_COM ")
				.append("AND C.COD_PRV = P.COD_PRV ")
				.append("AND P.COD_REG = R.COD_REG ")
				.append("AND (I.DAT_INI_VAL IS NULL OR I.DAT_INI_VAL <= SYSDATE) ")
				.append("AND (I.DAT_FIN_VAL IS NULL OR I.DAT_FIN_VAL >= SYSDATE) ")
				.append("AND UPPER(I.COD_SCU_UT) = UPPER(?) ")
				.append("AND I.DAT_ANN_SCO_RIL = ? ")
				.append("AND I.COD_SCU_UT = INFAGG.COD_SCU_UT (+) ")
				.append("AND I.DAT_ANN_SCO_RIL = INFAGG.DAT_ANN_SCO_RIL (+) ");
			
			log.debug("getCentroFormazioneProfessionaleByPrimaryKey query: " + query.toString());
						
			Object[] campi = new Object[]{codMeccanografico, datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			conn = this.getConnection();
					
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAnagraficaScuola voAnag = null;
			if(rs.next()) {
				voAnag = new VOAnagraficaScuola();
				
				voAnag.setCodScuUt(rs.getString("COD_SCU_UT"));
				voAnag.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voAnag.setDesAnnScoRil(Utility.descrizioneAnnoAccademico(rs.getString("DAT_ANN_SCO_RIL")));
				voAnag.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				voAnag.setCodForScuApp(rs.getString("COD_FOR_SCU_APP"));
				voAnag.setDesNomScu(rs.getString("DES_NOM_SCU"));
				voAnag.setDesIndScu(rs.getString("DES_IND_SCU"));
				voAnag.setDesIndEml(rs.getString("DES_IND_EML"));
				voAnag.setCodTelScu(rs.getString("COD_TEL_SCU"));
				voAnag.setCodFaxScu(rs.getString("COD_FAX_SCU"));
				voAnag.setCodReg(rs.getString("COD_REG"));
				voAnag.setDesReg(rs.getString("DES_REG"));
				voAnag.setCodPrv(rs.getString("COD_PRV"));
				voAnag.setDesPrv(rs.getString("DES_PRV"));
				voAnag.setCodCom(rs.getString("COD_COM"));
				voAnag.setDesCom(rs.getString("DES_COM"));
				voAnag.setCodCap(rs.getString("COD_CAP"));
				voAnag.setCodTipSit(rs.getString("COD_TIP_SIT"));
				voAnag.setDesTipSit(rs.getString("DES_TIP_SIT"));
				voAnag.setFlgIstSta(rs.getString("FLG_IST_STA"));
				voAnag.setFlgSedDir(rs.getString("FLG_SED_DIR"));
				voAnag.setDesIndWeb(rs.getString("DES_IND_WEB"));
				voAnag.setDesIndEmaPce(rs.getString("DES_IND_EMA_PCE"));
				voAnag.setCodFis(rs.getString("COD_FIS"));
				
				voAnag.setFlagCentroFormazioneProfessionale("S");
			}
			
			return voAnag;
		}
		catch (Exception e)
		{
			throw e;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}*/

	public VOAnagraficaScuola getCentroFormazioneProfessionaleByPrimaryKey(String codMeccanografico, String datAnnScoRil) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
				
		try {
			StringBuffer query = new StringBuffer("SELECT I.COD_CEN_FOR_PRO COD_SCU_UT, I.DAT_ANN_SCO_RIF DAT_ANN_SCO_RIL, I.COD_CEN_FOR_PRO COD_SCU_UT_PRI, I.DEN_CFP DES_NOM_SCU, I.DEN_CFP DEN_SCU_PRI,")
				.append("I.DES_IND_CFP DES_IND_SCU, I.DES_IND_EMA_CFP DES_IND_EML, I.COD_NUM_TEL_CFP COD_TEL_SCU, I.COD_FAX_CFP COD_FAX_SCU, ")
				.append("R.COD_REG, R.DES_REG, P.COD_PRV, P.DES_PRV, C.COD_COM, C.DES_COM, C.COD_CAP,")
				.append("'CP' as cod_tip_sit, 'CENTRO FORMAZIONE PROFESSIONALE' as des_tip_sit, I.COD_CEN_FOR_PRO COD_FOR_SCU_APP, I.DES_SIT_WEB_CFP DES_IND_WEB,")
				.append("'0' AS FLG_IST_STA, '0' AS FLG_SED_DIR, I.DES_IND_EMA_CER_CFP DES_IND_EMA_PCE, '' AS COD_FIS, '0' AS COD_CAR_SCU ")
				.append("FROM MFG1047_CENTRIFORPRO I, MFG1012_REGIONE R, MFG1013_PROVINCIA P,")
				.append("MFG1014_COMUNE C, TRS1046_INFAGGSCUOLA INFAGG ")			
				.append("WHERE I.COD_COM_CFP = C.COD_COM ")
				.append("AND C.COD_PRV = P.COD_PRV ")
				.append("AND P.COD_REG = R.COD_REG ")
				.append("AND UPPER(I.COD_CEN_FOR_PRO) = UPPER(?) ")
				.append("AND I.DAT_ANN_SCO_RIF = ? ")
				.append("AND I.COD_CEN_FOR_PRO = INFAGG.COD_SCU_UT (+) ")
				.append("AND I.DAT_ANN_SCO_RIF = INFAGG.DAT_ANN_SCO_RIL (+) ");
			
			log.debug("getCentroFormazioneProfessionaleByPrimaryKey query: " + query.toString());
						
			Object[] campi = new Object[]{codMeccanografico, datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			conn = this.getConnection();
					
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAnagraficaScuola voAnag = null;
			if(rs.next()) {
				voAnag = new VOAnagraficaScuola();
				
				voAnag.setCodScuUt(rs.getString("COD_SCU_UT"));
				voAnag.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voAnag.setDesAnnScoRil(Utility.descrizioneAnnoAccademico(rs.getString("DAT_ANN_SCO_RIL")));
				voAnag.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				voAnag.setCodForScuApp(rs.getString("COD_FOR_SCU_APP"));
				voAnag.setDesNomScu(rs.getString("DES_NOM_SCU"));
				voAnag.setDenScuPri(rs.getString("DEN_SCU_PRI"));
				voAnag.setDesIndScu(rs.getString("DES_IND_SCU"));
				voAnag.setDesIndEml(rs.getString("DES_IND_EML"));
				voAnag.setCodTelScu(rs.getString("COD_TEL_SCU"));
				voAnag.setCodFaxScu(rs.getString("COD_FAX_SCU"));
				voAnag.setCodReg(rs.getString("COD_REG"));
				voAnag.setDesReg(rs.getString("DES_REG"));
				voAnag.setCodPrv(rs.getString("COD_PRV"));
				voAnag.setDesPrv(rs.getString("DES_PRV"));
				voAnag.setCodCom(rs.getString("COD_COM"));
				voAnag.setDesCom(rs.getString("DES_COM"));
				voAnag.setCodCap(rs.getString("COD_CAP"));
				voAnag.setCodTipSit(rs.getString("COD_TIP_SIT"));
				voAnag.setDesTipSit(rs.getString("DES_TIP_SIT"));
				voAnag.setFlgIstSta(rs.getString("FLG_IST_STA"));
				voAnag.setFlgSedDir(rs.getString("FLG_SED_DIR"));
				voAnag.setDesIndWeb(rs.getString("DES_IND_WEB"));
				voAnag.setDesIndEmaPce(rs.getString("DES_IND_EMA_PCE"));
				voAnag.setCodFis(rs.getString("COD_FIS"));
				voAnag.setCodCarScu(rs.getString("COD_CAR_SCU"));
				
				voAnag.setFlagCentroFormazioneProfessionale("S");
			}
			
			return voAnag;
		}
		catch (Exception e)
		{
			throw e;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	
	
	public boolean getRadioButtonServizi(String desTipSer) throws Exception
	{
		log.debug("INIZIO getRadioButtonServizi");
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		boolean presenti=false;
		
		try{
			String query = " select cod_att_ser, des_att_ser, des_val_amm from TRS1003_ATTIVPERSERV where cod_tip_ser=11 and cod_ser=1 ";
			log.debug("query="+query);
			ps = con.prepareStatement(query);
			ps.setString(1, desTipSer);
			
			rs = ps.executeQuery();
			
			if (rs.next()){
				presenti=true;
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
		log.debug("FINE getRadioButtonServizi");
		return presenti;
	}	
	
	public List<VOOffertaFormativa> getListaIndirizziAnnoSuccessivo(String scuola,int anno)
	throws Exception {
		
		log.debug("INIZIO getListaIndirizziAnnoSuccessivo");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOOffertaFormativa> listaOffForm = new ArrayList<VOOffertaFormativa>();
		try {
			/*
			StringBuffer query = new StringBuffer("select cod_ind_min,cod_cla_min,des_ind_stu from TRS1049_INDSTUPROSCU ")
				.append(" where cod_scu_ut = ? and dat_ann_sco_ril = ? ");
			*/
			StringBuffer query = new StringBuffer("SELECT distinct ind.cod_ind_min, ind.cod_cla_min, ind.des_ind_stu FROM trs1049_indstuproscu ind ")
			.append(" WHERE ind.cod_scu_ut = ? AND ind.dat_ann_sco_ril = ? ");			
			
			Object[] campi = new Object[]{scuola,anno};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			log.debug("getListaIndirizziAnnoSuccessivo query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOOffertaFormativa voOffFor = null;
		
			while (rs.next()) {
													
					voOffFor = new VOOffertaFormativa();
					voOffFor.setCodSer(rs.getString("cod_ind_min")+"|"+rs.getString("cod_cla_min")+"|"+rs.getString("des_ind_stu"));
					voOffFor.setDesSer(rs.getString("des_ind_stu"));
			        listaOffForm.add(voOffFor);
			}
			if(listaOffForm.size()==0)
			{
			 voOffFor = new VOOffertaFormativa();
			 voOffFor.setCodSer("");
			 voOffFor.setDesSer("Dati non disponibili per la scuola");			 	
			 listaOffForm.add(voOffFor);
			}	
			log.debug("ListaIndirizziAnnoSuccessivo: \n"+listaOffForm.toString());
			log.debug("FINE getListaIndirizziAnnoSuccessivo");
			
			return listaOffForm;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaIndirizziAnnoSuccessivo");
			
		}
	}			
	
	public List<VOOffertaFormativa> getListaIndirizziPerAnnoCorrente(String scuola,int anno,int annoCorso,String codTipSit) 
	throws Exception {
		
		log.debug("INIZIO getListaIndirizziPerAnnoCorrente");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOOffertaFormativa> listaOffForm = new ArrayList<VOOffertaFormativa>();
		try {
			/*
			StringBuffer query = new StringBuffer("select t2.cod_ind_min,t2.cod_cla_min,t2.des_ind from MFG1054_SCUINDSCUMIN t1, MFG1052_INDSCUMIN t2 where t1.cod_ind_min = t2.cod_ind_min and t1.cod_cla_min = t2.cod_cla_min ")
					//.append(" and "+annoCorso+" between t2.per_ann_ini_val and t2.num_dur_ann ")
			         .append(" and t1.num_ann_cor = ? ")
					 .append(" and t1.cod_scu_ut = ? and t1.per_ann_sco_val = ? ");
			*/
			StringBuffer query = new StringBuffer("select t2.cod_ind_min,t2.cod_cla_min,t2.des_ind from MFG1054_SCUINDSCUMIN t1, MFG1052_INDSCUMIN t2 where t1.cod_ind_min = t2.cod_ind_min and t1.cod_cla_min = t2.cod_cla_min ")
			.append(" and t1.num_ann_cor = ? ")
			 .append(" and t1.cod_scu_ut = ? and t1.per_ann_sco_val = ? ");
			
			Object[] campi = new Object[]{annoCorso,scuola,anno};
			int[] tipi = new int[]{Types.INTEGER,Types.VARCHAR, Types.INTEGER};
			
			log.debug("getListaIndirizziPerAnnoCorrente query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOOffertaFormativa voOffFor = null;
		
			while (rs.next()) {
													
					voOffFor = new VOOffertaFormativa();
					voOffFor.setCodSer(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3));
					voOffFor.setDesSer(rs.getString(3));
			        listaOffForm.add(voOffFor);
			}
			if(listaOffForm.size()==0)
			{
			 voOffFor = new VOOffertaFormativa();
			 voOffFor.setCodSer("");
			 if(codTipSit.equalsIgnoreCase("MM"))
			  voOffFor.setDesSer("ORDINARIO");			 	
			 else
			  voOffFor.setDesSer("Dati non disponibili per la scuola");				 
			 listaOffForm.add(voOffFor);
			}			
			
			log.debug("FINE getListaIndirizziPerAnnoCorrente");
			
			return listaOffForm;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaIndirizziPerAnnoCorrente");
			
		}
	}
	
	public List<VOOffertaFormativa> getListaIndirizziPerAnnoCorrenteScuolaSerale(
			 String $codScuUt
			,int $anno
			,List<Integer> $anniCorso
			,String $codTipSit) throws Exception{
		log.info("getListaIndirizziPerAnnoCorrenteScuolaSerale('"  + $codScuUt + "'," + $anno + "," + $anniCorso + ",'" +  $codTipSit + "')...") ;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOOffertaFormativa> listaOffForm = new ArrayList<VOOffertaFormativa>();
		try {
			StringBuffer query = new StringBuffer();
			query.append("select distinct");
			query.append(" t2.cod_ind_min");
			query.append(",t2.cod_cla_min");
			query.append(",t2.des_ind");
			query.append(" from MFG1054_SCUINDSCUMIN t1, MFG1052_INDSCUMIN t2");
			query.append(" where t1.cod_ind_min = t2.cod_ind_min");
			query.append(" and t1.cod_cla_min = t2.cod_cla_min");
			query.append(" and t1.num_ann_cor IN (");
			for( int i = 0 ; i < $anniCorso.size(); i++ ) {
				query.append("?");
				if(i < $anniCorso.size() - 1){
					query.append(",");
				}
			}
			query.append(")");
			query.append(" and t1.cod_scu_ut = ?");
			query.append(" and t1.per_ann_sco_val = ?");
			Object[] campi = new Object[$anniCorso.size() + 2];
			int[] tipi = new int[$anniCorso.size() + 2];
			for(int j = 0; j < $anniCorso.size(); j++){
				campi[j] = $anniCorso.get(j);
				tipi[j] = Types.INTEGER;
			}
			campi[$anniCorso.size()] = $codScuUt;
			tipi[$anniCorso.size()] = Types.VARCHAR;
			campi[$anniCorso.size() + 1] = $anno;
			tipi[$anniCorso.size() + 1] = Types.INTEGER;
			log.info("query: " + query.toString());
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			VOOffertaFormativa voOffFor = null;
			while (rs.next()) {
				voOffFor = new VOOffertaFormativa();
				voOffFor.setCodSer(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3));
				voOffFor.setDesSer(rs.getString(3));
		        listaOffForm.add(voOffFor);
			}
			if(listaOffForm.size()==0){
				voOffFor = new VOOffertaFormativa();
				voOffFor.setCodSer("");
				if($codTipSit.equalsIgnoreCase("MM")){
					voOffFor.setDesSer("ORDINARIO");
				}
				else{
					voOffFor.setDesSer("Dati non disponibili per la scuola");
				}
				listaOffForm.add(voOffFor);
			}			
			log.debug("FINE getListaIndirizziPerAnnoCorrenteScuolaSerale");
			return listaOffForm;
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
			if(conn != null) {
				conn.close();
				conn = null;
			}
			log.debug("FINALLY getListaIndirizziPerAnnoCorrenteScuolaSerale");
		}
	}
    

	public List<VOOffertaFormativa> getListaIndirizziPerAnnoCorrente(String scuola,int anno,String codTipSit) 
	throws Exception {
		
		log.debug("INIZIO getListaIndirizziPerAnnoCorrente");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOOffertaFormativa> listaOffForm = new ArrayList<VOOffertaFormativa>();
		try {
			StringBuffer query = new StringBuffer("select distinct t2.cod_ind_min,t2.cod_cla_min,t2.des_ind from MFG1054_SCUINDSCUMIN t1, MFG1052_INDSCUMIN t2 where t1.cod_ind_min = t2.cod_ind_min and t1.cod_cla_min = t2.cod_cla_min ")
			 .append(" and t1.cod_scu_ut = ? and t1.per_ann_sco_val = ? ");
			
			Object[] campi = new Object[]{scuola,anno};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			log.debug("getListaIndirizziPerAnnoCorrente query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOOffertaFormativa voOffFor = null;
		
			while (rs.next()) {
													
					voOffFor = new VOOffertaFormativa();
					voOffFor.setCodSer(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3));
					voOffFor.setDesSer(rs.getString(3));
			        listaOffForm.add(voOffFor);
			}
			if(listaOffForm.size()==0)
			{
			 voOffFor = new VOOffertaFormativa();
			 voOffFor.setCodSer("");
			 if(codTipSit.equalsIgnoreCase("MM"))
			  voOffFor.setDesSer("ORDINARIO");			 	
			 else
			  voOffFor.setDesSer("Dati non disponibili per la scuola");				 
			 listaOffForm.add(voOffFor);
			}			
			
			log.debug("FINE getListaIndirizziPerAnnoCorrente");
			
			return listaOffForm;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaIndirizziPerAnnoCorrente");
			
		}
	}		

	
	public List<VOOffertaFormativa> getListaPercorsi(String scuola,int anno) 
	throws Exception {
		
		log.debug("INIZIO getListaPercorsi");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOOffertaFormativa> listaOffForm = new ArrayList<VOOffertaFormativa>();
		try {

			StringBuffer query = new StringBuffer(" SELECT distinct t2.des_per FROM mfg1054_scuindscumin t1, mfg1052_indscumin t2 ")
	        .append(" WHERE t1.cod_ind_min = t2.cod_ind_min AND t1.cod_cla_min = t2.cod_cla_min ") 
			 .append(" and t1.cod_scu_ut = ? and t1.per_ann_sco_val = ? ");
			
			Object[] campi = new Object[]{scuola,anno};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			log.debug("getListaPercorsi query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOOffertaFormativa voOffFor = null;
		
			while (rs.next()) {
													
					voOffFor = new VOOffertaFormativa();
					voOffFor.setDesPer(rs.getString(1));
			        listaOffForm.add(voOffFor);
			}
		
			
			log.debug("FINE getListaPercorsi");
			
			return listaOffForm;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaPercorsi");
			
		}
	}		

	
	public List<VOOffertaFormativa> getListaIndirizziPerAnnoCorrenteSucc(String scuola,int anno,int annoCorso,String codTipSit) 
	throws Exception {
		
		log.debug("INIZIO getListaIndirizziPerAnnoCorrenteSucc");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOOffertaFormativa> listaOffForm = new ArrayList<VOOffertaFormativa>();
		try {
			/*
			StringBuffer query = new StringBuffer("select t2.cod_ind_min,t2.cod_cla_min,t2.des_ind from MFG1054_SCUINDSCUMIN t1, MFG1055_INDSCUISCR t2 where t1.cod_ind_min = t2.cod_ind_min and t1.cod_cla_min = t2.cod_cla_min ")
					//.append(" and "+annoCorso+" between t2.per_ann_ini_val and t2.num_dur_ann ")
			         .append(" and t1.num_ann_cor = ? ")
					 .append(" and t1.cod_scu_ut = ? and t1.per_ann_sco_val = ? ");
			*/
			StringBuffer query = new StringBuffer("select distinct t2.cod_ind_min,t2.cod_cla_min,t2.des_ind from MFG1054_SCUINDSCUMIN t1, MFG1055_INDSCUISCR t2 where t1.cod_ind_min = t2.cod_ind_min and t1.cod_cla_min = t2.cod_cla_min ")
			.append(" and t1.num_ann_cor <= ? ")
			 .append(" and t1.cod_scu_ut = ? and t1.per_ann_sco_val = ? ");
			
															
			Object[] campi = new Object[]{annoCorso,scuola,anno};
			int[] tipi = new int[]{Types.INTEGER,Types.VARCHAR, Types.INTEGER};
			
			log.debug("getListaIndirizziPerAnnoCorrenteSucc query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOOffertaFormativa voOffFor = null;
		
			while (rs.next()) {
													
					voOffFor = new VOOffertaFormativa();
					voOffFor.setCodSer(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3));
					voOffFor.setDesSer(rs.getString(3));
			        listaOffForm.add(voOffFor);
			}
			if(listaOffForm.size()==0)
			{
			 voOffFor = new VOOffertaFormativa();
			 voOffFor.setCodSer("");
			 if(codTipSit.equalsIgnoreCase("MM"))
			  voOffFor.setDesSer("ORDINARIO");			 	
			 else
			  voOffFor.setDesSer("Dati non disponibili per la scuola");				 
			 listaOffForm.add(voOffFor);
			}			
			
			log.debug("ListaIndirizziPerAnnoCorrenteSucc: \n"+listaOffForm.toString());
			log.debug("FINE getListaIndirizziPerAnnoCorrenteSucc");
			
			return listaOffForm;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaIndirizziPerAnnoCorrenteSucc");
			
		}
	}			
	
	
	public List<VOTempiScuola> getListaTempiScuola(String scuola,int anno) 
	throws Exception {
		
		log.debug("INIZIO getListaTempiScuola");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOTempiScuola> listaTmp = new ArrayList<VOTempiScuola>();
		try {
			StringBuffer query = new StringBuffer("select distinct des_tem_scu,t1.prg_tem_scu from MFG1051_TEMPOSCUOLA t1,MFG1053_SCUTEMPOSCUOLA t2 ")
					.append(" where t1.prg_tem_scu = t2.prg_tem_scu ")
                    .append(" and t1.per_ann_sco_val = t2.per_ann_sco_val ")
                    .append(" and t2.cod_scu_ut = ? ")
					.append(" and t1.per_ann_sco_val = ? ");
			
			Object[] campi = new Object[]{scuola,anno};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			log.debug("getListaTempiScuola query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOTempiScuola voTmp = null;
		
			while (rs.next()) {
													
				voTmp = new VOTempiScuola();
				voTmp.setCodSer(rs.getString(2));
				voTmp.setDesSer(rs.getString(1));
			    listaTmp.add(voTmp);
			}
			
			log.debug("FINE getListaTempiScuola");
			
			return listaTmp;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaTempiScuola");
			
		}
	}			
	
	public List<VOTempiScuola> getListaTempiScuolaAnnoCorso(String scuola,int anno,int annoCorso,String codTipSit) 
	throws Exception {
		
		log.debug("INIZIO getListaTempiScuola");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOTempiScuola> listaTmp = new ArrayList<VOTempiScuola>();
		try {
			StringBuffer query = new StringBuffer("select des_tem_scu,t1.prg_tem_scu from MFG1051_TEMPOSCUOLA t1,MFG1053_SCUTEMPOSCUOLA t2 ")
					.append(" where t1.prg_tem_scu = t2.prg_tem_scu ")
                    .append(" and t1.per_ann_sco_val = t2.per_ann_sco_val ")
                    .append(" and t2.cod_scu_ut = ? ")
					.append(" and t1.per_ann_sco_val = ? and t2.num_ann_cor = ? ");
			
			Object[] campi = new Object[]{scuola,anno,annoCorso};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.INTEGER};
			
			log.debug("getListaTempiScuola query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOTempiScuola voTmp = null;
		
			while (rs.next()) {
													
				voTmp = new VOTempiScuola();
				voTmp.setCodSer(rs.getString(2));
				voTmp.setDesSer(rs.getString(1));
			    listaTmp.add(voTmp);
			}
			if(listaTmp.size()==0)
			{
			 voTmp = new VOTempiScuola();
			 voTmp.setCodSer("");
		     voTmp.setDesSer("Dati non disponibili per la scuola");				 
			 listaTmp.add(voTmp);
			}			
			
			log.debug("FINE getListaTempiScuola");
			
			return listaTmp;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaTempiScuola");
			
		}
	}				
	
	//COntrollo PON
	public String getIsPon(String codScuUt) throws Exception
	{
		log.debug("INIZIO getIsPon");
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String out = "false";
		try{
			String query = " select * from MFG1003_PIANO where cod_scu_ut = ? and cod_sta_pia in (5, 7) ";
			log.debug("query="+query);
			ps = con.prepareStatement(query);
			ps.setString(1, codScuUt);
			
			rs = ps.executeQuery();
			if (rs.next()){
				out = "true";
			}
			log.debug("out="+out);
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
		log.debug("FINE getIsPon");
		return out;
	}		
	
	
	@SuppressWarnings("rawtypes")
	public List<VOPon> getListaDatiSintesiPon1(String scuola) 
	throws Exception {
		
		log.debug("INIZIO getListaDatiSintesiPon");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOPon> listaTmp = new ArrayList<VOPon>();
		List<VOPon> listaNew = new ArrayList<VOPon>();
		try {
			StringBuffer query = new StringBuffer("SELECT t3.anno_riferimento as annorif,count(t1.cod_pia_naz) as NUMPROGETTI,t1.cod_fon, t1.cod_Sta_ric, SUM(t1.imp_ric_aut) as impaut ")
                                                  .append(" FROM MFG1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3 ")
                                                  .append(" where t1. cod_fon= '01' ")
                                                  .append(" and t1.cod_sta_ric in (5, 7, 13) ")
                                                  .append(" and t1.cod_pia=t2.cod_pia ")
                                                  .append(" and t1.cod_fon=t2.cod_fon ")
                                                  .append(" and t2.COD_SCU_UT = ? ")
                                                  .append(" and t2.cod_sta_pia in (5, 7) ")
                                                  .append(" and t2.cod_tip_ffi= '0' ")
                                                  .append(" and t3.idbando=t2.num_ban ")
                                                  .append(" group by t1.cod_fon, t1.cod_Sta_ric, t3.anno_riferimento ")
                                                  .append(" order by t3.anno_riferimento");

			
			Object[] campi = new Object[]{scuola};
			int[] tipi = new int[]{Types.VARCHAR};
			
			log.debug("getListaDatiSintesiPon query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon voTmp = null;
			VOPon voNew = null;
		
			while (rs.next()) {
													
				voTmp = new VOPon();
				voTmp.setAnno(rs.getString(1));
				voTmp.setNumProgettiFsePon(rs.getString(2));
				voTmp.setImpFinFsePon(rs.getString(5));
			    listaTmp.add(voTmp);
			}
			
			
			for(int i=2007;i<=2015;i++)
			{
			 boolean trovato = false;	
			 Iterator ite = listaTmp.iterator();			 
			 while(ite.hasNext() && !trovato)
			 {
			  voTmp = new VOPon();
			  voTmp = (VOPon)ite.next();
			  if(Integer.parseInt(voTmp.getAnno())==i)
			  {
			   trovato = true;
			   voNew = new VOPon();
			   voNew = voTmp;
			  }	
			 }	
			 if(!trovato)
			 {
			  voNew = new VOPon();
			  voNew.setAnno(String.valueOf(i));
			 }
			 listaNew.add(voNew);			 
			}
						
			log.debug("FINE getListaDatiSintesiPon");
			
			return listaNew;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaDatiSintesiPon");
			
		}
	}					
	
	@SuppressWarnings("rawtypes")
	public List<VOPon> getListaDatiSintesiPon2(String scuola) 
	throws Exception {
		
		log.debug("INIZIO getListaDatiSintesiPon2");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOPon> listaTmp = new ArrayList<VOPon>();
		List<VOPon> listaNew = new ArrayList<VOPon>();
		try {
			StringBuffer query = new StringBuffer("SELECT t3.anno_riferimento as annorif,count(t1.cod_pia_naz) as NUMPROGETTI,t1.cod_fon, t1.cod_Sta_ric, SUM(t1.imp_ric_aut) as impaut ")
                                                  .append(" FROM MFG1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3 ")
                                                  .append(" where t1. cod_fon= '01' ")
                                                  .append(" and t1.cod_sta_ric in (5, 7, 13) ")
                                                  .append(" and t1.cod_pia=t2.cod_pia ")
                                                  .append(" and t1.cod_fon=t2.cod_fon ")
                                                  .append(" and t2.COD_SCU_UT = ? ")
                                                  .append(" and t2.cod_sta_pia in (5, 7) ")
                                                  .append(" and t2.cod_tip_ffi= '1' ")
                                                  .append(" and t3.idbando=t2.num_ban ")
                                                  .append(" group by t1.cod_fon, t1.cod_Sta_ric, t3.anno_riferimento ")
                                                  .append(" order by t3.anno_riferimento");

			
			Object[] campi = new Object[]{scuola};
			int[] tipi = new int[]{Types.VARCHAR};
			
			log.debug("getListaDatiSintesiPon2 query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon voTmp = null;
			VOPon voNew = null;
		
			while (rs.next()) {
													
				voTmp = new VOPon();
				voTmp.setAnno(rs.getString(1));
				voTmp.setNumProgettiFsePor(rs.getString(2));
				voTmp.setImpFinFsePor(rs.getString(5));
			    listaTmp.add(voTmp);
			}
			
			
			for(int i=2007;i<=2015;i++)
			{
			 boolean trovato = false;	
			 Iterator ite = listaTmp.iterator();			 
			 while(ite.hasNext() && !trovato)
			 {
			  voTmp = new VOPon();
			  voTmp = (VOPon)ite.next();
			  if(Integer.parseInt(voTmp.getAnno())==i)
			  {
			   trovato = true;
			   voNew = new VOPon();
			   voNew = voTmp;
			  }	
			 }	
			 if(!trovato)
			 {
			  voNew = new VOPon();
			  voNew.setAnno(String.valueOf(i));
			 }
			 listaNew.add(voNew);			 
			}
						
			log.debug("FINE getListaDatiSintesiPon2");
			
			return listaNew;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaDatiSintesiPon2");
			
		}
	}	
	
	
	@SuppressWarnings("rawtypes")
	public List<VOPon> getListaDatiSintesiPon3(String scuola) 
	throws Exception {
		
		log.debug("INIZIO getListaDatiSintesiPon3");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOPon> listaTmp = new ArrayList<VOPon>();
		List<VOPon> listaNew = new ArrayList<VOPon>();
		try {
			StringBuffer query = new StringBuffer("SELECT t3.anno_riferimento as annorif,count(t1.cod_pia_naz) as NUMPROGETTI,t1.cod_fon, t1.cod_Sta_ric, SUM(t1.imp_ric_aut) as impaut ")
                                                  .append(" FROM MFG1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3 ")
                                                  .append(" where t1. cod_fon= '02' ")
                                                  .append(" and t1.cod_sta_ric in (5, 7, 13) ")
                                                  .append(" and t1.cod_pia=t2.cod_pia ")
                                                  .append(" and t1.cod_fon=t2.cod_fon ")
                                                  .append(" and t2.COD_SCU_UT = ? ")
                                                  .append(" and t2.cod_sta_pia in (5, 7) ")
                                                  .append(" and t2.cod_tip_ffi= '0' ")
                                                  .append(" and t3.idbando=t2.num_ban ")
                                                  .append(" group by t1.cod_fon, t1.cod_Sta_ric, t3.anno_riferimento ")
                                                  .append(" order by t3.anno_riferimento");

			
			Object[] campi = new Object[]{scuola};
			int[] tipi = new int[]{Types.VARCHAR};
			
			log.debug("getListaDatiSintesiPon3 query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon voTmp = null;
			VOPon voNew = null;
		
			while (rs.next()) {
													
				voTmp = new VOPon();
				voTmp.setAnno(rs.getString(1));
//				voTmp.setNumProgettiFsePon(rs.getString(2));
//				voTmp.setImpFinFsePon(rs.getString(5));
				voTmp.setNumProgettiFesrPon(rs.getString(2));
				voTmp.setImpFinFesrPon(rs.getString(5));
			    listaTmp.add(voTmp);
			}
			
			
			for(int i=2007;i<=2015;i++)
			{
			 boolean trovato = false;	
			 Iterator ite = listaTmp.iterator();			 
			 while(ite.hasNext() && !trovato)
			 {
			  voTmp = new VOPon();
			  voTmp = (VOPon)ite.next();
			  if(Integer.parseInt(voTmp.getAnno())==i)
			  {
			   trovato = true;
			   voNew = new VOPon();
			   voNew = voTmp;
			  }	
			 }	
			 if(!trovato)
			 {
			  voNew = new VOPon();
			  voNew.setAnno(String.valueOf(i));
			 }
			 listaNew.add(voNew);			 
			}
						
			log.debug("FINE getListaDatiSintesiPon3");
			
			return listaNew;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaDatiSintesiPon3");
			
		}
	}					
	
	@SuppressWarnings("rawtypes")
	public List<VOPon> getListaDatiSintesiPon4(String scuola) 
	throws Exception {
		
		log.debug("INIZIO getListaDatiSintesiPon4");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOPon> listaTmp = new ArrayList<VOPon>();
		List<VOPon> listaNew = new ArrayList<VOPon>();
		try {
			StringBuffer query = new StringBuffer("SELECT t3.anno_riferimento as annorif,count(t1.cod_pia_naz) as NUMPROGETTI,t1.cod_fon, t1.cod_Sta_ric, SUM(t1.imp_ric_aut) as impaut ")
                                                  .append(" FROM MFG1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3 ")
                                                  .append(" where t1. cod_fon= '02' ")
                                                  .append(" and t1.cod_sta_ric in (5, 7, 13) ")
                                                  .append(" and t1.cod_pia=t2.cod_pia ")
                                                  .append(" and t1.cod_fon=t2.cod_fon ")
                                                  .append(" and t2.COD_SCU_UT = ? ")
                                                  .append(" and t2.cod_sta_pia in (5, 7) ")
                                                  .append(" and t2.cod_tip_ffi= '1' ")
                                                  .append(" and t3.idbando=t2.num_ban ")
                                                  .append(" group by t1.cod_fon, t1.cod_Sta_ric, t3.anno_riferimento ")
                                                  .append(" order by t3.anno_riferimento");

			
			Object[] campi = new Object[]{scuola};
			int[] tipi = new int[]{Types.VARCHAR};
			
			log.debug("getListaDatiSintesiPon4 query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon voTmp = null;
			VOPon voNew = null;
		
			while (rs.next()) {
													
				voTmp = new VOPon();
				voTmp.setAnno(rs.getString(1));
//				voTmp.setNumProgettiFsePor(rs.getString(2));
//				voTmp.setImpFinFsePor(rs.getString(5));
				voTmp.setNumProgettiFesrPor(rs.getString(2));
				voTmp.setImpFinFesrPor(rs.getString(5));
			    listaTmp.add(voTmp);
			}
			
			
			for(int i=2007;i<=2015;i++)
			{
			 boolean trovato = false;	
			 Iterator ite = listaTmp.iterator();			 
			 while(ite.hasNext() && !trovato)
			 {
			  voTmp = new VOPon();
			  voTmp = (VOPon)ite.next();
			  if(Integer.parseInt(voTmp.getAnno())==i)
			  {
			   trovato = true;
			   voNew = new VOPon();
			   voNew = voTmp;
			  }	
			 }	
			 if(!trovato)
			 {
			  voNew = new VOPon();
			  voNew.setAnno(String.valueOf(i));
			 }
			 listaNew.add(voNew);			 
			}
						
			log.debug("FINE getListaDatiSintesiPon4");
			
			return listaNew;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaDatiSintesiPon4");
			
		}
	}		
	
	public List<VOPon> getListaDatiDettFse(String scuola) 
	throws Exception {
		
		log.debug("INIZIO getListaDatiDettFse");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOPon> listaTmp = new ArrayList<VOPon>();
		try {
			StringBuffer query = new StringBuffer("SELECT  count(t1.cod_ric) as MODULI, SUM(t1.imp_ric_aut) as importo, ")
			.append("(SELECT count(t4.codice_fiscale) as formati ")
			.append("FROM MFG1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3,MFG1065_ANAGSOGGFORM t4 ")
			.append("where t1. cod_fon= '01' ")
			.append("and t1.cod_sta_ric in (5, 7, 13) ")
			.append("and t1.cod_pia=t2.cod_pia ")
			.append("and t1.cod_fon=t2.cod_fon ")
			.append("and t2.COD_SCU_UT = ? ")
			.append("and t2.cod_sta_pia in (5, 7) ")
			.append("and t2.cod_tip_ffi in ('0','1') ")
			.append("and t3.idbando=t2.num_ban ")
			.append("and t4.codice_progetto = t1.cod_pia_naz ")
			.append("and t4.id_richiesta = t1.cod_ric ")
			.append("and t4.flg_tip_per is not null) as formati, ")
			.append("(SELECT count(t4.codice_fiscale) as formati ")
			 .append("FROM MFG1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3,MFG1065_ANAGSOGGFORM t4 ")
			.append("where t1. cod_fon= '01' ")
			.append("and t1.cod_sta_ric in (5, 7, 13) ")
			.append("and t1.cod_pia=t2.cod_pia ")
			.append("and t1.cod_fon=t2.cod_fon ")
			.append("and t2.COD_SCU_UT = ? ")
			.append("and t2.cod_sta_pia in (5, 7) ")
			.append("and t2.cod_tip_ffi in ('0','1') ")
			.append("and t3.idbando=t2.num_ban ")
			.append("and t4.codice_progetto = t1.cod_pia_naz ")
			.append("and t4.id_richiesta = t1.cod_ric ")
			.append("and t4.flg_tip_per is not null and t4.flg_tip_per = '03') as genitori, ")
			.append("(SELECT count(t4.codice_fiscale) as formati ")
			 .append("FROM MFG1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3,MFG1065_ANAGSOGGFORM t4 ")
			.append("where t1. cod_fon= '01' ")
			.append("and t1.cod_sta_ric in (5, 7, 13) ")
			.append("and t1.cod_pia=t2.cod_pia ")
			.append("and t1.cod_fon=t2.cod_fon ")
			.append("and t2.COD_SCU_UT = ? ")
			.append("and t2.cod_sta_pia in (5, 7) ")
			.append("and t2.cod_tip_ffi in ('0','1') ")
			.append("and t3.idbando=t2.num_ban ")
			.append("and t4.codice_progetto = t1.cod_pia_naz ")
			.append("and t4.id_richiesta = t1.cod_ric ")
			.append("and t4.flg_tip_per is not null and t4.flg_tip_per = '02') as personale, ")
			.append("(SELECT count(t4.codice_fiscale) as formati ")
			 .append("FROM MFG1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3,MFG1065_ANAGSOGGFORM t4 ")
			.append("where t1. cod_fon= '01' ")
			.append("and t1.cod_sta_ric in (5, 7, 13) ")
			.append("and t1.cod_pia=t2.cod_pia ")
			.append("and t1.cod_fon=t2.cod_fon ")
			.append("and t2.COD_SCU_UT = ? ")
			.append("and t2.cod_sta_pia in (5, 7) ")
			.append("and t2.cod_tip_ffi in ('0','1') ")
			.append("and t3.idbando=t2.num_ban ")
			.append("and t4.codice_progetto = t1.cod_pia_naz ")
			.append("and t4.id_richiesta = t1.cod_ric ")
			.append("and t4.flg_tip_per is not null and t4.flg_tip_per = '01') as alunni, ")
			.append("(SELECT SUM(to_number(DECODE(NVL(ore_didattica,0),' ',0,ore_didattica))) ")
			 .append("FROM MFG1004_richiesta t1, mfg1003_piano t2, mfgavviso_bando t3,mfgpiano_richiesta t4 ")
			.append("where t1. cod_fon= '01' ")
			.append("and t1.cod_sta_ric in (5, 7, 13) ")
			.append("and t1.cod_pia=t2.cod_pia ")
			.append("and t1.cod_fon=t2.cod_fon ")
			.append("and t2.COD_SCU_UT = ? ")
			.append("and t2.cod_sta_pia in (5, 7) ")
			.append("and t2.cod_tip_ffi in ('0','1') ")
			.append("and t3.idbando=t2.num_ban ")
			.append("and t4.idrichiesta = t1.cod_ric) as oredidattica, ")
			.append("(SELECT count(*) as attestati ")
			 .append("FROM MFG1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3,MFG1065_ANAGSOGGFORM t4 ")
			.append("where t1. cod_fon= '01' ")
			.append("and t1.cod_sta_ric in (5, 7, 13) ")
			.append("and t1.cod_pia=t2.cod_pia ")
			.append("and t1.cod_fon=t2.cod_fon ")
			.append("and t2.COD_SCU_UT = ? ")
			.append("and t2.cod_sta_pia in (5, 7) ")
			.append("and t2.cod_tip_ffi in ('0','1') ")
			.append("and t3.idbando=t2.num_ban ")
			.append("and t4.codice_progetto = t1.cod_pia_naz ")
			.append("and t4.id_richiesta = t1.cod_ric ")
			.append("and t4.flg_tip_per is not null ")
			.append("and flg_att_con = 01) as attestati ")
			.append(" FROM mfg1004_richiesta t1, mfg1003_piano t2, MFGAVVISO_BANDO t3 ")
			.append("where t1. cod_fon= '01' ")
			.append("and t1.cod_sta_ric in (5, 7, 13) ")
			.append("and t1.cod_pia=t2.cod_pia ")
			.append("and t1.cod_fon=t2.cod_fon ")
			.append("and t2.COD_SCU_UT = ? ")
			.append("and t2.cod_sta_pia in (5, 7) ")
			.append("and t2.cod_tip_ffi in ('0','1') ")
			.append("and t3.idbando=t2.num_ban ")
			.append("group by t1.cod_fon ");

			
			Object[] campi = new Object[]{scuola,scuola,scuola,scuola,scuola,scuola,scuola};
			int[] tipi = new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			
			log.debug("getListaDatiDettFse query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon voTmp = null;
		
			while (rs.next()) {													
				voTmp = new VOPon();
				voTmp.setTotIntReal(rs.getString(1));
				voTmp.setImpTotAut(rs.getString(2));
				voTmp.setNumTotDestForm(rs.getString(3));
				
				try{
				BigDecimal bd1g = new BigDecimal(rs.getString(4));
				BigDecimal bd2g = new BigDecimal(voTmp.getNumTotDestForm());				
				BigDecimal bd3g = bd1g.multiply(new BigDecimal(100));		
				BigDecimal bd4g = bd3g.divide(bd2g,2,RoundingMode.HALF_UP);
				voTmp.setGen(bd4g.toString());								
				}catch(Exception ex){voTmp.setGen("0");}
				
				try{
				BigDecimal bd1p = new BigDecimal(rs.getString(5));
				BigDecimal bd2p = new BigDecimal(voTmp.getNumTotDestForm());				
				BigDecimal bd3p = bd1p.multiply(new BigDecimal(100));		
				BigDecimal bd4p = bd3p.divide(bd2p,2,RoundingMode.HALF_UP);
				voTmp.setPers(bd4p.toString());				
				}catch(Exception ex){voTmp.setPers("0");}
				
				try{
				BigDecimal bd1a = new BigDecimal(rs.getString(6));
				BigDecimal bd2a = new BigDecimal(voTmp.getNumTotDestForm());				
				BigDecimal bd3a = bd1a.multiply(new BigDecimal(100));		
				BigDecimal bd4a = bd3a.divide(bd2a,2,RoundingMode.HALF_UP);
				voTmp.setAln(bd4a.toString());		
				}catch(Exception ex){voTmp.setAln("0");}
				
				try{
				BigDecimal bd1Ore = new BigDecimal(rs.getString(7));
				BigDecimal bd2Ore = new BigDecimal(voTmp.getTotIntReal());					
				BigDecimal bd3Ore = bd1Ore.divide(bd2Ore,2,RoundingMode.HALF_UP);									
				voTmp.setNumMedOreFormEff(bd3Ore.toString());
				}catch(Exception ex){voTmp.setNumMedOreFormEff("0");}
				
				try{
				BigDecimal bd1Att = new BigDecimal(rs.getString(8));
				BigDecimal bd2Att = new BigDecimal(voTmp.getTotIntReal());					
				BigDecimal bd3Att = bd1Att.divide(bd2Att,2,RoundingMode.HALF_UP);									
				voTmp.setNumMedCertAttCons(bd3Att.toString());		
				}catch(Exception ex){voTmp.setNumMedCertAttCons("0");}				
				
			    listaTmp.add(voTmp);
			}
			
			
			
			return listaTmp;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaDatiDettFse");
			
		}
	}	
	
	public List<VOPon> getListaDatiDettFesr(String scuola) 
	throws Exception {
		
		log.debug("INIZIO getListaDatiDettFesr");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOPon> listaTmp = new ArrayList<VOPon>();
		try {
			StringBuffer query = new StringBuffer(" SELECT 'ASSE1',count(t1.cod_pia_naz),SUM(t1.imp_ric_aut) ")
			.append(" FROM MFG1004_richiesta t1, MFG1003_PIANO t2, MFGAVVISO_BANDO t3,MFGPIANO_PROGETTO t4,MFGOBIETTIVO t5 ")
			.append(" where t1. cod_fon= '02' ")
			.append(" and t1.cod_sta_ric in (5, 7, 13) ")
			.append(" and t1.cod_pia=t2.cod_pia ")
			.append(" and t1.cod_fon=t2.cod_fon ")
			.append(" and t2.COD_SCU_UT = ? ")
			.append(" and t2.cod_sta_pia in (5, 7) ")
			.append(" and t2.cod_tip_ffi in ('0','1') ")
			.append(" and t3.idbando=t2.num_ban ")
			.append(" and t1.cod_pia_naz = t4.cod_progetto_nazionale ")
			.append(" and t4.idobiettivo = t5.idobiettivo ")
			.append(" and t5.gruppo = t1.cod_fon ")
			.append(" and t5.asse = '01' ")
			.append(" union ")
			.append(" SELECT 'ASSE2',count(t1.cod_pia_naz),SUM(t1.imp_ric_aut) ")
			.append(" FROM MFG1004_richiesta t1, MFG1003_PIANO t2, MFGAVVISO_BANDO t3,MFGPIANO_PROGETTO t4,MFGOBIETTIVO t5 ")
			.append(" where t1. cod_fon= '02' ")
			.append(" and t1.cod_sta_ric in (5, 7, 13) ")
			.append(" and t1.cod_pia=t2.cod_pia ")
			.append(" and t1.cod_fon=t2.cod_fon ")
			.append(" and t2.COD_SCU_UT = ? ")
			.append(" and t2.cod_sta_pia in (5, 7) ")
			.append(" and t2.cod_tip_ffi in ('0','1') ")
			.append(" and t3.idbando=t2.num_ban ")
			.append(" and t1.cod_pia_naz = t4.cod_progetto_nazionale ")
			.append(" and t4.idobiettivo = t5.idobiettivo ")
			.append(" and t5.gruppo = t1.cod_fon ")
			.append(" and t5.asse = '02' ")
			.append(" union ")
			.append(" SELECT 'ASSE3',count(t1.cod_pia_naz),SUM(t1.imp_ric_aut) ")
			.append("  FROM MFG1004_richiesta t1, MFG1003_PIANO t2, MFGAVVISO_BANDO t3,MFGPIANO_PROGETTO t4,MFGOBIETTIVO t5 ")
			.append(" where t1. cod_fon= '02' ")
			.append(" and t1.cod_sta_ric in (5, 7, 13) ")
			.append(" and t1.cod_pia=t2.cod_pia ")
			.append(" and t1.cod_fon=t2.cod_fon ")
			.append(" and t2.COD_SCU_UT = ? ")
			.append(" and t2.cod_sta_pia in (5, 7) ")
			.append(" and t2.cod_tip_ffi in ('0','1') ")
			.append(" and t3.idbando=t2.num_ban ")
			.append(" and t1.cod_pia_naz = t4.cod_progetto_nazionale ")
			.append(" and t4.idobiettivo = t5.idobiettivo ")
			.append(" and t5.gruppo = t1.cod_fon ")
			.append(" and t5.asse = '03' ");

			
			Object[] campi = new Object[]{scuola,scuola,scuola};
			int[] tipi = new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			
			log.debug("getListaDatiDettFesr query: " + query.toString());
			
			conn = this.getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon voTmp = null;
		
			while (rs.next()) {													
				voTmp = new VOPon();
				voTmp.setTipInt(rs.getString(1));
				voTmp.setNumProg(rs.getString(2));
				voTmp.setImpTotAut(rs.getString(3));							
				
			    listaTmp.add(voTmp);
			}
			
			
			
			return listaTmp;
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
			
			log.debug("FINALLY getListaDatiDettFesr");
			
		}
	}	

	public String getDirigenteScolastico(String codScuUt, String datAnnScoRil) throws Exception
	{
		log.debug("INIZIO getDirigenteScolastico("+codScuUt+","+datAnnScoRil+")");
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String out = "";
		try{
			String query = " SELECT DISTINCT A.COMP_COGN, A.COMP_NOME, A.COD_FISC, A.DAT_INI_INC, A.DAT_FIN_INC, A.DAT_FIN_ATT, A.COD_TIP_INC, A.DES_TIP_INC "
					+ " from MFG1001_ASSRESISTSCOL A, MFG1002_ANAGISTSCOL B "
					+ " where "
					+ " A.COD_FOR_SCU_APP = B.COD_FOR_SCU_APP  "
					+ " and (A.dat_fin_inc > TO_number(TO_char(sysdate,'YYYYMMDD'),'99999999') and A.dat_fin_att > TO_number(TO_char(sysdate,'YYYYMMDD'),'99999999')) "
					+ " and A.dat_ini_inc < TO_number(TO_char(sysdate,'YYYYMMDD'),'99999999') "
					+ " and A.cod_tip_inc in ('04','06','07') "
					+ " and A.cod_for_scu_app = (SELECT COD_FOR_SCU_APP FROM MFG1002_ANAGISTSCOL "
					+ " WHERE (COD_SCU_UT,DAT_ANN_SCO_RIL) = ( "
					+ " SELECT COD_SCU_UT_PRI,DAT_ANN_SCO_RIL FROM MFG1002_ANAGISTSCOL "
					+ " WHERE COD_SCU_UT = '" + codScuUt + "' "
					+ " AND DAT_ANN_SCO_RIL = " + datAnnScoRil + " )) "
					+ " ORDER BY A.cod_tip_inc DESC ";
			
			log.debug("query="+query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()){
				out =rs.getString("COMP_NOME") + " " + rs.getString("COMP_COGN");
			}
			log.debug("out="+out);
		}
		catch (Exception e)
		{
			log.error("ERRORE : getDirigenteScolastico ", e);
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
		return out;
	}
	
	public boolean provinciaAderisceIscrizioniOnLineCFP(String codPrv, String datAnnScoRil) throws Exception
	{
		log.debug("INIZIO aderisceIscrizioniOnLine("+codPrv+","+datAnnScoRil+")");
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean esito = false;
		try{
			String query = " SELECT A.* FROM MFG1049_ADEREGIOL A, MFG1013_PROVINCIA B "
					+ " WHERE "
					+ " B.COD_PRV = '"+codPrv+"' "
					+ " AND TRIM(B.COD_REG) = TRIM(A.COD_REG) "
					+ " AND A. FLG_ADE_IOL = 'S' "
					+ " AND A. FLG_CON_ANA_CFP = 'S' "
					+ " AND A.DAT_ANN_SCO_RIF = " + datAnnScoRil;
			
			log.debug("query="+query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()){
				esito = true;
			}
			log.debug("esito="+esito);
		}
		catch (Exception e)
		{
			log.error("ERRORE : aderisceIscrizioniOnLine ", e);
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
		return esito;
	}

	public boolean scuolaNonStataleAderisceIscrizioniOnLine(String codScuUt, String datAnnScoRil) throws Exception
	{
		log.debug("INIZIO scuolaNonStataleAderisceIscrizioniOnLineCFP("+codScuUt+","+datAnnScoRil+")");
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean esito = false;
		try{
			String query = " SELECT * FROM MFG1020_PUBBMODISC "
					+ " WHERE "
					+ " COD_SCU_UT = '"+codScuUt+"' "
					+ " AND DAT_ANN_SCO_RIL = " + datAnnScoRil
					+ " AND DAT_PUB_MOD IS NOT NULL "
					+ " AND FLG_PUB = 'S' ";
			
			log.debug("query="+query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()){
				esito = true;
			}
			log.debug("esito="+esito);
		}
		catch (Exception e)
		{
			log.error("ERRORE : scuolaNonStataleAderisceIscrizioniOnLineCFP ", e);
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
		return esito;
	}

	@Override
	public List<VOTipologia> getListaIndirizzoEsame(String codiceScuolaUtente, Integer periodoAnnoScolastico) throws Exception {
		log.debug("INIZIO getListaCodiceIndirizzoEsame");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOTipologia> result = new ArrayList<VOTipologia>();

		try {
			if (StringUtils.isEmpty(codiceScuolaUtente)) {
				throw new Exception("I parametri passati sono null");
			}

		
			StringBuilder query = new StringBuilder("select mfg1003.COD_IND, mfg1003.DES_EST_IND from MFG1003_SCUOLAINDESAME mfg1003 ")
				.append("where mfg1003.COD_FORTE = ? "); 
				
			if (periodoAnnoScolastico!=null){
				query.append("and mfg1003.ANNO_ESAME_CLA = ? ");
			}
			
			query.append("order by mfg1003.COD_IND");
			
			conn = this.getConnection();

			ps = conn.prepareStatement(query.toString());

			Object[] campi;
			int[] tipi;
			
			if (periodoAnnoScolastico!=null){
				campi = new Object[] { codiceScuolaUtente, periodoAnnoScolastico };
				tipi = new int[] { Types.VARCHAR, Types.NUMERIC };
			} else {
				campi = new Object[] { codiceScuolaUtente};
				tipi = new int[] { Types.VARCHAR};
			}

			rs = eseguiPreparedQuery(ps, campi, tipi, false);

			log.debug("getListaCodiceIndirizzoEsame query: " + query);

			while (rs.next()) {
				VOTipologia vo = new VOTipologia();

				vo.setCodice(rs.getString("COD_IND"));
				vo.setDescrizione(rs.getString("DES_EST_IND"));
				
				result.add(vo);
			}
			
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}

		log.debug("FINE getListaCodiceIndirizzoEsame");

		return result;
	}
	
	@Override
	public List<VOTipologia> getListaIndirizzoEsame(String codiceScuolaUtente) throws Exception {
		log.debug("INIZIO getListaCodiceIndirizzoEsame");

		List<VOTipologia> result = this.getListaIndirizzoEsame(codiceScuolaUtente, null);
		
		log.debug("FINE getListaCodiceIndirizzoEsame");

		return result;
	}	
}
