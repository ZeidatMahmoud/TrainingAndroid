package com.example.mahmo.retrofit.Controller;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.mahmo.retrofit.DataBase.Database;
import com.example.mahmo.retrofit.Interface.API;
import com.example.mahmo.retrofit.Model.Employee;
import com.example.mahmo.retrofit.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Insertion extends AppCompatActivity {
    private EditText firstName ,lastName ,phone ,salary ;
    private Button send ;
    private Employee employee ;
    private String strfirstname , strlastname , strphone ,strsalary ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion);
        firstName = (EditText)findViewById(R.id.firstname) ;
        lastName = (EditText)findViewById(R.id.lastname) ;
        phone = (EditText)findViewById(R.id.phone);
        salary  = (EditText)findViewById(R.id.salary) ;
        send = (Button)findViewById(R.id.senddata);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create()) ;


        Retrofit retrofit= builder.build();
        final API service  = retrofit.create(API.class) ;


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strfirstname = firstName.getText().toString() ;
                strlastname = lastName.getText().toString() ;
                strphone = phone.getText().toString() ;
                strsalary = salary.getText().toString() ;
                employee = new Employee(strfirstname,strlastname,Integer.parseInt(strsalary),strphone) ;
                Call<Employee>call = service.sendData(employee);
                call.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        Toast.makeText(Insertion.this,"Inserted",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(Insertion.this,"there is a problem",Toast.LENGTH_SHORT).show();
                    }
                });
                firstName.setText("");
                lastName.setText("");
                phone.setText("");
                salary.setText("");


            }
        });
    }
}
