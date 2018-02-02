package it.istruzione.ptof.helper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class RequestNotAutorizedException extends RuntimeException {
	private Errors errors;

	public RequestNotAutorizedException(){
	}
	
	public RequestNotAutorizedException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
}
