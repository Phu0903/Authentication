package com.example.authentication.NetWorKing;


import com.example.authentication.AccountModel;
import com.example.authentication.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {


// Thông tin user
    @GET("user/getInForUserByTokenId")
    Call<UserModel> getInforUser(@Header("Authorization") String authHeader);

//    https://smai-back-end.herokuapp.com/account/login
    @POST("account/login")
    Call<AccountModel> login(@Body AccountModel accountModel);



}
