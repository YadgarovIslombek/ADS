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
    EditText full_name, password,mobileNumber;
    User user;
    LocalStorage localStorage;
    Gson gson = new Gson();
    EditText name, mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.Name);
        mobile = findViewById(R.id.mobile);
        findViewById(R.id.btnsignup).setOnClickListener(this);
        findViewById(R.id.text_v_login).setOnClickListener(this);
    }
    private void userSignUp() {
        String getusername = name.getText().toString().trim() + "";
        String getphone = mobile.getText().toString().trim() + "";
        if (getusername.length() == 0) {
            name.setError("Ism Sharifingizni kiriting");
            name.requestFocus();
        } else if (getphone.length() == 0) {
            mobile.setError("Telefon raqam kiriting");
            mobile.requestFocus();
        } else {
            user = new User(getusername, getphone);
//            Map<String, String> fields = new HashMap<>();
//            fields.put("userName", getusername.toString().trim());
//            fields.put("phoneNumber", getphone.trim());

            Call<ClientResponse> call = RetrofitClient
                    .getData(getApplicationContext())
                    .createUser(getusername, "12345678", getphone);
            call.enqueue(new Callback<ClientResponse>() {
                @Override
                public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                    if (response.code() == 200) {
                        ClientResponse clientResponse = response.body();
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
                Intent intent1 = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent1);
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




