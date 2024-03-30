/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dataprocessing;

import dao.UserDao;
import java.util.InputMismatchException;
import java.util.Scanner;
import tables.User;
import tables.UserRole;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class MenuSystem {
    private Scanner scanner;
    private UserDao userDao;

    public MenuSystem(Scanner scanner, UserDao userDao) {
        this.scanner = scanner;
        this.userDao = userDao;
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
        
        boolean isAuthenticated = true; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!REPLACE WITH ACTUAL AUTHENTICATION LOGIC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        if (isAuthenticated) {
            showRoleBasedMenu();
        } else {
            System.out.println("Invalid login details! Please try again!");
        }
    }

    /**
     * Display menu based on user's role
     */
    private void showRoleBasedMenu() {
        boolean loggedIn = true; // Flag to control the loop
        while (loggedIn) {
            // CMS admin menu
            System.out.println("");
            System.out.println("****************************************************************************");
            System.out.println("Course Management System - Administrator Menu");
            System.out.println("****************************************************************************");
            System.out.println("1) Add User");
            System.out.println("2) Modify User");
            System.out.println("3) Delete User");
            System.out.println("0) Logout");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();                     
            
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    System.out.println("User Modified");
                    break;
                case 3:
                    System.out.println("User Deleted");
                    break;
                case 0:
                    System.out.println("Logging out...");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("!!!Invalid choice!!!");
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
}
