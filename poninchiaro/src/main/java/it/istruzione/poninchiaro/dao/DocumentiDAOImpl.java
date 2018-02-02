package it.istruzione.poninchiaro.dao;

import it.istruzione.poninchiaro.common.dao.BaseDAO;
import it.istruzione.poninchiaro.model.VODocumento;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DocumentiDAOImpl extends BaseDAO implements DocumentiDAO
{
	private static Logger log = Logger.getLogger(DocumentiDAOImpl.class);
	
	public DocumentiDAOImpl(DataSource dataSource) throws SQLException, NamingException
	{
		super(dataSource);
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

	
	
	
}

