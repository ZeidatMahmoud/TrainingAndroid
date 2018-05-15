package com.example.mahmo.retrofit.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Display extends AppCompatActivity {
    private TextView display ;
    private int id ;
    private String strId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        display = (TextView)findViewById(R.id.id) ;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create()) ;


        Retrofit retrofit= builder.build();
        final API service  = retrofit.create(API.class) ;

        SharedPreferences sharedPreferences = getSharedPreferences("employee", Context.MODE_PRIVATE);
        strId =   sharedPreferences.getString("id","no vlaue") ;
        if(strId.equals("no value")){
            Toast.makeText(Display.this,"dont get the id",Toast.LENGTH_SHORT).show();
        }
        else{
            id =Integer.parseInt(strId) ;
            Call<Employee> call = service.getEmploye(id);
            call.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    Employee e = response.body() ;
                    display.setText(e.toString());
                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {

                }
            });

        }


    }
}
