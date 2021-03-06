package com.A1tech.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import com.A1tech.ADS.R;
import com.A1tech.Activity.LoginRegister;
import com.A1tech.Activity.MainActivity;
import com.A1tech.ApiClient.RetrofitClient;
import com.A1tech.Helper.CustomToast;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Helper.Utils;
import com.A1tech.Model.ClientResponse;
import com.A1tech.Model.Client;
import com.google.gson.Gson;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpFragment extends Fragment implements View.OnClickListener {
    private  View view;
    private  EditText fullName,
            password;
    private  TextView login;
    private  Button signUpButton;
    private  CheckBox terms_conditions;
    Client client;
    LocalStorage localStorage;
    Gson gson = new Gson();
    View progress;
    EditText mobileNumber;
//    MaskedEditText mobileNumber;
    public SignUpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        localStorage = new LocalStorage(getContext());
        initViews();
        setListeners();
        return view;
    }
    // Initialize all views
    private void initViews() {
        fullName = view.findViewById(R.id.Name);
        progress = view.findViewById(R.id.progress_bar);
//        emailId = view.findViewById(R.id.userEmailId);
//        mobileNumber = (MaskedEditText) view.findViewById(R.id.phone);
        mobileNumber = view.findViewById(R.id.phone);
        password = view.findViewById(R.id.password);

        signUpButton = view.findViewById(R.id.btnsignup);
        login = view.findViewById(R.id.already_user);
        //terms_conditions = view.findViewById(R.id.terms_conditions);

        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            login.setTextColor(csl);
            // terms_conditions.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsignup:
                // Call checkValidation method
                checkValidation();
                break;

            case R.id.already_user:
                // Replace login fragment
                new LoginRegister().replaceLoginFragment();
                break;
        }
    }
// Check Validation Method
        private void checkValidation () {
            // Get all edittext texts
            String getFullName = fullName.getText().toString();
//        String getEmailId = emailId.getText().toString();
            String getMobileNumber = mobileNumber.getText().toString();
            String getPassword = password.getText().toString();
            // Pattern match for email id
            Pattern p = Pattern.compile(Utils.regEx);


            if (getFullName.length() == 0) {
                fullName.setError("Ismingizni kiriting");
                fullName.requestFocus();
            }/* else if (getEmailId.length() == 0) {
            emailId.setError("Eneter Your Email");
            emailId.requestFocus();
        } else if (!m.find()) {
            emailId.setError("Eneter Correct Email");
            emailId.requestFocus();
        }*/ else if (getMobileNumber.length() == 0) {
                mobileNumber.setError("Telefon raqamingizni kiriting");
                mobileNumber.requestFocus();
            } else if (getPassword.length() == 0) {
                password.setError("Parolingizni kiriting");
                password.requestFocus();
            } else if (getPassword.length() < 6) {
                password.setError("Parol 6 ta belgidan kam");
                password.requestFocus();
            }  else {
                client = new Client(getFullName,getPassword, getMobileNumber);
                registerUser(getFullName,getPassword,getMobileNumber);



            /*  gson = new Gson();
            String userString = gson.toJson(user);
            localStorage.createUserLoginSession(userString);
            progressDialog.setMessage("Registering Data....");
            progressDialog.show();
            Handler mHand = new Handler();
            mHand.postDelayed(new Runnable() {

                @Override
                public void run() {
                    progressDialog.dismiss();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                    getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            }, 5000);*/
            }

        }
    private void registerUser(String getFullName,String getPassword, String getMobileNumber) {
        showProgressDialog();
        Call<ClientResponse> call = RetrofitClient.getData(getContext()).createUser(getFullName,getPassword,getMobileNumber);
        call.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    ClientResponse clientResponse = response.body();
                    if (clientResponse.getStatus() == 200) {
                        String userString = gson.toJson(clientResponse.getClient());
                        localStorage.createUserLoginSession(userString);
                        Log.d("TAG",userString);
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    } else {
                        new CustomToast().Show_Toast(getActivity(), view,
                                String.valueOf(clientResponse.getStatus()));

                    }

                } else {
                    new CustomToast().Show_Toast(getActivity(), view,
                            "Telefon raqam va parolingizni kiriting");
                }

                hideProgressDialog();

            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());
                hideProgressDialog();
            }
        });

    }

    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }


}
