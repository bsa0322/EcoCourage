package com.example.ecocourage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EcoStoreDatabaseManager {
    //db명과 table명 설정
    static final String dbName = "ecoStore_db";
    static final String tbName = "ecoStore_tb";
    Context context;

    public EcoStoreDatabaseManager(Context context){
        this.context = context;
    }

    //db 호출하기
    SQLiteDatabase getDatabase(){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);

        //테이블이 존재하지 않으면 새로 생성
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tbName + " ( idx Integer primary key autoincrement,name text,address text,customerCourage integer,customerStamp integer);");

        return db;
    }

    //insert
    public int insert(EcoStore es){
        int res = 0;
        SQLiteDatabase db = null;
        try{
            db = getDatabase();
            String sql = String.format("insert into %s(name,address,customerCourage,customerStamp) values('%s','%s','%d','%d')", tbName, es.getName(), es.getAddress(), es.getCustomerCourage(), es.getCustomerStamp());
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
    public int update(EcoStore es){
        int res = 0;
        SQLiteDatabase db = null;
        try{
            db = getDatabase();
            String sql = String.format("update %s set name='%s',address='%s', customerCourage='%d', customerStamp='%d' where idx=%d", tbName, es.getName(), es.getAddress(), es.getCustomerCourage(), es.getCustomerStamp(), es.getIdx() );
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

    public ArrayList<EcoStore> selectList(){
        ArrayList<EcoStore> list = new ArrayList<EcoStore>();

        SQLiteDatabase db = null;

        //Cursor 개념 익혀두기
        Cursor cursor = null;
        String [] col_names = {"idx","name","address","customerCourage","customerStamp"};

        try{
            db = getDatabase();
            cursor = db.query(tbName, col_names, null, null, null, null, null);
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{
                        int idx = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String address = cursor.getString(2);

                        list.add(new EcoStore(idx,name,address));
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
}
