package pl.resolver.resultImplementation.employee;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pl.resolver.fileparser.csv.CSVFileParser;
import pl.resolver.fileparser.json.JSONFileParser;
import pl.resolver.inputparser.FileReader;
import pl.resolver.inputparser.SupportedTypes;

public class EmployeeFileParserFactoryTest {

	@Test
	void shouldReturnSiutableFileParser() {
		//given
		FileReader csvReader = new FileReader(null, null, SupportedTypes.CSV);
		FileReader jsonReader = new FileReader(null, null, SupportedTypes.JSON);
		
		//when
		EmployeeFileParserFactory jsonParserFactory = new EmployeeFileParserFactory(jsonReader);
		EmployeeFileParserFactory csvParserFactory = new EmployeeFileParserFactory(csvReader);
		EmployeeFileParserFactory nullParserFactory = new EmployeeFileParserFactory(null);
		
		//then
		assertTrue(jsonParserFactory.create().get() instanceof JSONFileParser<?>);
		assertTrue(csvParserFactory.create().get() instanceof CSVFileParser<?>);
		assertNull(nullParserFactory.create());
	}

}
