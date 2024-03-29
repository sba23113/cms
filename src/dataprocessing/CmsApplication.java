/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dataprocessing;

import dao.UserDaoImpl;
import tables.User;

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
            User user = userDao.getUser("admin");
            if (user != null) {
                System.out.println("User found: " + user.getUsername());
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
