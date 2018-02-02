package it.istruzione.cercalatuascuola.ricerca.service;

import org.springframework.stereotype.Service;

@Service(value = "testService2")
public class TestService2Impl implements TestService {

    public void execute() {
        System.out.println("TestService2Impl");
    }
}
