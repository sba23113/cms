/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import tables.Enrollment;
import java.sql.*;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class EnrollmentDaoImpl implements EnrollmentDao {
    private Connection conn;

    public EnrollmentDaoImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Method retrieves an enrollment by its ID.
     * @param enrollmentId
     * @return 
     */
    @Override // method is overriding a StudentDao interface method
    public Enrollment getEnrollmentById(int enrollmentId) {
        String sql = "SELECT * FROM Enrollments WHERE EnrollmentID = ?"; // SQL query to select all columns from the Enrollments table where the EnrollmentID column matches the requested value
        Enrollment enrollment = null; // Initialize Enrollment object to null so that null is returned if matching Enrollment is not found

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setInt(1, enrollmentId); // Replace the ? in the above SQL query with the provided studentId
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the SQL query -> store result in ResultSet object
                if (rs.next()) {  // Check if the query returned at least 1 result
                    enrollment = new Enrollment(  // Create a new Enrollment object
                        rs.getInt("EnrollmentID"),
                        rs.getInt("StudentID"),
                        rs.getInt("ModuleID"),
                        rs.getInt("CourseID"),
                        rs.getString("Status"),
                        rs.getInt("ModuleAttempt")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return enrollment;  //  Return Enrollment object
    }
}
