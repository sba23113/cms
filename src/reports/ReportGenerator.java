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
import java.io.IOException;
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
     * @param reportOutput
     * @throws IOException 
     */
    public void generateLecturerReport(int lecturerId, ReportOutput reportOutput) throws IOException {
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
            reportData.addRow(row); 
        }

        reportOutput.exportReport(reportData, "LecturerReport_" + lecturerId);
    }
    
    // STUDENT REPORT
    /**
     * Method produces report for a student
     * @param studentId
     * @param reportOutput
     * @throws IOException 
     */
    public void generateStudentReport(int studentId, ReportOutput reportOutput) throws IOException {
        Student student = studentDao.getStudentById(studentId);  // retrieve Student object for given lecturerId -> if not found, evaluate to null
        if (student == null) {
            System.out.println("Student not found");
            return;
        }

        ReportData reportData = new ReportData();
        List<String> header = List.of("Student Name", "Programme", "Modules Enrolled", "Modules Completed", "Modules to Repeat");
        reportData.addRow(header);

        Course course = courseDao.getCourseById(student.getCourseID());   // get course the student is undertaking
        List<String> row = new ArrayList<>();
        row.add(student.getFirstName() + " " + student.getLastName()); // if found, add student name
        row.add(course != null ? course.getCourseName() : "N/A");

        List<Enrollment> enrollments = enrollmentDao.getEnrollmentsByStudentId(studentId); // Get list of current, completed, and repeat modules
        List<String> modulesEnrolled = new ArrayList<>();
        List<String> modulesCompleted = new ArrayList<>();
        List<String> modulesRepeat = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            Module module = moduleDao.getModuleById(enrollment.getModuleID());
            String moduleName = module != null ? module.getModuleName() : "N/A";
            switch (enrollment.getStatus()) {
                case "Enrolled":
                    modulesEnrolled.add(moduleName);
                    break;
                case "Completed":
                    modulesCompleted.add(moduleName);
                    break;
                case "Repeat":
                    modulesRepeat.add(moduleName);
                    break;
                default:
                    break;
            }
        }

        row.add(String.join(", ", modulesEnrolled));
        row.add(String.join(", ", modulesCompleted));
        row.add(String.join(", ", modulesRepeat));

        reportData.addRow(row);

        reportOutput.exportReport(reportData, "StudentReport_" + studentId);
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
