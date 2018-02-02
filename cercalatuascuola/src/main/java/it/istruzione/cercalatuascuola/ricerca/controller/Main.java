package it.istruzione.cercalatuascuola.ricerca.controller;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String queryString = "?page=111&apage=1&rapida=prova+ricerca+spazi&tipoRicerca=RAPIDA&page=1234&x=3";
		String appo = queryString.replaceAll("(\\?page=)\\d+", "?").replaceAll("(\\&page=)\\d+", "").replaceAll("\\?&", "?");
		System.out.println(queryString);
		System.out.println(appo);
		
	}

}
