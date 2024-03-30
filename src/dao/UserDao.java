/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import tables.User;

import java.util.List;
/**
 *
 * @author Lukas Homola <sba23113@student.cct.ie>
 */
public interface UserDao {
    User getUser(String username);
    List<User> getAllUsers();
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(String username);
}
