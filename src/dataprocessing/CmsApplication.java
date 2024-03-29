/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dataprocessing;

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
        // to insert admin into the database, run 'insert-admin-into-database.sql'
        try {
            Connection conn = DBConnector.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
