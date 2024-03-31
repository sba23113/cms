/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dataprocessing;

import dao.UserDao;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import reports.ReportGenerator;
import reports.ReportOutput;
import reports.ReportOutputFormatSelector;
import tables.User;
import tables.UserRole;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class MenuSystem {
    private ReportGenerator reportGenerator;
    private Scanner scanner;
    private UserDao userDao;
    private User currentUser;

    public MenuSystem(ReportGenerator reportGenerator, Scanner scanner, UserDao userDao) {
        this.reportGenerator = reportGenerator;
        this.scanner = scanner;
        this.userDao = userDao;
        this.currentUser = null;
    }    
    
    /**
     * Display Main Menu
     */
    public void displayMainMenu() {
        boolean running = true; // Flag to control the loop
        while (running) {
            // CMS main menu
            System.out.println("");
            System.out.println("****************************************************************************");
            System.out.println("Course Management System");
            System.out.println("****************************************************************************");
            System.out.println("1) Login");
            System.out.println("0) Exit");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();                     
            
            switch (choice) {
                case 1: // if '1' entered -> log in
                    login();
                    break;
                case 0: // if '0' entered -> exit
                    System.out.println("***Good bye!***");
                    running = false;
                    break;
                default:
                    System.out.println("!!!Invalid choice!!!");
            }
        }
    }

    /**
     * get input from console
     * @return 
     */
    private int getIntInput() {
        int input = 0;
        boolean running = true; // Flag to control the loop
        while (running) {
            try {
                input = scanner.nextInt(); // store user's choice in interger variable                
                scanner.nextLine(); // consume any newline characters left in scanner buffer
                return input;
            } catch (InputMismatchException e) {
                System.out.println("!!!Invalid input. Please enter a number!!!"); // error message if input invalid
                scanner.nextLine(); // consume the invalid input
            }
        }
        
        return input;
    }    
    
    /**
     * Method implementing login process
     */
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();                
        
        User user = userDao.getUser(username); // Get user by username
        
        if (user != null && user.getPasswordHash().equals(password)) { // Login details match details stored in database
            currentUser = user;
            System.out.println("You are logged in!");
            showRoleBasedMenu(user.getUserRole()); // Show relevant menu based on user's role
        } else {
            System.out.println("Invalid login details! Please try again!");
        }
    }

    private void changeUsername() {
        if (currentUser == null) {
            return;
        }
        
        System.out.print("Enter your new username: ");
        String newUsername = scanner.nextLine();
        currentUser.setUsername(newUsername);
        if (userDao.updateUser(currentUser)) {
            System.out.println("Username updated");
        } else {
            System.out.println("Username could not be updated. Please try again.");
        }
    }
    
    private void changePassword() {
        if (currentUser == null) {
            return;
        }
        System.out.print("Enter your new password: ");
        String newPassword = scanner.nextLine();
        currentUser.setPasswordHash(newPassword);
        if (userDao.updateUser(currentUser)) {
            System.out.println("Password updated.");
        } else {
            System.out.println("Password could not be updated. Please try again.");
        }
    }
    
    private void showRoleBasedMenu(UserRole role) {
        switch (role) {
            case ADMIN:
                showAdminMenu();
                break;
            case OFFICE:
                showOfficeMenu();
                break;
            case LECTURER:
                showLecturerMenu();
                break;
            default:
                System.out.println("Wrong role - try again!");
        }
    }
    
    private void showAdminMenu() {
        boolean adminLoggedIn = true; // Flag to control the loop
        while (adminLoggedIn) {
            System.out.println("");
            System.out.println("****************************************************************************");
            System.out.println("Course Management System - Administrator Menu");
            System.out.println("****************************************************************************");
            System.out.println("1) Add User");
            System.out.println("2) Modify User");
            System.out.println("3) Delete User");
            System.out.println("4) Change Username");
            System.out.println("5) Change Password");
            System.out.println("0) Logout");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();                     
            
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    modifyUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    changeUsername();
                    break;
                case 5:
                    changePassword();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    adminLoggedIn = false;
                    break;
                default:
                    System.out.println("!!!Invalid choice!!!");
            }
        }
    }
    
    private void showOfficeMenu() {
        ReportOutputFormatSelector formatSelector = new ReportOutputFormatSelector(scanner);

        boolean userLoggedIn = true; // Flag to control the loop
        while (userLoggedIn) {
            System.out.println("");
            System.out.println("****************************************************************************");
            System.out.println("Course Management System - Office User Menu");
            System.out.println("****************************************************************************");
            System.out.println("1) Generate Course Report");
            System.out.println("2) Generate Student Report");
            System.out.println("3) Generate Lecturer Report");
            System.out.println("4) Change Username");
            System.out.println("5) Change Password");
            System.out.println("0) Logout");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();                     
            
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Course ID for the report: ");
                        int courseId = getIntInput();
                        ReportOutput reportOutput = formatSelector.selectFormat();
                        reportGenerator.generateCourseReport(courseId, reportOutput);
                        break;
                    case 2:
                        System.out.print("Enter Student ID for the report: ");
                        int studentId = getIntInput();
                        reportOutput = formatSelector.selectFormat();
                        reportGenerator.generateStudentReport(studentId, reportOutput);
                        break;
                    case 3:
                        System.out.print("Enter Lecturer ID for the report: ");
                        int lecturerId = getIntInput();
                        reportOutput = formatSelector.selectFormat();
                        reportGenerator.generateLecturerReport(lecturerId, reportOutput);
                        break;
                    case 4:
                        changeUsername();
                        break;
                    case 5:
                        changePassword();
                        break;
                    case 0:
                        userLoggedIn = false;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("!!!Invalid choice!!!");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
    public void showLecturerMenu() {
        ReportOutputFormatSelector formatSelector = new ReportOutputFormatSelector(scanner);
        
        boolean lecturerLoggedIn = true;
        while (lecturerLoggedIn) {
            System.out.println("");
            System.out.println("****************************************************************************");
            System.out.println("Course Management System - Lecturer Menu");
            System.out.println("****************************************************************************");
            System.out.println("1) Generate Lecturer Report");
            System.out.println("4) Change Username");
            System.out.println("5) Change Password");
            System.out.println("0. Logout");

            int choice = getIntInput();
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Lecturer's ID for the report: ");
                        int lecturerId = getIntInput();
                        ReportOutput reportOutput = formatSelector.selectFormat();
                        reportGenerator.generateLecturerReport(lecturerId, reportOutput);
                        break;
                    case 0:
                        lecturerLoggedIn = false;
                        System.out.println("Logging out...");
                        break;
                    case 4:
                        changeUsername();
                        break;
                    case 5:
                        changePassword();
                        break;
                    default:
                        System.out.println("!!!Invalid choice!!!");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * Insert new user into database
     */
    private void addUser() {
        boolean checkingUser = true; // Flag to control the loop
        while (checkingUser) { // Loop until user input is valid and user is successfully added
            System.out.println("");
            System.out.println("*** Enter new user's details ***");
            System.out.print("Enter new username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter user's role (ADMIN, OFFICE, LECTURER): ");
            String userRoleStr = scanner.nextLine().toUpperCase(); // Convert input to uppercase for matching

            try {
                UserRole userRole = UserRole.valueOf(userRoleStr); // Convert string to corresponding UserRole enum value
                User newUser = new User(username, password, userRole); // Create user with specified role
                if(userDao.insertUser(newUser)) { // Try to insert the new user into the database
                    System.out.println("");
                    System.out.println("User successfully added.");
                    checkingUser = false; // Exit loop after successful addition
                } else {
                    System.out.println("");
                    System.out.println("Failed to add user."); // Inform the user about failure to add user
                }
            } catch (IllegalArgumentException e) { // Exception in case of an invalid role provided by user
                System.out.println("Invalid role specified. Please enter a valid role (ADMIN, OFFICE or LECTURER).");
            }
        }
    }
    
    /**
    * This method update user details (username, password, user role)
     */
    private void modifyUser() {
        System.out.println("");
        System.out.println("*** Update user details***");
        System.out.print("Enter the username of the user to modify: ");
        String username = scanner.nextLine();

        User user = userDao.getUser(username); // Retrieve user information from cms database
        if (user == null) { // If no user found exit out of the method
            System.out.println("User not found.");
            return;
        }

        boolean updating = true; // Flag to control the loop
        while (updating) {
            System.out.println("");
            System.out.println("Select details to update:");
            System.out.println("1) Change username");
            System.out.println("2) Change password");
            System.out.println("3) Change user role");
            System.out.println("0) Go back");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1: // Prompt user for a new username and update it
                    System.out.println("");
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    user.setUsername(newUsername);
                    break;
                case 2: // Prompt user for a new password and update it
                    System.out.println("");
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    user.setPasswordHash(newPassword);
                    break;
                case 3: // Prompt user for a new user role and update it
                    System.out.println("");
                    System.out.print("Enter new user role (ADMIN, OFFICE, LECTURER): ");
                    String userRoleStr = scanner.nextLine().toUpperCase(); // Convert user input to upper case
                    try {
                        UserRole newRole = UserRole.valueOf(userRoleStr);
                        user.setUserRole(newRole);
                    } catch (IllegalArgumentException e) { // Exception in case of an invalid role provided by user
                        System.out.println("Invalid role specified. Please enter a valid role (ADMIN, OFFICE or LECTURER).");
                    }
                    break;
                case 0:
                    updating = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

            // If a valid choice was made, update the tow in the database
            if (choice > 0 && choice <= 3) {
                userDao.updateUser(user); 
                System.out.println("User information updated successfully.");
            }
        }
    }
    
    private void deleteUser() {
        System.out.println("");
        System.out.println("*** Delete user ***");
        System.out.print("Enter username of the user to be deleted: ");
        String username = scanner.nextLine();

        if (userDao.deleteUser(username)) {
            System.out.println("");
            System.out.println("User deleted.");
        } else {
            System.out.println("");
            System.out.println("Failed to delete user.");
        }
    }

}
