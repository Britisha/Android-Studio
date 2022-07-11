package com.example.meetingscheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbManager extends SQLiteOpenHelper
{
    public static final String DbName="Meeting.db";
    public static final String MEETINGAGENDA_COLUMN_DATE = "date";
    public static final String MEETINGAGENDA_COLUMN_TIME = "time";
    public static final String MEETINGAGENDA_COLUMN_AGENDA = "agenda";
    public static final String MEETINGAGENDA_COLUMN_ID = "id";
    public DbManager( Context context) {
        super(context, "Meeting.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table users(username TEXT primary key,password TEXT)");
        db.execSQL("create table meetingagenda(id integer primary key autoincrement,username TEXT ,date TEXT,time TEXT,agenda TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists meetingagenda");
    }

    public Boolean insertData(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=db.insert("users",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=?",new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=? and password=?",new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean insertMeetingData(String username,String date,String time,String agenda)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("agenda",agenda);
        long result=db.insert("meetingagenda",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkmeeting(String username,String date,String time)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from meetingagenda where username=? and date=? and time=?",new String[] {username,date,time});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkdate(String date,String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from meetingagenda where date=? and username=?",new String[] {date,username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public ArrayList<String> displaydate(String date, String username) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select date from meetingagenda where date=? and username=?", new String[]{date, username});
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            array_list.add(cursor.getString(cursor.getColumnIndex(MEETINGAGENDA_COLUMN_DATE)));
            cursor.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> displaytime(String date, String username) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select time from meetingagenda where date=? and username=?", new String[]{date, username});
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            array_list.add(cursor.getString(cursor.getColumnIndex(MEETINGAGENDA_COLUMN_TIME)));
            cursor.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> displayagenda(String date, String username) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select agenda from meetingagenda where date=? and username=?", new String[]{date, username});
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            array_list.add(cursor.getString(cursor.getColumnIndex(MEETINGAGENDA_COLUMN_AGENDA)));
            cursor.moveToNext();
        }
        return array_list;
    }

    public Boolean deletemeeting(String username,String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result=db.delete("meetingagenda",
                "username=? and id=?",new String[]{username,id});
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean updateMeeting (String id,String username,String date,String time,String agenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("agenda",agenda);
        long result=db.update("meetingagenda", contentValues, "id=? and username=?",new String[]{id,username});
        if(result==-1)
            return false;
        else
            return true;
    }
    public ArrayList<String> displayid(String username,String date) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id from meetingagenda where username=? and date=?", new String[]{username,date});
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            array_list.add(cursor.getString(cursor.getColumnIndex(MEETINGAGENDA_COLUMN_ID)));
            cursor.moveToNext();
        }
        return array_list;
    }
    public Boolean checkid(String username,String id,String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select id from meetingagenda where username=? and id=? and date=?",new String[] {username,id,date});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

