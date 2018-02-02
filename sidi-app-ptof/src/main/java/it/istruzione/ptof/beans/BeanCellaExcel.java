package it.istruzione.ptof.beans;

public class BeanCellaExcel extends BaseDTO {
	private static final long serialVersionUID = -8440825237339898648L;

	private Object contenuto;
	private String style;
	
	private Integer StartMergeColumns;
	private Integer EndMergeColumns;
	
	public BeanCellaExcel(){}
	
	public BeanCellaExcel(Object contenuto) {
		super();
		this.contenuto = contenuto;
	}
	
	public BeanCellaExcel(Object contenuto, String style) {
		super();
		this.contenuto = contenuto;
		this.style = style;
	}
	
	public BeanCellaExcel(Object contenuto, String style, Integer startMergeColumns, Integer endMergeColumns) {
		super();
		this.contenuto = contenuto;
		this.style = style;
		StartMergeColumns = startMergeColumns;
		EndMergeColumns = endMergeColumns;
	}

	public Object getContenuto() {
		return contenuto;
	}

	public void setContenuto(Object contenuto) {
		this.contenuto = contenuto;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	public Integer getStartMergeColumns() {
		return StartMergeColumns;
	}

	public void setStartMergeColumns(Integer startMergeColumns) {
		StartMergeColumns = startMergeColumns;
	}

	public Integer getEndMergeColumns() {
		return EndMergeColumns;
	}

	public void setEndMergeColumns(Integer endMergeColumns) {
		EndMergeColumns = endMergeColumns;
	}
}
