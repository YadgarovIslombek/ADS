package com.A1tech.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.A1tech.ADS.R;
import com.A1tech.Activity.BaseActivity;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.Client;
import com.google.gson.Gson;

public class MalumotFragment extends Fragment {
    Context context;
    TextView txt_pyment, txt_address, lat, longt;
    EditText txt_qosh_address, txt_name, txt_mobile;
    String _txt_address, _lat, _longt, _txt_qosh_address, _txt_name, _txt_mobile, userString;
    LocalStorage localStorage;
    Gson gson;
    Client client;
    View progress;
    BaseActivity baseActivity;

    public MalumotFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_malumot, container, false);
        Bundle bundle = this.getArguments();
        txt_pyment = v.findViewById(R.id.txt_pyment);
        txt_address = v.findViewById(R.id.txt_address);
        txt_qosh_address = v.findViewById(R.id.txt_qosh_address);
        txt_mobile = v.findViewById(R.id.txt_mobile);
        txt_name = v.findViewById(R.id.txt_name);
        lat = v.findViewById(R.id.lat);
        longt = v.findViewById(R.id.lang);
        _txt_address = bundle.getString("address");
        _lat = bundle.getString("lat");
        _longt = bundle.getString("longt");
        lat.setText(_lat);
        longt.setText(_longt);
        txt_address.setText(_txt_address);
        localStorage = new LocalStorage(getContext());
        gson = new Gson();
        userString = localStorage.getUserLogin();
        client = gson.fromJson(userString, Client.class);
        Log.d("User String : ", userString);
        if (client != null) {
            txt_name.setText(client.getUserName());
            txt_mobile.setText(client.getPhoneNumber());
        }
        context = container.getContext();
        txt_pyment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _txt_name = txt_name.getText().toString();
                _lat = lat.getText().toString();
                _longt = longt.getText().toString();
                _txt_address = txt_address.getText().toString();
                    _txt_qosh_address = txt_qosh_address.getText().toString();
                _txt_mobile = txt_mobile.getText().toString();
                txt_address.setClickable(false);
                txt_name.setClickable(false);
                if(_txt_qosh_address.length() == 0){
                    txt_qosh_address.setError("Iltimos manzilni aniqlashtiting");
                    txt_qosh_address.requestFocus();
                }else{
//                    User userAddress = new User(Double.parseDouble(_lat), Double.parseDouble(_longt), _txt_mobile, String.valueOf(_txt_name),_txt_qosh_address,_txt_address);
//                    String user_address = gson.toJson(userAddress);
//                    localStorage.createUserLoginSession(user_address);
//                    Log.d("test", user_address);
//                    saveUserAddress(userAddress);
                }

            }
        });
        return v;

    }

    private void saveUserAddress(Client clientAddress) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
        ft.replace(R.id.content_frame, new TolovFragment());
        ft.commit();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}