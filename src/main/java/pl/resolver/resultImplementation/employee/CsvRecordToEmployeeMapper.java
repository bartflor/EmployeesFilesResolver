package pl.resolver.resultImplementation.employee;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import pl.resolver.fileparser.ObjectDeserializationExeption;
import pl.resolver.fileparser.csv.ResultsMapper;

public class CsvRecordToEmployeeMapper<E extends Employee> implements ResultsMapper<Employee> {

	private char delimeter;

	public CsvRecordToEmployeeMapper() {
		this.delimeter =';';
	}

	public Employee map(Map<String, String> nameValueMap) throws ObjectDeserializationExeption {
		if(nameValueMap == Collections.EMPTY_MAP || nameValueMap == null) {
			throw new ObjectDeserializationExeption("Empty CSVRecord");
		}
		Employee employee = new Employee();
		for (Map.Entry<String, String> entry : nameValueMap.entrySet())
		switch (entry.getKey().toLowerCase()) {
		case "id":
			if (isNumeric(entry.getValue())) {
				employee.setId(getInteger(entry.getValue()));
			}
			break;
		case "name":
			employee.setName(entry.getValue());
			break;
		case "surname":
			employee.setSurname(entry.getValue());
			break;
		case "position":
			employee.setJob(entry.getValue());
			break;
		case "salary":
			if (isNumeric(entry.getValue())) {
				employee.setSalary(getBigDecimal(entry.getValue()));
			}
			break;
		default:
			throw new ObjectDeserializationExeption("Wrong CSVRecord format - no matching header");

		}
		return employee;
	}
	
	public char getDelimeter() {
		return this.delimeter;
	}

	private int getInteger(String value) {
		return Integer.valueOf(value);
	}

	private BigDecimal getBigDecimal(String f) {
		f = f.replaceAll(",", ".");
		return new BigDecimal(f);
	}

	private boolean isNumeric(String f) {
		return f.matches("-?\\d+(.\\d+)?");
	}
}
