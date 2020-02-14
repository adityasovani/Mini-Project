package com.cg.client;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.cg.bean.Asset;
import com.cg.bean.AssetAllocation;
import com.cg.bean.Employee;
import com.cg.bean.User;
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
	private static UserService user;
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
		user = new UserServiceImpl();
		assetAllocationService = new AssetAllocationServiceImpl();
		assetService = new AssetServiceImpl();
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

				User loggedUser = user.login(uName, uPass);
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
						// assetService = new AssetServiceImpl();
						System.out.println("Enter asset id, asset name, asset des, and asset status:");
						asset = assetService.addAsset(scanner.nextInt(), scanner.next(), scanner.next(),
								scanner.next());
						System.out.println("Asset added: ");
						break;

					case 2: // Update asset
						int key;
						System.out.println("Enter assetId to update: ");
						key = scanner.nextInt();
						asset = assetService.updateAsset(key, scanner.next(), scanner.next(), scanner.next());
						if (asset.getAssetId() == key)
							System.out.println("Asset Updated");
						break;
					case 3: // ViewAssets
						assetService.viewAllAssets();
						break;

					case 4: // Export
						System.out.println("Enter file name: ");
						assetService.export(scanner.next());
						break;

					case 5: // View requests
						assetAllocationService = new AssetAllocationServiceImpl();
						assetAllocationService.findAll();

					case 6: // View Pending
						assetAllocationService.findPending();
					case 7: // Change status

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

				User loggedUser = user.login(uName, uPass);
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
						assetAllocationService.request(assetAllocation);
						break;
						
					case 2: // View status
						System.out.println("Enter Allocation id to locate: ");
						int keyFind = scanner.nextInt();
						System.out.println( assetAllocationService.findById(keyFind));
						/*System.out.println("---------------------------");
						System.out.println("AllocationID: " + asaloc.getAllocationId());
						System.out.println("AssetId: " + asaloc.getAsset().getAssetId());
						System.out.println("AssetName: " + asaloc.getAsset().getAssetName());
						System.out.println("AssetDes" + asaloc.getAsset().getAssetDes());
						System.out.println("Employee Number" + asaloc.getEmployee().getEmpNo());
						System.out.println("Employee Name" + asaloc.getEmployee().getEmpName());
						System.out.println("Request status: " + asaloc.getStatus());
						System.out.println("Remark: " + asaloc.getRemark());*/
						System.out.println("---------------------------");
						break;
					case 3: // View All Request
						List<AssetAllocation> allocationList = assetAllocationService.findAll();

						for (int i = 0; i < allocationList.size(); i++) {
							assetAllocation = allocationList.get(i);
							System.out.println("AllocationId: "+assetAllocation.getAllocationId());
							System.out.println("AssetId: "+assetAllocation.getAsset().getAssetId());
							System.out.println("AssetName: "+assetAllocation.getAsset().getAssetName());
							System.out.println("AssetDescription: "+assetAllocation.getAsset().getAssetDes());
							System.out.println("EmployeeNo: "+assetAllocation.getEmployee().getEmpNo());
							System.out.println("EmployeeName: "+assetAllocation.getEmployee().getEmpName());
							System.out.println("Allocation Status: "+assetAllocation.getStatus());
							System.out.println("Remark: "+assetAllocation.getRemark());
							System.out.println("---------------------------");
						}

						break;
					case 4: // View all employees
						employeeService.viewAllEmployees();
						break;
					case 5: // Add Employee
						System.out.println("Enter Employee Id, Name and Department: ");
						employee = employeeService.addEmp(scanner.nextInt(), scanner.next(), scanner.next());
						System.out.println("Employee added. Name: " + employee.getEmpName());
						break;
					case 6: // Check employee
						if (employeeService.exists(scanner.nextInt()))
							System.out.println("Employee Exists");
						break;
					case 7: // Log Out
						break manager;
					default:
						break;
					}
				} while (isLoggedIn);

				break;
			}

			case 3:
				System.out.println("Exit");
				System.exit(0);
			default:
				break;
			}
		} while (true);

	}

}
