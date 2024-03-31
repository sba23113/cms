/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package reports;

import dao.LecturerDao;
import tables.Lecturer;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class ReportGenerator {
    private LecturerDao lecturerDao;

    public ReportGenerator(LecturerDao lecturerDao) {
        this.lecturerDao = lecturerDao;
    }
    
    /**
     * Method produces report for a lecturer
     * @param lecturerId
     * @return 
     */
    public void generateLecturerReport(int lecturerId) {
        Lecturer lecturer = lecturerDao.getLecturerById(lecturerId); // retrieve Lecturer object for given lecturerId -> if not found, evaluate to null
        if (lecturer == null) {
            System.out.println("Lecturer not found.");
        }

        System.out.println("Lecturer: " + lecturer.getFirstName() + " " + lecturer.getLastName()); // if found, print out lecturer details
        System.out.println("Role: " + lecturer.getLecturerRole());
        System.out.println("");

        
    }
}
