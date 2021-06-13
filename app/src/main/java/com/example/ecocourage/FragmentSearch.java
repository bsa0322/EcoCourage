package com.example.ecocourage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;

public class FragmentSearch extends Fragment implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private MapView mapView; //지도

    //애용가게리스트
    EcoStoreDatabaseManager databaseManager = null;
    ArrayList<EcoStore> eco_list = null;
    ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_search,container,false);

        mapView = v.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        //애용가게 리스트
        listView = v.findViewById(R.id.listView);
        databaseManager = new EcoStoreDatabaseManager(getActivity().getApplicationContext());
        listView = v.findViewById(R.id.listView);
        display_ecostroe_list();
        //
        return v;
    }

    public void display_ecostroe_list(){
        //데이터 목록 가져오기
        eco_list = databaseManager.selectList();
        listView.setAdapter(new EcoStoreAdapter());
    }

    //ListView 배치 Adapter
    class EcoStoreAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return eco_list.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.rankstore_list, null);
            }

            //컨트롤의 참조값
            TextView textView_name = convertView.findViewById(R.id.textView_name);
            TextView textView_address = convertView.findViewById(R.id.textView_address);
            Button button_info = convertView.findViewById(R.id.button_info);

            //배치해야될 데이터 1개 얻어오기
            final EcoStore es = eco_list.get(position);

            //컨트롤에 배치
            textView_name.setText(es.getName());
            textView_address.setText(es.getAddress());
            //이미지추가 구현못함

            return convertView;
        }

        public Object getItem(int position){
            return null;
        }

        public long getItemId(int position){
            return 0;
        }
    }

    public void OnRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
            if(!locationSource.isActivated()) {//권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        Marker marker = new Marker();
        marker.setPosition(new LatLng(37.48809,126.76913));
        marker.setMap(naverMap);

    }
}
