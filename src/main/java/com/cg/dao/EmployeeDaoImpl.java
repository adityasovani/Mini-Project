package com.cg.dao;

import java.util.HashMap;
import java.util.Map;
import com.cg.bean.Employee;

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

	public Employee addEmp(int empId, String empName, String department) {

		employee = new Employee();

		employee.setEmpNo(empId);
		employee.setEmpName(empName);
		employee.setDepartment(department);

		employees.put(empId, employee);

		return employee;
	}

	public void viewAllEmployees() {
		System.out.println("Employees List\n");
		System.out.println("EmpId\tEmpName\tDepartment");
		for (Integer key : employees.keySet()) {

			employee = employees.get(key);
			// if (asset.getStatus().equals("allocated")) {
			System.out.println(employee.getEmpNo() + "\t" + employee.getEmpName() + "\t" + employee.getDepartment());
			// }

		}
	}

	public boolean exists(int empId) {
		return employees.get(empId) != null ;
	}

}
