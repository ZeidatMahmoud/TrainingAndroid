package com.example.mahmo.retrofit.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mahmo.retrofit.DataBase.Database;
import com.example.mahmo.retrofit.Interface.API;
import com.example.mahmo.retrofit.Model.CustomTow;
import com.example.mahmo.retrofit.Model.Employee;
import com.example.mahmo.retrofit.Model.LoginModel;
import com.example.mahmo.retrofit.Model.Users;
import com.example.mahmo.retrofit.R;
import com.example.mahmo.retrofit.Veiw.DisplayALL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    private ListView listView  ;
    private Button add ,refresh,update ,delete;
    private CustomTow adapter  ;
    private ArrayList<Employee> employees = new ArrayList<>() ;
    private String token  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_controller);
        listView = (ListView)findViewById(R.id.list) ;
        add = (Button)findViewById(R.id.insert) ;
        refresh = (Button)findViewById(R.id.update) ;
        update = (Button)findViewById(R.id.updateC) ;
        delete = (Button)findViewById(R.id.del) ;

        //retrofit library initialize
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create()) ;


        Retrofit retrofit= builder.build();
        final API service  = retrofit.create(API.class) ;


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ArrayList<Employee>>  call = service.getEmployees() ;
                call.enqueue(new Callback<ArrayList<Employee>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
                        employees =response.body() ;
                        adapter = new CustomTow(getApplicationContext(),R.layout.itemlistraw,employees);
                        listView.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"there is a problem",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,Insertion.class);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id1 = employees.get(position).getId() ;
                SharedPreferences sharedPreferences = getSharedPreferences("employee", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("id",""+id1) ;
                editor.commit() ;
                Intent i = new Intent(MainActivity.this , com.example.mahmo.retrofit.Controller.Display.class) ;
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Update.class) ;
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,Delete.class) ;
                startActivity(i);
            }
        });

    }
}
