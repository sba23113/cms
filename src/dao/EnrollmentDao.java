/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import tables.Enrollment;

/**
 *
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public interface EnrollmentDao {
    Enrollment getEnrollmentById(int enrollmentId);
    int getEnrollmentCountByModuleId(int moduleId);
    List<Enrollment> getEnrollmentsByStudentId(int studentId);
}
