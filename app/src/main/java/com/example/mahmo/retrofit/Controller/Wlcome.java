package com.example.mahmo.retrofit.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mahmo.retrofit.R;

public class Wlcome extends AppCompatActivity {
    private TextView name ;
    private Button start ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlcome);
        name = (TextView)findViewById(R.id.text) ;
        start = (Button)findViewById(R.id.start) ;

        final SharedPreferences sharedPreferences = getSharedPreferences("welcome", Context.MODE_PRIVATE) ;
        String strname = sharedPreferences.getString("token","no value") ;
        String str =strname.split(":")[1] ;
        final String  str2 = str.split("\"")[1];

        name.setText(str2);



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences1 = getSharedPreferences("list",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit() ;
                editor.putString("token",str2) ;
                editor.commit();
                Intent i = new Intent(Wlcome.this ,MainActivity.class) ;
                finish();
                startActivity(i);
            }
        });




    }
}
