package com.A1tech.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.A1tech.ADS.R;
import com.A1tech.Activity.MainActivity;
import com.A1tech.Activity.ProductTypeActivity;

public class HomeFragment extends Fragment {
    private CardView card_btn;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("ADS");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        card_btn = (CardView)v.findViewById(R.id.card_oziq_ovqat);
        card_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProductTypeActivity.class);
                startActivity(intent);

            }
        });
    return v;
    }
}