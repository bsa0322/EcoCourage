package com.example.ecocourage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FragmentUseStampCard extends Fragment {
    private MainActivity activity;

    public void onAttach(Context context){
        super.onAttach(context);
        activity=(MainActivity)getActivity();
    }
    public void onDetach(){
        super.onDetach();
        activity=null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_usestampcard,container,false);

        PaymentDatabaseManager databaseManager_payment = new PaymentDatabaseManager(getActivity().getApplicationContext());

        Button aeyoung_usestampcard = v.findViewById(R.id.aeyoung_usestampcard);

        //마지막 결제내역 데이터 가져오기
        Payment pm = databaseManager_payment.lastPayment();

        aeyoung_usestampcard.setText(Double.toString(pm.getSale())+"%를 할인해주세요");

        //애용카드 버튼 클릭시
        aeyoung_usestampcard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //메인화면으로 가게 하기
            }
        });

        return v;
    }
}
