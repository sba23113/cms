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
        private int birthYear;
        private int birthMonth;
        private Date dateOfBirth;

        // constructor
    public Lecturer(int lecturerID, String firstName, String lastName, String email, String phoneNumber, int userID, String lecturerRole, int birthYear, int birthMonth, Date dateOfBirth) {
        this.lecturerID = lecturerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
        this.lecturerRole = lecturerRole;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.dateOfBirth = dateOfBirth;
    }

    // getters and setters
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

    public int getBirthYear() {
        return birthYear;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
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

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }            
}
