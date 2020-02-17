package com.cg.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cg.bean.Employee;
import com.cg.exception.EmployeeException;

public class EmployeeDaoImpl implements EmployeeDao {

	Employee employee;
	Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

	public EmployeeDaoImpl() {
		Employee employee = new Employee();
		employee.setEmpNo(101);
		employee.setEmpName("Ram");
		employee.setDepartment("IT");

		employees.put(employee.getEmpNo(), employee);

		Employee employee1 = new Employee();
		employee1.setEmpNo(102);
		employee1.setEmpName("Prakash");
		employee1.setDepartment("HR");
		employees.put(employee1.getEmpNo(), employee1);

		Employee employee2 = new Employee();
		employee2.setEmpNo(103);
		employee2.setEmpName("Anil");
		employee2.setDepartment("HR");
		employees.put(employee2.getEmpNo(), employee2);
	}

	
	//ADD employee 
	public Employee addEmp(int empId, String empName, String department) {

		employee = new Employee();

		employee.setEmpNo(empId);
		employee.setEmpName(empName);
		employee.setDepartment(department);

		employees.put(empId, employee);

		return employee;
	}

	//Find ALL EMPLOYEES
	public List<Employee> viewAllEmployees() {
		List<Employee> employeesList = new ArrayList<Employee>();
		for (Integer key : employees.keySet()) {
			employeesList.add(employees.get(key)) ;
		}
		return employeesList;
	}

	//Check existence of employee
	public boolean exists(int empId) {
		return employees.get(empId) instanceof Employee;
	}

	//Return employee of given id
	public Employee getEmployeeById(int employeeId) throws EmployeeException {
		
		if(exists(employeeId))
			return employees.get(employeeId);
		else
			throw new EmployeeException();
	}

}
