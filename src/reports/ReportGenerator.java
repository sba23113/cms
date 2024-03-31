/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package reports;

import dao.EnrollmentDao;
import dao.LecturerDao;
import dao.ModuleDao;
import java.util.List;
import tables.Lecturer;
import tables.Module;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class ReportGenerator {
    private EnrollmentDao enrollmentDao;
    private LecturerDao lecturerDao;
    private ModuleDao moduleDao;

    public ReportGenerator(EnrollmentDao enrollmentDao, LecturerDao lecturerDao, ModuleDao moduleDao) {
        this.enrollmentDao = enrollmentDao;
        this.lecturerDao = lecturerDao;
        this.moduleDao = moduleDao;
    }
    
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
}
