/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package tables;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class User {
    private int userID;
    private String username;
    private String passwordHash;
    private UserRole userRole;

    // Constructors
    public User(String username, String passwordHash, UserRole userRole) { // no userId
        this.username = username;
        this.passwordHash = passwordHash;
        this.userRole = userRole;
    }
    
    public User(int userID, String username, String passwordHash, UserRole userRole) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.userRole = userRole;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
