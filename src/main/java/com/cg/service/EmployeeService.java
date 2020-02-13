package com.cg.service;

import com.cg.bean.Employee;

public interface EmployeeService {
	
	public Employee addEmp(int empId, String empName, String department);
	public boolean exists(int empid);
	public void viewAllEmployees();

}
