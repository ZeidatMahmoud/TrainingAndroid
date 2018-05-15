package com.example.mahmo.retrofit.Veiw;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmo.retrofit.DataBase.Database;
import com.example.mahmo.retrofit.Interface.API;
import com.example.mahmo.retrofit.Model.Employee;
import com.example.mahmo.retrofit.R;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayALL extends AppCompatActivity {
   // private Database mydb ;
    private TextView display ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);
        display = (TextView)findViewById(R.id.text);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create()) ;


        Retrofit retrofit= builder.build();

        API service  = retrofit.create(API.class) ;
     //   Call<List<Employee>> cal = service.getEmployees() ;


//                if(list.size() == 0){
                 //  Toast.makeText(DisplayALL.this,"empty",Toast.LENGTH_LONG).show();
//                }
//                else {
////                    for (int i = 0; i < list.size(); i++) {
////                        display.append(list.get(i).toString());
////                        display.append("\n");
////                    }
//                }
            }







    }

