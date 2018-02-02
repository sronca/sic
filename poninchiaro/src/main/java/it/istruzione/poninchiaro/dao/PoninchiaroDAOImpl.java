package it.istruzione.poninchiaro.dao;

import it.istruzione.poninchiaro.common.dao.BaseDAO;
import it.istruzione.poninchiaro.common.util.HtmlEscapeStringEditor;
import it.istruzione.poninchiaro.common.util.LabelValueBean;
import it.istruzione.poninchiaro.model.ScuolaJson;
import it.istruzione.poninchiaro.model.VOBandoIstituto;
import it.istruzione.poninchiaro.model.VODistribuzionePartecipanti;
import it.istruzione.poninchiaro.model.VODistribuzioneProgetti;
import it.istruzione.poninchiaro.model.VODistribuzioneProgrammatoAutorizzato;
import it.istruzione.poninchiaro.model.VODistribuzioneProgrammazioneAreaTerritoriale;
import it.istruzione.poninchiaro.model.VOEvento;
import it.istruzione.poninchiaro.model.VOFaq;
import it.istruzione.poninchiaro.model.VOFiltroRicerca;
import it.istruzione.poninchiaro.model.VOFornitore;
import it.istruzione.poninchiaro.model.VOIstituto;
import it.istruzione.poninchiaro.model.VOModuloRichiesta;
import it.istruzione.poninchiaro.model.VOProgetto;
import it.istruzione.poninchiaro.model.VOProgettoIstituto;
import it.istruzione.poninchiaro.model.VOProgettoOpendata;

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
import org.springframework.web.util.HtmlUtils;

public class PoninchiaroDAOImpl extends BaseDAO implements PoninchiaroDAO
{
	private static Logger log = Logger.getLogger(PoninchiaroDAOImpl.class);
	
	public PoninchiaroDAOImpl(DataSource dataSource) throws SQLException, NamingException
	{
		super(dataSource);
	}
	
	private String escapePerOpenData(String strIn){
		if (strIn == null || strIn.trim().equals("")){
			return "";
		}else{
			return strIn.replaceAll("\r"," ").replaceAll("\n"," ");
		}
	}
	
	private String escapePerDatiDescrittiviProgetto(String strIn){
		if (strIn == null || strIn.trim().equals("")){
			return "";
		}else{
			strIn = strIn.replaceAll("\\<.*?>","").replace("ELEMENTI D CONGRUITA&rsquo  E COERENZA DELLA PROPOSTA PROGETTUALE CON IL POF DELLA SCUOLA.", "");
			strIn = strIn.replace("OBIETTIVI SPECIFICI:", "").replace("&rsquo", "'").replace("¿", "'").replace("\" ", "");
			return strIn;
		}
	}

	
	/**
	 * Restituisce la lista delle Faq
	 * 
	 * @return List
	 * @throws Exception
	 */

	public List<VOFaq> getFaq() throws Exception
	{
		log.debug("start method getFaq");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOFaq> lista = new ArrayList<VOFaq>();
		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			String sql = " SELECT " +
						 " PRG_FAQ, DES_DOM_FAQ, DES_RIS_FAQ " +
					     " FROM TFS1002_FAQ WHERE FLG_PUB_FAQ = '1' " +
					     " ORDER BY PRG_FAQ ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOFaq vo = null;
			while (rs.next())
			{
				vo = new VOFaq();
				vo.setPrgFaq(rs.getLong("PRG_FAQ"));
				vo.setDesDomFaq(rs.getString("DES_DOM_FAQ"));
				vo.setDesRisFaq(rs.getString("DES_RIS_FAQ"));
				lista.add(vo);
			}
			
			log.debug("end method getFaq - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	/**
	 * Restituisce la lista degli anni in cui Ã¨ stato emesso un bando
	 * 
	 * @return List
	 * @throws Exception
	 */

	public List<LabelValueBean> getAnniBandoList() throws Exception
	{
		log.debug("start method getAnniBandoList");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			String sql = " SELECT DISTINCT ANN_BAN COD, ANN_BAN DESCR FROM VFS1015_FSEANABANDO ORDER BY 1 DESC ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			LabelValueBean vo = null;
			while (rs.next())
			{
				vo = new LabelValueBean();
				vo.setValue(rs.getString("COD"));
				vo.setLabel(rs.getString("DESCR"));
				lista.add(vo);
			}
			
			log.debug("end method getAnniBandoList - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	/**
	 * Restituisce la lista dei tipi di fondo
	 * 
	 * @return List
	 * @throws Exception
	 */

	public List<LabelValueBean> getTipoFondoList() throws Exception
	{
		log.debug("start method getTipoFondoList");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			String sql = " SELECT COD_TIP_FON COD, COD_TIP_FON DESCR FROM VFS1014_FSEANAFONDO ORDER BY 1 ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			LabelValueBean vo = null;
			while (rs.next())
			{
				vo = new LabelValueBean();
				vo.setValue(rs.getString("COD"));
				vo.setLabel(rs.getString("DESCR"));
				lista.add(vo);
			}
			
			log.debug("end method getTipoFondoList - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<LabelValueBean> getRegioniList() throws Exception
	{
		log.debug("start method getRegioniList");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			//String sql = " SELECT TRIM(COD_REG) COD, TRIM(DES_REG) DESCR FROM MFG1012_REGIONE WHERE TRIM(COD_CIT) = '200' ORDER BY DES_REG ";
			String sql = " SELECT DISTINCT COD_REG COD, DES_REG DESCR FROM VFS1017_FSEANATERRITORIO WHERE COD_REG NOT IN ('VA','TR') ORDER BY 2 ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			LabelValueBean vo = null;
			while (rs.next())
			{
				vo = new LabelValueBean();
				vo.setValue(rs.getString("COD"));
				vo.setLabel(rs.getString("DESCR"));
				lista.add(vo);
			}
			
			log.debug("end method getRegioniList - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}
	
	public List<LabelValueBean> getProvinceList(String codRegione) throws Exception
	{
		log.debug("start method getProvinceList");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		try
		{
			Object[] campi = new Object[] {codRegione};
			int[] tipi = new int[] {Types.VARCHAR};

			//String sql = " SELECT TRIM(COD_PRV) COD, TRIM(DES_PRV) DESCR FROM MFG1013_PROVINCIA WHERE TRIM(COD_REG) = ? ORDER BY DES_PRV ";
			String sql = " SELECT DISTINCT COD_PRV COD, DES_PRV DESCR FROM VFS1017_FSEANATERRITORIO WHERE COD_REG = ? ORDER BY 2 ";
			

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			LabelValueBean vo = null;
			while (rs.next())
			{
				vo = new LabelValueBean();
				vo.setValue(rs.getString("COD"));
				vo.setLabel(rs.getString("DESCR"));
				lista.add(vo);
			}
			
			log.debug("end method getProvinceList - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<LabelValueBean> getComuniList(String codProvincia) throws Exception
	{
		log.debug("start method getComuniList");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		try
		{
			Object[] campi = new Object[] {codProvincia};
			int[] tipi = new int[] {Types.VARCHAR};

			String sql = " SELECT DISTINCT COD_COM COD, DES_COM DESCR FROM VFS1017_FSEANATERRITORIO WHERE COD_PRV = ? ORDER BY 2 ";
			

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			LabelValueBean vo = null;
			while (rs.next())
			{
				vo = new LabelValueBean();
				vo.setValue(rs.getString("COD"));
				vo.setLabel(rs.getString("DESCR"));
				lista.add(vo);
			}
			
			log.debug("end method getComuniList - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<LabelValueBean> getSottoCategoriaList(String tipoFondo) throws Exception
	{
		log.debug("start method getSottoCategoriaList");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		try
		{
			Object[] campi = new Object[] {tipoFondo};
			int[] tipi = new int[] {Types.VARCHAR};

			String sql = " SELECT DISTINCT COD_SOT_CAT COD, DES_SOT_CAT DESCR FROM VFS1025_FSEPGTCATSOTCATINT WHERE COD_TIP_FON = ? ORDER BY 2 ";
			

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			LabelValueBean vo = null;
			while (rs.next())
			{
				vo = new LabelValueBean();
				vo.setValue(rs.getString("COD"));
				vo.setLabel(rs.getString("DESCR"));
				lista.add(vo);
			}
			
			log.debug("end method getSottoCategoriaList - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<ScuolaJson> getIstitutiList(String filter) throws Exception
	{
		log.debug("start method getIstitutiList");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ScuolaJson> lista = new ArrayList<ScuolaJson>();
		try
		{
			if (filter != null && !filter.isEmpty()){
				
				String likeFilter = "%" + filter.toUpperCase() + "%";
				Object[] campi = new Object[] {likeFilter, likeFilter};
				int[] tipi = new int[] {Types.VARCHAR, Types.VARCHAR};

				String sql = " SELECT DISTINCT AI.COD_MEC_IST_PRI COD, AI.COD_MEC_IST_PRI || ' - ' || AI.DES_NOM_SCU DESCR "
						+ " FROM UFSPORPUB_OWN.VFS1010_FSEANAISTPRI AI "
						+ " WHERE "
						+ " AI.COD_MEC_IST_PRI LIKE ? OR UPPER(AI.DES_NOM_SCU) LIKE ? "
						+ " ORDER BY 1 ";

				ps = con.prepareStatement(sql);
				rs = eseguiPreparedQuery(ps, campi, tipi,false);

				ScuolaJson vo = null;
				while (rs.next())
				{
					vo = new ScuolaJson();
					vo.setCod(rs.getString("COD"));
					vo.setDes(rs.getString("DESCR").replaceAll("\"", " ").replaceAll("'", " "));
					lista.add(vo);
				}

			}
			
			log.debug("end method getIstitutiList - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public LabelValueBean getRegioneByCod(String codiceRegione) throws Exception
	{
		log.debug("start method getRegioneByCod");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		LabelValueBean out = null;
		try
		{
			Object[] campi = new Object[] {codiceRegione};
			int[] tipi = new int[] {Types.VARCHAR};

			String sql = " SELECT DISTINCT COD_REG COD, DES_REG DESCR FROM VFS1017_FSEANATERRITORIO WHERE COD_REG = ? ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				out = new LabelValueBean();
				out.setValue(rs.getString("COD"));
				out.setLabel(rs.getString("DESCR"));
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return out;
	}

	public LabelValueBean getProvinciaByCod(String codiceProvincia) throws Exception
	{
		log.debug("start method getProvinciaByCod");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		LabelValueBean out = null;
		try
		{
			Object[] campi = new Object[] {codiceProvincia};
			int[] tipi = new int[] {Types.VARCHAR};

			String sql = " SELECT DISTINCT COD_PRV COD, DES_PRV DESCR FROM VFS1017_FSEANATERRITORIO WHERE COD_PRV = ? ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				out = new LabelValueBean();
				out.setValue(rs.getString("COD"));
				out.setLabel(rs.getString("DESCR"));
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return out;
	}

	public LabelValueBean getComuneByCod(String codiceComune) throws Exception
	{
		log.debug("start method getComuneByCod");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		LabelValueBean out = null;
		try
		{
			Object[] campi = new Object[] {codiceComune};
			int[] tipi = new int[] {Types.VARCHAR};

			String sql = " SELECT DISTINCT COD_COM COD, DES_COM DESCR FROM VFS1017_FSEANATERRITORIO WHERE COD_COM = ? ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				out = new LabelValueBean();
				out.setValue(rs.getString("COD"));
				out.setLabel(rs.getString("DESCR"));
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return out;
	}

	public LabelValueBean getSottoCategoriaByCod(String codiceSottoCategoria) throws Exception
	{
		log.debug("start method getSottoCategoriaByCod");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		LabelValueBean out = null;
		try
		{
			Object[] campi = new Object[] {codiceSottoCategoria};
			int[] tipi = new int[] {Types.VARCHAR};

			String sql = " SELECT DISTINCT COD_SOT_CAT COD, DES_SOT_CAT DESCR FROM VFS1025_FSEPGTCATSOTCATINT WHERE COD_SOT_CAT = ? ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				out = new LabelValueBean();
				out.setValue(rs.getString("COD"));
				out.setLabel(rs.getString("DESCR"));
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return out;
	}

	public VOIstituto getIstituto(String codiceMeccanografico) throws Exception
	{
		log.debug("start method getIstituto " + codiceMeccanografico);
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		VOIstituto out = null;
		try
		{

			Object[] campi = new Object[] {codiceMeccanografico};
			int[] tipi = new int[] {Types.VARCHAR};

			String sql = " SELECT "
					+ " AI.COD_MEC_IST_PRI, AI.DAT_ANN_SCO_RIL, "
					+ " AI.DES_NOM_SCU, AI.DES_IND_EML, AI.COD_FOR_SCU_APP, AI.DES_IND_WEB, AI.DES_IND_EMA_PCE, AI.DES_IND_SCU, AI.COD_TEL_SCU, CO.DES_COM, CO.COD_PRV, AI.COD_CAP_SCU "
					+ " FROM UFSPORPUB_OWN.VFS1010_FSEANAISTPRI AI, VFS1017_FSEANATERRITORIO CO "
					+ " WHERE "
					+ " AI.COD_SCU_UT_PRI = ? "
					+ " AND AI.COD_COM = CO.COD_COM ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				out = new VOIstituto();
				out.setCodiceMeccanografico(rs.getString("COD_MEC_IST_PRI"));
				out.setDatAnnScoRil(rs.getString("DAT_ANN_SCO_RIL"));
				out.setDenominazione(rs.getString("DES_NOM_SCU"));
				out.setEmail(rs.getString("DES_IND_EML"));
				out.setCodiceForte(rs.getString("COD_FOR_SCU_APP"));
				out.setSitoweb(rs.getString("DES_IND_WEB"));
				out.setPec(rs.getString("DES_IND_EMA_PCE"));
				out.setIndirizzo(rs.getString("DES_IND_SCU"));
				out.setTelefono(rs.getString("COD_TEL_SCU"));
				out.setComune(rs.getString("DES_COM"));
				out.setCodProvincia(rs.getString("COD_PRV"));
				out.setCap(rs.getString("COD_CAP_SCU"));
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return out;
	}

	public String getDirigenteScolastico(String codScuUt, String datAnnScoRil) throws Exception
	{
		log.debug("INIZIO getDirigenteScolastico("+codScuUt+","+datAnnScoRil+")");
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String out = "";
		try{
			String query = " SELECT DISTINCT A.COMP_COGN, A.COMP_NOME, A.COD_FISC, A.DAT_INI_INC, A.DAT_FIN_INC, A.DAT_FIN_ATT, A.COD_TIP_INC, A.DES_TIP_INC "
					+ " from MFG1001_ASSRESISTSCOL A, UFSPORPUB_OWN.VFS1010_FSEANAISTPRI B "
					+ " where "
					+ " A.COD_FOR_SCU_APP = B.COD_FOR_SCU_APP  "
					+ " and (A.dat_fin_inc > TO_number(TO_char(sysdate,'YYYYMMDD'),'99999999') and A.dat_fin_att > TO_number(TO_char(sysdate,'YYYYMMDD'),'99999999')) "
					+ " and A.dat_ini_inc < TO_number(TO_char(sysdate,'YYYYMMDD'),'99999999') "
					+ " and A.cod_tip_inc in ('04','06','07') "
					+ " and A.cod_for_scu_app = (SELECT COD_FOR_SCU_APP FROM UFSPORPUB_OWN.VFS1010_FSEANAISTPRI "
					+ " WHERE COD_SCU_UT_PRI = '" + codScuUt + "' "
					+ " AND DAT_ANN_SCO_RIL = " + datAnnScoRil + " ) "
					+ " ORDER BY A.cod_tip_inc DESC ";
			
			log.debug("query="+query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()){
				out =rs.getString("COMP_NOME") + " " + rs.getString("COMP_COGN");
			}
			log.debug("out="+out);
		}
		catch (Exception e)
		{
			log.error("ERRORE : getDirigenteScolastico ", e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return out;
	}

	public List<VOIstituto> getIstitutiBeneficiari(VOFiltroRicerca filter, int prgEve) throws Exception
	{
		log.debug("start method getIstitutiBeneficiari");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOIstituto> lista = new ArrayList<VOIstituto>();
		try
		{

			Object[] campi = new Object[] {prgEve, filter.getTipoFondo(), filter.getAnno()};
			int[] tipi = new int[] {Types.NUMERIC, Types.VARCHAR, Types.NUMERIC};

			String sql = " SELECT AI.COD_MEC_IST_PRI, AI.DES_NOM_SCU, CO.DES_COM, "
					+ " SUM(PI.IMP_AUT_COR) IMP_AUT, SUM(PI.IMP_PAG) IMP_PAG, COUNT(*) NUM_PRG "
					+ " FROM TFS1003_STOPROGETTOISTITUTO PI, UFSPORPUB_OWN.VFS1010_FSEANAISTPRI AI, VFS1017_FSEANATERRITORIO CO "
					+ " WHERE "
					+ " PI.COD_MEC_IST_PRI = AI.COD_MEC_IST_PRI "
					+ " AND PI.PRG_EVE = ? "
					+ " AND AI.COD_COM = CO.COD_COM "
					+ " AND CO.COD_REG NOT IN ('VA','TR') "
					+ " AND PI.COD_TIP_FON = ? "
					+ " AND PI.PER_ANN_BAN = ? ";
			
			if (filter.getCodiceRegione() != null && !filter.getCodiceRegione().isEmpty()){
				sql = sql + " AND CO.COD_REG = '" + filter.getCodiceRegione() + "' ";
			}

			if (filter.getCodiceProvincia() != null && !filter.getCodiceProvincia().isEmpty()){
				sql = sql + " AND CO.COD_PRV = '" + filter.getCodiceProvincia() + "' ";
			}

			if (filter.getCodicemeccanografico_autocomplete() != null && !filter.getCodicemeccanografico_autocomplete().isEmpty()){
				sql = sql + " AND AI.COD_MEC_IST_PRI = '" + filter.getCodicemeccanografico_autocomplete() + "' ";
			}
					
			sql = sql + " GROUP BY AI.COD_MEC_IST_PRI, AI.DES_NOM_SCU, CO.DES_COM "
					  + " ORDER BY AI.COD_MEC_IST_PRI ";

			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOIstituto vo = null;
			while (rs.next())
			{
				vo = new VOIstituto();
				vo.setCodiceMeccanografico(rs.getString("COD_MEC_IST_PRI"));
				vo.setDenominazione(rs.getString("DES_NOM_SCU"));
				vo.setComune(rs.getString("DES_COM"));
				vo.setImportoAutorizzato(rs.getBigDecimal("IMP_AUT"));
				vo.setImportoErogato(rs.getBigDecimal("IMP_PAG"));
				vo.setNumeroProgetti(rs.getInt("NUM_PRG"));
				lista.add(vo);
			}


			log.debug("end method getIstitutiBeneficiari - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOIstituto> getIstitutiScuoledelpon(VOFiltroRicerca filter, int prgEve) throws Exception
	{
		log.debug("start method getIstitutiScuoledelpon");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOIstituto> lista = new ArrayList<VOIstituto>();
		try
		{

			Object[] campi = new Object[] {prgEve, prgEve};
			int[] tipi = new int[] {Types.NUMERIC, Types.NUMERIC};

			String sql = " SELECT AI.COD_MEC_IST_PRI, AI.DES_NOM_SCU, CO.DES_COM, "
					+ " SUM (CASE WHEN PI.COD_TIP_FON = 'FESR' THEN PI.IMP_AUT_COR ELSE 0 END) IMP_AUT_FESR, "
					+ " SUM (CASE WHEN PI.COD_TIP_FON = 'FSE' THEN PI.IMP_AUT_COR ELSE 0 END) IMP_AUT_FSE, "
					+ " SUM (CASE WHEN PI.COD_TIP_FON = 'FESR' THEN 1 ELSE 0 END) NUM_PRG_FESR, "
					+ " SUM (CASE WHEN PI.COD_TIP_FON = 'FSE' THEN 1 ELSE 0 END) NUM_PRG_FSE "
					+ " FROM UFSPORPUB_OWN.VFS1010_FSEANAISTPRI AI "
					+ " LEFT OUTER JOIN TFS1003_STOPROGETTOISTITUTO PI ON (PI.PRG_EVE = ? AND PI.COD_MEC_IST_PRI = AI.COD_MEC_IST_PRI), "
					+ " VFS1017_FSEANATERRITORIO CO "
					+ " WHERE "
					+ " AI.COD_COM = CO.COD_COM "
					+ " AND AI.COD_MEC_IST_PRI IN ( "
					+ " SELECT DISTINCT AI.COD_MEC_IST_PRI "
					+ " FROM UFSPORPUB_OWN.VFS1010_FSEANAISTPRI AI "
					+ " LEFT OUTER JOIN TFS1003_STOPROGETTOISTITUTO PI ON (PI.PRG_EVE = ? AND PI.COD_MEC_IST_PRI = AI.COD_MEC_IST_PRI) "
					+ " LEFT OUTER JOIN VFS1025_FSEPGTCATSOTCATINT FSEPGTCAT ON (FSEPGTCAT.PRG_PGT = PI.PRG_PGT), "
					+ " VFS1017_FSEANATERRITORIO CO "
					+ " WHERE "
					+ " AI.COD_COM = CO.COD_COM "
					+ " AND CO.COD_REG NOT IN ('VA','TR') ";
			
			if (filter.getCodiceRegione() != null && !filter.getCodiceRegione().isEmpty()){
				sql = sql + " AND CO.COD_REG = '" + filter.getCodiceRegione() + "' ";
			}

			if (filter.getCodiceProvincia() != null && !filter.getCodiceProvincia().isEmpty()){
				sql = sql + " AND CO.COD_PRV = '" + filter.getCodiceProvincia() + "' ";
			}

			if (filter.getCodiceComune() != null && !filter.getCodiceComune().isEmpty()){
				sql = sql + " AND CO.COD_COM = '" + filter.getCodiceComune() + "' ";
			}

			if (filter.getCodicemeccanografico_autocomplete() != null && !filter.getCodicemeccanografico_autocomplete().isEmpty()){
				sql = sql + " AND AI.COD_MEC_IST_PRI = '" + filter.getCodicemeccanografico_autocomplete() + "' ";
			}
			
			if (filter.getCodiceTipoIstruzione() != null && !filter.getCodiceTipoIstruzione().isEmpty()){
				
				if (filter.getCodiceTipoIstruzione().equals("CPIA")){
					sql = sql + " AND AI.COD_CAR_SCU = '1' ";
				}else if (filter.getCodiceTipoIstruzione().equals("PC")){
					sql = sql + " AND AI.COD_TIP_SIT IN ('AA','EE','IC','MM') ";
				}else if (filter.getCodiceTipoIstruzione().equals("SC")){
					sql = sql + " AND AI.COD_TIP_SIT NOT IN ('AA','EE','IC','MM') ";
				}
			}

			if (filter.getTipoFondo() != null && !filter.getTipoFondo().isEmpty()){

				if (filter.getTipoFondo().equals("NESSUNO")){
					
					sql = sql + " AND FSEPGTCAT.COD_TIP_FON IS NULL ";

				}else{

					sql = sql + " AND FSEPGTCAT.COD_TIP_FON = '" + filter.getTipoFondo() + "' ";

					if (filter.getCodiceSottoCategoria() != null && !filter.getCodiceSottoCategoria().isEmpty()){

						sql = sql + " AND FSEPGTCAT.COD_SOT_CAT = '" + filter.getCodiceSottoCategoria() + "' ";
					}
				}
			}
			
			
			sql = sql + " ) GROUP BY AI.COD_MEC_IST_PRI, AI.DES_NOM_SCU, CO.DES_COM "
					  + " ORDER BY AI.COD_MEC_IST_PRI ";

			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOIstituto vo = null;
			while (rs.next())
			{
				vo = new VOIstituto();
				vo.setCodiceMeccanografico(rs.getString("COD_MEC_IST_PRI"));
				vo.setDenominazione(rs.getString("DES_NOM_SCU"));
				vo.setComune(rs.getString("DES_COM"));
				
				vo.setImportoAutorizzatoFSE(rs.getBigDecimal("IMP_AUT_FSE"));
				vo.setNumeroProgettiFSE(rs.getInt("NUM_PRG_FSE"));
				
				vo.setImportoAutorizzatoFESR(rs.getBigDecimal("IMP_AUT_FESR"));
				vo.setNumeroProgettiFESR(rs.getInt("NUM_PRG_FESR"));
				lista.add(vo);
			}


			log.debug("end method getIstitutiScuoledelpon - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public VOEvento getPrgEvePubblicato(String tipoEvento) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		VOEvento evento = null;
		try
		{

			Object[] campi = new Object[] {tipoEvento};
			int[] tipi = new int[] {Types.VARCHAR};

			String sql = " SELECT PRG_EVE, TO_CHAR(DAT_RIF_GES, 'DD/MM/YYYY') DAT_RIF_GES FROM TFS1001_EVENTO WHERE FLG_PUB = 1 AND COD_TIP_EVE = ? ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				evento = new VOEvento();
				evento.setPrgEve(rs.getInt("PRG_EVE"));
				evento.setDataAggiornamento(rs.getString("DAT_RIF_GES"));
			}else{
				
				throw new Exception("ATTENZIONE PUBBLICAZIONE DATI NON PRESENTE");
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return evento;
	}

	public List<VOProgettoIstituto> getProgettiIstituto(String codiceMeccanografico, String anno, String codTipFon, int prgEve) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOProgettoIstituto> lista = new ArrayList<VOProgettoIstituto>();
		try
		{

			Object[] campi = new Object[] {codiceMeccanografico, prgEve, codTipFon};
			int[] tipi = new int[] {Types.VARCHAR, Types.NUMERIC, Types.VARCHAR};

			String sql = " SELECT AP.COD_PGT, AP.DES_TIT_PGT, AZ.DES_BRE_AZI, "
					+ "AZ.COD_AZI, SA.DES_PER, SA.COD_PER, ST.DES_STA STATO, PI.IMP_AUT_COR IMP_AUT, PI.IMP_PAG IMP_PAG, PI.PRG_PGT"
					+ " FROM UFSPORPUB_OWN.TFS1003_STOPROGETTOISTITUTO PI, "
					+ " VFS1009_FSEANAPROGETTI AP, VFS1012_FSEANAAZIONI AZ, UFSPORPUB_OWN.VFS1013_FSEANASOTTOAZIONI SA, "
					+ " VFS1011_FSEANASTATI ST "
					+ " WHERE "
					+ " COD_MEC_IST_PRI = ? "
					+ " AND PI.PRG_EVE = ? "
					+ " AND PI.COD_TIP_FON = ? "
					+ " AND PI.PRG_PGT = AP.PRG_PGT "
					+ " AND PI.PRG_AZI = AZ.PRG_AZI "
					+ " AND PI.PRG_PER = SA.PRG_PER "
					+ " AND PI.PRG_STA_PGT = ST.PRG_STA ";
			
			if (anno != null && !anno.isEmpty()){
				sql = sql + " AND PI.PER_ANN_BAN = " + anno;
			}
			
			sql = sql + " ORDER BY 1 ";
			
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOProgettoIstituto vo = null;
			while (rs.next())
			{
				vo = new VOProgettoIstituto();
				vo.setPrgProgetto(rs.getInt("PRG_PGT"));
				vo.setCodiceProgetto(rs.getString("COD_PGT"));
				vo.setTitolo(rs.getString("DES_TIT_PGT"));
				vo.setAzione(rs.getString("DES_BRE_AZI"));
				vo.setCodiceAzione(rs.getString("COD_AZI"));
				vo.setSottoazione(rs.getString("DES_PER"));
				vo.setCodiceSottoazione(rs.getString("COD_PER"));
				vo.setStato(rs.getString("STATO"));
				vo.setImportoAutorizzato(rs.getBigDecimal("IMP_AUT"));
				vo.setImportoErogato(rs.getBigDecimal("IMP_PAG"));
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOFornitore> getFornitoriBeneficiari(int prgEvePubblicazione) throws Exception
	{
		log.debug("start method getFornitoriBeneficiari");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOFornitore> lista = new ArrayList<VOFornitore>();
		try
		{

			Object[] campi = new Object[] {prgEvePubblicazione};
			int[] tipi = new int[] {Types.NUMERIC};

			String sql =  " SELECT AF.PRG_FOR, AF.COD_FOR, AF.DES_FOR, "
						+ " SUM(PF.IMP_AUT_COR) IMP_AUT, SUM(PF.IMP_PAG) IMP_PAG, COUNT(*) NUM_PRG "
						+ " FROM TFS1004_STOPROGETTOFORNITORE PF, UFSPORPUB_OWN.VFS1016_FSEANAFORNITORI AF "
						+ " WHERE "
						+ " PF.PRG_FOR = AF.PRG_FOR "
						+ " AND PF.PRG_EVE = ? "
			 			+ " GROUP BY AF.PRG_FOR, AF.COD_FOR, AF.DES_FOR "
					    + " ORDER BY AF.DES_FOR ";

			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOFornitore vo = null;
			while (rs.next())
			{
				vo = new VOFornitore();
				vo.setPrgFornitore(rs.getString("PRG_FOR"));
				vo.setCodFornitore(rs.getString("COD_FOR"));
				vo.setDenominazione(rs.getString("DES_FOR"));
				vo.setImportoAutorizzato(rs.getBigDecimal("IMP_AUT"));
				vo.setImportoErogato(rs.getBigDecimal("IMP_PAG"));
				vo.setNumeroProgetti(rs.getInt("NUM_PRG"));
				lista.add(vo);
			}


			log.debug("end method getFornitoriBeneficiari - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public VOFornitore getFornitore(String prgFornitore) throws Exception
	{
		log.debug("start method getFornitore " + prgFornitore);
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		VOFornitore out = null;
		try
		{

			Object[] campi = new Object[] {prgFornitore};
			int[] tipi = new int[] {Types.NUMERIC};

			String sql = " SELECT "
					+ " PRG_FOR, COD_FOR, DES_FOR "
					+ " FROM UFSPORPUB_OWN.VFS1016_FSEANAFORNITORI "
					+ " WHERE PRG_FOR = ? ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				out = new VOFornitore();
				out.setPrgFornitore(rs.getString("PRG_FOR"));
				out.setCodFornitore(rs.getString("COD_FOR"));
				out.setDenominazione(rs.getString("DES_FOR"));
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return out;
	}

	public List<VOProgettoIstituto> getProgettiFornitore(String prgFornitore, int prgEve) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOProgettoIstituto> lista = new ArrayList<VOProgettoIstituto>();
		try
		{

			Object[] campi = new Object[] {prgFornitore, prgEve};
			int[] tipi = new int[] {Types.NUMERIC, Types.NUMERIC};

			String sql = " SELECT PF.COD_PGT, PF.DES_TIT_PGT, AZ.DES_BRE_AZI, AZ.COD_AZI, SA.DES_PER, SA.COD_PER, ST.DES_STA STATO, PF.IMP_AUT_COR IMP_AUT, PF.IMP_PAG IMP_PAG, "
					+ " TO_CHAR(PF.DAT_PRO,'DD/MM/YYYY') DAT_PRO, PF.NUM_PRO "
					+ " FROM UFSPORPUB_OWN.TFS1004_STOPROGETTOFORNITORE PF, "
					+ " VFS1012_FSEANAAZIONI AZ, UFSPORPUB_OWN.VFS1013_FSEANASOTTOAZIONI SA, "
					+ " VFS1011_FSEANASTATI ST "
					+ " WHERE "
					+ " PF.PRG_FOR = ? "
					+ " AND PF.PRG_EVE = ? "
					+ " AND PF.PRG_AZI = AZ.PRG_AZI "
					+ " AND PF.PRG_PER = SA.PRG_PER "
					+ " AND PF.PRG_STA_PGT = ST.PRG_STA "
					+ " ORDER BY 1 ";
			
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOProgettoIstituto vo = null;
			while (rs.next())
			{
				vo = new VOProgettoIstituto();
				vo.setCodiceProgetto(rs.getString("COD_PGT"));
				vo.setTitolo(rs.getString("DES_TIT_PGT"));
				vo.setAzione(rs.getString("DES_BRE_AZI"));
				vo.setCodiceAzione(rs.getString("COD_AZI"));
				vo.setSottoazione(rs.getString("DES_PER"));
				vo.setCodiceSottoazione(rs.getString("COD_PER"));
				vo.setStato(rs.getString("STATO"));
				vo.setImportoAutorizzato(rs.getBigDecimal("IMP_AUT"));
				vo.setImportoErogato(rs.getBigDecimal("IMP_PAG"));
				vo.setDataProtocollo(rs.getString("DAT_PRO"));
				vo.setNumeroProtocollo(rs.getString("NUM_PRO"));
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<LabelValueBean> getTipoBandoList() throws Exception
	{
		log.debug("start method getTipoBandoList");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			String sql = " SELECT DES_TIP_BAN_SCU COD, DES_TIP_BAN_SCU DESCR FROM VFS1021_FSETIPBANSCU ORDER BY 1 ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			LabelValueBean vo = null;
			while (rs.next())
			{
				vo = new LabelValueBean();
				vo.setValue(rs.getString("COD"));
				vo.setLabel(rs.getString("DESCR"));
				lista.add(vo);
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOIstituto> getIstitutiBandi(VOFiltroRicerca filter) throws Exception
	{
		log.debug("start method getIstitutiBandi");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOIstituto> lista = new ArrayList<VOIstituto>();
		try
		{

			Object[] campi = new Object[] {filter.getTipoBando()};
			int[] tipi = new int[] {Types.VARCHAR};

			String sql = " SELECT AI.COD_MEC_IST_PRI, AI.DES_NOM_SCU, CO.DES_COM, COUNT(*) NUM_PRG "
					+ " FROM UFSPORPUB_OWN.VFS1020_FSEBANDOSCUOLA BS, UFSPORPUB_OWN.VFS1010_FSEANAISTPRI AI, VFS1017_FSEANATERRITORIO CO "
					+ " WHERE "
					+ " BS.COD_MEC_IST_PRI = AI.COD_MEC_IST_PRI  "
					+ " AND AI.COD_COM = CO.COD_COM "
					+ " AND BS.DES_TIP_BAN = ? ";
			
			if (filter.getCodiceRegione() != null && !filter.getCodiceRegione().isEmpty()){
				sql = sql + " AND CO.COD_REG = '" + filter.getCodiceRegione() + "' ";
			}

			if (filter.getCodiceProvincia() != null && !filter.getCodiceProvincia().isEmpty()){
				sql = sql + " AND CO.COD_PRV = '" + filter.getCodiceProvincia() + "' ";
			}

			if (filter.getCodicemeccanografico_autocomplete() != null && !filter.getCodicemeccanografico_autocomplete().isEmpty()){
				sql = sql + " AND AI.COD_MEC_IST_PRI = '" + filter.getCodicemeccanografico_autocomplete() + "' ";
			}
			if (filter.getStato() != null && filter.getStato().equals("I")){
				sql = sql + " AND SYSDATE BETWEEN BS.DAT_PUB_BAN AND BS.DAT_SCA_BAN ";
			}
			if (filter.getStato() != null && filter.getStato().equals("S")){
				sql = sql + " AND SYSDATE NOT BETWEEN BS.DAT_PUB_BAN AND BS.DAT_SCA_BAN ";
			}
					
			sql = sql + " GROUP BY AI.COD_MEC_IST_PRI, AI.DES_NOM_SCU, CO.DES_COM "
					  + " ORDER BY AI.COD_MEC_IST_PRI ";

			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOIstituto vo = null;
			while (rs.next())
			{
				vo = new VOIstituto();
				vo.setCodiceMeccanografico(rs.getString("COD_MEC_IST_PRI"));
				vo.setDenominazione(rs.getString("DES_NOM_SCU"));
				vo.setComune(rs.getString("DES_COM"));
				vo.setNumeroProgetti(rs.getInt("NUM_PRG"));
				lista.add(vo);
			}


			log.debug("end method getIstitutiBandi - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	
	public String getDataAggiornamentoIstitutiBandi() throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String out = "";
		try
		{

			Object[] campi = new Object[] {};
			int[] tipi = new int[] {};

			String sql = " SELECT DAT_ULT_EXP FROM UFSPORPUB_OWN.VFS1020_FSEBANDOSCUOLA ";
						
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				out = rs.getString("DAT_ULT_EXP");
			}

		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return out;
	}

	public String getDataAggiornamentoBandiAmministrazione() throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String out = "";
		try
		{

			Object[] campi = new Object[] {};
			int[] tipi = new int[] {};

			String sql = " SELECT DAT_ULT_EXP FROM UFSPORPUB_OWN.VFS1018_FSEPGTISTITUTI ";
						
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			if (rs.next())
			{
				out = rs.getString("DAT_ULT_EXP");
			}

		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return out;
	}
	
	public List<VOBandoIstituto> getBandiIstituto(String codiceMeccanografico, String tipoBando) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOBandoIstituto> lista = new ArrayList<VOBandoIstituto>();
		try
		{

			Object[] campi = new Object[] {codiceMeccanografico, tipoBando};
			int[] tipi = new int[] {Types.VARCHAR, Types.VARCHAR};

			String sql = " SELECT COD_MOD_GAR PROCEDURA, DES_OGG_BAN DESCRIZIONE, "
					+ " TO_CHAR(DAT_PUB_BAN,'DD/MM/YYYY') DAT_PUB, "
					+ " TO_CHAR(DAT_SCA_BAN,'DD/MM/YYYY') DAT_SCA, "
					+ " COD_PGT, DAT_PUB_BAN, COD_BAN "
					+ " FROM UFSPORPUB_OWN.VFS1020_FSEBANDOSCUOLA "
					+ " WHERE "
					+ " COD_MEC_IST_PRI = ? "
					+ " AND DES_TIP_BAN = ? "
					+ " ORDER BY DAT_PUB_BAN DESC ";
			
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOBandoIstituto vo = null;
			while (rs.next())
			{
				vo = new VOBandoIstituto();
				vo.setProcedura(rs.getString("PROCEDURA"));
				vo.setDescrizione(rs.getString("DESCRIZIONE"));
				vo.setDataPubblicazione(rs.getString("DAT_PUB"));
				vo.setDataScadenza(rs.getString("DAT_SCA"));
				vo.setCodiceProgetto(rs.getString("COD_PGT"));
				vo.setCodiceBando(rs.getString("COD_BAN"));
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public VOBandoIstituto getBando(String codBando) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		VOBandoIstituto vo = null;
		try
		{

			Object[] campi = new Object[] {codBando};
			int[] tipi = new int[] {Types.NUMERIC};

			String sql = " SELECT COD_MOD_GAR PROCEDURA, DES_OGG_BAN DESCRIZIONE, "
					+ " TO_CHAR(DAT_PUB_BAN,'DD/MM/YYYY') DAT_PUB, "
					+ " TO_CHAR(DAT_SCA_BAN,'DD/MM/YYYY') DAT_SCA, "
					+ " COD_PGT, DAT_PUB_BAN, COD_BAN, "
					+ " DES_TIP_BAN, DES_TIP_FON, DAT_ANN_BAN, COD_CIG, COD_CUP, "
					+ " DES_RIF, DES_CNT, DES_LNK "
					+ " FROM UFSPORPUB_OWN.VFS1020_FSEBANDOSCUOLA "
					+ " WHERE "
					+ " COD_BAN = ? ";
			
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			while (rs.next())
			{
				vo = new VOBandoIstituto();
				vo.setProcedura(rs.getString("PROCEDURA"));
				vo.setDescrizione(rs.getString("DESCRIZIONE"));
				vo.setDataPubblicazione(rs.getString("DAT_PUB"));
				vo.setDataScadenza(rs.getString("DAT_SCA"));
				vo.setCodiceProgetto(rs.getString("COD_PGT"));
				vo.setCodiceBando(rs.getString("COD_BAN"));
				vo.setTipoBando(rs.getString("DES_TIP_BAN"));
				vo.setFondo(rs.getString("DES_TIP_FON"));
				vo.setAnno(rs.getString("DAT_ANN_BAN"));
				vo.setCig(rs.getString("COD_CIG"));
				vo.setCup(rs.getString("COD_CUP"));
				vo.setRiferimento(rs.getString("DES_RIF"));
				vo.setContatto(rs.getString("DES_CNT"));
				vo.setLink(rs.getString("DES_LNK"));
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return vo;
	}

	

	public List<VOProgettoOpendata> getProgettiIstitutiBeneficiariOpendata(int prgEve) throws Exception
	{
		log.debug("start method getProgettiIstitutiBeneficiariOpendata");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOProgettoOpendata> lista = new ArrayList<VOProgettoOpendata>();
		try
		{

			Object[] campi = new Object[] {prgEve};
			int[] tipi = new int[] {Types.NUMERIC};

			String sql = " SELECT "
					+ " AP.DES_BRE_PRO PROGRAMMA, "
					+ " PI.COD_TIP_FON FONDO, "
					+ " PI.PER_ANN_BAN ANNO, "
					+ " TER.DES_ARE_TER_FSE AREA_TERRITORIALE, "
					+ " TER.DES_REG REGIONE, "
					+ " TER.DES_PRV PROVINCIA, "
					+ " AI.COD_MEC_IST_PRI, "
					+ " AI.COD_MEC_IST_PRI || ' - ' || AI.DES_NOM_SCU BENEFICIARIO,  "
					+ " AI.COD_FIS CODICE_FISCALE_BENEFICIARIO,  "
					+ " PI.COD_PGT CODICE_PROGETTO, "
					+ " PROG.COD_CUP CODICE_UNICO_PROGETTO, "
					+ " PROG.DES_TIT_PGT DENOMINAZIONE_OPERAZIONE, "
					+ " PROG.DES_SIN_PGT SINTESI_OPERAZIONE, "
					+ " TO_CHAR(PROG.DAT_AVV,'DD/MM/YYYY') DATA_INIZIO_OPERAZIONE, "
					+ " TO_CHAR(PROG.DAT_CHI,'DD/MM/YYYY') DATA_FINE_OPERAZIONE, "
					+ " PI.IMP_AUT_COR SPESA_AMMISSIBILE, "
					+ " (SELECT DISTINCT FSP.PRC_QTA_UEU FROM VFS1023_FSEPROGRAMMAZIONE FSP, VFS1017_FSEANATERRITORIO TERR "
					+ " WHERE FSP.COD_RIP_QTA = 1 "
					+ " AND FSP.DAT_ANN_RIF = PI.PER_ANN_BAN "
					+ " AND FSP.COD_TIP_FON = PI.COD_TIP_FON "
					+ " AND FSP.COD_ARE_TER_FSE = TERR.COD_ARE_TER_FSE "
					+ " AND TERR.COD_PRV = TER.COD_PRV) TASSO_DI_COFINANZIAMENTO_EU, "
					+ " (SELECT DISTINCT FSP.PRC_QTA_NAZ FROM VFS1023_FSEPROGRAMMAZIONE FSP, VFS1017_FSEANATERRITORIO TERR "
					+ " WHERE FSP.COD_RIP_QTA = 1 "
					+ " AND FSP.DAT_ANN_RIF = PI.PER_ANN_BAN "
					+ " AND FSP.COD_TIP_FON = PI.COD_TIP_FON "
					+ " AND FSP.COD_ARE_TER_FSE = TERR.COD_ARE_TER_FSE "
					+ " AND TERR.COD_PRV = TER.COD_PRV) TASSO_DI_COFINANZIAMENTO_NAZ, "
					+ " AI.COD_CAP_SCU CAP "
					+ " FROM TFS1003_STOPROGETTOISTITUTO PI, UFSPORPUB_OWN.VFS1010_FSEANAISTPRI AI, "
					+ " VFS1017_FSEANATERRITORIO TER, VFS1022_FSEANAGPROGRAMMA AP, VFS1009_FSEANAPROGETTI PROG "
					+ " WHERE "
					+ " PI.COD_MEC_IST_PRI = AI.COD_MEC_IST_PRI "
					+ " AND PI.PRG_EVE = ? "
					+ " AND AI.COD_COM = TER.COD_COM "
					+ " AND TER.COD_REG NOT IN ('VA','TR') "
					+ " AND PI.PRG_PRO = AP.PRG_PRO "
					+ " AND PI.PRG_PGT = PROG.PRG_PGT "
					+ " ORDER BY 5, 6, 7 ";
			
			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOProgettoOpendata vo = null;
			while (rs.next())
			{
				vo = new VOProgettoOpendata();
				vo.setProgramma(escapePerOpenData(rs.getString("PROGRAMMA")));
				vo.setFondo(escapePerOpenData(rs.getString("FONDO")));
				vo.setAnno(escapePerOpenData(rs.getString("ANNO")));
				vo.setAreaTerritoriale(escapePerOpenData(rs.getString("AREA_TERRITORIALE")));
				vo.setRegione(escapePerOpenData(rs.getString("REGIONE")));
				vo.setProvincia(escapePerOpenData(rs.getString("PROVINCIA")));
				vo.setBeneficiario(escapePerOpenData(rs.getString("BENEFICIARIO")));
				vo.setCodiceFiscale(escapePerOpenData(rs.getString("CODICE_FISCALE_BENEFICIARIO")));
				vo.setCodiceProgetto(escapePerOpenData(rs.getString("CODICE_PROGETTO")));
				vo.setCupProgetto(escapePerOpenData(rs.getString("CODICE_UNICO_PROGETTO")));
				vo.setDenomOperazione(escapePerOpenData(rs.getString("DENOMINAZIONE_OPERAZIONE")));
				vo.setSintesiOperazione(escapePerOpenData(rs.getString("SINTESI_OPERAZIONE")));
				vo.setDataInizio(escapePerOpenData(rs.getString("DATA_INIZIO_OPERAZIONE")));
				vo.setDataFine(escapePerOpenData(rs.getString("DATA_FINE_OPERAZIONE")));
				vo.setSpesaAmmissibile(escapePerOpenData(rs.getString("SPESA_AMMISSIBILE")));
				vo.setTassoUE(escapePerOpenData(rs.getString("TASSO_DI_COFINANZIAMENTO_EU")));
				vo.setTassoNAZ(escapePerOpenData(rs.getString("TASSO_DI_COFINANZIAMENTO_NAZ")));
				vo.setCap(escapePerOpenData(rs.getString("CAP")));
				lista.add(vo);
			}


			log.debug("end method getProgettiIstitutiBeneficiariOpendata - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}
	
	
	public List<VOProgettoOpendata> getProgettiFornitoriBeneficiariOpendata(int prgEve) throws Exception
	{
		log.debug("start method getProgettiFornitoriBeneficiariOpendata");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOProgettoOpendata> lista = new ArrayList<VOProgettoOpendata>();
		try
		{

			Object[] campi = new Object[] {prgEve};
			int[] tipi = new int[] {Types.NUMERIC};

			String sql = " SELECT "
					+ " AP.DES_BRE_PRO PROGRAMMA, "
					+ " PF.COD_TIP_FON FONDO,  "
					+ " PF.PER_ANN_BAN ANNO, "
					+ " AF.DES_FOR BENEFICIARIO, "
					+ " NVL(AF.COD_PAR_IVA, AF.COD_FIS) CODICE_FISCALE_BENEFICIARIO, "
					+ " PF.COD_PGT CODICE_PROGETTO, "
					+ " PROG.COD_CUP CODICE_UNICO_PROGETTO, "
					+ " PROG.DES_TIT_PGT DENOMINAZIONE_OPERAZIONE, "
					+ " PROG.DES_SIN_PGT SINTESI_OPERAZIONE, "
					+ " TO_CHAR(PROG.DAT_AVV,'DD/MM/YYYY') DATA_INIZIO_OPERAZIONE, "
					+ " TO_CHAR(PROG.DAT_CHI,'DD/MM/YYYY') DATA_FINE_OPERAZIONE, "
					+ " PF.IMP_AUT_COR SPESA_AMMISSIBILE, "
					+ " (SELECT DISTINCT FSP.PRC_QTA_UEU FROM VFS1023_FSEPROGRAMMAZIONE FSP, VFS1017_FSEANATERRITORIO TERR "
					+ " WHERE FSP.COD_RIP_QTA = 1 "
					+ " AND FSP.DAT_ANN_RIF = PF.PER_ANN_BAN "
					+ " AND FSP.COD_TIP_FON = PF.COD_TIP_FON "
					+ " AND FSP.COD_ARE_TER_FSE = TERR.COD_ARE_TER_FSE "
					+ " AND TERR.COD_PRV = TER.COD_PRV) TASSO_DI_COFINANZIAMENTO_EU, "
					+ " (SELECT DISTINCT FSP.PRC_QTA_NAZ FROM VFS1023_FSEPROGRAMMAZIONE FSP, VFS1017_FSEANATERRITORIO TERR "
					+ " WHERE FSP.COD_RIP_QTA = 1 "
					+ " AND FSP.DAT_ANN_RIF = PF.PER_ANN_BAN "
					+ " AND FSP.COD_TIP_FON = PF.COD_TIP_FON "
					+ " AND FSP.COD_ARE_TER_FSE = TERR.COD_ARE_TER_FSE "
					+ " AND TERR.COD_PRV = TER.COD_PRV) TASSO_DI_COFINANZIAMENTO_NAZ, "
					+ " AF.COD_CAP CAP "
					+ " FROM TFS1004_STOPROGETTOFORNITORE PF, UFSPORPUB_OWN.VFS1016_FSEANAFORNITORI AF, "
					+ " VFS1017_FSEANATERRITORIO TER, VFS1022_FSEANAGPROGRAMMA AP, VFS1009_FSEANAPROGETTI PROG "
					+ " WHERE "
					+ " PF.PRG_FOR = AF.PRG_FOR "
					+ " AND PF.PRG_EVE = ? "
					+ " AND AF.COD_COM = TER.COD_COM "
					+ " AND TER.COD_REG NOT IN ('VA','TR') "
					+ " AND PF.PRG_PRO = AP.PRG_PRO "
					+ " AND PF.PRG_PGT = PROG.PRG_PGT "
					+ " ORDER BY 4 ";
			
			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOProgettoOpendata vo = null;
			while (rs.next())
			{
				vo = new VOProgettoOpendata();
				vo.setProgramma(escapePerOpenData(rs.getString("PROGRAMMA")));
				vo.setFondo(escapePerOpenData(rs.getString("FONDO")));
				vo.setAnno(escapePerOpenData(rs.getString("ANNO")));
				vo.setBeneficiario(escapePerOpenData(rs.getString("BENEFICIARIO")));
				vo.setCodiceFiscale(escapePerOpenData(rs.getString("CODICE_FISCALE_BENEFICIARIO")));
				vo.setCodiceProgetto(escapePerOpenData(rs.getString("CODICE_PROGETTO")));
				vo.setCupProgetto(escapePerOpenData(rs.getString("CODICE_UNICO_PROGETTO")));
				vo.setDenomOperazione(escapePerOpenData(rs.getString("DENOMINAZIONE_OPERAZIONE")));
				vo.setSintesiOperazione(escapePerOpenData(rs.getString("SINTESI_OPERAZIONE")));
				vo.setDataInizio(escapePerOpenData(rs.getString("DATA_INIZIO_OPERAZIONE")));
				vo.setDataFine(escapePerOpenData(rs.getString("DATA_FINE_OPERAZIONE")));
				vo.setSpesaAmmissibile(escapePerOpenData(rs.getString("SPESA_AMMISSIBILE")));
				vo.setTassoUE(escapePerOpenData(rs.getString("TASSO_DI_COFINANZIAMENTO_EU")));
				vo.setTassoNAZ(escapePerOpenData(rs.getString("TASSO_DI_COFINANZIAMENTO_NAZ")));
				vo.setCap(escapePerOpenData(rs.getString("CAP")));
				lista.add(vo);
			}


			log.debug("end method getProgettiFornitoriBeneficiariOpendata - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}


	public List<VOProgettoOpendata> getBandiScuoleOpendata() throws Exception
	{
		log.debug("start method getBandiScuoleOpendata");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOProgettoOpendata> lista = new ArrayList<VOProgettoOpendata>();
		try
		{

			Object[] campi = new Object[] {};
			int[] tipi = new int[] {};

			String sql = " SELECT "
					+ " AP.DES_BRE_PRO PROGRAMMA, "
					+ " BS.DES_TIP_FON FONDO, "
					+ " TER.DES_REG REGIONE, "
					+ " TER.DES_PRV PROVINCIA, "
					+ " AI.COD_MEC_IST_PRI || ' - ' || AI.DES_NOM_SCU BENEFICIARIO, "
					+ " BS.COD_PGT CODICE_PROGETTO, "
					+ " BS.COD_CUP CODICE_UNICO_PROGETTO, "
					+ " BS.COD_CIG CIG, "
					+ " BS.COD_MOD_GAR PROCEDURA, "
					+ " BS.DES_OGG_BAN OGGETTO, "
					+ " TO_CHAR(BS.DAT_PUB_BAN,'DD/MM/YYYY') DAT_PUB, "
					+ " TO_CHAR(BS.DAT_SCA_BAN,'DD/MM/YYYY') DAT_SCA, "
					+ " BS.DES_LNK URL, "
					+ " BS.DES_RIF RIFERIMENTI, "
					+ " BS.DES_CNT CONTATTI "
					+ " FROM VFS1020_FSEBANDOSCUOLA BS, UFSPORPUB_OWN.VFS1010_FSEANAISTPRI AI, "
					+ " VFS1017_FSEANATERRITORIO TER, VFS1022_FSEANAGPROGRAMMA AP "
					+ " WHERE "
					+ " BS.COD_MEC_IST_PRI = AI.COD_MEC_IST_PRI "
					+ " AND AI.COD_COM = TER.COD_COM "
					+ " AND BS.PRG_PRO = AP.PRG_PRO "
					+ " ORDER BY 3, 4, 5 ";
			
			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOProgettoOpendata vo = null;
			while (rs.next())
			{
				vo = new VOProgettoOpendata();
				vo.setProgramma(escapePerOpenData(rs.getString("PROGRAMMA")));
				vo.setFondo(escapePerOpenData(rs.getString("FONDO")));
				vo.setRegione(escapePerOpenData(rs.getString("REGIONE")));
				vo.setProvincia(escapePerOpenData(rs.getString("PROVINCIA")));
				vo.setBeneficiario(escapePerOpenData(rs.getString("BENEFICIARIO")));
				vo.setCodiceProgetto(escapePerOpenData(rs.getString("CODICE_PROGETTO")));
				vo.setCupProgetto(escapePerOpenData(rs.getString("CODICE_UNICO_PROGETTO")));
				vo.setCig(escapePerOpenData(rs.getString("CIG")));
				vo.setProcedura(escapePerOpenData(rs.getString("PROCEDURA")));
				vo.setOggetto(escapePerOpenData(rs.getString("OGGETTO")));
				vo.setDataPubblicazione(escapePerOpenData(rs.getString("DAT_PUB")));
				vo.setDataScadenza(escapePerOpenData(rs.getString("DAT_SCA")));
				vo.setUrl(escapePerOpenData(rs.getString("URL")));
				vo.setRiferimenti(escapePerOpenData(rs.getString("RIFERIMENTI")));
				vo.setContatti(escapePerOpenData(rs.getString("CONTATTI")));
				lista.add(vo);
			}


			log.debug("end method getBandiScuoleOpendata - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VOProgettoOpendata> getBandiAmministrazioneOpendata() throws Exception
	{
		log.debug("start method getBandiAmministrazioneOpendata");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOProgettoOpendata> lista = new ArrayList<VOProgettoOpendata>();
		try
		{

			Object[] campi = new Object[] {};
			int[] tipi = new int[] {};

			String sql = " SELECT "
					+ " AP.DES_BRE_PRO PROGRAMMA, "
					+ " AB.COD_TIP_FON FONDO, "
					+ " '' PROCEDURA_DI_ATTIVAZIONE, "
					+ " AB.DES_BAN OGGETTO, "
					+ " DECODE(AB.COD_CAT_BAN,'IST','ISTITUTI','FOR','FORNITORI') TIPOLOGIA_DI_BENEFICIARI, "
					+ " TO_CHAR(AB.DAT_BAN,'DD/MM/YYYY') DAT_PUB, "
					+ " TO_CHAR(AB.DAT_FIN_PRE_PIA,'DD/MM/YYYY') DAT_SCA "
					+ " FROM VFS1015_FSEANABANDO AB, VFS1022_FSEANAGPROGRAMMA AP "
					+ " WHERE "
					+ " AB.PRG_PRO = AP.PRG_PRO "
					+ " ORDER BY 4";
			
			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VOProgettoOpendata vo = null;
			while (rs.next())
			{
				vo = new VOProgettoOpendata();
				vo.setProgramma(escapePerOpenData(rs.getString("PROGRAMMA")));
				vo.setFondo(escapePerOpenData(rs.getString("FONDO")));
				vo.setProcedura(escapePerOpenData(rs.getString("PROCEDURA_DI_ATTIVAZIONE")));
				vo.setOggetto(escapePerOpenData(rs.getString("OGGETTO")));
				vo.setTipologiaBeneficiari(escapePerOpenData(rs.getString("TIPOLOGIA_DI_BENEFICIARI")));
				vo.setDataPubblicazione(escapePerOpenData(rs.getString("DAT_PUB")));
				vo.setDataScadenza(escapePerOpenData(rs.getString("DAT_SCA")));
				lista.add(vo);
			}


			log.debug("end method getBandiAmministrazioneOpendata - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}



	public List<VODistribuzioneProgetti> getDistribuzioneProgettiPerTipoIntervento(int prgEvePubblicazioneIstituti, int prgEvePubblicazioneFornitori) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VODistribuzioneProgetti> lista = new ArrayList<VODistribuzioneProgetti>();
		try
		{

			Object[] campi = new Object[] {prgEvePubblicazioneIstituti, prgEvePubblicazioneFornitori};
			int[] tipi = new int[] {Types.NUMERIC, Types.NUMERIC};

			String sql = " SELECT COD_TIP_FON, DES_TIP_INT, SUM(NUMERO_PROGETTI) NUMERO_PROGETTI, SUM(IMP_AUT) IMP_AUT FROM ( "
					+ " SELECT A.COD_TIP_FON, A.DES_TIP_INT, COUNT(DISTINCT A.PRG_PGT) NUMERO_PROGETTI, SUM(B.IMP_AUT_COR) IMP_AUT "
					+ " FROM VFS1026_FSEPGTPICCATSOTCAT A, TFS1003_STOPROGETTOISTITUTO B "
					+ " WHERE "
					+ " B.PRG_EVE = ? "
					+ " AND A.PRG_PGT = B.PRG_PGT "
					+ " GROUP BY A.COD_TIP_FON, A.DES_TIP_INT "
					+ " UNION ALL "
					+ " SELECT A.COD_TIP_FON, A.DES_TIP_INT, COUNT(DISTINCT A.PRG_PGT) NUMERO_PROGETTI, SUM(B.IMP_AUT_COR) IMP_AUT "
					+ " FROM VFS1026_FSEPGTPICCATSOTCAT A, TFS1004_STOPROGETTOFORNITORE B "
					+ " WHERE "
					+ " B.PRG_EVE = ? "
					+ " AND A.PRG_PGT = B.PRG_PGT "
					+ " GROUP BY A.COD_TIP_FON, A.DES_TIP_INT "
					+ " ) GROUP BY COD_TIP_FON, DES_TIP_INT ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VODistribuzioneProgetti vo = null;
			while (rs.next())
			{
				vo = new VODistribuzioneProgetti();
				vo.setFondo(rs.getString("COD_TIP_FON"));
				vo.setCategoriaIntervento(rs.getString("DES_TIP_INT"));
				vo.setNumeroProgetti(rs.getLong("NUMERO_PROGETTI"));
				vo.setImportoAutorizzato(rs.getDouble("IMP_AUT"));
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<LabelValueBean> getAreeTerritorialiList() throws Exception
	{
		log.debug("start method getAreeTerritorialiList");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			String sql = " SELECT DISTINCT COD_ARE_TER_FSE COD, DES_ARE_TER_FSE DESCR FROM VFS1017_FSEANATERRITORIO ORDER BY 2 ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			LabelValueBean vo = null;
			while (rs.next())
			{
				vo = new LabelValueBean();
				vo.setValue(rs.getString("COD"));
				vo.setLabel(rs.getString("DESCR"));
				lista.add(vo);
			}
			
			log.debug("end method getAreeTerritorialiList - size : " + lista.size());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VODistribuzioneProgetti> getDistribuzioneProgettiIstitutiPerAreaTerritoriale(int prgEvePubblicazioneIstituti, String areaTerritoriale) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VODistribuzioneProgetti> lista = new ArrayList<VODistribuzioneProgetti>();
		try
		{

			Object[] campi = new Object[] {prgEvePubblicazioneIstituti};
			int[] tipi = new int[] {Types.NUMERIC};

			String sql = " SELECT TER.DES_REG, COUNT(DISTINCT A.PRG_PGT) NUMERO_PROGETTI, SUM(A.IMP_AUT_COR) IMP_AUT "
					+ " FROM TFS1003_STOPROGETTOISTITUTO A, VFS1010_FSEANAISTPRI B, VFS1017_FSEANATERRITORIO TER "
					+ " WHERE "
					+ " A.PRG_EVE = ? "
					+ " AND A.COD_MEC_IST_PRI = B.COD_SCU_UT_PRI "
					+ " AND B.COD_COM = TER.COD_COM ";
			
			if (areaTerritoriale != null && !areaTerritoriale.isEmpty() && !areaTerritoriale.equals("TUTTE")){
				sql = sql + " AND TER.COD_ARE_TER_FSE = '" + areaTerritoriale + "' ";
			}
			
			sql = sql + " GROUP BY TER.DES_REG "
					  + " ORDER BY 1 ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VODistribuzioneProgetti vo = null;
			while (rs.next())
			{
				vo = new VODistribuzioneProgetti();
				vo.setRegione(rs.getString("DES_REG"));
				vo.setNumeroProgetti(rs.getLong("NUMERO_PROGETTI"));
				vo.setImportoAutorizzato(rs.getDouble("IMP_AUT"));
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VODistribuzioneProgrammazioneAreaTerritoriale> getDistribuzioneProgrammazioneAreaTerritoriale() throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VODistribuzioneProgrammazioneAreaTerritoriale> lista = new ArrayList<VODistribuzioneProgrammazioneAreaTerritoriale>();
		try
		{

			Object[] campi = new Object[] {};
			int[] tipi = new int[] {};

			String sql = " SELECT SUM(IMP_PSVIL) IMP_PSVIL,SUM(IMP_MSVIL) IMP_MSVIL,SUM(IMP_TRANS) IMP_TRANS,PRG_ASS,COD_ASS,DES_ASS "
					+ " FROM "
					+ " ( "
					+ " select SUM (CASE WHEN cod_are_ter_fse='PSVIL' THEN IMP_PRO ELSE 0 END) IMP_PSVIL, "
					+ " SUM (CASE WHEN cod_are_ter_fse='MSVIL' THEN IMP_PRO ELSE 0 END) IMP_MSVIL, "
					+ " SUM (CASE WHEN cod_are_ter_fse='TRANS' THEN IMP_PRO ELSE 0 END) IMP_TRANS, "
					+ " PRG_ASS,COD_ASS,DES_ASS,COD_ARE_TER_FSE "
					+ " FROM VFS1023_FSEPROGRAMMAZIONE "
					+ " Where "
					+ " COD_RIP_QTA = 3 "
					+ " GROUP BY PRG_ASS, COD_ASS, DES_ASS, COD_ARE_TER_FSE "
					+ " ) "
					+ " GROUP BY PRG_ASS, COD_ASS, DES_ASS "
					+ " ORDER BY 4 ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VODistribuzioneProgrammazioneAreaTerritoriale vo = null;
			while (rs.next())
			{
				vo = new VODistribuzioneProgrammazioneAreaTerritoriale();
				vo.setImportoAreaPS(rs.getDouble("IMP_PSVIL"));
				vo.setImportoAreaMS(rs.getDouble("IMP_MSVIL"));
				vo.setImportoAreaTR(rs.getDouble("IMP_TRANS"));
				vo.setCodiceAsse(rs.getString("COD_ASS"));
				vo.setDescrizioneAsse(rs.getString("DES_ASS"));
				
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VODistribuzioneProgrammatoAutorizzato> getDistribuzioneProgrammatoAutorizzato(int prgEvePubblicazioneIstituti, int prgEvePubblicazioneFornitori) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VODistribuzioneProgrammatoAutorizzato> lista = new ArrayList<VODistribuzioneProgrammatoAutorizzato>();
		try
		{

			Object[] campi = new Object[] {prgEvePubblicazioneIstituti, prgEvePubblicazioneFornitori};
			int[] tipi = new int[] {Types.NUMERIC, Types.NUMERIC};

			String sql = " select SUM(A.IMP_PRO) IMP_PRO, A.PRG_ASS, A.COD_ASS, A.DES_ASS, "
					+ " (select NVL(sum(PGTIST.imp_aut_cor),0) from TFS1003_STOPROGETTOISTITUTO PGTIST where PGTIST.prg_ass = A.PRG_ASS AND PGTIST.PRG_EVE = ?) AS IMP_AUT_COR_IST, "
					+ " (select NVL(sum(PGTFOR.imp_aut_cor),0) from TFS1004_STOPROGETTOFORNITORE PGTFOR where PGTFOR.prg_ass = A.PRG_ASS AND PGTFOR.PRG_EVE = ?) AS IMP_AUT_COR_FOR "
					+ " FROM VFS1023_FSEPROGRAMMAZIONE A "
					+ " Where "
					+ " A.COD_RIP_QTA = 3 "
					+ " GROUP BY A.PRG_ASS, A.COD_ASS, A.DES_ASS "
					+ " ORDER BY 2 ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VODistribuzioneProgrammatoAutorizzato vo = null;
			while (rs.next())
			{
				vo = new VODistribuzioneProgrammatoAutorizzato();
				vo.setImportoProgrammato(rs.getDouble("IMP_PRO"));
				vo.setImportoAutorizzatoIst(rs.getDouble("IMP_AUT_COR_IST"));
				vo.setImportoAutorizzatoFor(rs.getDouble("IMP_AUT_COR_FOR"));
				vo.setCodiceAsse(rs.getString("COD_ASS"));
				vo.setDescrizioneAsse(rs.getString("DES_ASS"));
				
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VODistribuzionePartecipanti> getDistribuzionePartecipantiPerAreaTerritoriale(int prgEvePubblicazioneIstituti) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VODistribuzionePartecipanti> lista = new ArrayList<VODistribuzionePartecipanti>();
		try
		{

			Object[] campi = new Object[] {prgEvePubblicazioneIstituti};
			int[] tipi = new int[] {Types.NUMERIC};

			String sql = " SELECT COUNT(DISTINCT AI.COD_MEC_IST_PRI) ISTITUTI, COUNT(DISTINCT PI.COD_MEC_IST_PRI) ISTITUTI_PARTECIPANTI, TER.COD_ARE_TER_FSE, TER.DES_ARE_TER_FSE "
					+ " FROM VFS1010_FSEANAISTPRI AI "
					+ " LEFT OUTER JOIN TFS1003_STOPROGETTOISTITUTO PI ON (PI.PRG_EVE = ? AND AI.COD_MEC_IST_PRI = PI.COD_MEC_IST_PRI), "
					+ " VFS1017_FSEANATERRITORIO TER "
					+ " WHERE "
					+ " AI.COD_COM = TER.COD_COM "
					+ " AND TER.COD_REG  NOT IN ('VA','TR') "
					+ " GROUP BY TER.COD_ARE_TER_FSE, TER.DES_ARE_TER_FSE "
					+ " ORDER BY TER.COD_ARE_TER_FSE ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VODistribuzionePartecipanti vo = null;
			while (rs.next())
			{
				vo = new VODistribuzionePartecipanti();
				vo.setIstituti(rs.getLong("ISTITUTI"));
				vo.setPartecipanti(rs.getLong("ISTITUTI_PARTECIPANTI"));
				vo.setDescrizioneArea(rs.getString("DES_ARE_TER_FSE"));
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VODistribuzionePartecipanti> getDistribuzionePartecipantiPerRegione(int prgEvePubblicazioneIstituti) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VODistribuzionePartecipanti> lista = new ArrayList<VODistribuzionePartecipanti>();
		try
		{

			Object[] campi = new Object[] {prgEvePubblicazioneIstituti};
			int[] tipi = new int[] {Types.NUMERIC};

			String sql = " SELECT COUNT(DISTINCT AI.COD_MEC_IST_PRI) ISTITUTI, COUNT(DISTINCT PI.COD_MEC_IST_PRI) ISTITUTI_PARTECIPANTI, TER.COD_REG, TER.DES_REG "
					+ " FROM VFS1010_FSEANAISTPRI AI "
					+ " LEFT OUTER JOIN TFS1003_STOPROGETTOISTITUTO PI ON (PI.PRG_EVE = ? AND AI.COD_MEC_IST_PRI = PI.COD_MEC_IST_PRI), "
					+ " VFS1017_FSEANATERRITORIO TER "
					+ " WHERE "
					+ " AI.COD_COM = TER.COD_COM "
					+ " AND TER.COD_REG NOT IN ('VA','TR') "
					+ " GROUP BY TER.COD_REG, TER.DES_REG "
					+ " ORDER BY TER.COD_REG ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VODistribuzionePartecipanti vo = null;
			while (rs.next())
			{
				vo = new VODistribuzionePartecipanti();
				vo.setIstituti(rs.getLong("ISTITUTI"));
				vo.setPartecipanti(rs.getLong("ISTITUTI_PARTECIPANTI"));
				vo.setDescrizioneArea(rs.getString("DES_REG"));
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}

	public List<VODistribuzionePartecipanti> getDistribuzionePartecipantiPerCicloScolastico(int prgEvePubblicazioneIstituti) throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VODistribuzionePartecipanti> lista = new ArrayList<VODistribuzionePartecipanti>();
		try
		{

			Object[] campi = new Object[] {prgEvePubblicazioneIstituti,prgEvePubblicazioneIstituti};
			int[] tipi = new int[] {Types.NUMERIC,Types.NUMERIC};

			String sql = " SELECT COUNT(DISTINCT AI.COD_MEC_IST_PRI) ISTITUTI, COUNT(DISTINCT PI.COD_MEC_IST_PRI) ISTITUTI_PARTECIPANTI, 'Primo Ciclo' CICLO "
					+ " FROM VFS1010_FSEANAISTPRI AI "
					+ " LEFT OUTER JOIN TFS1003_STOPROGETTOISTITUTO PI ON (PI.PRG_EVE = ? AND AI.COD_MEC_IST_PRI = PI.COD_MEC_IST_PRI), "
					+ " VFS1017_FSEANATERRITORIO TER "
					+ " WHERE "
					+ " AI.COD_TIP_SIT IN ('AA','EE','IC','MM') "
					+ " AND AI.COD_COM = TER.COD_COM "
					+ " AND TER.COD_REG  NOT IN ('VA','TR') "
					+ " UNION ALL "
					+ " SELECT COUNT(DISTINCT AI.COD_MEC_IST_PRI) ISTITUTI, COUNT(DISTINCT PI.COD_MEC_IST_PRI) ISTITUTI_PARTECIPANTI, 'Secondo Ciclo' CICLO "
					+ " FROM VFS1010_FSEANAISTPRI AI "
					+ " LEFT OUTER JOIN TFS1003_STOPROGETTOISTITUTO PI ON (PI.PRG_EVE = ? AND AI.COD_MEC_IST_PRI = PI.COD_MEC_IST_PRI), "
					+ " VFS1017_FSEANATERRITORIO TER "
					+ " WHERE "
					+ " AI.COD_TIP_SIT NOT IN ('AA','EE','IC','MM') "
					+ " AND AI.COD_COM = TER.COD_COM "
					+ " AND TER.COD_REG  NOT IN ('VA','TR') ";
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			VODistribuzionePartecipanti vo = null;
			while (rs.next())
			{
				vo = new VODistribuzionePartecipanti();
				vo.setIstituti(rs.getLong("ISTITUTI"));
				vo.setPartecipanti(rs.getLong("ISTITUTI_PARTECIPANTI"));
				vo.setDescrizioneArea(rs.getString("CICLO"));
				lista.add(vo);
			}


		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return lista;
	}
	
	
	/*  
	 * Metodo che restituisce i dati descrittivi del progetto nell'ambito della scheda progetto
	 */
	public VOProgettoOpendata getDatiDescrittiviProgetto(int prgPgt, int prgEve) throws Exception{
		
		log.debug("start method getDatiDescrittiviProgetto");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		VOProgettoOpendata vo = new VOProgettoOpendata();
		try
		{

			Object[] campi = new Object[] {prgPgt, prgEve};
			int[] tipi = new int[] {Types.NUMERIC, Types.NUMERIC};

			String sql = " SELECT PI.DES_TIT_PGT AS DENOMINAZIONE_OPERAZIONE, PI.DES_SIN_PGT AS SINTESI_OPERAZIONE, FS.DES_OBI, FS.DES_POF "
					   + "FROM TFS1003_STOPROGETTOISTITUTO PI, VFS1009_FSEANAPROGETTI FS "
					   + "WHERE " 
					   + "PI.PRG_PGT = ? " 
					   + "AND PI.PRG_EVE = ? "
					   + "AND PI.PRG_PGT = FS.PRG_PGT";
			
			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			while (rs.next()){
				vo = new VOProgettoOpendata();
				
				// titolo del progetto
				vo.setDenomOperazione(escapePerOpenData(rs.getString("DENOMINAZIONE_OPERAZIONE")));
				
				// descrizione del progetto
				String escape = escapePerOpenData(rs.getString("SINTESI_OPERAZIONE"));
				vo.setSintesiOperazione(escapePerDatiDescrittiviProgetto(escape));
				
				// Obiettivi specifici e risultati attesi
				vo.setDescObiettivi(escapePerDatiDescrittiviProgetto(rs.getString("DES_OBI")));
				
				// Elementi di congruità e coerenza della proposta progettuale con il POF della scuola
				vo.setDescPOF(escapePerDatiDescrittiviProgetto(rs.getString("DES_POF")));
			}

			log.debug("end method getDatiDescrittiviProgetto");
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return vo;
	}
	

	@Override
	public VOProgettoIstituto getDatiFinanziariProgetto(int prgPgt, int prgEve) throws Exception {
		
		log.debug("start method getDatiFinanziariProgetto");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		VOProgettoIstituto vo = new VOProgettoIstituto();
		
		try{

			Object[] campi = new Object[] {prgPgt, prgEve};
			int[] tipi = new int[] {Types.NUMERIC, Types.NUMERIC};

			String sql = " SELECT PI.IMP_AUT_COR, PI.IMP_PAG, PI.NUM_PRO, TO_CHAR(PI.DAT_PRO,'DD/MM/YYYY') DAT_PRO, AP.DES_EST_PRO "
						+ "FROM TFS1003_STOPROGETTOISTITUTO PI, VFS1022_FSEANAGPROGRAMMA AP " 
						+ "WHERE PI.PRG_PGT = ? " 
						+ "AND PI.PRG_EVE = ? " 
						+ "AND PI.PRG_PRO = AP.PRG_PRO";
			
			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			
			while (rs.next()){
				vo = new VOProgettoIstituto();
				vo.setImportoAutorizzato(rs.getBigDecimal("IMP_AUT_COR"));
				vo.setImportoErogato(rs.getBigDecimal("IMP_PAG"));
				vo.setNumeroProtocollo(rs.getString("NUM_PRO"));
				vo.setDataProtocollo(rs.getString("DAT_PRO"));
				vo.setTipoFinanziamento(rs.getString("DES_EST_PRO"));
			}


			log.debug("end method getDatiFinanziariProgetto");
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return vo;
	}

	@Override
	public VOProgetto getDatiProgetto(int prgPgt, int prgEve) throws Exception {
		
		log.debug("start method getDatiProgetto");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		VOProgetto vo = new VOProgetto();
		
		try{

			Object[] campi = new Object[] {prgPgt, prgEve};
			int[] tipi = new int[] {Types.NUMERIC, Types.NUMERIC};

			String sql = " SELECT PI.COD_PGT, PI.PER_ANN_BAN, TPI.DES_TIP_INT, AZ.COD_AZI, SAZ.COD_PER, SAZ.DES_PER, " 
						+ "PI.NUM_INT, PI.NUM_EFF_PAR, PI.NUM_ATT, TO_CHAR(PR.DAT_AVV,'DD/MM/YYYY') DAT_AVV, "
						+ "TO_CHAR(PR.DAT_CHI,'DD/MM/YYYY') DAT_CHI, PGT.DES_SOT_CAT, PI.COD_TIP_FON "
						+ "FROM " 
						+ "TFS1003_STOPROGETTOISTITUTO PI, VFS1026_FSEPGTPICCATSOTCAT TPI, "
						+ "VFS1012_FSEANAAZIONI AZ, UFSPORPUB_OWN.VFS1013_FSEANASOTTOAZIONI SAZ, "
						+ "VFS1009_FSEANAPROGETTI PR, VFS1025_FSEPGTCATSOTCATINT PGT "
						+ "WHERE " 
						+ "PI.PRG_PGT = ? " 
						+ "AND PI.PRG_EVE = ? " 
						+ "AND PI.PRG_PGT = PGT.PRG_PGT " 
						+ "AND PI.PRG_PGT = PR.PRG_PGT "
						+ "AND PI.PRG_PGT = TPI.PRG_PGT "
						+ "AND PI.PRG_AZI = AZ.PRG_AZI "
						+ "AND PI.PRG_PER = SAZ.PRG_PER";
			
			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);
			
			while (rs.next()){
				vo = new VOProgetto();
				vo.setCodiceProgetto(rs.getString("COD_PGT"));
				vo.setAnnoAvviso(rs.getString("PER_ANN_BAN"));
				vo.setTipoIntervento(rs.getString("DES_TIP_INT"));
				vo.setCodiceAzione(rs.getString("COD_AZI") + "/" + rs.getString("COD_PER") + "/" + rs.getString("DES_PER"));
				vo.setNumInterventi(rs.getInt("NUM_INT"));
				vo.setNumEffPartecipanti(rs.getInt("NUM_EFF_PAR"));
				vo.setNumAttestati(rs.getInt("NUM_ATT"));
				vo.setDataInizioProgetto(rs.getString("DAT_AVV"));
				vo.setDataChiusuraAttivita(rs.getString("DAT_CHI"));
				vo.setDestinatari(rs.getString("DES_SOT_CAT"));
				vo.setCodTipoFondo(rs.getString("COD_TIP_FON"));
			}


			log.debug("end method getDatiProgetto");
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return vo;
	}
	
	
	@Override
	public List<VOModuloRichiesta> getListaModuliRichiesta(int prgPgt) throws Exception {
		
		log.debug("start method getListaModuliRichiesta");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOModuloRichiesta> moduliRichiestaList = new ArrayList<VOModuloRichiesta>();
		
		try{

			Object[] campi = new Object[] {prgPgt};
			int[] tipi = new int[] {Types.NUMERIC};

			String sql = " select ANA.DES_TIT_RIC, ANA.DES_RIC, ANA.NUM_ORE, ANA.NUM_PRE_PAR, ANA.NUM_EFF_PAR, "
					    + "ANA.NUM_ATT, TO_CHAR(ANA.DAT_AVV,'DD/MM/YYYY') DAT_AVV, TO_CHAR(ANA.DAT_CHI,'DD/MM/YYYY') DAT_CHI, ANA.PRG_PGT "
					    + "from VFS1027_FSEANARICHIESTE ANA, TFS1003_STOPROGETTOISTITUTO STO "
					    + "where STO.PRG_PGT = ANA.PRG_PGT "
					    + "AND STO.PRG_PGT = ? "
					    + "GROUP BY ANA.DES_TIT_RIC, ANA.DES_RIC, ANA.NUM_ORE, ANA.NUM_PRE_PAR, ANA.NUM_EFF_PAR, "
					    + "ANA.NUM_ATT, ANA.DAT_AVV, ANA.DAT_CHI, ANA.PRG_PGT";
			
			log.debug("SQL : " + sql);
			
			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);
			
			while (rs.next()){
				VOModuloRichiesta vo = new VOModuloRichiesta();
				vo.setTitoloRichiesta(rs.getString("DES_TIT_RIC"));
				vo.setNumPartecipanti(rs.getInt("NUM_PRE_PAR"));
				vo.setNumEffPartecipanti(rs.getInt("NUM_EFF_PAR"));
				vo.setDescRichiesta(rs.getString("DES_RIC"));
				vo.setNumOre(rs.getInt("NUM_ORE"));
				vo.setNumAttestati(rs.getInt("NUM_ATT"));
				vo.setDataInizio("01/01/1900".equals(rs.getString("DAT_AVV")) ? "" : rs.getString("DAT_AVV"));
				vo.setDataFine("31/12/9999".equals(rs.getString("DAT_CHI")) ? "" : rs.getString("DAT_CHI"));
				moduliRichiestaList.add(vo);
			}


			log.debug("end method getDatiFinanziariProgetto");
		}
		catch (Exception e)
		{
			log.error(e.getMessage(),e);
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();			
		}
		return moduliRichiestaList;
	}
	
}

