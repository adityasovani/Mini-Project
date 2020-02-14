package com.cg.service;

import com.cg.bean.Employee;
import com.cg.dao.EmployeeDao;
import com.cg.dao.EmployeeDaoImpl;
import com.cg.exception.EmployeeException;

public class EmployeeServiceImpl implements EmployeeService{

	EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	public Employee addEmp(int empId, String empName, String department) {
		return employeeDao.addEmp(empId, empName, department);
	}

	public boolean exists(int empid) {
		return employeeDao.exists(empid);
	}

	public void viewAllEmployees() {
		employeeDao.viewAllEmployees();
	}

	public Employee getEmployeeById(int employeeId) throws EmployeeException {
		return employeeDao.getEmployeeById(employeeId);
	}

}
