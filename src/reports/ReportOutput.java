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
    void exportReport(String reportContent, String reportName) throws IOException;
}
