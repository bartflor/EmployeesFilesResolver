package pl.resolver.resultImplementation.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.resolver.resultImplementation.employee.Employee;
import pl.resolver.resultImplementation.employee.EmployeesAnalize;

public class EmployeesAnalizeTest {
	
	@Test
	void shouldGiveProperCalculation() {
		//given
		Employee e1 = new Employee(1, "Ename1", "Esurname1", "job1", BigDecimal.valueOf(10.2));
		Employee e2 = new Employee(2, "Ename2", "Esurname2", "job1", BigDecimal.valueOf(20.2));
		Employee e3 = new Employee(3, "Ename3", "Esurname3", "job2", BigDecimal.valueOf(30.1));
		Employee e4 = new Employee(4, "Ename4", "Esurname4", "job2", BigDecimal.valueOf(40.3));
		List<Employee> employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		employees.add(e4);
		//when
		EmployeesAnalize employeesAnalize = new EmployeesAnalize(employees);
		//then
		assertEquals(15.2, employeesAnalize.getJobAverageSalary().get("job1"), "Should return correct calculation");
		assertEquals(35.2, employeesAnalize.getJobAverageSalary().get("job2"), "Should return correct calculation");
	}
	@Test
	void shouldNotThrowNullPointerExeption(){
		//given
		Employee e1 = new Employee(1, "Ename1", "Esurname1", "job", BigDecimal.valueOf(10.2));
		Employee e2 = new Employee(2, "Ename2", "Esurname2", "job", null);
		List<Employee> employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		//when
		EmployeesAnalize employeesAnalize = new EmployeesAnalize(employees);
		//then
		assertEquals(10.2, employeesAnalize.getJobAverageSalary().get("job"), "No error should be thrown. Should return correct calculation");

	}
}
