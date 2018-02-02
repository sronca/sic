package it.istruzione.cercalatuascuola.dettaglio.dao.model;

import java.util.List;

public class HighChartsSeriesElement<T> {
    private String name;
    private T data;

    public HighChartsSeriesElement(String name, T data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public T getData() {
        return data;
    }

    public T getY() {
        return data;
    }

}
