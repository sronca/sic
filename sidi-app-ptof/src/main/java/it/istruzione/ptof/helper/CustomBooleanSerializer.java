package it.istruzione.ptof.helper;

import java.io.IOException;
 

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomBooleanSerializer extends JsonSerializer<Boolean> {

	@Override
	public void serialize(Boolean value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		try {
			if( value !=null )
			  gen.writeString(value.toString() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}
}
