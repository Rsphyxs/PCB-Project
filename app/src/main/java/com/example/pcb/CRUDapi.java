package com.example.pcb;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CRUDapi {

    @FormUrlEncoded
    @POST("add/")
    Call<User> createUser(@Field("username") String username, @Field("email") String email, @Field("password") String password );

    @FormUrlEncoded
    @POST("verif/")
    Call<Verif> verifSignup(@Field("username") String username, @Field("email") String email);

    @FormUrlEncoded
    @POST("login/")
    Call<Verif> verifSignin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("username/")
    Call<List<User>> fetchUsername(@Field("email") String email);

    @POST("cpu/")
    Call<List<CPU>> fetchCPU();
}
