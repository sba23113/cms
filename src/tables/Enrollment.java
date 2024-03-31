/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package tables;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class Enrollment {
    private int enrollmentID;
    private int studentID;
    private int moduleID;
    private int courseID;
    private String status;
    private int moduleAttempt;

    public Enrollment(int enrollmentID, int studentID, int moduleID, int courseID, String status, int moduleAttempt) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.moduleID = moduleID;
        this.courseID = courseID;
        this.status = status;
        this.moduleAttempt = moduleAttempt;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getStatus() {
        return status;
    }

    public int getModuleAttempt() {
        return moduleAttempt;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setModuleAttempt(int moduleAttempt) {
        this.moduleAttempt = moduleAttempt;
    }
}
