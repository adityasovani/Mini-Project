package com.cg.client;

import java.util.List;
import java.util.Scanner;

import com.cg.bean.Asset;
import com.cg.bean.AssetAllocation;
import com.cg.bean.Employee;
import com.cg.bean.User;
import com.cg.service.AssetAllocationService;
import com.cg.service.AssetAllocationServiceImpl;
import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;
import com.cg.service.UserService;
import com.cg.service.UserServiceImpl;

public class Client {

	private static Scanner scanner;
	// private static User user;
	private static Asset asset;
	private static UserService user;
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
					System.out.println("5. Log Out");
					System.out.println("Enter choice");
					int ch = scanner.nextInt();
					switch (ch) {
					case 1: // Add asset

						/*
						 * System.out.
						 * println("Enter asset id, asset name, asset des, asset qty and asset status:"
						 * ); asset = ads.addAsset(scanner.nextInt(), scanner.next(), scanner.next(),
						 * scanner.nextInt(), scanner.next()); System.out.println("Asset added: ");
						 */
						break;

					case 2: // Update asset
						/*
						 * AdminService ads1 = new AdminServiceImpl(); int key;
						 * System.out.println("Enter assetId to update: "); key = scanner.nextInt();
						 * asset = ads1.updateAsset(key, scanner.next(), scanner.next(),
						 * scanner.nextInt(), scanner.next()); if (asset.getAssetId() == key)
						 * System.out.println("Asset Updated"); break;
						 */
					case 3: // Requests
						// ads.viewAllocatedAssets();
						break;

					case 4: // Export
						System.out.println("Enter file name: ");
						// ads.export(scanner.next());
						break;

					case 5: // LogOut
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
					case 1: // After integration
						assetAllocationService = new AssetAllocationServiceImpl();
						asset = new Asset();
						assetAllocation = new AssetAllocation();

						System.out.println("Enter ID: ");
						assetAllocation.setAllocationId(scanner.nextInt());

						System.out.println("Enter AssetId: ");
						asset.setAssetId(scanner.nextInt());

						assetAllocation.setAssetId(asset.getAssetId());

						asset = assetAllocation.getAsset();

						/*
						 * private int assetId; private Employee employee; private Asset asset; private
						 * String status; private String remark;
						 */

						assetAllocationService.request(assetAllocation);
						break;
					case 2: // View status
						System.out.println("Enter Allocation id to locate: ");
						assetAllocation = assetAllocationService.findById(scanner.nextInt());
						System.out.println("---------------------------");
						System.out.println("AllocationID: " + assetAllocation.getAllocationId());
						System.out.println("AssetId: " + assetAllocation.getAsset().getAssetId());
						System.out.println("AssetName: " + assetAllocation.getAsset().getAssetName());
						System.out.println("AssetDes" + assetAllocation.getAsset().getAssetDes());
						System.out.println("Employee Number" + assetAllocation.getEmployee().getEmpNo());
						System.out.println("Employee Name" + assetAllocation.getEmployee().getEmpName());
						System.out.println("Request status: " + assetAllocation.getStatus());
						System.out.println("Remark: " + assetAllocation.getRemark());
						System.out.println("---------------------------");
						break;
					case 3: // View All Request
						List<AssetAllocation> allocationList = assetAllocationService.findAll();

						for (int i = 0; i < allocationList.size(); i++) {
							assetAllocation = allocationList.get(i);
							System.out.println(assetAllocation.getAllocationId());
							System.out.println(assetAllocation.getAsset().getAssetId());
							System.out.println(assetAllocation.getAsset().getAssetName());
							System.out.println(assetAllocation.getAsset().getAssetDes());
							System.out.println(assetAllocation.getEmployee().getEmpNo());
							System.out.println(assetAllocation.getEmployee().getEmpName());
							System.out.println(assetAllocation.getStatus());
							System.out.println(assetAllocation.getRemark());
							System.out.println("---------------------------");
						}

						break;
					case 4:	//View all employees
						employeeService.viewAllEmployees();
						break;
					case 5:	//Add Employee
						System.out.println("Enter Employee Id,Name and Department: ");
						employee = employeeService.addEmp(scanner.nextInt(), scanner.next(), scanner.next());
						System.out.println("Employee added. Name: "+employee.getEmpName());
						break;
					case 6:	//Check employee
						if(employeeService.exists(scanner.nextInt()))
							System.out.println("Employee Exists");
						break;
					case 7:	//Log Out
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

		// Switch case to separate admin's and manager's console
		/*
		 * switch (userType) { case "admin": { System.out.println("In Admin"); int ch =
		 * 0; do { System.out.println("1. Add Asset");
		 * System.out.println("2. Update Asset"); System.out.println("3. View Assets");
		 * System.out.println("4. Export"); ch = s.nextInt(); switch (ch) { case 1:
		 * //Add asset
		 * 
		 * System.out.
		 * println("Enter asset id, asset name, asset des, asset qty and asset status:"
		 * ); a = ads.addAsset(s.nextInt(), s.next(), s.next(), s.nextInt(), s.next());
		 * System.out.println("Asset added: "); break;
		 * 
		 * case 2: //Update asset AdminService ads1 = new AdminServiceImpl(); int key;
		 * System.out.println("Enter assetId to update: "); key = s.nextInt(); a =
		 * ads1.updateAsset(key, s.next(), s.next(), s.nextInt(), s.next()); if
		 * (a.getAssetId() == key) System.out.println("Asset Updated"); break; case 3:
		 * //Requests ads.viewAllocatedAssets(); break;
		 * 
		 * case 4: //Export System.out.println("Enter file name: ");
		 * ads.export(s.next()); break; default: break; } } while (ch != 'P'); } break;
		 * case "manager": { System.out.println("In manager"); break; } default: break;
		 * }
		 */
	}

}
