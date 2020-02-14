package com.cg.service;

import com.cg.bean.Employee;
import com.cg.exception.EmployeeException;

public interface EmployeeService {
	
	public Employee addEmp(int empId, String empName, String department);
	public boolean exists(int empid);
	public void viewAllEmployees();
	public Employee getEmployeeById(int employeeId) throws EmployeeException;
	
}
