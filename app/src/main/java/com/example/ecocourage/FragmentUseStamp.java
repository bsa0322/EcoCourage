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


import org.w3c.dom.Text;

import java.util.ArrayList;


public class FragmentUseStamp extends Fragment {
    private ListView useStampList;//스탬프사용 가게 리스트뷰
    private UseStampAdapter adapter;
    private MainActivity activity;

    public void onAttach(Context context){
        super.onAttach(context);
        activity=(MainActivity)getActivity();
    }
    public void onDetach(){
        super.onDetach();
        activity=null;
    }

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
            TextView tv_storeStamp=(TextView) convertView.findViewById(R.id.storeStamp);
            TextView tv_storeSale=(TextView) convertView.findViewById(R.id.storeSale);

            //스탬프 사용하기 버튼
            Button usebutton=(Button) convertView.findViewById(R.id.useButton);

            //position에 위치한 데이터 참조 획득
            final EcoStore es = eco_list.get(position);

            //아이템 내 각 위젯에 데이터 반영
            tv_storeName.setText(es.getName());
            tv_storeAddress.setText(es.getAddress());
            tv_myStampNumber.setText(Integer.toString(es.getCustomerStamp()));
            tv_storeStamp.setText(Integer.toString(es.getStoreStamp()));
            tv_storeSale.setText(Double.toString(es.getStoreSale()));


            //사용하기 버튼 누르면 n%할인해주세요 애용카드로
            usebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onFragmentChange(2);

                    //결제내역 남기기
                    int storeIdx = es.getIdx();
                    String name = es.getName();
                    double sale = es.getStoreSale();
                    int stamp = es.getStoreStamp() * (-1);
                    int courage = 1;
                    PaymentDatabaseManager databaseManager_payment = new PaymentDatabaseManager(getActivity().getApplicationContext());

                    //데이터 저장-스탬프감소
                    Payment pm = new Payment(storeIdx,name,sale,stamp,0);

                    //데이터베이스에 등록
                    databaseManager_payment.insert(pm);

                    //데이터 저장-스탬프증가
                    pm = new Payment(storeIdx,name,sale,1,courage);

                    //데이터베이스에 등록
                    databaseManager_payment.insert(pm);

                    //스탬프 감소
                    int customerStamp = es.getCustomerStamp() + stamp + courage;
                    //업데이트 데이터 다시 만들기
                    EcoStore es_up = new EcoStore(es.getIdx(),es.getName(),es.getAddress(),es.getCustomerCourage(),customerStamp,es.getRanking(),es.getStoreStamp(),es.getStoreSale());
                    //애용가게 데이터 업데이트
                    databaseManager.update(es_up);
                }
            });


            return convertView;
        }
    }

}