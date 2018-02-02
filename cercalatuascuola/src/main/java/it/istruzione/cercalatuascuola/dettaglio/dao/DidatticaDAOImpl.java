package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.common.util.Constants;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOAttivita;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOLibro;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOPon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DidatticaDAOImpl extends BaseDAO implements DidatticaDAO {

	public DidatticaDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	public List<VOAttivita> getListaAttivita(String codScuUt, String datAnnScoRil, String desAtt)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOAttivita> listaAttivita = new ArrayList<VOAttivita>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, DES_ATT, PRG_ATT,")
				.append("COD_SCU_UT_PRI, DES_NOT, NUM_ALU, COD_STA_PUB ")
				.append("FROM TRS1014_ATTIVITASCUOLA ")
				.append("WHERE COD_SCU_UT = ? ")
				.append("AND DAT_ANN_SCO_RIL = ? ")
				.append("AND DES_ATT = ? ")
				.append("order by dat_ora_ult_mov ");
			
			Object[] campi = new Object[]{codScuUt, datAnnScoRil, desAtt};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR};

			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAttivita voAttivita = null;
			while (rs.next()) {
				voAttivita = new VOAttivita();
				voAttivita.setCodScuUt(rs.getString("COD_SCU_UT"));
				voAttivita.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				voAttivita.setCodStaPubb(rs.getString("COD_STA_PUB"));
				voAttivita.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voAttivita.setDesAtt(rs.getString("DES_ATT"));
				voAttivita.setDesNot(rs.getString("DES_NOT"));
				voAttivita.setNumAlu(rs.getString("NUM_ALU"));
				voAttivita.setPrgAtt(rs.getString("PRG_ATT"));
				
				listaAttivita.add(voAttivita);
			}
			
			return listaAttivita;
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
	
	
	public List<VOAttivita> getListaAttivitaIstPrinc(String codScuUt, String datAnnScoRil, String desAtt)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOAttivita> listaAttivita = new ArrayList<VOAttivita>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, DES_ATT, PRG_ATT,")
				.append("COD_SCU_UT_PRI, DES_NOT, NUM_ALU, COD_STA_PUB ")
				.append("FROM TRS1014_ATTIVITASCUOLA ")
				.append("WHERE COD_SCU_UT_PRI = ?   ")
				.append("AND DAT_ANN_SCO_RIL = ? ")
				.append("AND DES_ATT = ? ")
				.append("order by dat_ora_ult_mov");
			
			
			Object[] campi = new Object[]{codScuUt, datAnnScoRil, desAtt};
			int[] tipi = new int[]{Types.VARCHAR,Types.INTEGER, Types.VARCHAR};
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOAttivita voAttivita = null;
			while (rs.next()) {
				voAttivita = new VOAttivita();
				voAttivita.setCodScuUt(rs.getString("COD_SCU_UT"));
				voAttivita.setCodScuUtPri(rs.getString("COD_SCU_UT_PRI"));
				voAttivita.setCodStaPubb(rs.getString("COD_STA_PUB"));
				voAttivita.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				voAttivita.setDesAtt(rs.getString("DES_ATT"));
				voAttivita.setDesNot(rs.getString("DES_NOT"));
				voAttivita.setNumAlu(rs.getString("NUM_ALU"));
				voAttivita.setPrgAtt(rs.getString("PRG_ATT"));
				
				listaAttivita.add(voAttivita);
			}
			
			return listaAttivita;
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
	
	public List<VOLibro> getListaLibri(String codScuUt, String datAnnScoRil) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List <VOLibro> listaLibri = new ArrayList<VOLibro>();
		try {
			
			String sql = " SELECT COD_SCU_UT, DAT_ANN_SCO_RIL, COD_CLA, COD_SEZ, PRG_IDE_DOC, " +
				         " DES_IND_STU,DES_NOM_FIL, OGG_DOC_LTE, to_char(DAT_ORA_ULT_MOV,'dd/mm/yyyy hh24:mi') DAT_ORA_ULT_MOV, COD_PGM_ULT_MOV, COD_UTE_ULT_MOV " +
				         " FROM TRS1041_LIBRITESTO " +
				         " WHERE COD_SCU_UT = ? AND DAT_ANN_SCO_RIL = ? " +
				         " ORDER BY COD_CLA, COD_SEZ, UPPER(DES_IND_STU) " ;

			conn = getConnection();
			ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, codScuUt);
			ps.setInt(2, new Integer(datAnnScoRil));

			rs = ps.executeQuery();
			
			while (rs.next()){
				VOLibro voLibro = new VOLibro();
				voLibro.setCodiceMeccanografico(rs.getString("COD_SCU_UT"));
				voLibro.setAnnoScolastico(rs.getString("DAT_ANN_SCO_RIL"));
				voLibro.setClasse(rs.getString("COD_CLA"));
				voLibro.setSezione(rs.getString("COD_SEZ"));
				voLibro.setProgressivo(rs.getString("PRG_IDE_DOC"));
				voLibro.setIndirizzoDiStudio(rs.getString("DES_IND_STU"));
				voLibro.setNomeFile(rs.getString("DES_NOM_FIL"));
				voLibro.setDataAggiornamento(rs.getString("DAT_ORA_ULT_MOV"));
				voLibro.setNomeProgrammaUltimoMovimento(rs.getString("COD_PGM_ULT_MOV"));
				voLibro.setNomeUtenteUltimoMovimento(rs.getString("COD_UTE_ULT_MOV"));
				voLibro.setId(rs.getString("COD_SCU_UT") 
									+ Constants.TOKENIZER + rs.getString("DAT_ANN_SCO_RIL")
									+ Constants.TOKENIZER + rs.getString("COD_CLA")
									+ Constants.TOKENIZER + rs.getString("COD_SEZ")
									+ Constants.TOKENIZER + rs.getString("PRG_IDE_DOC"));
				listaLibri.add(voLibro);
			}

			return listaLibri;
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
	

	public String getCodiceMeccanograficoAnnoSuccessivo(String codForte, String annoRif) throws Exception
	{

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String codUt = null;
		try {
			
			String sql = " SELECT COD_SCU_UT FROM MFG1002_ANAGISTSCOL WHERE COD_FOR_SCU_APP = ? AND DAT_ANN_SCO_RIL = ? " ;

			conn = getConnection();
			ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, codForte);
			ps.setInt(2, new Integer(annoRif));
			rs = ps.executeQuery();
			
			if (rs.next()){
				codUt = rs.getString("COD_SCU_UT");
			}

			return codUt;
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
	
	public String getDesBeneficio(String codBen)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String des = "";
		
		try {
			StringBuffer query = new StringBuffer("SELECT DES_BEN_IST FROM TRS1054_TIPBENIST where cod_ben_ist = ?" );
			
			Object[] campi = new Object[]{codBen};
			int[] tipi = new int[]{Types.VARCHAR};
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			
			while (rs.next()) {
				des = rs.getString(1);
			}
			
			return des;
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
	
	public List<VOPon> getListaProgPonFse(String codScuUt)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOPon> lista = new ArrayList<VOPon>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT distinct DECODE(e1.cod_fon,'01','FSE','02','FESR'),e1.cod_pia_naz,")
			.append("( ")
			.append(" select flg_uti_esp from TRS1053_INFFONSTR ")
			.append("where cod_pro = e1.cod_pia_naz ")
			.append(") as selezione_esperti, ")
			.append("(SELECT distinct ")
			.append("  (select rtrim(xmlagg(xmlelement(e, d3.titolo || ', ')).extract('//text()'). extract('//text()') ,',') empnos  ")
			.append("  FROM MFG1004_RICHIESTA d1, MFG1003_PIANO d2,MFGPIANO_RICHIESTA d3 ")
			.append("where d1.cod_fon= t1.cod_fon ")
			.append("and d1.cod_sta_ric in (5, 7, 13) ")
			.append("and d1.cod_pia=d2.cod_pia ")
			.append("and d1.cod_fon=d2.cod_fon ")
			.append("and d2.COD_SCU_UT = t2.COD_SCU_UT ")
			.append("and d2.cod_sta_pia= t2.cod_sta_pia ")
			.append("and d2.cod_tip_ffi= t2.cod_tip_ffi ")
			.append("and d1.cod_ric = d3.idrichiesta ")
			.append("and d1.cod_pia_naz = t1.cod_pia_naz ")
			.append(") TITOLI ")
			.append("FROM MFG1004_RICHIESTA t1, MFG1003_PIANO t2,MFGPIANO_RICHIESTA t3 ")
			.append("where t1.cod_fon= e1.cod_fon ")
			.append("and t1.cod_sta_ric in (5, 7, 13) ")
			.append("and t1.cod_pia=t2.cod_pia ")
			.append("and t1.cod_fon=t2.cod_fon ")
			.append("and t2.COD_SCU_UT = e2.COD_SCU_UT ")
			.append("and t2.cod_sta_pia= e2.cod_sta_pia ")
			.append("and t2.cod_tip_ffi= e2.cod_tip_ffi ")
			.append("and t1.cod_ric = t3.idrichiesta and t1.cod_pia_naz=e1.cod_pia_naz) as titoli, ")
			.append("( ")
			.append("SELECT SUM (DECODE(f3.ore_didattica,'','0'))  ")
			.append("FROM MFG1004_RICHIESTA f1, MFG1003_PIANO f2,MFGPIANO_RICHIESTA f3 ")
			.append("where f1.cod_fon= e1.cod_fon ")
			.append("and f1.cod_sta_ric in (5, 7, 13) ")
			.append("and f1.cod_pia=f2.cod_pia ")
			.append("and f1.cod_fon=f2.cod_fon ")
			.append("and f2.COD_SCU_UT = e2.COD_SCU_UT ")
			.append("and f2.cod_sta_pia= e2.cod_sta_pia ")
			.append("and f2.cod_tip_ffi= e2.cod_tip_ffi ")
			.append("and f1.cod_ric = f3.idrichiesta ")
			.append("and f1.cod_pia_naz = e1.cod_pia_naz ")
			.append("group by f1.cod_pia_naz ")
			.append(") durata_prog, ")
			.append("( ")
			.append("select des_lin_con_pro from TRS1053_INFFONSTR ")
			.append(" where cod_pro = e1.cod_pia_naz ")
			.append(") as link ")
			.append("FROM MFG1004_RICHIESTA e1, MFG1003_PIANO e2 ")
			.append("where e1. cod_fon= '01' ")
			.append("and e1.cod_sta_ric in (5, 7, 13) ")
			.append("and e1.cod_pia=e2.cod_pia ")
			.append("and e1.cod_fon=e2.cod_fon ")
			.append("and e2.COD_SCU_UT = ? ")
			.append("and e2.cod_sta_pia in (5, 7) ")
			.append("and e2.cod_tip_ffi in ('0', '1') ")
			.append("order by e1.cod_pia_naz ");
			
			Object[] campi = new Object[]{codScuUt};
			int[] tipi = new int[]{Types.VARCHAR};
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon vo = null;
			int cont = 1;
			while (rs.next()) {
				vo = new VOPon();
				vo.setId(String.valueOf(cont));				
				vo.setFondo(rs.getString(1));				
				vo.setCodFondo("01");
				vo.setCodProgetto(rs.getString(2));
				vo.setFlgSelEsp(rs.getString(3));
				String des = getTitolo(vo.getCodProgetto(), vo.getCodFondo());
				
				if(des.equalsIgnoreCase(""))
				 vo.setDescProg(rs.getString(4));
				else vo.setDescProg(des);
				
				vo.setDurataProg(rs.getString(5));
				vo.setLink(rs.getString(6));
				vo.setScuola(codScuUt);								
				lista.add(vo);
				cont++;
			}
			
			return lista;
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
	
	
	public List<VOPon> getListaProgPonFesr(String codScuUt)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOPon> lista = new ArrayList<VOPon>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT distinct DECODE(e1.cod_fon,'01','FSE','02','FESR'),e1.cod_pia_naz,")
			.append("( ")
			.append(" select flg_gar from TRS1053_INFFONSTR ")
			.append("where cod_pro = e1.cod_pia_naz ")
			.append(") as selezione_gare, ")
			.append("(SELECT distinct ")
			.append("  (select rtrim(xmlagg(xmlelement(e, d3.titolo || ', ')).extract('//text()'). extract('//text()') ,',') empnos  ")
			.append("  FROM MFG1004_RICHIESTA d1, MFG1003_PIANO d2,MFGPIANO_RICHIESTA d3 ")
			.append("where d1.cod_fon= t1.cod_fon ")
			.append("and d1.cod_sta_ric in (5, 7, 13) ")
			.append("and d1.cod_pia=d2.cod_pia ")
			.append("and d1.cod_fon=d2.cod_fon ")
			.append("and d2.COD_SCU_UT = t2.COD_SCU_UT ")
			.append("and d2.cod_sta_pia= t2.cod_sta_pia ")
			.append("and d2.cod_tip_ffi= t2.cod_tip_ffi ")
			.append("and d1.cod_ric = d3.idrichiesta ")
			.append("and d1.cod_pia_naz = t1.cod_pia_naz ")
			.append(") TITOLI ")
			.append("FROM MFG1004_RICHIESTA t1, MFG1003_PIANO t2,MFGPIANO_RICHIESTA t3 ")
			.append("where t1.cod_fon= e1.cod_fon ")
			.append("and t1.cod_sta_ric in (5, 7, 13) ")
			.append("and t1.cod_pia=t2.cod_pia ")
			.append("and t1.cod_fon=t2.cod_fon ")
			.append("and t2.COD_SCU_UT = e2.COD_SCU_UT ")
			.append("and t2.cod_sta_pia= e2.cod_sta_pia ")
			.append("and t2.cod_tip_ffi= e2.cod_tip_ffi ")
			.append("and t1.cod_ric = t3.idrichiesta and t1.cod_pia_naz=e1.cod_pia_naz) as titoli, ")
			.append("( ")
			.append(" select cod_ben_ist from TRS1053_INFFONSTR ")
			.append("where cod_pro = e1.cod_pia_naz ")
			.append(") as benefici, ")
			.append("( ")
			.append("select des_lin_con_pro from TRS1053_INFFONSTR ")
			.append(" where cod_pro = e1.cod_pia_naz ")
			.append(") as link ")
			.append("FROM MFG1004_RICHIESTA e1, MFG1003_PIANO e2 ")
			.append("where e1. cod_fon= '02' ")
			.append("and e1.cod_sta_ric in (5, 7, 13) ")
			.append("and e1.cod_pia=e2.cod_pia ")
			.append("and e1.cod_fon=e2.cod_fon ")
			.append("and e2.COD_SCU_UT = ? ")
			.append("and e2.cod_sta_pia in (5, 7) ")
			.append("and e2.cod_tip_ffi in ('0','1') ")
			.append("order by e1.cod_pia_naz ");
			
			Object[] campi = new Object[]{codScuUt};
			int[] tipi = new int[]{Types.VARCHAR};
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon vo = null;
			int cont = 1;
			while (rs.next()) {
				vo = new VOPon();
				vo.setId(String.valueOf(cont));				
				vo.setFondo(rs.getString(1));				
				vo.setCodFondo("02");		
				vo.setCodProgetto(rs.getString(2));
				vo.setFlgGare(rs.getString(3));
				String des = getTitolo(vo.getCodProgetto(), vo.getCodFondo());
				
				if(des.equalsIgnoreCase(""))
				 vo.setDescProg(rs.getString(4));
				else vo.setDescProg(des);
				
				vo.setCodBenIst(rs.getString(5));
				vo.setDescBenefici(getDesBeneficio(rs.getString(5)));
				vo.setLink(rs.getString(6));
				vo.setScuola(codScuUt);
				lista.add(vo);
				cont++;
			}
			
			return lista;
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

	public String getTitolo(String codProg, String codFon)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String des = "";
		
		try {
			StringBuffer query = new StringBuffer("SELECT DES_PRO_LAV_LAB,DES_LAV_LAB FROM TRS1053_INFFONSTR where cod_pro = ? and cod_fon = ?" );
			
			Object[] campi = new Object[]{codProg,codFon};
			int[] tipi = new int[]{Types.VARCHAR,Types.VARCHAR};
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			
			while (rs.next()) {
			 if(codFon.equalsIgnoreCase("01"))	
			  des = rs.getString(1);
			 if(codFon.equalsIgnoreCase("02"))	
			  des = rs.getString(2);
			}
			
			return des;
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
	
	public List<VOPon> getListaProgPonInCorsoFse(String codScuUt)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOPon> lista = new ArrayList<VOPon>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT t1.cod_pia_naz,count(t1.cod_ric) as MODULI,t4.idobiettivo,t4.idazione, ")
			.append(" ( ")
			.append(" SELECT SUM(e5.perc_avan_atti)/count(t1.cod_ric) ")
			.append(" FROM mfg1004_richiesta e1, mfg1003_piano e2, mfgpiano_progetto e4,mfgACQAVAATT e5 ")
			.append(" where e1. cod_fon= '01' ")
			.append(" and e1.cod_sta_ric in (5, 7, 13) ")
			.append(" and e1.cod_pia=e2.cod_pia ")
			.append(" and e1.cod_fon=e2.cod_fon ")
			.append(" and e2.COD_SCU_UT = ? ")
			.append(" and e2.cod_sta_pia in (5, 7) ")
			.append(" and e2.cod_tip_ffi in ('0','1') ")
			.append(" and e1.cod_pia_naz = e4.cod_progetto_nazionale ")
			.append(" and e1.cod_pia_naz = e5.cod_nazionale ")
			.append(" and e4.idazione = e5.idazione ")
			.append(" and e4.idobiettivo = e5.idobiettivo ")
			.append(" and e1.cod_ric = e5.idrichiesta ")
			.append(" and e5.idobiettivo = t4.idobiettivo ")
			.append(" and e5.idazione = t4.idazione and t1.cod_pia_naz = e1.cod_pia_naz ")
			.append(" group by e1.cod_pia_naz,e1.cod_fon,e4.idobiettivo,e4.idazione ")
			.append(" ) perc, ")
			.append(" ( ")
			.append(" SELECT count(d1.cod_ric) ")
			.append(" FROM MFG1004_richiesta d1, mfg1003_piano d2, mfgpiano_progetto d4,MFGACQAVAATT d5 ")
			.append(" where d1. cod_fon= '01' ")
			.append(" and d1.cod_sta_ric in (5, 7, 13) ")
			.append(" and d1.cod_pia=d2.cod_pia ")
			.append(" and d1.cod_fon=d2.cod_fon ")
			.append(" and d2.COD_SCU_UT = ? ")
			.append(" and d2.cod_sta_pia in (5, 7) ")
			.append(" and d2.cod_tip_ffi in ('0','1') ")
			.append(" and d1.cod_pia_naz = d4.cod_progetto_nazionale ")
			.append(" and d1.cod_pia_naz = d5.cod_nazionale ")
			.append(" and d4.idazione = d5.idazione ")
			.append(" and d4.idobiettivo = d5.idobiettivo ")
			.append(" and d1.cod_ric = d5.idrichiesta ")
			.append(" and d5.idobiettivo = t4.idobiettivo ")
			.append(" and d5.idazione = t4.idazione ")
			.append(" and d5.conclusa = 1 and t1.cod_pia_naz = d1.cod_pia_naz ")
			.append(" group by d1.cod_pia_naz,d1.cod_fon,d4.idobiettivo,d4.idazione ")
			.append(" ) moduli_conclusi, ")
			.append(" ( ")
			.append(" SELECT count(*) as attestati ")
			.append(" FROM MFG1004_richiesta f1, mfg1003_piano f2, mfgavviso_bando f3,MFG1065_ANAGSOGGFORM f4 ")
			.append(" where f1. cod_fon= '01' ")
			.append(" and f1.cod_sta_ric in (5, 7, 13) ")
			.append(" and f1.cod_pia=f2.cod_pia ")
			.append(" and f1.cod_fon=f2.cod_fon ")
			.append(" and f2.COD_SCU_UT = ? ")
			.append(" and f2.cod_sta_pia in (5, 7) ")
			.append(" and f2.cod_tip_ffi in ('0','1') ")
			.append(" and f3.idbando=f2.num_ban ")
			.append(" and f4.codice_progetto = f1.cod_pia_naz ")
			.append(" and f4.flg_tip_per is not null ")
			.append(" and flg_att_con = 01 ")
			.append(" and f4.codice_progetto = t1.cod_pia_naz ")
			.append(" ) destinatari, ")
			.append(" ( ")
			.append(" select des_int from trs1055_tipointer a,trs1056_assintobiass b ")
			.append(" where a.cod_fon = b.cod_fon ")
			.append(" and a.cod_int = b.cod_int ") 
			.append(" and b.cod_obi = t4.idobiettivo ")
			.append(" and b.cod_azi = t4.idazione ")
			.append(" and a.cod_fon = '01' ")
			.append(" ) tipologiaint ")
			.append(" FROM MFG1004_richiesta t1, mfg1003_piano t2, mfgpiano_progetto t4,MFGACQAVAATT t5 ")
			.append(" where t1. cod_fon= '01' ")
			.append(" and t1.cod_sta_ric in (5, 7, 13) ")
			.append(" and t1.cod_pia=t2.cod_pia ")
			.append(" and t1.cod_fon=t2.cod_fon ")
			.append(" and t2.COD_SCU_UT = ? ")
			.append(" and t2.cod_sta_pia in (5, 7) ")
			.append(" and t2.cod_tip_ffi in ('0','1') ")
			.append(" and t1.cod_pia_naz = t4.cod_progetto_nazionale ")
			.append(" and t1.cod_pia_naz = t5.cod_nazionale ")
			.append(" and t4.idazione = t5.idazione ")
			.append(" and t4.idobiettivo = t5.idobiettivo ")
			.append(" and t1.cod_ric = t5.idrichiesta ")
			//.append(" group by t1.cod_pia_naz,t1.cod_fon,t4.idobiettivo,t4.idazione,t5.perc_avan_atti ")
			.append(" group by t1.cod_pia_naz,t1.cod_fon,t4.idobiettivo,t4.idazione ")
			.append(" order by t1.cod_pia_naz ");
			
			Object[] campi = new Object[]{codScuUt,codScuUt,codScuUt,codScuUt};
			int[] tipi = new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon vo = null;
			int cont = 1;
			while (rs.next()) {

				vo = new VOPon();
				vo.setTipInt(rs.getString(8));
				vo.setNumMod(rs.getString(2));
				vo.setPercAvvMod(rs.getString(5));
				vo.setNumModConcl(rs.getString(6));
				vo.setNumDestCoinv(rs.getString(7));				
				
				lista.add(vo);
				cont++;
			}
			
			return lista;
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
	
	public List<VOPon> getListaProgPonInCorsoFesr(String codScuUt)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOPon> lista = new ArrayList<VOPon>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT t1.cod_pia_naz,SUM(t1.imp_ric_aut) as IMPORTO,t4.idobiettivo,t4.idazione, ")
			.append(" ( ")
			.append(" SELECT SUM(e5.perc_avan_atti)/count(t1.cod_ric) ")
			.append(" FROM mfg1004_richiesta e1, mfg1003_piano e2, mfgpiano_progetto e4,mfgACQAVAATT e5 ")
			.append(" where e1. cod_fon= '02' ")
			.append(" and e1.cod_sta_ric in (5, 7, 13) ")
			.append(" and e1.cod_pia=e2.cod_pia ")
			.append(" and e1.cod_fon=e2.cod_fon ")
			.append(" and e2.COD_SCU_UT = ? ")
			.append(" and e2.cod_sta_pia in (5, 7) ")
			.append(" and e2.cod_tip_ffi in ('0','1') ")
			.append(" and e1.cod_pia_naz = e4.cod_progetto_nazionale ")
			.append(" and e1.cod_pia_naz = e5.cod_nazionale ")
			.append(" and e4.idazione = e5.idazione ")
			.append(" and e4.idobiettivo = e5.idobiettivo ")
			.append(" and e1.cod_ric = e5.idrichiesta ")
			.append(" and e5.idobiettivo = t4.idobiettivo ")
			.append(" and e5.idazione = t4.idazione and t1.cod_pia_naz = e1.cod_pia_naz ")
			.append(" group by e1.cod_pia_naz,e1.cod_fon,e4.idobiettivo,e4.idazione ")
			.append(" ) perc, ")
			.append(" ( ")
			.append(" SELECT count(d1.cod_ric) ")
			.append(" FROM MFG1004_richiesta d1, mfg1003_piano d2, mfgpiano_progetto d4,MFGACQAVAATT d5 ")
			.append(" where d1. cod_fon= '02' ")
			.append(" and d1.cod_sta_ric in (5, 7, 13) ")
			.append(" and d1.cod_pia=d2.cod_pia ")
			.append(" and d1.cod_fon=d2.cod_fon ")
			.append(" and d2.COD_SCU_UT = ? ")
			.append(" and d2.cod_sta_pia in (5, 7) ")
			.append(" and d2.cod_tip_ffi in ('0','1') ")
			.append(" and d1.cod_pia_naz = d4.cod_progetto_nazionale ")
			.append(" and d1.cod_pia_naz = d5.cod_nazionale ")
			.append(" and d4.idazione = d5.idazione ")
			.append(" and d4.idobiettivo = d5.idobiettivo ")
			.append(" and d1.cod_ric = d5.idrichiesta ")
			.append(" and d5.idobiettivo = t4.idobiettivo ")
			.append(" and d5.idazione = t4.idazione ")
			.append(" and d5.conclusa = 1 and t1.cod_pia_naz = d1.cod_pia_naz ")
			.append(" group by d1.cod_pia_naz,d1.cod_fon,d4.idobiettivo,d4.idazione ")
			.append(" ) moduli_conclusi, ")
			.append(" ( ")
			.append(" SELECT count(*) as attestati ")
			.append(" FROM MFG1004_richiesta f1, mfg1003_piano f2, mfgavviso_bando f3,MFG1065_ANAGSOGGFORM f4 ")
			.append(" where f1. cod_fon= '02' ")
			.append(" and f1.cod_sta_ric in (5, 7, 13) ")
			.append(" and f1.cod_pia=f2.cod_pia ")
			.append(" and f1.cod_fon=f2.cod_fon ")
			.append(" and f2.COD_SCU_UT = ? ")
			.append(" and f2.cod_sta_pia in (5, 7) ")
			.append(" and f2.cod_tip_ffi in ('0','1') ")
			.append(" and f3.idbando=f2.num_ban ")
			.append(" and f4.codice_progetto = f1.cod_pia_naz ")
			.append(" and f4.flg_tip_per is not null ")
			.append(" and flg_att_con = 01 ")
			.append(" and f4.codice_progetto = t1.cod_pia_naz ")
			.append(" ) destinatari, ")
            .append(" ( ")
			.append(" select des_int from trs1055_tipointer a,trs1056_assintobiass b ")
			.append(" where a.cod_fon = b.cod_fon ")
			.append(" and a.cod_int = b.cod_int ") 
			.append(" and b.cod_obi = t4.idobiettivo ")
			.append(" and b.cod_azi = t4.idazione ")
			.append(" and a.cod_fon = '02' ")
			.append(" ) tipologiaint ")			
			.append(" FROM MFG1004_richiesta t1, mfg1003_piano t2, mfgpiano_progetto t4,MFGACQAVAATT t5 ")
			.append(" where t1. cod_fon= '02' ")
			.append(" and t1.cod_sta_ric in (5, 7, 13) ")
			.append(" and t1.cod_pia=t2.cod_pia ")
			.append(" and t1.cod_fon=t2.cod_fon ")
			.append(" and t2.COD_SCU_UT = ? ")
			.append(" and t2.cod_sta_pia in (5, 7) ")
			.append(" and t2.cod_tip_ffi in ('0','1') ")
			.append(" and t1.cod_pia_naz = t4.cod_progetto_nazionale ")
			.append(" and t1.cod_pia_naz = t5.cod_nazionale ")
			.append(" and t4.idazione = t5.idazione ")
			.append(" and t4.idobiettivo = t5.idobiettivo ")
			.append(" and t1.cod_ric = t5.idrichiesta ")
			.append(" group by t1.cod_pia_naz,t1.cod_fon,t4.idobiettivo,t4.idazione,t5.perc_avan_atti ")
			.append(" order by t1.cod_pia_naz ");
			
			Object[] campi = new Object[]{codScuUt,codScuUt,codScuUt,codScuUt};
			int[] tipi = new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			
			conn = getConnection();
			ps = conn.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOPon vo = null;
			int cont = 1;
			while (rs.next()) {
				vo = new VOPon();				
				vo.setTipInt(rs.getString(8));
				vo.setImpAmmFin(rs.getString(2));
				vo.setStatoAv(rs.getString(5));				
				lista.add(vo);
				cont++;
			}
			
			return lista;
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
