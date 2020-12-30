package com.A1tech.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.A1tech.ADS.R;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.User;
import com.google.gson.Gson;


public class ProfileFragment extends Fragment {



    TextView name, email, mobile, address;

    LocalStorage localStorage;
    Gson gson = new Gson();

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


//        name = view.findViewById(R.id.name);
//        email = view.findViewById(R.id.email);
//        mobile = view.findViewById(R.id.mobile);
//        address = view.findViewById(R.id.address);
//        localStorage = new LocalStorage(getContext());
//
//        User user = gson.fromJson(localStorage.getUserLogin(), User.class);
//        name.setText(user.getUserName());
//        email.setText(user.getEmail());
//        mobile.setText(user.getPhoneNumber());
//        address.setText(user.getEmail());

        return view;
    }
}