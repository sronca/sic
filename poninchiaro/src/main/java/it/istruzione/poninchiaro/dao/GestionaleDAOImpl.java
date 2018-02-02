package it.istruzione.poninchiaro.dao;

import it.istruzione.poninchiaro.common.dao.BaseDAO;
import it.istruzione.poninchiaro.common.util.LabelValueBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class GestionaleDAOImpl extends BaseDAO implements GestionaleDAO
{
	private static Logger log = Logger.getLogger(GestionaleDAOImpl.class);
	
	public GestionaleDAOImpl(DataSource dataSource) throws SQLException, NamingException
	{
		super(dataSource);
	}
	
	public Map<String,List<LabelValueBean>> getGlossario() throws Exception
	{
		log.debug("start method getGlossario");
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String,List<LabelValueBean>> glossario = new LinkedHashMap<>();
		try
		{
			Object[] campi = new Object[0];
			int[] tipi = new int[0];

			String sql = " SELECT UPPER(SUBSTR(DES_HEL,1,1)) LETTERA, DES_HEL PAROLA, DES_MES DESCRIZIONE " +
						 " FROM TFS1042_GESTIONEHELPONLINE " +
					     " WHERE COD_TIP_HEL = 'GLS' AND FLG_ATT = 1 " +
					     " ORDER BY 2 ASC ";

			ps = con.prepareStatement(sql);
			rs = eseguiPreparedQuery(ps, campi, tipi,false);

			LabelValueBean vo = null;
			while (rs.next())
			{
				if (!glossario.containsKey(rs.getString("LETTERA"))){
					glossario.put(rs.getString("LETTERA"), new LinkedList<LabelValueBean>());
				}
				vo = new LabelValueBean();
				vo.setLabel(rs.getString("PAROLA"));
				vo.setValue(rs.getString("DESCRIZIONE").replaceAll("\\<.*?>",""));
				glossario.get(rs.getString("LETTERA")).add(vo);
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
		return glossario;
	}
	
	
}

