package it.istruzione.ptof.helper;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.lang3.time.FastDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomCalendarDeserializer extends JsonDeserializer<Calendar> {

	@Override
	public Calendar deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		 
	            try {
	            	if(org.apache.commons.lang.StringUtils.isBlank(jp.getText())){
	            		return null;
	            	}
					return CommonsUtility.getCalendar(FastDateFormat.getInstance("dd/MM/yyyy").parse(jp.getText()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
	         
	}

	 
	
}
