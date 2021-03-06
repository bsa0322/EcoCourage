package com.example.ecocourage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentSearch fragmentSearch = new FragmentSearch();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentStore fragmentStore = new FragmentStore();
    private FragmentChallenge fragmentChallenge = new FragmentChallenge();
    private FragmentCommunication fragmentCommunication = new FragmentCommunication();
    private FragmentAeyoungCard fragmentAeyoungCard=new FragmentAeyoungCard();
    private FragmentUseStamp fragmentUseStamp=new FragmentUseStamp();
    private Fragment_myecostore fragment_myecostore = new Fragment_myecostore();
    private FragmentSignupEcostore fragmentSignupEcostore = new FragmentSignupEcostore();
    private FragmentUseStampCard fragmentUseStampcard=new FragmentUseStampCard();
    private FragmentStampManage fragmentStampManage=new FragmentStampManage();
    private FragmentCourage fragmentCourage = new FragmentCourage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
        
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }
    
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem){
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            
            switch(menuItem.getItemId())
            {
                case R.id.searchItem:
                    transaction.replace(R.id.frameLayout, fragmentSearch).commitAllowingStateLoss();
                    break;
                case R.id.homeItem:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.storeItem:
                    transaction.replace(R.id.frameLayout, fragmentStore).commitAllowingStateLoss();
                    break;
                case R.id.challengeItem:
                    transaction.replace(R.id.frameLayout, fragmentChallenge).commitAllowingStateLoss();
                    break;
                case R.id.communicationItem:
                    transaction.replace(R.id.frameLayout, fragmentCommunication).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

    public void onFragmentChange(int index){
        if(index==0) getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentAeyoungCard).commit();//????????????
        else if(index==1) getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentUseStamp).commit();//???????????????
        else if(index==5) getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment_myecostore).commit(); //??????????????????
        else if(index==6) getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentSignupEcostore).commit(); //???????????? ??????
        else if(index==2) getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentUseStampcard).commit();//????????? ???????????? ?????? ????????? ????????? ????????????
        else if(index==3) getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentStampManage).commit();//??? ?????????????????? ??????????????? ????????? ????????? ?????????
        else if(index==4) getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentCourage).commit();//????????????(????????????)
        else if(index==7) getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentHome).commit();//????????????
    }


}
