package com.example.kannadasang.sqlitedatabase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KannadasanG on 1/16/2017.
 */

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder> {
    List<Employee> employees = new ArrayList<>();
    Context thisContext;

    public EmployeesAdapter(Context context, List<Employee> employees) {
        this.employees = employees;
        this.thisContext = context;
    }

    @Override
    public EmployeesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeesViewHolder(view);
    }

    public class EmployeesViewHolder extends RecyclerView.ViewHolder {
        TextView empName, empDesignation, empYear, empId;
        ImageView imgEdit, imgDelete;
        View divider;

        public EmployeesViewHolder(View ItemView) {
            super(ItemView);
            empId = (TextView) ItemView.findViewById(R.id.txtEmpId);
            empName = (TextView) ItemView.findViewById(R.id.txtEmpName);
            empDesignation = (TextView) ItemView.findViewById(R.id.txtEmpDesignation);
            empYear = (TextView) ItemView.findViewById(R.id.txtEmpYear);
            divider = (View) ItemView.findViewById(R.id.divider);
            imgEdit = (ImageView) ItemView.findViewById(R.id.imgEdit);
            imgDelete = (ImageView) ItemView.findViewById(R.id.imgDelete);
        }
    }

    @Override
    public void onBindViewHolder(EmployeesViewHolder holder, int position) {
        final Employee emp = employees.get(position);
        holder.empId.setText("Employee Id : ".concat(Integer.toString(emp.getEmployeeId())));
        holder.empName.setText("Employee Name : ".concat(emp.getName()));
        holder.empDesignation.setText("Designation : ".concat(emp.getDesignation()));
        holder.empYear.setText("Joining Year : ".concat(emp.getYear()));

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(thisContext instanceof EmployeesActivity){
                    ((EmployeesActivity)thisContext).editEmployeeDetails(emp.getEmployeeId());
                }
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(thisContext);
                dbHelper.deleteEmployee(emp.getEmployeeId());
                if(thisContext instanceof EmployeesActivity){
                    ((EmployeesActivity)thisContext).updateRecyclerView();
                }
            }
        });

        if (position + 1 == getItemCount()) //check if this is the last child, if yes then hide the divider
            holder.divider.setVisibility(View.GONE);
    }



    @Override
    public int getItemCount() {
        return employees.size();
    }
}