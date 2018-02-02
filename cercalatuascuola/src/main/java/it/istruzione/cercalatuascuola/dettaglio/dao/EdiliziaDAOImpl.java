package it.istruzione.cercalatuascuola.dettaglio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;



import javax.naming.NamingException;
import javax.sql.DataSource;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.*;

import org.apache.log4j.Logger;

public class EdiliziaDAOImpl extends BaseDAO implements EdiliziaDAO {
	
	Logger log = Logger.getLogger(EdiliziaDAOImpl.class);
	
	public EdiliziaDAOImpl(DataSource dataSource) throws SQLException, NamingException {
		super(dataSource);
	}	

	private List<VOSezione> getSezioni() throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<VOSezione> out = new ArrayList<VOSezione>();
		
		
		try {
			String sql = " SELECT COD_SEZ, DES_SEZ, FLG_VIS_SEZ FROM TRS1064_TIPOLOGICASEZIONE ";
							
			Object[] campi = new Object[]{};
			int[] tipi = new int[]{};
			
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			while(rs.next()) {
				VOSezione vo = new VOSezione();
				vo.setCodice(rs.getString(1));
				vo.setDescrizione(rs.getString(2));
				vo.setAbilitato(rs.getInt(3));
				
				out.add(vo);
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
		
		return out;
	}

	private List<VOCampo> getCampiPerSezione(String codSezione) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<VOCampo> out = new ArrayList<VOCampo>();
		
		
		try {
			String sql =  " SELECT B.COD_CAM, B.DES_CAM FROM TRS1065_ASSOCIACAMPOSEZIONE A, TRS1063_TIPOLOGICACAMPO B "
						+ " WHERE "
						+ " A.COD_CAM = B.COD_CAM "
						+ " AND A.COD_SEZ = ? ORDER BY B.COD_CAM";
							
			Object[] campi = new Object[]{codSezione};
			int[] tipi = new int[]{Types.VARCHAR};
			
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			while(rs.next()) {
				VOCampo vo = new VOCampo();
				vo.setCodice(rs.getString(1));
				vo.setDescrizione(rs.getString(2));
				
				out.add(vo);
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
		
		return out;
	}

	private List<VOEdificio> getEdificiCodiceScuola(String codiceMeccanograficoScuola) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<VOEdificio> edifici = new ArrayList<VOEdificio>();
		
		
		try {
			String sql =  " SELECT DISTINCT A.COD_EDI, C.A006 || ', ' || C.A008 || ', ' || C.A001 || ' (' || C.A002 || '), Italia ' INDIRIZZO_MARKER,"
					+ " C.A006 || ', ' || C.A008 || ', ' || C.A001 || ' (' || C.A003 || ') ' INDIRIZZO,"
					+ " C.A010 LATITUDINE, C.A011 LONGITUDINE, MAX(B.DAT_ANN_SCO_RIL) "
					+ " FROM VED1007_ASSOCSCUEDIFICI A, MFG1002_ANAGISTSCOL B, VED1006_ANAGEDIFICI C "
					+ " WHERE "
					+ " A.PER_ANN_SCO = B.DAT_ANN_SCO_RIL "
					+ " AND A.COD_SCU_UT = B.COD_SCU_UT "
					+ " AND A.COD_EDI = C.COD_EDI "
					+ " AND (B.COD_SCU_UT_PRI = ? or B.COD_SCU_UT = ? )"
					+ " GROUP BY A.COD_EDI, C.A006 || ', ' || C.A008 || ', ' || C.A001 || ' (' || C.A002 || '), Italia ', "
					+ "  C.A006 || ', ' || C.A008 || ', ' || C.A001 || ' (' || C.A003 || ') ', C.A010, C.A011 ";
							
			Object[] campi = new Object[]{codiceMeccanograficoScuola, codiceMeccanograficoScuola};
			int[] tipi = new int[]{Types.VARCHAR,Types.VARCHAR};
			
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			while(rs.next()) {
				VOEdificio vo = new VOEdificio();
				vo.setCodiceEdificio(rs.getString(1));
				vo.setIndirizzoMarker(rs.getString(2));
				vo.setIndirizzo(rs.getString(3));
				vo.setLatitudine(rs.getString(4));
				vo.setLongitudine(rs.getString(5));
				vo.setDatAnnScoRil(rs.getString(6));
				edifici.add(vo);
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
		
		return edifici;
	}
	
	public String getMaxAnnoSco() throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String maxAnnoSco = null;
		
		try {
			String sql = " SELECT MAX(PER_ANN_SCO) FROM VED1007_ASSOCSCUEDIFICI ";
			
			Object[] campi = new Object[]{};
			int[] tipi = new int[]{};
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			if(rs.next()) {
				maxAnnoSco = rs.getString(1);
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
		
		return maxAnnoSco;
	}

	
	private VOEdificio getEdificioByCodEdi(String codiceEdificio) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		VOEdificio vo = null;
		
		
		try {
			String sql =  " SELECT DISTINCT C.COD_EDI, C.A006 || ', ' || C.A008 || ', ' || C.A001 || ' (' || C.A002 || '), Italia ' INDIRIZZO_MARKER,"
						//+ " C.A006 || ' ' || C.A008 || ' ' || C.A001 || ' (' || C.A003 || ') ' INDIRIZZO "
						+ " C.A006 || ', ' || C.A008 || ', ' || C.A001 || ' (' || C.A003 || ') ' INDIRIZZO,"
						+ " C.A010 LATITUDINE, C.A011 LONGITUDINE "
						+ " FROM VED1006_ANAGEDIFICI C "
						+ " WHERE "
						+ " C.COD_EDI = ? ";
							
			Object[] campi = new Object[]{codiceEdificio};
			int[] tipi = new int[]{Types.VARCHAR};
			
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			if(rs.next()) {
				vo = new VOEdificio();
				vo.setCodiceEdificio(rs.getString(1));
				vo.setIndirizzoMarker(rs.getString(2));
				vo.setIndirizzo(rs.getString(3));
				vo.setLatitudine(rs.getString(4));
				vo.setLongitudine(rs.getString(5));
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
		
		return vo;
	}

	private List<VOAnagraficaScuola> getScuoleByEdificio(String codiceEdificio) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<VOAnagraficaScuola> scuole = new ArrayList<VOAnagraficaScuola>();
		
		
		try {
			String sql =  " SELECT DISTINCT B.COD_SCU_UT, B.DAT_ANN_SCO_RIL, B.DES_NOM_SCU, c.des_tip_sit "
						+ " FROM VED1007_ASSOCSCUEDIFICI A, MFG1002_ANAGISTSCOL B, MFG1028_TIPOSITO c "
						+ " WHERE "
						+ " A.PER_ANN_SCO = B.DAT_ANN_SCO_RIL "
						+ " AND A.COD_SCU_UT = B.COD_SCU_UT "
						+ " and b.cod_tip_sit= c.cod_tip_sit "
						+ " AND A.COD_EDI = ? ";
							
			Object[] campi = new Object[]{codiceEdificio};
			int[] tipi = new int[]{Types.VARCHAR};
			
			
			conn = getConnection();
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			while(rs.next()) {
				VOAnagraficaScuola vo = new VOAnagraficaScuola();
				vo.setCodScuUt(rs.getString(1));
				vo.setDatAnnScoRil(rs.getString(2));
				vo.setDesNomScu(rs.getString(3));
				vo.setDesTipSit(rs.getString(4));
				scuole.add(vo);
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
		
		return scuole;
	}
	
	public List<VOEdificio> getEdifici(String codiceMeccanograficoScuola) throws Exception {
		
		List<VOEdificio> edifici = this.getEdificiCodiceScuola(codiceMeccanograficoScuola);
		for (VOEdificio edificio : edifici){
			edificio.setScuole(this.getScuoleByEdificio(edificio.getCodiceEdificio()));
		}
			
		
		return edifici;
		
	}

	public VOEdificio getEdificio(String codiceEdificio) throws Exception {
		
		VOEdificio edificio = this.getEdificioByCodEdi(codiceEdificio);
		edificio.setScuole(this.getScuoleByEdificio(edificio.getCodiceEdificio()));
			
		
		return edificio;
		
	}
	
	/**
	 * Il metodo estrae l'oggetto VOEdilizia per il codice edificio di input
	 * VOEdilizia contiene una HashMap<String, VOSezione> di sezioni dove la chiave è il codice sezione
	 * Ogni VOSezione contiene codice, descrizione (il nome della sezione da visualizzare) ed una HashMap<String, VOCampo> campi, dove la chiave è il codice del campo
	 * In aggiunta sull'oggetto di tipo VOSezione si ha a disposizione il metodo public int getNumeroDiCampiPresenti() che restituisce il numero di campi della sezione con valori non vuoti.
	 * Può essere utilizzato per esempio se restituisce 0 per inibire l'accesso alla sezione.
	 * Ogni VOCampo contiene codice, descrizione (la label) e valore (il dato).
	 * @param codiceEdificio
	 * @return
	 * @throws Exception
	 */
	public VOEdilizia getVOEdilizia(String codiceEdificio) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		VOEdilizia out = new VOEdilizia();
		out.setSezioni(new LinkedHashMap<String, VOSezione>());
		
		List<VOSezione> sezioni = this.getSezioni();
		for(VOSezione sezione: sezioni){
			out.getSezioni().put(sezione.getCodice(), sezione);
			List<VOCampo> campiSezione = this.getCampiPerSezione(sezione.getCodice());
			String query = " SELECT ";
			String virgola = "";
			for(VOCampo campo: campiSezione){
				query = query + virgola + campo.getCodice();
				virgola = ",";				
			}
			query = query + " FROM VED1006_ANAGEDIFICI WHERE COD_EDI = ? ";
			
			try {
								
				Object[] campi = new Object[]{codiceEdificio};
				int[] tipi = new int[]{Types.VARCHAR};
							
				conn = getConnection();
				ps = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = eseguiPreparedQuery(ps, campi, tipi, true);
				
				sezione.setCampi(new LinkedHashMap<String, VOCampo>());
				if(rs.next()) {
					for(VOCampo campo: campiSezione){
						campo.setValore(rs.getString(campo.getCodice()));
						sezione.getCampi().put(campo.getCodice(), campo);
					}
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
			
			
		}
		
		

		
		return out;
	}
	
}
