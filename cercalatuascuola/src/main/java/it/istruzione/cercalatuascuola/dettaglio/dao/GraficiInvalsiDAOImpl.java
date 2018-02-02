package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGraficoInvalsi;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraficiInvalsiDAOImpl extends BaseDAO implements GraficiInvalsiDAO {
    Logger log = Logger.getLogger(GraficiInvalsiDAOImpl.class);

	public static final String NOME_TABELLA = "TRS1060_GRAFSCUOLA";
	
	/*
	 * Name                           Null     Type                                                                                                                                                                                          
	------------------------------ -------- ------------------- 
	COD_SCU_UT                     NOT NULL CHAR(10)                                                                                                                                                                                      
	DAT_ANN_SCO_RIF                NOT NULL NUMBER(6)                                                                                                                                                                                     
	DES_MAT                      NOT NULL VARCHAR2(50)                                                                                                                                                                                  
	DAT_ANN_SCO_RIL                         NUMBER(4)                                                                                                                                                                                     
	DES_NOM_FIL                             VARCHAR2(50)                                                                                                                                                                                  
	OGG_GRA_SCU                             BLOB()                                                                                                                                                                                        
	DAT_ORA_ULT_MOV                NOT NULL DATE                                                                                                                                                                                          
	COD_PGM_ULT_MOV                NOT NULL VARCHAR2(50)                                                                                                                                                                                  
	COD_UTE_ULT_MOV                NOT NULL VARCHAR2(50)    
	 */

	public GraficiInvalsiDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	public byte[] getGraficoInvalsi(String $codMeccanografico, String $datAnnScoRil, String $materia) throws Exception {
		log.debug("in getGraficoInvalsiInfo('" + $codMeccanografico + "','" + $datAnnScoRil + "','" + $materia+ "')...");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		byte[] img = null;
		try {
			StringBuffer strbQuery = new StringBuffer("SELECT ");
			strbQuery.append("OGG_GRA_SCU ");
			strbQuery.append("FROM ");
			strbQuery.append(NOME_TABELLA);
			strbQuery.append(" ");
			strbQuery.append("WHERE ");
			strbQuery.append("COD_SCU_UT = ? ");
			strbQuery.append("AND DAT_ANN_SCO_RIF = ? ");
			strbQuery.append("AND DES_MAT = ? ");
			log.debug("getGraficoInvalsi query: " + strbQuery.toString());
			Object[] campi = new Object[]{$codMeccanografico, $datAnnScoRil, $materia};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
			conn = getConnection();
			ps = conn.prepareStatement(strbQuery.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			while(rs.next()){
				Blob imgBlob = rs.getBlob("OGG_GRA_SCU");
				img = imgBlob.getBytes(1,(int)imgBlob.length()); 
				break;
			}
			return img;
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
	
	public List<VOGraficoInvalsi> getGraficoInvalsiInfo(String $codMeccanografico, String $datAnnScoRil) throws Exception {
		log.debug("in getGraficoInvalsiInfo('" + $codMeccanografico + "','" + $datAnnScoRil + "')...");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOGraficoInvalsi> list = new ArrayList<VOGraficoInvalsi>();
		try {
			StringBuffer strbQuery = new StringBuffer("SELECT ");
			strbQuery.append("COD_SCU_UT,");
			strbQuery.append("DAT_ANN_SCO_RIF,");
			strbQuery.append("DES_MAT,");
			strbQuery.append("DAT_ANN_SCO_RIL,");
			strbQuery.append("DES_NOM_FIL ");
			strbQuery.append("FROM ");
			strbQuery.append(NOME_TABELLA);
			strbQuery.append(" ");
			strbQuery.append("WHERE ");
			strbQuery.append("COD_SCU_UT = ? ");
			strbQuery.append("AND DAT_ANN_SCO_RIF = ? ");
			log.debug("getGraficoInvalsi query: " + strbQuery.toString());
			Object[] campi = new Object[]{$codMeccanografico, $datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			conn = getConnection();
			ps = conn.prepareStatement(strbQuery.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			while(rs.next()){
				VOGraficoInvalsi gInvalsiInfo = new VOGraficoInvalsi();
				gInvalsiInfo.setCodMeccanografico(rs.getString("COD_SCU_UT"));
				gInvalsiInfo.setAnnoScolasticoRiferimento(rs.getInt("DAT_ANN_SCO_RIF"));
				gInvalsiInfo.setMateriaDesc(rs.getString("DES_MAT"));
				gInvalsiInfo.setAnnoScolasticoRilevazione(rs.getInt("DAT_ANN_SCO_RIL"));
				gInvalsiInfo.setNomeFile(rs.getString("DES_NOM_FIL"));
				list.add(gInvalsiInfo);
			}
			if(!list.isEmpty()){
				Collections.sort(list);
			}
			return list;
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
