/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dataprocessing;

import dao.UserDaoImpl;
import tables.User;
import tables.UserRole;

import databaseintegration.DBConnector;
import java.sql.Connection;
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
        
        try (Connection conn = DBConnector.connect()) {
            UserDaoImpl userDao = new UserDaoImpl(conn);
            
            User newUser = new User("b", "54wet1g61jk61./'p/61.", UserRole.OFFICE);
            
            if (userDao.insertUser(newUser)) {
                System.out.println("New user was successfully inserted.");
            } else {
                System.out.println("Failed to insert new user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
