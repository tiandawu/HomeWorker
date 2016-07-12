package com.zbj.myapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tiandawu on 2016/7/12.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper helper;
    private static final String DATABASE_NAME = "homeworker.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String PRIMERY_KEY = "_id";

    /**
     * 创建number表的sql语句
     */
    private static final String SQL_CREATE_NUMBER = "CREATE TABLE IF NOT EXISTS " + DBColumns.NUMBER_TABLE_NAME +
            "(" + PRIMERY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DBColumns.NUMBER_ID + TEXT_TYPE + COMMA_SEP
            + DBColumns.NUMBER_ODD + TEXT_TYPE + ")";

    /**
     * 删除number表的sql语句
     */
    private static final String SQL_DELETE_NUMBER = "DROP TABLE IF EXISTS " + DBColumns.NUMBER_TABLE_NAME + ";";


    /**
     * 私有构造函数，单例模式返回对象
     *
     * @param context
     */
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context) {
        if (helper == null) {
            helper = new DBHelper(context);
        }
        return helper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_NUMBER);
    }

    /**
     * 数据库升级调用
     *
     * @param db
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_NUMBER);
        onCreate(db);
    }

    /**
     * 数据库降级调用
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
