package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAttivitaServizio;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAttrezzatureMultimediali;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOServizio;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipologiaServizio;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiziDAOImpl extends BaseDAO implements ServiziDAO {
	Logger log = Logger.getLogger(ServiziDAOImpl.class);
	
	public ServiziDAOImpl(DataSource dataSource) throws SQLException, NamingException {
		super(dataSource);
	}
	
	public List<VOTipologiaServizio> getServiziAttivi(String codScuUt, String datAnnScoRil)
	throws Exception {
		
		Connection conn = null;
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
				.append("ORDER BY TS.NUM_ORD_VIS, S.NUM_ORD_VIS, APS.NUM_ORD_VIS ");
			
			Object[] campi = new Object[]{codScuUt, datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			
			log.debug("getServiziAttivi query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOTipologiaServizio voTipSer = null;
			VOServizio voSer = null;
			VOAttivitaServizio voAttSer = null;
			while (rs.next()) {
				
				if(!Constants.HtmlInput.INPUT_RADIO.toString().equals(rs.getString("COD_TIP_CAM")) ||
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	public List<VOTipologiaServizio> getServiziAttiviPerTipologia(String codTipSer, String codScuUt, String datAnnScoRil) 
	throws Exception {
		
		Connection conn = null;
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
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOTipologiaServizio voTipSer = null;
			VOServizio voSer = null;
			VOAttivitaServizio voAttSer = null;
			while (rs.next()) {
				if(!Constants.HtmlInput.INPUT_RADIO.toString().equals(rs.getString("COD_TIP_CAM")) ||
						!"NO".equalsIgnoreCase(rs.getString("DES_VAL"))) {
					log.debug("getServiziAttiviPerTipologia **** : " + rs.getString("COD_SER"));				
					log.debug("getServiziAttiviPerTipologia **** : " + rs.getString("COD_ATT_SER"));
					log.debug("getServiziAttiviPerTipologia **** : " + rs.getString("DES_ATT_SER"));				
					if(voTipSer == null || !voTipSer.getCodTipSer().equals(rs.getString("COD_TIP_SER"))) {
						log.debug("getServiziAttiviPerTipologia 1 **** : " + rs.getString("COD_SER"));				
						log.debug("getServiziAttiviPerTipologia 1 **** : " + rs.getString("COD_ATT_SER"));
						log.debug("getServiziAttiviPerTipologia 1 **** : " + rs.getString("DES_ATT_SER"));						
						voTipSer = new VOTipologiaServizio();
						voTipSer.setCodTipSer(rs.getString("COD_TIP_SER"));
						voTipSer.setDesTipSer(rs.getString("DES_TIP_SER"));
						
						voSer = new VOServizio();
						voSer.setCodSer(rs.getString("COD_SER"));
						voSer.setDesSer(rs.getString("DES_SER"));					
											
						voAttSer = new VOAttivitaServizio();
						//log.debug("getServiziAttiviPerTipologia dentro while cod_att_ser 1 : " + rs.getString("COD_ATT_SER"));						
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
					boolean serv = true;				
					if(voSer == null || !voSer.getCodSer().equals(rs.getString("COD_SER"))) {
						log.debug("getServiziAttiviPerTipologia 2 **** : " + rs.getString("COD_SER"));				
						log.debug("getServiziAttiviPerTipologia 2 **** : " + rs.getString("COD_ATT_SER"));
						log.debug("getServiziAttiviPerTipologia 2 **** : " + rs.getString("DES_ATT_SER"));						
						voSer = new VOServizio();
						voSer.setCodSer(rs.getString("COD_SER"));
						voSer.setDesSer(rs.getString("DES_SER"));
						
						voTipSer.getServizi().add(voSer);
						serv = false;
					}	
														
					if( voAttSer == null || !voAttSer.getCodAttSer().equals(rs.getString("COD_ATT_SER"))) {
						log.debug("getServiziAttiviPerTipologia 3 **** : " + rs.getString("COD_SER"));				
						log.debug("getServiziAttiviPerTipologia 3 **** : " + rs.getString("COD_ATT_SER"));
						log.debug("getServiziAttiviPerTipologia 3 **** : " + rs.getString("DES_ATT_SER"));						
						voAttSer = new VOAttivitaServizio();
						//log.debug("getServiziAttiviPerTipologia dentro while cod_att_ser 2 : " + rs.getString("COD_ATT_SER"));						
						voAttSer.setCodAttSer(rs.getString("COD_ATT_SER"));						
						voAttSer.setCodTipCam(rs.getString("COD_TIP_CAM"));
						voAttSer.setDesAttSer(rs.getString("DES_ATT_SER"));
						voAttSer.setDesFonPro(rs.getString("DES_FON_PRO"));
						voAttSer.setDesNot(rs.getString("DES_NOT"));			
						voAttSer.setDesVal(rs.getString("DES_VAL"));
						
						voSer.getAttivitaServizio().add(voAttSer);
					}else if(!serv) {
						log.debug("getServiziAttiviPerTipologia 3 **** : " + rs.getString("COD_SER"));				
						log.debug("getServiziAttiviPerTipologia 3 **** : " + rs.getString("COD_ATT_SER"));
						log.debug("getServiziAttiviPerTipologia 3 **** : " + rs.getString("DES_ATT_SER"));						
						voAttSer = new VOAttivitaServizio();
						//log.debug("getServiziAttiviPerTipologia dentro while cod_att_ser 2 : " + rs.getString("COD_ATT_SER"));						
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	public VOAttrezzatureMultimediali getAttrezzatureMultimediali(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
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
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	public Integer getNumeroAlunni(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
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
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	public String getVisibilitaGrafici(String codGra) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String out = "N";
		
		try {
			String sql = "SELECT COD_STA_PUB "+ 
						  " FROM TRS1035_GRAFICOUFFICIO "+ 
						 " WHERE COD_SCH = 4 "+ 
						   " AND COD_SEZ = 6 "+ 
						   " AND COD_GRA = ? ";
							
			Object[] campi = new Object[]{new Integer(codGra)};
			int[] tipi = new int[]{Types.INTEGER};
			
			log.debug("getNumeroAlunni query: " + sql);
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			if(rs.next()) {
				
				out = rs.getString(1);
				
			}
						
			return out;
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
	
	public String getTitoloGrafici(String codGra) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String out = "";
		
		try {
			String sql = "SELECT DES_TIT_GRA "+ 
						  " FROM TRS1033_TIPOGRAFICO "+ 
						 " WHERE COD_SCH = 4 "+ 
						   " AND COD_SEZ = 6 "+ 
						   " AND COD_GRA = ? ";
							
			Object[] campi = new Object[]{new Integer(codGra)};
			int[] tipi = new int[]{Types.INTEGER};
			
			log.debug("getNumeroAlunni query: " + sql);
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			if(rs.next()) {
				
				out = rs.getString(1);
				
			}
						
			return out;
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
	
	public String getDescrizioneGrafici(String codGra) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String out = "";
		
		try {
			String sql = "SELECT DES_INF_GRA "+ 
						  " FROM TRS1033_TIPOGRAFICO "+ 
						 " WHERE COD_SCH = 4 "+ 
						   " AND COD_SEZ = 6 "+ 
						   " AND COD_GRA = ? ";
							
			Object[] campi = new Object[]{new Integer(codGra)};
			int[] tipi = new int[]{Types.INTEGER};
			
			log.debug("getNumeroAlunni query: " + sql);
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			if(rs.next()) {
				
				out = rs.getString(1);
				
			}
						
			return out;
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
	
	public VOAttrezzatureMultimediali getAttrezzatureMultimedialiNewPlesso(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			StringBuffer query = new StringBuffer("SELECT des_nov_uno,des_nov_due,des_und_uno,des_dod_uno,des_trd_uno,des_trd_due ")
				.append("FROM TRS1050_RILATTMUL ")
				.append("WHERE COD_SCU_PLE IN (SELECT DISTINCT COD_SCU_UT ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ? ) ")
				.append("ORDER BY DAT_ANN_SCO_RIL DESC");
							
			Object[] campi = new Object[]{codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR};
			
			log.debug("getAttrezzatureMultimedialiNew query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);			
			VOAttrezzatureMultimediali voAttr = null;
			log.debug("prima di if");			
			if(rs.next()) {
				log.debug("dentro if");				
				voAttr = new VOAttrezzatureMultimediali();
				voAttr.setCompScu(rs.getString("des_und_uno"));
				voAttr.setDispMobScu(rs.getString("des_dod_uno"));
				voAttr.setLimScu(rs.getString("des_trd_uno"));
				voAttr.setProiettScu(rs.getString("des_trd_due"));	
				voAttr.setNumAuleDid(rs.getString("des_nov_uno"));
				log.debug("dentro if 2");	
				try
				{
				 BigDecimal bd1 = new BigDecimal(rs.getString("des_nov_uno"));
				 BigDecimal bd2 = new BigDecimal(rs.getString("des_nov_due"));
				 log.debug("dentro if 3");				
				 BigDecimal bd3 = bd2.multiply(new BigDecimal(100));		
				 BigDecimal bd4 = bd3.divide(bd1,2,RoundingMode.HALF_UP);
				 voAttr.setPercAuleConn(bd4.toString());				
				}catch(Exception ex)
				{
				 voAttr.setPercAuleConn("0");										
				}
								
			}else{voAttr = new VOAttrezzatureMultimediali();}
			log.debug("dopo if");						
			log.debug("voAttr --> "+voAttr);
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}	
	
	//public VOAttrezzatureMultimediali getAttrezzatureMultimedialiNew(String codForScuApp, String anno)
	public VOAttrezzatureMultimediali getAttrezzatureMultimedialiNew(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer query = new StringBuffer("SELECT des_cin_uno,des_cin_due,des_sei_uno,des_sei_due,des_sei_tre,des_sei_qua ")
				.append("FROM TRS1050_RILATTMUL ")
				.append("where cod_scu_ut in (select distinct cod_scu_ut FROM TRS1050_RILATTMUL where cod_scu_ple in (SELECT DISTINCT COD_SCU_UT ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")
				.append("and cod_scu_ut = cod_scu_ple ORDER BY DAT_ANN_SCO_RIL DESC");
							
			Object[] campi = new Object[]{codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR};
			
			log.debug("getAttrezzatureMultimedialiNew query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);			
			VOAttrezzatureMultimediali voAttr = null;
			log.debug("prima di if");			
			if(rs.next()) {
				log.debug("dentro if");				
				voAttr = new VOAttrezzatureMultimediali();
				voAttr.setCompLab(getTrattino(rs.getString("des_cin_uno")));
				voAttr.setCompBib(getTrattino(rs.getString("des_cin_due")));
				voAttr.setLimLab(getTrattino(rs.getString("des_sei_uno")));
				voAttr.setLimBib(getTrattino(rs.getString("des_sei_tre")));
				voAttr.setProiettLab(getTrattino(rs.getString("des_sei_due")));
				voAttr.setProiettBib(getTrattino(rs.getString("des_sei_qua")));
				
				VOAttrezzatureMultimediali voAttrPle = getAttrezzatureMultimedialiNewPlesso(codForScuApp);
				
				voAttr.setCompScu(getTrattino(voAttrPle.getCompScu()));
				voAttr.setDispMobScu(getTrattino(voAttrPle.getDispMobScu()));
				voAttr.setLimScu(getTrattino(voAttrPle.getLimScu()));
				voAttr.setProiettScu(getTrattino(voAttrPle.getProiettScu()));	
				voAttr.setNumAuleDid(getTrattino(voAttrPle.getNumAuleDid()));
				log.debug("dentro if 2");			
				voAttr.setPercAuleConn(getTrattino(voAttrPle.getPercAuleConn()));										
								
			}//else{voAttr = new VOAttrezzatureMultimediali();}
			log.debug("dopo if");						
			log.debug("voAttr --> "+voAttr);
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}		
	
	public String getTrattino(String val) throws Exception {
		if(val==null || val.equalsIgnoreCase(""))
		 return "-";
		else
		 return val;			
	}
	
}
