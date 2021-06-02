package com.example.ecocourage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;


import java.util.ArrayList;


public class FragmentUseStamp extends Fragment {
    private ListView useStampList;//스탬프사용 가게 리스트뷰
    private UseStampAdapter adapter;
    //private ArrayList<UseStampStoreData> s_list;

    //데이터베이스
    EcoStoreDatabaseManager databaseManager = null;
    ArrayList<EcoStore> eco_list = null;
    ListView listView;
    
    public FragmentUseStamp(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_usestamp, container, false);

        databaseManager = new EcoStoreDatabaseManager(getActivity().getApplicationContext());

        //데이터 가져옴
        eco_list = databaseManager.selectList();

        adapter=new UseStampAdapter();
        useStampList=(ListView) v.findViewById(R.id.useStampList);
        useStampList.setAdapter(adapter);

      return v;
    }


    class UseStampAdapter extends BaseAdapter {
        //어댑터에 추가된 데이터 저장 arraylist

        public UseStampAdapter(){
        }


        @Override
        public int getCount() {
            return eco_list.size();
        }

        @Override
        public Object getItem(int position) {
            return eco_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos=position;
            final Context context=parent.getContext();

            //convertview참조
            if(convertView==null)
            {
                LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView =inflater.inflate(R.layout.fragment_usestamplistitem,parent,false);
            }

            TextView tv_storeName=(TextView) convertView.findViewById(R.id.storeName);
            TextView tv_storeAddress=(TextView) convertView.findViewById(R.id.storeAddress);
            TextView tv_myStampNumber=(TextView) convertView.findViewById(R.id.myStampNumber);
            //버튼도 구현?

            //position에 위치한 데이터 참조 획득
            final EcoStore es = eco_list.get(position);

            //아이템 내 각 위젯에 데이터 반영
            tv_storeName.setText(es.getName());
            tv_storeAddress.setText(es.getAddress());
            tv_myStampNumber.setText(Integer.toString(es.getCustomerStamp()));

            return convertView;
        }
    }

}