package pl.resolver.resultImplementation.employee;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

import java.util.List;
import java.util.Map;

public class EmployeesAnalize {
	private List<Employee> employees;

	public EmployeesAnalize(List<Employee> employees) {
		this.employees = employees;
	}

	public Map<String, Double> getJobAverageSalary() {
		return employees.stream()
				.filter(e -> e.getPosition() != null && e.getSalary() != null)
				.collect(groupingBy(Employee::getPosition, 
									mapping(Employee::getSalary,
											averagingDouble(e -> e.doubleValue()))));

	}

}
