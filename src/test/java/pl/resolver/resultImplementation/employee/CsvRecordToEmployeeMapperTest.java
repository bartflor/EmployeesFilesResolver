package pl.resolver.resultImplementation.employee;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import pl.resolver.fileparser.ObjectDeserializationExeption;

public class CsvRecordToEmployeeMapperTest {

	CsvRecordToEmployeeMapper<Employee> mapper = new CsvRecordToEmployeeMapper<Employee>();

	@Test
	void shouldDeserialzeFromPreparedMap() throws ObjectDeserializationExeption {
		//given
		Employee expected = new Employee(1, "E name", "E,surname", "E.job", BigDecimal.valueOf(123.45));
		Map<String, String> recordMock = new HashMap<>();
		recordMock.put("id", "1");
		recordMock.put("name", expected.getName());
		recordMock.put("surname", expected.getSurname());
		recordMock.put("position", expected.getPosition());
		recordMock.put("salary", expected.getSalary().toString());
		//when
		Employee actual = mapper.map(recordMock);
		//then
		assertEquals(expected, actual, "Should return poperly mapped employee object.");
	}
	
	@Test
    void shouldThrowExeptionGettingWrongMapFormat() {
		//given
		Map<String, String> badFormatMap = new HashMap<>();
		badFormatMap.put("id", "123");
		badFormatMap.put("name", "Name");
		badFormatMap.put("City", "City");
		badFormatMap.put("surname", "Surname");
		//then
		Assertions.assertThrows(ObjectDeserializationExeption.class, () -> mapper.map(badFormatMap),
		"Should throw exeption, when invalid map format was given");
		
    }
	
	@ParameterizedTest
	@NullAndEmptySource
    void shouldThrowExeptionGettingEmpty(Map<String, String> badFormatMap) {
		//given null and empty map
		//then
		Assertions.assertThrows(ObjectDeserializationExeption.class, () -> mapper.map(badFormatMap),
				"Should throw exeption, when null or empty map was given");
		
    }
	
}

