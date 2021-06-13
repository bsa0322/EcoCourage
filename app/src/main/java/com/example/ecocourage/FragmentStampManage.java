package com.example.ecocourage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentStampManage extends Fragment {
    private ListView stampManegeList;//스탬프관리 리스트뷰
    //private StampManageAdapter adapter;
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_myecostore,container,false);


        //adapter=new StampManageAdapter();
        //stampManegeList=(ListView) v.findViewById(R.id.stampManageList);
       // stampManegeList.setAdapter(adapter);


        return v;
    }


}
