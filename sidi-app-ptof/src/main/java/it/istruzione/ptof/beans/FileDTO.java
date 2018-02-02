package it.istruzione.ptof.beans;

import java.io.InputStream;
import java.util.LinkedList;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

 
@JsonIgnoreProperties(value = { "file" })
public class FileDTO extends BaseDTO {
    
	 
	InputStream file;
    
	String fileName;

	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}
 
	public String getFileName() {
		return fileName;
	}
	
	 
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
