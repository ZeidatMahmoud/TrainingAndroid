package com.example.mahmo.retrofit.Model;

/**
 * Created by mahmo on 3/18/2018.
 */

public class LoginModel {
    String name ,password;
    int  id ;

    public LoginModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginModel(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
