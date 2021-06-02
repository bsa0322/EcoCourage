package com.example.ecocourage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentSignupEcostore extends Fragment {

    View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.fragment_signup_ecostore,container,false);

        Button btn_add = v.findViewById(R.id.btn_add);
        EditText nameEditText = (EditText)v.findViewById(R.id.edittext_name);
        EditText telEditText = (EditText)v.findViewById(R.id.edittext_tel);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString().trim();
                String tel = telEditText.getText().toString().trim();

                /*
                //비어있으면
                if(name.isEmpty()){
                    MyUtil.showMessageDialog(getActivity().getApplicationContext(),"","이름을 입력하세요");
                    nameEditText.setText("");
                    nameEditText.requestFocus();
                    return;
                }

                if(tel.isEmpty()){
                    MyUtil.showMessageDialog(getActivity().getApplicationContext(),"","전화번호를 입력하세요");
                    telEditText.setText("");
                    telEditText.requestFocus();
                    return;
                }
                */

                EcoStoreDatabaseManager databaseManager = new EcoStoreDatabaseManager(getActivity().getApplicationContext());

                //데이터 저장
                EcoStore es = new EcoStore(name,tel);

                //데이터베이스에 등록
                databaseManager.insert(es);

                /*
                //결과 전송 데이터 포장
                EcoStore es = new EcoStore(name,tel);

                Intent data = new Intent();
                data.putExtra("es",es);

                //호출 측 onActivityResult 로 결과 전송
                getActivity().setResult(Activity.RESULT_OK, data);

                getActivity().finish();

                 */
            }
        });

        return v;
    }
    /*
    public void onClickOk(View view)
    {
        EditText nameEditText = (EditText)v.findViewById(R.id.edittext_name);
        EditText telEditText = (EditText)v.findViewById(R.id.edittext_tel);

        String name = nameEditText.getText().toString().trim();
        String tel = telEditText.getText().toString().trim();

        //비어있으면
        if(name.isEmpty()){
            MyUtil.showMessageDialog(getActivity().getApplicationContext(),"","이름을 입력하세요");
            nameEditText.setText("");
            nameEditText.requestFocus();
            return;
        }

        if(tel.isEmpty()){
            MyUtil.showMessageDialog(getActivity().getApplicationContext(),"","전화번호를 입력하세요");
            telEditText.setText("");
            telEditText.requestFocus();
            return;
        }

        EcoStoreDatabaseManager databaseManager = new EcoStoreDatabaseManager(getActivity().getApplicationContext());

        //데이터 저장
        EcoStore es = new EcoStore(name,tel);

        //데이터베이스에 등록
        databaseManager.insert(es);

        //결과 전송 데이터 포장
        EcoStore es = new EcoStore(name,tel);

        Intent data = new Intent();
        data.putExtra("es",es);

        //호출 측 onActivityResult 로 결과 전송
        getActivity().setResult(Activity.RESULT_OK, data);

        getActivity().finish();

    } */
}