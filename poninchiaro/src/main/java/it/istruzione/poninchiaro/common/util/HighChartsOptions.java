package it.istruzione.poninchiaro.common.util;

import java.util.List;

public class HighChartsOptions<T> {
    private String title;
    private String info;
    private String note;
    private List<HighChartsSeriesElement<T>> series;
    private List<String> categories;

    public HighChartsOptions(String title, String info, String note, List<HighChartsSeriesElement<T>> series) {
        this.title = title;
        this.info = info;
        this.note = note;
        this.series = series;
    }

    public HighChartsOptions(String title, String info, String note, List<HighChartsSeriesElement<T>> series, List<String> categories) {
        this.title = title;
        this.info = info;
        this.note = note;
        this.series = series;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getNote() {
        return note;
    }
    
    public List<HighChartsSeriesElement<T>> getSeries() {
        return series;
    }

    public List<String> getCategories() {
        return categories;
    }
}
