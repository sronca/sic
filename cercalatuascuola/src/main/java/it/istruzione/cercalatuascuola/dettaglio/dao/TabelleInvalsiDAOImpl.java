package it.istruzione.cercalatuascuola.dettaglio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTracciaInvalsi;
import org.apache.log4j.Logger;

public class TabelleInvalsiDAOImpl extends BaseDAO implements TabelleInvalsiDAO {
	Logger log = Logger.getLogger(TabelleInvalsiDAOImpl.class);

	public static final String NOME_TABELLA = "TRS1059_PUNTEGGI";
	
	/*
	 * Name                           Null     Type                                                                                                                                                                                          
	------------------------------ -------- --------------
	COD_SCU_UT                     NOT NULL CHAR(10)                                                                                                                                                                                      
	DAT_ANN_SCO_RIF                NOT NULL NUMBER(6)                                                                                                                                                                                     
	DES_MAT                        NOT NULL VARCHAR2(50)                                                                                                                                                                                  
	COD_TIP_IST                    NOT NULL NUMBER                                                                                                                                                                                        
	COD_LIV_CLA                    NOT NULL NUMBER                                                                                                                                                                                        
	DAT_ANN_SCO_RIL                         NUMBER(4)                                                                                                                                                                                     
	NUM_PUN_MED                             NUMBER(6,1)                                                                                                                                                                                   
	NUM_DIF_ESC                             NUMBER(6,1)                                                                                                                                                                                   
	DES_REG                                 VARCHAR2(50)                                                                                                                                                                                  
	NUM_PUN_REG                             NUMBER(6,1)                                                                                                                                                                                   
	COD_DIF_REG                             NUMBER                                                                                                                                                                                        
	DES_MAC_ARE                             VARCHAR2(50)                                                                                                                                                                                  
	NUM_PUN_MAC_ARE                         NUMBER(6,1)                                                                                                                                                                                   
	COD_DIFF_MAC_ARE                        NUMBER                                                                                                                                                                                        
	DES_NAZ                                 VARCHAR2(50)                                                                                                                                                                                  
	NUM_PUN_NAZ                             NUMBER(6,1)                                                                                                                                                                                   
	COD_DIF_NAZ                             NUMBER                                                                                                                                                                                        
	DAT_ORA_ULT_MOV                NOT NULL DATE                                                                                                                                                                                          
	COD_PGM_ULT_MOV                NOT NULL VARCHAR2(50)                                                                                                                                                                                  
	COD_UTE_ULT_MOV                NOT NULL VARCHAR2(50)   
	 */


    public TabelleInvalsiDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<VOTracciaInvalsi> getTabelleInvalsi(String $codMeccanografico, String $datAnnScoRil) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOTracciaInvalsi> listaTracce = new ArrayList<VOTracciaInvalsi>();
		try {
			StringBuilder strbQuery = new StringBuilder("SELECT ");
			strbQuery.append("COD_SCU_UT, ");
			strbQuery.append("DAT_ANN_SCO_RIF, ");
			strbQuery.append("DES_MAT, ");
			strbQuery.append("COD_TIP_IST, ");
			strbQuery.append("COD_LIV_CLA, ");
			strbQuery.append("DAT_ANN_SCO_RIL, ");
			strbQuery.append("NUM_PUN_MED, ");
			strbQuery.append("NUM_DIF_ESC, ");
			strbQuery.append("DES_REG, ");
			strbQuery.append("NUM_PUN_REG, ");
			strbQuery.append("COD_DIF_REG, ");
			strbQuery.append("DES_MAC_ARE, ");
			strbQuery.append("NUM_PUN_MAC_ARE, ");
			strbQuery.append("COD_DIFF_MAC_ARE, ");
			strbQuery.append("DES_NAZ, ");
			strbQuery.append("NUM_PUN_NAZ, ");
			strbQuery.append("COD_DIF_NAZ ");
			strbQuery.append("FROM ");
			strbQuery.append(NOME_TABELLA);
			strbQuery.append(" ");
			strbQuery.append("WHERE COD_SCU_UT = ? ");
			strbQuery.append("AND DAT_ANN_SCO_RIF = ? ");
			log.debug("getTabelleInvalsi query: " + strbQuery.toString());
			Object[] campi = new Object[]{$codMeccanografico, $datAnnScoRil};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER};
			conn = getConnection();
			ps = conn.prepareStatement(strbQuery.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			int counter = 0;
			while(rs.next()){
				log.debug("aggiungo traccia: " + counter++);
				VOTracciaInvalsi traccia = new VOTracciaInvalsi();
				traccia.setCodMeccanografico(rs.getString("COD_SCU_UT"));
				traccia.setAnnoScolasticoRiferimento(rs.getInt("DAT_ANN_SCO_RIF"));
				traccia.setMateriaDesc(rs.getString("DES_MAT"));
				traccia.setTipo(rs.getInt("COD_TIP_IST"));
				traccia.setLivello(rs.getInt("COD_LIV_CLA"));
				traccia.setAnnoScolasticoRilevazione(rs.getInt("DAT_ANN_SCO_RIL"));
				traccia.setMedia(rs.getFloat("NUM_PUN_MED"));
				traccia.setDiff(rs.getFloat("NUM_DIF_ESC"));
				traccia.setRegioneDesc(rs.getString("DES_REG"));
				traccia.setRegioneMedia(rs.getFloat("NUM_PUN_REG"));
				traccia.setRegioneIndicatore( rs.getInt("COD_DIF_REG")  );
				traccia.setZonaDesc(rs.getString("DES_MAC_ARE"));
				traccia.setZonaMedia(rs.getFloat("NUM_PUN_MAC_ARE"));
				traccia.setZonaIndicatore(rs.getInt("COD_DIFF_MAC_ARE"));
				traccia.setNazioneDesc(rs.getString("DES_NAZ"));
				traccia.setNazioneMedia(rs.getFloat("NUM_PUN_NAZ"));
				traccia.setNazioneIndicatore(rs.getInt("COD_DIF_NAZ"));
				listaTracce.add(traccia);
			}
			if(!listaTracce.isEmpty()){
				Collections.sort(listaTracce);
			}
			return listaTracce;
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
	
	
	public VOGrafico getTitoloGrafici(String codScheda, String codSezione, String codGra) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String out = "";
		
		VOGrafico  titGra = new VOGrafico();
		
		try {
			String sql = "SELECT DES_TIT_GRA, DES_INF_GRA "+ 
						  " FROM TRS1033_TIPOGRAFICO "+ 
						 " WHERE COD_SCH = ? "+ 
						   " AND COD_SEZ = ? "+ 
						   " AND COD_GRA = ? ";
							
			Object[] campi = new Object[]{codScheda,codSezione,codGra};
			int[] tipi = new int[]{Types.INTEGER,Types.INTEGER,Types.INTEGER};
			
			log.debug("getNumeroAlunni query: " + sql);
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			if(rs.next()) {
				titGra.setDesTitGra(rs.getString(1));
				titGra.setDesInfGra(rs.getString(2));
				
			}
						
			return titGra;
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
