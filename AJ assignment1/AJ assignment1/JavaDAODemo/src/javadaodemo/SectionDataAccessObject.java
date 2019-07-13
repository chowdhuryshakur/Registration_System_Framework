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
public interface SectionDataAccessObject {
     Section insert(Section section);
    Section getSectionbyID(int id);
    List <Section> getAllSection();
    Section update(int Id, Section section);
    void delete(int Id);
    void deleteAll();
}
