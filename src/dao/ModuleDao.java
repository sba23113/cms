/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import tables.Module;
import java.util.List;

/**
 *
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public interface ModuleDao {
    Module getModuleById(int moduleId);
    List<Module> getAllModules();
}
