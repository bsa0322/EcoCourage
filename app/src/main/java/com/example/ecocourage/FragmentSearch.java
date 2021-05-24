package com.example.ecocourage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.naver.maps.map.MapView;

public class FragmentSearch extends Fragment {
    private MapView mapView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_search,container,false);

        mapView = v.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        return v;
    }

}
