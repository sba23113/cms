/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package reports;

import java.io.IOException;

/**
 *
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public interface ReportOutput {
    /**
     * Exports the provided report data to a specific output format, identified by the reportName.
     * Implementations are responsible for the actual export logic, including file writing, console output, etc.
     *
     * @param reportData
     * @param reportName
     * @throws IOException 
     */
    void exportReport(ReportData reportData, String reportName) throws IOException; 
}
