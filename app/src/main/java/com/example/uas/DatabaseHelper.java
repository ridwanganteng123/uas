package com.example.uas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Uas";
    private static final String TABLE_NAME = "tbl_uas";
    private static final String KEY_JUDUL = "judul";
    private static final String KEY_DESKIRPSI = "deskripsi";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUasTable = "Create Table " + TABLE_NAME + "(" + KEY_JUDUL + " TEXT PRIMARY KEY," + KEY_DESKIRPSI + " TEXT " + ")";
        db.execSQL(createUasTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = ("drop table if exists " + TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);

    }
    public List<PersonBean> selectUserData(){
        ArrayList<PersonBean> userList = new ArrayList<PersonBean>();

        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {KEY_JUDUL,KEY_DESKIRPSI};

        Cursor c = db.query(TABLE_NAME,columns,null,null,null,null,null);

        while(c.moveToNext()){
            String judul = c.getString(0);
            String deskripsi = c.getString(1);


            PersonBean s = new PersonBean();
            s.setJudul(judul);
            s.setDeskripsi(deskripsi);
            userList.add(s);
        }

        return userList;
    }


    public void update(PersonBean personBean){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put(KEY_JUDUL,personBean.getJudul());
        val.put(KEY_DESKIRPSI,personBean.getDeskripsi());

        String whereClause = KEY_JUDUL+"='"+personBean.getJudul()+"'";
        db.update(TABLE_NAME,val,whereClause,null);
    }

    public void insert(PersonBean personBean){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(KEY_JUDUL,personBean.getJudul());
        val.put(KEY_DESKIRPSI,personBean.getDeskripsi());

        db.insert(TABLE_NAME,null,val);
    }

    //delete oleh tri
    public void delete(String judul){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = KEY_JUDUL+"='"+judul+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }
}
