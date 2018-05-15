package com.example.mahmo.retrofit.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmo.retrofit.Interface.API;
import com.example.mahmo.retrofit.Model.Employee;
import com.example.mahmo.retrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Update extends AppCompatActivity {
    private EditText firstName ,lastName , phone ,salary ,id;
    private Button update ;
    private String strFirstName ,strLast ,strphone,strsalary ,strid ;
    private Employee employee ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        firstName = (EditText)findViewById(R.id.firstname) ;
        lastName = (EditText)findViewById(R.id.lastname) ;
        phone = (EditText)findViewById(R.id.phone);
        salary  = (EditText)findViewById(R.id.salary) ;
        update = (Button)findViewById(R.id.senddata);
        id = (EditText)findViewById(R.id.id);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create()) ;


        Retrofit retrofit= builder.build();
        final API service  = retrofit.create(API.class) ;

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strid = id.getText().toString() ;
                strFirstName = firstName.getText().toString() ;
                strLast = lastName.getText().toString() ;
                strphone = phone.getText().toString() ;
                strsalary = salary.getText().toString() ;

                employee = new Employee(strFirstName,strLast,Integer.parseInt(strsalary),strphone) ;


                Call<Employee> call = service.updateEmployee(Integer.parseInt(strid),employee);
                call.enqueue(new Callback<Employee>() {
                    @Override
                        public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if(response.body().equals("Done")) {
                            Toast.makeText(Update.this, "Updated", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Update.this, "Updated", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(Update.this,"there is a problem",Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


    }
}
