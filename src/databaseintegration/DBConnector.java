/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package databaseintegration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class DBConnector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/cms";
        private static final String DATABASE_USER = "pooa2024";
        private static final String DATABASE_PASSWORD = "pooa2024";

        public static Connection connect() throws SQLException {
            try {
                return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("Could not connect to database", e);
            }
        }
}
