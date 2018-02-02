package it.istruzione.ptof.helper;

import java.io.IOException;
 
import java.util.Calendar;
 

import org.apache.commons.lang3.time.FastDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomCalendarExSerializer extends JsonSerializer<Calendar> {

	@Override
	public void serialize(Calendar value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
  
		gen.writeString(FastDateFormat.getInstance("dd/MM/yyyy HH:mm").format(value));

	}
}
