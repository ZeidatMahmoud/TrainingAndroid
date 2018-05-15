package com.example.mahmo.retrofit.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mahmo.retrofit.Interface.API;
import com.example.mahmo.retrofit.Model.LoginModel;
import com.example.mahmo.retrofit.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private EditText name ,password ;
    private Button login , register ;
    private String strname ,strpassword ;
    private LoginModel log ;
    private ProgressBar bar ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText)findViewById(R.id.name) ;
        password = (EditText)findViewById(R.id.password) ;
        login = (Button)findViewById(R.id.login) ;
        register = (Button)findViewById(R.id.register) ;
        bar = (ProgressBar)findViewById(R.id.prog) ;
        bar.setVisibility(View.INVISIBLE);


        Retrofit.Builder builder = new Retrofit.Builder()// retrofit
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create()) ;

        Retrofit retrofit= builder.build();
        final API service  = retrofit.create(API.class) ;

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // register on click // saave to database

                strname = name.getText().toString() ;
                strpassword = password.getText().toString() ;
                if(strname!= null & strpassword != null) {
                    LoginModel log = new LoginModel(strname, strpassword);
                    Call<LoginModel> cal = service.register(log);
                    cal.enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) { //sucsses
                            Toast.makeText(Login.this, "Registerd successfully", Toast.LENGTH_SHORT).show();}

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) { //failer
                            Toast.makeText(Login.this, "try again", Toast.LENGTH_SHORT).show();}
                    });
                    SharedPreferences sharedPreferences = getSharedPreferences("welcome", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", strname);
                    editor.commit();
                }
                else{
                    Toast.makeText(Login.this,"Please Enter Vaild User Name And Password",Toast.LENGTH_LONG).show();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("") && password.getText().toString().equals("")){
                    Toast.makeText(Login.this ,"Enter a vaild User Name or Password",Toast.LENGTH_LONG).show();
                }
                else {

                    strname = name.getText().toString();
                    strpassword = password.getText().toString();
                    log = new LoginModel(strname, strpassword);

                    Call<ResponseBody> cal = service.login(log);
                    bar.setVisibility(View.VISIBLE);

                    cal.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {// async
                            if(response.code() == 400 ){
                                Toast.makeText(Login.this,"not found",Toast.LENGTH_LONG).show();
                                bar.setVisibility(View.INVISIBLE); //set the bar on
                            }
                            else{
                                SharedPreferences sharedPreferences = getSharedPreferences("welcome", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                try {
                                    editor.putString("token", response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                editor.commit();
                                bar.setVisibility(View.INVISIBLE); // set the bar off

                                Intent i = new Intent(Login.this, Choose.class);
                                startActivity(i);
                            }
                            }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Login.this,"try again",Toast.LENGTH_LONG).show();

                        }

                    });
                }

            }
        });

    }
}
