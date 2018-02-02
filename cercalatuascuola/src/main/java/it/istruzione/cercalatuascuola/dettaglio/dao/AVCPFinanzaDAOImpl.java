package it.istruzione.cercalatuascuola.dettaglio.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAnnoWrapper;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VODocumentoAVCP;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

public class AVCPFinanzaDAOImpl extends BaseDAO implements AVCPFinanzaDAO {
	Logger log = Logger.getLogger(AVCPFinanzaDAOImpl.class);

	/*
	 * 	tabella: 'trs1060_grafscuola'
	 * 	COD_SCU_UT                     NOT NULL CHAR(10)                                                                                                                                                                                      
	   	DAT_ANN_SCO_RIF                NOT NULL NUMBER(6)                                                                                                                                                                                     
		DAT_ANN_BIL_RIF                NOT NULL NUMBER(4)                                                                                                                                                                                     
		DES_NOM_FIL                             VARCHAR2(50)                                                                                                                                                                                  
		OGG_XML_AVCP                    		BLOB()                                                                                                                                                                                        
		DAT_ORA_ULT_MOV                NOT NULL DATE                                                                                                                                                                                          
		COD_PGM_ULT_MOV                NOT NULL VARCHAR2(50)                                                                                                                                                                                  
		COD_UTE_ULT_MOV                NOT NULL VARCHAR2(50)  
	 */

	public AVCPFinanzaDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	private static final String NOME_TABELLA = "TRS1061_GESCONTPUBL";
	
	private static final String CAMPO_COD_SCU_UT = "COD_SCU_UT";
	
	private static final String CAMPO_DAT_ANN_SCO_RIF = "DAT_ANN_SCO_RIF";
	
	private static final String CAMPO_DAT_ANN_BIL_RIF = "DAT_ANN_RIF_BIL";
	
	private static final String CAMPO_DES_NOM_FIL = "DES_NOM_FIL";
	
	private static final String CAMPO_OGG_XML_AVCP = "OGG_CON_PUB";
	
	private static final String CAMPO_DAT_ORA_ULT_MOV = "DAT_ORA_ULT_MOV";
	
	private String getSelectStatement(){
		StringBuilder strbSqlSelect = new StringBuilder();
		strbSqlSelect.append("SELECT ");
		strbSqlSelect.append(CAMPO_COD_SCU_UT + ", ");
		strbSqlSelect.append(CAMPO_DAT_ANN_SCO_RIF + ", ");
		strbSqlSelect.append(CAMPO_DAT_ANN_BIL_RIF + ", ");
		strbSqlSelect.append(CAMPO_DES_NOM_FIL + ", ");
		strbSqlSelect.append(CAMPO_OGG_XML_AVCP + ", ");
		strbSqlSelect.append(CAMPO_DAT_ORA_ULT_MOV + " ");
		strbSqlSelect.append("from ");
		strbSqlSelect.append(NOME_TABELLA + " ");
		strbSqlSelect.append("WHERE ");
		strbSqlSelect.append(CAMPO_COD_SCU_UT + " = ? ");
		strbSqlSelect.append("AND " + CAMPO_DAT_ANN_SCO_RIF + " = ? ");
		strbSqlSelect.append("AND " + CAMPO_DAT_ANN_BIL_RIF + " = ? ");
		return strbSqlSelect.toString();
	}
	
	private String getSelectAnniScolasticiStatement(){
		StringBuilder strbSqlSelect = new StringBuilder();
		strbSqlSelect.append("SELECT ");
		strbSqlSelect.append("DISTINCT ");
		strbSqlSelect.append(CAMPO_DAT_ANN_SCO_RIF + " ");
		strbSqlSelect.append("from ");
		strbSqlSelect.append(NOME_TABELLA + " ");
		strbSqlSelect.append("WHERE ");
		strbSqlSelect.append(CAMPO_COD_SCU_UT + " = ? ");
		return strbSqlSelect.toString();
	}
	
	private String getSelectAnniBilancioStatement(){
		StringBuilder strbSqlSelect = new StringBuilder();
		strbSqlSelect.append("SELECT ");
		strbSqlSelect.append(CAMPO_DAT_ANN_BIL_RIF + " ");
		strbSqlSelect.append("from ");
		strbSqlSelect.append(NOME_TABELLA + " ");
		strbSqlSelect.append("WHERE ");
		strbSqlSelect.append(CAMPO_COD_SCU_UT + " = ? ");
		strbSqlSelect.append("AND " + CAMPO_DAT_ANN_SCO_RIF + " = ? ");
		return strbSqlSelect.toString();
	}

	public List<VOAnnoWrapper> getAnniScolastici(String $codiceMecc){
		List<VOAnnoWrapper> listaAnniScolastici = new ArrayList<VOAnnoWrapper>();
		if($codiceMecc == null || $codiceMecc.trim().length() != 10){
			log.error("codice meccanografico: '" + $codiceMecc + "' non valido");
			return listaAnniScolastici;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(getSelectAnniScolasticiStatement());
			log.debug("getAnniScolastici query: " + getSelectAnniScolasticiStatement());
			pstmt.setString(1, $codiceMecc);
			log.debug("getAnniScolastici param 1: " + $codiceMecc);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				listaAnniScolastici.add( new VOAnnoWrapper(rs.getInt(CAMPO_DAT_ANN_SCO_RIF) ) );
				log.debug("trovato anno : " + rs.getInt(CAMPO_DAT_ANN_SCO_RIF));
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		finally {
			if(rs != null) {
				try {
					rs.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs = null;
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt = null;
			}						
			if(conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = null;
			}
		}
		return listaAnniScolastici;
	}
			
	public List<VOAnnoWrapper> getAnniBilancio(String $codiceMecc, Integer $annoScolastico){
		List<VOAnnoWrapper> listaAnniBilancio = new ArrayList<VOAnnoWrapper>();
		if($codiceMecc == null || $codiceMecc.trim().length() != 10){
			log.error("codice meccanografico: '" + $codiceMecc + "' non valido");
			return listaAnniBilancio;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(getSelectAnniBilancioStatement());
			log.debug("getAnniBilancio query: " + getSelectAnniBilancioStatement());
			pstmt.setString(1, $codiceMecc);
			log.debug("getAnniBilancio param 1: " + $codiceMecc);
			pstmt.setInt(2, $annoScolastico);
			log.debug("getAnniBilancio param 2: " + $annoScolastico);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				listaAnniBilancio.add( new VOAnnoWrapper(rs.getInt(CAMPO_DAT_ANN_BIL_RIF) ) );
				log.debug("trovato anno : " + rs.getInt(CAMPO_DAT_ANN_BIL_RIF));
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if(rs != null) {
				try {
					rs.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs = null;
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt = null;
			}						
			if(conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = null;
			}
		}
		return listaAnniBilancio;
	}		
			
			
	
	public VODocumentoAVCP getAVCP(String $codiceMecc, Integer $annoScolastico, Integer $annoBilancio){
		VODocumentoAVCP retDoc = null;
		if($codiceMecc == null || $codiceMecc.trim().length() != 10){
			log.error("codice meccanografico: '" + $codiceMecc + "' non valido");
			return retDoc;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection(); 
			pstmt = conn.prepareStatement(getSelectStatement());
			log.debug("getAVCP query: " + getSelectStatement());
			pstmt.setString(1, $codiceMecc);
			log.debug("getAVCP param 1: " + $codiceMecc);
			pstmt.setInt(2, $annoScolastico);
			log.debug("getAVCP param 2: " + $annoScolastico);
			pstmt.setInt(3, $annoBilancio);
			log.debug("getAVCP param 3: " + $annoBilancio);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				log.debug("trovato doc ");
				retDoc = new VODocumentoAVCP();
				retDoc.setCodiceMeccanografico(rs.getString(CAMPO_COD_SCU_UT));
				retDoc.setAnnoScolastico(rs.getInt(CAMPO_DAT_ANN_SCO_RIF));
				retDoc.setAnnoBilancio(rs.getInt(CAMPO_DAT_ANN_BIL_RIF));
				retDoc.setNomeFile(rs.getString(CAMPO_DES_NOM_FIL));
				Blob documentoXml = rs.getBlob(CAMPO_OGG_XML_AVCP);
				retDoc.setFile( documentoXml.getBytes(1,(int)documentoXml.length()) );
				retDoc.setDataUltimaModifica(rs.getDate(CAMPO_DAT_ORA_ULT_MOV));
			}
			else{
				log.warn("doc non trovato");
			}	
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		finally {
			if(rs != null) {
				try {
					rs.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs = null;
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt = null;
			}						
			if(conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = null;
			}
		}
		return retDoc;
	}
	
}
