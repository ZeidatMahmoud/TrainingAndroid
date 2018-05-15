package com.example.mahmo.retrofit.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mahmo.retrofit.Model.Employee;

/**
 * Created by mahmo on 2/22/2018.
 */

public class Database extends SQLiteOpenHelper {
    private final static String Table = "Employee" ;
    private final static String firstName = "firstName" ;
    private final static String ID = "ID" ;
    private final static String lastName = "lastName" ;
    private final static String phone = "phone" ;
    private final static  String salary = "salary" ;
    private final static String query = "CREATE TABLE "+ Table+ " ("+ID+" INTEGER  , "+ firstName +" TEXT, "+ lastName +" TEXT,"+ salary +" TEXT ,"+ phone +" TEXT);";
    public Database(Context context) {
        super(context, "retro.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+Table ;
        db.execSQL(sql);
        onCreate(db);

    }


    public boolean insertData(Employee e){
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        contentValues.put(firstName,e.getFirstName());
        contentValues.put(lastName,e.getLastName());
        contentValues.put(phone,e.getPhone());
        contentValues.put(salary,""+ e.getSalary());

        long result =db.insertOrThrow(Table,"",contentValues);
        if(result == -1){
            return false;
        }
        return true ;
    }
    public Cursor getAllData(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from "+Table,null);
        return res ;
    }
    public void freeTable(){
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "DELETE FROM "+Table;
        db.execSQL(query);
    }
}
