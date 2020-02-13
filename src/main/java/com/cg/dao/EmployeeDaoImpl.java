package com.cg.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.cg.bean.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	Employee Emp;
	Map<Integer, Employee> emp = new HashMap<Integer, Employee>();

	public EmployeeDaoImpl() {
		Employee e = new Employee();
		e.setEmpNo(101);
		e.setEmpName("Ram");
		e.setDepartment("IT");

		emp.put(e.getEmpNo(), e);

		Employee e1 = new Employee();
		e1.setEmpNo(102);
		e1.setEmpName("Prakash");
		e1.setDepartment("HR");
		emp.put(e1.getEmpNo(), e1);

		Employee e2 = new Employee();
		e2.setEmpNo(103);
		e2.setEmpName("Anil");
		e2.setDepartment("HR");
		emp.put(e2.getEmpNo(), e2);
	}

	public Employee addEmp(int empId, String empName, String department) {

		Emp = new Employee();

		Emp.setEmpNo(empId);
		Emp.setEmpName(empName);
		Emp.setDepartment(department);

		emp.put(empId, Emp);

		return Emp;
	}

	public void viewAllEmployees() {
		System.out.println("Employees List\n");
		System.out.println("EmpId\tEmpName\tDepartment");
		for (Integer key : emp.keySet()) {

			Emp = emp.get(key);
			// if (asset.getStatus().equals("allocated")) {
			System.out.println(Emp.getEmpNo() + "\t" + Emp.getEmpName() + "\t" + Emp.getDepartment());
			// }

		}
	}

	public boolean exists(int empId) {
		return emp.get(empId) != null ;
	}

}
