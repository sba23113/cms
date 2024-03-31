/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import tables.Grade;

/**
 *
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public interface GradeDao {
    Grade getGradeById(int gradeId);
}
