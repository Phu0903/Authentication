package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.authentication.NetWorKing.ApiServices;
import com.example.authentication.NetWorKing.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class user_activity extends Fragment {
    private TextView textViewResult;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.user_activity, null);
        textViewResult = mView.findViewById(R.id.text_view_result);
        Intent intent = getActivity().getIntent();
        String token = intent.getStringExtra("Token");

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        Call<UserModel> call = jsonPlaceHolderApi.getInforUser("Bearer " +token);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Token " + response.body());
                    return;
                }
                UserModel posts = response.body();

                    String content = "";
                    content += "FullName: " + posts.getFullName() + "\n";
                    content += "Address: " + posts.getAddress() + "\n";
                    content += "PhoneNumber: " + posts.getPhoneNumber() + "\n";

                    textViewResult.append(content);

            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        return mView;

    }
}
