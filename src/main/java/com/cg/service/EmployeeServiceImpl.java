package com.cg.service;

import com.cg.bean.Employee;
import com.cg.dao.EmployeeDao;
import com.cg.dao.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService{

	EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	@Override
	public Employee addEmp(int empId, String empName, String department) {
		return employeeDao.addEmp(empId, empName, department);
	}

	@Override
	public boolean exists(int empid) {
		return employeeDao.exists(empid);
	}

	@Override
	public void viewAllEmployees() {
		employeeDao.viewAllEmployees();
	}

}
