package com.example.mahmo.retrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mahmo on 3/1/2018.
 */

public class Employee {
    @SerializedName("id")
    @Expose
    int id ;
    @SerializedName("firstName")
    @Expose
    private String firstName ;
    @SerializedName("lastName")
    @Expose
    private String lastName ;
    @SerializedName("phone")
    @Expose
    private String phone  ;
    @SerializedName("salary")
    @Expose
    private int salary ;

    public Employee(int id ,String firstName){
        this.id = id ;
        this .firstName = firstName ;
    }

    public Employee(String firstName, String lastName, int salary, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.salary = salary;
        this.phone = phone;
       // this.id = id ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                '}';
    }
}
