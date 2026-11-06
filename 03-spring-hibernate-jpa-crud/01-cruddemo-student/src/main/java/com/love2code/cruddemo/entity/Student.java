package com.love2code.cruddemo.entity;

import jakarta.persistence.*;

@Entity//Mapping class to a database table
@Table(name= "student")
public class Student {
//Mapping fields to data base columns
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String emailAdd;

    public Student() {

    }

    public Student(String firstName, String lastName, String emailAdd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAdd='" + emailAdd + '\'' +
                '}';
    }
}
