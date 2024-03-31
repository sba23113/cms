/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package tables;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class Grade {
    private int gradeID;
    private int enrollmentID;
    private float gradeValue;

    public Grade(int gradeID, int enrollmentID, float gradeValue) {
        this.gradeID = gradeID;
        this.enrollmentID = enrollmentID;
        this.gradeValue = gradeValue;
    }

    public int getGradeID() {
        return gradeID;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public float getGradeValue() {
        return gradeValue;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public void setGradeValue(float gradeValue) {
        this.gradeValue = gradeValue;
    }
    
    
}
