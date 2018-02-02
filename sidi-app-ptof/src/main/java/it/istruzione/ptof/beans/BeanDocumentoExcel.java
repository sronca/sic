package it.istruzione.ptof.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanDocumentoExcel extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3939445580590152157L;
	
	private String titlePagina ="";
	
	private Map<Integer,Integer> customColumns = new HashMap<>();
	private Map<String, String> headerPage = new HashMap<>();
	private List<BeanCellaExcel> listTitoloColonneMerge = new ArrayList<BeanCellaExcel>();
	private List<BeanCellaExcel> listTitoloColonne = new ArrayList<BeanCellaExcel>();
	private List<List<Object>> listRows = new ArrayList<List<Object>>();
	private List<List<BeanCellaExcel>> listFooters = new ArrayList<List<BeanCellaExcel>>();
	
	public BeanDocumentoExcel() {
	};
	 
	//Methods
	
	public void addTitolo(BeanCellaExcel titolo){
		listTitoloColonne.add(titolo);
	}
	
	public void addTitolo(BeanCellaExcel ...titoli ){
		for (BeanCellaExcel titolo : titoli) {
			listTitoloColonne.add(titolo);
		}
	}
	
	public void addTitoloColonneMerge(BeanCellaExcel ...titoli ){
		for (BeanCellaExcel titolo : titoli) {
			listTitoloColonneMerge.add(titolo);
		}
	}
	
	public void addColumns(Object ...titoli ){
		List<Object> row = new ArrayList<>();
		for (Object col : titoli) {
			row.add(col);
		}
		listRows.add(row);
	}
	
	public void addFooter(BeanCellaExcel ...beanCellaExcels ){
		List<BeanCellaExcel> row = new ArrayList<>();
		for (BeanCellaExcel col : beanCellaExcels) {
			row.add(col);
		}
		listFooters.add(row);
	}
	
	// Getters and Setters

	public String getTitlePagina() {
		return titlePagina;
	}

	public void setTitlePagina(String titlePagina) {
		this.titlePagina = titlePagina;
	}

	public Map<String, String> getHeaderPage() {
		return headerPage;
	}

	public void setHeaderPage(Map<String, String> headerPage) {
		this.headerPage = headerPage;
	}

	public List<BeanCellaExcel> getListTitoloColonne() {
		return listTitoloColonne;
	}
	
	public void setListTitoloColonne(List<BeanCellaExcel> listTitoloColonne) {
		this.listTitoloColonne = listTitoloColonne;
	}

	public List<List<Object>> getListRows() {
		return listRows;
	}

	public void setListRows(List<List<Object>> listRows) {
		this.listRows = listRows;
	}
	public Map<Integer, Integer> getCustomColumns() {
		return customColumns;
	}

	public void setCustomColumns(Map<Integer, Integer> customColumns) {
		this.customColumns = customColumns;
	}
	
	public List<BeanCellaExcel> getListTitoloColonneMerge() {
		return listTitoloColonneMerge;
	}

	public void setListTitoloColonneMerge(List<BeanCellaExcel> listTitoloColonneMerge) {
		this.listTitoloColonneMerge = listTitoloColonneMerge;
	}

	public List<List<BeanCellaExcel>> getListFooters() {
		return listFooters;
	}

	public void setListFooters(List<List<BeanCellaExcel>> listFooters) {
		this.listFooters = listFooters;
	}
}
