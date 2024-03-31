/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package reports;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class ReportData {
    private List<List<String>> rows;

    public ReportData() {
        this.rows = new ArrayList<>();
    }

    public void addRow(List<String> row) {
        this.rows.add(row);
    }

    public List<List<String>> getRows() {
        return this.rows;
    }
}
