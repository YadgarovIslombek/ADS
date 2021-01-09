package com.A1tech.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Vibrator;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.A1tech.ADS.R;
import com.A1tech.Activity.MainActivity;
import com.A1tech.ApiClient.RetrofitClient;
import com.A1tech.Helper.CustomToast;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Helper.Utils;
import com.A1tech.Model.ClientResponse;
import com.A1tech.Model.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private  View view;
    private  EditText mobile, password;
    private  Button loginButton;
    private  TextView forgotPassword, signUp;
    private  CheckBox show_hide_password;
    private  ConstraintLayout constraintLayout;
    private Animation shakeAnimation;
    private  FragmentManager fragmentManager;
    Gson gson = new Gson();
    View progress;
    LocalStorage localStorage;
    String userString;
    User user;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login2, container, false);
        initViews();
        setListeners();
        return view;
    }
    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        progress = view.findViewById(R.id.progress_bar);
        mobile = view.findViewById(R.id.mobileLog);
        password = view.findViewById(R.id.l_password);
        loginButton = view.findViewById(R.id.btnlog);
        forgotPassword = view.findViewById(R.id.forgot_password);
        signUp = view.findViewById(R.id.createAccount);
//        show_hide_password = view
//                .findViewById(R.id.show_hide_password);
        constraintLayout = view.findViewById(R.id.login_layout);

        localStorage = new LocalStorage(getContext());
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        userString = localStorage.getUserLogin();
        user = gson.fromJson(userString, User.class);
        Log.d("User", userString);
        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }
    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
//        show_hide_password
//                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//                    @Override
//                    public void onCheckedChanged(CompoundButton button,
//                                                 boolean isChecked) {
//
//                        // If it is checkec then show password else hide
//                        // password
//                        if (isChecked) {
//
//                            show_hide_password.setText(R.string.hide_pwd);// change
//                            // checkbox
//                            // text
//
//                            password.setInputType(InputType.TYPE_CLASS_TEXT);
//                            password.setTransformationMethod(HideReturnsTransformationMethod
//                                    .getInstance());// show password
//                        } else {
//                            show_hide_password.setText(R.string.show_pwd);// change
//                            // checkbox
//                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password




    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlog:
//                startActivity(new Intent(getContext(), MainActivity.class));
//                getActivity().finish();
                checkValidation();
                break;

            case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                Utils.ForgotPassword_Fragment).commit();
                break;
            case R.id.createAccount:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignUpFragment(),
                                Utils.SignUp_Fragment).commit();
                break;
        }

    }
    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
        final String getMobile = mobile.getText().toString();
        final String getPassword = password.getText().toString();
        // Check for both field is empty or not
        if (getMobile.equals("") || getMobile.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            constraintLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Telefon raqam va Parol kiritilmagan");
            vibrate(200);

        }else if (getMobile.equals("")|| getMobile.length() == 0){
            mobile.setError("Telefon raqam kiritilmagan");
        }
        else {
            user = new User(getPassword,getMobile);
            registerUser(getPassword,getMobile);
        }
    }

    private void registerUser(String getPassword, String getMobile) {
        showProgressDialog();
        Call<ClientResponse> call = RetrofitClient.getData(getContext()).loginUser(getPassword, getMobile);
        call.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {

                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    ClientResponse clientResponse = response.body();
                     if (clientResponse.getStatus() == 200) {
                        String userString = gson.toJson(clientResponse.getUser());
                        localStorage.createUserLoginSession(userString);
                        Log.d("TAG", userString);
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    } else if(clientResponse.getStatus() == 201){
                        new CustomToast().Show_Toast(getActivity(), view,
                            "Parolingiz Xato");
                    } else {    // Alex ga oytish garak TELEFON RAQAM hato bolsa 202 m ishqilib status qaytarsin serverda Hozircha 500 qaytadi akan TELEFON RAQAM xato bo'lib parol dogri bolsa!!!!!!!!
                         new CustomToast().Show_Toast(getActivity(),view,
                                 "Telefon raqam ro" +
                                         "Ro'yhatdan o'tmagan");
                     }

//                    }else {
//                        new CustomToast().Successfull_Toast(getActivity(), view,
//                                "Xush kelibsiz");
//                    }
                }
                else {
                    new CustomToast().Show_Toast(getActivity(), view,
                            "Xato ma'lumot kiritdingiz");
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


    private void login(String getMobile,String getPassword) {

    }

    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }


    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }
}