package com.taskGWT.client.Entities;

import java.sql.Timestamp;
import java.util.Date;

public class Person {

    public Person(String name, String lastName, Date birthDate, Timestamp time) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.time = time;
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    private String name;

    private String lastName;

    private Date birthDate;

    private Timestamp time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
