package com.example.mahmo.retrofit.Controller;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mahmo.retrofit.Model.Employee;
import com.example.mahmo.retrofit.Model.LoginModel;
import com.example.mahmo.retrofit.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mahmo on 3/6/2018.
 */

public class CustomAdapter extends ArrayAdapter<LoginModel>{
    private Context context ;
    private List<LoginModel> list  ;
    private int resourse ;


    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<LoginModel> objects) {
        super(context, resource, objects);
        this.context =context ;
        this.resourse = resource ;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the employee informations
        int id =  getItem(position).getId() ;
        String Name = getItem(position).getName() ;
        String phone = getItem(position).getPassword() ;

//         ;Employee e = new Employee(id,Name)
        LoginModel log  = new LoginModel(Name, phone,id) ;
        LayoutInflater inflater = LayoutInflater.from(context) ;
        convertView = inflater.inflate(resourse,parent,false) ;
        TextView tt1 = (TextView)convertView.findViewById(R.id.id);
        TextView tt2 = (TextView) convertView.findViewById(R.id.categoryId);
        TextView tt3 = (TextView) convertView.findViewById(R.id.password);
        tt1.append(""+id);
        tt2.append(log.getName());
        tt3.append(log.getPassword());

        return convertView ;

    }
}
