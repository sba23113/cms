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
        User user = null;
        String sql = "SELECT * FROM Users WHERE Username = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            int userID = rs.getInt("UserID");
            String userRole = rs.getString("UserRole");
            String passwordHash = rs.getString("PasswordHash");
            user = new User(userID, username, passwordHash, userRole);
        }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return user;
    }
}
