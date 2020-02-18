package com.cg.employee;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cg.bean.Employee;
import com.cg.exception.EmployeeException;
import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;

public class EmployeeTest {

	private EmployeeService employeeService;
	private Employee employee;

	@Before
	//Initialize
	public void initialize() {
		employeeService = new EmployeeServiceImpl();
		employee = employeeService.addEmp(104, "Sameer", "Statistics");
	}
	
	@Test
	//Test to check existence of employee
	public void testEmpExists() throws EmployeeException {
		assertEquals(employeeService.exists(104), true);
	}

	@Test
	// Test to check addEmployee()
	public void addEmployeeTest() {
		assertEquals(employeeService.addEmp(79, "Ratan", "BI").getEmpName(), "Ratan");
	}

	@Test
	// Check getEmployeeById
	public void testGetEmployeeById() throws EmployeeException {
		assertEquals("Sameer", employeeService.getEmployeeById(104).getEmpName());
	}

	@Test
	public void viewAllEmployees() {

		List<Employee> employeeList = new ArrayList<Employee>();
		for (int i = 0; i < employeeService.viewAllEmployees().size(); i++) {
			employeeList.add(employeeService.viewAllEmployees().get(i));
		}
		assertEquals(employeeList.get(0) instanceof Employee, true);
	}
	
	@Test(expected = EmployeeException.class)
	// Check EmployeeException of getEmployeeById
	public void checkExceptionGetById() throws EmployeeException {
		employeeService.getEmployeeById(903);
	}

	@Test(expected = EmployeeException.class)
	//Check EmployeeException while checking employee that doesn't exist
	public void existsExceptionCheck() throws EmployeeException {
		employeeService.exists(556);
	}
}
