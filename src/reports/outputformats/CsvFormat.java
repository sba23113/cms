/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package reports.outputformats;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import reports.ReportData;
import reports.ReportOutput;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class CsvFormat implements ReportOutput { // Implement the ReportOutput interface

    @Override
    public void exportReport(ReportData reportData, String reportName) throws IOException {
        try (FileWriter writer = new FileWriter(reportName + ".csv")) {
            for (List<String> row : reportData.getRows()) {
                String line = String.join(",", row); // For CSV, we separate items with a comma
                writer.write(line + System.lineSeparator());
            }
        }
    }
}