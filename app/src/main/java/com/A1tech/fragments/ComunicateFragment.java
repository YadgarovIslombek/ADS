package com.A1tech.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.A1tech.ADS.R;


public class ComunicateFragment extends Fragment {
ImageView img_telegram, phoneImg, emailImg, saytImg,adressImg;
TextView phoneNum, emailText, saytText, adressText;


    public ComunicateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_comunicate, container, false);
        img_telegram = v.findViewById(R.id.img_telegram);
        phoneImg = v.findViewById(R.id.phoneImg);
        phoneNum = v.findViewById(R.id.phoneNum);
        emailImg = v.findViewById(R.id.emailImg);
        emailText = v.findViewById(R.id.emailText);
        saytImg = v.findViewById(R.id.saytImg);
        saytText = v.findViewById(R.id.saytText);
        adressImg= v.findViewById(R.id.adressImg);
        adressText = v.findViewById(R.id.adressText);
        img_telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://t.me/MSanatbek"));
                startActivity(intent);
            }
        });
        phoneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "+998622288080";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent);
                }
        });
        phoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "+998622288080";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });
        emailImg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View v) {
                String email = "sanatbek@gmail.com";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.setData(Uri.parse("mailto:"+email));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
                try {

                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.d("Email error:",e.toString());
                }
            }
        });
        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "sanatbek@gmail.com";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.setData(Uri.parse("mailto:"+email));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
                try {

                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.d("Email error:",e.toString());
                }
            }
        });
        saytImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.a1tech.uz";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        saytText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.a1tech.uz";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        adressImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("http://maps.google.com/maps?saddr=41.5693.28&daddr=60.6314.12"));
//                startActivity(intent);
                double latitude =  41.569328;
                double longitude = 60.631412;
                String label = "ABC Label";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(intent);
//                41.569328, 60.63141
            }
        });
        adressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude =  41.569328;
                double longitude = 60.631412;
                String label = "ABC Label";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    return v;
    }
}