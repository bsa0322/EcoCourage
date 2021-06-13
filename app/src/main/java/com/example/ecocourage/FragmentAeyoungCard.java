package com.example.ecocourage;

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

public class FragmentAeyoungCard extends Fragment {
    private MainActivity activity;

    public void onAttach(Context context){
        super.onAttach(context);
        activity=(MainActivity)getActivity();
    }
    public void onDetach(){
        super.onDetach();
        activity=null;
    }

    Button aeyoungBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_aeyoungcard,container,false);
        
        //애니메이션
        Animation animation = AnimationUtils.loadAnimation(v.getContext(),R.anim.rotate);
        aeyoungBtn=v.findViewById(R.id.aeyoung_aeyoungcard);

        aeyoungBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인화면으로 돌아가기
                activity.onFragmentChange(7);
                //랜덤으로 가게 정해서 스탬프 ++
                EcoStoreDatabaseManager ecoStoreDatabaseManager = new EcoStoreDatabaseManager(getActivity().getApplicationContext());
                ArrayList<EcoStore> es_list = ecoStoreDatabaseManager.selectList();

                int scope = es_list.size();

                if(scope != 0){
                    int i=(int)(Math.random()*scope);

                    //랜덤으로 뽑힌 가게 스탬프 + 1
                    EcoStore es = es_list.get(i);
                    int stamp = es.getCustomerStamp() + 1;

                    //결제내역 남기기
                    int storeIdx = es.getIdx();
                    String name = es.getName();

                    PaymentDatabaseManager databaseManager_payment = new PaymentDatabaseManager(getActivity().getApplicationContext());

                    //데이터 저장-스탬프증가
                    Payment pm = new Payment(storeIdx,name,es.getStoreSale(),1,1);

                    //데이터베이스에 등록
                    databaseManager_payment.insert(pm);

                    //업데이트 데이터 다시 만들기
                    EcoStore es_up = new EcoStore(es.getIdx(),es.getName(),es.getAddress(),es.getCustomerCourage(),stamp,es.getRanking(),es.getStoreStamp(),es.getStoreSale());
                    //애용가게 데이터 업데이트
                    ecoStoreDatabaseManager.update(es_up);
                }

            }
        });

        aeyoungBtn.startAnimation(animation);

        return v;
    }
}
