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
import java.util.ArrayList;

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
            return;
        }

        ReportData reportData = new ReportData();
        List<String> header = List.of("Module Name", "Module Code", "Student Count");
        reportData.addRow(header);

        List<Module> modules = moduleDao.getModulesByLecturerId(lecturerId);
        for (Module module : modules) {
            int studentCount = enrollmentDao.getEnrollmentCountByModuleId(module.getModuleID());
            List<String> row = new ArrayList<>();
            row.add(module.getModuleName());
            row.add(module.getModuleCode());
            row.add(String.valueOf(studentCount));
            reportData.addRow(row); // Add the module data as a row to the report
        }

        // Use the provided ReportOutput implementation to export the report.
        reportOutput.exportReport(reportData, "LecturerReport_" + lecturerId);
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
    
    // COURSES REPORT
    /**
     * Method produces courses report
     * @return 
     */
    public void generateCourseReport(int courseId) {
        Course course = courseDao.getCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println("Course Report for " + course.getCourseName() + ":");
                
        List<Module> modules = moduleDao.getModulesByCourseId(courseId);// Get all modules for the specified course
        for (Module module : modules) {
            System.out.println("   Module Name: " + module.getModuleName());
            System.out.println("   Module Code: " + module.getModuleCode());
                        
            Lecturer lecturer = lecturerDao.getLecturerByModuleId(module.getModuleID()); // Get the lecturer for each module
            if (lecturer != null) {
                System.out.println("   Module Lecturer: " + lecturer.getFirstName() + " " + lecturer.getLastName());
            } else {
                System.out.println("N/A");
            }
                        
            int enrollmentCount = enrollmentDao.getEnrollmentCountByModuleId(module.getModuleID()); // Get enrollment count for each module
            System.out.println("   Number of Students Enrolled: " + enrollmentCount);
            
            String room;
            if (module.getRoomID() > 0) {
                room = "Room " + module.getRoomID();
            } else {
                room = "Online";
            }            
            System.out.println("   Room: " + room);
            System.out.println("");
        }
    }
}
