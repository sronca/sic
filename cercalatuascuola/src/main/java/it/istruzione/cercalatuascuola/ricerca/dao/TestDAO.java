package it.istruzione.cercalatuascuola.ricerca.dao;

import it.istruzione.cercalatuascuola.ricerca.dao.model.TestVO;

import java.util.List;

public interface TestDAO {
    List<TestVO> findTestData();
}
