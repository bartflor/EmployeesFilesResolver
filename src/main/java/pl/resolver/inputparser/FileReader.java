package pl.resolver.inputparser;

import java.io.Reader;

public class FileReader {
	String fileName;
	Reader reader;
	SupportedTypes type;
	
	public FileReader(String fileName, Reader reader, SupportedTypes type) {
		this.fileName = fileName;
		this.reader = reader;
		this.type = type;
	}



	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public SupportedTypes getType() {
		return type;
	}

	public void setType(SupportedTypes type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return "FileReader [fileName=" + fileName + ", reader=" + reader + ", type=" + type + "]";
	}
	
	
}
