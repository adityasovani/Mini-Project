package com.cg.client;

import java.util.Scanner;

import com.cg.bean.Asset;
import com.cg.bean.AssetAllocation;
import com.cg.bean.Employee;
import com.cg.bean.User;
import com.cg.exception.AllocationException;
import com.cg.exception.AssetException;
import com.cg.exception.EmployeeException;
import com.cg.resources.TableList;
import com.cg.service.AssetAllocationService;
import com.cg.service.AssetAllocationServiceImpl;
import com.cg.service.AssetService;
import com.cg.service.AssetServiceImpl;
import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;
import com.cg.service.UserService;
import com.cg.service.UserServiceImpl;

public class Client {

	private static Scanner scanner;
	private static Asset asset;
	private static UserService userService;
	private static AssetService assetService;
	private static AssetAllocationService assetAllocationService;
	private static AssetAllocation assetAllocation;
	private static Employee employee;
	private static EmployeeService employeeService;

	public static void main(String[] args) throws Exception {
		byte choice = 8;
		boolean isLoggedIn = true;
		String userType = null;
		scanner = new Scanner(System.in);
		userService = new UserServiceImpl();
		assetService = new AssetServiceImpl();
		assetAllocationService = new AssetAllocationServiceImpl();
		employeeService = new EmployeeServiceImpl();

		do {
			System.out.println("1.Admin Login");
			System.out.println("2.Manager Login");
			System.out.println("3.Exit");
			System.out.println("Enter choice: ");

			choice = scanner.nextByte();

			switch (choice) {
			case 1: // Admin
			{
				System.out.println("Enter Username and password");
				String uName, uPass;
				uName = scanner.next();
				uPass = scanner.next();

				User loggedUser = userService.login(uName, uPass);
				userType = loggedUser.getUserType();

				if (!userType.equals("admin"))
					break;
				else
					System.out.println("Logged In as Admin");
				admin: do {
					System.out.println("1. Add Asset");
					System.out.println("2. Update Asset");
					System.out.println("3. View Assets");
					System.out.println("4. Export");

					System.out.println("5. View Asset Requests");
					System.out.println("6. View Pending");
					System.out.println("7. Change status(Approve/deny)");
					System.out.println("8. Log Out");
					System.out.println("Enter choice");
					int ch = scanner.nextInt();
					switch (ch) {
					case 1: // Add asset
						System.out.println("Enter asset id:");
						int assetId = scanner.nextInt();
						System.out.println("Enter assetName:");
						String assetName = scanner.next();
						System.out.println("Enter asset description: ");
						String assetDes = scanner.next();
						System.out.println("Enter asset status");
						String assetStatus = scanner.next();
						try {
							asset = assetService.addAsset(assetId, assetName, assetDes,
									assetStatus);
						} catch (IllegalArgumentException e) {
							System.out.println("Status can be allocated or unallocated.");
						}
						System.out.println("Asset added: ");
						break;

					case 2: // Update asset
						int key;
						System.out.println("Enter assetId to update: ");
						key = scanner.nextInt();
						try {
							asset = assetService.updateAsset(key, scanner.next(), scanner.next(), scanner.next());
							if (asset.getAssetId() == key)
								System.out.println("Asset Updated");
						} catch (IllegalArgumentException e) {
							System.out.println("Error while updating asset.");
						} catch (AssetException e) {
							System.out.println("Asset doesn't exist.");
						} catch (Exception e) {
							System.out.println("Asset cannot be updated.");
						}
						
						break;
					case 3: // ViewAssets

						assetService.viewAllAssets();
						break;

					case 4: // Export
						System.out.println("Enter file name to export: ");
						try {
							assetService.export(scanner.next());
						} catch (Exception e) {
							System.out.println("Error while exporting. Please try again.");
						}
						break;

					case 5: // View requests
						TableList table = new TableList(8, "AllocationId", "AssetId", "AssetName", "AssetStatus",
								"EmpNo", "EmpName", "RequestStatus", "Remark");

						for (int i = 0; i < assetAllocationService.findAll().size(); i++) {
							table.addRow(Integer.toString(assetAllocationService.findAll().get(i).getAllocationId()),
									Integer.toString(assetAllocationService.findAll().get(i).getAsset().getAssetId()),
									assetAllocationService.findAll().get(i).getAsset().getAssetName(),
									assetAllocationService.findAll().get(i).getAsset().getStatus(),
									Integer.toString(assetAllocationService.findAll().get(i).getEmployee().getEmpNo()),
									assetAllocationService.findAll().get(i).getEmployee().getEmpName(),
									assetAllocationService.findAll().get(i).getStatus(),
									assetAllocationService.findAll().get(i).getRemark());
						}
						table.print();
						break;
					case 6: // View Pending

						System.out.println("PENDING REQUESTS");

						TableList table1 = new TableList(8, "AllocationId", "AssetId", "AssetName", "AssetStatus",
								"EmpNo", "EmpName", "RequestStatus", "Remark");

						for (int i = 0; i < assetAllocationService.findPending().size(); i++) {

							table1.addRow(Integer.toString(assetAllocationService.findAll().get(i).getAllocationId()),
									Integer.toString(assetAllocationService.findAll().get(i).getAsset().getAssetId()),
									assetAllocationService.findAll().get(i).getAsset().getAssetName(),
									assetAllocationService.findAll().get(i).getAsset().getStatus(),
									Integer.toString(assetAllocationService.findAll().get(i).getEmployee().getEmpNo()),
									assetAllocationService.findAll().get(i).getEmployee().getEmpName(),
									assetAllocationService.findAll().get(i).getStatus(),
									assetAllocationService.findAll().get(i).getRemark());
						}

						break;

					case 7: // Change status
						System.out.println("Enter AllocationId, Status and Remark");
						try {
							assetAllocationService.changeStatus(scanner.nextInt(), scanner.next(), scanner.next());
						} catch (AllocationException e) {
							System.out.println("Requested asset doesn't exist.");
						} catch (IllegalArgumentException e) {
							System.out.println("Status can only be as approved, denied or pending.");
						}
						break;

					case 8: // Log out
						System.out.println("Log Out");
						break admin;

					default:
						break;
					}
				} while (isLoggedIn);

				break;
			}

			case 2: // Manager
			{
				System.out.println("Enter Username and password");
				String uName, uPass;
				uName = scanner.next();
				uPass = scanner.next();

				User loggedUser = userService.login(uName, uPass);
				userType = loggedUser.getUserType();

				if (!userType.equals("manager"))
					break;
				else
					System.out.println("Logged In as Manager");
				manager: do {
					System.out.println("1. Raise Request");
					System.out.println("2. View Status");
					System.out.println("3. View Asset Requests");
					System.out.println("4. View All Employees");
					System.out.println("5. Add Employee");
					System.out.println("6. Check Employee");
					System.out.println("7. Log Out");
					System.out.println("Enter choice");
					int ch = scanner.nextInt();
					switch (ch) {
					case 1: // Raise Request

						assetAllocation = new AssetAllocation();

						System.out.println("Enter allocation ID: ");
						assetAllocation.setAllocationId(scanner.nextInt());

						System.out.println("Enter AssetId: ");
						int asstId = scanner.nextInt();
						asset = assetService.getAssetById(asstId);
						assetAllocation.setAsset(asset);
						assetAllocation.setAssetId(asset.getAssetId());

						System.out.println("Enter Employee ID: ");
						int empId = scanner.nextInt();
						assetAllocation.setEmployee(employeeService.getEmployeeById(empId));

						assetAllocation.setStatus("pending");
						System.out.println("Add remark: ");
						assetAllocation.setRemark(scanner.next());
						try {
							assetAllocationService.request(assetAllocation);
						} catch (Exception e) {
							System.out.println("Error while requesting");
						}
						break;

					case 2: // View status

						TableList table3 = new TableList(7, "AllocationID", "AssetID", "AssetName", "EmpNo", "EmpName",
								"Status", "Remark");

						System.out.println("Enter Allocation id to locate: ");
						int keyFind = scanner.nextInt();

						try {
							table3.addRow(Integer.toString(assetAllocationService.findById(keyFind).getAllocationId()),
									Integer.toString(assetAllocationService.findById(keyFind).getAsset().getAssetId()),
									assetAllocationService.findById(keyFind).getAsset().getAssetName(),
									Integer.toString(assetAllocationService.findById(keyFind).getEmployee().getEmpNo()),
									assetAllocationService.findById(keyFind).getEmployee().getEmpName(),
									assetAllocationService.findById(keyFind).getStatus(),
									assetAllocationService.findById(keyFind).getRemark());
							table3.print();
						} catch (AllocationException e) {
							System.out.println("AllocationId does not exist.");
						}

						break;
					case 3: // View All Request
						TableList table = new TableList(8, "AllocationId", "AssetId", "AssetName", "AssetStatus",
								"EmpNo", "EmpName", "RequestStatus", "Remark");

						for (int i = 0; i < assetAllocationService.findAll().size(); i++) {
							table.addRow(Integer.toString(assetAllocationService.findAll().get(i).getAllocationId()),
									Integer.toString(assetAllocationService.findAll().get(i).getAsset().getAssetId()),
									assetAllocationService.findAll().get(i).getAsset().getAssetName(),
									assetAllocationService.findAll().get(i).getAsset().getStatus(),
									Integer.toString(assetAllocationService.findAll().get(i).getEmployee().getEmpNo()),
									assetAllocationService.findAll().get(i).getEmployee().getEmpName(),
									assetAllocationService.findAll().get(i).getStatus(),
									assetAllocationService.findAll().get(i).getRemark());
						}
						table.print();
						break;

					case 4: // View all employees

						TableList table2 = new TableList(3, "Id", "Name", "Department");
						for (int i = 0; i < employeeService.viewAllEmployees().size(); i++) {
							table2.addRow(Integer.toString(employeeService.viewAllEmployees().get(i).getEmpNo()),
									employeeService.viewAllEmployees().get(i).getEmpName(),
									employeeService.viewAllEmployees().get(i).getDepartment());
						}
						table2.print();
						break;

					case 5: // Add Employee
						System.out.println("Enter Employee Id, Name and Department: ");
						employee = employeeService.addEmp(scanner.nextInt(), scanner.next(), scanner.next());
						System.out.println("Employee added. Name: " + employee.getEmpName());
						break;

					case 6: // Check employee
						try {
							if (employeeService.exists(scanner.nextInt()))
								System.out.println("Employee Exists");
						} catch (EmployeeException e) {
							System.out.println("Employee doesn't exist.");
						}
						break;

					case 7: // Log Out
						break manager;
					default:
						System.err.println("Wrong choice. Please try again");
					}
				} while (isLoggedIn);

				break;
			}

			case 3:
				System.out.println("Exit");
				System.exit(0);
			default:
				System.err.println("Wrong choice. Please try again");
			}
		} while (true);

	}

}
