package com.example.mahmo.retrofit.Interface;

import com.example.mahmo.retrofit.Model.Employee;
import com.example.mahmo.retrofit.Model.LoginModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by mahmo on 2/22/2018.
 */

public interface API {

    String url = "http://192.168.1.111" ;
    @GET("/androidapp/api/values")
    Call<ArrayList<Employee>> getEmployees() ;
    @GET("/androidapp/api/values/{Id}")
    Call<Employee> getEmploye(@Path("Id") int id) ;
    @POST("/androidapp/api/values")
    Call<Employee> sendData(@Body Employee e) ;
    @PUT("/androidapp/api/values/{Id}")
    Call<Employee> updateEmployee(@Path("Id")int id ,@Body Employee e);
    @DELETE("/androidapp/api/values/{Id}")
    Call<Employee> deleteEmployee(@Path("Id")int id) ;
    @Multipart
    @POST("/androidapp/api/values/files")
    Call<ResponseBody> uploadPhoto(@Part MultipartBody.Part image , @Part("name") okhttp3.RequestBody name) ;
    @POST("/androidapp/api/login/register")
    Call<LoginModel> register(@Body LoginModel l) ;
    @GET("/androidapp/api/login/register")
    Call<String> getRegister() ;
    @POST("/androidapp/api/login/login2")
    Call<ResponseBody> login(@Body LoginModel l) ;
    @GET("/androidapp/api/login/list")
    Call<ArrayList<LoginModel>> getList(@Header("Authorization") String token ) ;





}
