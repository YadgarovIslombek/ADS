package com.A1tech.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.A1tech.ADS.R;
import com.A1tech.ApiClient.RetrofitClient;
import com.A1tech.Model.User;
import com.A1tech.Model.ClientResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt1, edt2;
    Button btnreg;
    TextView SignUptxt;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt1 = findViewById(R.id.mobileLog);
        btnreg = findViewById(R.id.btnreg);
        SignUptxt = findViewById(R.id.createAccount);
        btnreg.setOnClickListener(this);
        SignUptxt.findViewById(R.id.createAccount).setOnClickListener(this);


    }
    private void userLogin() {
        String getphone = edt1.getText().toString().trim();
        if (getphone.length() == 0) {
            edt1.setError("Telefon raqam kiriting");
            edt1.requestFocus();

        } else if ( edt2.length() < 6) {
                    edt2.setError("Parolingiz 6 xonadan kam");
                    edt2.requestFocus();
        } else {
            user = new User(getphone);

        }
        Call<ClientResponse> call = RetrofitClient.getData(getApplicationContext()).loginUser("g12345678", getphone);
        call.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                Toast.makeText(LoginActivity.this, response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnreg:
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.createAccount:
                Intent intent1 = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent1);
                break;
        }
    }

}