package com.cg.employee;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cg.bean.Employee;
import com.cg.dao.EmployeeDao;
import com.cg.dao.EmployeeDaoImpl;
import com.cg.exception.EmployeeException;

public class EmployeeTest {

	EmployeeDao employeeDao = new EmployeeDaoImpl();
	Employee employee = employeeDao.addEmp(104, "Sameer", "Statistics");

	@Test
	//Test to check existence of employee
	public void testEmpExists() throws EmployeeException {
		assertEquals(employeeDao.exists(104), true);
	}

	@Test
	// Test to check addEmployee()
	public void addEmployeeTest() {
		Employee employee = employeeDao.addEmp(79, "Ratan", "BI");
		assertEquals(employee.getEmpName(), "Ratan");
	}

	@Test
	// Check getEmployeeById
	public void testGetEmployeeById() throws EmployeeException {
		Employee employee = employeeDao.getEmployeeById(104);
		assertEquals("Sameer", employee.getEmpName());
	}

	@Test(expected = EmployeeException.class)
	// Check employeeException of getEmployeeById
	public void checkExceptionGetById() throws EmployeeException {
		employeeDao.getEmployeeById(903);
	}

	@Test
	public void viewAllEmployees() {

		List<Employee> employeeList = new ArrayList<Employee>();
		for (int i = 0; i < employeeDao.viewAllEmployees().size(); i++) {
			employeeList.add(employeeDao.viewAllEmployees().get(i));
		}
		assertEquals(employeeList.get(0).getEmpName(), "Ram");
	}
}
