package com.example.kannadasang.sqlitedatabase;

/**
 * Created by KannadasanG on 1/16/2017.
 */

public class Employee {
    int EmployeeId;
    String EmployeeName,Designation,YearOfJoining;

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getName() {
        return EmployeeName;
    }

    public void setName(String name) {
        EmployeeName = name;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getYear() {
        return YearOfJoining;
    }

    public void setYear(String year) {
        YearOfJoining = year;
    }
    public Employee(){}
    public Employee(int employeeId, String name, String designation, String year) {
        EmployeeId = employeeId;
        EmployeeName = name;
        Designation = designation;
        YearOfJoining = year;
    }
}
