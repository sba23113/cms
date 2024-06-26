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
public class Student {
    private int studentID;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String phoneNumber;
    private int courseID;
    private boolean isActive;
    private Date dateOfBirth;
    private int userID;

    public Student(int studentID, String firstName, String lastName, String gender, String email, String phoneNumber, int courseID, boolean isActive, Date dateOfBirth, int userID) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.courseID = courseID;
        this.isActive = isActive;
        this.dateOfBirth = dateOfBirth;
        this.userID = userID;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getCourseID() {
        return courseID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getUserID() {
        return userID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    
}