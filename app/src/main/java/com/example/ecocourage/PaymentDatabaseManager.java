package com.example.ecocourage;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PaymentDatabaseManager {
    //db명과 table명 설정
    static final String dbName = "payment_db";
    static final String tbName = "payment_tb";
    Context context;

    public PaymentDatabaseManager(Context context){
        this.context = context;
    }

    //db 호출하기
    SQLiteDatabase getDatabase(){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);

        //테이블이 존재하지 않으면 새로 생성
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tbName + " ( idx Integer primary key autoincrement,storeIdx integer,name text,sale double,stamp integer,courage integer);");

        return db;
    }

    //insert
    public int insert(Payment pm){
        int res = 0;
        SQLiteDatabase db = null;
        try{
            db = getDatabase();
            String sql = String.format("insert into %s(storeIdx,name,sale,stamp,courage) values('%d','%s','%f','%d','%d')", tbName, pm.getStoreIdx(), pm.getName(), pm.getSale(), pm.getStamp(), pm.getCourage());
            db.execSQL(sql);
            res = 1;
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            if(db!=null)
                db.close();
        }
        return res;
    }

    //delete
    public int delete(int idx){
        int res = 0;
        SQLiteDatabase db = null;
        try{
            db = getDatabase();
            String sql = String.format("delete from %s where idx=%d", tbName, idx);
            db.execSQL(sql);
            res = 1;
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            if(db!=null)
                db.close();
        }
        return res;
    }

    //update
    public int update(Payment pm){
        int res = 0;
        SQLiteDatabase db = null;
        try{
            db = getDatabase();
            String sql = String.format("update %s set storeIdx='%d',name='%s',sale='%f',stamp='%d',courage='%d' where idx=%d",tbName,pm.getStoreIdx(),pm.getName(),pm.getSale(),pm.getStamp(),pm.getCourage() );
            db.execSQL(sql);
            res = 1;
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            if(db!=null)
                db.close();
        }
        return res;
    }

    public ArrayList<Payment> selectList(){
        ArrayList<Payment> list = new ArrayList<Payment>();

        SQLiteDatabase db = null;

        //Cursor 개념 익혀두기->데이터베이스에서 데이터 가져올 때 쓰는 것
        Cursor cursor = null;
        String [] col_names = {"idx","storeIdx","name","sale","stamp","courage"};

        try{
            db = getDatabase();
            cursor = db.query(tbName, col_names, null, null, null, null, null);
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{
                        int idx = cursor.getInt(0);
                        int storeIdx = cursor.getInt(1);
                        String name = cursor.getString(2);
                        double sale = cursor.getDouble(3);
                        int stamp = cursor.getInt(4);
                        int courage = cursor.getInt(5);
                        list.add(new Payment(idx,storeIdx,name,sale,stamp,courage));
                    }while(cursor.moveToNext());
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(cursor!=null)
                cursor.close();
            if(db!=null)
                db.close();
        }
        return list;
    }
    
    public Payment lastPayment(){
        Payment pm = new Payment();

        SQLiteDatabase db = null;

        //Cursor 개념 익혀두기
        Cursor cursor = null;
        String [] col_names = {"idx","storeIdx","name","sale","stamp","courage"};

        try{
            db = getDatabase();
            cursor = db.query(tbName, col_names, null, null, null, null, null);
            if(cursor!=null){
                if(cursor.moveToLast()){
                    int idx = cursor.getInt(0);
                    int storeIdx = cursor.getInt(1);
                    String name = cursor.getString(2);
                    double sale = cursor.getDouble(3);
                    int stamp = cursor.getInt(4);
                    int courage = cursor.getInt(5);
                    pm = new Payment(idx,storeIdx,name,sale,stamp,courage);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(cursor!=null)
                cursor.close();
            if(db!=null)
                db.close();
        }
        return pm;
    }
}
