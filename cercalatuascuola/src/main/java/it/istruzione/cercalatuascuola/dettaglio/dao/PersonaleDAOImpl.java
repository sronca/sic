package it.istruzione.cercalatuascuola.dettaglio.dao;


import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.*;

import org.apache.log4j.Logger;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PersonaleDAOImpl extends BaseDAO implements PersonaleDAO {
	private Logger logger = Logger.getLogger(PersonaleDAOImpl.class);

	public PersonaleDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	public VOTrasferimento getIndicatoriPersonaleIstogramma1(String codForScuApp) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VOTrasferimento voTrasf = null;
		
		try {
			StringBuffer query = new StringBuffer("SELECT NVL(PER_TRA, 0) PER_TRA, NVL(PER_TRA_REG, 0) PER_TRA_REG,") 
				.append("NVL(PER_TRA_NAZ, 0) PER_TRA_NAZ ")             
				.append("FROM TRS1023_SIDIMOBI ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1023_SIDIMOBI ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?))");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
									
			rs = pstmt.executeQuery();
			
			logger.debug("getIndicatoriPersonaleIstogramma1 query: " + query);
			
			if(rs.next()) {
				voTrasf = new VOTrasferimento();
				
				voTrasf.setPerTraScu(rs.getBigDecimal("PER_TRA").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voTrasf.setPerTraReg(rs.getBigDecimal("PER_TRA_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voTrasf.setPerTraNaz(rs.getBigDecimal("PER_TRA_NAZ").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());						
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
		
		return voTrasf;
	}
	
	public VOPensione getIndicatoriPersonaleIstogramma2(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VOPensione voPens = null;
		
		try {
			StringBuffer query = new StringBuffer("SELECT NVL(PER_CES, 0) PER_CES, NVL(PER_CES_REG, 0) PER_CES_REG,")
			.append("NVL(PER_CES_NAZ, 0) PER_CES_NAZ ")             
			.append("FROM TRS1029_CESSAZIONE ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?) ")
			.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
			.append("FROM TRS1029_CESSAZIONE ")
			.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
			.append("FROM MFG1002_ANAGISTSCOL ")
			.append("WHERE COD_FOR_SCU_APP = ?))");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
						
			rs = pstmt.executeQuery();
			
			logger.debug("getIndicatoriPersonaleIstogramma2: " + query);
			
			if(rs.next()) {
				voPens = new VOPensione();
								
				voPens.setPerCes(rs.getBigDecimal("PER_CES").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voPens.setPerCesReg(rs.getBigDecimal("PER_CES_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voPens.setPerCesNaz(rs.getBigDecimal("PER_CES_NAZ").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
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
		
		return voPens;
	}
	
	public List<VOAssenza> getAssenzeDocenti(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAssenza> result = new ArrayList<VOAssenza>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT DES_TIP_PER, initcap(DES_TIP_ASS) DES_TIP_ASS, NVL(NUM_GIO_ASS, 0) NUM_GIO_ASS,")
				.append("NVL(NUM_GIO_ASS_PRO, 0) NUM_GIO_ASS_PRO, NVL(NUM_GIO_ASS_NAZ, 0) NUM_GIO_ASS_NAZ ")
				.append("FROM TRS1024_RILASS ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT_PRI ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1024_RILASS ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT_PRI ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ? ")
				.append("AND UPPER(DES_TIP_PER) = 'DOC')) ")
				.append("AND UPPER(DES_TIP_PER) = 'DOC' ")
				.append("ORDER BY decode(initcap(des_tip_ass), 'Malattia', 1, 'Maternità', 2, 'Altri', 3, 2) ");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("query getAssenzeDocenti: " + query);
			
			while(rs.next()) {
				VOAssenza voAssenza = new VOAssenza();
				voAssenza.setTipoPersonale(rs.getString("DES_TIP_PER"));
				voAssenza.setTipologiaAssenza(rs.getString("DES_TIP_ASS"));
				voAssenza.setGiorniAssenza(rs.getInt("NUM_GIO_ASS"));
				voAssenza.setGiorniAssenzaProv(rs.getInt("NUM_GIO_ASS_PRO"));
				voAssenza.setGiorniAssenzaNaz(rs.getInt("NUM_GIO_ASS_NAZ"));

				result.add(voAssenza);
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
		return result;
	}
	
	public List<VOAssenza> getAssenzeATA(String codForScuApp) 
	throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAssenza> result = new ArrayList<VOAssenza>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT DES_TIP_PER, initcap(DES_TIP_ASS) as DES_TIP_ASS, NVL(NUM_GIO_ASS, 0) NUM_GIO_ASS,") 
				.append("NVL(NUM_GIO_ASS_PRO, 0) NUM_GIO_ASS_PRO, NVL(NUM_GIO_ASS_NAZ, 0) NUM_GIO_ASS_NAZ ")
				.append("FROM TRS1024_RILASS ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT_PRI ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1024_RILASS ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT_PRI ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ? ")
				.append("AND UPPER(DES_TIP_PER) = 'ATA')) ")
				.append("AND UPPER(DES_TIP_PER) = 'ATA' ")
				.append("ORDER BY decode(initcap(des_tip_ass), 'Malattia', 1, 'Maternità', 2, 'Altri', 3, 2) ");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("query: " + query);
			
			while(rs.next()) {
				VOAssenza voAssenza = new VOAssenza();
				voAssenza.setTipoPersonale(rs.getString("DES_TIP_PER"));
				voAssenza.setTipologiaAssenza(rs.getString("DES_TIP_ASS"));
				voAssenza.setGiorniAssenza(rs.getInt("NUM_GIO_ASS"));
				voAssenza.setGiorniAssenzaProv(rs.getInt("NUM_GIO_ASS_PRO"));
				voAssenza.setGiorniAssenzaNaz(rs.getInt("NUM_GIO_ASS_NAZ"));
				
				result.add(voAssenza);
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
		return result;
	}
	
	public String getTipologieMalattia(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		
		try {
			StringBuffer query = new StringBuffer("SELECT DISTINCT DES_TIP_ASS ")
				.append("FROM TRS1024_RILASS ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT_PRI ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1024_RILASS ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT_PRI ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?))")
				.append("ORDER BY 1");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("query: " + query);
			int cont = 0;
			
			while(rs.next()) {
				
				if(cont == 0){
					result += rs.getString("DES_TIP_ASS");
				}else{
					result += ","+rs.getString("DES_TIP_ASS");
				}
				
				cont++;
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
		return result;
	}
	
	public Integer getNumTotDocenti(String codForScuApp) throws Exception {
		Connection conn = null;
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
			
			logger.debug("getNumTotDocenti query: " + query);
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("TOT_DOC");
			}
			
			return result;
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
	}

	public List<VOFascia> getIndicatoriIstogrammaFascia(String codForScuApp, Integer totDoc) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOFascia> result = new ArrayList<VOFascia>();
		
		try {
			/*
			String query = "SELECT TRIM(COD_FAS_ETA) AS COD_FAS_ETA, (NVL(NUM_DOC_RUO_MAS, 0) + NVL(NUM_DOC_RUO_FEM, 0)) AS RUOLO," +
		                   " (NVL(NUM_DOC_NRU_MAS, 0) + NVL(NUM_DOC_NRU_FEM, 0)) AS NO_RUOLO "+
						   " FROM TRS1022_CONANNDOC "+  
						   " WHERE COD_SCU_UT = ? "+
						   " AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) " +
						   " FROM TRS1022_CONANNDOC " +
						   " WHERE COD_SCU_UT = ?) " +
						   " ORDER BY 1";
			*/			
			
			StringBuffer query = new StringBuffer("SELECT TRIM(COD_FAS_ETA) AS COD_FAS_ETA,")
				.append("((NVL(NUM_DOC_RUO_MAS, 0) + NVL(NUM_DOC_RUO_FEM, 0))/?)*100 AS RUOLO,") 
				.append("((NVL(NUM_DOC_NRU_MAS, 0) + NVL(NUM_DOC_NRU_FEM, 0))/?)*100 AS NO_RUOLO ")
				.append("FROM TRS1022_CONANNDOC ")  
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1022_CONANNDOC ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")
				.append("ORDER BY 1");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, totDoc);
			pstmt.setInt(2, totDoc);
			pstmt.setString(3, codForScuApp);
			pstmt.setString(4, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("query: " + query);
			
			while(rs.next()) {
				VOFascia voFascia = new VOFascia();
								
				if("3".equals(rs.getString("COD_FAS_ETA"))){
					voFascia.setDescrizione("<35");	
				}else if("4".equals(rs.getString("COD_FAS_ETA"))){
					voFascia.setDescrizione("35-44");	
				}else if("5".equals(rs.getString("COD_FAS_ETA"))){
					voFascia.setDescrizione("45-54");	
				}else if("6".equals(rs.getString("COD_FAS_ETA"))){
					voFascia.setDescrizione("55+");	
				}
				
				voFascia.setRuolo(rs.getBigDecimal("RUOLO").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voFascia.setNoRuolo(rs.getBigDecimal("NO_RUOLO").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				
				result.add(voFascia);
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
		return result;
	}
	
	public VOPersonale getIndicatoriATATabelle(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			StringBuffer query = new StringBuffer(" SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, ")
				.append(" NVL(NUM_ATA_MAS, 0) NUM_ATA_MAS, NVL(NUM_ATA_FEM, 0) NUM_ATA_FEM, ")
				.append(" (NVL(NUM_ATA_MAS, 0) + NVL(NUM_ATA_FEM, 0)) TOT_ATA ")
				.append(" FROM TRS1021_FLUDATPER ")
				.append(" WHERE COD_SCU_UT IN ")
				.append(" (SELECT DISTINCT COD_SCU_UT_PRI ")
				.append(" FROM MFG1002_ANAGISTSCOL ")
				.append(" WHERE COD_FOR_SCU_APP = ? ")
				.append(" AND (DAT_FIN_VAL IS NULL OR DAT_FIN_VAL > SYSDATE) ")
				.append(" AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append(" FROM TRS1021_FLUDATPER ")
				.append(" WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT_PRI ")
				.append(" FROM MFG1002_ANAGISTSCOL ")
				.append(" WHERE COD_FOR_SCU_APP = ?))) ")
				.append(" AND DAT_ANN_SCO_RIL = ")
				.append(" (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append(" FROM TRS1021_FLUDATPER ")
				.append(" WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT_PRI ")
				.append(" FROM MFG1002_ANAGISTSCOL ")
				.append(" WHERE COD_FOR_SCU_APP = ?)) ");
							
			Object[] campi = new Object[]{codForScuApp, codForScuApp, codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			
			logger.debug("getIndicatoriATATabelle query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPersonale vo = null;
			if(rs.next()) {
				vo = new VOPersonale();
				vo.setCodScuUt(rs.getString("COD_SCU_UT"));				
				vo.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));				
				vo.setNumAtaFemmine(rs.getInt("NUM_ATA_FEM"));
				vo.setNumAtaMaschi(rs.getInt("NUM_ATA_MAS"));
				vo.setTotAta(rs.getInt("TOT_ATA"));			
			}
						
			return vo;
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
	
	public VOPersonale getIndicatoriDocentiTabelle(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			StringBuffer query = new StringBuffer("SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, (NVL(NUM_DOC_RUO_MAS, 0) + NVL(NUM_DOC_NRU_MAS, 0)) NUM_DOC_MAS,")
				.append("(NVL(NUM_DOC_RUO_FEM, 0) + NVL(NUM_DOC_NRU_FEM, 0)) NUM_DOC_FEM, (NVL(NUM_DOC_RUO_MAS, 0) + NVL(NUM_DOC_NRU_MAS, 0) + NVL(NUM_DOC_RUO_FEM, 0) + NVL(NUM_DOC_NRU_FEM, 0)) TOT_DOC,")				
				.append("(NVL(NUM_SOS_RUO_MAS, 0) + NVL(NUM_SOS_NRU_MAS, 0)) NUM_SOS_MAS, (NVL(NUM_SOS_RUO_FEM, 0) + NVL(NUM_SOS_NRU_FEM, 0)) NUM_SOS_FEM,")
				.append("(NVL(NUM_SOS_RUO_MAS, 0) + NVL(NUM_SOS_NRU_MAS, 0) + NVL(NUM_SOS_RUO_FEM, 0) + NVL(NUM_SOS_NRU_FEM, 0)) TOT_SOS,")								
				.append("NVL(NUM_DOC_RUO_MAS, 0) NUM_DOC_RUO_MAS, NVL(NUM_DOC_RUO_FEM, 0) NUM_DOC_RUO_FEM, NVL(NUM_DOC_NRU_MAS, 0) NUM_DOC_NRU_MAS, NVL(NUM_DOC_NRU_FEM, 0) NUM_DOC_NRU_FEM,")
				.append("(NVL(NUM_DOC_RUO_MAS, 0) + NVL(NUM_DOC_RUO_FEM, 0)) TOT_RUO,")
				.append("(NVL(NUM_DOC_NRU_MAS, 0) + NVL(NUM_DOC_NRU_FEM, 0)) TOT_NRU ")
				.append("FROM TRS1021_FLUDATPER ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1021_FLUDATPER ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?))");
							
			Object[] campi = new Object[]{codForScuApp, codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR, Types.VARCHAR};
			
			logger.debug("getIndicatoriDocentiTabelle query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPersonale vo = null;
			if(rs.next()) {
				vo = new VOPersonale();
				vo.setCodScuUt(rs.getString("COD_SCU_UT"));				
				vo.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));				
				vo.setNumDocFemmine(rs.getInt("NUM_DOC_FEM"));
				vo.setNumDocMaschi(rs.getInt("NUM_DOC_MAS"));
				vo.setNumNoRuoFemmine(rs.getInt("NUM_DOC_NRU_FEM"));
				vo.setNumNoRuoMaschi(rs.getInt("NUM_DOC_NRU_MAS"));
				vo.setNumRuoFemmine(rs.getInt("NUM_DOC_RUO_FEM"));
				vo.setNumRuoMaschi(rs.getInt("NUM_DOC_RUO_MAS"));
				vo.setNumSosFemmine(rs.getInt("NUM_SOS_FEM"));
				vo.setNumSosMaschi(rs.getInt("NUM_SOS_MAS"));				
				vo.setTotDoc(rs.getInt("TOT_DOC"));
				vo.setTotNoRuo(rs.getInt("TOT_NRU"));
				vo.setTotRuo(rs.getInt("TOT_RUO"));
				vo.setTotSos(rs.getInt("TOT_SOS"));				
			}
						
			return vo;
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
	
	private String dat_ann_sco_ril_format(String dataIn){
		return dataIn.substring(0, 4)+"/"+dataIn.substring(4);
	}
	
}
