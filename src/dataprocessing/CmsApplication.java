/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dataprocessing;

import dao.CourseDaoImpl;
import dao.UserDaoImpl;
import tables.User;
import tables.UserRole;

import databaseintegration.DBConnector;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import tables.Course;

/**
 *
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class CmsApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        to insert admin into the database, run 'insert-admin-into-database.sql'
        */
        
       Scanner scanner = new Scanner(System.in);
       
       try (Connection conn = DBConnector.connect()) {
           CourseDaoImpl courseDao = new CourseDaoImpl(conn);
            UserDaoImpl userDao = new UserDaoImpl(conn);            
            
            List<Course> courses = courseDao.getAllCourses();
            for (Course course : courses) {
                System.out.println(course.getCourseID() + " " + course.getCourseName());
            }
            
            MenuSystem menuSystem = new MenuSystem(scanner, userDao);
            menuSystem.displayMainMenu();
            /*
            List<User> users = userDao.getAllUsers();
            
            if (users.isEmpty()) {
                System.out.println("There are no users to show.");
            } else {
                System.out.printf("%-10s %-20s %-30s %s\n", "UserID", "Username", "Password Hash", "Role");
                System.out.println("------------------------------------------------------------------------------");

                for (User user : users) {
                    System.out.printf("%-10d %-20s %-30s %s\n",
                        user.getUserID(),
                        user.getUsername(),
                        user.getPasswordHash(),
                        user.getUserRole());
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
