package com.example.mahmo.retrofit.Model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mahmo.retrofit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmo on 4/3/2018.
 */

public class CustomTow extends ArrayAdapter<Employee>{
    private Context context ;
    private List<LoginModel> list  ;
    private int resourse ;




    public CustomTow(@NonNull android.content.Context context, @LayoutRes int resource,@NonNull ArrayList<Employee> objects) {
        super(context, resource,objects);
        this.context =context ;
        this.resourse = resource ;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the employee informations
        int id =  getItem(position).getId() ;
        String Name = getItem(position).getFirstName() ;
        String phone = getItem(position).getPhone() ;

//         ;Employee e = new Employee(id,Name)
        Employee e= new Employee(id,Name);
        LayoutInflater inflater = LayoutInflater.from(context) ;
        convertView = inflater.inflate(resourse,parent,false) ;
        TextView tt1 = (TextView)convertView.findViewById(R.id.id);
        TextView tt2 = (TextView) convertView.findViewById(R.id.categoryId);
        //TextView tt3 = (TextView) convertView.findViewById(R.id.password);
        tt1.append(""+id);
        tt2.append(e.getFirstName());
        //tt3.append(e.getLastName());

        return convertView ;

    }
}
