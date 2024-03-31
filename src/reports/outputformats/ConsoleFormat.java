/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package reports.outputformats;

import java.util.List;
import reports.ReportData;
import reports.ReportOutput;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class ConsoleFormat implements ReportOutput {

    @Override
    public void exportReport(ReportData reportData, String reportName) {
        System.out.println("Report Name: " + reportName);
        for (List<String> row : reportData.getRows()) {
            String line = String.join(", ", row);
            System.out.println(line);
        }
    }
}