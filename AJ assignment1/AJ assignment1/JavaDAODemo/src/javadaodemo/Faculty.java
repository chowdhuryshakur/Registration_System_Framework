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
public class Faculty {
    private String initial;
    private String name;
    private String rank;

    public Faculty(String initial, String name, String rank) {
        this.initial = initial;
        this.name = name;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Faculty{" + "initial=" + initial + ", name=" + name + ", rank=" + rank + '}';
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
        final Faculty other = (Faculty) obj;
        if (!Objects.equals(this.initial, other.initial)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.rank, other.rank)) {
            return false;
        }
        return true;
    }
    
    
}
