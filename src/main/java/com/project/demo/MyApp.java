package com.project.demo;

import java.util.Scanner;

import org.hibernate.Session;

import com.project.demo.customException.InvalidDetailsInputException;
import com.project.demo.dao.AdminDao;
import com.project.demo.dao.UserDao;
import com.project.demo.dao.impl.AdminDaoImpl;
import com.project.demo.dao.impl.UserDaoImpl;
import com.project.demo.dashboard.AdminDashboard;
import com.project.demo.dashboard.UserDashboard;
import com.project.demo.entity.Admin;
import com.project.demo.entity.User;
import com.project.demo.utility.HibernateUtil;

public class MyApp {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		Boolean returnToMenu = false;
		
		do {
			System.out.println("1. Enter 1 for User portal\n2. Enter 2 for Admin portal\n3. Exit");
			System.out.print("Select your role: ");
			
			int roleChoice = scanner.nextInt();
			
			switch (roleChoice) {
			case 1:
				// User part
				System.out.println("1. Enter 1 for new User reistration\n2. Enter 2 for existing User login\n3. Enter 3 for Exit");
				System.out.print("Choose option: ");
				int u_choice = scanner.nextInt();
				
				switch (u_choice) {
					case 1:
						registerUser();
						break;
					case 2:
						try {
							loginUser();
						} catch (InvalidDetailsInputException e) {
							System.out.println(e.getMessage());
						}
						break;
					default:
	                    System.out.println("Please choose again.");
	                    break;
				}
				break;
			case 2:
				// Admin part
				System.out.println("1. Enter 1 for new Admin reistration\n2. Enter 2 for existing Admin login\n3. Enter 3 for Exit");
				System.out.print("Choose option: ");
				int a_choice = scanner.nextInt();
				
				switch (a_choice) {
					case 1:
						registerAdmin();
						break;
					case 2:
						try {
							loginAdmin();
						} catch (InvalidDetailsInputException e) {
							System.out.println(e.getMessage());
						}
						break;
					default:
	                    System.out.println("Please choose again.");
	                    break;
				}
				break;
				
			case 3:
                System.out.println("Exiting program.");
                returnToMenu = false;
                break;

            default:
                System.out.println("Invalid choice. Please choose again.");
                break;
			}

            if (roleChoice == 1 || roleChoice == 2) {
                // After registration or login, set returnToMenu to true to loop back to the menu
                returnToMenu = true;
            }
			
		} while (returnToMenu);
		scanner.close();
	}

	
	private static boolean isValidEmail(String mail) {
		return mail.endsWith("@gmail.com");
	}
	
	public static void registerUser() {

		User user = new User();
		
		System.out.println("User Registration:");
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter full-name: ");
		String uname = scanner.nextLine();
		user.setUname(uname);
		while(true) {
			System.out.print("Enter mail id: ");
			String umail = scanner.nextLine();
			
			if(isValidEmail(umail)) {
				user.setUmail(umail);
				break;
			} else {
				System.out.println("Invalid email format. please provide a valid email with '@gmail.com' extension");
			}
		}
	 	        
	 	System.out.print("Enter password: ");
	 	String upass = scanner.nextLine();
	 	user.setUpass(upass);
	 	
	 	UserDao userDao = new UserDaoImpl();
        userDao.saveUser(user);
		
	}


	public static void loginUser() throws InvalidDetailsInputException {
		
		System.out.println("User Login:");
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter mail id: ");
		String umail = scanner.nextLine();      
	 	System.out.print("Enter password: ");
	 	String upass = scanner.nextLine();
	    
	 	 if (umail.isEmpty() || upass.isEmpty()) {
	 		 throw new InvalidDetailsInputException("Invalid user input. All fields are required.");
	 	 }
	 	
	    UserDao userDao = new UserDaoImpl();
        User user = userDao.loginUser(umail, upass);
        
        if (user != null) {
	        System.out.println("Login successful!");
	        UserDashboard.showUserDashboard(user);
	    } else {
	        System.out.println("Invalid email or password. Do you want to register? (yes/no)");
	        
	        Scanner sc = new Scanner(System.in);
	        String registerChoice = sc.nextLine().toLowerCase();
	        if (registerChoice.equals("yes")) {
	            registerUser();
	        } else {
	            System.out.println("Exiting...");
	        }
	    }
	}

	public static void registerAdmin() {
		
		Admin admin = new Admin();
		 
		Scanner scanner = new Scanner(System.in);
		System.out.println("Admin Registration:");
		
		System.out.print("Enter full-name: ");
		String aname = scanner.nextLine();
		admin.setAname(aname);
		while(true) {
			System.out.print("Enter mail id: ");
			String amail = scanner.nextLine();
			
			if(isValidEmail(amail)) {
				admin.setAmail(amail);
				break;
			} else {
				System.out.println("Invalid email format. please provide a valid email with '@gmail.com' extension");
			}
		}        
	 	System.out.print("Enter password: ");
	 	String apass = scanner.nextLine();
	 	admin.setApass(apass);
	 	
	 	AdminDao adminDao = new AdminDaoImpl();
	 	adminDao.saveAdmin(admin);
		
	}

	public static void loginAdmin() throws InvalidDetailsInputException {
		
		//System.out.println("login admin");
		
		System.out.println("Admin Login:");
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter mail id: ");
		String amail = scanner.nextLine();    
	 	System.out.print("Enter password: ");
	 	String apass = scanner.nextLine();
	 	
	 	if (amail.isEmpty() || apass.isEmpty()) {
	 		 throw new InvalidDetailsInputException("Invalid admin input. All fields are required.");
	 	 }
	 	
	 	AdminDao adminDao = new AdminDaoImpl();
	 	Admin admin = adminDao.loginAdmin(amail, apass);

        if (admin != null) {
            System.out.println("Login successful!");
            new AdminDashboard(admin).showAdminDashboard();
        } else {
            System.out.println("Invalid email or password. Do you want to register? (yes/no)");

            Scanner sc = new Scanner(System.in);
            String registerChoice = sc.nextLine().toLowerCase();
            if (registerChoice.equals("yes")) {
                registerAdmin();
            } else {
                System.out.println("Exiting...");
            }
        }   
	}

}
