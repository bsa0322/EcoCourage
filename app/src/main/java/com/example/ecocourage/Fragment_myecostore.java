package com.example.ecocourage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment_myecostore extends Fragment {

    private MainActivity activity;
    View v;

    EcoStoreDatabaseManager databaseManager = null;
    ArrayList<EcoStore> eco_list = null;
    ListView listView;

    public void onAttach(Context context){
        super.onAttach(context);
        activity=(MainActivity)getActivity();
    }
    public void onDetach(){
        super.onDetach();
        activity=null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v=inflater.inflate(R.layout.fragment_myecostore,container,false);

        databaseManager = new EcoStoreDatabaseManager(getActivity().getApplicationContext());
        listView = v.findViewById(R.id.listView);

        display_ecostroe_list();

        //등록 버튼 클릭시
        Button btn_add = v.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                activity.onFragmentChange(6);
            }
        });

        return v;
    }

    /*
    //등록 버튼 클릭시
    public void onClick(View view){
        //Intent intent = new Intent(getActivity().getApplicationContext(), FragmentSignupEcostore.class);
        //getActivity().startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //'등록'버튼이 보낸 requestCode
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                EcoStore es = (EcoStore) data.getSerializableExtra("es");

                //데이터베이스에 등록
                databaseManager.insert(es);

                //갱신목록을 출력
                display_ecostroe_list();
            }
        }

    }
    */
    public void display_ecostroe_list(){
        //데이터 목록 가져오기
        eco_list = databaseManager.selectList();
        listView.setAdapter(new EcoStoreAdapter());
    }

    //ListView 배치 Adapter
    class EcoStoreAdapter extends BaseAdapter{
        @Override
        public int getCount(){
            return eco_list.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.ecostore_list, null);
            }

            //컨트롤의 참조값
            TextView textView_name = convertView.findViewById(R.id.textView_name);
            TextView textView_address = convertView.findViewById(R.id.textView_address);
            Button button_delete = convertView.findViewById(R.id.button_delete);
            Button button_stamp=convertView.findViewById(R.id.button_stamp);

            //배치해야될 데이터 1개 얻어오기
            final EcoStore es = eco_list.get(position);

            //컨트롤에 배치
            textView_name.setText(es.getName());
            textView_address.setText(es.getAddress());
            //이미지추가 구현못함

            //삭제버튼 클릭시
            button_delete.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v){
                    /*
                    //Handler 통해서 "삭제하시겠습니까?" 메세지 띄우기
                    Handler handler = new Handler(){
                        public void handleMessage(Message msg){
                            super.handleMessage(msg);

                            if(msg.what == 1){
                                databaseManager.delete(es.getIdx());
                                //삭제 후 데이터목록 갱신
                                display_ecostroe_list();
                            }
                        }
                    };
                    MyUtil.showConfirmDialog(getActivity().getApplicationContext(), "[삭제]", "정말 삭제하시겠습니까?",handler);
                    */
                    databaseManager.delete(es.getIdx());
                    //삭제 후 데이터목록 갱신
                    display_ecostroe_list();
                }
            });

            //내 애용가게에서 스탬프 버튼
            button_stamp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onFragmentChange(3);
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