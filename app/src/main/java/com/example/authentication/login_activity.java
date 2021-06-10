package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.authentication.NetWorKing.ApiServices;
import com.example.authentication.NetWorKing.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class login_activity extends Fragment {
    EditText edtUserName, edtPassWord;
    Button btn_login;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.login_activity, null);
        //Find Id Text
        edtUserName = mView.findViewById(R.id.signin_id);
        edtPassWord = mView.findViewById(R.id.signin_password);
        btn_login = mView.findViewById(R.id.signinBtn); //Tim category recycler view*/
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = edtUserName.getText().toString();
                String passWord = edtPassWord.getText().toString();
                AccountModel accountModel = new AccountModel();
                accountModel.setPhoneNumber(userName);
                accountModel.setPassword(passWord);
                Retrofit retrofit = RetrofitClient.getRetrofitInstance();
                ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
                Call<AccountModel> call = jsonPlaceHolderApi.login(accountModel);
                call.enqueue(new Callback<AccountModel>() {
                    @Override
                    public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                        if (response.isSuccessful()) {
                            AccountModel accountModel1 = response.body();
                            String message = accountModel1.getMessage();
                            String tk = accountModel1.getAccessToken();
                            Log.d("Token Login", tk);
                            Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
                            intent.putExtra("message", message);
                            intent.putExtra("Token", tk);
                            getActivity().startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Sai tên tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AccountModel> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return mView;
    }
}
