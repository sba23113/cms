/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import tables.Grade;
import java.sql.*;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class GradeDaoImpl implements GradeDao {
    private Connection conn;

    public GradeDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Grade getGradeById(int gradeId) {
        String sql = "SELECT * FROM Grade WHERE GradeID = ?"; // SQL query to select all columns from the Grades table where the GradeID column matches the requested value
        Grade grade = null; // Initialize Grade object to null so that null is returned if matching Grade is not found

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setInt(1, gradeId); // Replace the ? in the above SQL query with the provided studentId
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the SQL query -> store result in ResultSet object
                if (rs.next()) {  // Check if the query returned at least 1 result
                    grade = new Grade(  // Create a new Grade object
                        rs.getInt("GradeID"),
                        rs.getInt("EnrollmentID"),
                        rs.getFloat("GradeValue")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return grade;  //  Return Grade object
    }
    
    @Override
    public Grade getGradeByEnrollmentId(int enrollmentId) {  
        String sql = "SELECT * FROM Grades WHERE EnrollmentID = ?"; // SQL query to select all columns from the Grades table where the enrollmentId column matches the requested value
        Grade grade = null;  // Initialize Grade object to null so that null is returned if matching Grade is not found

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setInt(1, enrollmentId); // Replace the ? in the above SQL query with the provided enrollmentId
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the SQL query -> store result in ResultSet object
                if (rs.next()) { // Check if the query returned at least 1 result
                    grade = new Grade(  // Create a new Grade object
                        rs.getInt("GradeID"),
                        rs.getInt("EnrollmentID"),
                        rs.getFloat("GradeValue")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return grade; //  Return Grade object
    }
}
