package com.example.ecocourage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FragmentAeyoungCard extends Fragment {
    Button aeyoungBtn;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_aeyoungcard,container,false);
        
        Animation animation = AnimationUtils.loadAnimation(v.getContext(),R.anim.rotate);
        aeyoungBtn=v.findViewById(R.id.aeyoung_aeyoungcard);

        aeyoungBtn.startAnimation(animation);

        return v;
    }
}
