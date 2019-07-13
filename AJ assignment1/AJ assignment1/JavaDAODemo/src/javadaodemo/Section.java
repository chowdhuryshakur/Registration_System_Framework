/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Section {
    private int sectionId;
    private int sectionNumber;
    private int semester;
    private int seatLimit;
    private String code;
    private String initial;

    public Section(int sectionId, int sectionNumber, int semester, int seatLimit, String code, String initial) {
        this.sectionId = sectionId;
        this.sectionNumber = sectionNumber;
        this.semester = semester;
        this.seatLimit = seatLimit;
        this.code = code;
        this.initial = initial;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getSeatLimit() {
        return seatLimit;
    }

    public void setSeatLimit(int seatLimit) {
        this.seatLimit = seatLimit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Section{" + "sectionId=" + sectionId + ", sectionNumber=" + sectionNumber + ", semester=" + semester + ", seatLimit=" + seatLimit + ", code=" + code + ", initial=" + initial + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Section other = (Section) obj;
        if (this.sectionId != other.sectionId) {
            return false;
        }
        if (this.sectionNumber != other.sectionNumber) {
            return false;
        }
        if (this.semester != other.semester) {
            return false;
        }
        if (this.seatLimit != other.seatLimit) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.initial, other.initial)) {
            return false;
        }
        return true;
    }
    
    
}
