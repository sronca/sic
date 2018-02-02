package it.istruzione.poninchiaro.common.util;

public class HighChartsSeriesElement<T> {
    private String name;
    private T data;
    private T secondata;

    public HighChartsSeriesElement(String name, T data) {
        this.name = name;
        this.data = data;
    }

    public HighChartsSeriesElement(String name, T data, T secondata) {
        this.name = name;
        this.data = data;
        this.secondata = secondata;
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

    public T getSecondata() {
        return secondata;
    }

}

