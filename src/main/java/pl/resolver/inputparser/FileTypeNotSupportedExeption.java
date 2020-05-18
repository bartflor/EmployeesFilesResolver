package pl.resolver.inputparser;

import java.nio.file.Path;

public class FileTypeNotSupportedExeption extends Exception {
	private static final long serialVersionUID = 741730536746006973L;
	private String fileName;

	public FileTypeNotSupportedExeption(Path file) {
		this.fileName = file.getFileName().toString();
	}
	
	public FileTypeNotSupportedExeption(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getMessage() {
		
		return "Not supported file type: " +this.fileName+". \n Runs with JSON or CSV.\n";
	}

	
	

}
