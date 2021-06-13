package com.example.ecocourage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentHome extends Fragment {
    private FragmentAeyoungCard fragmentAeyoungCard=new FragmentAeyoungCard();
    private MainActivity activity;
    public static int myCourageNumber=0;

    public void onAttach(Context context){
        super.onAttach(context);
        activity=(MainActivity)getActivity();
    }
    public void onDetach(){
        super.onDetach();
        activity=null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton aeyoung =(ImageButton)v.findViewById(R.id.aeyoung);
        Button addcard=(Button) v.findViewById(R.id.addcard);
        Button mystore=(Button) v.findViewById(R.id.mystore);
        Button addstore=(Button) v.findViewById(R.id.addstore);
        Button usestamp=(Button) v.findViewById(R.id.usestamp);
        Button mycourage=(Button) v.findViewById(R.id.mycourage);
        
        //홈화면의 용기점수
        mycourage.setText(Integer.toString(myCourageNumber)+"점");
        
        aeyoung.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.onFragmentChange(0);
                //용기점수 올라감
                myCourageNumber++;
            }
        });

        addcard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        mystore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { activity.onFragmentChange(5);
            }
        });
        addstore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { activity.onFragmentChange(6);
            }
        });
        usestamp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.onFragmentChange(1);
            }
        });

        mycourage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {activity.onFragmentChange(4); }
        });



        return v;
    }
}
