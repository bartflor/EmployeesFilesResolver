package pl.resolver.fileparser.csv;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.resolver.fileparser.ObjectDeserializationExeption;
import pl.resolver.resultImplementation.employee.CsvRecordToEmployeeMapper;
import pl.resolver.resultImplementation.employee.Employee;

public class CsvParserTest {
	ResultsMapper<Employee> resultMaper = new CsvRecordToEmployeeMapper<Employee>();
	
	@Test
    void shouldGiveEmployeeObjParsedFromCsv() throws IOException, ObjectDeserializationExeption {
		//given
		Reader csvReader = Files.newBufferedReader(Paths.get("src/test/resources/e.csv"));
		CSVFileParser<Employee> csvParser = new CSVFileParser<>(csvReader, resultMaper);
		
		//when
		List<Employee> actualResult = csvParser.getObjectsList();
		List<Employee> expectedResult = new ArrayList<>();
		expectedResult.add(new Employee(1, "Mark", "Green", "Teacher", BigDecimal.valueOf(3540.2)));
		expectedResult.add(new Employee(2, "Oscar", "Mustache", "Janitor", BigDecimal.valueOf(13460.45)));

		//then
		assertIterableEquals(expectedResult, actualResult, "Should parse test file source and give proper result employees list");
		
	}

    @Test
    void shouldThrowExeptionGettingMallformedCsv() throws IOException {
    	//given
    	Reader csvReader = Files.newBufferedReader(Paths.get("src/test/resources/malformed.csv"));
    	
    	//when
		CSVFileParser<Employee> csvParser = new CSVFileParser<>(csvReader, resultMaper);
		
		//then
		Assertions.assertThrows(ObjectDeserializationExeption.class, () -> csvParser.getObjectsList(),
				"Shoult throw exeption when given malformed file source");
		
    }
}
