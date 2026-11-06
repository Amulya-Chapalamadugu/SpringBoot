package com.love2code.springboot.cruddemo.entity;

import jakarta.persistence.*;
//import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
//import org.springframework.data.annotation.Id;

@Entity
@Table(name="employee")
public class Employee {

    //define fields
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name ="email")
    private String emailAdd;

    //define Constructors

    public Employee(String firstName, String lastName, String emailAdd) {
        //this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
    }

    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    //define toString method


    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAdd='" + emailAdd + '\'' +
                '}';
    }
}
