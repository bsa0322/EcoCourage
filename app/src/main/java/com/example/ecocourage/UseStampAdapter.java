package com.example.ecocourage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UseStampAdapter extends RecyclerView.Adapter<UseStampAdapter.CustomViewHolder> {

    private ArrayList<UseStampStoreData> arrayList;


    public UseStampAdapter(ArrayList<UseStampStoreData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UseStampAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_usestamplistitem,parent,false);
        CustomViewHolder holder =new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UseStampAdapter.CustomViewHolder holder, int position) {
        holder.storeName.setText(arrayList.get(position).getStoreName());
        holder.storeAddress.setText(arrayList.get(position).getStoreAddress());
        holder.myStampNumber.setText(arrayList.get(position).getMyStampNumber());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curName = holder.storeName.getText().toString();//클릭한 현재이름
                Toast.makeText(v.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null!=arrayList?arrayList.size():0);
    }

    public void useStamp(int position){
        try{
            //버튼구현

        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView storeName;
        protected TextView storeAddress;
        protected TextView myStampNumber;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.storeName=(TextView) itemView.findViewById(R.id.storeName);
            this.storeAddress=(TextView) itemView.findViewById(R.id.storeAddress);
            this.myStampNumber=(TextView) itemView.findViewById(R.id.myStampNumber);



        }
    }
}
