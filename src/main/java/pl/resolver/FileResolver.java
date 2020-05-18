package pl.resolver;

import java.util.List;
import java.util.Optional;

import pl.resolver.fileparser.FileParser;
import pl.resolver.fileparser.ObjectDeserializationExeption;
import pl.resolver.inputparser.ArgParser;
import pl.resolver.inputparser.FileReader;
import pl.resolver.inputparser.FileTypeNotSupportedExeption;
import pl.resolver.inputparser.InputFilesCollector;
import pl.resolver.resultImplementation.employee.Employee;
import pl.resolver.resultImplementation.employee.EmployeeFileParserFactory;
import pl.resolver.resultImplementation.employee.EmployeesAnalize;

public class FileResolver {
	private ArgParser argParser;
	private InputFilesCollector inputFilesCollector;

	public FileResolver(String... args) {
		this.argParser = new ArgParser(args);
		inputFilesCollector = new InputFilesCollector();
	}

	public void run() {
		String[] input = argParser.getInput();
		List<FileReader> fileReadersList = inputFilesCollector.getFiles(input);
		for (FileReader filereader : fileReadersList) {
		try {	
			EmployeeFileParserFactory parserFactory = new EmployeeFileParserFactory(filereader);
			Optional<FileParser<Employee>> parser = parserFactory.create();
			List<Employee> resultsList = parser.orElseThrow(() -> new FileTypeNotSupportedExeption(filereader.getFileName()))
											   .getObjectsList();
			EmployeesAnalize analizeList = new EmployeesAnalize(resultsList);
			ConsolePrinter.printOutput(filereader.getFileName(), analizeList.getJobAverageSalary());
			} catch (ObjectDeserializationExeption e) {
				System.err.println(e.getMessage());
			} catch (FileTypeNotSupportedExeption e) {
				System.err.println(e.getMessage());
			}

		}
		

	}

}
