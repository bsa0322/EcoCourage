package com.example.ecocourage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class FragmentHome extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton aeyoung =(ImageButton)v.findViewById(R.id.aeyoung);
        Button addstore=(Button) v.findViewById(R.id.addstore);
        Button mystore=(Button) v.findViewById(R.id.mystore);
        Button add_store=(Button) v.findViewById(R.id.add_store);
        Button usestamp=(Button) v.findViewById(R.id.usestamp);


        aeyoung.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            }
        });

        addstore.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            }
        });

        mystore.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            }
        });

        add_store.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            }
        });


        usestamp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            }
        });



        return v;
    }
}
