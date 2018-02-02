package it.istruzione.poninchiaro.common.dao;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class BaseDAO {
	private DataSource dataSource;


	private DecimalFormat decimalFormat = new DecimalFormat("#0.##");



	public BaseDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected Connection getConnection() throws Exception
	{
		return dataSource.getConnection();
	}
	protected ResultSet eseguiPreparedQuery(PreparedStatement preparedStatement, Object parValues[], int[] parTypes, boolean isMaxRows) throws SQLException
	{
		if (parValues != null) {
			for (int i = 0; i < parValues.length; i++) {
				preparedStatement.setObject(i + 1, parValues[i], parTypes[i]);
			}	
		}
		
		if(isMaxRows) {
			preparedStatement.setMaxRows(1000);
		}
		
		return preparedStatement.executeQuery();
	}	
	
	protected int eseguiPreparedUpdate(PreparedStatement preparedStatement, Object parValues[], int[] parTypes) throws SQLException
	{
		if (parValues != null) {
			for (int i = 0; i < parValues.length; i++) {
				preparedStatement.setObject(i + 1, parValues[i], parTypes[i]);
			}	
		}
		
		return preparedStatement.executeUpdate();				
	}	
	
	protected String checkNull(String dato)
	{
		if (null != dato) {
			return dato.trim();
		}
		
		return "";
	}
	
	protected BigDecimal check0(String in){
		return in == null || "".equals(in.trim()) ? new BigDecimal(0) : new BigDecimal(in.trim());
	}
	
	protected String checkOne(String in){
		return in == null || "".equals(in.trim()) ? "1" : in.trim();
	}
	
	protected String checkZero(String in){
		return in == null || "".equals(in.trim()) ? "0" : in.trim();
	}
	
	protected BigDecimal replaceForDivideBig(BigDecimal denominatore) {
		if (denominatore == null || "0".equals(denominatore.toString())) {
			return new BigDecimal(1);
		} else {
			return denominatore;
		}
	}
}
