package com.example.ecocourage;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

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

        //애니메이션
        Animation animation = AnimationUtils.loadAnimation(v.getContext(),R.anim.rotate);
        Button aeyoungBtn=v.findViewById(R.id.aeyoung_usestampcard);
        aeyoungBtn.startAnimation(animation);

        //팝업창
        final View popupView = getLayoutInflater().inflate(R.layout.checkpopup, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        PaymentDatabaseManager databaseManager_payment = new PaymentDatabaseManager(getActivity().getApplicationContext());
        EcoStoreDatabaseManager databaseManager_ecoStore = new EcoStoreDatabaseManager(getActivity().getApplicationContext());

        Button aeyoung_usestampcard = v.findViewById(R.id.aeyoung_usestampcard);

        //마지막 결제내역 데이터 가져오기
        Payment pm = databaseManager_payment.lastPayment();

        aeyoung_usestampcard.setText(Double.toString(pm.getSale())+"%를 할인해주세요");

        //애용카드 버튼 클릭시
        aeyoung_usestampcard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //용기점수 올라감
                FragmentHome.myCourageNumber++;

                //팝업창
                builder.setView(popupView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                //확인버튼
                Button checkbtn = popupView.findViewById(R.id.checkbtn);
                checkbtn.setOnClickListener(new Button.OnClickListener(){
                    public void onClick(View v){
                        //메인화면으로 돌아가기
                        activity.onFragmentChange(7);
                        //팝업창 닫힘
                        alertDialog.dismiss();
                    }
                });


            }
        });


        return v;
    }
}
