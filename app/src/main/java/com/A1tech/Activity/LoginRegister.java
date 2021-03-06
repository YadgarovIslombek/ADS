package com.A1tech.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.A1tech.ADS.R;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Helper.Utils;
import com.A1tech.fragments.LoginFragment;

public class LoginRegister extends AppCompatActivity {
        private static FragmentManager fragmentManager;
        LocalStorage localStorage;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            localStorage = new LocalStorage(getApplicationContext());
            if (localStorage.isUserLoggedIn()) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_login_register);
            fragmentManager = getSupportFragmentManager();


            // If savedinstnacestate is null then replace login fragment
            if (savedInstanceState == null) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer,   new LoginFragment(),
                                Utils.Login_Fragment).commit();
            }

            // On close icon click finish activity
//            findViewById(R.id.close_activity).setOnClickListener(
//                    new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View arg0) {
//                            finish();
//
//                        }
//                    });

        }

        // Replace Login Fragment with animation
        public void replaceLoginFragment() {
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                    .replace(R.id.frameContainer, new LoginFragment(),
                            Utils.Login_Fragment).commit();
        }

        @Override
        public void onBackPressed() {

            // Find the tag of signup and forgot password fragment
            Fragment SignUp_Fragment = fragmentManager
                    .findFragmentByTag(Utils.SignUp_Fragment);
            Fragment ForgotPassword_Fragment = fragmentManager
                    .findFragmentByTag(Utils.ForgotPassword_Fragment);

            // Check if both are null or not
            // If both are not null then replace login fragment else do backpressed
            // task

            if (SignUp_Fragment != null)
                replaceLoginFragment();
            else if (ForgotPassword_Fragment != null)
                replaceLoginFragment();
            else
                super.onBackPressed();
        }
}