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
            
            User existingUser = userDao.getUser("a");
            
            if (existingUser != null) {
                existingUser.setPasswordHash("newPasswordHash");
                existingUser.setUserRole(UserRole.LECTURER);
                
                boolean isUpdated = userDao.updateUser(existingUser);
                if (isUpdated) {
                    System.out.println("Successfully updated.");
                } else {
                    System.out.println("Update failed.");
                }
            } else {
                System.out.println("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
