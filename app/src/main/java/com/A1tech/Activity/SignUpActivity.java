package com.A1tech.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.A1tech.ADS.R;
import com.A1tech.ApiClient.RetrofitClient;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.User;
import com.A1tech.Model.ClientResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    User user;
    LocalStorage localStorage;
    Gson gson = new Gson();
    EditText name, mobile, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.Name);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        findViewById(R.id.btnsignup).setOnClickListener(this);
        findViewById(R.id.text_v_login).setOnClickListener(this);
    }
    private void userSignUp() {
        String getusername = name.getText().toString().trim() + "";
        String getphone = mobile.getText().toString().trim() + "";
        String getpassword = password.getText().toString().trim() + "";
        if (getusername.length() == 0) {
            name.setError("Ism Sharifingizni kiriting");
            name.requestFocus();
        } else if (getphone.length() == 0) {
            mobile.setError("Telefon raqam kiriting");
            mobile.requestFocus();
        }else if(getpassword.length() > 6){
            password.setError("Parol kamida 6 belgili bo'lishi shart");
            password.requestFocus();
        }
        else {
            user = new User(getusername,getpassword, getphone);
//            Map<String, String> fields = new HashMap<>();
//            fields.put("userName", getusername.toString().trim());
//            fields.put("phoneNumber", getphone.trim());

            Call<ClientResponse> call = RetrofitClient
                    .getData(getApplicationContext())
                    .createUser(getusername,getpassword,getphone);
            call.enqueue(new Callback<ClientResponse>() {
                @Override
                public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                    if (response.code() == 200) {
                        ClientResponse clientResponse = response.body();
                        String userString = gson.toJson(clientResponse.getUser());
                        localStorage.createUserLoginSession(userString);
                        Toast.makeText(getApplicationContext(), clientResponse.getStatus(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        Toast.makeText(SignUpActivity.this, clientResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (response.code() == 401) {
                        Toast.makeText(SignUpActivity.this, "Avval ro'yhatdan o'tgansiz", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SignUpActivity.this, "Telefon raqam hato kiritildi", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ClientResponse> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "Ulanib bolmadi", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnsignup:
                userSignUp();


                finish();
                break;
            case R.id.text_v_login:
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
                break;
        }
    }


//    private void registerUser(Client client) {
//        Call<ClientResponse> call = RetrofitClient
//                .getData(getApplicationContext())
//                .createUser("Islombek", "1234567891011","1234567891011");
//        call.enqueue(new Callback<ClientResponse>() {
//            @Override
//            public void onResponse(Call<ClientResponse> call, @NotNull Response<ClientResponse> response) {
//                ClientResponse clientResponse = response.body();
//                if (clientResponse.getStatus() == 200) {
//                    Toast.makeText(SignUpActivity.this, clientResponse.getStatus(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(SignUpActivity.this, clientResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//               @Override
//            public void onFailure(Call<ClientResponse> call, Throwable t) {
//                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
    }




