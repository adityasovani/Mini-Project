package com.cg.employee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cg.bean.Employee;
import com.cg.dao.EmployeeDao;
import com.cg.dao.EmployeeDaoImpl;
import com.cg.exception.EmployeeException;

public class TestEmployee {

	EmployeeDao employeeDao = new EmployeeDaoImpl();

	Employee employee = employeeDao.addEmp(104, "Sameer", "Statistics");

	@Test
	//Test  to check empExists()
	public void testEmpExists() throws EmployeeException {
		assertEquals(employeeDao.exists(104), true);
		assertEquals(employeeDao.exists(105), false);
	}
	
	@Test
	//Test to check addEmployee()
	public void addEmployeeTest() {
		Employee employee = employeeDao.addEmp(79, "Ratan", "BI");
		assertEquals(employee.getEmpName(), "Ratan");
	}
}
