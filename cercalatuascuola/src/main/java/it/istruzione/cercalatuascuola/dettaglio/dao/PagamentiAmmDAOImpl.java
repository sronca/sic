package it.istruzione.cercalatuascuola.dettaglio.dao;


import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnnoWrapper;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOIndiceDiTempestivitaPagamenti;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PagamentiAmmDAOImpl extends BaseDAO implements PagamentiAmmDAO {

	public PagamentiAmmDAOImpl(DataSource dataSource) {
		super(dataSource);
	}
		
	public List<VOAnnoWrapper> getListaAnniSco(String cod_for_scu_app) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOAnnoWrapper> result = new ArrayList<VOAnnoWrapper>();
		
		try {
//			String query =  " SELECT DISTINCT DAT_ANN_SCO_RIL "
//						  + " FROM MFG1002_ANAGISTSCOL "
//						  + " WHERE COD_FOR_SCU_APP = (SELECT DISTINCT COD_FOR_SCU_APP FROM MFG1002_ANAGISTSCOL WHERE COD_SCU_UT = ? AND DAT_ANN_SCO_RIL = ? ) "
//						  + " ORDER BY DAT_ANN_SCO_RIL";
			
			String query =  "SELECT	DISTINCT  A.DAT_ANN_SCO_PER "
							+"FROM  "
							+"TRS1066_INDICETEMPESTIVITA A, "
							+"MFG1002_ANAGISTSCOL B "
							+"WHERE "
							+"A.COD_MEC_IST_PRI = B.COD_SCU_UT_PRI "
							+"AND A.DAT_ANN_SCO_PER = B.DAT_ANN_SCO_RIL "
							+"AND B.COD_FOR_SCU_APP = ? "
							+"ORDER BY DAT_ANN_SCO_PER DESC ";
			
			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cod_for_scu_app);
//			pstmt.setInt(2, Integer.valueOf(dat_ann_sco_ril).intValue());
						
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				VOAnnoWrapper vo = new VOAnnoWrapper();
//				vo.setAnno(rs.getInt("DAT_ANN_SCO_RIL"));	
				vo.setAnno(rs.getInt("DAT_ANN_SCO_PER"));	
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
		return result;
	}	


	public List<VOIndiceDiTempestivitaPagamenti> getListaIndicatori(String cod_scu_ut,
                                                                    String dat_ann_sco_ril,
                                                                    String annoScolasticoDiRiferimento,
                                                                    String annoFinanziarioDiRiferimento) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VOIndiceDiTempestivitaPagamenti> result = new ArrayList<VOIndiceDiTempestivitaPagamenti>();
		
		try {

			String query = " SELECT " +		 
						   " A.COD_MEC_IST_PRI, A.DAT_ANN_SCO_PER, A.DAT_ANN_FIN, " +
						   " A.COD_PER_RIF, A.NUM_VAL_IND_TEM, TO_CHAR(A.DAT_ORA_ULT_MOV, 'DD/MM/YYYY HH24:MI:SS')  " +
						   " FROM " +
						   " TRS1066_INDICETEMPESTIVITA A, " +
						   " MFG1002_ANAGISTSCOL B " +
						   " WHERE " +
						   " A.COD_MEC_IST_PRI = B.COD_SCU_UT_PRI " +
						   " AND A.DAT_ANN_SCO_PER = B.DAT_ANN_SCO_RIL " +
						   " AND B.COD_FOR_SCU_APP = (SELECT DISTINCT COD_FOR_SCU_APP FROM MFG1002_ANAGISTSCOL WHERE COD_SCU_UT = ? AND DAT_ANN_SCO_RIL = ? ) " +
						   " AND A.DAT_ANN_SCO_PER = ? " +
						   " AND A.DAT_ANN_FIN = ? " +
						   " ORDER BY DAT_ANN_SCO_PER DESC, DAT_ANN_FIN DESC, COD_PER_RIF ";

			conn = this.getConnection(); 
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cod_scu_ut);
			pstmt.setInt(2, Integer.valueOf(dat_ann_sco_ril).intValue());
			pstmt.setInt(3, Integer.valueOf(annoScolasticoDiRiferimento).intValue());
			pstmt.setInt(4, Integer.valueOf(annoFinanziarioDiRiferimento).intValue());
						
			rs = pstmt.executeQuery();
			
			NumberFormat numberFormatter = DecimalFormat.getInstance(Locale.ITALY);
			numberFormatter.setMinimumFractionDigits(2);
			numberFormatter.setMaximumFractionDigits(2);
			
			while(rs.next()) {
				VOIndiceDiTempestivitaPagamenti vo = new VOIndiceDiTempestivitaPagamenti();
				vo.setCodiceMeccanografico(rs.getString("COD_MEC_IST_PRI"));
				vo.setDatAnnScoRil(rs.getInt("DAT_ANN_SCO_PER"));
				vo.setAnnoFinanz(rs.getInt("DAT_ANN_FIN"));
				vo.setCodPrRif(rs.getInt("COD_PER_RIF"));
				vo.setNumValIndTem(rs.getDouble("NUM_VAL_IND_TEM"));
				vo.setNumValIndTemStr(numberFormatter.format(vo.getNumValIndTem()));
				vo.setDataUltMod(rs.getString(6));
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
		return result;
	}	

	
}
