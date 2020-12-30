package com.A1tech.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.A1tech.ADS.R;


public class ComunicateFragment extends Fragment {
ImageView img_telegram;


    public ComunicateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_comunicate, container, false);
        img_telegram = v.findViewById(R.id.img_telegram);
        img_telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://t.me/MSanatbek"));
                startActivity(intent);
            }
        });
    return v;
    }
}