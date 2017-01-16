package com.example.kannadasang.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class EmployeesActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List<Employee> employees = new ArrayList<>();
    EmployeesAdapter empAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        getEmployeesList();
        recyclerView = (RecyclerView) findViewById(R.id.empRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        empAdapter = new EmployeesAdapter(this, employees);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(empAdapter);
    }

    private void getEmployeesList() {
        dbHelper = new DBHelper(this);
        employees = dbHelper.getAllEmployees();
    }

    public void updateRecyclerView() {
        dbHelper = new DBHelper(this);
        employees = dbHelper.getAllEmployees();
        empAdapter = new EmployeesAdapter(this, employees);
        recyclerView.setAdapter(empAdapter);
        empAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateRecyclerView();
    }

    public void editEmployeeDetails(int employeeId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Config.EMPLOYEE_ID, employeeId);
        startActivityForResult(intent,Config.EDIT_EMPLOYEE);
    }
}
