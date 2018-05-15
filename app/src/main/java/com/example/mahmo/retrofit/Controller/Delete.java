package com.example.mahmo.retrofit.Controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmo.retrofit.Interface.API;
import com.example.mahmo.retrofit.Model.Employee;
import com.example.mahmo.retrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Delete extends AppCompatActivity {
    private EditText text  ;
    private Button delete ;
    private String strtext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        text = (EditText)findViewById(R.id.delete) ;
        delete = (Button)findViewById(R.id.delbutton) ;


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create()) ;


        Retrofit retrofit= builder.build();
        final API service  = retrofit.create(API.class) ;

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtext  =text.getText().toString() ;
                Call<Employee>call = service.deleteEmployee(Integer.parseInt(strtext)) ;
                call.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if(response.body().equals("Done")) {
                            Toast.makeText(Delete.this, "Done ", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Delete.this, "this element is not found", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(Delete.this ,"not Done ",Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });

    }
}
