/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package tables;

import java.util.Date;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class Lecturer {
    private int lecturerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int userID;
    private String lecturerRole;

    public Lecturer(int lecturerID, String firstName, String lastName, String email, String phoneNumber, int userID, String lecturerRole) {
        this.lecturerID = lecturerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
        this.lecturerRole = lecturerRole;
    }

    public int getLecturerID() {
        return lecturerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public String getLecturerRole() {
        return lecturerRole;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setLecturerRole(String lecturerRole) {
        this.lecturerRole = lecturerRole;
    }    
}
