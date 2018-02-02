package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VODocumento;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipoDocumento;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DocumentiDAOImpl extends BaseDAO implements DocumentiDAO {
	Logger log = Logger.getLogger(DocumentiDAOImpl.class);
	
	public DocumentiDAOImpl(DataSource dataSource) throws SQLException, NamingException {
		super(dataSource);
	}
	
	public List<VOTipoDocumento> getTipologieDocumento(String codScuUt, String datAnnScoRil, String flgBinFile)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOTipoDocumento> tipologieDocumento = new ArrayList<VOTipoDocumento>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT TD.COD_TIP_FIL, TD.DES_TIP_FIL, TD.DES_EXT_FIL,")
				.append("TD.NUM_MAX_SIZ, TD.FLG_BIN_FIL, TD.FLG_MUL_FIL ")
				.append("FROM TRS1011_TIPODOCU TD ")
				.append("WHERE FLG_BIN_FIL = ? ")
				.append("AND NOT EXISTS (SELECT 'X' ")
				.append("FROM TRS1012_DOCUSCUOLA ")
				.append("WHERE COD_SCU_UT = ? ")
				.append("AND DAT_ANN_SCO_RIL = ? ")
				.append("AND COD_TIP_FIL = TD.COD_TIP_FIL ")
				.append("AND TD.FLG_MUL_FIL = '1') ")
				.append("ORDER BY TD.COD_TIP_FIL");
			
			Object[] campi = new Object[]{flgBinFile, codScuUt, datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
			
			log.debug("getTipologieDocumento query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOTipoDocumento voTipoDoc = null;
			while (rs.next()) {
				voTipoDoc = new VOTipoDocumento();
				voTipoDoc.setCodTipFil(rs.getString("COD_TIP_FIL"));
				voTipoDoc.setDesExtFil(rs.getString("DES_EXT_FIL"));
				voTipoDoc.setDesTipFil(rs.getString("DES_TIP_FIL"));
				voTipoDoc.setFlgBinFil(rs.getString("FLG_BIN_FIL"));
				voTipoDoc.setFlgMulFil(rs.getString("FLG_MUL_FIL"));
				voTipoDoc.setNumMaxSiz(rs.getString("NUM_MAX_SIZ"));
				
				tipologieDocumento.add(voTipoDoc);
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
		
		return tipologieDocumento;
	}
	
	public VOTipoDocumento getTipologiaDocumento(String codTipFile)
	throws Exception {
					
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
				
		try {
			StringBuffer query = new StringBuffer("SELECT TD.COD_TIP_FIL, TD.DES_TIP_FIL, TD.DES_EXT_FIL,")
				.append("TD.NUM_MAX_SIZ, TD.FLG_BIN_FIL, TD.FLG_MUL_FIL ")
				.append("FROM TRS1011_TIPODOCU TD ")
				.append("WHERE COD_TIP_FIL = ? ");
			
			Object[] campi = new Object[]{codTipFile};
			int[] tipi = new int[]{Types.VARCHAR};
			
			log.debug("getTipologiaDocumento query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOTipoDocumento voTipoDoc = null;
			while (rs.next()) {
				voTipoDoc = new VOTipoDocumento();
				voTipoDoc.setCodTipFil(rs.getString("COD_TIP_FIL"));
				voTipoDoc.setDesExtFil(rs.getString("DES_EXT_FIL"));
				voTipoDoc.setDesTipFil(rs.getString("DES_TIP_FIL"));
				voTipoDoc.setFlgBinFil(rs.getString("FLG_BIN_FIL"));
				voTipoDoc.setFlgMulFil(rs.getString("FLG_MUL_FIL"));
				voTipoDoc.setNumMaxSiz(rs.getString("NUM_MAX_SIZ"));
			}
			
			return voTipoDoc;
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
	
	public List<VODocumento> getDocumentiScuola(String codScuUt, String datAnnScoRil,
												String flgBinFile)
	throws Exception {
					
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VODocumento> documentiScuola = new ArrayList<VODocumento>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT D.COD_SCU_UT, D.DAT_ANN_SCO_RIL, D.COD_TIP_FIL,")
				.append("D.PRG_DOC, D.COD_SCU_UT_PRI, D.OGG_FIL, D.DES_NOT, D.DES_NOM_FIL, D.DES_EXT_FIL,")
				.append("D.DES_STA_PUB, TD.DES_TIP_FIL ")
				.append("FROM TRS1012_DOCUSCUOLA D, TRS1011_TIPODOCU TD ")
				.append("WHERE D.COD_TIP_FIL = TD.COD_TIP_FIL ")				
				.append("AND D.COD_SCU_UT = ? ")
				.append("AND D.DAT_ANN_SCO_RIL = ? ")
				.append("AND TD.FLG_BIN_FIL = ? ")
				.append("ORDER BY D.COD_TIP_FIL, D.PRG_DOC");
			
			Object[] campi = new Object[]{codScuUt, datAnnScoRil, flgBinFile};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
			
			log.debug("getDocumentiScuola query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VODocumento voDoc = null;
			while (rs.next()) {
				voDoc = new VODocumento();
				voDoc.setCodScuUt(rs.getString("COD_SCU_UT"));
				voDoc.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				voDoc.setCodTipFil(rs.getString("COD_TIP_FIL"));				
				voDoc.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voDoc.setDesExtFil(rs.getString("DES_EXT_FIL"));
				voDoc.setDesNomFil(rs.getString("DES_NOM_FIL"));
				voDoc.setDesNot(rs.getString("DES_NOT"));
				voDoc.setDesStaPub(rs.getString("DES_STA_PUB"));
				voDoc.setDesTipFil(rs.getString("DES_TIP_FIL"));
				voDoc.setPrgDoc(rs.getString("PRG_DOC"));			
								
				documentiScuola.add(voDoc);
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
		
		return documentiScuola;
	}
	
	public VODocumento getPTOFScuola(String codScuUt, String datAnnScoRil, String flgBinFile)
	throws Exception {
					
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		VODocumento pof = null;
		
		try {
			StringBuffer query = new StringBuffer("SELECT D.COD_SCU_UT, D.DAT_ANN_SCO_RIL, D.COD_TIP_FIL,")
				.append("D.PRG_DOC, D.COD_SCU_UT_PRI, D.OGG_FIL, D.DES_NOT, D.DES_NOM_FIL, D.DES_EXT_FIL,")
				.append("D.DES_STA_PUB, TD.DES_TIP_FIL ")
				.append("FROM TRS1012_DOCUSCUOLA D, TRS1011_TIPODOCU TD ")
				.append("WHERE D.COD_TIP_FIL = TD.COD_TIP_FIL ")				
				.append("AND D.COD_SCU_UT = ? ")
				.append("AND D.DAT_ANN_SCO_RIL = ? ")
				.append("AND TD.FLG_BIN_FIL = ? ")
				.append("AND TD.COD_TIP_FIL = 14 ");
			
			Object[] campi = new Object[]{codScuUt, datAnnScoRil, flgBinFile};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
			
			log.debug("getPOFScuola query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			if (rs.next()) {
				pof = new VODocumento();
				pof.setCodScuUt(rs.getString("COD_SCU_UT"));
				pof.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				pof.setCodTipFil(rs.getString("COD_TIP_FIL"));				
				pof.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				pof.setDesExtFil(rs.getString("DES_EXT_FIL"));
				pof.setDesNomFil(rs.getString("DES_NOM_FIL"));
				pof.setDesNot(rs.getString("DES_NOT"));
				pof.setDesStaPub(rs.getString("DES_STA_PUB"));
				pof.setDesTipFil(rs.getString("DES_TIP_FIL"));
				pof.setPrgDoc(rs.getString("PRG_DOC"));			
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
			
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
		
		return pof;
	}

	public VODocumento getDocumentoScuola(String codScuUt, String datAnnScoRil,
										  String codTipFil, String prgDoc)
	throws Exception {
					
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		
		Object[] campi = null;
		int[] tipi = null;
		
		try {
			StringBuffer query = new StringBuffer("SELECT D.COD_SCU_UT, D.DAT_ANN_SCO_RIL, D.COD_TIP_FIL,")
				.append("D.PRG_DOC, D.COD_SCU_UT_PRI, D.OGG_FIL, D.DES_NOT, D.DES_NOM_FIL, D.DES_EXT_FIL,")
				.append("D.DES_STA_PUB, TD.DES_TIP_FIL ")
				.append("FROM TRS1012_DOCUSCUOLA D, TRS1011_TIPODOCU TD ")
				.append("WHERE D.COD_TIP_FIL = TD.COD_TIP_FIL ")				
				.append("AND D.COD_SCU_UT = ? ")
				.append("AND D.DAT_ANN_SCO_RIL = ? ")
				.append("AND D.COD_TIP_FIL = ? ");
						
			if(prgDoc != null) {
				query.append("AND D.PRG_DOC = ?");
				
				campi = new Object[]{codScuUt, datAnnScoRil, codTipFil, prgDoc};
				tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER};
			}	
			else {
				query.append("ORDER BY D.PRG_DOC DESC");
				
				campi = new Object[]{codScuUt, datAnnScoRil, codTipFil};
				tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.INTEGER};
			}
						
			log.debug("getDocumentoScuola query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VODocumento voDoc = null;
			if(rs.next()) {
				voDoc = new VODocumento();
				voDoc.setCodScuUt(rs.getString("COD_SCU_UT"));
				voDoc.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				voDoc.setCodTipFil(rs.getString("COD_TIP_FIL"));				
				voDoc.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voDoc.setDesExtFil(rs.getString("DES_EXT_FIL"));
				voDoc.setDesNomFil(rs.getString("DES_NOM_FIL"));
				voDoc.setDesNot(rs.getString("DES_NOT"));
				voDoc.setDesStaPub(rs.getString("DES_STA_PUB"));
				voDoc.setDesTipFil(rs.getString("DES_TIP_FIL"));
				voDoc.setPrgDoc(rs.getString("PRG_DOC"));			
				
				Blob blob = rs.getBlob("OGG_FIL");
				
				if(blob != null) {
					voDoc.setOggFil(blob.getBinaryStream());
					voDoc.setFileSize((int)blob.length());
				}
			}
			
			return voDoc;
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

	public int countDocumentoScuola(String codScuUt, String datAnnScoRil,
										  String codTipFil, String prgDoc)
	throws Exception {
					
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		
		Object[] campi = null;
		int[] tipi = null;
		
		try {
			StringBuffer query = new StringBuffer("SELECT count(*) ")
				.append("FROM TRS1012_DOCUSCUOLA D, TRS1011_TIPODOCU TD ")
				.append("WHERE D.COD_TIP_FIL = TD.COD_TIP_FIL ")				
				.append("AND D.COD_SCU_UT = ? ")
				.append("AND D.DAT_ANN_SCO_RIL = ? ")
				.append("AND D.COD_TIP_FIL = ? ");
						
			if(prgDoc != null) {
				query.append("AND D.PRG_DOC = ?");
				
				campi = new Object[]{codScuUt, datAnnScoRil, codTipFil, prgDoc};
				tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER};
			}	
			else {
				query.append("ORDER BY D.PRG_DOC DESC");
				
				campi = new Object[]{codScuUt, datAnnScoRil, codTipFil};
				tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.INTEGER};
			}
						
			log.debug("getDocumentoScuola query: " + query.toString());
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			int count = 0;
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			return count;
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
	
	
	
	public VODocumento getBlobLibro(String idLibro)	throws Exception {
					
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		VODocumento voDoc = null;
		
		try {
			
			//String[] tokens = idLibro.split("-");
			
			//String codUt = tokens[0];
			//String annoRif = tokens[1];
			//String classe = tokens[2];
			//String sezione = tokens[3];
			//String prgIdeDoc = tokens[4];
			
			StringTokenizer st = new StringTokenizer(idLibro, Constants.TOKENIZER);
			String codUt = st.nextToken();
			String annoRif = st.nextToken();
			String classe = st.nextToken();
			String sezione = st.nextToken();
			String prgIdeDoc = st.nextToken();
			
			String sql = " SELECT OGG_DOC_LTE, DES_NOM_FIL " +
				         " FROM TRS1041_LIBRITESTO " +
				         " WHERE COD_SCU_UT = ? AND DAT_ANN_SCO_RIL = ? " +
				         " AND COD_CLA = ? AND COD_SEZ = ? AND PRG_IDE_DOC = ? " ;

			log.debug("sql=" + sql);
			log.debug("codMecc="+codUt);
			log.debug("annoRif="+annoRif);
			log.debug("classe="+classe);
			log.debug("sezione="+sezione);
			log.debug("prgIdeDoc="+prgIdeDoc);
			
			conn = getConnection();
			ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, codUt);
			ps.setInt(2, new Integer(annoRif));
			ps.setInt(3, new Integer(classe));
			ps.setString(4, sezione);
			ps.setString(5, prgIdeDoc);

			rs = ps.executeQuery();
			
			if (rs.next()){
				log.debug("found");
				voDoc = new VODocumento();
				voDoc.setDesNomFil(rs.getString("DES_NOM_FIL"));
				Blob blob = rs.getBlob("OGG_DOC_LTE");
				if(blob != null) {
					voDoc.setOggFil(blob.getBinaryStream());
					voDoc.setFileSize((int)blob.length());
				}
				
			}
			
			return voDoc;
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
			log.debug("FINE getVoLibro");
		}
		
				
	}

	
	
}
