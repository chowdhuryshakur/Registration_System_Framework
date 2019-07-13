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
public interface CourseDataAccessObject {
    Course insert(Course course);
    Course getCoursebyCode(String code);
    List <Course> getAllCourse();
    Course update(String code, Course course);
    void delete(String code);
    void deleteAll();
}
