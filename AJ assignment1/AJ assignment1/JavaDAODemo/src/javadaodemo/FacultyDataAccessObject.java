/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

import java.util.List;

/**
 *
 * @author User
 */
public interface FacultyDataAccessObject {
    Faculty insert(Faculty faculty);
    Faculty getFacultybyInitial(String code);
    List <Faculty> getAllFaculty();
    Faculty update(String code, Faculty faculty);
    void delete(String code);
    void deleteAll();
}
