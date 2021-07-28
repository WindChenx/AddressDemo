package com.wind.addressdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "person.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table address(id integer primary key autoincrement,"+
                "name varchar(20),"+
                "phone varchar(20),"+
                "address varchar(50))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(SQLiteDatabase db){

        db.execSQL("insert into address values(3,'宋先生','17679861286','上海市徐汇区')");
        db.execSQL("insert into address values(4,'刘女士','17646892576','上海市闵行区')");
        db.execSQL("insert into address values(5,'吴先生','13276382672','上海市闵行区')");

    }
}
