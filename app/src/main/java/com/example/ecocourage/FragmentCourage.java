package com.example.ecocourage;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentCourage extends Fragment {

    View v;

    PaymentDatabaseManager databaseManager = null;
    ArrayList<Payment> pay_list = null;
    ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v=inflater.inflate(R.layout.fragment_courage,container,false);

        databaseManager = new PaymentDatabaseManager(getActivity().getApplicationContext());
        listView = v.findViewById(R.id.listView);

        display_payment_list();

        return v;
    }

    public void display_payment_list(){
        //데이터 목록 가져오기
        pay_list = databaseManager.selectList();
        listView.setAdapter(new PaymentAdapter());
    }

    //ListView 배치 Adapter
    class PaymentAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return pay_list.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.courage_list, null);
            }

            //컨트롤의 참조값
            TextView textView_name = convertView.findViewById(R.id.textView_name);
            Button button_delete = convertView.findViewById(R.id.button_delete);

            //배치해야될 데이터 1개 얻어오기
            final Payment pm = pay_list.get(position);

            //컨트롤에 배치
            textView_name.setText(Integer.toString(pm.getStoreIdx()));
            //이미지추가 구현못함

            //삭제버튼 클릭시
            button_delete.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v){

                    databaseManager.delete(pm.getIdx());
                    //삭제 후 데이터목록 갱신
                    display_payment_list();
                }
            });

            return convertView;
        }

        public Object getItem(int position){
            return null;
        }

        public long getItemId(int position){
            return 0;
        }
    }
}