package com.cg.dao;

import com.cg.bean.Employee;

public interface EmployeeDao {

	public Employee addEmp(int empId, String empName, String department);
	public boolean exists(int empid);
	public void viewAllEmployees();

}
