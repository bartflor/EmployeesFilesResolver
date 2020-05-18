package pl.resolver.fileparser.json;

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
import pl.resolver.resultImplementation.employee.Employee;
import pl.resolver.resultImplementation.employee.EmployeesJsonAdapter;

public class JSONFileParserTest {
	EmployeesJsonAdapter adapter = new EmployeesJsonAdapter();
	@Test
    public void shouldGiveEmployeeObjestsParsedFromJson() throws IOException, ObjectDeserializationExeption {
		//given
		String filePath = "src/test/resources/e.json";
		Reader jsonReader = Files.newBufferedReader(Paths.get(filePath));
		JSONFileParser<Employee> jsonParser = new JSONFileParser<>(jsonReader, adapter);
		
		//when
		List<Employee>  actualResult = jsonParser.getObjectsList();

		//then
		List<Employee> expectedResult = new ArrayList<>() ;
		expectedResult.add(new Employee(1, "Mark", "Green", "Teacher", BigDecimal.valueOf(3540.2)));
		expectedResult.add(new Employee(2, "Oscar", "Mustache", "Janitor", BigDecimal.valueOf(13460.45)));
		
		assertIterableEquals(expectedResult, actualResult, "Should parse test file source and give proper result employees list");
		
	}

    @Test
    void shouldThrowExeptionGettingMallformedJson() throws IOException {
    	//given
    	String filePath = "src/test/resources/malformed.json";
		Reader jsonReader = Files.newBufferedReader(Paths.get(filePath));
		//when
		JSONFileParser<Employee> jsonParser = new JSONFileParser<>(jsonReader, adapter);
		//then
		Assertions.assertThrows(ObjectDeserializationExeption.class, () -> jsonParser.getObjectsList(),
				"Should throw exeption when invalid source file was given.");
		
    }

}
