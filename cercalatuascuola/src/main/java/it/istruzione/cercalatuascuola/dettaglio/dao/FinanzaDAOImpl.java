package it.istruzione.cercalatuascuola.dettaglio.dao;


import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOFinanza;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FinanzaDAOImpl extends BaseDAO implements FinanzaDAO {

	public FinanzaDAOImpl(DataSource dataSource) {
		super(dataSource);
	}
		
	public List<VOFinanza> getEntratePerFontiFin(String codForScuApp)
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOFinanza> result = new ArrayList<VOFinanza>();
		
		try {
			String query = "SELECT "+
							    " trs1039.prg_agg_ent, "+ 
							    " trs1039.des_agg_ent, "+ 
							    " ROUND (trs1039.prc_agg_ent, 1), "+ 
							    " trs1039.prc_agg_ent "+
							" FROM trs1039_entrmonsintesi trs1039 "+
							" WHERE trs1039.cod_scu_ut IN ( "+
							    " SELECT DISTINCT cod_scu_ut_pri "+
							    " FROM mfg1002_anagistscol mfg1002 "+
							    " WHERE mfg1002.cod_for_scu_app = ? "+
							    " AND mfg1002.dat_ann_sco_ril = ( "+
							        " SELECT MAX (trs1039_in.dat_ann_sco_rif) "+
							        " FROM trs1039_entrmonsintesi trs1039_in "+
							        " WHERE trs1039_in.cod_scu_ut IN ( "+
							            " SELECT DISTINCT mfg1002_in.cod_scu_ut_pri "+
							            " FROM mfg1002_anagistscol mfg1002_in "+
							            " WHERE mfg1002_in.cod_for_scu_app = ? "+
							        " ) "+
							    " ) "+
							" ) "+
							" ORDER BY 1";
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
						
			rs = pstmt.executeQuery();

			while(rs.next()) {
				VOFinanza voFinanza = new VOFinanza();
				voFinanza.setPrgAggEnt(rs.getString("PRG_AGG_ENT"));
				voFinanza.setDesAggEnt(rs.getString("DES_AGG_ENT"));
				voFinanza.setPrcAggEnt(rs.getBigDecimal("PRC_AGG_ENT").setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
				
				result.add(voFinanza);
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
	
	public List<VOFinanza> getEntratePerFontiFinSpesa(String codForScuApp) 
	throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOFinanza> result = new ArrayList<VOFinanza>();
		
		try {
			String query = "SELECT prg_agg_ent, NVL (des_agg_ent, '') des_agg_ent, prg_det_ent, "+
						         " NVL (des_det_ent, '') des_det_ent, ROUND (imp_fnz_gen, 1) imp_fnz_gen, "+
						         " ROUND (prc_fnz_gen, 1) prc_fnz_gen, "+
						         " ROUND (imp_spe_pul, 1) imp_spe_pul, "+
						         " ROUND (prc_spe_pul, 1) prc_spe_pul, "+
						         " ROUND (imp_ris_ret_ape, 1) imp_ris_ret_ape, "+
						         " ROUND (prc_ris_ret_ape, 1) prc_ris_ret_ape, "+
						         " ROUND (imp_ris_sup_bre, 1) imp_ris_sup_bre, "+
						         " ROUND (prc_ris_sup_bre, 1) prc_ris_sup_bre, "+
						         " ROUND (imp_sti_per_ruo, 1) imp_sti_per_ruo, "+
						         " ROUND (prc_sti_per_ruo, 1) prc_sti_per_ruo, "+
						         " ROUND (imp_sti_per_sup, 1) imp_sti_per_sup, "+
						         " ROUND (prc_sti_per_sup, 1) prc_sti_per_sup, "+
						         " ROUND (IMP_AMP_OFF_FOR, 1) IMP_AMP_OFF_FOR, "+
						         " ROUND (PRC_AMP_OFF_FOR, 1) PRC_AMP_OFF_FOR "+
						    " FROM trs1040_entmondetagg "+
						   " WHERE cod_scu_ut IN (SELECT DISTINCT cod_scu_ut_pri "+
						                                   " FROM mfg1002_anagistscol "+
						                                  " WHERE cod_for_scu_app = ? "+
						                                    " AND dat_ann_sco_ril = "+
						                                                         " (SELECT MAX (dat_ann_sco_rif) "+
						                                                            " FROM trs1040_entmondetagg "+
						                                                           " WHERE cod_scu_ut IN (SELECT DISTINCT cod_scu_ut_pri "+
						                                                                                           " FROM mfg1002_anagistscol "+
						                                                                                          " WHERE cod_for_scu_app = ? )) "+          
						                        " ) "+
						 "ORDER BY prg_agg_ent, prg_det_ent";

			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, codForScuApp);
			pstmt.setString(2, codForScuApp);
						
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				VOFinanza voFinanza = new VOFinanza();
				voFinanza.setPrgAggEnt(rs.getString("PRG_AGG_ENT"));
				voFinanza.setDesAggEnt(rs.getString("DES_AGG_ENT"));
				voFinanza.setPrgDetEnt(rs.getString("PRG_DET_ENT"));
				voFinanza.setDesDetEnt(rs.getString("DES_DET_ENT"));
				
				voFinanza.setImpFnzGen(rs.getFloat("IMP_FNZ_GEN"));
				voFinanza.setImpRisRetApe(rs.getFloat("IMP_RIS_RET_APE"));
				voFinanza.setImpRisSupBre(rs.getFloat("IMP_RIS_SUP_BRE"));
				voFinanza.setImpSpePul(rs.getFloat("IMP_SPE_PUL"));
				voFinanza.setImpStiPerRuo(rs.getFloat("IMP_STI_PER_RUO"));
				voFinanza.setImpStiPerSup(rs.getFloat("IMP_STI_PER_SUP"));
				voFinanza.setPrcFnzGen(rs.getFloat("PRC_FNZ_GEN"));
				voFinanza.setPrcRisRetApe(rs.getFloat("PRC_RIS_RET_APE"));
				voFinanza.setPrcRisSupBre(rs.getFloat("PRC_RIS_SUP_BRE"));
				voFinanza.setPrcSpePul(rs.getFloat("PRC_SPE_PUL"));
				voFinanza.setPrcStiPerRuo(rs.getFloat("PRC_STI_PER_RUO"));
				voFinanza.setPrcStiPerSup(rs.getFloat("PRC_STI_PER_SUP"));
				
				voFinanza.setImpAmpOffFor(rs.getFloat("IMP_AMP_OFF_FOR"));
				voFinanza.setPrcAmpOffFor(rs.getFloat("PRC_AMP_OFF_FOR"));
				
				result.add(voFinanza);
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
}
