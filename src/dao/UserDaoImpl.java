/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import tables.User;
import java.sql.*;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class UserDaoImpl implements UserDao {
    private Connection conn;
    
    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User getUser(String username) {
        User user = null; // Initialize User object to null so that null is returned if matching user is not found
        String sql = "SELECT * FROM Users WHERE Username = ?"; //  SQL query to select all columns from the Users table where the Username column matches the requested value
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setString(1, username); // Replace the ? in the above SQL query with the provided username
            ResultSet rs = pstmt.executeQuery(); // Execute the SQL query -> store result in ResultSet object

            if (rs.next()) { // Check if the query returned at least 1 result
                int userID = rs.getInt("UserID");
                String userRole = rs.getString("UserRole");
                String passwordHash = rs.getString("PasswordHash");
                user = new User(userID, username, passwordHash, userRole); // create new User object
            }
        } catch (SQLException e) {
            System.out.println(e); // If an exception is caught print the error message to the console. 
        }
        
        return user; //  Return User object
    }
}
