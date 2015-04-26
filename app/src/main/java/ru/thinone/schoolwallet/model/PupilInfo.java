package ru.thinone.schoolwallet.model;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class PupilInfo {

    private int id;
    private String school;
    private String group;
    private String fio;


    public void setId(int id) {
        this.id = id;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public String getSchool() {
        return school;
    }

    public String getGroup() {
        return group;
    }

    public String getFio() {
        return fio;
    }
}
