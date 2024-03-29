/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Tables;

/**
 * 
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public class Module {
    private int moduleID;
    private String moduleCode;
    private String moduleName;
    private String moduleDescription;
    private int credits;
    private int courseID;
    private int roomID;

    public Module(int moduleID, String moduleCode, String moduleName, String moduleDescription, int credits, int courseID, int roomID) {
        this.moduleID = moduleID;
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.credits = credits;
        this.courseID = courseID;
        this.roomID = roomID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public int getCredits() {
        return credits;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }   
    
}
