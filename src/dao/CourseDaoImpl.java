/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import tables.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class CourseDaoImpl implements CourseDao {
    private Connection conn;

    public CourseDaoImpl(Connection conn) {
        this.conn = conn;
    }

   /**
    * Method retrieves a course by its ID
    * @param courseId
    * @return 
    */
    @Override
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM Courses WHERE CourseID = ?"; // SQL query to select a course by its  ID
        Course course = null; // Initialize course object to null (if course cannot be found, method will return null)

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setInt(1, courseId);  // Replace the ? in the above SQL query with the provided courseId
            ResultSet rs = pstmt.executeQuery(); // Execute the SQL query -> store result in ResultSet object

            if (rs.next()) { // Check if the query returned at least 1 result
                int id = rs.getInt("CourseID");
                String name = rs.getString("CourseName");
                course = new Course(id, name); // create new Course object
            }
        } catch (SQLException e) {
            e.printStackTrace(); // If an exception is caught print the error message to the console. 
        }

        return course; //  Return Course object
    }

    /**
     * Method retrieves all courses from cms database
     * @return 
     */
    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>(); // Initialize course ArrayList
        String sql = "SELECT * FROM Courses"; // SQL query to select all courses

        try (Statement stmt = conn.createStatement(); // Create a statement object
             ResultSet rs = stmt.executeQuery(sql)) { // Execute the SQL query -> store result in ResultSet object
                        
            while (rs.next()) { // Iterate over each row in the result set
                int courseId = rs.getInt("CourseID");
                String courseName = rs.getString("CourseName");
                Course course = new Course(courseId, courseName); // Create a new course object
                courses.add(course); // Add the object to the courses ArrayList
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return courses; // Return list of courses
    }
}