package it.istruzione.cercalatuascuola.dettaglio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import it.istruzione.cercalatuascuola.common.dao.BaseDAO;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOCaratteristica;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOMenu;
import it.istruzione.cercalatuascuola.dettaglio.dao.model.VOTipologia;

public class CaratteristicaDiplomatoDAOImpl extends BaseDAO implements CaratteristicaDiplomatoDAO {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	public CaratteristicaDiplomatoDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<VOMenu> getMenu(List<VOTipologia> listaVOTipologia, Integer periodoAnnoScolastico)
			throws Exception {
		LOGGER.debug("INIZIO getMenu");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		List<VOMenu> result = new ArrayList<VOMenu>();

		try {
			if (!CollectionUtils.isEmpty(listaVOTipologia) && periodoAnnoScolastico != null) {
				StringBuilder clausolaIn = new StringBuilder("");
	
				for (VOTipologia value : listaVOTipologia) {
					if (StringUtils.isNotEmpty(clausolaIn.toString())) {
						clausolaIn.append(", ");
					}
	
					clausolaIn.append("'").append(value.getCodice()).append("'");
				}
	
				StringBuilder query = new StringBuilder(
						"select trs1078.COD_SEZ_EUR, trs1078.DES_SEZ_EUR, '1' as livello, 'COMPETENZA' as area ")
								.append("from TRS1079_COMPETENZAUSCITA trs1079 ")
								.append("inner join TRS1078_TIPOSEZIONEUROPASS trs1078 on trs1079.COD_SEZ_EUR=trs1078.COD_SEZ_EUR ")
								.append("where trs1079.COD_IND_ESA in ( ").append(clausolaIn).append(") ")
								.append("and trs1079.PER_ANN_ESA = ? ")
								.append("group by trs1078.COD_SEZ_EUR, trs1078.DES_SEZ_EUR, '1' ");
	
				conn = this.getConnection();
	
				ps = conn.prepareStatement(query.toString());
	
				Object[] campi = new Object[] { periodoAnnoScolastico };
				int[] tipi = new int[] { Types.NUMERIC };
	
				rs = eseguiPreparedQuery(ps, campi, tipi, false);
	
				LOGGER.debug("getMenu query: " + query);
	
				VOMenu voMenu = new VOMenu();
				
				while (rs.next()) {
					VOTipologia tipologia = new VOTipologia();

					tipologia.setCodice(rs.getString("COD_SEZ_EUR"));
					tipologia.setDescrizione(rs.getString("DES_SEZ_EUR"));

					if (rs.getString("livello").equals("1")) {
						voMenu = new VOMenu();

						voMenu.setTipologia(tipologia);
						voMenu.setArea(rs.getString("area"));

						result.add(voMenu);
					}
				}
	
				query = new StringBuilder(
						"select trs1083.COD_TIT_CMP as COD_SEZ_EUR, trs1083.des_tit_cmp as DES_SEZ_EUR, '2' as livello, 'COMPETENZA' as area ")
								.append("from TRS1079_COMPETENZAUSCITA trs1079 ")
								.append("inner join TRS1081_ASSOTITOSOTTOTITO trs1081 on trs1079.COD_TIT_CMP=trs1081.COD_TIT_CMP and trs1079.COD_SOT_CMP=trs1081.COD_SOT_CMP ")
								.append("inner join TRS1083_TIPOTITOCOMPET trs1083 on trs1081.COD_TIT_CMP=trs1083.COD_TIT_CMP ")
								.append("where trs1079.COD_IND_ESA in ( ").append(clausolaIn).append(") ")
								.append("and trs1079.PER_ANN_ESA = ? ")
								.append("group by trs1083.COD_TIT_CMP, trs1083.des_tit_cmp, '2' ")
								.append("order by trs1083.des_tit_cmp ASC");
	
//				conn = this.getConnection();
	
				ps = conn.prepareStatement(query.toString());
	
				rs2 = eseguiPreparedQuery(ps, campi, tipi, false);
	
				LOGGER.debug("getMenu query: " + query);
	
				while (rs2.next()) {
					VOTipologia tipologia = new VOTipologia();

					tipologia.setCodice(rs2.getString("COD_SEZ_EUR"));
					tipologia.setDescrizione(rs2.getString("DES_SEZ_EUR"));

					if (rs2.getString("livello").equals("2")) {
						if (CollectionUtils.isEmpty(voMenu.getSottomenu())) {
							voMenu.setSottomenu(new ArrayList<VOTipologia>());
						}

						voMenu.getSottomenu().add(tipologia);
					}
				}
	
				query = new StringBuilder(
						"select trs1078.COD_SEZ_EUR, trs1078.DES_SEZ_EUR, '1' as livello, 'PROFILO' as area ")
								.append("from TRS1080_PROFILODIPLOMATO trs1080 ")
								.append("inner join TRS1078_TIPOSEZIONEUROPASS trs1078 on trs1080.COD_SEZ_EUR=trs1078.COD_SEZ_EUR ")
								.append("where trs1080.COD_IND_ESA in ( ").append(clausolaIn).append(") ")
								.append("and trs1080.PER_ANN_ESA = ? ")
								.append("group by trs1078.COD_SEZ_EUR, trs1078.DES_SEZ_EUR, '1' ");
	
//				conn = this.getConnection();
	
				ps = conn.prepareStatement(query.toString());
	
				rs3 = eseguiPreparedQuery(ps, campi, tipi, false);
	
				LOGGER.debug("getMenu query: " + query);
	
				voMenu = new VOMenu();

				while (rs3.next()) {
					VOTipologia tipologia = new VOTipologia();

					tipologia.setCodice(rs3.getString("COD_SEZ_EUR"));
					tipologia.setDescrizione(rs3.getString("DES_SEZ_EUR"));

					if (rs3.getString("livello").equals("1")) {
						voMenu = new VOMenu();

						voMenu.setTipologia(tipologia);
						voMenu.setArea(rs3.getString("area"));

						result.add(voMenu);
					}
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (rs2 != null) {
				rs2.close();
				rs2 = null;
			}
			if (rs3 != null) {
				rs3.close();
				rs3 = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}

		LOGGER.debug("FINE getMenu");

		return result;
	}

	@Override
	public List<VOCaratteristica> getListaCompetenza(String codiceIndirizzoEsame, Integer periodoAnnoScolastico,
			String codiceTitolo) throws Exception {
		LOGGER.debug("INIZIO getListaCompetenza");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCaratteristica> result = new ArrayList<>();

		try {
			if (StringUtils.isEmpty(codiceIndirizzoEsame) || periodoAnnoScolastico == null
					|| (StringUtils.isEmpty(codiceTitolo))) {
				throw new Exception("I parametri passati sono null");
			}

			StringBuilder query = new StringBuilder("select trs1081.DES_CMP, trs1084.DES_SOT_CMP ")
					.append("from TRS1079_COMPETENZAUSCITA trs1079 ")
					.append("inner join TRS1081_ASSOTITOSOTTOTITO trs1081 on trs1079.COD_TIT_CMP=trs1081.COD_TIT_CMP and trs1079.COD_SOT_CMP=trs1081.COD_SOT_CMP and trs1079.PRG_ASS=trs1081.PRG_ASS ")
					.append("inner join TRS1084_TIPOSOTTOTITOCOMPET trs1084 on trs1081.COD_SOT_CMP=trs1084.COD_SOT_CMP ")
					.append("where trs1079.COD_IND_ESA =? ")
					.append("and trs1079.PER_ANN_ESA = ? ")
					.append("and trs1081.COD_TIT_CMP = ?");

			conn = this.getConnection();

			ps = conn.prepareStatement(query.toString());

			Object[] campi = new Object[] { codiceIndirizzoEsame, periodoAnnoScolastico, codiceTitolo };
			int[] tipi = new int[] { Types.VARCHAR, Types.NUMERIC, Types.VARCHAR };

			rs = eseguiPreparedQuery(ps, campi, tipi, false);

			LOGGER.debug("getListaCompetenza query: " + query);

			String descrizioneCompetenza ="";

			VOCaratteristica vo = null;

			while (rs.next()) {
				if (!rs.getString("DES_SOT_CMP").equalsIgnoreCase(descrizioneCompetenza)) {
					if (vo!=null){
						result.add(vo);
					}
					
					vo = new VOCaratteristica();

					vo.setSottotitolo(rs.getString("DES_SOT_CMP"));

					descrizioneCompetenza = rs.getString("DES_SOT_CMP");
				}

				if (CollectionUtils.isEmpty(vo.getListaCaratteristica())){
					vo.setListaCaratteristica(new ArrayList<String>());
				}
				
				vo.getListaCaratteristica().add(rs.getString("DES_CMP"));
			}
			
			//Aggiungo l'ultimo elemento elaborato
			if (vo!=null){
				result.add(vo);
			}
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}

		LOGGER.debug("FINE getListaCompetenza");

		return result;
	}
	
	@Override
	public List<VOCaratteristica> getListaProfiloDiplomato(String codiceIndirizzoEsame, Integer periodoAnnoScolastico)
			throws Exception {
		LOGGER.debug("INIZIO getListaProfiloDiplomato");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VOCaratteristica> result = new ArrayList<>();

		try {
			if (StringUtils.isEmpty(codiceIndirizzoEsame) || periodoAnnoScolastico == null) {
				throw new Exception("I parametri passati sono null");
			}

			StringBuilder query = new StringBuilder("select trs1085.DES_TIT_ATT, trs1082.DES_ATT " )
					.append("from TRS1080_PROFILODIPLOMATO trs1080 " )
					.append("inner join TRS1086_ASSOTITDESATTPROF trs1086 on trs1080.COD_TIT_ATT=trs1086.COD_TIT_ATT and trs1080.COD_DES_ATT=trs1086.COD_DES_ATT ")
					.append("inner join TRS1085_TIPTITATTPROF trs1085 on trs1086.COD_TIT_ATT=trs1085.COD_TIT_ATT ")
					.append("inner join TRS1082_TIPDESATTPROF trs1082 on trs1086.COD_DES_ATT=trs1082.COD_DES_ATT ")
					.append("where trs1080.COD_IND_ESA = ? ")
					.append("and trs1080.PER_ANN_ESA = ?");

			conn = this.getConnection();

			ps = conn.prepareStatement(query.toString());

			Object[] campi = new Object[] { codiceIndirizzoEsame, periodoAnnoScolastico };
			int[] tipi = new int[] { Types.VARCHAR, Types.NUMERIC };

			rs = eseguiPreparedQuery(ps, campi, tipi, false);

			LOGGER.debug("getListaProfiloDiplomato query: " + query);

			String descrizioneTitolo = "";

			VOCaratteristica vo = null;

			while (rs.next()) {
				if (!rs.getString("DES_TIT_ATT").equalsIgnoreCase(descrizioneTitolo)) {
					if (vo != null) {
						result.add(vo);
					}

					vo = new VOCaratteristica();

					vo.setSottotitolo(rs.getString("DES_TIT_ATT"));

					descrizioneTitolo = rs.getString("DES_TIT_ATT");
				}

				if (CollectionUtils.isEmpty(vo.getListaCaratteristica())) {
					vo.setListaCaratteristica(new ArrayList<String>());
				}

				vo.getListaCaratteristica().add(rs.getString("DES_ATT"));
			}

			// Aggiungo l'ultimo elemento elaborato
			if (vo != null) {
				result.add(vo);
			}
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}

		LOGGER.debug("FINE getListaProfiloDiplomato");

		return result;
	}
	
}
