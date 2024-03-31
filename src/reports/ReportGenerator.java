/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package reports;

import dao.CourseDao;
import dao.EnrollmentDao;
import dao.GradeDao;
import dao.LecturerDao;
import dao.ModuleDao;
import dao.StudentDao;

import java.util.List;
import tables.Course;
import tables.Enrollment;
import tables.Grade;
import tables.Lecturer;
import tables.Module;
import tables.Student;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class ReportGenerator {
    private CourseDao courseDao;
    private EnrollmentDao enrollmentDao;
    private GradeDao gradeDao;
    private LecturerDao lecturerDao;
    private ModuleDao moduleDao;
    private StudentDao studentDao;

    public ReportGenerator(CourseDao courseDao, EnrollmentDao enrollmentDao, GradeDao gradeDao, LecturerDao lecturerDao, ModuleDao moduleDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.enrollmentDao = enrollmentDao;
        this.gradeDao = gradeDao;
        this.lecturerDao = lecturerDao;
        this.moduleDao = moduleDao;
        this.studentDao = studentDao;
    }    
    
    // LECTURER REPORT
    /**
     * Method produces report for a lecturer
     * @param lecturerId
     * @return 
     */
    public void generateLecturerReport(int lecturerId) {
        Lecturer lecturer = lecturerDao.getLecturerById(lecturerId); // retrieve Lecturer object for given lecturerId -> if not found, evaluate to null
        if (lecturer == null) {
            System.out.println("Lecturer not found!");
        }

        System.out.println("Lecturer: " + lecturer.getFirstName() + " " + lecturer.getLastName()); // if found, print out lecturer details
        System.out.println("Role: " + lecturer.getLecturerRole());
        System.out.println("");
        System.out.println("Can teach: " + lecturer.getSubjectsTaught());
        System.out.println("");
        System.out.println("Modules taught:");
        
        List<Module> modules = moduleDao.getModulesByLecturerId(lecturerId); // get list of modules taught by the lecturer        
        
        for (Module module : modules) {
            int studentCount = enrollmentDao.getEnrollmentCountByModuleId(module.getModuleID());
            System.out.println(module.getModuleName() + " | " + "Code: " + module.getModuleCode() + " | " + "Student Count: " + studentCount); // Extract and print out module details
        }
    }
    
    // STUDENT REPORT
    /**
     * Method produces report for a student
     * @param studentId
     * @return 
     */
    public void generateStudentReport(int studentId) {
        Student student = studentDao.getStudentById(studentId);  // retrieve Student object for given lecturerId -> if not found, evaluate to null
        if (student == null) {
            System.out.println("Student not found");
        }
        Course course = courseDao.getCourseById(student.getCourseID());   // get course the student is undertaking

        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName()); // if found, print out student details
        System.out.println("Programme: " + course.getCourseName());        
        System.out.println("");
        
        List<Enrollment> enrollments = enrollmentDao.getEnrollmentsByStudentId(studentId); // Get list of current, completed, and repeat modules
        System.out.println("Current Enrollments:");
        printEnrollmentDetails(enrollments, "Enrolled");
        System.out.println("");
        System.out.println("Completed Modules:");
        printEnrollmentDetails(enrollments, "Completed");
        System.out.println("");
        System.out.println("Modules to Repeat:");
        printEnrollmentDetails(enrollments, "Repeat");
    }

    private void printEnrollmentDetails(List<Enrollment> enrollments, String status) {
        boolean found = false;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStatus().equals(status)) {
                found = true;
                Module module = moduleDao.getModuleById(enrollment.getModuleID());
                System.out.println(" - " + module.getModuleName());

                if (status.equals("Completed")) {
                    Grade grade = gradeDao.getGradeByEnrollmentId(enrollment.getEnrollmentID());
                    System.out.println("   Grade: " + (grade != null ? grade.getGradeValue() : "N/A"));
                }
            }
        }

        if (!found) {
            System.out.println("   None");
        }
    }
}
