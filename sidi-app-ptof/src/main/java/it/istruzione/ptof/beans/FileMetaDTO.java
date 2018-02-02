package it.istruzione.ptof.beans;

 

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
@JsonIgnoreProperties({"bytes"}) 
public class FileMetaDTO extends BaseDTO{

	private String fileName;
	private Long fileSize;
	private String fileType,textMessage;
	
	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	private byte[] bytes;
    private List<ValidationErrorFiedDTO> errors ;
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	 

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public List<ValidationErrorFiedDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationErrorFiedDTO> errors) {
		this.errors = errors;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	 

	 

       //setters & getters
}