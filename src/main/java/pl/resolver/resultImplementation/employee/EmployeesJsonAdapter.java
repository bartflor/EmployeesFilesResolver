package pl.resolver.resultImplementation.employee;

import java.util.ArrayList;
import java.util.List;

import pl.resolver.fileparser.json.Adapter;

public class EmployeesJsonAdapter implements Adapter<Employee>{
	List<Employee> employees = new ArrayList<Employee>();

	public EmployeesJsonAdapter() {

	}

	public EmployeesJsonAdapter(List<Employee> el) {
		this.employees = el;
	}

	public List<Employee> getAdapteeList() {
		return employees;
	}

	public void setEmployees(List<Employee> el) {
		this.employees = el;
	}

}