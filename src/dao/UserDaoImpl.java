/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import tables.User;
import tables.UserRole;

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
    
    /**
     * This method fetches user from database based on their username
     * @param username
     * @return 
     */
    @Override
    public User getUser(String username) {
        User user = null; // Initialize User object to null so that null is returned if matching user is not found
        String sql = "SELECT * FROM Users WHERE Username = ?"; //  SQL query to select all columns from the Users table where the Username column matches the requested value
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setString(1, username); // Replace the ? in the above SQL query with the provided username
            ResultSet rs = pstmt.executeQuery(); // Execute the SQL query -> store result in ResultSet object

            if (rs.next()) { // Check if the query returned at least 1 result
                int userID = rs.getInt("UserID");
                String passwordHash = rs.getString("PasswordHash");
                String userRoleStr = rs.getString("UserRole");
                UserRole userRole = UserRole.valueOf(userRoleStr.toUpperCase());
                user = new User(userID, username, passwordHash, userRole); // create new User object
            }
        } catch (SQLException e) {
            System.out.println(e); // If an exception is caught print the error message to the console. 
        }
        
        return user; //  Return User object
    }
        
    /**
     * This method inserts user into User table in cms database
     * @param user 
     */
    @Override // method is overriding a UserDao interface insertUser(User user) method
    public boolean insertUser(User user) {
        String sql = "INSERT INTO Users (Username, PasswordHash, UserRole) VALUES (?, ?, ?)"; //  SQL query to insert new row into Users table in cms database
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setString(1, user.getUsername()); // Replace the first ? in the above SQL query with the provided username
            pstmt.setString(2, user.getPasswordHash()); // Replace the second ? in the above SQL query with the provided password
            pstmt.setString(3, user.getUserRole().toString()); // Replace the third ? in the above SQL query with the provided role
            
            pstmt.executeUpdate(); // Execute the SQL query
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * This method updates row in User table in cms database where username matches given value
     * @param user
     * @return 
     */
    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET PasswordHash = ?, UserRole = ? WHERE Username = ?"; //  SQL query to insert new row into Users table in cms database
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setString(1, user.getPasswordHash()); // Replace the first ? in the above SQL query with the provided password
            pstmt.setString(2, user.getUserRole().toString()); // Replace the second ? in the above SQL query with the provided user role
            pstmt.setString(3, user.getUsername()); // Replace the third ? in the above SQL query with the provided username
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
