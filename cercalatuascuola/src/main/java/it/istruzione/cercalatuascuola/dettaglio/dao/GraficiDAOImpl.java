package it.istruzione.cercalatuascuola.dettaglio.dao;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOGrafico;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class GraficiDAOImpl extends BaseDAO implements GraficiDAO {

	public GraficiDAOImpl(DataSource dataSource) {
		super(dataSource);
	}
	
	private Logger logger = Logger.getLogger(GraficiDAOImpl.class);

	public List<VOGrafico> getListaGraficiScuola(String codScuUt, String datAnnScoRil,
												 String codSch, String codSez)
	throws Exception {
			
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<VOGrafico> listaGrafici = new ArrayList<VOGrafico>();
		
		try {
			StringBuffer query = new StringBuffer("SELECT TG.COD_GRA, TG.DES_TIT_GRA, TG.DES_INF_GRA,") 
				.append("NVL(GU.COD_STA_PUB, 'S') COD_STA_PUB_UFF,")				
				.append("DECODE(GU.COD_STA_PUB, 'F', NVL(GS.COD_STA_PUB, 'N'), NVL(GU.COD_STA_PUB, 'S')) COD_STA_PUB_SCU ")
				.append("FROM TRS1033_TIPOGRAFICO TG, TRS1035_GRAFICOUFFICIO GU, TRS1034_GRAFICOSCUOLA GS ")							
				.append("WHERE TG.COD_SCH = GU.COD_SCH(+) ")
				.append("AND TG.COD_SEZ = GU.COD_SEZ(+) ")
				.append("AND TG.COD_GRA = GU.COD_GRA(+) ")
				.append("AND TG.COD_SCH = GS.COD_SCH(+) ")
				.append("AND TG.COD_SEZ = GS.COD_SEZ(+) ")
				.append("AND TG.COD_GRA = GS.COD_GRA(+) ")
				.append("AND GS.COD_SCU_UT(+) = ? ")
				.append("AND GS.DAT_ANN_SCO_RIL(+) = ? ")
				.append("AND TG.COD_SCH = ? ")	
				.append("AND TG.COD_SEZ = ? ")	
				.append("AND SYSDATE BETWEEN TG.DAT_INI_VAL_GRA AND TG.DAT_FIN_VAL_GRA ")
				.append("ORDER BY TG.COD_GRA");
			
			Object[] campi = new Object[]{codScuUt, datAnnScoRil, codSch, codSez};
			int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER};

			conn = getConnection();
			ps = conn.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = eseguiPreparedQuery(ps, campi, tipi, true);
			
			VOGrafico voGrafico = null;
			while(rs.next()) {																		
				voGrafico = new VOGrafico();
				voGrafico.setCodScuUt(codScuUt);
				voGrafico.setCodGra(rs.getString("COD_GRA"));
				voGrafico.setDesInfGra(rs.getString("DES_INF_GRA"));
				voGrafico.setDesTitGra(rs.getString("DES_TIT_GRA"));
				voGrafico.setCodStaPubbUff(rs.getString("COD_STA_PUB_UFF"));
				voGrafico.setCodStaPubbScu(rs.getString("COD_STA_PUB_SCU"));
																		
				listaGrafici.add(voGrafico);
			}
			
			return listaGrafici;
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

	@Override
	public VOGrafico getGraficoScuola(String codScuUt, String datAnnScoRil, String codSch, String codSez, String codGra) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            StringBuffer query = new StringBuffer("SELECT TG.COD_GRA, TG.DES_TIT_GRA, TG.DES_INF_GRA, TG.DES_NOT_GRA,")
                    .append("NVL(GU.COD_STA_PUB, 'S') COD_STA_PUB_UFF,")
                    .append("DECODE(GU.COD_STA_PUB, 'F', NVL(GS.COD_STA_PUB, 'N'), NVL(GU.COD_STA_PUB, 'S')) COD_STA_PUB_SCU, TG.ANN_SCO_RIF ")
                    .append("FROM TRS1033_TIPOGRAFICO TG, TRS1035_GRAFICOUFFICIO GU, TRS1034_GRAFICOSCUOLA GS ")
                    .append("WHERE TG.COD_SCH = GU.COD_SCH(+) ")
                    .append("AND TG.COD_SEZ = GU.COD_SEZ(+) ")
                    .append("AND TG.COD_GRA = GU.COD_GRA(+) ")
                    .append("AND TG.COD_SCH = GS.COD_SCH(+) ")
                    .append("AND TG.COD_SEZ = GS.COD_SEZ(+) ")
                    .append("AND TG.COD_GRA = GS.COD_GRA(+) ")
                    .append("AND GS.COD_SCU_UT(+) = ? ")
                    .append("AND GS.DAT_ANN_SCO_RIL(+) = ? ")
                    .append("AND TG.COD_SCH = ? ")
                    .append("AND TG.COD_SEZ = ? ")
                    .append("AND TG.COD_GRA = ? ")
                    .append("AND SYSDATE BETWEEN TG.DAT_INI_VAL_GRA AND TG.DAT_FIN_VAL_GRA ");

            Object[] campi = new Object[]{codScuUt, datAnnScoRil, codSch, codSez, codGra};
            int[] tipi = new int[]{Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER};

            conn = getConnection();
            ps = conn.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = eseguiPreparedQuery(ps, campi, tipi, true);

            VOGrafico voGrafico = new VOGrafico();
            if(rs.next()) {
                voGrafico = new VOGrafico();
                voGrafico.setCodScuUt(codScuUt);
                voGrafico.setCodGra(rs.getString("COD_GRA"));
                voGrafico.setDesInfGra(rs.getString("DES_INF_GRA"));
                voGrafico.setDesTitGra(rs.getString("DES_TIT_GRA"));
                voGrafico.setDesNotGra(rs.getString("DES_NOT_GRA"));
                voGrafico.setCodStaPubbUff(rs.getString("COD_STA_PUB_UFF"));
                voGrafico.setCodStaPubbScu(rs.getString("COD_STA_PUB_SCU"));
                voGrafico.setAnnScoRif(rs.getInt("ANN_SCO_RIF"));
            }
            
            logger.debug("getGraficoScuola : ");
            logger.debug(voGrafico.getDesTitGra());
            logger.debug(voGrafico.getDesInfGra());

            return voGrafico;
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
