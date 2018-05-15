package com.example.mahmo.retrofit.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mahmo.retrofit.Interface.API;
import com.example.mahmo.retrofit.Model.LoginModel;
import com.example.mahmo.retrofit.R;
import com.squareup.okhttp.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListAuth extends AppCompatActivity {
    private ListView listview ;
    private CustomAdapter adapter ;
    private String token ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_auth);
        listview = (ListView)findViewById(R.id.authList) ;

        SharedPreferences sharedPreferences = getSharedPreferences("welcome", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token","no vlaue") ;
        token  = token.split("\"")[3] ;// split x ="token" -> get token


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create()) ;


        Retrofit retrofit= builder.build();

        final API service  = retrofit.create(API.class) ;

        Call<ArrayList<LoginModel>> cal = service.getList("bearer ".concat(token)) ; // send token as heder
        cal.enqueue(new Callback<ArrayList<LoginModel>>() {
            @Override
            public void onResponse(Call<ArrayList<LoginModel>> call, retrofit2.Response<ArrayList<LoginModel>> response) {
                ArrayList list = response.body() ;
                adapter = new CustomAdapter(getApplicationContext(),R.layout.itemlistraw,list);
                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<LoginModel>> call, Throwable t) {
                Toast.makeText(ListAuth.this,"unauthorized",Toast.LENGTH_SHORT).show();

            }
        });



    }
}
