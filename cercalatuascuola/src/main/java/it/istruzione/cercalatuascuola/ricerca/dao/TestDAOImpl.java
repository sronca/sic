package it.istruzione.cercalatuascuola.ricerca.dao;

import it.istruzione.cercalatuascuola.ricerca.dao.model.TestVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestDAOImpl implements TestDAO {
    private JdbcTemplate jdbcTemplate;

    public TestDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TestVO> findTestData() {
        String sql = "select id from test";

        return jdbcTemplate.query(sql, new RowMapper<TestVO>() {
            public TestVO mapRow(ResultSet resultSet, int i) throws SQLException {
                TestVO testVO = new TestVO();
                testVO.setId(resultSet.getString("id"));
                return testVO;
            }
        });
    }
}
