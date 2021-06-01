package com.example.ecocourage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentUseStamp extends Fragment {

    private ArrayList<UseStampStoreData> arrayList;
    private UseStampAdapter useStampAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        recyclerView =(RecyclerView)getActivity().findViewById(R.id.usestampstore);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList =new ArrayList<>();

        useStampAdapter =new UseStampAdapter(arrayList);
        recyclerView.setAdapter(useStampAdapter);

        return inflater.inflate(R.layout.fragment_usestamp,container,false);
    }
}
