package com.example.kannadasang.sqlitedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int employeeId=0,requestCode=0;
    Button btnSaveUpdate, btnViewEmployees;
    TextView txtEmployeeId;
    EditText etEmployeeName, etEmployeeDesignation, etEmployeeYearOfJoining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSaveUpdate = (Button) findViewById(R.id.btnSaveUpdate);
        btnViewEmployees = (Button) findViewById(R.id.btnViewEmployees);
        txtEmployeeId = (TextView) findViewById(R.id.txtEmployeeId);
        etEmployeeName = (EditText) findViewById(R.id.etEmployeeName);
        etEmployeeDesignation = (EditText) findViewById(R.id.etEmployeeDesignation);
        etEmployeeYearOfJoining = (EditText) findViewById(R.id.etEmployeeYearOfJoining);

        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                Employee employee = new Employee();

                employee.setName(etEmployeeName.getText().toString());
                employee.setDesignation(etEmployeeDesignation.getText().toString());
                employee.setYear(etEmployeeYearOfJoining.getText().toString());

                // For edit mode we will get employeeId. Use it for updating and finish the activity
                if(employeeId>0)
                {
                    employee.setEmployeeId(employeeId);
                    dbHelper.updateEmployee(employee);
                    setResult(RESULT_OK);
                    finish();
                }
                else {
                    dbHelper.addEmployee(employee);
                    clearFields();
                    Toast.makeText(getApplicationContext(), "Employee added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnViewEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EmployeesActivity.class));
            }
        });

        // For Editing

        btnSaveUpdate.setText("SAVE");
        txtEmployeeId.setText("Add New Employee");
        if (getIntent().getExtras() != null) {

            // Edit mode update button text
            btnSaveUpdate.setText("UPDATE");
            employeeId = getIntent().getExtras().getInt(Config.EMPLOYEE_ID);
            requestCode = getIntent().getExtras().getInt("RequestCode");
            Employee employee = new Employee();
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            employee = dbHelper.getEmployeeDetails(employeeId);
            txtEmployeeId.setText("EmployeeId :".concat(String.valueOf(employee.getEmployeeId())));
            etEmployeeName.setText(employee.getName());
            etEmployeeDesignation.setText(employee.getDesignation());
            etEmployeeYearOfJoining.setText(employee.getYear());
        }

    }

    private void clearFields() {
        etEmployeeName.setText("");
        etEmployeeDesignation.setText("");
        etEmployeeYearOfJoining.setText("");
    }
}
