package it.istruzione.poninchiaro.common.util;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonConverterIE9Fix extends
		MappingJackson2HttpMessageConverter {

	private String jsonPrefix;

	public JacksonConverterIE9Fix() {
		super();
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders()
				.getContentType());

		// fix per internet explorer < 10
		fixIE9(outputMessage);

		// The following has been deprecated as late as Jackson 2.2 (April
		// 2013);
		// preserved for the time being, for Jackson 2.0/2.1 compatibility.
		@SuppressWarnings("deprecation")
		JsonGenerator jsonGenerator = this.getObjectMapper().getJsonFactory()
				.createJsonGenerator(outputMessage.getBody(), encoding);

		// A workaround for JsonGenerators not applying serialization features
		// https://github.com/FasterXML/jackson-databind/issues/12
		if (this.getObjectMapper()
				.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
			jsonGenerator.useDefaultPrettyPrinter();
		}

		try {
			if (this.jsonPrefix != null) {
				jsonGenerator.writeRaw(this.jsonPrefix);
			}
			this.getObjectMapper().writeValue(jsonGenerator, object);
		} catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: "
					+ ex.getMessage(), ex);
		}
	}

	private void fixIE9(HttpOutputMessage outputMessage) {
		
		boolean toFix = true;
		
		for (MediaType mediaType : outputMessage.getHeaders().getAccept()) {
			if (mediaType.isConcrete()
					&& mediaType.isCompatibleWith(MediaType.APPLICATION_JSON)) {
				toFix = false;
				break;
			}
		}
		
		if (toFix) {
			outputMessage.getHeaders().setContentType(MediaType.TEXT_HTML);
		}
	}

}
