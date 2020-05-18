package pl.resolver.resultImplementation.employee;

import java.util.Optional;

import pl.resolver.fileparser.FileParser;
import pl.resolver.fileparser.csv.CSVFileParser;
import pl.resolver.fileparser.json.JSONFileParser;
import pl.resolver.inputparser.FileReader;

public class EmployeeFileParserFactory {
	FileReader fileReader;

	public EmployeeFileParserFactory(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public Optional<FileParser<Employee>> create(){
		if(fileReader==null) {
			return null;
		}
		switch (this.fileReader.getType()) {
		case JSON:
			return Optional.of(new JSONFileParser<Employee>(this.fileReader.getReader(), new EmployeesJsonAdapter()));
		case CSV:
			return Optional.of(new CSVFileParser<Employee>(this.fileReader.getReader(), new CsvRecordToEmployeeMapper<Employee>()));
		}
		return Optional.empty();

	}

}
