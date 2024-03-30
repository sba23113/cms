/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;


import tables.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class StudentDaoImpl implements StudentDao {
    private Connection conn;
    
    public StudentDaoImpl(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * Method retrieves a student by their ID.
     * @param studentId
     * @return 
     */
    @Override // method is overriding a StudentDao interface method
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM Students WHERE StudentID = ?"; //  SQL query to select all columns from the Students table where the StudentID column matches the requested value
        Student student = null; // Initialize Student object to null so that null is returned if matching Student is not found

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setInt(1, studentId); // Replace the ? in the above SQL query with the provided studentId
            ResultSet rs = pstmt.executeQuery(); // Execute the SQL query -> store result in ResultSet object

            if (rs.next()) {  // Check if the query returned at least 1 result
                student = new Student( // Create a new Student object
                    rs.getInt("StudentID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Gender"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber"),
                    rs.getInt("CourseID"),
                    rs.getBoolean("IsActive"),
                    rs.getDate("DateOfBirth"),
                    rs.getInt("UserID")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return student; //  Return Student object
    }
    
    /**
     * * Method returns a list of all student in cms database
     * @return 
     */
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>(); // Initialize empty ArrayList for Student objects        
        String sql = "SELECT * FROM Students"; // SQL query to select all students.

        try (Statement stmt = conn.createStatement();  // Create a statement object
             ResultSet rs = stmt.executeQuery(sql)) { // Execute the SQL query -> store result in ResultSet object
            
            while (rs.next()) { // Iterate over each row in the result set
                Student student = new Student( // Create a new Student object
                    rs.getInt("StudentID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Gender"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber"),
                    rs.getInt("CourseID"),
                    rs.getBoolean("IsActive"),
                    rs.getDate("DateOfBirth"),
                    rs.getInt("UserID")
                );
                students.add(student); // Add the object to the students ArrayList
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return students; // return list of students
    }
}
