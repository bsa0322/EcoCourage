package com.example.ecocourage;

import android.app.Application;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class FragmentStampManage extends Fragment {
    private ListView stampManegeList;//스탬프관리 리스트뷰
    private StampManageAdapter adapter;

    //데이터베이스
    EcoStoreDatabaseManager ecoStoreDatabaseManager = null;
    PaymentDatabaseManager databaseManager = null;
    ArrayList<Payment> pay_list = new ArrayList<Payment>();

    public FragmentStampManage(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_stampmanage,container,false);

        databaseManager = new PaymentDatabaseManager(getActivity().getApplicationContext());
        ecoStoreDatabaseManager = new EcoStoreDatabaseManager(getActivity().getApplicationContext());

        pay_list=databaseManager.selectList();

        int ecoStore_idx = Fragment_myecostore.ecoStore_idx; //애용가게 데이터베이스 인덱스 번호

        for(int i=0;;){
            if(pay_list.isEmpty() || i == pay_list.size()) break;
            if(ecoStore_idx != pay_list.get(i).getStoreIdx())
            {
                pay_list.remove(i);
            }
            else{
                i++;
            }
        }

        ArrayList<EcoStore> es_list = ecoStoreDatabaseManager.selectList();
        EcoStore es = es_list.get(ecoStore_idx-1);

        TextView allstampnumber = (TextView)v.findViewById(R.id.allstampnumber);
        TextView storeStamp = (TextView)v.findViewById(R.id.storeStamp);
        TextView storeSale = (TextView)v.findViewById(R.id.storeSale);

        int stamp = es.getCustomerStamp();
        int store_stamp = es.getStoreStamp();
        double store_sale = es.getStoreSale();

        allstampnumber.setText(Integer.toString(stamp));
        storeStamp.setText(Integer.toString(store_stamp));
        storeSale.setText(Double.toString(store_sale));
    
        //리스트뷰에 역순으로 채우기 위해 리스트 리벌스
        Collections.reverse(pay_list);

        adapter=new StampManageAdapter();
        stampManegeList=(ListView) v.findViewById(R.id.stampManageList);
        stampManegeList.setAdapter(adapter);



        return v;
    }

    class StampManageAdapter extends BaseAdapter {
        //어댑터에 추가된 데이터 저장 arraylist

        public StampManageAdapter() {
        }

        @Override
        public int getCount() {return pay_list.size();

        }

        @Override
        public Object getItem(int position) {
            return pay_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();


            //convertview참조
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fragment_stampmanagelistitem, parent, false);
            }

            TextView tv_stampList = (TextView) convertView.findViewById(R.id.stamplist);//사용변화량
            TextView tv_date = (TextView) convertView.findViewById(R.id.date); // 날짜

            //position에 위치한 데이터 참조 획득
            final Payment pm = pay_list.get(position);

            //아이템 내 각 위젯에 데이터 반영
            tv_stampList.setText(Integer.toString(pm.getStamp()));

            //날짜설정
            int date = pm.getIdx();
            int month = date / 30 + 1;
            int day = date % 30;
            tv_date.setText(Integer.toString(month)+"."+Integer.toString(day));

            return convertView;
        }
    }
}
