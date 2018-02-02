package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAlternanzaScuolaLavoro;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAlunniImm;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAlunniImmAD;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAndamento;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnniCorsoAlunni;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOClasse;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOEsameAlunni;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOEsameVotazione;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLavoro1;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLavoro2;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLavoro3;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLavoro4;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOPercorsiAttivati;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VORav24C5;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VORav24b1;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VORav24c1;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VORav24c2;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOStruttureOspitanti;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class AlunniDAOImpl extends BaseDAO implements AlunniDAO {
	
	private Logger logger = Logger.getLogger(AlunniDAOImpl.class);

    public AlunniDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<VOClasse> getDistribuzioneClassiIndirizzi(String codForScuApp)
	throws Exception {
		
		logger.debug("INIZIO getDistribuzioneClassiIndirizzi");
		logger.debug("codForScuApp="+codForScuApp);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOClasse> result = new ArrayList<VOClasse>();
		
		try {
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
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getDistribuzioneClassiIndirizzi query: " + query);
			
			while(rs.next()) {
				VOClasse voClasse = new VOClasse();
				voClasse.setDescrizione(rs.getString("DES_IND"));
				voClasse.setNumeroClassi(rs.getString("NUM_CLASSI"));
				result.add(voClasse);
				logger.debug(rs.getString("DES_IND"));
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
		logger.debug("FINE getDistribuzioneClassiIndirizzi");
		return result;
	}
		
	public List<VOAndamento> getAlunniIscrittiUltimiDueAnni(String codForScuApp) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAndamento> result = new ArrayList<VOAndamento>();
		
		try {
			/*
			StringBuffer query = new StringBuffer("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL, NVL(NUM_ALU, 0) NUM_ALU ")
				.append("FROM TRS1027_RILEINTE ")
				.append("WHERE COD_SCU_UT = ? ")
				.append("ORDER BY 1, 2");
			*/
			
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
				.append("ORDER BY DAT_ANN_SCO_RIL, PER_ANN_COR");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			pstmt.setString(3, codForScuApp);
			pstmt.setString(4, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getAlunniIscrittiUltimiDueAnni query: " + query);
			
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
	
	public List<VOAndamento> getAlunniRipetenti(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAndamento> result = new ArrayList<VOAndamento>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL,")
				.append("DECODE(NVL(NUM_ALU, 0), 0, 0, (NVL(NUM_ALU_RIP, 0)/NUM_ALU)*100) AS RIPETENTI_PLESSO,")
				.append("DECODE(NVL(NUM_TOT_ALU_PRO, 0), 0, 0, (NVL(NUM_ALU_RIP_PRO, 0)/NUM_TOT_ALU_PRO)*100) AS RIPETENTI_PROVINCIA,")
				.append("DECODE(NVL(NUM_ALU_NAZ, 0), 0, 0, (NVL(NUM_ALU_RIP_NAZ, 0)/NUM_ALU_NAZ)*100) AS RIPETENTI_NAZIONE ")
				.append("FROM TRS1027_RILEINTE ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1027_RILEINTE ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")
				.append("ORDER BY 1");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getAlunniRipetenti query: " + query);
			
			while(rs.next()) {
				VOAndamento voAndamento = new VOAndamento();
				voAndamento.setAnnoCorso(rs.getString("PER_ANN_COR"));
				voAndamento.setAnnoScolastico(dat_ann_sco_ril_format(rs.getString("DAT_ANN_SCO_RIL")));
				
				voAndamento.setPercentualeRipetentiPlesso(rs.getBigDecimal("RIPETENTI_PLESSO").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeRipetentiProv(rs.getBigDecimal("RIPETENTI_PROVINCIA").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeRipetentiNaz(rs.getBigDecimal("RIPETENTI_NAZIONE").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
			
				result.add(voAndamento);
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
	
	public List<VOAndamento> getAlunniIngressoUscita(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAndamento> result = new ArrayList<VOAndamento>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL,")
				.append("DECODE(NVL(NUM_ALU_TOT, 0), 0, 0, (NVL(NUM_ALU_ENT, 0)/NUM_ALU_TOT)*100) AS PERC_ENTR,")
				.append("DECODE(NVL(NUM_ALU_TOT, 0), 0, 0, (NVL(NUM_ALU_USC, 0)/NUM_ALU_TOT)*100) AS PERC_USC ")
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
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getAlunniIngressoUscita query: " + query);
			
			while(rs.next()) {
				VOAndamento voAndamento = new VOAndamento();
				voAndamento.setAnnoCorso(rs.getString("PER_ANN_COR"));
				voAndamento.setAnnoScolastico(dat_ann_sco_ril_format(rs.getString("DAT_ANN_SCO_RIL")));
				
				voAndamento.setPercentualeEntrate(rs.getBigDecimal("PERC_ENTR").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeUscite(rs.getBigDecimal("PERC_USC").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
			
				result.add(voAndamento);
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
	
	public List<VOAndamento> getIndicatoriAlunniIstogramma4(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAndamento> result = new ArrayList<VOAndamento>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL,")
				.append("DECODE(NVL(NUM_ALU_TOT, 0), 0, 0, (NVL(NUM_ALU_ABB, 0)/NUM_ALU_TOT)*100) AS NUM_ALUNNI_ABBANDONATO,")
				.append("DECODE(NVL(NUM_ALU_PRO, 0), 0, 0, (NVL(NUM_ALU_ABB_PRO, 0)/NUM_ALU_PRO)*100) AS NUM_ALUNNI_ABBANDONATO_PROV,")
				.append("DECODE(NVL(NUM_ALU_NAZ, 0), 0, 0, (NVL(NUM_ALU_ABB_NAZ, 0)/NUM_ALU_NAZ)*100) AS NUM_ALUNNI_ABBANDONATO_NAZ ")
				.append("FROM TRS1018_RILEVESITI ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?"
						+ " AND dat_ann_sco_ril = ( "
						+ " SELECT MAX (dat_ann_sco_ril) FROM trs1018_rilevesiti "
						+ " WHERE cod_scu_ut IN (SELECT DISTINCT cod_scu_ut FROM mfg1002_anagistscol "
						+ " WHERE cod_for_scu_app = ?))" 
						+ ") ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1018_RILEVESITI ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")
				.append("ORDER BY 1");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			pstmt.setString(3, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getIndicatoriAlunniIstogramma4 query: " + query);
			
			while(rs.next()) {
				VOAndamento voAndamento = new VOAndamento();
				voAndamento.setAnnoCorso(rs.getString("PER_ANN_COR"));
				voAndamento.setAnnoScolastico(dat_ann_sco_ril_format(rs.getString("DAT_ANN_SCO_RIL")));
				
				voAndamento.setPercentualeAbbandonoPlesso(rs.getBigDecimal("NUM_ALUNNI_ABBANDONATO").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeAbbandonoProv(rs.getBigDecimal("NUM_ALUNNI_ABBANDONATO_PROV").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeAbbandonoNaz(rs.getBigDecimal("NUM_ALUNNI_ABBANDONATO_NAZ").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
			
				result.add(voAndamento);
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
	
	public VOAndamento getEsitiScrutini(String codForScuApp, boolean $secondariaSecondoGrado) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VOAndamento voAndamento = null;
		try {
			/*
			String query = " SELECT SUM(NVL(NUM_ALU_AMM, 0)) AS ALU_AMM, " +
						   " SUM(NVL(NUM_ALU_NON_AMM, 0)) AS ALU_NON_AMM, " +
						   " SUM(NVL(NUM_ALU_SOS, 0)) AS ALU_SOS, " +
						   " SUM(NVL(NUM_ALU_AMM_SET, 0)) AS ALU_AMM_SET, " +
						   " SUM(NVL(NUM_ALU_NON_AMM_SET, 0)) AS ALU_NON_AMM_SET " +
						   " FROM TRS1018_RILEVESITI " +
						   " WHERE COD_SCU_UT = ? " +
						   " AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) " +
						   " FROM TRS1018_RILEVESITI " +
						   " WHERE COD_SCU_UT = ?) " + 
						   " ORDER BY 1 ";
			*/			   
			/*
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
			*/
			
			/*
			
			StringBuffer query = new StringBuffer("select  avg(NUM_ALUNNI_AMMESSI)      as ALU_AMM , ") 
					.append(" avg(NUM_ALUNNI_NON_AMMESSI)  as ALU_NON_AMM, ")
					.append(" avg(NUM_ALUNNI_SOSPESI)      as ALU_SOS, ")
			        .append(" avg(NUM_ALUNNI_AMMESSI_SET)  as ALU_AMM_SET, ")
			        .append(" avg(NUM_ALUNNI_NON_AMMESSI_SET)  as ALU_NON_AMM_SET ")
			        .append(" from (   ")
			        .append(" SELECT PER_ANN_COR, DAT_ANN_SCO_RIL, ") 
			        .append(" DECODE(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0)     + NVL(NUM_ALU_SOS, 0), 0, 0, ") 
			        .append(" (NVL(NUM_ALU_AMM, 0)/(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)))*100) ") 
			        .append(" AS NUM_ALUNNI_AMMESSI, ")
			        .append(" DECODE(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0)     + NVL(NUM_ALU_SOS, 0), 0, 0, ") 
			        .append(" (NVL(NUM_ALU_NON_AMM, 0)/(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)))*100) ") 
			        .append(" AS NUM_ALUNNI_NON_AMMESSI, ")
			        .append(" DECODE(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0)     + NVL(NUM_ALU_SOS, 0), 0, 0, ") 
			        .append(" (NVL(NUM_ALU_SOS, 0)/(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)))*100) ") 
			        .append(" AS NUM_ALUNNI_SOSPESI, ")
			        .append(" DECODE(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM_SET, 0), 0, 0, ") 
			        .append(" ( (NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0))        /    (NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_NON_AMM_SET, 0))   )*100)  AS NUM_ALUNNI_AMMESSI_SET, ")
			        .append(" DECODE(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM_SET, 0), 0, 0,  ")
			        .append(" ( (NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_NON_AMM_SET, 0))        /    (NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_NON_AMM_SET, 0))   )*100)  AS NUM_ALUNNI_NON_AMMESSI_SET ")
			        .append(" FROM TRS1018_RILEVESITI ")  
			        .append(" WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")   
			        .append(" FROM MFG1002_ANAGISTSCOL   ")
			        .append(" WHERE COD_FOR_SCU_APP = ?) ")  
			        .append(" AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")  
			        .append(" FROM TRS1018_RILEVESITI ")  
					.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ")   
					.append("FROM MFG1002_ANAGISTSCOL   ")
					.append("WHERE COD_FOR_SCU_APP = ?)) ")  
					.append("ORDER BY 1 ")
					.append(") ");
			*/
			StringBuffer query = new StringBuffer();
			if($secondariaSecondoGrado){
				query.append("SELECT ");
				query.append("DECODE((ammessi_totale+non_ammessi_totale+sospesi_totale), 0, 0, (ammessi_totale*100)/(ammessi_totale + non_ammessi_totale + sospesi_totale) ) AS ALU_AMM, ");
				query.append("DECODE((ammessi_totale+non_ammessi_totale+sospesi_totale), 0, 0, (non_ammessi_totale*100)/(ammessi_totale + non_ammessi_totale + sospesi_totale) ) AS ALU_NON_AMM, ");
				query.append("DECODE((ammessi_totale+non_ammessi_totale+sospesi_totale), 0, 0, (sospesi_totale*100)/(ammessi_totale + non_ammessi_totale + sospesi_totale) )     AS ALU_SOS, ");
				query.append("DECODE((ammessi_totale + ammessi_totale_settembre + non_ammessi_totale + non_ammessi_totale_settembre), 0, 0, (ammessi_totale + ammessi_totale_settembre)*100/(ammessi_totale + ammessi_totale_settembre + non_ammessi_totale + non_ammessi_totale_settembre) ) AS ALU_AMM_SET, ");
				query.append("DECODE((ammessi_totale + ammessi_totale_settembre + non_ammessi_totale + non_ammessi_totale_settembre), 0, 0, (non_ammessi_totale + non_ammessi_totale_settembre)*100/(ammessi_totale + ammessi_totale_settembre + non_ammessi_totale + non_ammessi_totale_settembre) )  AS ALU_NON_AMM_SET ");
				query.append("from ( "); 
				query.append("SELECT ");
				query.append("DAT_ANN_SCO_RIL, ");
				query.append("sum( NVL(NUM_ALU_AMM, 0) ) as ammessi_totale, ");
				query.append("sum( NVL(NUM_ALU_NON_AMM, 0) ) as non_ammessi_totale, ");
				query.append("sum( NVL(NUM_ALU_SOS, 0) ) as sospesi_totale, ");
				query.append("sum( NVL(NUM_ALU_AMM_SET, 0) ) as ammessi_totale_settembre, ");
				query.append("sum( NVL(NUM_ALU_NON_AMM_SET, 0) ) as non_ammessi_totale_settembre ");
				query.append("FROM TRS1018_RILEVESITI ");  
				query.append("WHERE COD_SCU_UT IN ( ");
				query.append("SELECT DISTINCT COD_SCU_UT ");    
				query.append("FROM MFG1002_ANAGISTSCOL ");   
				query.append(" WHERE COD_FOR_SCU_APP = ?) ");
				query.append("AND PER_ANN_COR IN (1,2,3,4) ");
				query.append("AND DAT_ANN_SCO_RIL = ( ");
				query.append("SELECT ");
				query.append("MAX(DAT_ANN_SCO_RIL) ");  
				query.append("FROM TRS1018_RILEVESITI ");  
				query.append("WHERE COD_SCU_UT IN ( ");
				query.append("SELECT DISTINCT COD_SCU_UT ");   
				query.append("FROM MFG1002_ANAGISTSCOL "); 
				query.append("WHERE COD_FOR_SCU_APP = ?)) ");
				query.append("group by DAT_ANN_SCO_RIL ");
				query.append(") ");
			}
			else{
				query.append("select ");  
				query.append("DECODE((ammessi_totale+non_ammessi_totale), 0, 0, (ammessi_totale*100)/(ammessi_totale + non_ammessi_totale) ) AS ALU_AMM, "); 
				query.append("DECODE((ammessi_totale+non_ammessi_totale), 0, 0, (non_ammessi_totale*100)/(ammessi_totale + non_ammessi_totale) ) AS ALU_NON_AMM, "); 
				query.append("0 AS ALU_SOS, ");
				query.append("0 AS ALU_AMM_SET, "); 
				query.append("0 AS ALU_NON_AMM_SET "); 
				query.append("from ( ");  
				query.append("SELECT "); 
				query.append("DAT_ANN_SCO_RIL, "); 
				query.append("sum( NVL(NUM_ALU_AMM, 0) ) as ammessi_totale, ");
				query.append("sum( NVL(NUM_ALU_NON_AMM, 0) ) as non_ammessi_totale, ");
				query.append("sum( NVL(NUM_ALU_SOS, 0) ) as sospesi_totale, ");
				query.append("sum( NVL(NUM_ALU_AMM_SET, 0) ) as ammessi_totale_settembre, ");
				query.append("sum( NVL(NUM_ALU_NON_AMM_SET, 0) ) as non_ammessi_totale_settembre ");
				query.append("FROM TRS1018_RILEVESITI ");   
				query.append("WHERE COD_SCU_UT IN ( ");
				query.append("SELECT DISTINCT COD_SCU_UT ");    
				query.append("FROM MFG1002_ANAGISTSCOL ");   
				query.append(" WHERE COD_FOR_SCU_APP = ?) ");
				query.append("AND PER_ANN_COR IN (1,2) ");
				query.append("AND DAT_ANN_SCO_RIL = ( ");
				query.append("SELECT "); 
				query.append("MAX(DAT_ANN_SCO_RIL) ");   
				query.append("FROM TRS1018_RILEVESITI ");   
				query.append("WHERE COD_SCU_UT IN ( ");
				query.append("SELECT DISTINCT COD_SCU_UT ");    
				query.append("FROM MFG1002_ANAGISTSCOL ");   
				query.append("WHERE COD_FOR_SCU_APP = ?)) ");  
				query.append("group by DAT_ANN_SCO_RIL ");
				query.append(") ");
			}	
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			rs = pstmt.executeQuery();
			logger.debug("getEsitiScrutini query: " + query);
			if(rs.next()) {
				voAndamento = new VOAndamento();
				voAndamento.setAlunniAmmessi(rs.getBigDecimal("ALU_AMM").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setAlunniNonAmmessi(rs.getBigDecimal("ALU_NON_AMM").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setAlunniSospesi(rs.getBigDecimal("ALU_SOS").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setAlunniAmmessiSett(rs.getBigDecimal("ALU_AMM_SET").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setAlunniNonAmmessiSett(rs.getBigDecimal("ALU_NON_AMM_SET").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());				
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
	
	public List<VOAndamento> getIndicatoriAlunniIstogramma1_Risultati(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAndamento> result = new ArrayList<VOAndamento>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL, ")
				.append("DECODE(NVL((NUM_ALU_AMM + NUM_ALU_NON_AMM + NUM_ALU_SOS), 0), 0, 0, (NVL(NUM_ALU_SOS, 0)/(NUM_ALU_AMM + NUM_ALU_NON_AMM + NUM_ALU_SOS))*100) AS NUM_ALUNNI_SOSP, ")
				.append("DECODE(NVL((NUM_TOT_AMM_PRO + NUM_NON_AMM_PRO + NUM_ALU_SOS_PRO), 0), 0, 0, (NVL(NUM_ALU_SOS_PRO, 0)/(NUM_TOT_AMM_PRO + NUM_NON_AMM_PRO + NUM_ALU_SOS_PRO))*100) AS NUM_ALUNNI_SOSP_PROV, ")
				.append("DECODE(NVL((NUM_TOT_AMM_NAZ + NUM_NON_AMM_NAZ  + NUM_ALU_SOS_NAZ ), 0), 0, 0, (NVL(NUM_ALU_SOS_NAZ, 0)/(NUM_TOT_AMM_NAZ + NUM_NON_AMM_NAZ  + NUM_ALU_SOS_NAZ ))*100) AS NUM_ALUNNI_SOSP_NAZ ") 
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
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getIndicatoriAlunniIstogramma1_Risultati query: " + query);
			
			while(rs.next()) {
				VOAndamento voAndamento = new VOAndamento();
				voAndamento.setAnnoCorso(rs.getString("PER_ANN_COR"));
				voAndamento.setAnnoScolastico(dat_ann_sco_ril_format(rs.getString("DAT_ANN_SCO_RIL")));
				
				voAndamento.setPercentualeSospesiPlesso(rs.getFloat("NUM_ALUNNI_SOSP"));
				voAndamento.setPercentualeSospesiProv(rs.getFloat("NUM_ALUNNI_SOSP_PROV"));
				voAndamento.setPercentualeSospesiNaz(rs.getFloat("NUM_ALUNNI_SOSP_NAZ"));
			
				result.add(voAndamento);
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
	
	public List<VOEsameAlunni> getIndicatoriAlunniTorta2(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOEsameAlunni> result = new ArrayList<VOEsameAlunni>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT TRIM(COD_FAS_VOT) COD_FAS_VOT, (NUM_ALU_VOT_FAS / (SELECT SUM(RA.NUM_ALU_VOT_FAS) ")
				.append("FROM TRS1019_RILESASTAALU RA ")
                .append("WHERE RA.COD_SCU_UT = R2.COD_SCU_UT ")
                .append("AND DAT_ANN_SCO_RIL = R2.DAT_ANN_SCO_RIL) * 100) AS PERCENTUALE_ALUNNI ")
				.append("FROM TRS1019_RILESASTAALU R2 ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1019_RILESASTAALU ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")
				.append("ORDER BY TO_NUMBER(TRIM(COD_FAS_VOT))");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getIndicatoriAlunniTorta2 query: " + query);
			
			while(rs.next()) {
				VOEsameAlunni voClasse = new VOEsameAlunni();
				voClasse.setCodiceFasciaVoto(rs.getString("COD_FAS_VOT"));
				
				BigDecimal percAlunni = rs.getBigDecimal("PERCENTUALE_ALUNNI").setScale(1, BigDecimal.ROUND_HALF_DOWN);
				
				voClasse.setNumeroAlunni(percAlunni+"");
				
				result.add(voClasse);
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
	
	public List<VOAndamento> getAmmessiGiugno(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAndamento> result = new ArrayList<VOAndamento>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL,")
				.append("DECODE(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0), 0, 0, (NVL(NUM_ALU_AMM, 0)/(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_SOS, 0)))*100) AS NUM_ALUNNI_AMMESSO,")
				.append("DECODE(NVL(NUM_TOT_AMM_PRO, 0) + NVL(NUM_NON_AMM_PRO, 0) + NVL(NUM_ALU_SOS_PRO, 0), 0, 0, (NVL(NUM_TOT_AMM_PRO, 0)/(NVL(NUM_TOT_AMM_PRO, 0) + NVL(NUM_NON_AMM_PRO, 0) + NVL(NUM_ALU_SOS_PRO, 0)))*100) AS NUM_ALUNNI_AMMESSO_PROV,")
				.append("DECODE(NVL(NUM_TOT_AMM_NAZ, 0) + NVL(NUM_NON_AMM_NAZ, 0) + NVL(NUM_ALU_SOS_NAZ, 0), 0, 0, (NVL(NUM_TOT_AMM_NAZ, 0)/(NVL(NUM_TOT_AMM_NAZ, 0) + NVL(NUM_NON_AMM_NAZ, 0) + NVL(NUM_ALU_SOS_NAZ, 0)))*100) AS NUM_ALUNNI_AMMESSO_NAZ ")
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
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getAmmessiGiugno query: " + query);
			
			while(rs.next()) {
				VOAndamento voAndamento = new VOAndamento();
				voAndamento.setAnnoCorso(rs.getString("PER_ANN_COR"));
				voAndamento.setAnnoScolastico(dat_ann_sco_ril_format(rs.getString("DAT_ANN_SCO_RIL")));
				
				voAndamento.setPercentualeAmmessoPlesso(rs.getBigDecimal("NUM_ALUNNI_AMMESSO").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeAmmessoProv(rs.getBigDecimal("NUM_ALUNNI_AMMESSO_PROV").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeAmmessoNaz(rs.getBigDecimal("NUM_ALUNNI_AMMESSO_NAZ").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
			
				result.add(voAndamento);
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
	
	public List<VOAndamento> getAmmessiSettembre(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAndamento> result = new ArrayList<VOAndamento>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT PER_ANN_COR, DAT_ANN_SCO_RIL,")
				//.append("DECODE(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM_SET, 0), 0, 0, (NVL(NUM_ALU_AMM, 0)/(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM_SET, 0)))*100) AS NUM_ALUNNI_AMMESSO,")
				//.append("DECODE(NVL(NUM_TOT_AMM_PRO, 0) + NVL(NUM_NON_AMM_PRO, 0) + NVL(NUM_AMM_SET_PRO, 0) + NVL(NUM_NON_SET_PRO, 0), 0, 0, (NVL(NUM_TOT_AMM_PRO, 0)/(NVL(NUM_TOT_AMM_PRO, 0) + NVL(NUM_NON_AMM_PRO, 0) + NVL(NUM_AMM_SET_PRO, 0) + NVL(NUM_NON_SET_PRO, 0)))*100) AS NUM_ALUNNI_AMMESSO_PROV,")
				//.append("DECODE(NVL(NUM_TOT_AMM_NAZ, 0) + NVL(NUM_NON_AMM_NAZ, 0) + NVL(NUM_AMM_SET_NAZ, 0) + NVL(NUM_NON_SETT_NAZ, 0), 0, 0, (NVL(NUM_TOT_AMM_NAZ, 0)/(NVL(NUM_TOT_AMM_NAZ, 0) + NVL(NUM_NON_AMM_NAZ, 0) + NVL(NUM_AMM_SET_NAZ, 0) + NVL(NUM_NON_SETT_NAZ, 0)))*100) AS NUM_ALUNNI_AMMESSO_NAZ ")
				.append("DECODE(NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM_SET, 0), 0, 0,") 
				.append("( (NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0))        /    (NVL(NUM_ALU_AMM, 0) + NVL(NUM_ALU_AMM_SET, 0) + NVL(NUM_ALU_NON_AMM, 0) + NVL(NUM_ALU_NON_AMM_SET, 0))   )*100)  AS NUM_ALUNNI_AMMESSO,")  
				.append("DECODE(NVL(NUM_TOT_AMM_PRO, 0) + NVL(NUM_NON_AMM_PRO, 0) + NVL(NUM_AMM_SET_PRO, 0) + NVL(NUM_NON_SET_PRO, 0), 0, 0,")
				.append("( (NVL(NUM_TOT_AMM_PRO, 0) + NVL(NUM_AMM_SET_PRO,0))     /    (NVL(NUM_TOT_AMM_PRO, 0) + NVL(NUM_NON_AMM_PRO, 0) + NVL(NUM_AMM_SET_PRO, 0) + NVL(NUM_NON_SET_PRO, 0))   )*100)  AS NUM_ALUNNI_AMMESSO_PROV,") 
				.append("DECODE(NVL(NUM_TOT_AMM_NAZ, 0) + NVL(NUM_NON_AMM_NAZ, 0) + NVL(NUM_AMM_SET_NAZ, 0) + NVL(NUM_NON_SETT_NAZ, 0), 0, 0,")
				.append("( ( NVL(NUM_TOT_AMM_NAZ, 0) + NVL(NUM_AMM_SET_NAZ, 0) )  /    (NVL(NUM_TOT_AMM_NAZ, 0) + NVL(NUM_NON_AMM_NAZ, 0) + NVL(NUM_AMM_SET_NAZ, 0) + NVL(NUM_NON_SETT_NAZ, 0))  )*100)  AS NUM_ALUNNI_AMMESSO_NAZ, ")  
				.append("NUM_ALU_SOS - (NUM_ALU_AMM_SET + NUM_ALU_NON_AMM_SET) FLG_VIS ")
				.append(" FROM TRS1018_RILEVESITI ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1018_RILEVESITI ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")
				.append("ORDER BY 1");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getAmmessiSettembre query: " + query);
			
			boolean datoBuono = true;
			while(rs.next()) {
				VOAndamento voAndamento = new VOAndamento();
				voAndamento.setAnnoCorso(rs.getString("PER_ANN_COR"));
				voAndamento.setAnnoScolastico(dat_ann_sco_ril_format(rs.getString("DAT_ANN_SCO_RIL")));
				
				voAndamento.setPercentualeAmmessoPlesso(rs.getBigDecimal("NUM_ALUNNI_AMMESSO").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeAmmessoProv(rs.getBigDecimal("NUM_ALUNNI_AMMESSO_PROV").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeAmmessoNaz(rs.getBigDecimal("NUM_ALUNNI_AMMESSO_NAZ").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
							
				result.add(voAndamento);
				
				if (rs.getInt("FLG_VIS") != 0) {
					datoBuono = false;
				}
			}
			
			if (!datoBuono){
				result = new ArrayList<VOAndamento>();
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
	
	public VOAndamento getIndicatoriAlunniIstogramma3_Risultati(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VOAndamento voAndamento = null;
		try {
			StringBuffer query = new StringBuffer("SELECT DECODE(NVL(NUM_ALU_ESA, 0), 0, 0, (NVL(NUM_ALU_DIP, 0)/NUM_ALU_ESA)*100) AS NUM_ALUNNI_PROMOSSI_PLESSO,")
				.append("DECODE(NVL(NUM_ALU_ESA_PRO, 0), 0, 0, (NVL(NUM_ALU_DIP_PRO, 0)/NUM_ALU_ESA_PRO)*100) AS NUM_ALUNNI_PROMOSSI_PROV,")
				.append("DECODE(NVL(NUM_ALU_ESA_NAZ, 0), 0, 0, (NVL(NUM_ALU_DIP_NAZ, 0)/NUM_ALU_ESA_NAZ)*100) AS NUM_ALUNNI_PROMOSSI_NAZ ")
				.append("FROM TRS1028_RILEESADIPLO ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1028_RILEESADIPLO ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")
				.append("ORDER BY 1");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			logger.debug("getIndicatoriAlunniIstogramma3_Risultati query: " + query);
			
			if(rs.next()) {
				voAndamento = new VOAndamento();

				voAndamento.setPercentualeAmmessoPlesso(rs.getBigDecimal("NUM_ALUNNI_PROMOSSI_PLESSO").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeAmmessoProv(rs.getBigDecimal("NUM_ALUNNI_PROMOSSI_PROV").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				voAndamento.setPercentualeAmmessoNaz(rs.getBigDecimal("NUM_ALUNNI_PROMOSSI_NAZ").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				
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
	
	public List<VOAnniCorsoAlunni> getAlunniPerAnnoCorso(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOAnniCorsoAlunni> result = new ArrayList<VOAnniCorsoAlunni>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, PER_ANN_COR,")
				.append("NVL(NUM_ALU, 0) NUM_ALU, NVL(NUM_CLA, 0) NUM_CLA, DECODE(NVL(NUM_CLA, 0), 0, 0, ROUND((NVL(NUM_ALU, 0)/NUM_CLA), 2)) ALU_PER_CLA ")				
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
			
			logger.debug("getAlunniPerAnnoCorso query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAnniCorsoAlunni vo = null;
			while(rs.next()) {
				vo = new VOAnniCorsoAlunni();
				vo.setCodScuUt(rs.getString("COD_SCU_UT"));				
				vo.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				vo.setPerAnnCor(rs.getString("PER_ANN_COR"));
				vo.setNumAlu(rs.getString("NUM_ALU"));
				vo.setNumCla(rs.getString("NUM_CLA"));
				vo.setAluPerCla(rs.getFloat("ALU_PER_CLA"));
				
				result.add(vo);
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
	
	public List<VOAnniCorsoAlunni> getAlunniPerAnnoCorsoScuoleSerali(String $codForScuApp) throws Exception {
		logger.info("getAlunniPerAnnoCorsoScuoleSerali(" + $codForScuApp + ")...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer numAluPeriodoI = 0, numAluPeriodoII = 0, numAluPeriodoIII = 0;
		String datAnnScoRil = null;
		boolean hasData = false;
		List<VOAnniCorsoAlunni> anniCorsoAlunniRaggruppati = new ArrayList<VOAnniCorsoAlunni>();
		StringBuffer strbQuery = new StringBuffer();
		try {
			strbQuery.append("SELECT");
			strbQuery.append(" COD_SCU_UT");
			strbQuery.append(",DAT_ANN_SCO_RIL");
			strbQuery.append(",NVL (SUM (CASE WHEN (PER_ANN_COR = 1 OR PER_ANN_COR = 2) THEN NUM_ALU ELSE 0 END),0) AS N_ALU_PRI_PER");
			strbQuery.append(",NVL (SUM (CASE WHEN (PER_ANN_COR = 3 OR PER_ANN_COR = 4) THEN NUM_ALU ELSE 0 END),0) AS N_ALU_SEC_PER");
			strbQuery.append(",NVL (SUM (CASE WHEN (PER_ANN_COR = 5) THEN NUM_ALU ELSE 0 END),0) AS N_ALU_TER_PER");
			strbQuery.append(" FROM TRS1016_ANANAZANNCOR");
			strbQuery.append(" WHERE COD_SCU_UT IN (");
			strbQuery.append(" SELECT DISTINCT COD_SCU_UT"); 
			strbQuery.append(" FROM MFG1002_ANAGISTSCOL");
			strbQuery.append(" WHERE COD_FOR_SCU_APP = ?");
			strbQuery.append(")");                      
			strbQuery.append(" AND DAT_ANN_SCO_RIL = (");
			strbQuery.append(" SELECT MAX(DAT_ANN_SCO_RIL)");
			strbQuery.append(" FROM TRS1016_ANANAZANNCOR");
			strbQuery.append(" WHERE COD_SCU_UT IN (");
			strbQuery.append(" SELECT DISTINCT COD_SCU_UT");
			strbQuery.append(" FROM MFG1002_ANAGISTSCOL");
			strbQuery.append(" WHERE COD_FOR_SCU_APP = ?");
			strbQuery.append(")");
			strbQuery.append(")");
			strbQuery.append("GROUP BY COD_SCU_UT, DAT_ANN_SCO_RIL");
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(strbQuery.toString());
			pstmt.setString(1, $codForScuApp);
			pstmt.setString(2, $codForScuApp);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// la query produce un solo record
				hasData = true;
				datAnnScoRil = rs.getString("DAT_ANN_SCO_RIL");
				numAluPeriodoI = rs.getInt("N_ALU_PRI_PER");
				numAluPeriodoII = rs.getInt("N_ALU_SEC_PER");
				numAluPeriodoIII = rs.getInt("N_ALU_TER_PER");
			}
			if(hasData){
				// primo periodo
				VOAnniCorsoAlunni voI = null;
				voI = new VOAnniCorsoAlunni();
				voI.setCodScuUt($codForScuApp);				
				voI.setDatAnnScoRil(datAnnScoRil);
				voI.setPerAnnCor("I Periodo");
				voI.setNumAlu(numAluPeriodoI.toString());
				anniCorsoAlunniRaggruppati.add(voI);
				// secondo periodo
				VOAnniCorsoAlunni voII = null;
				voII = new VOAnniCorsoAlunni();
				voII.setCodScuUt($codForScuApp);				
				voII.setDatAnnScoRil(datAnnScoRil);
				voII.setPerAnnCor("II Periodo");
				voII.setNumAlu(numAluPeriodoII.toString());
				anniCorsoAlunniRaggruppati.add(voII);;
				// terzo periodo
				VOAnniCorsoAlunni voIII = null;
				voIII = new VOAnniCorsoAlunni();
				voIII.setCodScuUt($codForScuApp);				
				voIII.setDatAnnScoRil(datAnnScoRil);
				voIII.setPerAnnCor("III Periodo");
				voIII.setNumAlu(numAluPeriodoIII.toString());
				anniCorsoAlunniRaggruppati.add(voIII);
			}
		}
		catch(Exception ex){
			logger.error("ERRORE getAlunniPerAnnoCorsoScuoleSerali(" + $codForScuApp + "), query: " + strbQuery.toString(), ex);
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
		return anniCorsoAlunniRaggruppati;
	}
	
	public List<VOEsameVotazione> getVotazioniProveEsami(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOEsameVotazione> result = new ArrayList<VOEsameVotazione>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT COD_SCU_UT, DAT_ANN_SCO_RIL,")
				.append("DES_PRO_ESA, NVL(NUM_VOT_MED_FAS, 0) NUM_VOT_MED_FAS ")								
				.append("FROM TRS1020_RILESASTAVOT ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")					
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1020_RILESASTAVOT ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")	
				.append("ORDER BY DES_PRO_ESA");
							
			Object[] campi = new Object[]{codForScuApp, codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR, Types.VARCHAR};
			
			logger.debug("getVotazioniProveEsami query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOEsameVotazione vo = null;
			while(rs.next()) {
				vo = new VOEsameVotazione();
				
				vo.setCodScuUt(rs.getString("COD_SCU_UT"));				
				vo.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				vo.setDesProEsa(rs.getString("DES_PRO_ESA"));
				vo.setNumVotMedFas(rs.getString("NUM_VOT_MED_FAS"));
				
				result.add(vo);
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
	
	public List<VOAlunniImmAD> getAlunniImmatricolatiPerAreaDidattica(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOAlunniImmAD> result = new ArrayList<VOAlunniImmAD>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT COD_ARE_DID, DES_ARE_DID, PRC_STU_IMM ")											
				.append("FROM TRS1037_STUDDIPLIMMATR ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")					
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1037_STUDDIPLIMMATR ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ")	
				.append("ORDER BY PRC_STU_IMM DESC");
							
			Object[] campi = new Object[]{codForScuApp, codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR, Types.VARCHAR};
			
			logger.debug("getAlunniImmatricolatiPerAreaDidattica query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAlunniImmAD vo = null;
			while(rs.next()) {
				vo = new VOAlunniImmAD();
								
				vo.setIdAreaDidattica(rs.getString("COD_ARE_DID"));
				vo.setDesAreaDidattica(rs.getString("DES_ARE_DID"));
				vo.setPercentStudenti(rs.getString("PRC_STU_IMM"));
				
				result.add(vo);
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
	
	public List<VOAlunniImm> getAlunniImmatricolati(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOAlunniImm> result = new ArrayList<VOAlunniImm>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT PRC_IMM_UNI, PRC_NIM_UNI ")											
				.append("FROM TRS1038_STUDIPLIMMUNI ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?) ")					
				.append("AND DAT_ANN_SCO_RIL = (SELECT MAX(DAT_ANN_SCO_RIL) ")
				.append("FROM TRS1038_STUDIPLIMMUNI ")
				.append("WHERE COD_SCU_UT IN (SELECT DISTINCT COD_SCU_UT ") 
				.append("FROM MFG1002_ANAGISTSCOL ")
				.append("WHERE COD_FOR_SCU_APP = ?)) ");
							
			Object[] campi = new Object[]{codForScuApp, codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR, Types.VARCHAR};
			
			logger.debug("getAlunniImmatricolati query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAlunniImm vo = null;
			while(rs.next()) {
				vo = new VOAlunniImm();
								
				vo.setPercentStudentiImm(rs.getString("PRC_IMM_UNI"));
				vo.setPercentStudentiNonImm(rs.getString("PRC_NIM_UNI"));
				
				result.add(vo);
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
	
    public List<VORav24b1> getIndicatoriRav24b1(String codScuUt, int datAnnScoRil, int annoCorso)
	throws Exception {
		
		logger.debug("INIZIO getIndicatoriRav24b1");
		logger.debug("codScuUt="+codScuUt);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VORav24b1> result = new ArrayList<VORav24b1>();
		
		try {
			StringBuffer query = new StringBuffer(" SELECT SCU.COD_FOR_SCU_APP, SCU.PER_ANN_SCO_SCU, SCU.COD_ARE_DIS, SCU.DAT_ANN_RIF_CRE, ")
				.append(" SCU.PRC_CRE_CON_MME SCU_MENO, ")
				.append(" SCU.PRC_CRE_CON_PME SCU_PIU, ")
				.append(" SCU.PRC_CRE_CON_ZER SCU_ZERO, ")
				.append(" REG.PRC_CRE_CON_MDM REG_MENO, ")
				.append(" REG.PRC_CRE_CON_PDM REG_PIU, ")
				.append(" REG.PRC_CRE_CON_ZER REG_ZERO, ")
				.append(" ITA.PRC_CRE_CON_MDM ITA_MENO, ")
				.append(" ITA.PRC_CRE_CON_PDM ITA_PIU, ")
				.append(" ITA.PRC_CRE_CON_ZER ITA_ZERO ")
				.append(" FROM MFG1002_ANAGISTSCOL ANAG,	MFG1014_COMUNE COM,	MFG1013_PROVINCIA PRO,	TRS1067_CREDITICONSEGUITI SCU,	TRS1068_BMCREDICONSE REG,	TRS1068_BMCREDICONSE ITA WHERE ")
				.append(" ANAG.COD_SCU_UT = ? ")
				.append(" AND ANAG.DAT_ANN_SCO_RIL = ? ")
				.append(" AND ANAG.COD_COM = COM.COD_COM ")
				.append(" AND COM.COD_PRV = PRO.COD_PRV ")
				.append(" AND SCU.COD_FOR_SCU_APP = ANAG.COD_FOR_SCU_APP ")
				.append(" AND SCU.DAT_ANN_RIF_CRE = ? ")
				.append(" AND REG.COD_LIV_TER = 2 ")
				.append(" AND REG.COD_RIP = TRIM(PRO.COD_REG) ")
				.append(" AND REG.PER_ANN_RIF_CRE = SCU.DAT_ANN_RIF_CRE ")
				.append(" AND REG.DES_ARE_DIS = SCU.COD_ARE_DIS ")
				.append(" AND REG.FLG_ORI_FRE = SCU.FLG_ORI_FRE ")
				.append(" AND ITA.COD_LIV_TER = 3 ")
				.append(" AND ITA.PER_ANN_RIF_CRE = SCU.DAT_ANN_RIF_CRE ")
				.append(" AND ITA.DES_ARE_DIS = SCU.COD_ARE_DIS ")
				.append(" AND ITA.FLG_ORI_FRE = SCU.FLG_ORI_FRE ")
				.append(" ORDER BY COD_ARE_DIS ");

			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codScuUt);
			pstmt.setInt(2, datAnnScoRil);
			pstmt.setInt(3, annoCorso);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				VORav24b1 voRav24b1 = new VORav24b1();
				voRav24b1.setAnnoScolastico(rs.getString("PER_ANN_SCO_SCU"));
				voRav24b1.setArea(rs.getString("COD_ARE_DIS"));
				
				voRav24b1.setScuMeno(rs.getBigDecimal("SCU_MENO").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				voRav24b1.setScuPiu(rs.getBigDecimal("SCU_PIU").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				voRav24b1.setScuZero(rs.getBigDecimal("SCU_ZERO").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				
				voRav24b1.setRegMeno(rs.getBigDecimal("REG_MENO").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				voRav24b1.setRegPiu(rs.getBigDecimal("REG_PIU").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				voRav24b1.setRegZero(rs.getBigDecimal("REG_ZERO").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				
				voRav24b1.setItaMeno(rs.getBigDecimal("ITA_MENO").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				voRav24b1.setItaPiu(rs.getBigDecimal("ITA_PIU").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				voRav24b1.setItaZero(rs.getBigDecimal("ITA_ZERO").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				
				result.add(voRav24b1);
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
		logger.debug("FINE getIndicatoriRav24b1");
		return result;
	}

    public VORav24C5 getIndicatoriRav24c5(String codScuUt, int datAnnScoRil)
	throws Exception {
		
		logger.debug("INIZIO getIndicatoriRav24c5");
		logger.debug("codScuUt="+codScuUt);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VORav24C5 vo = null;
		
		try {
			StringBuffer query = new StringBuffer(" SELECT SCU.COD_FOR_SCU_APP, SCU.PER_ANN_SCO_SCU, ")
				.append(" SCU.PRC_DIP_60 SCU_6, ")
				.append(" SCU.PRC_DIP_61_70 SCU_7, ")
				.append(" SCU.PRC_DIP_71_80 SCU_8, ")
				.append(" SCU.PRC_DIP_81_90 SCU_9, ")
				.append(" SCU.PRC_DIP_91_100 SCU_10, ")
				.append(" SCU.PRC_DIP_LOD SCU_L, ")
				.append(" REG.PRC_DIP_60 REG_6, ")
				.append(" REG.PRC_DIP_61_70 REG_7, ")
				.append(" REG.PRC_DIP_71_80 REG_8, ")
				.append(" REG.PRC_DIP_81_90 REG_9, ")
				.append(" REG.PRC_DIP_91_100 REG_10, ")
				.append(" REG.PRC_DIP_LOD REG_L, ")
				.append(" ITA.PRC_DIP_60 ITA_6, ")
				.append(" ITA.PRC_DIP_61_70 ITA_7, ")
				.append(" ITA.PRC_DIP_71_80 ITA_8, ")
				.append(" ITA.PRC_DIP_81_90 ITA_9, ")
				.append(" ITA.PRC_DIP_91_100 ITA_10, ")
				.append(" ITA.PRC_DIP_LOD ITA_L ")				
				.append(" FROM MFG1002_ANAGISTSCOL ANAG,	MFG1014_COMUNE COM,	MFG1013_PROVINCIA PRO,	TRS1069_STUDSUPVOTOLICMEDIA SCU,	TRS1070_BMSTUDSUPVOTOLICMEDIA REG,	TRS1070_BMSTUDSUPVOTOLICMEDIA ITA WHERE ")
				.append(" ANAG.COD_SCU_UT = ? ")
				.append(" AND ANAG.DAT_ANN_SCO_RIL = ? ")
				.append(" AND ANAG.COD_COM = COM.COD_COM ")
				.append(" AND COM.COD_PRV = PRO.COD_PRV ")
				.append(" AND SCU.COD_FOR_SCU_APP = ANAG.COD_FOR_SCU_APP ")
				.append(" AND REG.COD_LIV_TER = 2 ")
				.append(" AND REG.COD_RIP = TRIM(PRO.COD_REG) ")
				.append(" AND REG.FLG_ORI_FRE = SCU.FLG_ORI_FRE ")
				.append(" AND ITA.COD_LIV_TER = 3 ")
				.append(" AND ITA.FLG_ORI_FRE = SCU.FLG_ORI_FRE ");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codScuUt);
			pstmt.setInt(2, datAnnScoRil);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new VORav24C5();
				vo.setAnnoScolastico(rs.getString("PER_ANN_SCO_SCU"));
				
				vo.setScu6(rs.getBigDecimal("SCU_6").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setScu7(rs.getBigDecimal("SCU_7").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setScu8(rs.getBigDecimal("SCU_8").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setScu9(rs.getBigDecimal("SCU_9").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setScu10(rs.getBigDecimal("SCU_10").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setScuL(rs.getBigDecimal("SCU_L").setScale(1, BigDecimal.ROUND_HALF_DOWN));

				vo.setReg6(rs.getBigDecimal("REG_6").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setReg7(rs.getBigDecimal("REG_7").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setReg8(rs.getBigDecimal("REG_8").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setReg9(rs.getBigDecimal("REG_9").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setReg10(rs.getBigDecimal("REG_10").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setRegL(rs.getBigDecimal("REG_L").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				
				vo.setIta6(rs.getBigDecimal("ITA_6").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setIta7(rs.getBigDecimal("ITA_7").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setIta8(rs.getBigDecimal("ITA_8").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setIta9(rs.getBigDecimal("ITA_9").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setIta10(rs.getBigDecimal("ITA_10").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setItaL(rs.getBigDecimal("ITA_L").setScale(1, BigDecimal.ROUND_HALF_DOWN));				
				
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
		logger.debug("FINE getIndicatoriRav24c5");
		return vo;
	}

    public List<VORav24c1> getIndicatoriRav24c1(String codScuUt, int datAnnScoRil)
	throws Exception {
		
		logger.debug("INIZIO getIndicatoriRav24c1");
		logger.debug("codScuUt="+codScuUt);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VORav24c1> result = new ArrayList<VORav24c1>();
		
		try {
			StringBuffer query = new StringBuffer(" SELECT SCU.COD_FOR_SCU_APP, SCU.PER_ANN_SCO_SCU, SCU.COD_ARE_DIS, ")
				.append(" SCU.NUM_CON NUM_CON, ")
				.append(" SCU.PRC_CON PRC_CON ")
				.append(" FROM MFG1002_ANAGISTSCOL ANAG,	MFG1014_COMUNE COM,	MFG1013_PROVINCIA PRO, TRS1071_CONSIGORIENSCUOLA SCU WHERE ")
				.append(" ANAG.COD_SCU_UT = ? ")
				.append(" AND ANAG.DAT_ANN_SCO_RIL = ? ")
				.append(" AND ANAG.COD_COM = COM.COD_COM ")
				.append(" AND COM.COD_PRV = PRO.COD_PRV ")
				.append(" AND SCU.COD_FOR_SCU_APP = ANAG.COD_FOR_SCU_APP ")
				.append(" ORDER BY SCU.COD_ARE_DIS ");

			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codScuUt);
			pstmt.setInt(2, datAnnScoRil);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				VORav24c1 vo = new VORav24c1();
				vo.setAnnoScolastico(rs.getString("PER_ANN_SCO_SCU"));
				vo.setArea(rs.getString("COD_ARE_DIS"));
				
				vo.setNumCon(rs.getBigDecimal("NUM_CON").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setPrcCon(rs.getBigDecimal("PRC_CON").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				
				result.add(vo);
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
		logger.debug("FINE getIndicatoriRav24c1");
		return result;
	}

    public VORav24c2 getIndicatoriRav24c2(String codScuUt, int datAnnScoRil, boolean isIGrado)
	throws Exception {
		
		logger.debug("INIZIO getIndicatoriRav24c2");
		logger.debug("codScuUt="+codScuUt);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VORav24c2 vo = null;
		
		String flgOreFri = "F";
		if (isIGrado){
			flgOreFri = "O";
		}/*TODO DECOMMENTARE else{

			if(codScuUt.substring(2, 3).compareTo("P") == 0 || codScuUt.substring(2, 3).compareTo("S") == 0) {
				flgOreFri = "L"; //Licei
			}
			else if(codScuUt.substring(2, 3).compareTo("T") == 0) {
				flgOreFri = "T"; //Tecnici
			}
			else if(codScuUt.substring(2, 3).compareTo("R") == 0) {
				flgOreFri = "P"; //Professionali
			}
			
		}*/
        


		
		try {
			StringBuffer query = new StringBuffer(" SELECT SCU.COD_FOR_SCU_APP, SCU.PER_ANN_SCO_SCU, ")
				.append(" SCU.PRC_CON_COR SCU_CON_COR, ")
				.append(" SCU.PRC_CON_NON_COR SCU_CON_NON_COR, ")
				.append(" REG.PRC_MED_CON_COR REG_CON_COR, ")
				.append(" REG.PRC_MED_CON_NON_COR REG_CON_NON_COR, ")
				.append(" ITA.PRC_MED_CON_COR ITA_CON_COR, ")
				.append(" ITA.PRC_MED_CON_NON_COR ITA_CON_NON_COR ")
				.append(" FROM MFG1002_ANAGISTSCOL ANAG,	MFG1014_COMUNE COM,	MFG1013_PROVINCIA PRO,	TRS1072_CONORISCELTAEFFET SCU,	TRS1073_BMCONSORISCEFF REG,	TRS1073_BMCONSORISCEFF ITA WHERE ")
				.append(" ANAG.COD_SCU_UT = ? ")
				.append(" AND ANAG.DAT_ANN_SCO_RIL = ? ")
				.append(" AND ANAG.COD_COM = COM.COD_COM ")
				.append(" AND COM.COD_PRV = PRO.COD_PRV ")
				.append(" AND SCU.COD_FOR_SCU_APP = ANAG.COD_FOR_SCU_APP ")
				.append(" AND SCU.COD_DES = '2.4.c.2' ")
				.append(" AND REG.COD_DES = SCU.COD_DES ")
				.append(" AND REG.COD_LIV_TER = 2 ")
				.append(" AND REG.COD_RIP = TRIM(PRO.COD_REG) ")
				.append(" AND ITA.COD_DES = SCU.COD_DES ")
				.append(" AND ITA.COD_LIV_TER = 3 ")
				.append(" AND REG.FLG_ORI_FRE = SCU.FLG_ORI_FRE ")
				.append(" AND ITA.FLG_ORI_FRE = SCU.FLG_ORI_FRE ");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codScuUt);
			pstmt.setInt(2, datAnnScoRil);
			//pstmt.setString(3, flgOreFri);
			//pstmt.setString(4, flgOreFri);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new VORav24c2();
				vo.setAnnoScolastico(rs.getString("PER_ANN_SCO_SCU"));
				
				vo.setScuConCor(rs.getBigDecimal("SCU_CON_COR").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setScuConNonCor(rs.getBigDecimal("SCU_CON_NON_COR").setScale(1, BigDecimal.ROUND_HALF_DOWN));

				vo.setRegConCor(rs.getBigDecimal("REG_CON_COR").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setRegConNonCor(rs.getBigDecimal("REG_CON_NON_COR").setScale(1, BigDecimal.ROUND_HALF_DOWN));

				vo.setItaConCor(rs.getBigDecimal("ITA_CON_COR").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setItaConNonCor(rs.getBigDecimal("ITA_CON_NON_COR").setScale(1, BigDecimal.ROUND_HALF_DOWN));

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
		logger.debug("FINE getIndicatoriRav24c2");
		return vo;
	}

    public VORav24c2 getIndicatoriRav24c3(String codScuUt, int datAnnScoRil, boolean isIGrado)
	throws Exception {
		
		logger.debug("INIZIO getIndicatoriRav24c3");
		logger.debug("codScuUt="+codScuUt);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VORav24c2 vo = null;
		
		String flgOreFri = "F";
		if (isIGrado){
			flgOreFri = "O";
		}/*TODO DECOMMENTARE else{

		if(codScuUt.substring(2, 3).compareTo("P") == 0 || codScuUt.substring(2, 3).compareTo("S") == 0) {
			flgOreFri = "L"; //Licei
		}
		else if(codScuUt.substring(2, 3).compareTo("T") == 0) {
			flgOreFri = "T"; //Tecnici
		}
		else if(codScuUt.substring(2, 3).compareTo("R") == 0) {
			flgOreFri = "P"; //Professionali
		}
		
		}*/
		
		try {
			StringBuffer query = new StringBuffer(" SELECT ")
				.append(" DECODE( ")
				.append(" NVL(SCU.NUM_CON_COR, 0), ")
				.append(" 0, 0, ")
				.append(" (NVL(SCU.NUM_CON_COR, 0)/( SCU.NUM_CON_COR + SCU.NUM_NAM_SEG_CON_ORI)*100)) SCU_AMM_SEG_CON, ")
				.append(" DECODE( ")
				.append(" NVL(SCU.NUM_CON_NON_COR, 0), ")
				.append(" 0, 0, ")
				.append(" (NVL(SCU.NUM_CON_NON_COR, 0)/( SCU.NUM_CON_NON_COR + SCU.NUM_NAM_NSE_CON_ORI)*100)) SCU_AMM_NON_SEG_CON, ")
				.append(" DECODE( ")
				.append(" NVL(REG.NUM_MED_CON_COR, 0), ")
				.append(" 0, 0, ")
				.append(" (NVL(REG.NUM_MED_CON_COR, 0)/( REG.NUM_MED_CON_COR + REG.NUM_MED_NAM_SEG_CON_ORI)*100)) REG_AMM_SEG_CON, ")
				.append(" DECODE( ")
				.append(" NVL(REG.NUM_MED_CON_NON_COR, 0), ")
				.append(" 0, 0, ")
				.append(" (NVL(REG.NUM_MED_CON_NON_COR, 0)/( REG.NUM_MED_CON_NON_COR + REG.NUM_MED_NAM_NSE_CON_ORI)*100)) REG_AMM_NON_SEG_CON, ")
				.append(" DECODE( ")
				.append(" NVL(ITA.NUM_MED_CON_COR, 0), ")
				.append(" 0, 0, ")
				.append(" (NVL(ITA.NUM_MED_CON_COR, 0)/( ITA.NUM_MED_CON_COR + ITA.NUM_MED_NAM_SEG_CON_ORI)*100)) ITA_AMM_SEG_CON, ")
				.append(" DECODE( ")
				.append(" NVL(ITA.NUM_MED_CON_NON_COR, 0), ")
				.append(" 0, 0, ")
				.append(" (NVL(ITA.NUM_MED_CON_NON_COR, 0)/( ITA.NUM_MED_CON_NON_COR + ITA.NUM_MED_NAM_NSE_CON_ORI)*100)) ITA_AMM_NON_SEG_CON ")				
				.append(" FROM MFG1002_ANAGISTSCOL ANAG,	MFG1014_COMUNE COM,	MFG1013_PROVINCIA PRO,	TRS1072_CONORISCELTAEFFET SCU,	TRS1073_BMCONSORISCEFF REG,	TRS1073_BMCONSORISCEFF ITA WHERE ")
				.append(" ANAG.COD_SCU_UT = ? ")
				.append(" AND ANAG.DAT_ANN_SCO_RIL = ? ")
				.append(" AND ANAG.COD_COM = COM.COD_COM ")
				.append(" AND COM.COD_PRV = PRO.COD_PRV ")
				.append(" AND SCU.COD_FOR_SCU_APP = ANAG.COD_FOR_SCU_APP ")
				.append(" AND SCU.COD_DES = '2.4.c.3' ")
				.append(" AND REG.COD_DES = SCU.COD_DES ")
				.append(" AND REG.COD_LIV_TER = 2 ")
				.append(" AND REG.COD_RIP = TRIM(PRO.COD_REG) ")
				.append(" AND ITA.COD_DES = SCU.COD_DES ")
				.append(" AND ITA.COD_LIV_TER = 3 ")
				.append(" AND REG.FLG_ORI_FRE = SCU.FLG_ORI_FRE ")
				.append(" AND ITA.FLG_ORI_FRE = SCU.FLG_ORI_FRE ");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codScuUt);
			pstmt.setInt(2, datAnnScoRil);
			//pstmt.setString(3, flgOreFri);
			//pstmt.setString(4, flgOreFri);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new VORav24c2();
				
				vo.setScuConCor(rs.getBigDecimal("SCU_AMM_SEG_CON").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setScuConNonCor(rs.getBigDecimal("SCU_AMM_NON_SEG_CON").setScale(1, BigDecimal.ROUND_HALF_DOWN));

				vo.setRegConCor(rs.getBigDecimal("REG_AMM_SEG_CON").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setRegConNonCor(rs.getBigDecimal("REG_AMM_NON_SEG_CON").setScale(1, BigDecimal.ROUND_HALF_DOWN));

				vo.setItaConCor(rs.getBigDecimal("ITA_AMM_SEG_CON").setScale(1, BigDecimal.ROUND_HALF_DOWN));
				vo.setItaConNonCor(rs.getBigDecimal("ITA_AMM_NON_SEG_CON").setScale(1, BigDecimal.ROUND_HALF_DOWN));

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
		logger.debug("FINE getIndicatoriRav24c3");
		return vo;
	}

	public List<VOAlternanzaScuolaLavoro> getDatiAlternanzaScuolaLavoro(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOAlternanzaScuolaLavoro> result = new ArrayList<VOAlternanzaScuolaLavoro>();
		
		try {
			StringBuffer query = new StringBuffer(" SELECT PER_ANN_SCO, COD_SCU, TIP_STR_OSP, SUM(N_ALUNNI_1) N_ALUNNI_1, SUM(N_ALUNNI_2) N_ALUNNI_2, SUM(N_ALUNNI_3) N_ALUNNI_3, SUM(N_ALUNNI_4) N_ALUNNI_4, SUM(N_ALUNNI_5) N_ALUNNI_5, SUM(N_PERCORSI) N_PERCORSI ")
				.append(" FROM ( ")
				.append(" SELECT DISTINCT A.PER_ANN_SCO, A.COD_SCU, A.DES_TIP_STR_OSP TIP_STR_OSP, ")
				.append(" NVL(A1.\"COUNT(DISTINCTCOD_ALU)\",0) N_ALUNNI_1, ")
				.append(" NVL(A2.\"COUNT(DISTINCTCOD_ALU)\",0) N_ALUNNI_2, ")
				.append(" NVL(A3.\"COUNT(DISTINCTCOD_ALU)\",0) N_ALUNNI_3, ")
				.append(" NVL(A4.\"COUNT(DISTINCTCOD_ALU)\",0) N_ALUNNI_4, ")
				.append(" NVL(A5.\"COUNT(DISTINCTCOD_ALU)\",0) N_ALUNNI_5, ")
				.append(" TO_NUMBER(NVL(A1.\"COUNT(DISTINCTPRG_PER_SCU)\",0)) + ")
				.append(" TO_NUMBER(NVL(A2.\"COUNT(DISTINCTPRG_PER_SCU)\",0)) +  ")
				.append(" TO_NUMBER(NVL(A3.\"COUNT(DISTINCTPRG_PER_SCU)\",0)) +  ")
				.append(" TO_NUMBER(NVL(A4.\"COUNT(DISTINCTPRG_PER_SCU)\",0)) +  ")
				.append(" TO_NUMBER(NVL(A5.\"COUNT(DISTINCTPRG_PER_SCU)\",0)) N_PERCORSI ")
				.append(" FROM MFG1426_ALUALTSCULAV A ")
				.append(" LEFT OUTER JOIN MFG1426_ALUALTSCULAV A1 ON (A.PER_ANN_SCO = A1.PER_ANN_SCO AND A.COD_SCU = A1.COD_SCU AND NVL(A.DES_TIP_STR_OSP,'XXX') = NVL(A1.DES_TIP_STR_OSP,'XXX') AND A1.COD_ANN_COR = 1) ")
				.append(" LEFT OUTER JOIN MFG1426_ALUALTSCULAV A2 ON (A.PER_ANN_SCO = A2.PER_ANN_SCO AND A.COD_SCU = A2.COD_SCU AND NVL(A.DES_TIP_STR_OSP,'XXX') = NVL(A2.DES_TIP_STR_OSP,'XXX') AND A2.COD_ANN_COR = 2) ")
				.append(" LEFT OUTER JOIN MFG1426_ALUALTSCULAV A3 ON (A.PER_ANN_SCO = A3.PER_ANN_SCO AND A.COD_SCU = A3.COD_SCU AND NVL(A.DES_TIP_STR_OSP,'XXX') = NVL(A3.DES_TIP_STR_OSP,'XXX') AND A3.COD_ANN_COR = 3) ")
				.append(" LEFT OUTER JOIN MFG1426_ALUALTSCULAV A4 ON (A.PER_ANN_SCO = A4.PER_ANN_SCO AND A.COD_SCU = A4.COD_SCU AND NVL(A.DES_TIP_STR_OSP,'XXX') = NVL(A4.DES_TIP_STR_OSP,'XXX') AND A4.COD_ANN_COR = 4) ")
				.append(" LEFT OUTER JOIN MFG1426_ALUALTSCULAV A5 ON (A.PER_ANN_SCO = A5.PER_ANN_SCO AND A.COD_SCU = A5.COD_SCU AND NVL(A.DES_TIP_STR_OSP,'XXX') = NVL(A5.DES_TIP_STR_OSP,'XXX') AND A5.COD_ANN_COR = 5) ")
				.append(" WHERE ")
				.append(" A.COD_SCU = ? ")
				.append(" ) GROUP BY PER_ANN_SCO, COD_SCU, TIP_STR_OSP ")
				.append(" ORDER BY TIP_STR_OSP ");
							
			Object[] campi = new Object[]{codForScuApp};
			int[] tipi = new int[]{Types.VARCHAR};
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString());
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAlternanzaScuolaLavoro vo = null;
			while(rs.next()) {
				vo = new VOAlternanzaScuolaLavoro();
				vo.setTipoStrutturaOspitante((rs.getString("TIP_STR_OSP")!=null && !rs.getString("TIP_STR_OSP").isEmpty())?rs.getString("TIP_STR_OSP"):"n.d.");			
				//vo.setDesStrutturaOspitante(rs.getString("DES_STR_OSP"));
				vo.setPercorsi(rs.getInt("N_PERCORSI"));
				vo.setAlunni1(rs.getInt("N_ALUNNI_1"));
				vo.setAlunni2(rs.getInt("N_ALUNNI_2"));
				vo.setAlunni3(rs.getInt("N_ALUNNI_3"));
				vo.setAlunni4(rs.getInt("N_ALUNNI_4"));
				vo.setAlunni5(rs.getInt("N_ALUNNI_5"));
				
				result.add(vo);
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


    public List<VOLavoro1> getDatiLavoro1(String codForScuApp)
	throws Exception {
		
		logger.debug("INIZIO getDatiLavoro1");
		logger.debug("codForScuApp="+codForScuApp);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOLavoro1> result = new ArrayList<VOLavoro1>();
		
		try {
			StringBuffer query = new StringBuffer(" SELECT A.COD_SCU_UTE, A.DAT_ANN_SCO_RIL, A.ANN_CSG_DIP, A.PRC_LAV_SCU, A.PRC_LAV_REG, A.PRC_LAV_ITA ")
				.append(" FROM TRS1074_DIPLOMATIRAPLAVORO A, MFG1002_ANAGISTSCOL ANAG WHERE ")
				.append(" A.COD_SCU_UTE = ANAG.COD_SCU_UT ")
				.append(" AND A.DAT_ANN_SCO_RIL = ANAG.DAT_ANN_SCO_RIL ")
				.append(" AND ANAG.COD_FOR_SCU_APP = ? ")
				.append(" ORDER BY A.ANN_CSG_DIP ");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				VOLavoro1 vo = new VOLavoro1();
				vo.setAnnoDiploma(rs.getString("ANN_CSG_DIP"));
				
				vo.setScu(rs.getBigDecimal("PRC_LAV_SCU")!=null?rs.getBigDecimal("PRC_LAV_SCU").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setReg(rs.getBigDecimal("PRC_LAV_REG")!=null?rs.getBigDecimal("PRC_LAV_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setIta(rs.getBigDecimal("PRC_LAV_ITA")!=null?rs.getBigDecimal("PRC_LAV_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);

				result.add(vo);
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
		logger.debug("FINE getDatiLavoro1");
		return result;
	}

    
    public List<VOLavoro2> getDatiLavoro2(String codForScuApp)
	throws Exception {
		
		logger.debug("INIZIO getDatiLavoro2");
		logger.debug("codForScuApp="+codForScuApp);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOLavoro2> result = new ArrayList<VOLavoro2>();
		
		try {
			StringBuffer query = new StringBuffer(" SELECT A.COD_SCU_UTE, A.DAT_ANN_SCO_RIL, A.ANN_CSG_DIP, ")
				.append(" A.PRC_CON_IND, A.PRC_CON_DET, A.PRC_CON_APP, A.PRC_CON_COL, A.PRC_CON_TIR, A.PRC_CON_ALT, ")
				.append(" A.PRC_IND_REG, A.PRC_DET_REG, A.PRC_APP_REG, A.PRC_COL_REG, A.PRC_TIR_REG, A.PRC_ALT_REG, ")
				.append(" A.PRC_IND_ITA, A.PRC_DET_ITA, A.PRC_APP_ITA, A.PRC_COL_ITA, A.PRC_TIR_ITA, A.PRC_ALT_ITA ")
				.append(" FROM TRS1075_DIPLOMATICONTRATTOLAV A, MFG1002_ANAGISTSCOL ANAG WHERE ")
				.append(" A.COD_SCU_UTE = ANAG.COD_SCU_UT ")
				.append(" AND A.DAT_ANN_SCO_RIL = ANAG.DAT_ANN_SCO_RIL ")
				.append(" AND ANAG.COD_FOR_SCU_APP = ? ")
				.append(" ORDER BY A.ANN_CSG_DIP ");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				VOLavoro2 vo = new VOLavoro2();
				vo.setAnnoDiploma(rs.getString("ANN_CSG_DIP"));
				
				vo.setScuTin(rs.getBigDecimal("PRC_CON_IND")!=null?rs.getBigDecimal("PRC_CON_IND").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setScuTde(rs.getBigDecimal("PRC_CON_DET")!=null?rs.getBigDecimal("PRC_CON_DET").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setScuApp(rs.getBigDecimal("PRC_CON_APP")!=null?rs.getBigDecimal("PRC_CON_APP").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setScuCol(rs.getBigDecimal("PRC_CON_COL")!=null?rs.getBigDecimal("PRC_CON_COL").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setScuTir(rs.getBigDecimal("PRC_CON_TIR")!=null?rs.getBigDecimal("PRC_CON_TIR").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setScuAlt(rs.getBigDecimal("PRC_CON_ALT")!=null?rs.getBigDecimal("PRC_CON_ALT").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);

				vo.setRegTin(rs.getBigDecimal("PRC_IND_REG")!=null?rs.getBigDecimal("PRC_IND_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setRegTde(rs.getBigDecimal("PRC_DET_REG")!=null?rs.getBigDecimal("PRC_DET_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setRegApp(rs.getBigDecimal("PRC_APP_REG")!=null?rs.getBigDecimal("PRC_APP_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setRegCol(rs.getBigDecimal("PRC_COL_REG")!=null?rs.getBigDecimal("PRC_COL_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setRegTir(rs.getBigDecimal("PRC_TIR_REG")!=null?rs.getBigDecimal("PRC_TIR_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setRegAlt(rs.getBigDecimal("PRC_ALT_REG")!=null?rs.getBigDecimal("PRC_ALT_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);

				vo.setItaTin(rs.getBigDecimal("PRC_IND_ITA")!=null?rs.getBigDecimal("PRC_IND_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setItaTde(rs.getBigDecimal("PRC_DET_ITA")!=null?rs.getBigDecimal("PRC_DET_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setItaApp(rs.getBigDecimal("PRC_APP_ITA")!=null?rs.getBigDecimal("PRC_APP_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setItaCol(rs.getBigDecimal("PRC_COL_ITA")!=null?rs.getBigDecimal("PRC_COL_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setItaTir(rs.getBigDecimal("PRC_TIR_ITA")!=null?rs.getBigDecimal("PRC_TIR_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setItaAlt(rs.getBigDecimal("PRC_ALT_ITA")!=null?rs.getBigDecimal("PRC_ALT_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				
				result.add(vo);
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
		logger.debug("FINE getDatiLavoro2");
		return result;
	}

    public List<VOLavoro3> getDatiLavoro3(String codForScuApp)
	throws Exception {
		
		logger.debug("INIZIO getDatiLavoro3");
		logger.debug("codForScuApp="+codForScuApp);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOLavoro3> result = new ArrayList<VOLavoro3>();
		
		try {
			StringBuffer query = new StringBuffer(" SELECT A.COD_SCU_UTE, A.DAT_ANN_SCO_RIL, A.ANN_CSG_DIP, ")
										  .append(" A.PRC_ATT_AGR, A.PRC_ATT_IDR, A.PRC_ATT_SER, ")
										  .append(" A.PRC_AGR_REG, A.PRC_IDR_REG, A.PRC_SER_REG, ")
										  .append(" A.PRC_AGR_ITA, A.PRC_IDR_ITA, A.PRC_SER_ITA ")
										  .append(" FROM TRS1076_DIPLOMATISERVIZIOLAV A, MFG1002_ANAGISTSCOL ANAG WHERE ")
										  .append(" A.COD_SCU_UTE = ANAG.COD_SCU_UT ")
										  .append(" AND A.DAT_ANN_SCO_RIL = ANAG.DAT_ANN_SCO_RIL ")
										  .append(" AND ANAG.COD_FOR_SCU_APP = ? ")
										  .append(" ORDER BY A.ANN_CSG_DIP ");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				VOLavoro3 vo = new VOLavoro3();
				vo.setAnnoDiploma(rs.getString("ANN_CSG_DIP"));
				
				vo.setScuAgr(rs.getBigDecimal("PRC_ATT_AGR")!=null?rs.getBigDecimal("PRC_ATT_AGR").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setScuInd(rs.getBigDecimal("PRC_ATT_IDR")!=null?rs.getBigDecimal("PRC_ATT_IDR").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setScuSer(rs.getBigDecimal("PRC_ATT_SER")!=null?rs.getBigDecimal("PRC_ATT_SER").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);

				vo.setRegAgr(rs.getBigDecimal("PRC_AGR_REG")!=null?rs.getBigDecimal("PRC_AGR_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setRegInd(rs.getBigDecimal("PRC_IDR_REG")!=null?rs.getBigDecimal("PRC_IDR_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setRegSer(rs.getBigDecimal("PRC_SER_REG")!=null?rs.getBigDecimal("PRC_SER_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);

				vo.setItaAgr(rs.getBigDecimal("PRC_AGR_ITA")!=null?rs.getBigDecimal("PRC_AGR_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setItaInd(rs.getBigDecimal("PRC_IDR_ITA")!=null?rs.getBigDecimal("PRC_IDR_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setItaSer(rs.getBigDecimal("PRC_SER_ITA")!=null?rs.getBigDecimal("PRC_SER_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				
				result.add(vo);
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
		logger.debug("FINE getDatiLavoro3");
		return result;
	}

    public List<VOLavoro4> getDatiLavoro4(String codForScuApp)
	throws Exception {
		
		logger.debug("INIZIO getDatiLavoro4");
		logger.debug("codForScuApp="+codForScuApp);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOLavoro4> result = new ArrayList<VOLavoro4>();
		
		try {
			StringBuffer query = new StringBuffer(" SELECT A.COD_SCU_UTE, A.DAT_ANN_SCO_RIL, A.ANN_CSG_DIP, ")
										  .append(" A.PRC_QUA_ALT, A.PRC_QUA_MED, A.PRC_QUA_BAS, ")
										  .append(" A.PRC_ALT_REG, A.PRC_MED_REG, A.PRC_BAS_REG, ")
										  .append(" A.PRC_ALT_ITA, A.PRC_MED_ITA, A.PRC_BAS_ITA ")
										  .append(" FROM TRS1077_DIPLOMATIQUALIFICALAV A, MFG1002_ANAGISTSCOL ANAG WHERE ")
										  .append(" A.COD_SCU_UTE = ANAG.COD_SCU_UT ")
										  .append(" AND A.DAT_ANN_SCO_RIL = ANAG.DAT_ANN_SCO_RIL ")
										  .append(" AND ANAG.COD_FOR_SCU_APP = ? ")
										  .append(" ORDER BY A.ANN_CSG_DIP ");
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				VOLavoro4 vo = new VOLavoro4();
				vo.setAnnoDiploma(rs.getString("ANN_CSG_DIP"));
				
				vo.setScuAlt(rs.getBigDecimal("PRC_QUA_ALT")!=null?rs.getBigDecimal("PRC_QUA_ALT").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setScuMed(rs.getBigDecimal("PRC_QUA_MED")!=null?rs.getBigDecimal("PRC_QUA_MED").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setScuBas(rs.getBigDecimal("PRC_QUA_BAS")!=null?rs.getBigDecimal("PRC_QUA_BAS").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);

				vo.setRegAlt(rs.getBigDecimal("PRC_ALT_REG")!=null?rs.getBigDecimal("PRC_ALT_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setRegMed(rs.getBigDecimal("PRC_MED_REG")!=null?rs.getBigDecimal("PRC_MED_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setRegBas(rs.getBigDecimal("PRC_BAS_REG")!=null?rs.getBigDecimal("PRC_BAS_REG").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);

				vo.setItaAlt(rs.getBigDecimal("PRC_ALT_ITA")!=null?rs.getBigDecimal("PRC_ALT_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setItaMed(rs.getBigDecimal("PRC_MED_ITA")!=null?rs.getBigDecimal("PRC_MED_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				vo.setItaBas(rs.getBigDecimal("PRC_BAS_ITA")!=null?rs.getBigDecimal("PRC_BAS_ITA").setScale(1, BigDecimal.ROUND_HALF_DOWN):null);
				
				result.add(vo);
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
		logger.debug("FINE getDatiLavoro4");
		return result;
	}
    
	public String dat_ann_sco_ril_format(String dataIn){
		return dataIn.substring(0, 4)+"/"+dataIn.substring(4);
	}

	@Override
	public List<VOStruttureOspitanti> getStruttureOspitanti(String $codScuUt, Integer $perAnnScoVal, Long $prgStrScu) throws Exception{
		logger.info("getStruttureOspitanti('" + $codScuUt + "'," + $perAnnScoVal + "," + $prgStrScu + ")...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOStruttureOspitanti> result = new ArrayList<VOStruttureOspitanti>();
		StringBuffer strbQuery = new StringBuffer();
		try {
			strbQuery.append("SELECT PER_ANN_SCO_VAL    PER_ANN_SCO");
			strbQuery.append(",COD_SCU_UT         COD_SCU");
			strbQuery.append(",DES_AZI            DES_AZI");
			strbQuery.append(",PRG_STR_SCU       PRG_STR_SCU");
			strbQuery.append(",COUNT (DISTINCT PRG_PER_SCU) N_PERCORSI");
			strbQuery.append(",SUM ( N_ALUNNI_3 ) N_ALUNNI_3");
			strbQuery.append(",SUM ( N_ALUNNI_4 ) N_ALUNNI_4");
			strbQuery.append(",SUM ( N_ALUNNI_5 ) N_ALUNNI_5");
			strbQuery.append(" FROM(");    
			strbQuery.append("SELECT   PER_ANN_SCO_VAL");
			strbQuery.append(",COD_SCU_UT");
			strbQuery.append(",DES_AZI");
			strbQuery.append(",PRG_STR_SCU");
			strbQuery.append(",PRG_PER_SCU");
			strbQuery.append(",NVL (SUM ( NUM_ALU ) , 0) AS N_ALUNNI_3");
			strbQuery.append(",0 N_ALUNNI_4");
			strbQuery.append(",0 N_ALUNNI_5");
			strbQuery.append(" FROM  MFG1056_ALTERSCUOLAVORO");
			strbQuery.append(" WHERE NUM_ANN_COR = 3");
			strbQuery.append(" GROUP BY PER_ANN_SCO_VAL");
			strbQuery.append(",COD_SCU_UT");
			strbQuery.append(",DES_AZI");
			strbQuery.append(",PRG_STR_SCU");
			strbQuery.append(",PRG_PER_SCU");
			strbQuery.append(" UNION ALL");
			strbQuery.append(" SELECT   PER_ANN_SCO_VAL");
			strbQuery.append(",COD_SCU_UT");
			strbQuery.append(",DES_AZI");
			strbQuery.append(",PRG_STR_SCU");
			strbQuery.append(",PRG_PER_SCU");
			strbQuery.append(",0");
			strbQuery.append(",NVL (SUM ( NUM_ALU ) , 0)");
			strbQuery.append(",0");
			strbQuery.append(" FROM  MFG1056_ALTERSCUOLAVORO");
			strbQuery.append(" WHERE NUM_ANN_COR = 4");
			strbQuery.append(" GROUP BY PER_ANN_SCO_VAL");
			strbQuery.append(",PRG_PER_SCU");
			strbQuery.append(",COD_SCU_UT");
			strbQuery.append(",DES_AZI");
			strbQuery.append(",PRG_STR_SCU");
			strbQuery.append(" UNION ALL");
			strbQuery.append(" SELECT  PER_ANN_SCO_VAL");
			strbQuery.append(",COD_SCU_UT");
			strbQuery.append(",DES_AZI");
			strbQuery.append(",PRG_STR_SCU");
			strbQuery.append(",PRG_PER_SCU");
			strbQuery.append(",0");
			strbQuery.append(",0");
			strbQuery.append(",NVL (SUM ( NUM_ALU ) , 0)");
			strbQuery.append(" FROM MFG1056_ALTERSCUOLAVORO");
			strbQuery.append(" WHERE NUM_ANN_COR = 5");
			strbQuery.append(" GROUP BY");
			strbQuery.append(" PER_ANN_SCO_VAL");
			strbQuery.append(",COD_SCU_UT");
			strbQuery.append(",DES_AZI");
			strbQuery.append(",PRG_STR_SCU");
			strbQuery.append(",PRG_PER_SCU)");
			strbQuery.append(" WHERE COD_SCU_UT = ?");
			strbQuery.append(" AND PER_ANN_SCO_VAL = ?");
			if($prgStrScu != null){
				strbQuery.append(" AND PRG_STR_SCU = ?");
			}
			strbQuery.append(" GROUP BY  PER_ANN_SCO_VAL, COD_SCU_UT, DES_AZI, PRG_STR_SCU");
			strbQuery.append(" ORDER BY DES_AZI");
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(strbQuery.toString());
			pstmt.setString(1, $codScuUt);
			pstmt.setInt(2, $perAnnScoVal);
			if($prgStrScu != null){
				pstmt.setLong(3, $prgStrScu);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOStruttureOspitanti vo = new VOStruttureOspitanti();
				vo.setPrgStrScu(rs.getLong("PRG_STR_SCU"));
				vo.setDesStrutturaOspitante(rs.getString("DES_AZI"));
				vo.setNumPercorsiAttivati(rs.getInt("N_PERCORSI"));
				vo.setNumAlunniAnno3(rs.getInt("N_ALUNNI_3"));
				vo.setNumAlunniAnno4(rs.getInt("N_ALUNNI_4"));
				vo.setNumAlunniAnno5(rs.getInt("N_ALUNNI_5"));
				result.add(vo);
			}			
		}
		catch(Exception ex){
			logger.error("ERRORE getStruttureOspitanti('" + $codScuUt + "'," + $perAnnScoVal + "), query: " + strbQuery.toString(), ex);
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
		logger.debug("FINE getDatiLavoro4");
		return result;
	}
	
	@Override
	public List<VOPercorsiAttivati> getPercorsiStruttura(String $codScuUt,Integer $perAnnScoVal, Long $prgStrScu) throws Exception {
		logger.info("getPercorsiStruttura('" + $codScuUt + "'," + $perAnnScoVal + "," + $prgStrScu + ")...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOPercorsiAttivati> result = new ArrayList<VOPercorsiAttivati>();
		StringBuffer strbQuery = new StringBuffer();
		try {
			strbQuery.append("SELECT DISTINCT");
			strbQuery.append(" A.PER_ANN_SCO_VAL   AS PER_ANN_SCO");
			strbQuery.append(",A.COD_SCU_UT        AS COD_SCU");
			strbQuery.append(",A.PRG_PER_SCU       AS PRG_PER_SCU");
			strbQuery.append(",A.PRG_STR_SCU       AS PRG_STR_SCU");
			strbQuery.append(",A.DES_PER_SCU       AS DES_PER_SCU");
			strbQuery.append(",NVL ( (NUM_ORE_DID_AUL),0) AS NUM_ORE_DID_AUL");
			strbQuery.append(",NVL ( (NUM_ORE_STR_OSP),0) AS NUM_ORE_STR_OSP");
			strbQuery.append(" FROM  MFG1056_ALTERSCUOLAVORO A");
			strbQuery.append(" WHERE NUM_ANN_COR IN (3,4,5)");
			strbQuery.append(" AND COD_SCU_UT =  ?");
			strbQuery.append(" AND PER_ANN_SCO_VAL = ?");
			strbQuery.append(" AND PRG_STR_SCU = ?");
			strbQuery.append(" ORDER BY A.DES_PER_SCU");
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(strbQuery.toString());
			pstmt.setString(1, $codScuUt);
			pstmt.setInt(2, $perAnnScoVal);
			pstmt.setLong(3, $prgStrScu);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOPercorsiAttivati vo = new VOPercorsiAttivati();
				vo.setPrgPerScu(rs.getLong("PRG_PER_SCU"));
				vo.setDesPercorso(rs.getString("DES_PER_SCU"));
				vo.setNumOreAula(rs.getInt("NUM_ORE_DID_AUL"));
				vo.setNumOreStruttura(rs.getInt("NUM_ORE_STR_OSP"));
				result.add(vo);
			}			
		}
		catch(Exception ex){
			logger.error("ERRORE getPercorsiStruttura('" + $codScuUt + "'," + $perAnnScoVal + "," + $prgStrScu + "), query: " + strbQuery.toString(), ex);
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
	

	
	@Override
	public List<VOPercorsiAttivati> getPercorsiAttivati(String $codScuUt, Integer $perAnnScoVal, Long $prgPerScu) throws Exception {
		logger.info("getPercorsiAttivati('" + $codScuUt + "'," + $perAnnScoVal + ")...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOPercorsiAttivati> result = new ArrayList<VOPercorsiAttivati>();
		StringBuffer strbQuery = new StringBuffer();
		try {
			strbQuery.append("SELECT");
			strbQuery.append(" A.PER_ANN_SCO_VAL   AS PER_ANN_SCO");
			strbQuery.append(",A.COD_SCU_UT        AS COD_SCU");
			strbQuery.append(",A.PRG_PER_SCU       AS PRG_PER_SCU");
			strbQuery.append(",A.DES_PER_SCU       AS DES_PER_SCU");
			strbQuery.append(",A.NUM_ORE_DID_AUL AS NUM_ORE_DID_AUL");
			strbQuery.append(",A.NUM_ORE_STR_OSP AS NUM_ORE_STR_OSP");
			strbQuery.append(",SUM(NVL ( CASE WHEN A.NUM_ANN_COR = 3 THEN A.NUM_ALU ELSE 0 END,0)) AS N_ALUNNI_3");
			strbQuery.append(",SUM(NVL ( CASE WHEN A.NUM_ANN_COR = 4 THEN A.NUM_ALU ELSE 0 END,0)) AS N_ALUNNI_4");
			strbQuery.append(",SUM(NVL ( CASE WHEN A.NUM_ANN_COR = 5 THEN A.NUM_ALU ELSE 0 END,0)) AS N_ALUNNI_5");
			strbQuery.append(" FROM  MFG1056_ALTERSCUOLAVORO A");
			strbQuery.append(" WHERE COD_SCU_UT =  ?");
			strbQuery.append(" AND PER_ANN_SCO_VAL = ?");
			if($prgPerScu != null){
				strbQuery.append(" AND PRG_PER_SCU = ?");
			}
			strbQuery.append(" AND NUM_ANN_COR IN (3,4,5)");
			strbQuery.append(" GROUP BY"); 
			strbQuery.append(" A.PER_ANN_SCO_VAL"); 
			strbQuery.append(",A.COD_SCU_UT");      
			strbQuery.append(",A.PRG_PER_SCU");     
			strbQuery.append(",A.DES_PER_SCU");  
			strbQuery.append(",A.NUM_ORE_DID_AUL"); 
			strbQuery.append(",A.NUM_ORE_STR_OSP"); 
			strbQuery.append(" ORDER BY A.DES_PER_SCU");
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(strbQuery.toString());
			pstmt.setString(1, $codScuUt);
			pstmt.setInt(2, $perAnnScoVal);
			if($prgPerScu != null){
				pstmt.setLong(3, $prgPerScu);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOPercorsiAttivati vo = new VOPercorsiAttivati();
				vo.setPrgPerScu(rs.getLong("PRG_PER_SCU"));
				vo.setDesPercorso(rs.getString("DES_PER_SCU"));
				vo.setNumOreAula(rs.getInt("NUM_ORE_DID_AUL"));
				vo.setNumOreStruttura(rs.getInt("NUM_ORE_STR_OSP"));
				vo.setNumAlunniAnno3(rs.getInt("N_ALUNNI_3"));
				vo.setNumAlunniAnno4(rs.getInt("N_ALUNNI_4"));
				vo.setNumAlunniAnno5(rs.getInt("N_ALUNNI_5"));
				result.add(vo);
			}			
		}
		catch(Exception ex){
			logger.error("ERRORE getPercorsiAttivati('" + $codScuUt + "'," + $perAnnScoVal + "), query: " + strbQuery.toString(), ex);
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

	@Override
	public List<VOStruttureOspitanti> getStruttureOspitantiPercorso(String $codScuUt, Integer $perAnnScoVal, Long $prgPerScu) throws Exception {
		logger.info("getStruttureOspitantiPercorso('" + $codScuUt + "'," + $perAnnScoVal + "," + $prgPerScu + ")...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOStruttureOspitanti> result = new ArrayList<VOStruttureOspitanti>();
		StringBuffer strbQuery = new StringBuffer();
		try {
			strbQuery.append("SELECT DISTINCT");
			strbQuery.append(" PER_ANN_SCO_VAL  AS PER_ANN_SCO");
			strbQuery.append(",COD_SCU_UT       AS COD_SCU");
			strbQuery.append(",DES_AZI          AS DES_AZI");
			strbQuery.append(" FROM  MFG1056_ALTERSCUOLAVORO");
			strbQuery.append(" WHERE NUM_ANN_COR IN (3,4,5)");
			strbQuery.append(" AND COD_SCU_UT =  ?");
			strbQuery.append(" AND PER_ANN_SCO_VAL = ?");
			strbQuery.append(" AND PRG_PER_SCU = ?");
			strbQuery.append(" ORDER BY DES_AZI");
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(strbQuery.toString());
			pstmt.setString(1, $codScuUt);
			pstmt.setInt(2, $perAnnScoVal);
			pstmt.setLong(3, $prgPerScu);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOStruttureOspitanti vo = new VOStruttureOspitanti();
				vo.setDesStrutturaOspitante(rs.getString("DES_AZI"));
				result.add(vo);
			}			
		}
		catch(Exception ex){
			logger.error("ERRORE getStruttureOspitantiPercorso('" + $codScuUt + "'," + $perAnnScoVal + "," + $prgPerScu + "), query: " + strbQuery.toString(), ex);
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

	@Override
	public Integer getNumPercorsiAlternanzaScuolaLavoro(String $codScuUt, Integer $perAnnScoVal) throws Exception {
		logger.info("getNumPercorsiAlternanzaScuolaLavoro('" + $codScuUt + "'," + $perAnnScoVal + ")...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer numPercorsi = 0;
		StringBuffer strbQuery = new StringBuffer();
		try {
			strbQuery.append("SELECT COUNT(DISTINCT PRG_PER_SCU) AS NUM_PERCORSI");
			strbQuery.append(" FROM  MFG1056_ALTERSCUOLAVORO");
			strbQuery.append(" WHERE NUM_ANN_COR IN (3,4,5)");
			strbQuery.append(" AND COD_SCU_UT =  ?");
			strbQuery.append(" AND PER_ANN_SCO_VAL = ?");
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(strbQuery.toString());
			pstmt.setString(1, $codScuUt);
			pstmt.setInt(2, $perAnnScoVal);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				numPercorsi = rs.getInt("NUM_PERCORSI");
			}			
		}
		catch(Exception ex){
			logger.error("ERRORE getNumPercorsiAlternanzaScuolaLavoro('" + $codScuUt + "'," + $perAnnScoVal + "), query: " + strbQuery.toString(), ex);
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
		return numPercorsi;
	}
	
	
	public Integer getNumStruttureAlternanzaScuolaLavoro(String $codScuUt, Integer $perAnnScoVal) throws Exception{
		logger.info("getNumStruttureAlternanzaScuolaLavoro('" + $codScuUt + "'," + $perAnnScoVal + ")...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer numPercorsi = 0;
		StringBuffer strbQuery = new StringBuffer();
		try {
			strbQuery.append("SELECT COUNT(DISTINCT PRG_STR_SCU) AS NUM_STRUTTURE");
			strbQuery.append(" FROM  MFG1056_ALTERSCUOLAVORO");
			strbQuery.append(" WHERE NUM_ANN_COR IN (3,4,5)");
			strbQuery.append(" AND COD_SCU_UT =  ?");
			strbQuery.append(" AND PER_ANN_SCO_VAL = ?");
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(strbQuery.toString());
			pstmt.setString(1, $codScuUt);
			pstmt.setInt(2, $perAnnScoVal);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				numPercorsi = rs.getInt("NUM_STRUTTURE");
			}			
		}
		catch(Exception ex){
			logger.error("ERRORE getNumStruttureAlternanzaScuolaLavoro('" + $codScuUt + "'," + $perAnnScoVal + "), query: " + strbQuery.toString(), ex);
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
		return numPercorsi;
	}
	
}
