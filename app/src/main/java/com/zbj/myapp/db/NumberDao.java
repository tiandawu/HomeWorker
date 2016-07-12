package com.zbj.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zbj.myapp.bean.OddNumber;

import java.util.ArrayList;

/**
 * Created by tiandawu on 2016/7/12.
 */
public class NumberDao {

    private DBHelper helper;

    public NumberDao(Context context) {
        helper = DBHelper.getInstance(context);
    }


    /**
     * 插入一条数据到数据库
     */
    public long insert(OddNumber oddNumber) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBColumns.NUMBER_ODD, oddNumber.getOddNumber());
        values.put(DBColumns.NUMBER_ID, oddNumber.getId());
        Log.e("tt", "vlues = " + oddNumber.getOddNumber());
        Log.e("tt", "oddId = " + oddNumber.getId());
        long id = db.insert(DBColumns.NUMBER_TABLE_NAME, null, values);
//        Log.e("tt", "id = " + id);
        db.close();
        return id;
    }

    /**
     * 插入一个集合中的所有数据
     *
     * @param oddNumbers
     */
    public void insertListData(ArrayList<OddNumber> oddNumbers) {
        SQLiteDatabase db = helper.getWritableDatabase();
        for (OddNumber oddNumber : oddNumbers) {
            ContentValues values = new ContentValues();
            values.put(DBColumns.NUMBER_ODD, oddNumber.getOddNumber());
            values.put(DBColumns.NUMBER_ID, oddNumber.getId());
//            Log.e("tt", "vlues = " + oddNumber.getOddNumber());
//            Log.e("tt", "oddId = " + oddNumber.getId());
//            String sql = "insert into " + DBColumns.NUMBER_TABLE_NAME +"("+DBColumns.NUMBER_ID+","+DBColumns.NUMBER_ODD+")"
//                    + " values(" + oddNumber.getId() + "," + oddNumber.getOddNumber() + ");";
            db.insert(DBColumns.NUMBER_TABLE_NAME, null, values);
//            Log.e("tt", "sql = " + sql);
//            db.execSQL(sql);
        }
        db.close();
    }

    /**
     * 根据id修改一条数据
     *
     * @param id        需要修改行的id
     * @param oddNumber 新的数据对象
     */
    public int update(String id, OddNumber oddNumber) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBColumns.NUMBER_ODD, oddNumber.getOddNumber());
        int result = db.update(DBColumns.NUMBER_TABLE_NAME, values, "id=?", new String[]{id});
        db.close();
        return result;
    }

    /**
     * 根据id删除一条数据
     *
     * @param id
     * @return
     */
    public int delete(String id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int reslut = db.delete(DBColumns.NUMBER_TABLE_NAME, "id=?", new String[]{id});
        db.close();
        return reslut;
    }


    /**
     * 根据id查询一条数据
     *
     * @param id
     * @return
     */
    public OddNumber queryOne(String id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DBColumns.NUMBER_TABLE_NAME, null, "id=?", new String[]{id}, null, null, null);
        OddNumber oddNumber = null;
        if (cursor.moveToNext()) {
            oddNumber = new OddNumber();
            oddNumber.setOddNumber(cursor.getString(cursor.getColumnIndex(DBColumns.NUMBER_ODD)));
            oddNumber.setId(cursor.getString(cursor.getColumnIndex(DBColumns.NUMBER_ID)));
        }
        cursor.close();
        db.close();
        return oddNumber;
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public ArrayList<OddNumber> queryAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DBColumns.NUMBER_TABLE_NAME, null, null, null, null, null, null);
        ArrayList<OddNumber> list = new ArrayList<>();
        OddNumber oddNumber = null;
        while (cursor.moveToNext()) {
            oddNumber = new OddNumber();
            oddNumber.setId(cursor.getString(cursor.getColumnIndex(DBColumns.NUMBER_ID)));
            oddNumber.setOddNumber(cursor.getString(cursor.getColumnIndex(DBColumns.NUMBER_ODD)));
            list.add(oddNumber);
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 删除数据库
     */

    public boolean deleteDatabase() {
        SQLiteDatabase db = helper.getWritableDatabase();
        int result = db.delete(DBColumns.NUMBER_TABLE_NAME, null, null);
        db.close();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

}
