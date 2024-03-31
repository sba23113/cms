/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package reports;

import reports.outputformats.ConsoleFormat;
import reports.outputformats.CsvFormat;
import reports.outputformats.TxtFormat;
import java.util.Scanner;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class ReportOutputFormatSelector {
    private Scanner scanner;
    private ReportGenerator reportGenerator;

    public ReportOutputFormatSelector(Scanner scanner, ReportGenerator reportGenerator) {
        this.scanner = scanner;
        this.reportGenerator = reportGenerator;
    }
    
    public ReportOutput selectFormat() {
        while (true) { // ideally shouldnt be using while(true) but a boolean flag instead, unsure how to implement it here
            System.out.println("Select the output format for the report:");
            System.out.println("1) TXT File");
            System.out.println("2) CSV File");
            System.out.println("3) Console Output");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    return new TxtFormat();
                case 2:
                    return new CsvFormat();
                case 3:
                    return new ConsoleFormat();
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
