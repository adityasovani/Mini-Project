package com.cg.dao;

import java.util.List;

import com.cg.bean.Employee;
import com.cg.exception.EmployeeException;

public interface EmployeeDao {

	public Employee getEmployeeById(int employeeId) throws EmployeeException;
	public Employee addEmp(int empId, String empName, String department);
	public boolean exists(int empid) throws EmployeeException;
	public List<Employee> viewAllEmployees();

}
