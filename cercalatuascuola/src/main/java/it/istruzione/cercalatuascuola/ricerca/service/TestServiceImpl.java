package it.istruzione.cercalatuascuola.ricerca.service;

import it.istruzione.cercalatuascuola.ricerca.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "testService")
public class TestServiceImpl implements TestService {
    private TestDAO testDAO;

    @Autowired
    public TestServiceImpl(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    public void execute() {
        testDAO.findTestData();
    }
}
