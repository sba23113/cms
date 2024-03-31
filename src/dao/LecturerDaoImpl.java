/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import tables.Lecturer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class LecturerDaoImpl implements LecturerDao {
    private Connection conn;

    // Constructor initializes the database connection.
    public LecturerDaoImpl(Connection conn) {
        this.conn = conn;
    }

    /**
      * Method retrieves a lecturer by their lecturerID.
     * @param lecturerId
     * @return 
     */
    @Override // method is overriding a LecturerDao interface method
    public Lecturer getLecturerById(int lecturerId) {
        String sql = "SELECT * FROM Lecturers WHERE LecturerID = ?"; //  SQL query to select all columns from the Lecturers table where the LecturerID column matches the requested value
        Lecturer lecturer = null; // Initialize a Lecturer object to null so that null is returned if a matching Lecturer is not found

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setInt(1, lecturerId); // Replace the ? in the above SQL query with the provided lecturerId
            ResultSet rs = pstmt.executeQuery(); // Execute the SQL query -> store result in ResultSet object

            if (rs.next()) {  // Check if the query returned at least 1 result
                lecturer = new Lecturer( // Create a new Student object
                    rs.getInt("LecturerID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber"),
                    rs.getInt("UserID"),
                    rs.getString("LecturerRole"),
                    rs.getString("SubjectsTaught")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return lecturer; // Return Lecturer object
    }

    /**
     * Method returns a list of all lecturers in cms database
     * @return 
     */
    @Override // method is overriding a StudentDao interface method
    public List<Lecturer> getAllLecturers() {
        List<Lecturer> lecturers = new ArrayList<>(); // Initialize empty ArrayList for Lecturer objects        
        String sql = "SELECT * FROM Lecturers"; // SQL query to select all lecturers

        try (Statement stmt = conn.createStatement(); // Create a statement object
             ResultSet rs = stmt.executeQuery(sql)) { // Execute the SQL query -> store result in ResultSet object

            while (rs.next()) { // Iterate over each row in the result set
                Lecturer lecturer = new Lecturer( // Create a new Lecturer object
                    rs.getInt("LecturerID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber"),
                    rs.getInt("UserID"),
                    rs.getString("LecturerRole"),
                    rs.getString("SubjectsTaught")
                );
                lecturers.add(lecturer); // Add the object to the lecturers ArrayList
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return lecturers;
    }
    
    @Override
    public Lecturer getLecturerByModuleId(int moduleId) {
        Lecturer lecturer = null;
        String sql = "SELECT l.* FROM Lecturers l " + "JOIN LecturerModules lm ON l.LecturerID = lm.LecturerID " + "WHERE lm.ModuleID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, moduleId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                lecturer = new Lecturer(
                    rs.getInt("LecturerID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber"),
                    rs.getInt("UserID"),
                    rs.getString("LecturerRole"),
                    rs.getString("SubjectsTaught")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return lecturer;
    }
}
