package com.A1tech.fragments;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.A1tech.ADS.R;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.Client;
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
        name = view.findViewById(R.id.name);
        mobile = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.phone);
        address = view.findViewById(R.id.address);
        localStorage = new LocalStorage(getContext());
        Client user = gson.fromJson(localStorage.getUserLogin(), Client.class);
       // name.setText(user.getUserName());
//        mobile.setText(user.getPhoneNumber());
     //   email.setText(user.getEmail());
      //  address.setText(user.getMessage());

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Shahsiy kabinet");

    }
}