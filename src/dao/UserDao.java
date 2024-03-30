/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import tables.User;
/**
 *
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public interface UserDao {
    User getUser(String username);
    Boolean insertUser(User user);
}
