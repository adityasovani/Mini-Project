package com.cg.client;

import java.util.Scanner;

import com.cg.bean.Asset;
import com.cg.bean.User;
import com.cg.service.AdminService;
import com.cg.service.AdminServiceImpl;
import com.cg.service.UserService;
import com.cg.service.UserServiceImpl;

public class Client {

	private static Scanner s;
	//private static User user;
	private static Asset a;
	private static UserService user;
	private static AdminService ads;

	public static void main(String[] args) throws Exception {
		byte choice = 8;
		boolean isLoggedIn = true;
		String userType = null;
		s = new Scanner(System.in);
		user = new UserServiceImpl();
		ads = new AdminServiceImpl();

		do {
			System.out.println("1.Admin Login");
			System.out.println("2.Manager Login");
			System.out.println("3.Exit");
			System.out.println("Enter choice: ");

			choice = s.nextByte();

			switch (choice) {
			case 1: // Admin
			{
				System.out.println("Enter Username and password");
				String uName, uPass;
				uName = s.next();
				uPass = s.next();

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
					int ch = s.nextInt();
					switch (ch) {
					case 1: // Add asset

						System.out.println("Enter asset id, asset name, asset des, asset qty and asset status:");
						a = ads.addAsset(s.nextInt(), s.next(), s.next(), s.nextInt(), s.next());
						System.out.println("Asset added: ");
						break;

					case 2: // Update asset
						AdminService ads1 = new AdminServiceImpl();
						int key;
						System.out.println("Enter assetId to update: ");
						key = s.nextInt();
						a = ads1.updateAsset(key, s.next(), s.next(), s.nextInt(), s.next());
						if (a.getAssetId() == key)
							System.out.println("Asset Updated");
						break;
					case 3: // Requests
						ads.viewAllocatedAssets();
						break;

					case 4: // Export
						System.out.println("Enter file name: ");
						ads.export(s.next());
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
				uName = s.next();
				uPass = s.next();

				User loggedUser = user.login(uName, uPass);
				userType = loggedUser.getUserType();
				
				if (!userType.equals("manager"))
					break ;
				else
					System.out.println("Logged In as Manager");
				manager: do {
					System.out.println("1. Raise Request");
					System.out.println("2. View Status");
					System.out.println("3. View Assets");
					System.out.println("4. Log Out");
					System.out.println("Enter choice");
					int ch = s.nextInt();
					switch (ch) {
					case 1: 
						break;
					case 2: 
						break;
					case 3: 
						break;
					case 4: 
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
