package it.istruzione.cercalatuascuola.dettaglio.dao.model;

public enum EnumIndiceDiTempestivita {
	i1(1,"I Trimestre"),
	i2(2,"II Trimestre"),
	i3(3,"III Trimestre"),
	i4(4,"IV Trimestre"),
	i5(5,"Annuale");
	
	private int indice;
	private String descrizione;
	
	private EnumIndiceDiTempestivita(int indice,  String descrizione)
	{
		this.indice = indice;
		this.descrizione= descrizione;
	}

	public int getIndice() {
		return indice;
	}

	public String getDescrizione() {
		return descrizione;
	}



}

