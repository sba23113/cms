/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tables.Module;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class ModuleDaoImpl implements ModuleDao {
private Connection conn;

    public ModuleDaoImpl(Connection conn) {
        this.conn = conn;
    }

   /**
    * Method retrieves a module by its ID
    * @param moduleId
    * @return 
    */
    @Override
    public Module getModuleById(int moduleId) {
        String sql = "SELECT * FROM Modules WHERE ModuleID = ?"; // SQL query to select a module by its ID
        Module module = null; // Initialize module object to null (if module cannot be found, method will return null)

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement to prevent SQL injection attacks wrapped in try-with-resources to prevent resouce leaks
            pstmt.setInt(1, moduleId);  // Replace the ? in the above SQL query with the provided moduleId
            ResultSet rs = pstmt.executeQuery(); // Execute the SQL query -> store result in ResultSet object

            if (rs.next()) { // Check if the query returned at least 1 result
                module = new Module(rs.getInt("ModuleID"), rs.getString("ModuleCode"), rs.getString("ModuleName"), rs.getString("ModuleDescription"), rs.getInt("Credits"), rs.getInt("ModuleID"), rs.getInt("RoomID")); // Create a new Module object
            }
        } catch (SQLException e) {
            e.printStackTrace(); // If an exception is caught print the error message to the console. 
        }

        return module; //  Return Module object
    }

    /**
     * Method retrieves all modules from cms database
     * @return 
     */
    @Override
    public List<Module> getAllModules() {
        List<Module> modules = new ArrayList<>(); // Initialize module ArrayList
        String sql = "SELECT * FROM Modules"; // SQL query to select all modules

        try (Statement stmt = conn.createStatement(); // Create a statement object
             ResultSet rs = stmt.executeQuery(sql)) { // Execute the SQL query -> store result in ResultSet object
                        
            while (rs.next()) { // Iterate over each row in the result set
                Module module = new Module(rs.getInt("ModuleID"), rs.getString("ModuleCode"), rs.getString("ModuleName"), rs.getString("ModuleDescription"), rs.getInt("Credits"), rs.getInt("ModuleID"), rs.getInt("RoomID")); // Create a new Module object
                modules.add(module); // Add the object to the modules ArrayList
            }
        } catch (SQLException e) {
            e.printStackTrace(); // If an exception is caught print the error message to the console
        }

        return modules; // Return list of modules
    }
}
