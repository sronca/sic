package it.istruzione.ptof.beans;

public class ResponseDTO<E> implements ResposeIntDTO{
	
	
	/**
	 * se null non ci sono errori 
	 */
	private ValidationErrorDTO validationError;
	
	private E result;

	/* (non-Javadoc)
	 * @see it.istruzione.ptof.beans.ResposeIntDTO#getValidationErrorDTO()
	 */
	@Override
	public ValidationErrorDTO getValidationError() {
		return validationError;
	}

	public void setValidationError(ValidationErrorDTO validationError) {
		this.validationError = validationError;
	}

	public E getResult() {
		return result;
	}

	public void setResult(E result) {
		this.result = result;
	}

}
