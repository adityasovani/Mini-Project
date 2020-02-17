package com.cg.client;

import java.util.HashMap;
import java.util.Map;

import com.cg.resources.TableList;

class Student {
	private int id;
	private String name;
	private String des;
	private int marks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
}

public class TableListImpl {

	static Map<Integer, Student> students = new HashMap<Integer, Student>();

	public static void main(String[] args) {

		Student student = new Student();
		student.setId(8);
		student.setName("Vaibhav");
		student.setMarks(55);
		student.setDes("Good boy");

		students.put(student.getId(), student);

		Student student2 = new Student();
		student2.setId(52);
		student2.setName("Ashutosh");
		student2.setMarks(88);
		student2.setDes("Mulga hushar ahe pn sangat kharabb ahe.");
		
		students.put(student2.getId(), student2);
		
		TableList table = new TableList(4, "Id", "Name", "Description", "Marks");

		for (int key : students.keySet()) {
			table.addRow(Integer.toString(key), students.get(key).getName(), students.get(key).getDes(),
					Integer.toString(students.get(key).getMarks()));
		}
		table.print();
	}
}
