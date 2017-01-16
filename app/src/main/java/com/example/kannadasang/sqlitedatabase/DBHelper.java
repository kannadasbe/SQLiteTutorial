package com.example.kannadasang.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KannadasanG on 1/16/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "EmployeesManager";

    // Employees table name
    private static final String TABLE_EMPLOYEE = "Employees";

    // Employees Table Columns Names
    private static final String EMPLOYEE_ID = "EmployeeId";
    private static final String EMPLOYEE_NAME = "EmployeeName";
    private static final String EMPLOYEE_DESIGNATION = "Designation";
    private static final String EMPLOYEE_YEAR_OF_JOINING = "YearOfJoining";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //All create table statements should be placed here
        String CREATE_EMPLOYEES_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
                + EMPLOYEE_ID + " INTEGER PRIMARY KEY,"
                + EMPLOYEE_NAME + " TEXT,"
                + EMPLOYEE_DESIGNATION + " TEXT,"
                + EMPLOYEE_YEAR_OF_JOINING + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_EMPLOYEES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //All modifications to table and database should be done here
        //like modifying table structure adding constrains etc.
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Adding new employee
    public void addEmployee(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //Setting content values
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_NAME, employee.getName()); // Employee Name
        contentValues.put(EMPLOYEE_DESIGNATION, employee.getDesignation()); // Employee Designation
        contentValues.put(EMPLOYEE_YEAR_OF_JOINING,employee.getYear());//Employee Year Of Joining
        // Inserting employee record into table
        sqLiteDatabase.insert(TABLE_EMPLOYEE, null, contentValues);
        // Closing database connection
        sqLiteDatabase.close();
    }

    // Getting employee details by employee id
    public Employee getEmployeeDetails(int employeeId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res =  sqLiteDatabase.rawQuery( "SELECT * FROM "+TABLE_EMPLOYEE+" WHERE "+EMPLOYEE_ID+"="+employeeId, null );
        if (res != null)
            res.moveToFirst();

        //Fill employee object
        Employee employee = new Employee(res.getInt(0),res.getString(1), res.getString(2),res.getString(3));
        // return employee
        return employee;
    }

    // Getting all employee details
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        // Reading all records from employees table
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res =  sqLiteDatabase.rawQuery( "SELECT * FROM "+TABLE_EMPLOYEE, null );
        res.moveToFirst();

        // Adding records to employeelist
        while(res.isAfterLast() == false){
            Employee employee = new Employee(res.getInt(0),res.getString(1), res.getString(2),res.getString(3));
            employeeList.add(employee);
            res.moveToNext();
        }
        return employeeList;
    }

    // Getting employees Count
    public int getEmployeesCount() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res =  sqLiteDatabase.rawQuery( "SELECT * FROM "+TABLE_EMPLOYEE, null );
        res.close();
        return res.getCount();
    }

    // Updating employee details
    public void updateEmployee(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //Setting content values
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_NAME, employee.getName()); // Employee Name
        contentValues.put(EMPLOYEE_DESIGNATION, employee.getDesignation()); // Employee Designation
        contentValues.put(EMPLOYEE_YEAR_OF_JOINING,employee.getYear());//Employee Year Of Joining
        sqLiteDatabase.update(TABLE_EMPLOYEE, contentValues,EMPLOYEE_ID+" = ? ", new String[] { Integer.toString(employee.getEmployeeId()) } );
    }

    // Deleting employee by id
    public void deleteEmployee(int employeeId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_EMPLOYEE, EMPLOYEE_ID + " = ?",new String[] { Integer.toString(employeeId) });
        sqLiteDatabase.close();
    }
}
